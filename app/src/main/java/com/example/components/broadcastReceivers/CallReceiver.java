package com.example.components.broadcastReceivers;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.TelephonyManager;
import android.widget.Toast;

public class CallReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
        if (TelephonyManager.EXTRA_STATE_RINGING.equals(state)) {
            // Perform actions when there is an incoming call
            String incomingNumber = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
            Toast.makeText(context, "Incoming call from " + incomingNumber, Toast.LENGTH_SHORT).show();
        }
        else if (TelephonyManager.EXTRA_STATE_OFFHOOK.equals(state)) {
            // Perform actions when there is an outgoing call
            String outgoingNumber = intent.getStringExtra(TelephonyManager.EXTRA_STATE_OFFHOOK);
            Toast.makeText(context, "Outgoing call to " + outgoingNumber, Toast.LENGTH_SHORT).show();
        }
        else if (TelephonyManager.EXTRA_STATE_IDLE.equals(state)) {
            // Perform actions when there is no call
            Toast.makeText(context, "No call", Toast.LENGTH_SHORT).show();
        }
    }
}

