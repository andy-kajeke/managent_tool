package com.planetsystems.tela.activities.fingerprintactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.graphics.Bitmap;
import android.hardware.usb.UsbManager;
import android.os.Bundle;

import com.planetsystems.tela.R;
import com.suprema.IBioMiniDevice;

public class FingerPrintActivity extends AppCompatActivity implements DeviceBroadcastReceiver.OnDeviceConnectionListener{
    public static final String ACTION_USB_PERMISSION = "com.planetsystems.tela.activities.fingerprintactivity.FingerPrintActivity";
    public IBioMiniDevice.TemplateData teacherCapturedTemplate;
    private Bitmap teacherImage;
    //Flag.
    public static final boolean mbUsbExternalUSBManager = false;
    private UsbManager mUsbManager = null;
    private PendingIntent mPermissionIntent= null;
    //

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finger_print);
    }

    @Override
    public void onDeviceConnectionSuccess() {

    }

    @Override
    public void onDeviceConnectionError() {

    }
}
