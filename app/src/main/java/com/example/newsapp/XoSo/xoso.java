package com.example.newsapp.XoSo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ActivityOptions;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.newsapp.LichVanNien.lichvannien;
import com.example.newsapp.R;
import com.example.newsapp.TrangChu.AdapterViewPaper;
import com.example.newsapp.TrangChu.taikhoan;
import com.example.newsapp.TrangChu.trangchu;
import com.example.newsapp.TruyenDuLieu;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class xoso extends AppCompatActivity {

    ViewPager2 viewPager;
    BottomNavigationView bottomNavigationView;

    ArrayList<Fragment> fragmentArrayList = new ArrayList<>();

    ImageView IMG_xoso_back;
    TextView tv_date;
    LinearLayout ll_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xoso);

        khaibao();

        Calendar calendar = Calendar.getInstance();
        if (TruyenDuLieu.Truyen_NgayXoso==""){
            SimpleDateFormat dinhDangNgay = new SimpleDateFormat("dd-MM-yyyy");
            TruyenDuLieu.Truyen_NgayXoso = dinhDangNgay.format(calendar.getTime());
        }
        tv_date.setText(TruyenDuLieu.Truyen_NgayXoso);

        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        ll_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(xoso.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        month = month+1;
                        TruyenDuLieu.Truyen_NgayXoso = day+"-"+month+"-"+year;
                        tv_date.setText(TruyenDuLieu.Truyen_NgayXoso);
                        finish();
                        overridePendingTransition(0, 0);
                        startActivity(getIntent());
                        overridePendingTransition(0, 0);
                    }
                },year,month,day);
                datePickerDialog.show();
            }
        });

        fragmentArrayList.add(new fg_xoso_mienbac());
        fragmentArrayList.add(new fg_xoso_mientrung());
        fragmentArrayList.add(new fg_xoso_miennam());

        AdapterViewPaper viewPaperAdapter = new AdapterViewPaper(this, fragmentArrayList);
        viewPager.setAdapter(viewPaperAdapter);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        bottomNavigationView.setSelectedItemId(R.id.nav_mienbac);
                        break;
                    case 1:
                        bottomNavigationView.setSelectedItemId(R.id.nav_mientrung);
                        break;
                    case 2:
                        bottomNavigationView.setSelectedItemId(R.id.nav_miennam);
                        break;
                }
                super.onPageSelected(position);
            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_mienbac:
                        viewPager.setCurrentItem(0);
                        break;
                    case R.id.nav_mientrung:
                        viewPager.setCurrentItem(1);
                        break;
                    case R.id.nav_miennam:
                        viewPager.setCurrentItem(2);
                        break;
                }
                return true;
            }
        });

        IMG_xoso_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(xoso.this, trangchu.class);
                startActivity(intent);
            }
        });
    }

    private void khaibao() {
        IMG_xoso_back = findViewById(R.id.img_xoso_back);
        tv_date = findViewById(R.id.tv_date);
        ll_date = findViewById(R.id.ll_date);
        viewPager = findViewById(R.id.view_pager_xoso);
        bottomNavigationView = findViewById(R.id.bottom_navi_xoso);
    }
}