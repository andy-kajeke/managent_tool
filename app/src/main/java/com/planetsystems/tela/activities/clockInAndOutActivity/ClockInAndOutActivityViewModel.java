package com.planetsystems.tela.activities.clockInAndOutActivity;

import android.app.Application;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.planetsystems.tela.MainRepository;
import com.planetsystems.tela.data.ClockIn.ClockInRepository;
import com.planetsystems.tela.data.ClockIn.SyncClockIn;
import com.planetsystems.tela.data.Teacher.SyncTeacher;
import com.planetsystems.tela.data.Teacher.TeacherRepository;
import com.planetsystems.tela.data.clockOut.ClockOutRepository;
import com.planetsystems.tela.data.clockOut.SyncClockOut;
import com.planetsystems.tela.utils.DynamicData;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class ClockInAndOutActivityViewModel extends AndroidViewModel {
    private ClockOutRepository clockOutRepository;
    private ClockInRepository clockInRepository;
    private TeacherRepository teacherRepository;
    //private String schoolID;

    public ClockInAndOutActivityViewModel(@NonNull Application application) {
        super(application);
        MainRepository mainRepository = MainRepository.getInstance(application);
        clockOutRepository = mainRepository.getClockOutRepository();
        clockInRepository = mainRepository.getClockInRepository();
        teacherRepository = mainRepository.getTeachersRepository();

    }

    public SyncTeacher clockOutTeacherWithEmployeeID(String staffID, String staffComment) {
        try {
            SyncClockOut clockOut = clockOutRepository.getSyncClockOutByEmployeeNumberAndDate(staffID, DynamicData.getDate());
            SyncTeacher teacher = teacherRepository.getTeacherWithEmployeeNumber(staffID);
            SyncClockIn clockIn = clockInRepository.getSyncClockInByEmployeeIDAndDate(staffID, DynamicData.getDate());
            if (clockIn != null ){
                if ((clockOut == null) && (teacher != null )) {
                    clockOutRepository.insertSynClockOut(
                            new SyncClockOut(
                                    DynamicData.getDate(),
                                    DynamicData.getDay(),
                                    DynamicData.getTime(),
                                    staffComment,
                                    teacher.getEmployeeNumber(),
                                    teacher.getEmployeeNumber(),
                                    DynamicData.getLatitude(),
                                    DynamicData.getLongitude(),
                                    DynamicData.getSchoolID(),
                                    DynamicData.getSchoolName(),
                                    teacher.getFirstName(),
                                    teacher.getLastName(),
                                    teacher.getFingerPrint()
                            )
                    );
                    return  teacher;
                } else {
                    return null;
                }
            } else {
                Toast.makeText(getApplication(), "Teacher Did not Clock In", Toast.LENGTH_SHORT).show();
                return null;
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public TeacherWrapper clockInTeacherWithEmployeeNumber(String employeeNumber) {
        try {
            SyncClockIn clockIn = clockInRepository.getSyncClockInByEmployeeIDAndDate(employeeNumber, DynamicData.getDate());
            SyncTeacher syncTeacher = teacherRepository.getTeacherWithEmployeeNumber(employeeNumber);
            if (syncTeacher == null) {
                return new TeacherWrapper("No Record for: " + employeeNumber, null);
            } else {
                if (clockIn == null ) {
                    clockInRepository.synClockInTeacher(new SyncClockIn(
                            syncTeacher.getEmployeeNumber(),
                            syncTeacher.getEmployeeNumber(),
                            syncTeacher.getFirstName(),
                            syncTeacher.getLastName(),
                            DynamicData.getLatitude(),
                            DynamicData.getLongitude(),
                            DynamicData.getDate(),
                            DynamicData.getDay(),
                            DynamicData.getTime(),
                            DynamicData.getSchoolID(),
                            syncTeacher.getFingerPrint()
                    ));
                    return new TeacherWrapper("Clocked In Successfully", syncTeacher);
                } else return new TeacherWrapper("Clocked Already at: " + clockIn.getClockInTime(), syncTeacher);
            }
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }
        return new TeacherWrapper("Unknown Error Occurred", null);
    }

    public static class TeacherWrapper {
        private final String msg;
        private final SyncTeacher teacher;

        public TeacherWrapper(String msg, SyncTeacher teacher){
            this.msg = msg;
            this.teacher = teacher;
        }

        public String getMsg() {
            return msg;
        }

        public SyncTeacher getTeacher() {
            return teacher;
        }
    }
}
