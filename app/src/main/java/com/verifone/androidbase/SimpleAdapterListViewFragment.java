package com.verifone.androidbase;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.orhanobut.logger.AndroidLogAdapter;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
import java.util.HashMap;

public class SimpleAdapterListViewFragment extends Fragment implements AdapterView.OnItemClickListener {

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


    ArrayList<HashMap<String, Object>> getListData() {
        ArrayList<HashMap<String, Object>> arrayList = new ArrayList<>();
        for (int i = 0; i < strs.length; i++) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("LineIcon", R.mipmap.ic_launcher_round);
            hashMap.put("LineNumber", "The " + i + " Line");
            hashMap.put("LineContent", strs[i]);
            arrayList.add(hashMap);
        }

        return arrayList;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listview_simpleadapter, container, false);

        mListView = (ListView) view.findViewById(R.id.simpleadapter_listview);
        mListView.setOnItemClickListener(this);

        ArrayList<HashMap<String, Object>> arrayList = getListData();
        SimpleAdapter simpleAdapter = new SimpleAdapter(getActivity(), arrayList,
                R.layout.simple_adapter_list_item,
                new String[]{"LineIcon", "LineNumber", "LineContent"},
                new int[]{R.id.icon, R.id.title, R.id.content});

        mListView.setAdapter(simpleAdapter);
        return view;
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Log.d("TAG", "clicked: " + position);
    }
}
