package com.planetsystems.tela.data.timeOnTask;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = SynTimeOnTaskConstant.TABLE_NAME)
public class SynTimeOnTask {
    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = SynTimeOnTaskConstant.ID)
    private int id;

    @ColumnInfo(name = SynTimeOnTaskConstant.DATE_CREATED)
    private String dateCreated;

    @ColumnInfo(name = SynTimeOnTaskConstant.DATE_UPDATED)
    private String dateUpdated;

    @ColumnInfo(name = SynTimeOnTaskConstant.STATUS)
    private String status;

    @ColumnInfo(name = SynTimeOnTaskConstant.ACTION_STATUS)
    private String actionStatus;

    @ColumnInfo(name = SynTimeOnTaskConstant.COMMENT)
    private String comment;

    @ColumnInfo(name = SynTimeOnTaskConstant.EMPLOYEE_ID)
    private String employeeId;

    @ColumnInfo(name = SynTimeOnTaskConstant.EMPLOYEE_NUMBER)
    private String employeeNumber;

    @ColumnInfo(name = SynTimeOnTaskConstant.TASK_ID)
    private String taskId;

    @ColumnInfo(name = SynTimeOnTaskConstant.TRANSACTION_TIME)
    private String transactionTime;

    @ColumnInfo(name = SynTimeOnTaskConstant.TRANSACTION_DATE)
    private String transactionDate;

    @ColumnInfo(name = SynTimeOnTaskConstant.SUPERVISION_STATUS)
    private String supervisionStatus;

    @ColumnInfo(name = SynTimeOnTaskConstant.IN_TIME)
    private String inTime;

    @ColumnInfo(name = SynTimeOnTaskConstant.SUPERVISION_ID)
    private String supervisionId;

    @ColumnInfo(name = SynTimeOnTaskConstant.EMPLOYEE_FIRST_NAME)
    private String employeeFirstName;

    @ColumnInfo(name = SynTimeOnTaskConstant.EMPLOYEE_LAST_NAME)
    private String employeeLastName;

    @ColumnInfo(name = SynTimeOnTaskConstant.END_TIME)
    private String endTime;

    @ColumnInfo(name = SynTimeOnTaskConstant.START_TIME)
    private String startTime;

    @ColumnInfo(name = SynTimeOnTaskConstant.TASK_NAME)
    private String taskName;

    @ColumnInfo(name = SynTimeOnTaskConstant.IS_UPLOADED_COLUMN_NAME_TEACHER)
    private boolean isUploaded_teacher;

    @ColumnInfo(name = SynTimeOnTaskConstant.IS_UPLOADED_COLUMN_NAME_SUPERVISOR)
    private boolean isUploaded_supervisor;

    public SynTimeOnTask(String dateCreated, String dateUpdated, String status, String actionStatus, String comment, String employeeId, String employeeNumber, String taskId,
                         String transactionTime, String transactionDate,String supervisionStatus, String inTime, String supervisionId, String employeeFirstName,
                         String employeeLastName, String endTime, String startTime, String taskName, boolean isUploaded_teacher, boolean isUploaded_supervisor) {
        //this.id = id;
        this.dateCreated = dateCreated;
        this.dateUpdated = dateUpdated;
        this.status = status;
        this.actionStatus = actionStatus;
        this.comment = comment;
        this.employeeId = employeeId;
        this.employeeNumber = employeeNumber;
        this.taskId = taskId;
        this.transactionTime = transactionTime;
        this.supervisionStatus = supervisionStatus;
        this.inTime = inTime;
        this.supervisionId = supervisionId;
        this.transactionDate = transactionDate;
        this.employeeFirstName = employeeFirstName;
        this.employeeLastName = employeeLastName;
        this.endTime = endTime;
        this.startTime = startTime;
        this.taskName = taskName;
        this.isUploaded_teacher = isUploaded_teacher;
        this.isUploaded_supervisor = isUploaded_supervisor;
    }

    @NonNull
    public int getId() {
        return id;
    }

    public void setId(@NonNull int id) {
        this.id = id;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(String dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getActionStatus() {
        return actionStatus;
    }

    public void setActionStatus(String actionStatus) {
        this.actionStatus = actionStatus;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public void setEmployeeNumber(String employeeNumber) {
        this.employeeNumber = employeeNumber;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getSupervisionStatus() {
        return supervisionStatus;
    }

    public void setSupervisionStatus(String supervisionStatus) {
        this.supervisionStatus = supervisionStatus;
    }

    public String getInTime() {
        return inTime;
    }

    public void setInTime(String inTime) {
        this.inTime = inTime;
    }

    public String getSupervisionId() {
        return supervisionId;
    }

    public void setSupervisionId(String supervisionId) {
        this.supervisionId = supervisionId;
    }

    public String getEmployeeFirstName() {
        return employeeFirstName;
    }

    public void setEmployeeFirstName(String employeeFirstName) {
        this.employeeFirstName = employeeFirstName;
    }

    public String getEmployeeLastName() {
        return employeeLastName;
    }

    public void setEmployeeLastName(String employeeLastName) {
        this.employeeLastName = employeeLastName;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean isUploaded_teacher() {
        return isUploaded_teacher;
    }

    public void setUploaded_teacher(boolean uploaded_teacher) {
        isUploaded_teacher = uploaded_teacher;
    }

    public boolean isUploaded_supervisor() {
        return isUploaded_supervisor;
    }

    public void setUploaded_supervisor(boolean uploaded_supervisor) {
        isUploaded_supervisor = uploaded_supervisor;
    }
}
