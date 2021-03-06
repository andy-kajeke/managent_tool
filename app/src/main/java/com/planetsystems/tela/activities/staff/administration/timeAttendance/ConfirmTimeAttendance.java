package com.planetsystems.tela.activities.staff.administration.timeAttendance;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.planetsystems.tela.R;
import com.planetsystems.tela.activities.staff.administration.serviceRequests.ApproveMaterialRequests;
import com.planetsystems.tela.activities.staff.administration.serviceRequests.PendingMaterialRequest;
import com.planetsystems.tela.data.ConfirmTimeOnSiteAttendance.SyncConfirmTimeOnSiteAttendance;
import com.planetsystems.tela.utils.DynamicData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ConfirmTimeAttendance extends AppCompatActivity {

    TextView close,Status,Name;
    Button btnFollow;
    EditText staffId, staff_comment;
    CheckBox norm,oth;

    String teacherID_extra;
    String teacherName_extra;
    String supervisorID_extra;
    String dayOfTheWeek;
    String dateOfTheWeek;
    int count = 0;
    List<SyncConfirmTimeOnSiteAttendance> onSiteAttendance;
    private TimeAttendanceListViewModel timeAttendanceListViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_time_attendance);
        setTitle("Attendance Confirmation");

        close =(TextView) findViewById(R.id.txclose);
        Name =(TextView) findViewById(R.id.staffName);
        Status =(TextView) findViewById(R.id.status);
        btnFollow =(Button) findViewById(R.id.out);
        staffId =(EditText) findViewById(R.id.staff_id);
        staff_comment =(EditText) findViewById(R.id.comment);
        norm =(CheckBox) findViewById(R.id.normal) ;
        oth =(CheckBox) findViewById(R.id.other) ;

        Bundle bundle = getIntent().getExtras();
        supervisorID_extra = bundle.getString("admin");
        teacherID_extra = bundle.getString("teacher_id");
        teacherName_extra = bundle.getString("teacher_name");

        Name.setText(teacherName_extra);
        staffId.setText(teacherID_extra);

        onSiteAttendance = new ArrayList<>();

        long date = System.currentTimeMillis();

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd /MM/ yyy");
        dateOfTheWeek = dateFormat.format(date);

        SimpleDateFormat sdf = new SimpleDateFormat("EEEE");
        Date d = new Date();
        dayOfTheWeek = sdf.format(d);

        norm.setChecked(true);
        Status.setText("PRESENT");
        //staff_comment.setFocusableInTouchMode(false);

        oth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    Status.setText("Absent");
                    norm.setChecked(false);
                }

            }
        });
        norm.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked)
            {
                if (isChecked)
                {
                    Status.setText("Present");
                    oth.setChecked(false);
                }

            }
        });

        timeAttendanceListViewModel = new ViewModelProvider(this).get(TimeAttendanceListViewModel.class);

        timeAttendanceListViewModel.getEmployeeIDAndDate(teacherID_extra, DynamicData.getDate()).observe(this, new Observer<List<SyncConfirmTimeOnSiteAttendance>>() {
            @Override
            public void onChanged(List<SyncConfirmTimeOnSiteAttendance> syncConfirmTimeOnSiteAttendances) {
                if(syncConfirmTimeOnSiteAttendances.size() != count ){
                    new AlertDialog.Builder(ConfirmTimeAttendance.this)
                            .setTitle("Confirmation")
                            .setMessage("Dear Supervisor, "+ teacherName_extra +"'s attendance on site has been already confirmed and submitted successfully.")
                            .setIcon(android.R.drawable.ic_dialog_alert)
                            .setPositiveButton("Alright", new DialogInterface.OnClickListener() {

                                public void onClick(DialogInterface dialog, int whichButton) {
                                    Intent intent = new Intent(ConfirmTimeAttendance.this, TimeAttendanceList.class);
                                    intent.putExtra("admin", supervisorID_extra);
                                    //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                    startActivity(intent);
                                    ConfirmTimeAttendance.this.finish();
                                }}).show();
                }else {
                    btnFollow.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(staffId.getText().toString().equalsIgnoreCase("")){
                                staffId.setError("Id Missing!");
                            }else{
                                new AlertDialog.Builder(ConfirmTimeAttendance.this)
                                        .setTitle("Confirmation")
                                        .setMessage("Do you really want to submit this?")
                                        .setIcon(android.R.drawable.ic_dialog_alert)
                                        .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                                            public void onClick(DialogInterface dialog, int whichButton) {
                                                PostToSyncConfirmTimeOnSiteAttendances();
                                            }})
                                        .setNegativeButton(android.R.string.no, null).show();
                            }
                        }
                    });
                }
            }
        });

        timeAttendanceListViewModel.onSiteAttendance().observe(this, new Observer<List<SyncConfirmTimeOnSiteAttendance>>() {
            @Override
            public void onChanged(List<SyncConfirmTimeOnSiteAttendance> syncConfirmTimeOnSiteAttendances) {
//                Toast.makeText(getApplicationContext(), "size is: " + String.valueOf(syncConfirmTimeOnSiteAttendances.size()), Toast.LENGTH_LONG).show();

                for (int i = 0; i < syncConfirmTimeOnSiteAttendances.size(); i++){
                    setTimeOnSiteDetails(syncConfirmTimeOnSiteAttendances);
                }
            }
        });
    }

    private void PostToSyncConfirmTimeOnSiteAttendances() {

        SyncConfirmTimeOnSiteAttendance syncConfirmTimeOnSiteAttendance = new SyncConfirmTimeOnSiteAttendance(
                "",
                "",
                "",
                teacherID_extra,
                teacherID_extra,
                dateOfTheWeek,
                dayOfTheWeek,
                Status.getText().toString(),
                staff_comment.getText().toString(),
                supervisorID_extra,
                "",
                ""
        );

         timeAttendanceListViewModel.insertTimeOnSiteAttendance(syncConfirmTimeOnSiteAttendance);
        Toast.makeText(getApplicationContext(),"Submitted Successfully " ,Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(ConfirmTimeAttendance.this, TimeAttendanceList.class);
        intent.putExtra("admin", supervisorID_extra);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        ConfirmTimeAttendance.this.finish();
    }

    public void setTimeOnSiteDetails(List<SyncConfirmTimeOnSiteAttendance> onSite){
        onSiteAttendance = onSite;
        //notifyDataSetChanged();
    }
}
