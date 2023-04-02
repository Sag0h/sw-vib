package com.example.wearsmartwatch;

import android.content.Context;
import android.os.Vibrator;

public class VibrationController {
    private Vibrator vibrator;
    private Context context;
    private VibrationStrategy strat;

    public VibrationController(Context context){
        this.context = context;
        this.useUpwardSlopeStrategy(10000, 15, 250);
        this.vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    public void usePlateauStrategy(long duration, int intensity, int repetitions){
        this.strat = null;
    }

    public void useDownwardSlopeStrategy(long period, int vibrations, long pause){
        this.strat = new SlopeStrategy(new LinearDownStrategy(period, vibrations, pause));
    }

    public void useUpwardSlopeStrategy(long period, int vibrations, long pause){
        this.strat = new SlopeStrategy(new LinearUpStrategy(period, vibrations, pause));
    }

    public boolean vibrate() {
        if(vibrator.hasVibrator()){
            try {
                return strat.vibrate(vibrator);
            } catch (InterruptedException e) {
                return false;
            }
        }else{
            System.out.println("This device has not vibration hardware.");
            return false;
        }
    }
}
