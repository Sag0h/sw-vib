package com.example.wearsmartwatch;

import android.util.Log;

import com.google.android.gms.wearable.MessageEvent;
import com.google.android.gms.wearable.WearableListenerService;

public class WearMessageListenerService extends WearableListenerService {

    private static final String TAG = "WearMessageListener";

    @Override
    public void onMessageReceived(MessageEvent messageEvent) {
        if (messageEvent.getPath().equals("/vibrate")) {
            String message = new String(messageEvent.getData());
            VibrationController vc = new VibrationController(getApplicationContext());
        }
        else {
            super.onMessageReceived(messageEvent);
        }
    }
}