package com.example.newsapp.DocBao;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsapp.Card.CardTrangChu_Adapter;
import com.example.newsapp.Card.ClickItem;
import com.example.newsapp.Card.NoiDungModel;
import com.example.newsapp.R;
import com.example.newsapp.TrangChu.trangchu;
import com.example.newsapp.TruyenDuLieu;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener;
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

public class docbaovideo extends AppCompatActivity {

    String linkbao,tieude,tgian,ndung,tacgia;
    TextView tv_tieude,tv_tgian,tv_ndung,tv_tacgia;
    RecyclerView rcv_lienquan;
    ImageView quaylai,img_tim, img_binhluan;

    CardTrangChu_Adapter cardTrangChu_adapter;
    List<NoiDungModel> noiDungModelList = new ArrayList<>();

    Elements data,data1;
    Document document;

    private YouTubePlayerView youTubePlayerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docbaovideo);

        linkbao = TruyenDuLieu.Truyen_Linkbao;

        khaibao();

        Content content = new Content();
        content.execute();

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        rcv_lienquan.setLayoutManager(llm);

        quaylai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(docbaovideo.this, trangchu.class);
                startActivity(intent);
            }
        });

        youTubePlayerView.addYouTubePlayerListener(new AbstractYouTubePlayerListener() {
            @Override
            public void onReady(@NonNull YouTubePlayer youTubePlayer) {
                String videoId = "pg3EISkqTII";
                youTubePlayer.cueVideo(videoId, 0);
            }
        });

        img_tim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(docbaovideo.this,"Chức năng này đang được phát triển",Toast.LENGTH_SHORT).show();
            }
        });

        img_binhluan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(docbaovideo.this,"Chức năng này đang được phát triển",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void khaibao() {
        youTubePlayerView = findViewById(R.id.img_anhbao_docbaovideo);
        tv_tieude = findViewById(R.id.txt_tieude_docbaovideo);
        tv_tgian = findViewById(R.id.txt_tgian_docbaovideo);
        tv_ndung = findViewById(R.id.txt_tieude2_docbaovideo);
        tv_tacgia = findViewById(R.id.txt_tacgia_docbaovideo);
        rcv_lienquan = findViewById(R.id.rcv_tinlienquan_docbaovideo);
        quaylai = findViewById(R.id.img_quaylai_docbaovideo);
        img_tim = findViewById(R.id.img_like_docbaovideo);
        img_binhluan = findViewById(R.id.img_binhluan_docbaovideo);
    }

    private class Content extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            tv_tieude.setText(tieude);
            tv_tgian.setText(tgian);
            tv_ndung.setText(ndung);
            tv_tacgia.setText(tacgia);

            cardTrangChu_adapter = new CardTrangChu_Adapter((ArrayList<NoiDungModel>) noiDungModelList, new ClickItem() {
                @Override
                public void onClickItem(NoiDungModel noiDungModel) {
                    Intent intent = new Intent(docbaovideo.this, docbaovideo.class);
                    startActivity(intent);
                }
            });
            rcv_lienquan.setAdapter(cardTrangChu_adapter);
            cardTrangChu_adapter.notifyDataSetChanged();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String url = linkbao;
                Log.e("url", url);
                document = Jsoup.connect(url).get();

                //đổ dữ liệu cho phần báo - xong
                data = document.select("div#autonextNoiBat");
                tieude = data.select("div.description-video>h2>a.name-video").text();
                tgian = data.select("div.description-video>span.list-category>a.time-ago").text();
                ndung = data.select("div.description-video>p.sapo-video").text();
                tacgia = data.select("p.authorvideo").text();

                //đổ dữ liệu cho rcv liên quan - chưa
                data1 = document.select("div.list-video.box-related.box-lastest>ul.list-video>li");
                int size = data1.size();
                for (int i=0; i<size;i++) {
                    String tieude = data1.select("h3>a.name-video-list").eq(i).text();
                    String thoigian = data1.select("b.time-ago").eq(i).text();
                    String anhbao = data1.select("a.item>img").eq(i).attr("src");
                    linkbao = "https://tv.tuoitre.vn/" + data1.select("a.item").eq(i).attr("href");
                    noiDungModelList.add(new NoiDungModel(tieude,thoigian,anhbao,linkbao));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }
    }
}