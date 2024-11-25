package com.uilover.project2042.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import androidx.appcompat.app.AppCompatActivity;

import com.uilover.project2042.R;
import com.uilover.project2042.databinding.ActivityPerfilBinding;

public class PerfilActivity extends AppCompatActivity {
    private ActivityPerfilBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPerfilBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sair();
        voltar();
    }

    private void sair() {
        binding.logoffBtn.setOnClickListener(v -> {
            Intent intent=new Intent(PerfilActivity.this, IntroActivity.class);
            startActivity(intent);
        });
    }
        private void voltar() {
            binding.backBtn.setOnClickListener(v -> {
                Intent intent=new Intent(PerfilActivity.this, MainActivity.class);
                startActivity(intent);
            });
}}
