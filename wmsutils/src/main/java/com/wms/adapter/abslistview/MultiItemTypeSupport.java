package com.wms.adapter.abslistview;

/**
 * Created by wms1993 on 2015/12/23 0023.
 */
public interface MultiItemTypeSupport<T> {
    int getLayoutId(int position, T t);

    int getViewTypeCount();

    int getItemViewType(int position, T t);
}