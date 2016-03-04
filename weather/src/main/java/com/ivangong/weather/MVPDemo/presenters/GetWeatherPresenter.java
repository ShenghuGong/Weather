package com.ivangong.weather.MVPDemo.presenters;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.ivangong.weather.MVPDemo.IGetWeatherView;
import com.ivangong.weather.MVPDemo.domain.GetWeatherBiz;
import com.ivangong.weather.MVPDemo.domain.IGetWeatherBiz;

/**
 * Created by gongshenghu on 16/3/3.
 * TODO base Presenter
 */
public class GetWeatherPresenter {
  private IGetWeatherView mWeatherView;
  private IGetWeatherBiz mGetWeatherBiz;
  private Handler mHandler;
  private static final int WEATHER_RESULT = 1;

  public GetWeatherPresenter(IGetWeatherView view) {
    /**
     * TODO 对Activity有强引用,如果Presenter里面的异步线程还没有结束
     * 而Activity已经关闭的话,会有空指针或内存泄露的风险?
     */
    mWeatherView = view;
    mGetWeatherBiz = new GetWeatherBiz();
    mHandler = new Handler(Looper.getMainLooper()) {
      @Override public void handleMessage(Message msg) {
        super.handleMessage(msg);
        switch (msg.arg1) {
          case WEATHER_RESULT:
            showResult((String) msg.obj);
            break;
        }
      }
    };
  }

  public void getWeather() {
    new Thread() {
      @Override public void run() {
        super.run();
        //TODO 把结果怎么Post到主线程,Base方法?
        Message message = Message.obtain();
        message.arg1 = WEATHER_RESULT;
        message.obj = mGetWeatherBiz.getWeather();
        mHandler.sendMessage(message);
      }
    }.start();
  }

  private void showResult(String result) {
    mWeatherView.showWeatherResult(result);
  }
}

