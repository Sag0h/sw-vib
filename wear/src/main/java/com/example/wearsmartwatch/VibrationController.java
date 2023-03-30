package com.example.wearsmartwatch;

import android.content.Context;
import android.os.Vibrator;

public class VibrationController {
    private Vibrator vibrator;
    private Context context;
    private VibrationStrategy strat;

    public VibrationController(Context context){
        this.context = context;
        this.usePlateauStrategy(1000, 255, 5);
        this.vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    public void usePlateauStrategy(long duration, int intensity, int repetitions){
        this.strat = new PlateauStrategy(duration, intensity, repetitions);
    }



    public boolean vibrate(){
        try {
            return strat.vibrate(vibrator);
        } catch (InterruptedException e) {
            return false;
        }
    }

}
