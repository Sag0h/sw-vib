package com.example.wearsmartwatch;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    private Vibrator vibrator;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Obtener una instancia del servicio de vibración
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        /*long[] tiempos = new long[]{100, 100, 500, 100, 1000, 100, 2000, 100, 2000 };
        int[] prendido = new int[]{-1, 0, -1, 0, -1, 0, -1, 0, -1};
        for(int i=0; i<1; i++) {
            for(int j=0; j<tiempos.length; j++){
                if(prendido[j] != 0){
                    vibrator.vibrate(tiempos[j]);
                }
                try {
                    TimeUnit.MILLISECONDS.sleep(tiempos[j]);
                } catch (InterruptedException e) {

                }
            }
    */
        VibrationController vc = new VibrationController(this);
        vc.vibrate();

        //vibrator.vibrate( new long[] {0, 500, 200, 500, 200, 500}, -1);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Detener la vibración al cerrar la aplicación
        if (vibrator != null) {
            vibrator.cancel();
        }
    }
}