package com.verifone.androidbase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by KaiL1 on 2017/9/20.
 */

public class ExpandAdapter extends BaseExpandableListAdapter {
    private ArrayList<HashMap<String, Object>> groupList;
    private Context mContext;

    public ExpandAdapter(Context context, ArrayList<HashMap<String, Object>> grouplist) {
        this.mContext = context;
        this.groupList = grouplist;
    }

    @Override
    public int getGroupCount() {
        return groupList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return ((ArrayList) groupList.get(groupPosition).get("childList")).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return groupList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return ((ArrayList) groupList.get(groupPosition).get("childList")).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        View view = convertView;
        GroupHolder holder;
        if (view == null) {
            holder = new GroupHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.expand_list_group, null);
            holder.groupName = (TextView) view.findViewById(R.id.tv_group_name);
            holder.arrow = (ImageView) view.findViewById(R.id.iv_arrow);
            view.setTag(holder);
        } else {
            holder = (GroupHolder) view.getTag();
        }

        //判断是否已经打开列表
        if (isExpanded) {
            holder.arrow.setBackgroundResource(R.mipmap.down_arrow_circle);
        } else {
            holder.arrow.setBackgroundResource(R.mipmap.right_arrow_circle);
        }

        String group_str = (String) groupList.get(groupPosition).get("groupTitle");
        holder.groupName.setText(group_str);
        return view;
    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        View view = convertView;
        ChildHolder holder;
        if (view == null) {
            holder = new ChildHolder();
            view = LayoutInflater.from(mContext).inflate(R.layout.expand_list_child, null);
            holder.childName = (TextView) view.findViewById(R.id.tv_child_name);
            view.setTag(holder);
        } else {
            holder = (ChildHolder) view.getTag();
        }

        ArrayList<String> childList = (ArrayList<String>) groupList.get(groupPosition).get("childList");
        String child_str = childList.get(childPosition);
        holder.childName.setText(child_str);
        return view;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }

    class GroupHolder {
        public TextView groupName;
        public ImageView arrow;
    }

    class ChildHolder {
        public TextView childName;
    }
}
