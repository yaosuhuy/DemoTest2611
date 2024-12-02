package com.example.demotest1611;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class ThemSanPhamActivity extends AppCompatActivity {

    Button btnThem, btnSua, btnXoa, btnHienThi;

    EditText txtMa, txtTen, txtSoLuong;

    Context context;

    SanPhamDAO sanPhamDAO ; // tạo csdl và bảng dữ liệu
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_san_pham);

        txtMa = findViewById(R.id.maSPEdt);
        txtTen = findViewById(R.id.tenSPEdt);
        txtSoLuong = findViewById(R.id.soluongSPEdt);
        btnThem = findViewById(R.id.addBtn);
//        btnSua = findViewById(R.id.updateBtn);
//        btnXoa = findViewById(R.id.delBtn);
//        btnHienThi = findViewById(R.id.showBtn);
        context = this;
        sanPhamDAO = new SanPhamDAO(context);
        // btn hien thi
//        btnHienThi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                list.clear(); // xóa hết nội dung trong list
//                SanPhamDAO sanPhamDAO = new SanPhamDAO(context);
//                list = sanPhamDAO.getAllSanPhamToString();
//                ArrayAdapter arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, list);
//                listView.setAdapter(arrayAdapter);
//            }
//        });

        // btn them
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SanPham s = new SanPham(); // tao san pham chua du lieu nguoi dung nhap

                // dua du lieu vao doi tuong
                s.setMaSP(txtMa.getText().toString());
                s.setTenSP(txtTen.getText().toString());
                s.setSoLuongSP(Integer.parseInt(txtSoLuong.getText().toString()));

                // goi ham insert
                int kq = sanPhamDAO.InsertSanPham(s);
                if (kq == -1) {
                    Toast.makeText(context, "Thêm sản phẩm thất bại!", Toast.LENGTH_LONG).show();
                }
                if (kq == 1) {
                    // Truyền dữ liệu sang MainActivity
                    Intent intent = new Intent(ThemSanPhamActivity.this, MainActivity.class);
                    intent.putExtra("maSP", s.getMaSP());
                    intent.putExtra("tenSP", s.getTenSP());
                    intent.putExtra("soLuongSP", s.getSoLuongSP());
                    startActivity(intent);
                    Toast.makeText(context, "Thêm sản phẩm thành công!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnSua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SanPham s = new SanPham(); // tao san pham chua du lieu nguoi dung nhap

                // dua du lieu vao doi tuong
                s.setMaSP(txtMa.getText().toString());
                s.setTenSP(txtTen.getText().toString());
                s.setSoLuongSP(Integer.parseInt(txtSoLuong.getText().toString()));

                // goi ham insert
                int kq = sanPhamDAO.UpdateSanPham(s);
                if (kq == -1) {
                    Toast.makeText(context, "Sửa sản phẩm thất bại!", Toast.LENGTH_LONG).show();
                }
                if (kq == 1) {
                    Toast.makeText(context, "Sửa sản phẩm thành công!", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int kq = sanPhamDAO.DeleteSanPham(txtMa.getText().toString());
                if (kq == -1) {
                    Toast.makeText(context, "Xóa sản phẩm thất bại!", Toast.LENGTH_LONG).show();
                }
                if (kq == 1) {
                    Toast.makeText(context, "Xóa sản phẩm thành công!", Toast.LENGTH_LONG).show();
                }

            }
        });
    }
}