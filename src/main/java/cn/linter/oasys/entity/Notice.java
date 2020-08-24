package cn.linter.oasys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Alias("Notice")
public class Notice implements Serializable {
    private int id;
    private String content;
    private boolean hasRead;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createdTime;
    private String receiverName;
    private static final long serialVersionUID = 1L;
}
