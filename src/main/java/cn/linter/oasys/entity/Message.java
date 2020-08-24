package cn.linter.oasys.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Message implements Serializable {
    private int id;
    private String username;
    private String avatar;
    private String content;
    private static final long serialVersionUID = 1L;
}
