package qulix.com.puremvponloaders.example.activity;

import qulix.com.puremvponloaders.mvp.MvpView;

public interface ExampleActivityView extends MvpView {

    void showLoading(boolean loading);

    void showLoadedData(String data);

}
