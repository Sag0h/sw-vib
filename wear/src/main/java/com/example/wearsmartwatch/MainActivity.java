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
                    public void onClick(View v) {
                        setContentView(R.layout.plateau_input_layout);

                        // Obtener las referencias a los EditText
                        EditText editText1 = findViewById(R.id.editText1);
                        EditText editText2 = findViewById(R.id.editText2);
                        EditText editText3 = findViewById(R.id.editText3);

                        System.out.println(editText1.getText().toString());
                        Button submitButton = findViewById(R.id.submit_button);


                        submitButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                int repetitions, intensity;
                                long duration;
                                try{
                                    duration = Long.parseLong(editText1.getText().toString());
                                }catch(Exception e) {
                                    duration = 1000;
                                };

                                try{
                                    intensity = Integer.parseInt(editText2.getText().toString());
                                }catch (Exception e){
                                    intensity = 127;
                                }

                                try {
                                    repetitions = Integer.parseInt(editText3.getText().toString());
                                }catch (Exception e){
                                    repetitions = 1;
                                }
                                vc.usePlateauStrategy(duration, intensity, repetitions);
                                setContentView(binding.getRoot());
                            }
                        });
                    }
                });

                Button button2 = findViewById(R.id.downwardslope_button);
                button2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Acciones para el botón 2
                    }
                });

                Button button3 = findViewById(R.id.upwardslope_button);
                button3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Acciones para el botón 3
                    }
                });

                Button button4 = findViewById(R.id.hill_button);
                button4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Acciones para el botón 4
                    }
                });
            }
        });

    }
}