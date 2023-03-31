package com.example.wearsmartwatch;
import android.os.Vibrator;
import android.os.VibrationEffect;

import java.util.concurrent.TimeUnit;
import android.os.Build;

public class PlateauStrategy implements VibrationStrategy {
    private long duration;
    private int intensity;
    private int repetitions;

    public PlateauStrategy(long duration, int intensity, int repetitions) {
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
    public boolean vibrate(Vibrator vibrator) {

        for (int i = 0; i < this.getRepetitions(); i++) {

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                //  si la version de android puede usar la api >26 la condicion da true, sino se utiliza el metodo disponible en la api <26
                vibrator.vibrate(VibrationEffect.createOneShot(this.getDuration(), this.getIntensity()));
            } else {
                vibrator.vibrate(this.getDuration());
            }
            try {
                TimeUnit.MILLISECONDS.sleep(this.getDuration());
            } catch (InterruptedException e) {
                return false;
            }
        }
        return true;
    }
}

