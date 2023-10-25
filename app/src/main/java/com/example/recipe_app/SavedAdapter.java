package com.example.recipe_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class SavedAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private List<Saved> savedList;

    public SavedAdapter(Context context, int layout, List<Saved> savedList) {
        this.context = context;
        this.layout = layout;
        this.savedList = savedList;
    }

    @Override
    public int getCount() {
        return savedList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {
        ImageView imgHinh;
        TextView txtTen;
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
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        Saved saved = savedList.get(position);
        holder.imgHinh.setImageResource(saved.getHinh());
        holder.txtTen.setText(saved.getTen());

        return view;
    }
}
