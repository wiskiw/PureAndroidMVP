package qulix.com.puremvponloaders.mvp;

import android.support.annotation.NonNull;

public interface MvpStatePresenter<V extends MvpView, VS extends MvpViewState> {

    @NonNull
    VS getViewState();

    @NonNull
    VS newViewState();

    void applyViewState(boolean firstAttach, V view, VS viewState);

}
