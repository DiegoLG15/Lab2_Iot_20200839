package com.example.lab2_iot_20200839;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        CheckBox checkBoxValidacion = findViewById(R.id.checkBox);
        Button btnContinuar = findViewById(R.id.iniciarSesion);
        btnContinuar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (checkBoxValidacion.isChecked()) {
                    iniciarSesion(v);
                } else {
                    // El CheckBox no está marcado, muestra un mensaje de error
                    Toast.makeText(SignUp.this, "Debes aceptar los términos y condiciones", Toast.LENGTH_SHORT).show();
                }
            }
        });
        Toast.makeText(this, "Estás en la vista de SignUp", Toast.LENGTH_SHORT).show();
    }
    public void iniciarSesion(View view){
        Intent intent=new Intent(this, PlayApp.class);
        startActivity(intent);
    }
}