package cn.linter.oasys.entity;

import org.apache.ibatis.type.Alias;

import java.sql.Timestamp;

@Alias("Notice")
public class Notice {
    private int id;
    private String content;
    private boolean hasRead;
    private Timestamp createTime;
    private String receiverName;

    public Notice() {
    }

    public Notice(String content, Timestamp createTime, String receiverName) {
        this.content = content;
        this.createTime = createTime;
        this.receiverName = receiverName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public boolean isHasRead() {
        return hasRead;
    }

    public void setHasRead(boolean hasRead) {
        this.hasRead = hasRead;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }
}
