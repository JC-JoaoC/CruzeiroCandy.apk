package com.uilover.project2042.Activity;

import android.content.Intent;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.uilover.project2042.R;
import com.uilover.project2042.databinding.ActivityLoginBinding;
import com.uilover.project2042.databinding.ActivityPerfilBinding;

public class LoginActivity extends AppCompatActivity {
    private ActivityLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        conectar();
    }
    private void conectar() {
        binding.loginBtn.setOnClickListener(v -> {
            Intent intent=new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
        });
    }
}