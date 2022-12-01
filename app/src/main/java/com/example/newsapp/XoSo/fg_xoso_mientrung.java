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
import com.example.newsapp.R;
import com.example.newsapp.TruyenDuLieu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class fg_xoso_mientrung extends Fragment {

    View view;
    Elements data;
    Document document;
    TextView[] tv_xoso = new TextView[38];
    String[] str_xoso = new String[38];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fg_xoso_mientrung, container, false);

        khaibao();

        Content content = new Content();
        content.execute();

        return view;
    }

    private void khaibao() {
        tv_xoso[0] = view.findViewById(R.id.trung_g8);
        tv_xoso[1] = view.findViewById(R.id.trung_g7);
        tv_xoso[2] = view.findViewById(R.id.trung_g6_1);
        tv_xoso[3] = view.findViewById(R.id.trung_g6_2);
        tv_xoso[4] = view.findViewById(R.id.trung_g6_3);
        tv_xoso[5] = view.findViewById(R.id.trung_g5);
        tv_xoso[6] = view.findViewById(R.id.trung_g4_1);
        tv_xoso[7] = view.findViewById(R.id.trung_g4_2);
        tv_xoso[8] = view.findViewById(R.id.trung_g4_3);
        tv_xoso[9] = view.findViewById(R.id.trung_g4_4);
        tv_xoso[10] = view.findViewById(R.id.trung_g4_5);
        tv_xoso[11] = view.findViewById(R.id.trung_g4_6);
        tv_xoso[12] = view.findViewById(R.id.trung_g4_7);
        tv_xoso[13] = view.findViewById(R.id.trung_g3_1);
        tv_xoso[14] = view.findViewById(R.id.trung_g3_2);
        tv_xoso[15] = view.findViewById(R.id.trung_g2);
        tv_xoso[16] = view.findViewById(R.id.trung_g1);
        tv_xoso[17] = view.findViewById(R.id.trung_g0);
        tv_xoso[18] = view.findViewById(R.id.trung_1g8);
        tv_xoso[19] = view.findViewById(R.id.trung_1g7);
        tv_xoso[20] = view.findViewById(R.id.trung_1g6_1);
        tv_xoso[21] = view.findViewById(R.id.trung_1g6_2);
        tv_xoso[22] = view.findViewById(R.id.trung_1g6_3);
        tv_xoso[23] = view.findViewById(R.id.trung_1g5);
        tv_xoso[24] = view.findViewById(R.id.trung_1g4_1);
        tv_xoso[25] = view.findViewById(R.id.trung_1g4_2);
        tv_xoso[26] = view.findViewById(R.id.trung_1g4_3);
        tv_xoso[27] = view.findViewById(R.id.trung_1g4_4);
        tv_xoso[28] = view.findViewById(R.id.trung_1g4_5);
        tv_xoso[29] = view.findViewById(R.id.trung_1g4_6);
        tv_xoso[30] = view.findViewById(R.id.trung_1g4_7);
        tv_xoso[31] = view.findViewById(R.id.trung_1g3_1);
        tv_xoso[32] = view.findViewById(R.id.trung_1g3_2);
        tv_xoso[33] = view.findViewById(R.id.trung_1g2);
        tv_xoso[34] = view.findViewById(R.id.trung_1g1);
        tv_xoso[35] = view.findViewById(R.id.trung_1g0);
        tv_xoso[36] = view.findViewById(R.id.trung_tp1);
        tv_xoso[37] = view.findViewById(R.id.trung_tp2);
    }

    private class Content extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            for (int i=0;i<38;i++) {
                tv_xoso[i].setText(str_xoso[i]);
            }
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String url = "https://www.minhngoc.net.vn/ket-qua-xo-so/mien-trung/"+ TruyenDuLieu.Truyen_NgayXoso+".html";
                Log.e("url",url);
                document = Jsoup.connect(url).get();
                data = document.select("div.box_kqxs").eq(0);
                str_xoso[36]= data.select("td.tinh").eq(0).text();
                str_xoso[37]= data.select("td.tinh").eq(1).text();
                int q = 0;
                for (int i = 1; i<=2;i++){
                    str_xoso[0+q]= data.select("td.giai8").eq(i).text();
                    str_xoso[1+q]= data.select("td.giai7").eq(i).text();
                    str_xoso[2+q]= data.select("td.giai6").eq(i).select("div").eq(0).text();
                    str_xoso[3+q]= data.select("td.giai6").eq(i).select("div").eq(1).text();
                    str_xoso[4+q]= data.select("td.giai6").eq(i).select("div").eq(2).text();
                    str_xoso[5+q]= data.select("td.giai5").eq(i).text();
                    str_xoso[6+q]= data.select("td.giai4").eq(i).select("div").eq(0).text();
                    str_xoso[7+q]= data.select("td.giai4").eq(i).select("div").eq(1).text();
                    str_xoso[8+q]= data.select("td.giai4").eq(i).select("div").eq(2).text();
                    str_xoso[9+q]= data.select("td.giai4").eq(i).select("div").eq(3).text();
                    str_xoso[10+q]= data.select("td.giai4").eq(i).select("div").eq(4).text();
                    str_xoso[11+q]= data.select("td.giai4").eq(i).select("div").eq(5).text();
                    str_xoso[12+q]= data.select("td.giai4").eq(i).select("div").eq(6).text();
                    str_xoso[13+q]= data.select("td.giai3").eq(i).select("div").eq(0).text();
                    str_xoso[14+q]= data.select("td.giai3").eq(i).select("div").eq(1).text();
                    str_xoso[15+q]= data.select("td.giai2").eq(i).text();
                    str_xoso[16+q]= data.select("td.giai1").eq(i).text();
                    str_xoso[17+q]= data.select("td.giaidb").eq(i).text();
                    q+=18;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}