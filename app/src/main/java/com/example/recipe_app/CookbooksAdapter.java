package com.example.recipe_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CookbooksAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Cookbooks> cookbooksList;

    public CookbooksAdapter(Context context, int layout, List<Cookbooks> cookbooksList) {
        this.context = context;
        this.layout = layout;
        this.cookbooksList = cookbooksList;
    }

    @Override
    public int getCount() {
        return cookbooksList.size();
    }

    @Override
    public Cookbooks getItem(int position) {
        if (position >= 0 && position < cookbooksList.size()) {
            return cookbooksList.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        ImageView imgHinh;
        TextView txtTen, txtSoLuong;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;

        if(view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            view = inflater.inflate(layout, null);
            holder.imgHinh = (ImageView) view.findViewById(R.id.imgHinhAnh);
            holder.txtTen = (TextView) view.findViewById(R.id.txtTen);
            holder.txtSoLuong = (TextView) view.findViewById(R.id.txtSoLuong);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Cookbooks cookbooks = cookbooksList.get(position);
        holder.imgHinh.setImageResource(cookbooks.getHinh());
        holder.txtTen.setText(cookbooks.getTen());
        holder.txtSoLuong.setText(cookbooks.getSoLuong()+"");

        return view;
    }
}
