package cn.linter.oasys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Alias("File")
public class File implements Serializable {
    private int id;
    private String name;
    private String path;
    private String type;
    private String size;
    private User user;
    private int parentId;
    private boolean personal;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime createdTime;
    private static final long serialVersionUID = 1L;
}
