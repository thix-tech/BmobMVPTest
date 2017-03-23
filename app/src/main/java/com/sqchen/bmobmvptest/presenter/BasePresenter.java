package com.sqchen.bmobmvptest.presenter;

import java.lang.ref.WeakReference;

/**
 * Created by Administrator on 2017/3/23.
 */

public abstract class BasePresenter<T> {

    //当内存不足时，释放内存
    protected WeakReference<T> mViewRef;

    /**
     * bind view with presenter
     * @param view
     */
    public void attachView(T view) {
        mViewRef = new WeakReference<T>(view);
    }

    public void detachView() {
        if(mViewRef != null) {
            mViewRef.clear();
            mViewRef = null;
        }
    }

    protected T getView() {
        return mViewRef.get();
    }
}
