package qulix.com.puremvponloaders.mvp.state;

import android.support.annotation.NonNull;
import qulix.com.puremvponloaders.mvp.MvpPresenter;
import qulix.com.puremvponloaders.mvp.MvpView;

public abstract class MvpStatePresenter<V extends MvpView, VS extends MvpViewState> extends MvpPresenter<V> {

    @NonNull
    private VS viewState = newViewState();

    @Override
    protected void onViewAttached(V view, boolean firstAttached) {
        super.onViewAttached(view, firstAttached);
        applyViewState(firstAttached, view, viewState);
    }

    @NonNull
    public VS getViewState() {
        return viewState;
    }

    @NonNull
    protected abstract VS newViewState();

    protected abstract void applyViewState(boolean firstAttach, V view, VS viewState);


}
