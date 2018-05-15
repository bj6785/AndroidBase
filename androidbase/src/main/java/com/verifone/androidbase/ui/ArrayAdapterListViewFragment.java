package com.verifone.androidbase.ui;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.verifone.androidbase.R;

public class ArrayAdapterListViewFragment extends Fragment {
    private ListView mListView;
    private static final String[] strs = {
            "array_adapter_1",
            "array_adapter_2",
            "array_adapter_3",
            "array_adapter_4",
            "array_adapter_5",
            "array_adapter_6",
            "array_adapter_7",
            "array_adapter_8",
            "array_adapter_10",
            "array_adapter_11",
            "array_adapter_12",
            "array_adapter_13",
    };

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listview_arrayadapter, container, false);
        mListView = (ListView) view.findViewById(R.id.arrayadapter_listview);
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("TAG", "clicked: " + position);
            }
        });

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
                getActivity(), R.layout.array_adapter_list_item, strs);
        mListView.setAdapter(arrayAdapter);
        return view;
    }
}
