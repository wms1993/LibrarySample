package com.wms.adapter.abslistview;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.wms.adapter.CommonViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wms1993 on 2015/12/23 0023.
 */
public abstract class MultiItemCommonAdapter<T> extends CommonAdapter<T> {

    protected MultiItemTypeSupport<T> mMultiItemTypeSupport;

    public MultiItemCommonAdapter(Context context, List<T> datas,
                                  MultiItemTypeSupport<T> multiItemTypeSupport) {
        super(context, datas, -1);
        mMultiItemTypeSupport = multiItemTypeSupport;
        if (mMultiItemTypeSupport == null)
            throw new IllegalArgumentException("the mMultiItemTypeSupport can not be null.");
    }

    @Override
    public int getViewTypeCount() {
        if (mMultiItemTypeSupport != null)
            return mMultiItemTypeSupport.getViewTypeCount();
        return super.getViewTypeCount();
    }

    @Override
    public int getItemViewType(int position) {
        if (mMultiItemTypeSupport != null)
            return mMultiItemTypeSupport.getItemViewType(position,
                    mDatas.get(position));
        return super.getItemViewType(position);

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (mMultiItemTypeSupport == null)
            return super.getView(position, convertView, parent);

        int layoutId = mMultiItemTypeSupport.getLayoutId(position,
                getItem(position));
        CommonViewHolder viewHolder = CommonViewHolder.get(mContext, convertView, parent,
                layoutId, position);
        convert(viewHolder, getItem(position));
        return viewHolder.getConvertView();
    }

    public void setDatas(List<T> datas) {
        if (datas == null) {
            datas = new ArrayList<>();
        }
        this.mDatas = datas;
        notifyDataSetChanged();
    }
}
