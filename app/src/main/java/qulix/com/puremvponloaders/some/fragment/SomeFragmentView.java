package qulix.com.puremvponloaders.some.fragment;

import qulix.com.puremvponloaders.mvp.MvpView;

public interface SomeFragmentView extends MvpView {

    void showLoading(boolean loading);

    void showLoadedData(String data);

}
