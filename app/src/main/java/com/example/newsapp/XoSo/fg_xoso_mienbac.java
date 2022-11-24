package com.example.newsapp.XoSo;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.airbnb.lottie.animation.content.Content;
import com.example.newsapp.Card.NoiDungModel;
import com.example.newsapp.R;
import com.example.newsapp.TruyenDuLieu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class fg_xoso_mienbac extends Fragment {

    View view;
    Elements data;
    Document document;
    TextView[] tv_xoso = new TextView[19];
    String[] str_xoso = new String[19];
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fg_xoso_mienbac, container, false);
        
        khaibao();

        Content content = new Content();
        content.execute();
        return view;
    }

    private void khaibao() {
        tv_xoso[0] = view.findViewById(R.id.bac_g8);
        tv_xoso[1] = view.findViewById(R.id.bac_g7);
        tv_xoso[2] = view.findViewById(R.id.bac_g6_1);
        tv_xoso[3] = view.findViewById(R.id.bac_g6_2);
        tv_xoso[4] = view.findViewById(R.id.bac_g6_3);
        tv_xoso[5] = view.findViewById(R.id.bac_g5);
        tv_xoso[6] = view.findViewById(R.id.bac_g4_1);
        tv_xoso[7] = view.findViewById(R.id.bac_g4_2);
        tv_xoso[8] = view.findViewById(R.id.bac_g4_3);
        tv_xoso[9] = view.findViewById(R.id.bac_g4_4);
        tv_xoso[10] = view.findViewById(R.id.bac_g4_5);
        tv_xoso[11] = view.findViewById(R.id.bac_g4_6);
        tv_xoso[12] = view.findViewById(R.id.bac_g4_7);
        tv_xoso[13] = view.findViewById(R.id.bac_g3_1);
        tv_xoso[14] = view.findViewById(R.id.bac_g3_2);
        tv_xoso[15] = view.findViewById(R.id.bac_g2);
        tv_xoso[16] = view.findViewById(R.id.bac_g1);
        tv_xoso[17] = view.findViewById(R.id.bac_g0);
        tv_xoso[18] = view.findViewById(R.id.bac_tp); //thành phố
    }

    private class Content extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            for (int i=0;i<19;i++) {
                tv_xoso[i].setText(str_xoso[i]);
            }
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String url = "https://www.minhngoc.net.vn/ket-qua-xo-so/mien-bac/"+TruyenDuLieu.Truyen_NgayXoso+".html";
                Log.e("url",url);
                document = Jsoup.connect(url).get();
                data = document.select("div.box-news-latest.isstream").select("li.news-item");
                int size = data.size();
                //str_xoso[0] = data.select("h3.title-news").select("a").text();
                for (int i=0; i<size;i++) {
                    str_xoso[8] = data.select("h3.title-news").eq(i).select("a").text();
                    str_xoso[9] = data.select("p.sapo").eq(i).text();
                    str_xoso[10] = data.select("a.img212x132.pos-rlt").eq(i).select("img").attr("src");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}