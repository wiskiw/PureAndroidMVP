package qulix.com.puremvponloaders.mvp;

import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

public abstract class MvpPresenter<V extends MvpView> {

    @Nullable
    private WeakReference<V> weakView;
    private boolean firstAttached = true;

    public final void attachView(V view) {
        this.weakView = new WeakReference<>(view);
        onViewAttached(view, firstAttached);
        firstAttached = false;
    }

    public final void detachView() {
        this.weakView = null;
    }

    protected void ifViewAttached(PresenterCallback<V> callback) {
        if (weakView != null && weakView.get() != null) {
            callback.onViewAttached(weakView.get());
        }
    }

    protected void onViewAttached(V view, boolean firstAttached) {

    }

    public interface PresenterCallback<V> {
        void onViewAttached(V view);
    }

}
