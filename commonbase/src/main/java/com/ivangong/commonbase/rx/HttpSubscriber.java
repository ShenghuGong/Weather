package com.ivangong.commonbase.rx;

import android.util.Log;
import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by gongshenghu on 16/3/8.
 * Common base Subscriber for Http request.
 */
public class HttpSubscriber<T> extends BaseHttpSubscriber<T> {
  public static final String API_ERROR_TAG = "API_ERROR";
  public static final String HTTP_ERROR_TAG = "HTTP_ERROR";

  @Override public void onSucceed(T t) {

  }

  @Override public void onApiError(Throwable throwable) {
    //TODO common LOG
    Log.w(API_ERROR_TAG, "Error Message: " + throwable.toString() + " " + throwable.getMessage());
  }

  @Override public void onHttpError(HttpException httpException) {
    //TODO common LOG
    Log.w(HTTP_ERROR_TAG, httpException.code() + httpException.message());
  }
}
