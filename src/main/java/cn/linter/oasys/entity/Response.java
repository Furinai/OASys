package cn.linter.oasys.entity;

import java.io.Serializable;

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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
