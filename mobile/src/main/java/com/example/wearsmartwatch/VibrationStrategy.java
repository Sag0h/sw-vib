package com.example.wearsmartwatch;

import android.os.Vibrator;

public interface VibrationStrategy {

    boolean vibrate(Vibrator vibrator) throws InterruptedException;

}
