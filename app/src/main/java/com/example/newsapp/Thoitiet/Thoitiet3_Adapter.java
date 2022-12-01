package com.example.newsapp.Thoitiet;

import static com.google.api.AnnotationsProto.http;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.example.newsapp.R;
import com.example.newsapp.TruyenDuLieu;
import com.google.gson.JsonObject;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class Thoitiet3_Adapter extends RecyclerView.Adapter<Thoitiet3_Adapter.Thoitiet3ViewHolder>{
    private List<Thoitiet3> mthoitiet3;
    private ClickItem clickItem;
    private Context context;

    public Thoitiet3_Adapter(List<Thoitiet3> mthoitiet3, ClickItem clickItem, Context context) {
        this.mthoitiet3 = mthoitiet3;
        this.clickItem = clickItem;
        this.context = context;
    }

    public void setMthoitiet3(List<Thoitiet3> mthoitiet3) {
        this.mthoitiet3 = mthoitiet3;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public Thoitiet3ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_thoitiet_thanhpho,parent,false);
        return new Thoitiet3ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Thoitiet3ViewHolder holder, int position) {
        Thoitiet3 thoitiet3 = mthoitiet3.get(position);
        if (thoitiet3 == null) {
            return;
        }

        DecimalFormat df = new DecimalFormat("#.#");
        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = "https://api.openweathermap.org/data/2.5/weather?q="+thoitiet3.getThanhpho()+"&appid=830d983248574a22491e0e61de20ba7d";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonObject = new JSONObject(response);
                    holder.txt_tp.setText(thoitiet3.getTenthanhpho());

                    JSONArray jsonArrayWeather = jsonObject.getJSONArray("weather");
                    JSONObject jsonObjectWeather = jsonArrayWeather.getJSONObject(0);
                    String status = jsonObjectWeather.getString("main");
                    String icon = jsonObjectWeather.getString("icon");

                    Glide.with(holder.imgThoitiet3).load("http://openweathermap.org/img/wn/"+icon+"@4x.png").into(holder.imgThoitiet3);
                    holder.txt_tinhtrang3.setText(status);

                    JSONObject jsonObjectMain = jsonObject.getJSONObject("main");
                    double temp = jsonObjectMain.getDouble("temp") - 273.15;
                    holder.txt_nhietdo3.setText(df.format(temp) + "ºC");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Error at sign in : ",error.getMessage());
            }
        });
        requestQueue.add(stringRequest);

        holder.cardview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clickItem.onClickItem(thoitiet3);
                TruyenDuLieu.Truyen_TenTP = thoitiet3.getTenthanhpho();
                TruyenDuLieu.Truyen_TP = thoitiet3.getThanhpho();
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mthoitiet3 != null) {
            return mthoitiet3.size();
        }
        return 0;
    }

    public class Thoitiet3ViewHolder extends RecyclerView.ViewHolder{

        private ImageView imgThoitiet3;
        private TextView txt_tp, txt_tinhtrang3, txt_nhietdo3;
        private CardView cardview;

        public Thoitiet3ViewHolder(@NonNull View itemView) {
            super(itemView);

            imgThoitiet3 = itemView.findViewById(R.id.img_thoitiet_thoitiettp);
            txt_tp = itemView.findViewById(R.id.txt_thanhpho_thoitiettp);
            txt_tinhtrang3 = itemView.findViewById(R.id.txt_tinhtrang_thoitiettp);
            txt_nhietdo3 = itemView.findViewById(R.id.txt_nhietdo_thoitiettp);
            cardview = itemView.findViewById(R.id.layout_card_thoitiet3);
        }
    }
}
