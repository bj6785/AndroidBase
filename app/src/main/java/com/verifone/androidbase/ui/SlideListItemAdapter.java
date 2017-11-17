package com.verifone.androidbase.ui;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.verifone.androidbase.R;
import com.verifone.androidbase.ui.widgets.SlideDeleteListView;

import java.util.ArrayList;

public class SlideListItemAdapter extends BaseAdapter {
    // 列表数据集合
    // 布局转换器
    private LayoutInflater inflater;
    // 当前ListView对象
    private SlideDeleteListView lv_templates;
    private Context mContext;
    private ArrayList<String> items;

    /**
     * 构造方法传入上下文，列表数据
     *
     * @param context 上下文
     * @param items   列表数据
     */
    public SlideListItemAdapter(Context context, ArrayList<String> items,  SlideDeleteListView lv_messages) {
        this.lv_templates = lv_messages;
        this.inflater = LayoutInflater.from(context);
        this.mContext = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return (null == items) ? 0 : items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ViewHolder holder = null;
        if (null == convertView) { // 当前缓存布局为空
            // 布局转换工具新载入布局
            convertView = inflater.inflate(R.layout.slide_list_item, null);
            // 初始化布局，并传入view设置布局参数
            holder = new ViewHolder(convertView);
            // 缓存布局
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        setView(holder, position);
        return convertView;
    }

    /**
     * 设置数据
     *
     * @param holder
     * @param position
     */
    private void setView(final ViewHolder holder, final Integer position) {

        String template_name = items.get(position);
        holder.tv_msg_content.setText(template_name);
		holder.tv_msg_time.setText("0:00:00");

        holder.tv_btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                items.remove((int)position);
                lv_templates.resetItemView();
                notifyDataSetChanged();
            }
        });

/*        holder.template_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ArrayList<LinkedTreeMap<String, Object>> selectedItemArrayList = DeviceApplication.getSelectedmodule();
                selectedItemArrayList.clear();

                String valse = items.get_value(position);
                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<LinkedTreeMap<String, Object>>>() {
                }.getType();

                ArrayList<LinkedTreeMap<String, Object>> selectedItemList = gson.fromJson(valse, type);
                for (int i = 0; i < selectedItemList.size(); i++)
                    selectedItemArrayList.add(selectedItemList.get(i));
                if (Activity.class.isInstance(mContext)) {
                    Activity activity = (Activity) mContext;
                    activity.finish();
                }
            }
        });*/
    }

    /**
     * 静态化ViewHolder类使内存只有一个ViewHolder实例，用于运行调优
     */
    static class ViewHolder {
        TextView tv_msg_content, tv_msg_time, tv_btn_delete;
        RelativeLayout template_layout;

        ViewHolder(View view) {
            tv_msg_content = (TextView) view.findViewById(R.id.template_name);
            tv_msg_time = (TextView) view.findViewById(R.id.template_time);
            tv_btn_delete = (TextView) view.findViewById(R.id.tv_btn_delete);
            template_layout = (RelativeLayout) view.findViewById(R.id.template_layout);
        }
    }
}