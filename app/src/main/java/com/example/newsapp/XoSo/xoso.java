package com.example.newsapp.XoSo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ActivityOptions;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.airbnb.lottie.animation.content.Content;
import com.example.newsapp.Card.CardTrangChu_Adapter;
import com.example.newsapp.Card.ClickItem;
import com.example.newsapp.Card.NoiDungModel;
import com.example.newsapp.DocBao.docbaovideo;
import com.example.newsapp.GiaVang.giavang;
import com.example.newsapp.R;
import com.example.newsapp.TrangChu.AdapterViewPaper;
import com.example.newsapp.TrangChu.taikhoan;
import com.example.newsapp.TrangChu.tintuc;
import com.example.newsapp.TrangChu.xuhuong;
import com.example.newsapp.fg_xoso_mienbac;
import com.example.newsapp.fg_xoso_mientrung;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

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
                        String date = day+"/"+month+"/"+year;
                        tv_date.setText(date);
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
    }

    private void khaibao() {
        IMG_xoso_back = findViewById(R.id.img_xoso_back);
        tv_date = findViewById(R.id.tv_date);
        ll_date = findViewById(R.id.ll_date);
        viewPager = findViewById(R.id.view_pager_xoso);
        bottomNavigationView = findViewById(R.id.bottom_navi_xoso);
    }

    //Hàm quay về màn hình trước
    public void backFromXoso(View view){

        Intent intent = new Intent(getApplicationContext(), taikhoan.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.LayoutXoso),"transition_taikhoan");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(xoso.this,pairs);
            startActivity(intent,options.toBundle());
        }else {
            startActivity(intent);
        }
    }
}