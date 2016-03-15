package com.ivangong.commonbase;

import android.content.Context;
import android.support.multidex.MultiDexApplication;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by gongshenghu on 16/3/1.
 */
public class BaseApp extends MultiDexApplication {
  /**
   * LeakCanary references watcher.<BR/>
   * You could use the mRefWatcher to watch for fragment leaks.<BR/>
   * But before used you should call LeakCanary.install(this) and set the RefWatcher value
   * in base application.
   */
  protected RefWatcher mRefWatcher;

  @Override public void onCreate() {
    super.onCreate();
    mRefWatcher = LeakCanary.install(this);
  }

  public static RefWatcher getRefWatcher(Context context) {
    BaseApp application = (BaseApp) context.getApplicationContext();
    return application.mRefWatcher;
  }
}
