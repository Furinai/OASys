package cn.linter.oasys.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;

@Alias("Attendance")
public class Attendance implements Serializable {
    private int id;
    private int userId;
    private Date signInDate;
    private Time signInTime;
    private Time signOutTime;
    private boolean signOut;
    private static final long serialVersionUID = 1L;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getSignInDate() {
        return signInDate;
    }

    public void setSignInDate(Date signInDate) {
        this.signInDate = signInDate;
    }

    public Time getSignInTime() {
        return signInTime;
    }

    public void setSignInTime(Time signInTime) {
        this.signInTime = signInTime;
    }

    public Time getSignOutTime() {
        return signOutTime;
    }

    public void setSignOutTime(Time signOutTime) {
        this.signOutTime = signOutTime;
    }

    public boolean isSignOut() {
        return signOut;
    }

    public void setSignOut(boolean signOut) {
        this.signOut = signOut;
    }
}
