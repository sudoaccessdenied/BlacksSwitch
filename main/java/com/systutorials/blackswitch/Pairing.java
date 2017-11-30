package com.systutorials.blackswitch;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Set;

public class Pairing extends AppCompatActivity {
    private static final int REQUEST_ENABLE_BT = 1;
    ListView lv;
    private BluetoothAdapter BA;
    private Set<BluetoothDevice> pairedDevices;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_pairing);


        BA = BluetoothAdapter.getDefaultAdapter();
        lv = (ListView) findViewById(R.id.listView_pairing);

        if (!BA.isEnabled()) {
            Intent turnOn = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(turnOn, REQUEST_ENABLE_BT);
            showDevice();


        } else {
            showDevice();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    protected void onResume() {
        super.onResume();

        showDevice();
    }


    public void showDevice() {
        pairedDevices = BA.getBondedDevices();
        ArrayList list = new ArrayList();
        if (pairedDevices.size() > 0) {
            for (BluetoothDevice bt : pairedDevices) {
                list.add(new String(bt.getName() + "\n" + bt.getAddress()));

            }

            final ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
            lv.setAdapter(adapter);


            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position,
                                        long id) {


                    // ListView Clicked item index
                    int itemPosition = position;

                    // ListView Clicked item value
                    String itemValue = (String) lv.getItemAtPosition(position);
                    String macAdd = itemValue.substring(itemValue.lastIndexOf("\n"));

                    macAdd.trim();
                    // Show Alert
                    Intent intent = new Intent(getApplicationContext(), MainSwitchActivity.class);
                    intent.putExtra("MacAddress", macAdd);

                    startActivity(intent);


                }
            });


        }


    }









    @Override
    protected void onDestroy() {
        super.onDestroy();
    }




}













