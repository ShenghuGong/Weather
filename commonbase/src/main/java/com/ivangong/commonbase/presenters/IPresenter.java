package com.ivangong.commonbase.presenters;

/**
 * Created by gongshenghu on 16/3/4.
 *
 * Every presenter in the app must either implement this interface or extend BasePresenter
 * indicating the MvpView type that wants to be attached with.
 */
public interface IPresenter<V extends BaseMVPView> {

  void attachView(V mvpView);

  V getView();

  void detachView();
}
