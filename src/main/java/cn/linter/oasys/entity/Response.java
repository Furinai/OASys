package cn.linter.oasys.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Response implements Serializable {
    private String status;
    private String message;
    private Long total;
    private Object object;
    private static final long serialVersionUID = 1L;

    public Response() {
    }

    public Response(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public Response(String status, Object object) {
        this.status = status;
        this.object = object;
    }

    public Response(String status, String message, Object object) {
        this.status = status;
        this.message = message;
        this.object = object;
    }

    public Response(String status, Long total, Object object) {
        this.status = status;
        this.total = total;
        this.object = object;
    }

    public Response(String status, String message, Long total, Object object) {
        this.status = status;
        this.message = message;
        this.total = total;
        this.object = object;
    }
}
