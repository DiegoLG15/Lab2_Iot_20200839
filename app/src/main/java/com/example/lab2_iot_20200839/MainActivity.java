package com.example.lab2_iot_20200839;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean tieneInternet = verificarConexionInternet();
        if (tieneInternet) {
            Toast.makeText(this, "Conexión a Internet disponible", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Sin conexión a Internet", Toast.LENGTH_SHORT).show();
        }
    }
    public void login(View view){
        Intent intent=new Intent(this, SignUp.class);
        startActivity(intent);
    }
    private boolean verificarConexionInternet() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }


}