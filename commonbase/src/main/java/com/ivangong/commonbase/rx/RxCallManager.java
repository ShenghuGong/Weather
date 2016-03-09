package com.ivangong.commonbase.rx;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by gongshenghu on 16/3/9.
 * 统一管理Rx Call
 */
public interface RxCallManager {
  <T> void manageRxCall(Observable<T> observable, Subscriber<T> subscribe);

  /**
   * RxCallManager
   */
  class RxCallManagerImpl implements RxCallManager {

    private CompositeSubscription mCompositeSubscription = new CompositeSubscription();

    public <T> void manageRxCall(Observable<T> observable, final Subscriber<T> subscribe) {
      mCompositeSubscription.add(observable.subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread())
          .subscribe(subscribe));
    }

    public void unsubscribeAll() {
      mCompositeSubscription.unsubscribe();
    }
  }
}