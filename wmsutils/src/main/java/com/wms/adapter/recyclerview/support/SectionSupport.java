package com.wms.adapter.recyclerview.support;

/**
 * Created by wms1993 on 2015/12/23 0023.
 */
public interface SectionSupport<T> {
    int sectionHeaderLayoutId();

    int sectionTitleTextViewId();

    String getTitle(T t);
}
