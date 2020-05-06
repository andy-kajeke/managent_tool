package com.planetsystems.tela.data.clockOut;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.planetsystems.tela.data.ClockIn.SyncClockInTableConstants;

@Entity(tableName = SyncClockOutTableConstant.TABLE_NAME)
public class SyncClockOut {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = SyncClockOutTableConstant.PRIMARY_KEY)
    @NonNull
    private int primaryKey;

    @ColumnInfo(name = SyncClockOutTableConstant.DATE_COLUMN_NAME)
    private String date;

    @ColumnInfo(name = SyncClockOutTableConstant.DAY_COLUMN_NAME)
    private String day;

    @ColumnInfo(name = SyncClockOutTableConstant.TIME_COLUMN_NAME)
    private String time;

    @ColumnInfo(name = SyncClockOutTableConstant.COMMENT_COLUMN_NAME)
    private String comment;

    @NonNull
    @ColumnInfo(name = SyncClockOutTableConstant.EMPLOYEE_NUMBER_COLUMN_NAME)
    private String employeeNo;

    @ColumnInfo(name = SyncClockOutTableConstant.EMPLOYEE_ID_COLUMN_NAME)
    private String employeeId;

    @ColumnInfo(name = SyncClockOutTableConstant.LATITUDE_COLUMN_NAME)
    private String latitude;

    @ColumnInfo(name = SyncClockOutTableConstant.LONGITUDE_COLUMN_NAME)
    private String longitude;

    @ColumnInfo(name = SyncClockOutTableConstant.STATUS_COLUMN_NAME)
    private String status;

    @ColumnInfo(name = SyncClockOutTableConstant.SCHOOL_ID_COLUMN_NAME)
    private String schoolId;

    @ColumnInfo(name = SyncClockOutTableConstant.SCHOOL_NAME_COLUMN_NAME)
    private String schoolName;

    @ColumnInfo(name = SyncClockOutTableConstant.FIRST_NAME_COLUMN_NAME)
    private String firstName;

    @ColumnInfo(name = SyncClockOutTableConstant.LAST_NAME_TABLE_NAME)
    private String lastName;

    @ColumnInfo(name = SyncClockOutTableConstant.IS_UPLOADED_COLUMN_NAME)
    private boolean isUploaded;

    public int getPrimaryKey() {
        return primaryKey;
    }

    public void setPrimaryKey(int primaryKey) {
        this.primaryKey = primaryKey;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @NonNull
    public String getEmployeeNo() {
        return employeeNo;
    }

    public void setEmployeeNo(@NonNull String employeeNo) {
        this.employeeNo = employeeNo;
    }

    @NonNull
    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(@NonNull String employeeId) {
        this.employeeId = employeeId;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public SyncClockOut(String date, String day, String time, String comment, @NonNull String employeeNo, String employeeId, String latitude, String longitude, String status, String schoolId, String schoolName, String firstName, String lastName) {
        this.date = date;
        this.day = day;
        this.time = time;
        this.comment = comment;
        this.employeeNo = employeeNo;
        this.employeeId = employeeId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.status = status;
        this.schoolId = schoolId;
        this.schoolName = schoolName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.isUploaded = false;
    }

    public boolean isUploaded() {
        return isUploaded;
    }

    public void setUploaded(boolean uploaded) {
        isUploaded = uploaded;
    }

    @Override
    public String toString() {
        return "SyncClockOut{" +
                "primaryKey=" + primaryKey +
                ", date='" + date + '\'' +
                ", day='" + day + '\'' +
                ", time='" + time + '\'' +
                ", comment='" + comment + '\'' +
                ", employeeNo='" + employeeNo + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                ", status='" + status + '\'' +
                ", schoolId='" + schoolId + '\'' +
                ", schoolName='" + schoolName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isUploaded=" + isUploaded +
                '}';
    }
}
