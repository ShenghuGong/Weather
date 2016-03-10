package com.ivangong.weather.MVPDemo.domain;

import okhttp3.ResponseBody;
import rx.Observable;

/**
 * Created by gongshenghu on 16/3/4.
 */
public interface IGetWeatherBiz {
  /**
   * 获得天气信息详情
   *
   * @param cityid 城市ID
   * @param key 认证Key
   * @return 天气信息
   */
  Observable<ResponseBody> getWeather(String cityid, String key);
}
