package com.verifone.androidbase.ui;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;

import com.verifone.androidbase.R;
import com.verifone.androidbase.ui.widgets.SlideDeleteListView;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class SlideListActivity extends Activity {
    private SlideDeleteListView lv_templates;
    private SlideListItemAdapter adapter;
    private ArrayList<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_list);
        initData();
        initView();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        dataList = new ArrayList<>();
        for (int i = 0; i < 10; i++)
           dataList.add("测试项:" + i);
    }

    /**
     * 初始化界面
     */
    private void initView() {
        lv_templates = (SlideDeleteListView) findViewById(R.id.lv_messages);
        lv_templates.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("TAG", "onItemClick....");
            }
        });
        adapter = new SlideListItemAdapter(this, dataList, lv_templates);
        lv_templates.setAdapter(adapter);
    }

    /**
     * 清空消息
     *
     * @param view
     */
    public void cleanAllMessage(View view) {
        if (null != adapter) {
            // 清除数据
            dataList.clear();
            // 隐藏清空信息按钮
            view.setVisibility(View.GONE);
            // 通知界面更新
            adapter.notifyDataSetChanged();
        }
    }
}
