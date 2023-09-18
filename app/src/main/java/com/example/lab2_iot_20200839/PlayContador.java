package com.example.lab2_iot_20200839;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class PlayContador extends AppCompatActivity {
    private TextView contadorTextView;
    private Button iniciarButton;
    private long contador = 104; // Valor inicial del contador
    private boolean running = false; // Indica si el contador está corriendo
    private int aumento = 10;
    private Vibrator vibrator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_contador);
        Toast.makeText(this, "Estás en la vista de PlayContador", Toast.LENGTH_SHORT).show();

        contadorTextView = findViewById(R.id.textContador);
        iniciarButton = findViewById(R.id.iniciarContador);

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);
        iniciarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!running) {
                    iniciarContador();
                } else {
                    cambiarAumento();
                }
            }
        });


    }
    private void sonarAlarma() {
        // Vibrar el dispositivo
        vibrator.vibrate(VibrationEffect.createOneShot(3000, VibrationEffect.DEFAULT_AMPLITUDE));
    }
    private Handler handler = new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            actualizarContador();
            return true;
        }
    });
    private void actualizarContador() {
        contadorTextView.setText(String.valueOf(contador));
        if (contador >= 226) {
            detenerContador();
        }
    }
    private void detenerContador() {
        running = false;
    }
    private void iniciarContador() {
        running = true;
        iniciarButton.setText("Cambiar");

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (running) {
                    contador += aumento;
                    handler.sendEmptyMessage(0);

                    try {
                        Thread.sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    private void cambiarAumento() {
        if (aumento > 1) {
            aumento -= 1;
        }
    }

}