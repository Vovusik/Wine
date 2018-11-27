package com.andrukhiv.mynavigationdrawer.tabs;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andrukhiv.mynavigationdrawer.RecyclerViewAdapter;
import com.andrukhiv.mynavigationdrawer.database.DbAdapter;
import com.andrukhiv.mynavigationdrawer.models.VarietiesModel;
import com.andrukhiv.mynavigationdrawer.VarietiesDetailsActivity;
import com.andrukhiv.mynavigationdrawer.R;

import java.util.ArrayList;
import java.util.Objects;

public class AllGrapesFragment extends Fragment {

    // Требуемый пустой публичный конструктор
    public AllGrapesFragment() {
    }

    DbAdapter mDbHelper;

    protected static final String TAG = "AllGrapesFragment";

    RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private ArrayList<VarietiesModel> grapes;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view;
        view = inflater.inflate(R.layout.fragment_tabs, container, false);

        mDbHelper = DbAdapter.getInstance(Objects.requireNonNull(getActivity()).getApplicationContext());

        // Щоб картки відображалися в табличному виді, використовуємо об'єкт GridLayoutManager.
        // Кількість колонок в сітці залежить від розміру і орієнтації екрана
        int numColumns = getResources().getInteger(R.integer.search_results_columns);
        mRecyclerView = view.findViewById(R.id.my_recycler_view);
        mRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getActivity(), numColumns);
        mRecyclerView.setLayoutManager(mLayoutManager);
        grapes = mDbHelper.getGrapes();

        // Передати масиви адаптера.
        mAdapter = new RecyclerViewAdapter(grapes);
        mRecyclerView.setAdapter(mAdapter);

        return mRecyclerView;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((RecyclerViewAdapter) mAdapter).setOnItemClickListener(new RecyclerViewAdapter
                .MyClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                Log.i(TAG, " Clicked on Item " + grapes.get(position));
                Intent intent = new Intent(getActivity().getApplicationContext(), VarietiesDetailsActivity.class);
                intent.putExtra(VarietiesDetailsActivity.EXTRA_GRAPES_ID, grapes.get(position));
                startActivity(intent);
            }
        });
    }
}