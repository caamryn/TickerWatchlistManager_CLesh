package com.example.tickerwatchlistmanager_clesh;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SMSReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // get data from intent
        final Bundle bundle = intent.getExtras();

        if(intent.getAction().equals(Telephony.Sms.Intents.SMS_RECEIVED_ACTION)){

            Intent myIntent = new Intent(context, MainActivity.class);  //create an intent to the main activity
            myIntent.setAction(Intent.ACTION_SEND);
            myIntent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            myIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);  // in otder to call startActivity outside of an activity requires this flag
            // getting the info from the message
            if(bundle != null){
                Object[] pdusObj = (Object[]) bundle.get("pdus");
                String format = bundle.getString("format").toString();

                for(int i=0; i<pdusObj.length; i++){
                    SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) pdusObj[i], format);
                    String message = currentMessage.getDisplayMessageBody();
                    //String printMessage = "Sender: " + sender + " Message: " + message;
                    Log.i("SMS", message);
                    myIntent.putExtra("sms", message);
                }
            }
            context.startActivity(myIntent);
        }
    }
}