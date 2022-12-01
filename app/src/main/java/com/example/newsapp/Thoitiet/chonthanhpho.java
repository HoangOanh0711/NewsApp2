package com.example.newsapp.Thoitiet;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.newsapp.R;
import com.example.newsapp.TruyenDuLieu;

import java.util.ArrayList;
import java.util.List;

public class chonthanhpho extends AppCompatActivity {
    SearchView searchView;
    ListView listView;
    ImageView btn_quaylai;
    String[] tentp = {"TP. Hồ Chí Minh","Hà Nội","An Giang","Bà Rịa - Vũng Tàu","Bắc Giang","Bắc Kạn","Bạc Liêu","Bắc Ninh","Bến Tre","Bình Định","Bình Dương","Bình Phước","Bình Thuận","Cà Mau","Cần Thơ","Cao Bằng","Đà Nẵng","Đắk Lắk","Đắk Nông","Điện Biên","Đồng Nai","Đồng Tháp","Gia Lai","Hà Giang","Hà Nam","Hà Tĩnh","Hải Dương","Hải Phòng","Hậu Giang","Hòa Bình","Hưng Yên","Khánh Hòa","Kiên Giang","Kon Tum","Lai Châu","Lâm Đồng","Lạng Sơn","Lào Cai","Long An","Nam Định","Nghệ An","Ninh Bình","Ninh Thuận","Phú Thọ","Phú Yên","Quảng Bình","Quảng Nam","Quảng Ngãi","Quảng Ninh","Quảng Trị","Sóc Trăng","Sơn La","Tây Ninh","Thái Bình","Thái Nguyên","Thanh Hóa","Thừa Thiên Huế","Tiền Giang","Trà Vinh","Tuyên Quang","Vĩnh Long","Vĩnh Phúc","Yên Bái"};
    String[] tpList = {"Thành phố Hồ Chí Minh","Ha Noi","Tỉnh An Giang" ,  "Vung Tau" ,  "Bắc Giang" ,  "Bắc Kạn" ,  "Bạc Liêu" ,  "Bac Ninh" ,  "Ben Tre" ,  "Bình Định" ,  "Binh Duong" ,  "Dong xoai" ,  "Phan Thiet" ,  "Ca Mau" ,  "Can Tho" ,  "Cao Bằng" ,  "Turan" ,  "Buon Ma Thuot" ,  "Buon Ma Thuot" ,  "Dien Bien Phu" ,  "Long Khanh" ,  "Sa Dec" ,  "Tỉnh Gia Lai" ,  "Ha Giang" ,  "Hà Nam" ,  "Hà Tĩnh" ,  "hai duong" ,  "Haiphong" ,  "Can Tho" ,  "Hòa Bình" ,  "Hưng Yên" ,  "Khánh Hòa" ,  "Rach Gia" ,  "Kon Tum" ,  "Lai Châu" ,  "Da Lat" ,  "Lạng Sơn" ,  "Lào Cai" ,  "Long An" ,  "Nam Định" ,  "Vinh" ,  "Ninh Bình" ,  "Phan Rang-Thap Cham" ,  "Phú Thọ" ,  "Quy Nhon" ,  "Dong Hoi" ,  "Hoi An" ,  "Quảng Ngãi" ,  "Ha Long" ,  "Quảng Trị" ,  "Sóc Trăng" ,  "Sơn La" ,  "Phu Khuong" ,  "Thái Bình" ,  "Thai Nguyen" ,  "Thanh Hóa" ,  "Hue" ,  "Cai Be" , "Trà Vinh" ,  "Tuyên Quang" ,  "Vĩnh Long" ,  "Vĩnh Phúc" ,  "Yên Bái"};

    ArrayAdapter<String> arrayAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chonthanhpho);

        khaibao();

        arrayAdapter = new ArrayAdapter<>(chonthanhpho.this,android.R.layout.simple_list_item_1,tentp);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(chonthanhpho.this, quanlydiaphuong.class);
                intent.putExtra("Địa phương đã chọn",tpList[i]);
                intent.putExtra("Tên địa phương đã chọn",adapterView.getItemAtPosition(i).toString());
                startActivity(intent);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                chonthanhpho.this.arrayAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                chonthanhpho.this.arrayAdapter.getFilter().filter(newText);
                return false;
            }
        });

        btn_quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(chonthanhpho.this, quanlydiaphuong.class);
                startActivity(intent);
            }
        });
    }

    private void khaibao() {
        searchView = findViewById(R.id.searchView);
        listView = findViewById(R.id.list_chontp);
        btn_quaylai = findViewById(R.id.img_quaylai_chontp);
    }


}