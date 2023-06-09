package com.example.wearsmartwatch;

import android.content.Context;
import android.os.Vibrator;

public class VibrationController {
    private Vibrator vibrator;
    private Context context;
    private VibrationStrategy strat;

    public VibrationController(Context context){
        this.context = context;
        this.usePlateauStrategy(1000, 255, 1);
        this.vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
    }

    public void usePlateauStrategy(long duration, int intensity, int repetitions){
        this.strat = new PlateauStrategy(duration, intensity, repetitions);
    }

    public void useDownwardSlopeStrategy(long duration, int repetitions, long pause){
        this.strat = new SlopeStrategy(new LinearDownStrategy(duration, repetitions, pause));
    }

    public void useUpwardSlopeStrategy(long duration, int repetitions, long pause){
        this.strat = new SlopeStrategy(new LinearUpStrategy(duration, repetitions, pause));
    }

    public void useHillStrategy(){
        this.strat = new HillStrategy(5000);
    }

    public boolean vibrate() {
        if(vibrator.hasVibrator()){
            try {
                return strat.vibrate(vibrator);
            } catch (Exception e) {
                return false;
            }
        }else{
            return false;
        }
    }
}
