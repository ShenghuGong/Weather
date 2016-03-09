package com.ivangong.weather.MVPDemo.domain;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by gongshenghu on 16/3/4.
 */
public interface IGetWeatherBiz {
  String getWeather();

  Observable<ResponseBody> requestWithRetrofitRx(String cityid, String key);
}
