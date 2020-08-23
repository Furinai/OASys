package cn.linter.oasys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.util.Date;

@Data
@Alias("Leave")
public class Leave implements Serializable {
    private int id;
    private User user;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date beginDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private String reason;
    private String comment;
    private String type;
    private int status;
    private static final long serialVersionUID = 1L;
}
