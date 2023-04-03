package com.example.wearsmartwatch;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Continuation;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.Wearable;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
 //   private Vibrator vibrator;
    private GoogleApiClient mGoogleApiClient;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected(@Nullable Bundle bundle) {
                        Log.d(TAG, "Conexión establecida con éxito.");
                    }

                    @Override
                    public void onConnectionSuspended(int i) {
                        Log.d(TAG, "Conexión suspendida. Código: " + i);
                    }
                })
                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
                        Log.d(TAG, "Conexión fallida. Código: " + connectionResult.getErrorCode());
                    }
                })
                .addApi(Wearable.API)
                .build();

        Button sendButton = findViewById(R.id.send_button);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessage("vibrate");
            }
        });
        /*
        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        VibrationController vc = new VibrationController(this);
        vc.vibrate(); */
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    private void sendMessage(String message) {
        if (mGoogleApiClient == null || !mGoogleApiClient.isConnected()) {
            return;
        }
        // Obtén el identificador del nodo del dispositivo de Wear OS
        Task<String> getConnectedNodesTask =
                Wearable.getNodeClient(getApplicationContext()).getConnectedNodes().continueWith(new Continuation<List<Node>, String>() {
                    @Override
                    public String then(@NonNull Task<List<Node>> task) throws Exception {
                        List<Node> nodes = task.getResult();
                        for (Node node : nodes) {
                            if (node.isNearby()) {
                                return node.getId();
                            }
                        }
                        return null;
                    }
                });

        getConnectedNodesTask.addOnSuccessListener(new OnSuccessListener<String>() {
            @Override
            public void onSuccess(String nodeId) {
                if (nodeId == null) {
                    Log.e(TAG, "No se encontraron nodos de Wear OS cercanos.");
                    return;
                }
                // Construye el mensaje
                byte[] bytes = message.getBytes();
                Task<Integer> sendMessageTask =
                        Wearable.getMessageClient(getApplicationContext())
                                .sendMessage(nodeId, "/start_function", bytes);

                sendMessageTask.addOnSuccessListener(new OnSuccessListener<Integer>() {
                    @Override
                    public void onSuccess(Integer integer){
                            Log.d(TAG, "Mensaje enviado correctamente al dispositivo Wear OS.");
                        }
                    })
                            .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e(TAG, "Error al enviar el mensaje al dispositivo Wear OS.", e);
                        }
                    });
                }
            });
        }
    }