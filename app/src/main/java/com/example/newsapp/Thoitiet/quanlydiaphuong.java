package com.example.newsapp.Thoitiet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.newsapp.R;
import com.example.newsapp.TruyenDuLieu;

import java.util.ArrayList;
import java.util.List;

public class quanlydiaphuong extends AppCompatActivity {
    LinearLayout btn_themdiaphuong;
    ImageView btn_quaylai;

    RecyclerView rcvThoitiet3;
    Thoitiet3_Adapter thoitiet3_adapter;
    List<Thoitiet3> thoitiet3s;
    String tp,tentp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quanlydiaphuong);

        khaibao();

        thoitiet3_adapter = new Thoitiet3_Adapter(this);
        rcvThoitiet3.setLayoutManager(new LinearLayoutManager(this, RecyclerView.VERTICAL,false));

        thoitiet3s = new ArrayList<>();
        thoitiet3_adapter.setMthoitiet3(thoitiet3s);
        rcvThoitiet3.setAdapter(thoitiet3_adapter);

        thoitiet3s.add(new Thoitiet3("Thành phố Hồ Chí Minh","TP. Hồ Chí Minh"));

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            tp = extras.getString("Địa phương đã chọn");
            tentp = extras.getString("Tên địa phương đã chọn");
            thoitiet3s.add(new Thoitiet3(tp,tentp));
        }

        btn_themdiaphuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(quanlydiaphuong.this, chonthanhpho.class);
                startActivity(intent);
            }
        });

        btn_quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(quanlydiaphuong.this, thoitiet.class);
                startActivity(intent);
            }
        });
    }

    private void khaibao() {
        btn_themdiaphuong = findViewById(R.id.btn_themdiaphuong);
        btn_quaylai = findViewById(R.id.img_quaylai_quanlydiaphuong);
        rcvThoitiet3 = findViewById(R.id.recyclerView_quanlydiaphuong);

    }
}