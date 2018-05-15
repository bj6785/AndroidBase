package com.verifone.androidbase.ui;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.verifone.androidbase.R;

import java.util.ArrayList;
import java.util.HashMap;

/*
    注意一点:ListView的item中如果有诸如ImageButton，Button，CheckBox等子控件
    (也可以说是Button或者Checkable的子类控件)，此时这些子控件会将焦点获取到，
    所以常常当点击item时变化的是子控件，item本身的点击没有响应。使用descendantFocusability来解决这个问题！

    android:descendantFocusability
    Defines the relationship between the ViewGroup and its descendants when looking for a View to take focus.
    Must be one of the following constant values.

    Constant	Value	Description
    afterDescendants	1	The ViewGroup will get focus only if none of its descendants want it.
    beforeDescendants	0	The ViewGroup will get focus before any of its descendants.
    blocksDescendants	2	The ViewGroup will block its descendants from receiving focus.
 */

public class BaseAdapterListviewFragment extends Fragment implements AdapterView.OnItemClickListener {
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
        mListView.setOnItemClickListener(this);

        MyBaseAdapter myBaseAdapter = new MyBaseAdapter(getActivity());
        mListView.setAdapter(myBaseAdapter);
        return view;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("TAG", "你点击了ListView条目" + position);//在LogCat中输出信息
    }

    class MyBaseAdapter extends BaseAdapter {
        private LayoutInflater mLayoutInflater;
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
        public View getView(final int position, View convertView, ViewGroup parent) {

            ViewHolder viewHolder;
            if (convertView == null) {
                viewHolder = new ViewHolder();
                convertView = mLayoutInflater.inflate(R.layout.base_adapter_list_item, null);
                viewHolder.title = (TextView) convertView.findViewById(R.id.title);
                viewHolder.content = (TextView) convertView.findViewById(R.id.content);
                viewHolder.more_bt = (Button) convertView.findViewById(R.id.baseadapter_bt);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }

            viewHolder.title.setText(arraylist.get(position).get("LineNumber").toString());
            viewHolder.content.setText(arraylist.get(position).get("LineContent").toString());
            viewHolder.more_bt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d("TAG", "你点击了按钮" + position);
                }
            });
            return convertView;
        }
    }

    class ViewHolder {
        TextView title;
        TextView content;
        Button more_bt;
    }
}
