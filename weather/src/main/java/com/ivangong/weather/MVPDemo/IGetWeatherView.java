package com.ivangong.weather.MVPDemo;

/**
 * Created by gongshenghu on 16/3/3.
 */
public interface IGetWeatherView {
  /**
   * TODO 要不要写在接口里?
   * 是否是Presenter需要调用View的方法采用写在接口里,而view本身的操作不用写在接口里?
   */
  void getWeather();

  void showWeatherResult(String result);
}
