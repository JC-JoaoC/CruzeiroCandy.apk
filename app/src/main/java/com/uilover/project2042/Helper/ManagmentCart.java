package com.uilover.project2042.Helper;

import android.content.Context;
import android.widget.Toast;

import com.uilover.project2042.Model.ItemsModel;

import java.util.ArrayList;

public class ManagmentCart {
    private com.uilover.project2042.Helper.TinyDB tinyDB;
    private Context context;

    public ManagmentCart(Context context) {
        tinyDB = new com.uilover.project2042.Helper.TinyDB(context);
        this.context = context;
    }

    public void insertItems(ItemsModel item) {
        ArrayList<ItemsModel> listItems = getListCart();
        boolean existAlready = false;
        int index = -1;

        for (int i = 0; i < listItems.size(); i++) {
            if (listItems.get(i).getTitle().equals(item.getTitle())) {
                existAlready = true;
                index = i;
                break;
            }
        }

        if (existAlready) {
            listItems.get(index).setNumberInCart(item.getNumberInCart());
        } else {
            listItems.add(item);
        }
        tinyDB.putListObject("CartList", listItems);
        Toast.makeText(context, "Adicionado no seu carrinho!", Toast.LENGTH_SHORT).show();
    }

    public ArrayList<ItemsModel> getListCart() {
        return tinyDB.getListObject("CartList");
    }

    public void minusItem(ArrayList<ItemsModel> listItems, int position, ChangeNumberItemsListener listener) {
        if (listItems.get(position).getNumberInCart() == 1) {
            listItems.remove(position);
        } else {
            listItems.get(position).setNumberInCart(listItems.get(position).getNumberInCart() - 1);
        }
        tinyDB.putListObject("CartList", listItems);
        listener.onChanged();
    }

    public void plusItem(ArrayList<ItemsModel> listItems, int position, ChangeNumberItemsListener listener) {
        listItems.get(position).setNumberInCart(listItems.get(position).getNumberInCart() + 1);
        tinyDB.putListObject("CartList", listItems);
        listener.onChanged();
    }

    public double getTotalFee() {
        ArrayList<ItemsModel> listItems = getListCart();
        double fee = 0.0;
        for (ItemsModel item : listItems) {
            fee += item.getPrice() * item.getNumberInCart();
        }
        return fee;
    }
}
