package com.example.demotest1611;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button btnPlus;
    ListView listView;
    EditText searchEdt;
    Context context;
    List<String> list = new ArrayList<>(); // tạo list rỗng

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.demoListView);
        btnPlus = findViewById(R.id.button);
        searchEdt = findViewById(R.id.searchEdt);


        // Khoi tao cac bien
        context = this;

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        String maSP = intent.getStringExtra("maSP");
        String tenSP = intent.getStringExtra("tenSP");
        int soLuongSP = intent.getIntExtra("soLuongSP", 0);

        // hien thi du lieu khi chay chuong trinh
        list.clear(); // xóa hết nội dung trong list
        SanPhamDAO sanPhamDAO = new SanPhamDAO(context); // tạo csdl và bảng dữ liệu
        list = sanPhamDAO.getAllSanPhamToString(); // lấy toàn bộ sanPham trong bảng sanpham
        ArrayAdapter arrayAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, list); // tạo adapter
        listView.setAdapter(arrayAdapter); // đẩy dữ liệu vào listView
        //---
        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ThemSanPhamActivity.class);
                startActivity(i);
            }
        });

        // Lắng nghe sự kiện thay đổi văn bản của EditText
        searchEdt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // không cần xử lý trước khi văn bản thay đổi
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                // không cần xử lý khi văn bản thay đổi
            }

            @Override
            public void afterTextChanged(Editable editable) {
                // Khi văn bản thay đổi, lọc dữ liệu và hiển thị lại ListView
                filterProducts(editable.toString());
            }
            private void filterProducts(String keyword) {
                List<String> filteredList = sanPhamDAO.getFilteredSanPhamToString(keyword);
                arrayAdapter.clear();
                arrayAdapter.addAll(filteredList);
                arrayAdapter.notifyDataSetChanged();
            }
        });
    }
}