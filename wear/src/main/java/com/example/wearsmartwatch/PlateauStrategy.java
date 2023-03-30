package com.example.wearsmartwatch;
import android.os.Vibrator;
import android.os.VibrationEffect;

import java.util.concurrent.TimeUnit;

public class PlateauStrategy implements VibrationStrategy{
    private long duration;
    private int intensity;
    private int repetitions;
    public PlateauStrategy(long duration, int intensity, int repetitions){
        this.duration = duration;
        this.intensity = intensity;
        this.repetitions = repetitions;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    @Override
    public boolean vibrate(Vibrator vibrator){
        for(int i=0; i<this.getRepetitions(); i++) {
            vibrator.vibrate(VibrationEffect.createOneShot(this.getDuration(), this.getIntensity()));
            try {
                TimeUnit.MILLISECONDS.sleep(1001);
            } catch (InterruptedException e) {
                return false;
            }
        }
        return vibrator.hasVibrator();
    }
}
