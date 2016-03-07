package com.ivangong.weather.MVPDemo.domain;

import android.util.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by gongshenghu on 16/3/3.
 */
public class GetWeatherBiz implements IGetWeatherBiz {
  /**
   * TODO 配置文件
   */
  String httpUrl = "https://api.heweather.com/x3/weather";
  String httpArg = "cityid=CN101210101&key=5a7f3bf3b3814b20ba6fa59388c3d0ae";

  public String getWeather() {
    //return request(httpUrl, httpArg);
    return requestWithRetrofit();
  }

  /**
   * @param httpUrl :请求接口
   * @param httpArg :参数
   * @return 返回结果
   * TODO 公共网络封装
   */
  public String request(String httpUrl, String httpArg) {
    BufferedReader reader = null;
    String result = null;
    StringBuffer sbf = new StringBuffer();
    httpUrl = httpUrl + "?" + httpArg;

    try {
      URL url = new URL(httpUrl);
      Log.i("URL", httpUrl);
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();
      connection.setRequestMethod("GET");
      // 填入apikey到HTTP header
      //connection.setRequestProperty("apikey", "您自己的apikey");
      connection.connect();
      InputStream is = connection.getInputStream();
      reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
      String strRead = null;
      while ((strRead = reader.readLine()) != null) {
        sbf.append(strRead);
        sbf.append("\r\n");
      }
      reader.close();
      result = sbf.toString();
      //TODO 公共Log封装
      Log.i("URL", result);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return result;
  }

  private String requestWithRetrofit() {
    Retrofit retrofit = new Retrofit.Builder().baseUrl(IWeatherService.WEATHER_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build();
    IWeatherService weatherService = retrofit.create(IWeatherService.class);
    Call<ResponseBody> call =
        weatherService.getWeather("CN101210101", "5a7f3bf3b3814b20ba6fa59388c3d0ae");
    try {
      Response<ResponseBody> response = call.execute();
      return response.body().string();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return "";
  }
}
