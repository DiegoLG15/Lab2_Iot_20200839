package com.example.lab2_iot_20200839;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class PlayCronometro extends AppCompatActivity{
    private Chronometer chronometer;
    private long pauseOffset;
    private boolean running;
    private boolean paused;
    /*La razon por la cual realice la implementacion de un cronometro a traves del uso de chronometer
     y los metodos iniciar, pausar, despausar y limpar es debido a la facilidad de su uso.
     Ademas, como el sistema android realiza seguimiento del tiempo evita que nuestro cronometro sea
     mas propenso a errores, ya que mediante hilos seria necesario establecer manualmente un seguimiento
     del tiempo*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_cronometro);
        Toast.makeText(this, "Estás en la vista de PlayCronometro", Toast.LENGTH_SHORT).show();
        chronometer=findViewById(R.id.cronometro);
    }
    public void iniciar(View view){
        if(!running && !paused){
            chronometer.setBase(SystemClock.elapsedRealtime()-pauseOffset);
            //devuelve los segundo en suspension
            chronometer.start();
            running=true;
        }
    }
    public void pausar(View view){
        if(running){
            chronometer.stop();
            pauseOffset = SystemClock.elapsedRealtime()-chronometer.getBase();
            running=false;
            paused = true;
        }
    }
    public void despausar(View view){
        if (paused) {
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseOffset);
            chronometer.start();
            running = true;
            paused = false; // El cronómetro ya no está pausado
        }
    }
    public void limpiar(View view){
        chronometer.setBase(SystemClock.elapsedRealtime());
        pauseOffset=0;
    }

}