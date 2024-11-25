package com.uilover.project2042.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.RequestOptions;
import com.uilover.project2042.Adapter.PicListAdapter;
import com.uilover.project2042.Adapter.WeightAdapter;
import com.uilover.project2042.Helper.ManagmentCart;
import com.uilover.project2042.Model.ItemsModel;
import com.uilover.project2042.databinding.ActivityDetailBinding;

import java.util.ArrayList;

public class DetailActivity extends BaseActivity {
    private ActivityDetailBinding binding;
    private ItemsModel item;
    private int numberOrder = 1;
    private ManagmentCart managmentCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        managmentCart = new ManagmentCart(this);

        getBundleExtra();
        initLists();


        Window window = getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        View decor = window.getDecorView();
        decor.setSystemUiVisibility(0);
    }

    private void initLists() {
        ArrayList<String> weightList = new ArrayList<>(item.getSize());

        binding.weightList.setAdapter(new WeightAdapter(weightList));
        binding.weightList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        ArrayList<String> picList = new ArrayList<>(item.getPicUrl());

        Glide.with(this)
                .load(picList.get(0))
                .into(binding.picMain);

        binding.picList.setAdapter(new PicListAdapter(picList, binding.picMain));
        binding.picList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void getBundleExtra() {
        item = (ItemsModel) getIntent().getSerializableExtra("object");

        binding.titleTxt.setText(item.getTitle());
        binding.descriptionTxt.setText(item.getDescription());
        binding.priceTxt.setText("R$" + item.getPrice());
        binding.ratingTxt.setText(item.getRating() + " ");
        binding.sellerNameTxt.setText(item.getSellerName());

        binding.addToCardBtn.setOnClickListener(v -> {
            item.setNumberInCart(numberOrder);
            managmentCart.insertItems(item);
        });

        binding.backBtn.setOnClickListener(v -> startActivity(new Intent(DetailActivity.this, MainActivity.class)));
        binding.cartBtn.setOnClickListener(v -> {

                    if (!managmentCart.getListCart().isEmpty()) {
                        startActivity(new Intent(DetailActivity.this, CartActivity.class));
                    } else {
                        Toast.makeText(DetailActivity.this, "Seu carrinho está vazio", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        Glide.with(this)
                .load(item.getSellerPic())
                .apply(new RequestOptions().transform(new CenterCrop()))
                .into(binding.picSeller);

        binding.msgToSellerBtn.setOnClickListener(v -> {
            Intent sendIntent = new Intent(Intent.ACTION_VIEW);
            sendIntent.setData(Uri.parse("mensagem:" + item.getSellerTell()));
            sendIntent.putExtra("mensagem_corpo", "escreva sua mensagem");
            startActivity(sendIntent);
        });

        binding.callToSellerBtn.setOnClickListener(v -> {
            String phone = String.valueOf(item.getSellerTell());
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
            startActivity(intent);
        });
    }
}