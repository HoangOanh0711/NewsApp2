package com.example.newsapp.DocBao;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;

import java.util.ArrayList;

public class CardNdungBao_adapter extends RecyclerView.Adapter<CardNdungBao_adapter.ViewHolder>{
    ArrayList<CardNdungBao_model> ndungBao_modelArrayList;
    Context context;

    public CardNdungBao_adapter(ArrayList<CardNdungBao_model> ndungBao_modelArrayList, Context context) {
        this.ndungBao_modelArrayList = ndungBao_modelArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_ndungbao, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CardNdungBao_model cardNdungBao_model = ndungBao_modelArrayList.get(position);
        holder.txt_ndung.setText(cardNdungBao_model.getNdung());
    }

    @Override
    public int getItemCount() {
        return ndungBao_modelArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txt_ndung;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_ndung = itemView.findViewById(R.id.txt_card_ndungbao);
        }

        @Override
        public void onClick(View view) {

        }
    }
}
