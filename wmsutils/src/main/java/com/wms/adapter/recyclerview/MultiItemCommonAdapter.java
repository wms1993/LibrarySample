package com.wms.adapter.recyclerview;

import android.content.Context;
import android.view.ViewGroup;

import com.wms.adapter.CommonViewHolder;

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
    public int getItemViewType(int position) {
        if (mMultiItemTypeSupport != null)
            return mMultiItemTypeSupport.getItemViewType(position, mDatas.get(position));
        return super.getItemViewType(position);
    }

    @Override
    public CommonViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (mMultiItemTypeSupport == null) return super.onCreateViewHolder(parent, viewType);

        int layoutId = mMultiItemTypeSupport.getLayoutId(viewType);
        CommonViewHolder holder = CommonViewHolder.get(mContext, null, parent, layoutId, -1);
        setListener(parent, holder, viewType);
        return holder;
    }

    public void setDatas(List<T> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }
}
