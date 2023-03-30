package com.example.wearsmartwatch;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import com.example.wearsmartwatch.databinding.ActivityMainBinding;
import android.widget.Button;
import android.view.View;
public class MainActivity extends Activity {

    private TextView mTextView;
    private VibrationController vc;
private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mTextView = binding.text;
        vc = new VibrationController(this);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(vc.vibrate());
            }
        });


    }
}