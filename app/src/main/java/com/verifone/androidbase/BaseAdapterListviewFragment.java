package com.verifone.androidbase;


import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class BaseAdapterListviewFragment extends Fragment {
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
    @Nullable
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listview_baseadapter, container, false);
        mListView = (ListView) view.findViewById(R.id.baseadapter_listview);

        MyBaseAdapter myBaseAdapter = new MyBaseAdapter(getActivity());
        mListView.setAdapter(myBaseAdapter);
        return view;
    }

    class MyBaseAdapter extends BaseAdapter {
        private LayoutInflater mLayoutInflater;
        private int index = 0;
        private ArrayList<HashMap<String, Object>> arraylist;

        public MyBaseAdapter(Context context) {
            mLayoutInflater = LayoutInflater.from(context);
            arraylist = getListData();
        }

        @Override
        public int getCount() {
            return getListData().size();
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder = null;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = mLayoutInflater.inflate(R.layout.base_adapter_list_item, null);
                viewHolder.title = (TextView) convertView.findViewById(R.id.title);
                viewHolder.content = (TextView) convertView.findViewById(R.id.content);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.title.setText(arraylist.get(position).get("LineNumber").toString());
            viewHolder.content.setText(arraylist.get(position).get("LineContent").toString());
            return convertView;
        }
    }

    class ViewHolder {
        TextView title;
        TextView content;
    }
}
