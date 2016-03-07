package com.ivangong.commonbase.presenters;

import android.support.annotation.NonNull;
import java.lang.ref.WeakReference;

/**
 * Created by gongshenghu on 16/3/4.
 * Base class that implements the IPresenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
public abstract class BasePresenter<V extends BaseMVPView> implements IPresenter<V> {
  private WeakReference<V> mViewReference;

  public BasePresenter(@NonNull V mvpView) {
    attachView(mvpView);
  }

  @Override public void attachView(V mvpView) {
    mViewReference = new WeakReference<>(mvpView);
  }

  @Override public V getView() {
    //TODO 如果为空 怎么处理比较好?
    //TODO 或者让Presenter 拥有生命周期,在销毁的时候回收资源,结束异步线程?
    //TODO Presenter 怎么很好的解决Activity重启(旋转屏幕)的问题? 包括数据缓存和线程等?
    return mViewReference.get();
  }

  protected boolean isViewAttached() {
    //TODO check view is attached
    return true;
  }
}
