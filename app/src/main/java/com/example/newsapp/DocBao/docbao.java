package com.example.newsapp.DocBao;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
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
import com.example.newsapp.TruyenDuLieu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class docbao extends AppCompatActivity {
    String linkbao,chude,tieude1,tgian,tieude2,anhbao,ndung,tacgia;

    TextView txt_chude,txt_tieude1,txt_tgian,txt_tieude2,txt_ndung,txt_tacgia;
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

        Content content = new Content();
        content.execute();

    }

    private void khaibao() {
        txt_chude = findViewById(R.id.txt_chude_docbao);
        txt_tieude1 = findViewById(R.id.txt_tieude1_docbao);
        txt_tgian = findViewById(R.id.txt_tgian_docbao);
        txt_tieude2 = findViewById(R.id.txt_tieude2_docbao);
        txt_ndung = findViewById(R.id.txt_noidung_docbao);
        txt_tacgia = findViewById(R.id.txt_tacgia_docbao);

        rcv_lienquan = findViewById(R.id.rcv_tinlienquan_docbao);

        img_quaylai = findViewById(R.id.img_quaylai_docbao);
        img_anhbao = findViewById(R.id.img_anhbao_docbao);

        cardTrangChu_adapter = new CardTrangChu_Adapter((ArrayList<NoiDungModel>) noiDungModelList, new ClickItem() {
            @Override
            public void onClickItem(NoiDungModel noiDungModel) {
                Intent intent = new Intent(docbao.this, docbao.class);
                startActivity(intent);
            }
        });
        rcv_lienquan.setAdapter(cardTrangChu_adapter);
        cardTrangChu_adapter.notifyDataSetChanged();

        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this, RecyclerView.VERTICAL,false);
        rcv_lienquan.setLayoutManager(linearLayoutManager2);

        cardTrangChu_adapter.setCardTrangChu(getList_lienquan());
        rcv_lienquan.setAdapter(cardTrangChu_adapter);
    }

    private ArrayList<NoiDungModel> getList_lienquan() {
        ArrayList<NoiDungModel> noiDungModels = new ArrayList<>();
        noiDungModels.add(new NoiDungModel("Một số cây xăng tại Đà Nẵng hết xăng để bán","1 giờ trước","https://cdn.tuoitre.vn/thumb_w/586/2022/11/12/logo…thieu-hut-xang-dau-02-16682326665542088494253.jpg","https://tuoitre.vn/mot-so-cay-xang-tai-da-nang-het-xang-de-ban-20221112130302427.htm"));
        noiDungModels.add(new NoiDungModel("Điều hành giá xăng dầu nhìn từ hiện tượng 'cây xăng cục gạch'","1 giờ trước","https://cdn.tuoitre.vn/thumb_w/586/2022/11/12/logo…6681047203162114213926-1668223325566330952414.jpg","https://tuoitre.vn/dieu-hanh-gia-xang-dau-nhin-tu-hien-tuong-cay-xang-cuc-gach-20221112103543742.htm"));
        noiDungModels.add(new NoiDungModel("Một số cây xăng tại Đà Nẵng hết xăng để bán","1 giờ trước","https://cdn.tuoitre.vn/thumb_w/586/2022/11/12/logo…thieu-hut-xang-dau-02-16682326665542088494253.jpg","https://tuoitre.vn/mot-so-cay-xang-tai-da-nang-het-xang-de-ban-20221112130302427.htm"));
        return noiDungModels;
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
            txt_ndung.setText(ndung);
            txt_tacgia.setText(tacgia);
            Glide.with(img_anhbao).load(anhbao).into(img_anhbao);

        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String url = linkbao;
                Log.e("url", url);
                document = Jsoup.connect(url).get();
                data = document.select("div.content");
                chude = data.select("div.content-left>div.bread-crumbs>ul>li.fl").eq(0).select("a").text();
                tieude1 = data.select("div.content-detail>div.w980>h1.article-title").text();
                tgian = data.select("div.content-detail>div.w980>div.date-time").text();
                tieude2 = data.select("div.column-first-second>div.main-content-body>h2.sapo").text();
                anhbao = data.select("div.column-first-second>div.main-content-body>div.content").select("div.VCSortableInPreviewMode[type='photo']").select("img").attr("src");
                ndung = data.select("div.column-first-second>div.main-content-body>div.content>p").text();
                tacgia = data.select("div.column-first-second>div.main-content-body>div.author").text();


            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}