package qulix.com.puremvponloaders.some;

import qulix.com.puremvponloaders.mvp.MvpView;

public interface SomeView extends MvpView {

    void showLoading(boolean loading);

    void showLoadedData(String data);

}
