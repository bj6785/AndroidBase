package com.verifone.androidbase.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.verifone.androidbase.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by KaiL1 on 2017/9/20.
 */

public class ExpandListViewActivity extends Activity {

    private ExpandableListView mExpandableListView;
    private ExpandAdapter mExpandAdapter;

    private ArrayList<HashMap<String, Object>> groupList;
    private HashMap<String, Object> groupItem;
    private ArrayList<String> childList;

    private void prepareDate() {
        groupList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            groupItem = new HashMap<>();
            String groupName = String.format("项目组%d: ", i);
            childList = new ArrayList<>();
            groupItem.put("groupTitle", groupName);
            groupItem.put("childList", childList);
            groupList.add(groupItem);
            for (int j = 0; j < 10; j++) {
                String childName = String.format("项目组%d-%d: ", i, j);
                childList.add(childName);
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand_listview);

        prepareDate();
        mExpandableListView = (ExpandableListView) findViewById(R.id.expandable_listview);
        mExpandAdapter = new ExpandAdapter(this, groupList);
        mExpandableListView.setAdapter(mExpandAdapter);
        mExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Toast.makeText(ExpandListViewActivity.this,
                        "第"+ groupPosition + "组" + "第" + childPosition + "个", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }
}
