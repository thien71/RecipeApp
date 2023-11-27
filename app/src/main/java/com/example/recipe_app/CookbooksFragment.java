package com.example.recipe_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class CookbooksFragment extends Fragment {

    private ListView lvCookbooks;
    ArrayList<Cookbooks> arrayCookbooks;
    CookbooksAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cookbooks, container, false);

        lvCookbooks = (ListView) view.findViewById(R.id.lvCookbooks);
        arrayCookbooks = new ArrayList<>();

        arrayCookbooks.add(new Cookbooks((R.drawable.img_mon_ham), "Mùa đông", 1));
        arrayCookbooks.add(new Cookbooks((R.drawable.img_carrot_nuong), "Bữa tối", 2));
        arrayCookbooks.add(new Cookbooks((R.drawable.img_sup_ga), "Bữa trưa", 3));
        arrayCookbooks.add(new Cookbooks((R.drawable.img_dau_trang_ham_thit), "Bữa sáng", 1));
        arrayCookbooks.add(new Cookbooks((R.drawable.img_dau_trang_ham_thit), "Bữa xế", 1));


        adapter = new CookbooksAdapter(getActivity(), R.layout.item_cookbooks, arrayCookbooks);
        lvCookbooks.setAdapter(adapter);

        lvCookbooks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Cookbooks selectCookbooks = adapter.getItem(position);
                Intent intent = new Intent(getActivity(), DetailActivity.class);

                intent.putExtra("idTenMon", selectCookbooks.getTen());
                intent.putExtra("idHinh", selectCookbooks.getHinh());

                startActivity(intent);
            }
        });

        return view;
    }
}