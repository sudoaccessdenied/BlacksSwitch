package com.systutorials.blackswitch;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;



import java.io.IOException;
import java.io.OutputStream;
import java.util.UUID;

public class MainSwitchActivity extends AppCompatActivity {
    UUID uuid = UUID.fromString("00001101-0000-1000-8000-00805f9b34fb");
    private BluetoothAdapter bluetoothAdapter = null;
    private BluetoothSocket mmSocket = null;
    private OutputStream outStream = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_switch);


        





        bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
 

    }

    @Override
    protected void onResume() {
        
       super.onResume();



        Bundle bundle = getIntent().getExtras();
        final String macA = bundle.getString("MacAddress").trim();


        //getDevice name on Click on Pairing Activity


        //   A MAC address, which we got above.
        //   A Service ID or UUID.  In this case we are using the
        //     UUID for SPP.
        try {

            BluetoothDevice mmDevice = bluetoothAdapter.getRemoteDevice(macA);

            mmSocket = mmDevice.createRfcommSocketToServiceRecord(uuid);
        } catch (IOException e) {
            AlertBox("Fatal Error", ":" + e.getMessage() + ".");
        }

        //It will cancel the discovery
        bluetoothAdapter.cancelDiscovery();

        // Establish the connection.  This will block until it connects.
        if(!mmSocket.isConnected()) {
            try {
                mmSocket.connect();
                Toast.makeText(getApplicationContext(), "Connected", Toast.LENGTH_SHORT).show();
                TextView state = (TextView) findViewById(R.id.TextViewState);
                state.setText("CONNECTED");
            } catch (IOException e) {
                try {
                    TextView state = (TextView) findViewById(R.id.TextViewState);
                    state.setText("You are not connected to Arduino");
                    mmSocket.close();
                } catch (IOException e2) {
                    AlertBox("Fatal Error", "Connection Close fail" + e2.getMessage() + ".");
                }
            }


            try {
                outStream = mmSocket.getOutputStream();
            } catch (IOException e) {
                AlertBox("Fatal Error", "Make sure remote device is Connected" + e.getMessage() + ".");
            }

        }
        final SwitchCompat toggle1 = (SwitchCompat) findViewById(R.id.toggleButton1);

        toggle1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    String message = "light on";
                    byte[] msgBuffer = message.getBytes();
                    try {
                        outStream.write(msgBuffer);
                    } catch (IOException e) {
                        String msg = "Exception occure during Sending Command : " + e.getMessage();

                        msg = msg + ".\n\nCheck that the SPP UUID: " + uuid.toString() + " exists on server.\n\n";

                        AlertBox("Fatal Error", msg);
                    }
                    // The toggle is enabled
                } else {
                    // The toggle is disabled
                    String message = "light off";
                    byte[] msgBuffer = message.getBytes();
                    try {
                        outStream.write(msgBuffer);
                    } catch (IOException e) {
                        String msg = "Exception occure during Sending Command : " + e.getMessage();

                        msg = msg + ".\n\nCheck that the SPP UUID: " + uuid.toString() + " exists on server.\n\n";

                        AlertBox("Fatal Error", msg);
                    }


                }
            }
        });


        final SwitchCompat toggle2 = (SwitchCompat) findViewById(R.id.toggleButton2);

        toggle2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String message = "lamp on";
                    byte[] msgBuffer = message.getBytes();
                    try {
                        outStream.write(msgBuffer);
                    } catch (IOException e) {
                        String msg = "Exception occure during Sending Command : " + e.getMessage();

                        msg = msg + ".\n\nCheck that the SPP UUID: " + uuid.toString() + " exists on server.\n\n";

                        AlertBox("Fatal Error", msg);
                    }

                    // The toggle is enabled
                } else {
                    // The toggle is disabled

                    String message = "lamp off";
                    byte[] msgBuffer = message.getBytes();
                    try {
                        outStream.write(msgBuffer);
                    } catch (IOException e) {
                        String msg = "Exception occure during Sending Command : " + e.getMessage();

                        msg = msg + ".\n\nCheck that the SPP UUID: " + uuid.toString() + " exists on server.\n\n";

                        AlertBox("Fatal Error", msg);
                    }


                }
            }
        });


        final SwitchCompat toggle3 = (SwitchCompat) findViewById(R.id.toggleButton3);

        toggle3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    String message = "light3 on";
                    byte[] msgBuffer = message.getBytes();
                    try {
                        outStream.write(msgBuffer);
                    } catch (IOException e) {
                        String msg = "Exception occure during Sending Command : " + e.getMessage();

                        msg = msg + ".\n\nCheck that the SPP UUID: " + uuid.toString() + " exists on server.\n\n";

                        AlertBox("Fatal Error", msg);
                    }

                    // The toggle is enabled
                } else {
                    // The toggle is disabled

                    String message = "light3 off";
                    byte[] msgBuffer = message.getBytes();
                    try {
                        outStream.write(msgBuffer);
                    } catch (IOException e) {
                        String msg = "Exception occure during Sending Command: " + e.getMessage();

                        msg = msg + ".\n\nCheck that the SPP UUID: " + uuid.toString() + " exists on server.\n\n";

                        AlertBox("Fatal Error", msg);
                    }


                }
            }
        });


        final SwitchCompat toggle4 = (SwitchCompat) findViewById(R.id.toggleButton4);

        toggle4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {

                    String message = "fan on";
                    byte[] msgBuffer = message.getBytes();
                    try {
                        outStream.write(msgBuffer);
                    } catch (IOException e) {
                        String msg = "Exception occure during Sending Command : " + e.getMessage();

                        msg = msg + ".\n\nCheck that the SPP UUID: " + uuid.toString() + " exists on server.\n\n";

                        AlertBox("Fatal Error", msg);
                    }
                    // The toggle is enabled
                } else {
                    // The toggle is disabled
                    String message = "fan off";
                    byte[] msgBuffer = message.getBytes();
                    try {
                        outStream.write(msgBuffer);
                    } catch (IOException e) {
                        String msg = "Exception occure during Sending Command: " + e.getMessage();

                        msg = msg + ".\n\nCheck that the SPP UUID: " + uuid.toString() + " exists on server.\n\n";

                        AlertBox("Fatal Error", msg);
                    }


                }
            }
        });


        final SwitchCompat toggle5 = (SwitchCompat) findViewById(R.id.toggleButton5);

        toggle5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {


                    String message = "all on";
                    byte[] msgBuffer = message.getBytes();
                    try {

                        outStream.write(msgBuffer);

                        Toast.makeText(getApplicationContext(), "All Load are turning On", Toast.LENGTH_SHORT).show();
                        outStream.flush();

                    } catch (IOException e) {
                        String msg = "Exception occure during Sending Command : " + e.getMessage();

                        msg = msg + ".\n\nCheck that the SPP UUID: " + uuid.toString() + " exists on server.\n\n";

                        AlertBox("Fatal Error", msg);
                    }
                    // The toggle is enabled
                } else {


                    // The toggle is disabled
                    String message = "all off";
                    byte[] msgBuffer = message.getBytes();
                    try {


                        outStream.write(msgBuffer);
                        Toast.makeText(getApplicationContext(), "All Load are turning Off", Toast.LENGTH_SHORT).show();
                        outStream.flush();

                    } catch (IOException e) {
                        String msg = "Exception occure during Sending Command: " + e.getMessage();

                        msg = msg + ".\n\nCheck that the SPP UUID: " + uuid.toString() + " exists on server.\n\n";

                        AlertBox("Fatal Error", msg);
                    }


                }
            }
        });
    }


    @Override
    public void onPause() {
        mAd.pause(this);
        super.onPause();

    }

    @Override
    public  void onStop(){
        super.onStop();

    }

    public void about_help(View view) {

        Button button1 = (Button) findViewById(R.id.button1);

            Intent about = new Intent(getApplicationContext(), About.class);
            startActivity(about);
    }



    public void AlertBox(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message + " Press OK to exit.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {
                        finish();
                    }
                }).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mAd.destroy(this);
        try {
            mmSocket.close();
            outStream.close();
        } catch (IOException e) {
            AlertBox("Fatal Error", e.getMessage());
        }
    }

    
    

    














}














