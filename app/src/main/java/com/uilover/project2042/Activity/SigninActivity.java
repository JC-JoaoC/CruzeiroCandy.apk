package com.uilover.project2042.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.uilover.project2042.R;
import com.uilover.project2042.databinding.ActivityPerfilBinding;
import com.uilover.project2042.databinding.ActivitySigninBinding;

public class SigninActivity extends AppCompatActivity {
    private ActivitySigninBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        conectar();
    }

    private void conectar() {
        binding.loginBtn.setOnClickListener(v -> {
            Intent intent=new Intent(SigninActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}