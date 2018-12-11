package qulix.com.puremvponloaders.mvp;

import android.support.annotation.Nullable;

public abstract class MvpPresenter<V extends MvpView> {

    @Nullable
    private V view;

    private boolean firstAttached = true;

    protected void onViewAttached(V view, boolean firstAttached) {

    }

    public final void attachView(V view) {
        this.view = view;
        onViewAttached(view, firstAttached);
        firstAttached = false;
    }

    public final void detachView() {
        this.view = null;
    }

    protected void ifViewAttached(PresenterCallback<V> callback) {
        if (view != null) {
            callback.onViewAttached(view);
        }
    }

    protected interface PresenterCallback<V> {
        void onViewAttached(V view);
    }

}
