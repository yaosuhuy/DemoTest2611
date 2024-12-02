package com.example.demotest1611;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class SanPhamDAO {
    private SQLiteDatabase db;
    private SQLiteHelper dbHelper;
    private Context context;

    public SanPhamDAO(Context context) {
        this.context = context;
        dbHelper = new SQLiteHelper(context); // thuc thi tao db
        db = dbHelper.getWritableDatabase(); // cho phep ghi du lieu vao database
    }

    // 1. Them du lieu
    public int InsertSanPham(SanPham s) {
        ContentValues values = new ContentValues(); // tao doi tuong chua du lieu
        // Dua du lieu vao doi tuong chua
        values.put("maSP", s.getMaSP());
        values.put("tenSP", s.getTenSP());
        values.put("soLuongSP", s.getSoLuongSP());

        // thuc thi insert
        long kq = db.insert("sanpham", null, values);

        // kiem tra kq
        if (kq <= 0) {
            return -1;
            // insert that bai
        }
        return 1; // insert thanh cong
    }

    // 2. Hien thi du lieu dang string
    public List<String> getAllSanPhamToString() {
        List<String> ls = new ArrayList<>(); // tao mot danh sach rong
        // Tao con tro doc bang du lieu san pham
        Cursor c = db.query("sanpham", null, null, null, null, null, null);

        c.moveToFirst(); // di chuyen con tro ve ban ghi dau tien

        // doc
        while (!c.isAfterLast()) // trong khi khong phai dong cuoi cung
        {
            SanPham s = new SanPham(); // tao doi tuong moi de chua du lieu
            s.setMaSP(c.getString(0)); // doc du lieu truong ma san pham va dua vao doi tuong
            s.setTenSP(c.getString(1)); // doc du lieu truong ten san pham
            s.setSoLuongSP(c.getInt(2)); // doc du lieu truong so luong

            // Chuyen doi tuong thanh chuoi
            String chuoi = s.getMaSP() + " - " + s.getTenSP() + " - " + s.getSoLuongSP();

            // dua chuoi vao líst
            ls.add(chuoi);
            c.moveToNext();
        }
        c.close(); // dong con tro
        return ls;
    }
    // xóa
    public int DeleteSanPham(String masp){
        // thuc thi xoa
        int kq = db.delete("sanpham", "maSP = ?" , new String[]{masp});
        // kiem tra kq
        if (kq <= 0) {
            return -1;
            // delete that bai
        }
        return 1; // delete thanh cong
    }
    // UPDATE
    public int UpdateSanPham(SanPham s){
        ContentValues values = new ContentValues(); // tao doi tuong chua du lieu

        values.put("maSP", s.getMaSP());
        values.put("tenSP", s.getTenSP());
        values.put("soLuongSP", s.getSoLuongSP());

        // thuc thi update
        long kq = db.update("sanpham", values, "maSP = ?",new String[]{s.getMaSP()});
        // kiem tra kq
        if (kq <= 0) {
            return -1;
            // delete that bai
        }
        return 1; // delete thanh cong
    }

    public List<String> getFilteredSanPhamToString(String keyword) {
        List<String> filteredList = new ArrayList<>();

        // Lấy tất cả sản phẩm từ CSDL (giả định là đã có phương thức getAllSanPhamToString)
        List<String> allProducts = getAllSanPhamToString();

        // Lọc các sản phẩm dựa trên từ khóa
        for (String product : allProducts) {
            if (product.toLowerCase().contains(keyword.toLowerCase())) {
                filteredList.add(product);
            }
        }
        return filteredList;
    }
}


