package com.andrukhiv.mynavigationdrawer.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.andrukhiv.mynavigationdrawer.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public class FragmentSettingsBottomSheetDialog extends BottomSheetDialogFragment {

    String mString;

    public static FragmentSettingsBottomSheetDialog newInstance(String string) {
        FragmentSettingsBottomSheetDialog f = new FragmentSettingsBottomSheetDialog();
        Bundle args = new Bundle();
        args.putString("string", string);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setStyle(STYLE_NORMAL, R.style.SheetDialog);

        assert getArguments() != null;
        mString = getArguments().getString("string");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_settings_bottom_sheet, container, false);
        TextView tv = view.findViewById(R.id.text);

        TextView textView = view.findViewById(R.id.dismiss);
        textView.setOnClickListener(v -> {
            SettingsFragment.myBottomSheet.dismiss();
        });

        return view;
    }
}
