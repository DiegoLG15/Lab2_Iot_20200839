package com.example.lab2_iot_20200839;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class PlayApp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_app);
        Toast.makeText(this, "Estás en la vista de PlayApp", Toast.LENGTH_SHORT).show();
    }

    public void cronometro(View view){
        Intent intent=new Intent(this, PlayCronometro.class);
        startActivity(intent);
    }
    public void contador(View view){
        Intent intent=new Intent(this, PlayContador.class);
        startActivity(intent);
    }

}