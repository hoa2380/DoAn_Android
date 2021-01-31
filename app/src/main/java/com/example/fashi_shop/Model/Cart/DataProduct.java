package com.example.fashi_shop.Model.Cart;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataProduct extends SQLiteOpenHelper {

//    public static String TB_GIOHANG = "GIOHANG";
//    public static String TB_GIOHANG_IDPRODUCT = "ID";
//    public static String TB_GIOHANG_NAMEPRODUCT = "NAME";
//    public static String TB_GIOHANG_PRICEPRODUCT = "PRICE";
//    public static String TB_GIOHANG_IMAGEPRODUCT = "IMAGE";

    public static String TB_GIOHANG = "GIOHANG";
    public static String TB_GIOHANG_MASP = "MASP";
    public static String TB_GIOHANG_TENSP = "TENSP";
    public static String TB_GIOHANG_GIATIEN = "GIATIEN";
    public static String TB_GIOHANG_HINHANH = "HINHANH";
    public static String TB_GIOHANG_SOLUONG = "SOLUONG";
    public static String TB_GIOHANG_SOLUONGTONKHO = "SOLUONGTONKHO";

    public static String TB_FAVOR = "FAVOR";
    public static String TB_FAVOR_IDPRODUCT = "ID";
    public static String TTB_FAVOR_NAMEPRODUCT = "NAME";
    public static String TTB_FAVORPRICEPRODUCT = "PRICE";
    public static String TB_FAVOR_IMAGEPRODUCT = "IMAGE";

    public DataProduct(Context context) {
        super(context, "ProductManager", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
//        String tblGioHang = "CREATE TABLE  " + TB_GIOHANG + " (" + TB_GIOHANG_IDPRODUCT + " INTEGER PRIMARY KEY AUTOINCREMENT, " + TB_GIOHANG_NAMEPRODUCT + " TEXT, " + TB_GIOHANG_PRICEPRODUCT + " REAL, " + TB_GIOHANG_IMAGEPRODUCT + " BLOB); ";
        String tblGioHang = "CREATE TABLE " + TB_GIOHANG + " (" + TB_GIOHANG_MASP + " INTEGER PRIMARY KEY , "
                + TB_GIOHANG_TENSP + " TEXT, " + TB_GIOHANG_GIATIEN + " REAL, " +TB_GIOHANG_HINHANH + "  BLOB, "
                + TB_GIOHANG_SOLUONG + " INTEGER, " + TB_GIOHANG_SOLUONGTONKHO + " INTEGER );";
        String tblFavor = "CREATE TABLE  " + TB_FAVOR + " (" + TB_FAVOR_IDPRODUCT + " INTEGER PRIMARY KEY, " + TTB_FAVOR_NAMEPRODUCT + " TEXT, " + TTB_FAVORPRICEPRODUCT + " REAL, " + TB_FAVOR_IMAGEPRODUCT + " BLOB); ";
        db.execSQL(tblGioHang);
        db.execSQL(tblFavor);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
