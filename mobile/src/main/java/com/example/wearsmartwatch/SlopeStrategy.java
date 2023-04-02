package com.example.wearsmartwatch;

import android.os.Vibrator;

public class SlopeStrategy implements VibrationStrategy{
    private FormatStrategy format;


    public SlopeStrategy(FormatStrategy f){
        this.format = f;
    }
    @Override
    public boolean vibrate(Vibrator vibrator) {
        try {
            vibrator.vibrate(format.getPattern(), -1);
        } catch (Exception e){
            return false;
        }
        return true;
    }

}
