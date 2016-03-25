package com.ivangong.commonbase.utils;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by gongshenghu on 16/3/25.
 * 位置请求相关工具类
 * TODO: 改为Rx调用方式
 */
public class LocationManager {

  /**
   * TAG
   */
  private static final String TAG = "LocationManager";

  /**
   * 单例
   */
  private static LocationManager mInstance;

  /**
   * 上下文
   */
  private Context mContext;

  /**
   * 百度定位Client
   */
  private LocationClient mLocationClient;

  /**
   * 标志当前是否正在定位
   */
  private boolean mIsRequesting;

  /**
   * LocationClientOption
   */
  private LocationClientOption mClientOption;

  /**
   * 外界注册进来的LocationListener
   * TODO: 用set更合适
   */
  private List<LocationListener> mLocationListeners = new ArrayList<>();

  private LocationManager(Context context) {
    mContext = context;
    mLocationClient = new LocationClient(context);
    mLocationClient.registerLocationListener(new MyLocationListener());
    mClientOption = new LocationClientOption();
    configLocationClient();
  }

  /**
   * 获取LocationManager的单例<BR>
   *
   * @param context context
   * @return LocationManager的单例
   */
  public static synchronized LocationManager getInstance(@NonNull Context context) {
    if (mInstance == null) {
      mInstance = new LocationManager(context.getApplicationContext());
    }
    return mInstance;
  }

  /**
   * 实现BDLocation Listener<BR>
   *
   * @author gongshenghu
   * @version [Paitao Client V20130911, 2014-6-5]
   */
  private class MyLocationListener implements BDLocationListener {

    @Override public void onReceiveLocation(BDLocation location) {
      synchronized (this) {
        mIsRequesting = false;
        mLocationClient.stop();

        switch (location.getLocType()) {
          case BDLocation.TypeGpsLocation:
            onRequestSucceed(location);
            break;
          case BDLocation.TypeNetWorkLocation:
            onRequestSucceed(location);
            break;
          case BDLocation.TypeOffLineLocation:
            break;
          default:
            onRequestFailed();
            notifyListeners(null);
            return;
        }

        notifyListeners(location);
      }
    }
  }

  private void configLocationClient() {
    //打开gps
    mClientOption.setOpenGps(true);
    //设置坐标类型
    mClientOption.setCoorType("bd09ll");
    mClientOption.setIsNeedAddress(true);
    //设置定位模式，小于1000ms则一次定位;大于等于1000ms则定时定位
    mClientOption.setScanSpan(1);
    mClientOption.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
    setLocationClientOption(mClientOption);
  }

  /**
   * 设置定位参数
   *
   * @param option 定位相关参数
   */
  public void setLocationClientOption(LocationClientOption option) {
    mLocationClient.setLocOption(option);
  }

  /**
   * 位置获取成功
   */
  private void onRequestSucceed(@Nullable BDLocation location) {

  }

  /**
   * 位置获取失败
   */
  private void onRequestFailed() {
  }

  /**
   * 请求一次定位<BR>
   */
  public void requestLocation() {
    if (!isRequesting()) {
      mLocationClient.start();
      mLocationClient.requestLocation();
      mIsRequesting = true;
    }
  }

  /**
   * 当前是否正在定位<BR>
   *
   * @return 是否正在定位
   */
  public synchronized boolean isRequesting() {
    return mIsRequesting;
  }

  private synchronized void notifyListeners(@Nullable BDLocation location) {
    LocationListener[] arrays = new LocationListener[mLocationListeners.size()];
    mLocationListeners.toArray(arrays);
    for (LocationListener listener : arrays) {
      listener.onLocationReceived(location);
    }
  }

  /**
   * 注册LocationListener<BR>
   *
   * @param listener LocationListener
   */
  public synchronized void registerLocationListener(@NonNull LocationListener listener) {
    mLocationListeners.add(listener);
  }

  /**
   * 解注册LocationListener<BR>
   *
   * @param listener LocationListener
   */
  public synchronized void unregisterLocationListener(LocationListener listener) {
    mLocationListeners.remove(listener);
  }

  /**
   * 定位回调接口
   */
  public interface LocationListener {
    /**
     * 定位完成后，返回地址信息，定位失败时locationInfo为null<BR>
     *
     * @param location 地址信息
     */
    void onLocationReceived(@Nullable BDLocation location);
  }
}
