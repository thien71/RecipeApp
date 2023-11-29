package com.example.recipe_app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe_app.R;
import com.example.recipe_app.model.BuocThucHien;

import java.util.List;

public class BuocThucHienAdapter extends RecyclerView.Adapter<BuocThucHienAdapter.ViewHolder> {
    private List<BuocThucHien> buocThucHienList;

    // Constructor
    public BuocThucHienAdapter(List<BuocThucHien> buocThucHienList) {
        this.buocThucHienList = buocThucHienList;
    }

    // ViewHolder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTenBuoc;

        public ViewHolder(View itemView) {
            super(itemView);
            txtTenBuoc = itemView.findViewById(R.id.txtBuocThucHien);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_buoc_thuc_hien, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BuocThucHien buocThucHien = buocThucHienList.get(position);
        holder.txtTenBuoc.setText(buocThucHien.getMaBuoc() + ". " + buocThucHien.getMoTa());
    }

    @Override
    public int getItemCount() {
        return buocThucHienList.size();
    }
}

