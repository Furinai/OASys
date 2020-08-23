package cn.linter.oasys.entity;

import lombok.Data;

@Data
public class Response<T> {
    private String status;
    private String message;
    private T data;

    public Response(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public Response(String status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public static <T> Response<T> success(String message) {
        return new Response<>("success", message);
    }

    public static <T> Response<T> error(String message) {
        return new Response<>("error", message);
    }

    public static <T> Response<T> success(String message, T data) {
        return new Response<>("success", message, data);
    }
}
