package cn.linter.oasys.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Alias("Attendance")
public class Attendance implements Serializable {
    private int id;
    private int userId;
    private LocalDate signInDate;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime signInTime;
    @JsonFormat(pattern = "HH:mm")
    private LocalTime signOutTime;
    private boolean hasSignOut;
    private static final long serialVersionUID = 1L;
}
