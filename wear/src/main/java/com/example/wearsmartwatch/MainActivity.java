package com.example.wearsmartwatch;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
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


        vc = new VibrationController(this);
        Button button = findViewById(R.id.vibrate_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println(vc.vibrate());
            }
        });

        Button changeModeButton = findViewById(R.id.change_mode_button);
        changeModeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setContentView(R.layout.select_mode_main);

                Button button1 = findViewById(R.id.plateau_button);
                button1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        vc.usePlateauStrategy(3000, 255, 0);
                        setContentView(binding.getRoot());
                    }
                });

                Button button2 = findViewById(R.id.downwardslope_button);
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vc.useDownwardSlopeStrategy(10000,10 , 250);
                        vc.vibrate();
                        setContentView(binding.getRoot());
                    }
                });
                Button button3 = findViewById(R.id.upwardslope_button);
                button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        vc.useUpwardSlopeStrategy(10000,10 , 250);
                        vc.vibrate();
                        setContentView(binding.getRoot());
                    }
                });

                Button button4 = findViewById(R.id.hill_button);
                button4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setContentView(binding.getRoot());
                        vc.useHillStrategy();
                        vc.vibrate();
                    }
                });
            }
        });

    }
}