package com.planetsystems.tela.activities.staff.administration.serviceRequests;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.planetsystems.tela.R;
import com.planetsystems.tela.activities.staff.regularStaff.serviceRequests.ServiceRequestsViewModel;

import java.text.SimpleDateFormat;

public class ApproveMaterialRequests extends AppCompatActivity {

    EditText item_name, qntyNeeded, requestedOn, neededOn;
    EditText requestedBy, reason;
    Button approve, cancel;

    String dayString;
    String itemName_extra, itemID_extra;
    String name_extra, supervisorID, qnty_extra;
    String requiredOn_extra, requestedOn_extra, reason_extra;
    int db_id_extra;

    private ServiceRequestsViewModel serviceRequestsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_approve_material_requests);
        setTitle("School Material Request");

        item_name = (EditText) findViewById(R.id.itemName);
        qntyNeeded = (EditText) findViewById(R.id.qntyNeeded);
        requestedOn = (EditText) findViewById(R.id.requestedOn);
        neededOn = (EditText) findViewById(R.id.neededOn);
        requestedBy = (EditText) findViewById(R.id.staff_name);
        reason = (EditText) findViewById(R.id.reason);
        approve = (Button) findViewById(R.id.approveRequest);
        cancel = (Button) findViewById(R.id.cancelRequest);

        Bundle bundle = getIntent().getExtras();
        db_id_extra = bundle.getInt("db_id");
        name_extra = bundle.getString("emp_name");
        itemID_extra = bundle.getString("item_id");
        itemName_extra = bundle.getString("item_name");
        qnty_extra = bundle.getString("quantity");
        requiredOn_extra = bundle.getString("requiredDate");
        requestedOn_extra = bundle.getString("requestDate");
        reason_extra = bundle.getString("reason");
        supervisorID = bundle.getString("supervisorID");

        item_name.append(itemName_extra);
        qntyNeeded.append(qnty_extra);
        neededOn.append(requiredOn_extra);
        requestedOn.append(requestedOn_extra);
        requestedBy.append(name_extra);
        reason.append(reason_extra);

        long date = System.currentTimeMillis();
        SimpleDateFormat day = new SimpleDateFormat("dd /MM/ yyy");
        dayString = day.format(date);

        serviceRequestsViewModel = new ViewModelProvider(this).get(ServiceRequestsViewModel.class);

        approve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(ApproveMaterialRequests.this)
                        .setTitle("Confirmation")
                        .setMessage("Do you really want to approve this request?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                ApproveRequest();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                new AlertDialog.Builder(ApproveMaterialRequests.this)
                        .setTitle("Confirmation")
                        .setMessage("Do you really want to reject this request?")
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int whichButton) {
                                RejectRequest();
                            }})
                        .setNegativeButton(android.R.string.no, null).show();

            }
        });

    }

    private void ApproveRequest() {
        String approvalStatus = "Approved";
        int row_id = db_id_extra;

        serviceRequestsViewModel.updateMaterialApprovalStatus(approvalStatus, dayString, supervisorID, row_id);
        Toast.makeText(getApplicationContext(),"Submitted Successfully " ,Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(ApproveMaterialRequests.this, PendingMaterialRequest.class);
        intent.putExtra("supervisor", supervisorID);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        ApproveMaterialRequests.this.finish();
    }

    private void RejectRequest() {
        String approvalStatus = "Rejected";
        int row_id = db_id_extra;

        serviceRequestsViewModel.updateMaterialApprovalStatus(approvalStatus, dayString, supervisorID, row_id);
        Toast.makeText(getApplicationContext(),"Submitted Successfully " ,Toast.LENGTH_SHORT).show();

        Intent intent = new Intent(ApproveMaterialRequests.this, PendingMaterialRequest.class);
        intent.putExtra("supervisor", supervisorID);
        //intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        ApproveMaterialRequests.this.finish();
    }
}
