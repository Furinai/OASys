package cn.linter.oasys.chatting.handler;

import cn.linter.oasys.chatting.entity.Message;
import cn.linter.oasys.chatting.messaging.MessagePublisher;
import cn.linter.oasys.chatting.utils.ObjectStringConverter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.support.atomic.RedisAtomicLong;
import org.springframework.web.reactive.socket.WebSocketHandler;
import org.springframework.web.reactive.socket.WebSocketMessage;
import org.springframework.web.reactive.socket.WebSocketSession;
import reactor.core.publisher.DirectProcessor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.FluxSink;
import reactor.core.publisher.Mono;

@Slf4j
public class ChatWebSocketHandler implements WebSocketHandler {

    private final DirectProcessor<Message> messageDirectProcessor;
    private final FluxSink<Message> messageFluxSink;
    private final MessagePublisher messagePublisher;
    private final RedisAtomicLong activeUserCounter;

    public ChatWebSocketHandler(DirectProcessor<Message> messageDirectProcessor, MessagePublisher messagePublisher, RedisAtomicLong activeUserCounter) {
        this.messageDirectProcessor = messageDirectProcessor;
        this.messageFluxSink = messageDirectProcessor.sink();
        this.messagePublisher = messagePublisher;
        this.activeUserCounter = activeUserCounter;
    }

    @Override
    public Mono<Void> handle(WebSocketSession webSocketSession) {
        Flux<WebSocketMessage> sendMessageFlux = messageDirectProcessor.flatMap(ObjectStringConverter::objectToString)
                .map(webSocketSession::textMessage)
                .doOnError(throwable -> log.info("Error Occurred while sending message to WebSocket.", throwable));
        Mono<Void> outputMessage = webSocketSession.send(sendMessageFlux);

        Mono<Void> inputMessage = webSocketSession.receive()
                .concatMap(webSocketMessage -> messagePublisher.publishMessage(webSocketMessage.getPayloadAsText()))
                .doOnSubscribe(subscription -> {
                    long activeUserCount = activeUserCounter.incrementAndGet();
                    log.debug("User '{}' Connected. Total Active Users: {}", webSocketSession.getId(), activeUserCount);
                    //chatMessageFluxSink.next(new Message(0, "CONNECTED", "CONNECTED", activeUserCount));
                })
                .doOnError(throwable -> log.info("Error Occurred while sending message to Redis.", throwable))
                .doFinally(signalType -> {
                    long activeUserCount = activeUserCounter.decrementAndGet();
                    log.debug("User '{}' Disconnected. Total Active Users: {}", webSocketSession.getId(), activeUserCount);
                    //chatMessageFluxSink.next(new Message(0, "DISCONNECTED", "DISCONNECTED", activeUserCount));
                })
                .then();

        return Mono.zip(inputMessage, outputMessage).then();
    }

    public Mono<Void> sendMessage(Message message) {
        return Mono.fromSupplier(() -> messageFluxSink.next(message)).then();
    }

}
