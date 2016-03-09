package com.ivangong.weather;

import com.ivangong.commonbase.BaseApp;
import com.ivangong.commonbase.net.RetrofitWrapper;
import com.ivangong.weather.MVPDemo.domain.IWeatherService;

/**
 * Created by gongshenghu on 16/3/1.
 */
public class WeatherApp extends BaseApp {
  @Override public void onCreate() {
    super.onCreate();
    RetrofitWrapper.getInstance().init(IWeatherService.WEATHER_URL);
  }
}
