package com.wms.adapter.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.wms.adapter.CommonViewHolder;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wms1993 on 2015/12/23 0023.
 */
public abstract class CommonAdapter<T> extends RecyclerView.Adapter<CommonViewHolder> {
    protected Context mContext;
    protected int mLayoutId;
    protected List<T> mDatas = new ArrayList<>();
    protected LayoutInflater mInflater;

    private OnItemClickListener mOnItemClickListener;

    public CommonAdapter(Context context, List<T> datas, int layoutId) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;

        if (datas != null) {
            mDatas = datas;
        }
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.mOnItemClickListener = onItemClickListener;
    }

    @Override
    public CommonViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        CommonViewHolder viewHolder = CommonViewHolder.get(mContext, null, parent, mLayoutId, -1);
        setListener(parent, viewHolder, viewType);
        return viewHolder;
    }

    protected int getPosition(RecyclerView.ViewHolder viewHolder) {
        return viewHolder.getAdapterPosition();
    }

    protected boolean isEnabled(int viewType) {
        return true;
    }


    protected void setListener(final ViewGroup parent, final CommonViewHolder viewHolder, int viewType) {
        if (!isEnabled(viewType)) return;
        viewHolder.getConvertView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = getPosition(viewHolder);
                    mOnItemClickListener.onItemClick(parent, v, mDatas.get(position), position);
                }
            }
        });


        viewHolder.getConvertView().setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (mOnItemClickListener != null) {
                    int position = getPosition(viewHolder);
                    return mOnItemClickListener.onItemLongClick(parent, v, mDatas.get(position), position);
                }
                return false;
            }
        });
    }

    @Override
    public void onBindViewHolder(CommonViewHolder holder, int position) {
        holder.updatePosition(position);
        convert(holder, mDatas.get(position));
    }

    public abstract void convert(CommonViewHolder holder, T t);

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public void setDatas(List<T> datas) {
        this.mDatas = datas;
        notifyDataSetChanged();
    }
}
