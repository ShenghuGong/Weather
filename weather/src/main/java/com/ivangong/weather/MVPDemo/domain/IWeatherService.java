package com.ivangong.weather.MVPDemo.domain;

import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by gongshenghu on 16/3/7.
 */
public interface IWeatherService {
  // TODO 配置文件
  String WEATHER_URL = "https://api.heweather.com/";
  String WEATHER_PATH = "x3/weather";
  String CITY_ID = "cityid";
  String USER_KEY = "key";

  @GET(WEATHER_PATH) Observable<ResponseBody> getWeather(@Query(CITY_ID) String cityid,
      @Query(USER_KEY) String key);
}
