package com.example.wearsmartwatch;

import android.os.VibrationEffect;
import android.os.Vibrator;

public class DownwardSlopeStrategy implements VibrationStrategy{
    //private FormatStrategy format;
    private long period;
    private int amplitude;
    private int repetitions;
    private long[] pattern;

    public DownwardSlopeStrategy(long period, int amplitude, int repetitions){
        this.period = period;
        this.amplitude = amplitude;
        this.repetitions = repetitions;
    }
    @Override
    public boolean vibrate(Vibrator vibrator) throws InterruptedException {
        long pauseTime = (long)(period * 0.02);
        long totalVibrationTime =  period - (long)(pauseTime * repetitions);
        long[] pattern = new long[repetitions*2];
        pattern[0] = pauseTime;
        for(int i=1; i<pattern.length; i++){
            if(i%2 == 0){
                pattern[i] = pauseTime;
            }else{
                pattern[i] = totalVibrationTime/10;
                totalVibrationTime -= totalVibrationTime/10;
            }
        }

        try {
            vibrator.vibrate(pattern, -1);
        } catch (Exception e){
            return false;
        }
        return true;
    }

}
