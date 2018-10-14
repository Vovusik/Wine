package com.andrukhiv.mynavigationdrawer.TabFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andrukhiv.mynavigationdrawer.R;


public class SeedlessGrapesFragment extends Fragment {

    public SeedlessGrapesFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tab, container, false);

        ImageView imageView = rootView.findViewById(R.id.image);
        imageView.setImageResource(R.drawable.tab_icon_seedless_true);

        TextView textView = rootView.findViewById(R.id.tab);
        textView.setText("перелік киш-мишних сортів винограду");

        return rootView;
    }

}
