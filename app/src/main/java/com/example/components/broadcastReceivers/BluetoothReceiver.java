package com.example.components.broadcastReceivers;

import android.bluetooth.BluetoothAdapter;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class BluetoothReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();

        if (BluetoothAdapter.ACTION_STATE_CHANGED.equals(action)) {
            int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.ERROR);
            switch (state) {
                case BluetoothAdapter.STATE_OFF:
                    // Bluetooth is off
                    Toast.makeText(context, "Bluetooth is off", Toast.LENGTH_SHORT).show();
                    break;
                case BluetoothAdapter.STATE_TURNING_OFF:
                    // Bluetooth is turning off
                    break;
                case BluetoothAdapter.STATE_ON:
                    // Bluetooth is on
                    Toast.makeText(context, "Bluetooth is on", Toast.LENGTH_SHORT).show();
                    break;
                case BluetoothAdapter.STATE_TURNING_ON:
                    // Bluetooth is turning on
                    break;
            }
        }
    }
}

