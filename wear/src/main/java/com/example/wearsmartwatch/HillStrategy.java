package com.example.wearsmartwatch;

import android.os.Vibrator;

public class HillStrategy implements VibrationStrategy{
    private long duration;

    public HillStrategy(long duration){
        this.duration = duration;
    }
    @Override
    public boolean vibrate(Vibrator vibrator) {
        try {
            vibrator.vibrate(this.createPattern(), -1);
        } catch (Exception e){
            return false;
        }
        return true;
    }

    private long[] createPattern(){

        long pauseTime = (long)(duration * 0.1);
        pauseTime = (long)(pauseTime*0.8);
        this.duration -= pauseTime;
        long half = duration / 2;
        long halves = half/2;

        long[] pattern = new long[]{ pauseTime, (long)(halves*0.1), pauseTime,
                (long)(halves*0.2), pauseTime, (long)(halves*0.3), pauseTime, (long)(halves*0.4),
                pauseTime, half, pauseTime, (long)(halves*0.4),  pauseTime, (long)(halves*0.3),
                pauseTime, (long)(halves*0.2),  pauseTime, (long)(halves*0.1)};

        return pattern;
    }
}
