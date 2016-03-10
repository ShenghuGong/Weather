package com.ivangong.weather.MVPDemo.domain;

import com.ivangong.commonbase.net.RetrofitWrapper;
import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by gongshenghu on 16/3/3.
 */
public class GetWeatherBiz implements IGetWeatherBiz {
  IWeatherService weatherService;

  public GetWeatherBiz() {
    weatherService = RetrofitWrapper.getInstance().getRetrofit().create(IWeatherService.class);
  }

  @Override public Observable<ResponseBody> getWeather(String cityid, String key) {
    return weatherService.getWeather(cityid, key);
  }
}
