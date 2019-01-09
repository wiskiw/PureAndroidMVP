package qulix.com.puremvponloaders.example.fragment;

import qulix.com.puremvponloaders.mvp.MvpView;

public interface ExampleFragmentView extends MvpView {

    void showLoading(boolean loading);

    void showLoadedData(String data);

}
