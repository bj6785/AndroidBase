package com.verifone.androidbase;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ArrayAdapterListViewFragment extends Fragment {
    private ListView mListView;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.right_fragment_listview_arrayadapter,container, false);
        mListView = (ListView) view.findViewById(R.id.arrayadapter_listview);
        return view;
    }
}
