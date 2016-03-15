package com.ivangong.commonbase.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.ivangong.commonbase.BaseApp;

/**
 * Created by gongshenghu on 16/2/29.
 * Fragment 基类,和具体业务无关
 */
public abstract class BaseFragment extends Fragment {

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    return super.onCreateView(inflater, container, savedInstanceState);
  }

  @Override public void onStart() {
    super.onStart();
  }

  @Override public void onResume() {
    super.onResume();
  }

  @Override public void onPause() {
    super.onPause();
  }

  @Override public void onStop() {
    super.onStop();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    //TODO 基类
    ButterKnife.unbind(this);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    BaseApp.getRefWatcher(getActivity()).watch(this);
  }
}
