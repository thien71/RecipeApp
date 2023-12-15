package com.example.recipe_app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.recipe_app.R;

import de.hdodenhof.circleimageview.CircleImageView;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder> {
    private Context context;
    private int[] arrayHinh;
    private String[] arrayTen;

    public SearchAdapter(Context context, int[] arrayHinh, String[] arrayTen) {
        this.context = context;
        this.arrayHinh = arrayHinh;
        this.arrayTen = arrayTen;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_search, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imgHinh.setImageResource(arrayHinh[position]);
        holder.txtTen.setText(arrayTen[position]);
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public int getItemCount() {
        return arrayTen.length;
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        CircleImageView imgHinh;
        TextView txtTen;

        public ViewHolder(View itemView) {
            super(itemView);
            imgHinh = itemView.findViewById(R.id.cimgHinhSearch);
            txtTen = itemView.findViewById(R.id.txtTenSearch);
        }
    }
}
