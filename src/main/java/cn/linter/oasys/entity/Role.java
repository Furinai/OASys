package cn.linter.oasys.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Alias("Role")
public class Role implements Serializable {
    private int id;
    private String name;
    private static final long serialVersionUID = 1L;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
