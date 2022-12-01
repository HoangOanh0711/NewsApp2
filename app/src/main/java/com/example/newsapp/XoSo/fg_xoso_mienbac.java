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
    TextView[] tv_xoso = new TextView[27];
    String[] str_xoso = new String[27];
    
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
        tv_xoso[0] = view.findViewById(R.id.bac_g7_1);
        tv_xoso[1] = view.findViewById(R.id.bac_g7_2);
        tv_xoso[2] = view.findViewById(R.id.bac_g7_3);
        tv_xoso[3] = view.findViewById(R.id.bac_g7_4);
        tv_xoso[4] = view.findViewById(R.id.bac_g6_1);
        tv_xoso[5] = view.findViewById(R.id.bac_g6_2);
        tv_xoso[6] = view.findViewById(R.id.bac_g6_3);
        tv_xoso[7] = view.findViewById(R.id.bac_g5_1);
        tv_xoso[8] = view.findViewById(R.id.bac_g5_2);
        tv_xoso[9] = view.findViewById(R.id.bac_g5_3);
        tv_xoso[10] = view.findViewById(R.id.bac_g5_4);
        tv_xoso[11] = view.findViewById(R.id.bac_g5_5);
        tv_xoso[12] = view.findViewById(R.id.bac_g5_6);
        tv_xoso[13] = view.findViewById(R.id.bac_g4_1);
        tv_xoso[14] = view.findViewById(R.id.bac_g4_2);
        tv_xoso[15] = view.findViewById(R.id.bac_g4_3);
        tv_xoso[16] = view.findViewById(R.id.bac_g4_4);
        tv_xoso[17] = view.findViewById(R.id.bac_g3_1);
        tv_xoso[18] = view.findViewById(R.id.bac_g3_2);
        tv_xoso[19] = view.findViewById(R.id.bac_g3_3);
        tv_xoso[20] = view.findViewById(R.id.bac_g3_4);
        tv_xoso[21] = view.findViewById(R.id.bac_g3_5);
        tv_xoso[22] = view.findViewById(R.id.bac_g3_6);
        tv_xoso[23] = view.findViewById(R.id.bac_g2_1);
        tv_xoso[24] = view.findViewById(R.id.bac_g2_2);
        tv_xoso[25] = view.findViewById(R.id.bac_g1);
        tv_xoso[26] = view.findViewById(R.id.bac_g0);
    }

    private class Content extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            for (int i=0;i<27;i++) {
                tv_xoso[i].setText(str_xoso[i]);
            }
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String url = "https://www.minhngoc.net.vn/ket-qua-xo-so/mien-bac/"+TruyenDuLieu.Truyen_NgayXoso+".html";
                Log.e("url",url);
                document = Jsoup.connect(url).get();
                data = document.select("div.box_kqxs").eq(0);

                str_xoso[0]= data.select("td.giai7").select("div").eq(0).text();
                str_xoso[1]= data.select("td.giai7").select("div").eq(1).text();
                str_xoso[2]= data.select("td.giai7").select("div").eq(2).text();
                str_xoso[3]= data.select("td.giai7").select("div").eq(3).text();
                str_xoso[4]= data.select("td.giai6").select("div").eq(0).text();
                str_xoso[5]= data.select("td.giai6").select("div").eq(1).text();
                str_xoso[6]= data.select("td.giai6").select("div").eq(2).text();
                str_xoso[7]= data.select("td.giai5").select("div").eq(0).text();
                str_xoso[8]= data.select("td.giai5").select("div").eq(1).text();
                str_xoso[9]= data.select("td.giai5").select("div").eq(2).text();
                str_xoso[10]= data.select("td.giai5").select("div").eq(3).text();
                str_xoso[11]= data.select("td.giai5").select("div").eq(4).text();
                str_xoso[12]= data.select("td.giai5").select("div").eq(5).text();
                str_xoso[13]= data.select("td.giai4").select("div").eq(0).text();
                str_xoso[14]= data.select("td.giai4").select("div").eq(1).text();
                str_xoso[15]= data.select("td.giai4").select("div").eq(2).text();
                str_xoso[16]= data.select("td.giai4").select("div").eq(3).text();
                str_xoso[17]= data.select("td.giai3").select("div").eq(0).text();
                str_xoso[18]= data.select("td.giai3").select("div").eq(1).text();
                str_xoso[19]= data.select("td.giai3").select("div").eq(2).text();
                str_xoso[20]= data.select("td.giai3").select("div").eq(3).text();
                str_xoso[21]= data.select("td.giai3").select("div").eq(4).text();
                str_xoso[22]= data.select("td.giai3").select("div").eq(5).text();
                str_xoso[23]= data.select("td.giai2").select("div").eq(0).text();
                str_xoso[24]= data.select("td.giai2").select("div").eq(1).text();
                str_xoso[25]= data.select("td.giai1").eq(0).text();
                str_xoso[26]= data.select("td.giaidb").eq(0).text();

            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }
    }
}