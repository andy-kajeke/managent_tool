package com.planetsystems.tela.activities.test;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.planetsystems.tela.R;
import com.planetsystems.tela.activities.clockInAndOutActivity.ClockInAndOutActivityViewModel;
import com.planetsystems.tela.activities.staff.regularStaff.home.TeacherHomeActivityViewModel;
import com.planetsystems.tela.data.Teacher.SyncTeacher;
import com.planetsystems.tela.data.clockOut.SyncClockOut;
import com.planetsystems.tela.data.schoolClasses.SyncSchoolClasses;
import com.planetsystems.tela.data.schoolMaterials.SchoolMaterials;
import com.planetsystems.tela.data.timeOnTask.SynTimeOnTask;
import com.planetsystems.tela.data.timetable.SyncTimeTable;

import java.util.List;
import java.util.Locale;

public class TestActivity extends AppCompatActivity implements LocationListener{
    TextView textView;

    LocationManager locationManager;
    double lng;
    double lat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        textView = findViewById(R.id.textView);

        TestActivityViewModel testActivityViewModel = new ViewModelProvider(this).get(TestActivityViewModel.class);
        testActivityViewModel.allClockOuts().observe(this, new Observer<List<SyncClockOut>>() {
            @Override
            public void onChanged(List<SyncClockOut> syncClockOuts) {
                Toast.makeText(getApplicationContext(), "clock-out size is: " + String.valueOf(syncClockOuts.size()), Toast.LENGTH_LONG).show();
                String teacherName = "";
                for (int i = 0; i < syncClockOuts.size(); i++) {
                    teacherName = teacherName + " \n " + syncClockOuts.get(i).getFirstName() + " | "+ syncClockOuts.get(i).getComment() + " | "+ syncClockOuts.get(i).getClockOutTime()
                    + " | " + syncClockOuts.get(i).getDay() + " | " + syncClockOuts.get(i).getEmployeeId();
                }
                textView.setText(teacherName);
            }
        });

//        testActivityViewModel.getAllMaterials().observe(this, new Observer<List<SchoolMaterials>>() {
//            @Override
//            public void onChanged(List<SchoolMaterials> schoolMaterials) {
//                Toast.makeText(getApplicationContext(), "size is: " + String.valueOf(schoolMaterials.size()), Toast.LENGTH_LONG).show();
//                String teacherName = "";
//                for (int i = 0; i < schoolMaterials.size(); i++) {
//                    teacherName = teacherName + " \n " + schoolMaterials.get(i).getItemName();
//                }
//                textView.setText(teacherName);
//            }
//        });

//        ClockInAndOutActivityViewModel clockInAndOutActivityViewModel = new ViewModelProvider(this).get(ClockInAndOutActivityViewModel.class);
//        clockInAndOutActivityViewModel.allClockOuts().observe(this, new Observer<List<SyncClockOut>>() {
//            @Override
//            public void onChanged(List<SyncClockOut> syncClockOuts) {
//                Toast.makeText(getApplicationContext(), "clock-out size is: " + String.valueOf(syncClockOuts.size()), Toast.LENGTH_LONG).show();
//                String teacherName = "";
//                for (int i = 0; i < syncClockOuts.size(); i++) {
//                    teacherName = teacherName + " \n " + syncClockOuts.get(i).getFirstName() + " | "+ syncClockOuts.get(i).getComment() + " | "+ syncClockOuts.get(i).getTime()
//                    + " | " + syncClockOuts.get(i).getDay();
//                }
//                textView.setText(teacherName);
//            }
//        });

//        TeacherHomeActivityViewModel teacherHomeActivityViewModel = new ViewModelProvider(this).get(TeacherHomeActivityViewModel.class);
//        teacherHomeActivityViewModel.getTimeOnTasks().observe(this, new Observer<List<SynTimeOnTask>>() {
//            @Override
//            public void onChanged(List<SynTimeOnTask> synTimeOnTasks) {
//                Toast.makeText(getApplicationContext(), "size is: " + String.valueOf(synTimeOnTasks.size()), Toast.LENGTH_LONG).show();
//                String teacherName = "";
//                for (int i = 0; i < synTimeOnTasks.size(); i++) {
//                    teacherName = teacherName + " \n " + synTimeOnTasks.get(i).getEmployeeFirstName() + " | " + synTimeOnTasks.get(i).getTaskName() + " | " + synTimeOnTasks.get(i).getActionStatus();
//                }
//                textView.setText(teacherName);
//            }
//        });

        getLocation();
    }

    //////////////////////GPS Functionality//////////////////////////////////////////////////////
    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, (LocationListener) this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onLocationChanged(Location location) {
        lng = location.getLatitude();
        lat = location.getLongitude();

        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            //LocName.setText("Town Name: " + addresses.get(0).getAddressLine(0));
            if (null != addresses && addresses.size() > 0) {
                String _addre = addresses.get(0).getAddressLine(0);
                //LocName.setText(_addre);
            }
        }catch(Exception e)
        {

        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
