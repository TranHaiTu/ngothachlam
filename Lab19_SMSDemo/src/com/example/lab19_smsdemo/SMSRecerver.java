package com.example.lab19_smsdemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class SMSRecerver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Bundle bundle = intent.getExtras();
		SmsMessage[] msgs = null;
		String str = "SMS from ";
		if (bundle != null) {
			// ---retrieve the SMS message received---
			Object[] pdus = (Object[]) bundle.get("pdus");
			msgs = new SmsMessage[pdus.length];
			for (int i = 0; i < msgs.length; i++) {
				msgs[i] = SmsMessage.createFromPdu((byte[]) pdus[i]);
				if (i == 0) {
					// ---get the sender address/phone number---
					str += msgs[i].getOriginatingAddress();
					str += ": ";
				}
				// ---get the message body---
				str += msgs[i].getMessageBody().toString();
			}
			// ---display the new SMS message---
			Toast.makeText(context, str, Toast.LENGTH_SHORT).show();
			Log.d("SMSReceiver", str);	
			this.abortBroadcast();
		}
	}
}
