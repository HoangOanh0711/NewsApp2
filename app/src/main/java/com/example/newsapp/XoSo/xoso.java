package com.example.newsapp.XoSo;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.animation.content.Content;
import com.example.newsapp.Card.CardTrangChu_Adapter;
import com.example.newsapp.Card.ClickItem;
import com.example.newsapp.Card.NoiDungModel;
import com.example.newsapp.DocBao.docbaovideo;
import com.example.newsapp.GiaVang.giavang;
import com.example.newsapp.R;
import com.example.newsapp.TrangChu.taikhoan;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Calendar;

public class xoso extends AppCompatActivity {

    ImageView IMG_xoso_back;
    TextView tv_date;

    Elements data,data1;
    Document document;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xoso);

        khaibao();

        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);

        tv_date.setOnClickListener(new View.OnClickListener() {
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

        Content content = new Content();
        content.execute();
    }

    private class Content extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
        }
        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String url = "https://www.minhngoc.net.vn/ket-qua-xo-so/mien-nam/23-11-2022.html";
                document = Jsoup.connect(url).get();

                data = document.select("div.box_kqxs>div.content>table.bkqmiennam>tbody>tr>td>table.leftcl>tbody>tr");
                int size = data.size();
                Log.e("size", String.valueOf(size));
                for (int i=0;i<size;i++) {
                    String tieude = data1.select("a").eq(i).text();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
    private void khaibao() {
        IMG_xoso_back = findViewById(R.id.img_xoso_back);
        tv_date = findViewById(R.id.tv_date);
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