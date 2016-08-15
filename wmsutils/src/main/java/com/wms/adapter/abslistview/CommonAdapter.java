package com.wms.adapter.abslistview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.wms.adapter.CommonViewHolder;
import com.wms.utils.CommonUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wms1993 on 2015/12/23 0023.
 */
public abstract class CommonAdapter<T> extends BaseAdapter {
    protected Context mContext;
    protected List<T> mDatas = new ArrayList<>();
    protected LayoutInflater mInflater;
    private int layoutId;

    public CommonAdapter(Context context, List<T> datas, int layoutId) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        this.layoutId = layoutId;

        if (datas != null) {
            mDatas = datas;
        }
    }

    @Override
    public int getCount() {
        if (!CommonUtils.listIsEmpty(mDatas)) {
            return mDatas.size();
        }
        return 0;
    }

    @Override
    public T getItem(int position) {
        if (!CommonUtils.listIsEmpty(mDatas)) {
            return mDatas.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CommonViewHolder holder = CommonViewHolder.get(mContext, convertView, parent, layoutId, position);
        convert(holder, getItem(position));
        return holder.getConvertView();
    }

    public void setDatas(List<T> datas) {
        if (datas == null) {
            datas = new ArrayList<>();
        }
        this.mDatas = datas;
        notifyDataSetChanged();
    }

    public abstract void convert(CommonViewHolder holder, T t);

}
