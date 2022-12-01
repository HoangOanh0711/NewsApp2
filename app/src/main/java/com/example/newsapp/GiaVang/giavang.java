package com.example.newsapp.GiaVang;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.newsapp.R;
import com.example.newsapp.TrangChu.taikhoan;

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

    Elements data,data1;
    Document document;

    String giocapnhat,tenhang,tienmua,tienban,linkbai;

    Spinner spnThumbnail;
    ThumbnailAdapter thumbnailAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_giavang);

        IMG_giavang_back = findViewById(R.id.img_giavang_back);
        TXT_giavang_ngay = findViewById(R.id.txt_giavang_ngay);
        rcvGiavang = findViewById(R.id.rcv_giavang);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rcvGiavang.setLayoutManager(llm);

        spnThumbnail = findViewById(R.id.spn_thumbnail);
        thumbnailAdapter = new ThumbnailAdapter(this, R.layout.item_selected_thumbnail, getListThumbnail());
        spnThumbnail.setAdapter(thumbnailAdapter);

        spnThumbnail.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                switch (thumbnailAdapter.getItem(i).getThanhpho()) {
                    case "Hồ Chí Minh":
                        linkbai = "https://www.pnj.com.vn/blog/gia-vang/?zone=00";
                    case "Cần Thơ":
                        linkbai = "https://www.pnj.com.vn/blog/gia-vang/?zone=07";
                    case "Hà Nội":
                        linkbai = "https://www.pnj.com.vn/blog/gia-vang/?zone=11";
                    case "Đà Nẵng":
                        linkbai = "https://www.pnj.com.vn/blog/gia-vang/?zone=13";
                    case "Tây Nguyên":
                        linkbai = "https://www.pnj.com.vn/blog/gia-vang/?zone=14";
                    case "Đông Nam Bộ":
                        linkbai = "https://www.pnj.com.vn/blog/gia-vang/?zone=21";
                }
                giavang.Content content = new giavang.Content();
                content.execute();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
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
                String url = linkbai;
                document = Jsoup.connect(url).get();
                //dữ liệu giá vàng - rồi
                data = document.select("div.bang-gia-vang-outer>div.content");
                giocapnhat = data.select("h2").text();

                data1 = data.select("table>tbody#content-price>tr");
                int size = data1.size();
                for (int i=0; i<size;i++) {
                    tenhang = data1.eq(i).select("td").eq(0).text();
                    tienmua = data1.eq(i).select("td").eq(1).text();
                    tienban = data1.eq(i).select("td").eq(2).text();
                    giaVangModelList.add(new GiaVangModel(giocapnhat,tenhang,tienmua,tienban));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }

    private List<Thumbnail> getListThumbnail() {
        List<Thumbnail> list = new ArrayList<>();
        list.add(new Thumbnail("Hồ Chí Minh"));
        list.add(new Thumbnail("Cần Thơ"));
        list.add(new Thumbnail("Hà Nội"));
        list.add(new Thumbnail("Đà Nẵng"));
        list.add(new Thumbnail("Tây Nguyên"));
        list.add(new Thumbnail("Đông Nam Bộ"));
        return list;
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