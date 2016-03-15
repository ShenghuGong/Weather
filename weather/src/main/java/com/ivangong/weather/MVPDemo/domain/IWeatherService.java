package com.ivangong.weather.MVPDemo.domain;

import com.ivangong.weather.config.ServerConfig;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by gongshenghu on 16/3/7.
 */
public interface IWeatherService {
  String CITY_ID = "cityid";
  String USER_KEY = "key";

  @GET(ServerConfig.WEATHER_PATH) Observable<ResponseBody> getWeather(@Query(CITY_ID) String cityid,
      @Query(USER_KEY) String key);
}
