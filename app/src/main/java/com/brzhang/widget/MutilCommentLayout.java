package com.brzhang.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.brzhang.testui.R;

import java.util.ArrayList;
import java.util.List;

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

/**
 * Created by brzhang on 16/12/29.
 * 多级评论专用组件
 */
public abstract class MutilCommentLayout<T> extends LinearLayout {

    private List<T> mDataList;
    private int mInitShow = 3;//最初最多展示多少条评论
    private int mMaxShow = 5;//最多显示几条评论
    private LinearLayout mCommentContainer;
    private TextView mCommentLeft;

    private enum COMMENT_WIDGET_STATE {
        STATE_INIT,
        STATE_CLICK_ONE_TIME
    }

    private COMMENT_WIDGET_STATE mState = COMMENT_WIDGET_STATE.STATE_INIT;

    public MutilCommentLayout(Context context) {
        this(context, null);
    }

    public MutilCommentLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MutilCommentLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        View.inflate(context, R.layout.mutil_commnet_layout, this);
        mCommentContainer = (LinearLayout) findViewById(R.id.comment_content_ll);
        mCommentLeft = (TextView) findViewById(R.id.comment_left_tv);
        mCommentLeft.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mState == COMMENT_WIDGET_STATE.STATE_INIT) {
                    mState = COMMENT_WIDGET_STATE.STATE_CLICK_ONE_TIME;
                    //展示数据就是了
                    populateDataShow();
                } else if (mState == COMMENT_WIDGET_STATE.STATE_CLICK_ONE_TIME) {
                    if (mCommentSeeAllListener != null) {
                        //再次点击，进入更下一级页面
                        mCommentSeeAllListener.onClick();
                    }
                }
            }
        });
    }

    public List<T> getmDataList() {
        return mDataList;
    }

    /**
     * 删除某个位置的数据
     *
     * @param index
     */
    public void removeData(int index) {
        if (index > 0 && index < mDataList.size()) {
            mDataList.remove(index);
        }
        // TODO: 16/12/29 这里更新数据展示
        populateDataShow();
    }

    private void addData(T data) {
        if (mDataList == null) {
            mDataList = new ArrayList<>();
            mDataList.add(data);
        } else {
            mDataList.add(data);
        }
        populateDataShow();
    }

    /**
     * 插入到指定的位置
     *
     * @param data
     * @param index 插入到按个位置，通常应该是最后一个位置
     * @deprecated
     */
    private void addData(T data, int index) {
        if (mDataList == null) {
            mDataList = new ArrayList<>();
            mDataList.add(data);
        } else {
            if (index > 0 && index <= mDataList.size()) {
                mDataList.add(index, data);
            }
        }
        populateDataShow();
    }

    /**
     * 塞评论数据
     *
     * @param mDataList
     */
    public void setmDataList(List<T> mDataList) {
        this.mDataList = mDataList;
        populateDataShow();
    }

    /**
     * 展示评论数据
     */
    private void populateDataShow() {
        updateVisiably();
        if (getVisibility() == GONE) {//如果没有数据了，啥都不用处理
            return;
        }
        //还剩下多少数据需要展示
        int dataNeedToShow = 0, dataLeftToShow = 0;
        if (mState == COMMENT_WIDGET_STATE.STATE_INIT) {
            dataLeftToShow = mDataList.size() - mInitShow;
            if (dataLeftToShow > 0) {
                dataNeedToShow = mInitShow;//只显示初始条数
            } else {
                dataNeedToShow = getCurrentDataSize();//需要全部显示
            }
        } else if (mState == COMMENT_WIDGET_STATE.STATE_CLICK_ONE_TIME) {
            dataLeftToShow = mDataList.size() - mMaxShow;
            if (dataLeftToShow > 0) {
                dataNeedToShow = mMaxShow;//只显示初始条数
            } else {
                dataNeedToShow = getCurrentDataSize();//需要全部显示
            }
        }
        if (dataLeftToShow <= 0) {
            mCommentLeft.setVisibility(GONE);//并没有更多需要显示，所以直接隐藏这个按钮
        } else {
            mCommentLeft.setVisibility(VISIBLE);
            mCommentLeft.setText(getContext().getString(R.string.show_more_comment_tips, dataLeftToShow));
        }
        /**
         * 展示评论
         */
        showComment(dataNeedToShow);
    }

    /**
     * 子类写展示的逻辑吧，只需要提供给我展示的view
     *
     * @param datasize 添加多少数据
     */
    private void showComment(int datasize) {
        mCommentContainer.removeAllViews();
        for (int i = 0; i < datasize; i++) {
            View view = offerMeAView(mCommentContainer,i);
            if (view != null) {
                MarginLayoutParams layoutParams = (MarginLayoutParams) view.getLayoutParams();
                layoutParams.width = MATCH_PARENT;
                layoutParams.height = WRAP_CONTENT;
                view.setLayoutParams(layoutParams);
                mCommentContainer.addView(view);
            }
        }
    }

    /**
     * 根据我提供给你的位置，给我一个view好吗？
     *
     *
     * @param mCommentContainer
     * @param index
     * @return
     */
    abstract View offerMeAView(ViewGroup mCommentContainer, int index);

    private int getCurrentDataSize() {
        return mDataList == null ? 0 : mDataList.size();
    }

    /**
     * 控制整个控件的显示与隐藏
     */
    private void updateVisiably() {
        if (getCurrentDataSize() > 0) {
            setVisibility(VISIBLE);
        } else {
            setVisibility(GONE);
        }
    }

    private CommentSeeAllListener mCommentSeeAllListener;

    public void setmCommentSeeAllListener(CommentSeeAllListener mCommentSeeAllListener) {
        this.mCommentSeeAllListener = mCommentSeeAllListener;
    }

    /**
     * 查看全部点击事件，传递出去
     */
    public interface CommentSeeAllListener {
        void onClick();
    }

}
