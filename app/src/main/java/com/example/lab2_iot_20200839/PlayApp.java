package com.example.lab2_iot_20200839;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.example.lab2_iot_20200839.databinding.ActivityPlayAppBinding;

public class PlayApp extends AppCompatActivity {
    private ActivityPlayAppBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPlayAppBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Toast.makeText(this, "Estás en la vista de PlayApp", Toast.LENGTH_SHORT).show();


        binding.username.setText(getIntent().getStringExtra("username"));
        String fullname = getIntent().getStringExtra("nombre") +" "+ getIntent().getStringExtra("apellido");
        binding.fullName.setText(fullname);

        ImageView imageView=findViewById(R.id.imageUser);
        Glide.with(this)
                .load(getIntent().getStringExtra("imagen"))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(binding.imageUser);
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