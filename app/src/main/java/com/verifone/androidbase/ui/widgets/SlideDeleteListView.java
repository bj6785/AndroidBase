package com.verifone.androidbase.ui.widgets;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;

public class SlideDeleteListView extends ListView {
    // 屏幕宽度
    private Integer mSreeenWidth;
    // 按下点的X值
    private Integer mDownX;
    // 按下点的Y值
    private Integer mDownY;
    // 移动x距离
    private Integer mMoveX;
    // 移动y距离
    private Integer mMoveY;


    // 删除按钮View的宽度
    private Integer mBtnDelWidth;
    // 删除按钮是否在显示，item正常显示状态下，默认是fasle
    private Boolean isBtnDelShow = false;
    // Item View的子View（Item正常显示的那个子View）布局参数
    private LinearLayout.LayoutParams params;
    // ListView 某item布局对象
    private ViewGroup itemViewGroup;

    public SlideDeleteListView(Context context, AttributeSet attrs) {
        // 使用下面的构造方法
        this(context, attrs, 0);
    }

    /**
     * 构造方法，实例化入口，初始化相关数据或实例
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public SlideDeleteListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        // 窗口管理器
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        // 新建显示度量尺
        DisplayMetrics metrics = new DisplayMetrics();
        // 对度量尺进行包装,附参
        wm.getDefaultDisplay().getMetrics(metrics);
        // 初始化屏幕宽度参数
        mSreeenWidth = metrics.widthPixels;
    }

    /**
     * 手势操作
     *
     * @param ev
     * @return
     */
    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:// 按压
                onActionDowm(ev);
            case MotionEvent.ACTION_MOVE:// 移动
                onActionMove(ev);
            case MotionEvent.ACTION_UP:// 释放
                onActionUp(ev);
        }

        if (Math.abs(mMoveX) > 10 || Math.abs(mMoveY) > 10)
            return true;
        else
            return super.onTouchEvent(ev);
    }

    /**
     * 手指按下逻辑
     */
    private void onActionDowm(MotionEvent e) {
        if (isBtnDelShow) {
            resetItemView();
        }
        mDownX = (int) e.getX();
        mDownY = (int) e.getY();
        // 获得被按下位置的item
        Integer currentPosition = pointToPosition(mDownX, mDownY);
        if (-1 == currentPosition) {
            return;
        }
        itemViewGroup = (ViewGroup) getChildAt(currentPosition - getFirstVisiblePosition());
        // 获得删除按钮的宽度，删除按钮属于第二个子View(上述布局中能看得出来),position为1
        mBtnDelWidth = itemViewGroup.getChildAt(1).getLayoutParams().width;
        /* 将第一个子View也就是我们常见的Item显示的View的宽固定为屏幕同宽度 */
        params = (LinearLayout.LayoutParams) itemViewGroup.getChildAt(0).getLayoutParams();
        params.width = mSreeenWidth;
        itemViewGroup.getChildAt(0).setLayoutParams(params);
    }

    /**
     * 手指移动逻辑
     */
    private void onActionMove(MotionEvent e) {
        int nowX = (int) e.getX();
        int nowY = (int) e.getY();
        // 判断是否为偏向左右的滑动
        if (Math.abs(nowX - mDownX) > Math.abs(nowY - mDownY)) {
            // 左右滑动请求消费该事件，防止上下滑动以及被ScrollView嵌套的手势冲突
            requestDisallowInterceptTouchEvent(true);
            // 判断是否为向左滑动
            if (nowX < mDownX) {
                int srollX = mDownX - nowX;
                // 判断左滑距离是否超过删除按钮宽
                if (srollX >= mBtnDelWidth) {
                    srollX = mBtnDelWidth;
                }
                params.leftMargin = -srollX;
                itemViewGroup.getChildAt(0).setLayoutParams(params);
            }
        }
    }

    /**
     * 手指释放逻辑
     */
    private void onActionUp(MotionEvent e) {
        mMoveX = (int) e.getX() - mDownX;
        mMoveY = (int) e.getY() - mDownY;
        Log.i("TAG", "movex:" + mMoveX);
        Log.i("TAG", "movey:" + mMoveY);

        Log.i("TAG", "abs movex:" + Math.abs(mMoveX));
        Log.i("TAG", "abs movey:" + Math.abs(mMoveY));

        // 判断手指释放后，删除按钮是否已显示超过其宽度的一半
        if (-params.leftMargin >= mBtnDelWidth / 2) {
            params.leftMargin = -mBtnDelWidth;
            isBtnDelShow = true;
        } else {
            // 恢复滑动前的视图状态
            resetItemView();
        }
        itemViewGroup.getChildAt(0).setLayoutParams(params);

//        if (Math.abs(mMoveX) > 10 || Math.abs(mMoveY) > 10)
//            return true;
//        return false;
    }

    /**
     * 重置itemView，恢复原显示状态
     */
    public void resetItemView() {
        params.leftMargin = 0;
        itemViewGroup.getChildAt(0).setLayoutParams(params);
        isBtnDelShow = false;
    }

    /**
     * 重写该方法是用来应对ScrollView嵌套显示不全的问题
     *
     * @param widthMeasureSpec
     * @param heightMeasureSpec
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // 获得ScrollView或其子类对象，这里视情况而定，可能不需要只需要一个getParent()，或多次，视自己的布局层次而定
        Object object = getParent().getParent();
        if (object instanceof ScrollView) {// 是ScrollView或其子类
            /**
             * 解决与ScrollView的布局冲突，让ListView完全显示
             */
            int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
            super.onMeasure(widthMeasureSpec, expandSpec);
        } else {
            // 没有ScrollView嵌套，正常super的方法
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }

    /**
     * 提供外界调用返回当前删除按钮是否显示的状态
     *
     * @return
     */
    public boolean isBtnDelShow() {
        return this.isBtnDelShow;
    }
}
