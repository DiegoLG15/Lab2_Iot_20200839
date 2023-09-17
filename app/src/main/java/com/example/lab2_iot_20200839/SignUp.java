package com.example.lab2_iot_20200839;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.lab2_iot_20200839.databinding.ActivitySignUpBinding;
import com.example.lab2_iot_20200839.dto.Profile;
import com.example.lab2_iot_20200839.dto.Result;
import com.example.lab2_iot_20200839.services.TypicodeServices;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SignUp extends AppCompatActivity {
    TypicodeServices typicodeService;
    private ActivitySignUpBinding binding;
    private String username;
    private String nombre;
    private String apellido;

    private String imagen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignUpBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        CheckBox checkBoxValidacion = findViewById(R.id.checkBox);
        Button btnContinuar = findViewById(R.id.iniciarSesion);
        typicodeService = new Retrofit.Builder()
                .baseUrl("https://randomuser.me")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(TypicodeServices.class);
        fetchWebServiceData();
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
        intent.putExtra("nombre",nombre) ;
        intent.putExtra("apellido",apellido);
        intent.putExtra("imagen",imagen);
        intent.putExtra("username",username);
        startActivity(intent);
    }
    public void fetchWebServiceData(){
        if(verificarConexionInternet()){
            typicodeService.getResult().enqueue(new Callback<Profile>() {
                @Override
                public void onResponse(Call<Profile> call, Response<Profile> response) {
                    if(response.isSuccessful()){
                        Profile profile=response.body();
                        List<Result> results=profile.getResults();
                        if (!results.isEmpty()) {
                            Result firstResult = results.get(0);

                            nombre = firstResult.getName().getFirst();
                            apellido = firstResult.getName().getLast();
                            username= firstResult.getLogin().getUsername();
                            imagen = firstResult.getPicture().getLarge();

                            binding.textInputNombre.getEditText().setText(nombre);
                            binding.textInputApellido.getEditText().setText(apellido);
                            binding.textInputCorreo.getEditText().setText(firstResult.getEmail());
                            binding.editTextPassword.setText(firstResult.getLogin().getPassword());

                            binding.textInputNombre.getEditText().setEnabled(false);
                            binding.textInputApellido.getEditText().setEnabled(false);
                            binding.textInputCorreo.getEditText().setEnabled(false);
                            binding.editTextPassword.setEnabled(false);

                        }
                    } else {
                        Log.d("msg-test", "error en la respuesta del webservice");
                    }
                }

                @Override
                public void onFailure(Call<Profile> call, Throwable t) {
                    t.printStackTrace();
                }
            });
        }
    }
    private boolean verificarConexionInternet() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = manager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
}