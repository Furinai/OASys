package cn.linter.oasys.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@Alias("Leave")
public class Leave implements Serializable {
    private int id;
    private User user;
    private LocalDate beginDate;
    private LocalDate endDate;
    private String reason;
    private String comment;
    private String type;
    private int status;
    private static final long serialVersionUID = 1L;
}
