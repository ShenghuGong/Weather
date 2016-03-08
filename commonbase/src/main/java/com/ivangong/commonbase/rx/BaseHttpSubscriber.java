package com.ivangong.commonbase.rx;

import retrofit2.adapter.rxjava.HttpException;
import rx.Subscriber;

/**
 * Created by gongshenghu on 16/3/8.
 */
public abstract class BaseHttpSubscriber<T> extends Subscriber<T> {
  @Override public void onCompleted() {
    onEnd();
  }

  @Override public void onError(Throwable e) {
    if (e instanceof HttpException) {
      onHttpError((HttpException) e);
    } else {
      onApiError(e);
    }
    onEnd();
  }

  @Override public void onNext(T t) {
    onSucceed(t);
  }

  public abstract void onSucceed(T t);

  /**
   * The Subscriber onError or onCompleted
   */
  public void onEnd() {
  }

  public abstract void onApiError(Throwable throwable);

  public abstract void onHttpError(HttpException httpException);
}
