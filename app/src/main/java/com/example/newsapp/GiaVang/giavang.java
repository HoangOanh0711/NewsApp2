package com.example.newsapp.GiaVang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.animation.content.Content;
import com.example.newsapp.Card.CardTrangChu_Adapter;
import com.example.newsapp.Card.ClickItem;
import com.example.newsapp.Card.NoiDungModel;
import com.example.newsapp.DocBao.docbao;
import com.example.newsapp.R;
import com.example.newsapp.Thoitiet.Thoitiet1;
import com.example.newsapp.Thoitiet.Thoitiet1_Adapter;
import com.example.newsapp.TinTuc.fg_moi;
import com.example.newsapp.TrangChu.taikhoan;
import com.google.android.material.tabs.TabLayout;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class giavang extends AppCompatActivity {

    TextView TXT_giavang_ngay;
    ImageView IMG_giavang_back;

    RecyclerView rcvGiavang;
    GiaVang_Adapter giaVang_adapter;
    List<GiaVangModel> giaVangModelList = new ArrayList<>();

    Elements data;
    Document document;

    String giocapnhat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giavang);

        IMG_giavang_back = findViewById(R.id.img_giavang_back);
        TXT_giavang_ngay = findViewById(R.id.txt_giavang_ngay);
        rcvGiavang = findViewById(R.id.rcv_giavang);

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
            giaVang_adapter = new GiaVang_Adapter(giaVangModelList);
            rcvGiavang.setAdapter(giaVang_adapter);
            giaVang_adapter.notifyDataSetChanged();

            TXT_giavang_ngay.setText(giocapnhat);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String url = "https://dantri.com.vn/gia-vang.htm";
                document = Jsoup.connect(url).get();
                /*data = document.select("table.table-hover.table-gold-provider>tbody>tr");
                int size = data.size();
                for (int i=0; i<size;i++) {
                    String anhhang = data.select("a.img212x132.pos-rlt").eq(i).select("img").attr("src");
                    String tenhang = data.select("h3.title-news").eq(i).text();

                    String tienmua = data.select("p.sapo").eq(i).text();
                    String tangmua = data.select("p.sapo").eq(i).text();

                    String tienban = data.select("p.sapo").eq(i).text();
                    String tangban = data.select("p.sapo").eq(i).text();
                    giaVangModelList.add(new GiaVangModel(anhhang,tenhang,tienmua,tangmua,tienban,tangban));
                }
                data1 = document.select("table.table-hover.table-gold-provider>tbody>tr");
                giocapnhat = data.select("h3.title-news").text();
                */
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }


    //Hàm quay về màn hình trước
    public void backFromGiaVang(View view){
        Intent intent = new Intent(getApplicationContext(), taikhoan.class);
        Pair[] pairs = new Pair[1];
        pairs[0] = new Pair<View, String>(findViewById(R.id.layoutgiavang),"transition_taikhoan");

        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(giavang.this,pairs);
            startActivity(intent,options.toBundle());
        }else {
            startActivity(intent);
        }
    }
}