package com.example.newsapp.GiaVang;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;

import java.util.List;

public class GiaVang_Adapter extends RecyclerView.Adapter<GiaVang_Adapter.GiaVangViewHolder>{

    private List<GiaVangModel> giaVangModels;

    public GiaVang_Adapter(List<GiaVangModel> giaVangModels) {
        this.giaVangModels = giaVangModels;
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

        holder.txttenhang.setText(giaVangModel.getLoaivang());
        holder.txtgiatienmua.setText(giaVangModel.getGiamua());
        holder.txtgiatienban.setText(giaVangModel.getGiaban());
    }

    @Override
    public int getItemCount() {
        if (giaVangModels != null) {
            return giaVangModels.size();
        }
        return 0;
    }

    public class GiaVangViewHolder extends RecyclerView.ViewHolder{

        private TextView txttenhang,txtgiatienmua,txtgiatienban;

        public GiaVangViewHolder(@NonNull View itemView) {
            super(itemView);

            txttenhang = itemView.findViewById(R.id.txt_ten_cardgiavang);
            txtgiatienmua = itemView.findViewById(R.id.txt_giatienmua_cardgiavang);
            txtgiatienban = itemView.findViewById(R.id.txt_giatienban_cardgiavang);
        }
    }
}
