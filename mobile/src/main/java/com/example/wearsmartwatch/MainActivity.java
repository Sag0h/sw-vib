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

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        VibrationController vc = new VibrationController(this);
        vc.vibrate();
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