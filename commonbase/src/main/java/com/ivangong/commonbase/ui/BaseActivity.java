package com.ivangong.commonbase.ui;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import java.util.Stack;

/**
 * Created by gongshenghu on 16/2/29.
 * Activity 基类 和具体业务无关
 */
public abstract class BaseActivity extends AppCompatActivity {

  /**
   * activity堆栈
   */
  private static Stack<Activity> mActivityStack = new Stack<>();

  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mActivityStack.add(this);
  }

  @Override protected void onRestart() {
    super.onRestart();
  }

  @Override protected void onStart() {
    super.onStart();
  }

  @Override protected void onResume() {
    super.onResume();
  }

  @Override protected void onPause() {
    super.onPause();
  }

  @Override protected void onStop() {
    super.onStop();
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    mActivityStack.remove(this);
  }

  /**
   * 退出所有的Activity<BR>
   */
  public void performQuitAllActivities() {
    Activity[] currentActivitis = mActivityStack.toArray(new Activity[mActivityStack.size()]);
    for (Activity activity : currentActivitis) {
      activity.finish();
    }
  }
}