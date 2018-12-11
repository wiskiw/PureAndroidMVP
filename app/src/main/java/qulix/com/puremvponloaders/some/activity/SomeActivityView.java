package qulix.com.puremvponloaders.some.activity;

import qulix.com.puremvponloaders.mvp.MvpView;

public interface SomeActivityView extends MvpView {

    void showLoading(boolean loading);

    void showLoadedData(String data);

}
