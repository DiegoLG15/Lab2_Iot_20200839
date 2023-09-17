package com.example.lab2_iot_20200839;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

public class PlayCronometro extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_cronometro);
        Toast.makeText(this, "Estás en la vista de PlayCronometro", Toast.LENGTH_SHORT).show();
    }
}