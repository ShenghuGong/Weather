package com.ivangong.weather.MVPDemo.presenters;

import android.support.annotation.NonNull;
import com.ivangong.commonbase.presenters.BasePresenter;
import com.ivangong.weather.MVPDemo.IGetWeatherView;
import com.ivangong.weather.MVPDemo.domain.GetWeatherBiz;
import com.ivangong.weather.MVPDemo.domain.IGetWeatherBiz;
import com.ivangong.weather.rx.UiHttpSubscriber;
import java.io.IOException;
import okhttp3.ResponseBody;

/**
 * Created by gongshenghu on 16/3/3.
 */
public class GetWeatherPresenter extends BasePresenter<IGetWeatherView> {
  private IGetWeatherBiz mGetWeatherBiz;

  public GetWeatherPresenter(@NonNull IGetWeatherView view) {
    super(view);
    mGetWeatherBiz = new GetWeatherBiz();
  }

  public void getWeather() {
    manageRxCall(mGetWeatherBiz.getWeather("CN101210101", "5a7f3bf3b3814b20ba6fa59388c3d0ae"),
        new UiHttpSubscriber<ResponseBody>() {
          @Override public void onSucceed(ResponseBody responseBody) {
            try {
              showResult(responseBody.string());
            } catch (IOException e) {
              e.printStackTrace();
            }
          }
        });
  }

  private void showResult(String result) {
    getView().showWeatherResult(result);
  }
}

