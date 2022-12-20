package com.example.newsapp.DocBao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.animation.content.Content;
import com.bumptech.glide.Glide;
import com.example.newsapp.Card.CardTrangChu_Adapter;
import com.example.newsapp.Card.ClickItem;
import com.example.newsapp.Card.NoiDungModel;
import com.example.newsapp.R;
import com.example.newsapp.Thoitiet.Thoitiet2_Adapter;
import com.example.newsapp.TinTuc.fg_moi;
import com.example.newsapp.TrangChu.trangchu;
import com.example.newsapp.TruyenDuLieu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class docbao extends AppCompatActivity {
    String linkbao,chude,tieude1,tgian,tieude2,anhbao,tenanh,ndung,tacgia;
    String[] ndungs = new String[50];

    TextView txt_chude,txt_tieude1,txt_tgian,txt_tieude2,txt_tacgia, txt_ndung;
    RecyclerView rcv_lienquan;
    ImageView img_quaylai, img_anhbao;

    CardTrangChu_Adapter cardTrangChu_adapter;
    List<NoiDungModel> noiDungModelList = new ArrayList<>();

    Elements data,data1;
    Document document;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docbao);

        linkbao = TruyenDuLieu.Truyen_Linkbao;

        khaibao();

        LinearLayoutManager llm1 = new LinearLayoutManager(this);
        llm1.setOrientation(LinearLayoutManager.VERTICAL);
        rcv_lienquan.setLayoutManager(llm1);

        Content content = new Content();
        content.execute();

        img_quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(docbao.this, trangchu.class);
                startActivity(intent);
            }
        });
    }

    private void khaibao() {
        txt_chude = findViewById(R.id.txt_chude_docbao);
        txt_tieude1 = findViewById(R.id.txt_tieude1_docbao);
        txt_tgian = findViewById(R.id.txt_tgian_docbao);
        txt_tieude2 = findViewById(R.id.txt_tieude2_docbao);
        txt_tacgia = findViewById(R.id.txt_tacgia_docbao);
        txt_ndung = findViewById(R.id.txt_card_ndungbao);

        rcv_lienquan = findViewById(R.id.rcv_tinlienquan_docbao);

        img_quaylai = findViewById(R.id.img_quaylai_docbao);
        img_anhbao = findViewById(R.id.img_anhbao_docbao);
    }

    private class Content extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            txt_chude.setText(chude);
            txt_tieude1.setText(tieude1);
            txt_tgian.setText(tgian);
            txt_tieude2.setText(tieude2);
            txt_tacgia.setText(tacgia);
            for (int i=0;i<ndungs.length;i++) {
                if (ndungs[i]!=null)
                txt_ndung.append(ndungs[i] + "\n");
            }
            Glide.with(img_anhbao).load(anhbao).into(img_anhbao);

            cardTrangChu_adapter = new CardTrangChu_Adapter((ArrayList<NoiDungModel>) noiDungModelList, new ClickItem() {
                @Override
                public void onClickItem(NoiDungModel noiDungModel) {
                    Intent intent = new Intent(docbao.this, docbao.class);
                    startActivity(intent);
                }
            });
            rcv_lienquan.setAdapter(cardTrangChu_adapter);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String url = linkbao;
                Log.e("url", url);
                document = Jsoup.connect(url).get();

                //đổ dữ liệu cho phần báo - xong
                data = document.select("div.content");
                chude = data.select("div.content-left>div.bread-crumbs>ul>li.fl").eq(0).select("a").text();
                tieude1 = data.select("div.content-detail>div.w980>h1.article-title").text();
                tgian = data.select("div.content-detail>div.w980>div.date-time").text();
                tieude2 = data.select("div.column-first-second>div.main-content-body>h2.sapo").text();
                tacgia = data.select("div.column-first-second>div.main-content-body>div.author").text();

                Elements find = data.select("div.column-first-second>div.main-content-body>div#main-detail-body");
                int size1 = find.select("p").size();
                for (int i=0; i<size1;i++) {
                    if ( find.select("div.VCSortableInPreviewMode[type='photo']").eq(i).select("img").attr("src") != ""){
                        anhbao = find.select("div.VCSortableInPreviewMode[type='photo']").eq(i).select("img").attr("src");
                    }

                    if (i<(size1-1))
                    {
                        if ( find.select("p").select("p").eq(i).text() != "" ){
                            ndungs[i] = find.select("p").eq(i).text();
                        }
                    }

                }

                //đổ dữ liệu cho rcv liên quan - chưa
                data1 = document.select("div.box-category3.canyoucare.box-top>ul.list-news>li"); //chỗ này bị sai
                int size = data1.size();
                if (size == 0)
                {
                    url = "https://tuoitre.vn/khi-nao-hoc-sinh-ha-noi-duoc-nghi-hoc-neu-ret-dam-20221219221837761.htm";
                    document = Jsoup.connect(url).get();
                    data1 = document.select("div.box-category3.canyoucare.box-top>ul.list-news>li");
                    size = data1.size();
                }

                for (int i=0; i<size;i++) {
                    String tieude = data1.eq(i).select("div.name-title").text(); //log đc size đúng thì log từng cái này coi đúng kh
                    String thoigian = "1 giờ";
                    String anhbao = data1.select("a.img188x117.pos-rlt").select("img.img188x117").eq(i).attr("src");
                    String linkbao2 = "https://tuoitre.vn"+data.select("div.name-title>a").eq(i).attr("href");
                    noiDungModelList.add(new NoiDungModel(tieude,thoigian,anhbao,linkbao2));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}