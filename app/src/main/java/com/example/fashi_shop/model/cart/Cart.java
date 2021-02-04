package com.example.fashi_shop.model.cart;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.fashi_shop.model.Product;

import java.util.ArrayList;
import java.util.List;

public class Cart {

    SQLiteDatabase database;

    public void ConnectSQL(Context context) {
        DataProduct dataProduct = new DataProduct(context);
        database = dataProduct.getWritableDatabase();
    }

    public boolean addCartItem(Product product) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(DataProduct.TB_GIOHANG_MASP, product.getId());
        contentValues.put(DataProduct.TB_GIOHANG_TENSP, product.getName());
        contentValues.put(DataProduct.TB_GIOHANG_GIATIEN, product.getPrice());
        contentValues.put(DataProduct.TB_GIOHANG_HINHANH, product.getImage_gio_hang());

        long id = database.insert(DataProduct.TB_GIOHANG, null, contentValues);
        if (id > 0) {
            return true;
        } else {
            return false;
        }
    }

    public List<Product> loadProductCart() {
        List<Product> products = new ArrayList<>();

        String query = "SELECT * FROM " + DataProduct.TB_GIOHANG;
        Cursor cursor = database.rawQuery(query, null);

        cursor.moveToFirst();

        while (!cursor.isAfterLast()) {
            int id = cursor.getInt(cursor.getColumnIndex(DataProduct.TB_GIOHANG_MASP));
            String name = cursor.getString(cursor.getColumnIndex(DataProduct.TB_GIOHANG_TENSP));
            int price = cursor.getInt(cursor.getColumnIndex(DataProduct.TB_GIOHANG_GIATIEN));
            byte[] image = cursor.getBlob(cursor.getColumnIndex(DataProduct.TB_GIOHANG_HINHANH));

            Product product = new Product();
            product.setId(id);
            product.setName(name);
            product.setPrice(price);
            product.setImage_gio_hang(image);

            products.add(product);
            cursor.moveToNext();
        }
        return products;
    }

    public boolean DeleteProduct(int id) {
      int check =  database.delete(DataProduct.TB_GIOHANG, DataProduct.TB_GIOHANG_MASP + "=" + id, null);
      if (check > 0){
            return true;
      }else {
          return false;
      }
    }

}
