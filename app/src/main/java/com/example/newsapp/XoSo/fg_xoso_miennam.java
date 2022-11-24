package com.example.newsapp.XoSo;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.newsapp.R;
import com.example.newsapp.TruyenDuLieu;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class fg_xoso_miennam extends Fragment {

    View view;
    Elements data;
    Document document;
    TextView[] tv_xoso = new TextView[57];
    String[] str_xoso = new String[57];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_fg_xoso_miennam, container, false);

        khaibao();

        Content content = new Content();
        content.execute();

        return view;
    }

    private void khaibao() {
        tv_xoso[0] = view.findViewById(R.id.ng81);
        tv_xoso[1] = view.findViewById(R.id.ng71);
        tv_xoso[2] = view.findViewById(R.id.ng61);
        tv_xoso[3] = view.findViewById(R.id.ng61_2);
        tv_xoso[4] = view.findViewById(R.id.ng61_3);
        tv_xoso[5] = view.findViewById(R.id.ng51);
        tv_xoso[6] = view.findViewById(R.id.ng41_1);
        tv_xoso[7] = view.findViewById(R.id.ng41_2);
        tv_xoso[8] = view.findViewById(R.id.ng41_3);
        tv_xoso[9] = view.findViewById(R.id.ng41_4);
        tv_xoso[10] = view.findViewById(R.id.ng41_5);
        tv_xoso[11] = view.findViewById(R.id.ng41_6);
        tv_xoso[12] = view.findViewById(R.id.ng41_7);
        tv_xoso[13] = view.findViewById(R.id.ng31);
        tv_xoso[14] = view.findViewById(R.id.ng31_1);
        tv_xoso[15] = view.findViewById(R.id.ng21);
        tv_xoso[16] = view.findViewById(R.id.ng11);
        tv_xoso[17] = view.findViewById(R.id.ng01);

        tv_xoso[18] = view.findViewById(R.id.ng82);
        tv_xoso[19] = view.findViewById(R.id.ng72);
        tv_xoso[20] = view.findViewById(R.id.ng62);
        tv_xoso[21] = view.findViewById(R.id.ng62_2);
        tv_xoso[22] = view.findViewById(R.id.ng62_3);
        tv_xoso[23] = view.findViewById(R.id.ng52);
        tv_xoso[24] = view.findViewById(R.id.ng42_1);
        tv_xoso[25] = view.findViewById(R.id.ng42_2);
        tv_xoso[26] = view.findViewById(R.id.ng42_3);
        tv_xoso[27] = view.findViewById(R.id.ng42_4);
        tv_xoso[28] = view.findViewById(R.id.ng42_5);
        tv_xoso[29] = view.findViewById(R.id.ng42_6);
        tv_xoso[30] = view.findViewById(R.id.ng42_7);
        tv_xoso[31] = view.findViewById(R.id.ng32);
        tv_xoso[32] = view.findViewById(R.id.ng32_1);
        tv_xoso[33] = view.findViewById(R.id.ng22);
        tv_xoso[34] = view.findViewById(R.id.ng12);
        tv_xoso[35] = view.findViewById(R.id.ng02);

        tv_xoso[36] = view.findViewById(R.id.ng83);
        tv_xoso[37] = view.findViewById(R.id.ng73);
        tv_xoso[38] = view.findViewById(R.id.ng63);
        tv_xoso[39] = view.findViewById(R.id.ng63_2);
        tv_xoso[40] = view.findViewById(R.id.ng63_3);
        tv_xoso[41] = view.findViewById(R.id.ng53);
        tv_xoso[42] = view.findViewById(R.id.ng43_1);
        tv_xoso[43] = view.findViewById(R.id.ng43_2);
        tv_xoso[44] = view.findViewById(R.id.ng43_3);
        tv_xoso[45] = view.findViewById(R.id.ng43_4);
        tv_xoso[46] = view.findViewById(R.id.ng43_5);
        tv_xoso[47] = view.findViewById(R.id.ng43_6);
        tv_xoso[48] = view.findViewById(R.id.ng43_7);
        tv_xoso[49] = view.findViewById(R.id.ng33);
        tv_xoso[50] = view.findViewById(R.id.ng33_1);
        tv_xoso[51] = view.findViewById(R.id.ng23);
        tv_xoso[52] = view.findViewById(R.id.ng13);
        tv_xoso[53] = view.findViewById(R.id.ng03);

        tv_xoso[54] = view.findViewById(R.id.ntp1);
        tv_xoso[55] = view.findViewById(R.id.ntp2);
        tv_xoso[56] = view.findViewById(R.id.ntp3);
    }

    private class Content extends AsyncTask<Void,Void,Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Void unused) {
            super.onPostExecute(unused);
            for (int i=0;i<57;i++) {
                tv_xoso[i].setText(str_xoso[i]);
            }
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String url = "https://www.minhngoc.net.vn/ket-qua-xo-so/mien-nam/"+ TruyenDuLieu.Truyen_NgayXoso+".html";
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