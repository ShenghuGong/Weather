package com.ivangong.weather.MVPDemo;

/**
 * Created by gongshenghu on 16/3/3.
 * Presenter需要调用View的方法采用写在接口里,而view本身的操作不用写在接口里
 */
public interface IGetWeatherView {

  void showWeatherResult(String result);
}
