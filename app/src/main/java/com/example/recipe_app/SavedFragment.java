package com.example.recipe_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class SavedFragment extends Fragment {

    private GridView grvSaved;
    ArrayList<Saved> arraySaved;
    SavedAdapter adapter;

    private LinearLayout search_bar_Saved;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved, container, false);

        grvSaved = (GridView) view.findViewById(R.id.grvSaved);
        search_bar_Saved = (LinearLayout) view.findViewById(R.id.search_bar_Saved);

        arraySaved = new ArrayList<>();

        arraySaved.add(new Saved((R.drawable.img_mon_ham), "Món hầm cho mùa đông"));
        arraySaved.add(new Saved((R.drawable.img_carrot_nuong), "Cà rốt xông khói"));
        arraySaved.add(new Saved((R.drawable.img_sup_ga), "Súp gà"));
        arraySaved.add(new Saved((R.drawable.img_dau_trang_ham_thit), "Đậu trắng hầm thịt"));
        arraySaved.add(new Saved((R.drawable.img_dau_trang_ham_thit), "Đậu trắng hầm thịt"));


        adapter = new SavedAdapter(getActivity(), R.layout.item_saved, arraySaved);
        grvSaved.setAdapter(adapter);

        grvSaved.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getActivity(), arraySaved.get(position).getTen(), Toast.LENGTH_SHORT).show();
            }
        });

        // search

        search_bar_Saved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), SearchActivity.class);
                startActivity(intent);
            }
        });

        return view;
    }
}