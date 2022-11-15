package com.example.newsapp.GiaVang;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsapp.R;
import com.example.newsapp.Thoitiet.Thoitiet1;
import com.example.newsapp.Thoitiet.Thoitiet1_Adapter;

import java.util.List;

public class GiaVang_Adapter extends RecyclerView.Adapter<GiaVang_Adapter.GiaVangViewHolder>{

    private List<GiaVangModel> giaVangModels;

    public GiaVang_Adapter(List<GiaVangModel> giaVangModels) {
        this.giaVangModels = giaVangModels;
    }

    public void setMgiavang(List<GiaVangModel> mgiavang) {
        this.giaVangModels = mgiavang;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GiaVang_Adapter.GiaVangViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_giavang_loai,parent,false);
        return new GiaVang_Adapter.GiaVangViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GiaVang_Adapter.GiaVangViewHolder holder, int position) {
        GiaVangModel giaVangModel = giaVangModels.get(position);
        if (giaVangModel == null) {
            return;
        }

        Glide.with(holder.imgtenhang).load(giaVangModel.getHang()).into(holder.imgtenhang);
        holder.txttenhang.setText(giaVangModel.getTenhang());
        holder.txtgiatienmua.setText(giaVangModel.getGiatienmua());
        holder.txttangmua.setText(giaVangModel.getTangmua());
        holder.txtgiatienban.setText(giaVangModel.getGiatienban());
        holder.txttangban.setText(giaVangModel.getTangban());
    }

    @Override
    public int getItemCount() {
        if (giaVangModels != null) {
            return giaVangModels.size();
        }
        return 0;
    }

    public class GiaVangViewHolder extends RecyclerView.ViewHolder{

        private TextView txttenhang,txtgiatienmua,txttangmua,txtgiatienban,txttangban;
        private ImageView imgtenhang;

        public GiaVangViewHolder(@NonNull View itemView) {
            super(itemView);

            imgtenhang = itemView.findViewById(R.id.txt_hang_cardgiavang);
            txttenhang = itemView.findViewById(R.id.txt_ten_cardgiavang);

            txtgiatienmua = itemView.findViewById(R.id.txt_giatienmua_cardgiavang);
            txttangmua = itemView.findViewById(R.id.txt_tangmua_cardgiavang);

            txtgiatienban = itemView.findViewById(R.id.txt_giatienban_cardgiavang);
            txttangban = itemView.findViewById(R.id.txt_tangban_cardgiavang);
        }
    }
}
