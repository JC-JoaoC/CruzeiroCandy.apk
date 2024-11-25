package com.uilover.project2042.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.uilover.project2042.Adapter.CartAdapter;
import com.uilover.project2042.Helper.ManagmentCart;
import com.uilover.project2042.databinding.ActivityCartBinding;

public class CartActivity extends BaseActivity {
    private ActivityCartBinding binding;
    private ManagmentCart managmentCart;
    private double tax = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        managmentCart = new ManagmentCart(this);

        setVarialble();
        initCartList();
        calculatorCart();

        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        View decor = window.getDecorView();
        decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    private void initCartList() {
        binding.cartView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.cartView.setAdapter(new CartAdapter(managmentCart.getListCart(), this, this::calculatorCart));
    }

    private void calculatorCart() {
        double percentTax = 0.02;
        double delivery = 15.0;
        tax = Math.round(managmentCart.getTotalFee() * percentTax * 100) / 100.0;
        double total = Math.round((managmentCart.getTotalFee() + tax + delivery) * 100) / 100.0;
        double itemTotal = Math.round(managmentCart.getTotalFee() * 100) / 100.0;

        binding.totalFeeTxt.setText("R$" + itemTotal);
        binding.taxTxt.setText("R$" + tax);
        binding.deliveryTxt.setText("R$" + delivery);
        binding.totalTxt.setText("R$" + total);
    }

    private void setVarialble() {
        binding.backBtn.setOnClickListener(v -> startActivity(new Intent(CartActivity.this, MainActivity.class)));
    }
}