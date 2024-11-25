package com.uilover.project2042.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.uilover.project2042.R;
import com.uilover.project2042.databinding.ActivityIntroBinding;
import com.uilover.project2042.databinding.ActivitySecondBinding;

public class SecondActivity extends BaseActivity {
    private ActivitySecondBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySecondBinding.inflate(LayoutInflater.from(this));
        setContentView(binding.getRoot());

        binding.TemContaBtn.setOnClickListener(v -> {
            Intent intent=new Intent(SecondActivity.this, LoginActivity.class);
            startActivity(intent);

        });

            binding.CreateBtn.setOnClickListener(v -> {
                Intent intent=new Intent(SecondActivity.this, SigninActivity.class);
                startActivity(intent);

            });


        binding.convidadoBtn.setOnClickListener(v -> {
            Intent intent=new Intent(SecondActivity.this, MainActivity.class);
            startActivity(intent);
        });

        Window window=getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        View decor=window.getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

    }
}