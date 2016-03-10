package com.ivangong.weather.rx;

import com.ivangong.commonbase.rx.HttpSubscriber;
import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by gongshenghu on 16/3/8.
 * 和业务相关的Http Subscriber,可以处理共同的与业务逻辑相关的Error等
 */
public class UiHttpSubscriber<T> extends HttpSubscriber<T> {

  @Override public void onApiError(Throwable throwable) {
    super.onApiError(throwable);
  }

  @Override public void onHttpError(HttpException httpException) {
    super.onHttpError(httpException);
    // common Http Code:404,500,503
  }
}
