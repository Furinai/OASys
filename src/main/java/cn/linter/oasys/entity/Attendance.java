package cn.linter.oasys.entity;

import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@Data
@Alias("Attendance")
public class Attendance implements Serializable {
    private int id;
    private int userId;
    private Date signInDate;
    private Time signInTime;
    private Time signOutTime;
    private boolean signOut;
    private static final long serialVersionUID = 1L;
}
