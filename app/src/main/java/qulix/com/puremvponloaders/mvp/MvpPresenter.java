package qulix.com.puremvponloaders.mvp;

import android.support.annotation.Nullable;

import java.lang.ref.WeakReference;

/**
 * Base MVP-Presenter class. That class must process user input-actions and update UI elements of {@link V} class.
 *
 * @param <V> view class type
 * @author Anrey Yablonsky
 */
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

    /**
     * Call lambda-callback method only if any view been attached.
     *
     * @param callback lambda-callback
     */
    protected void ifViewAttached(PresenterCallback<V> callback) {
        if (weakView != null && weakView.get() != null) {
            callback.onViewAttached(weakView.get());
        }
    }

    /**
     * Will be called when {@link V} class attacked to presenter
     *
     * @param view          view class, than been attached
     * @param firstAttached true, if it's first view attacked to this presenter
     */
    protected void onViewAttached(V view, boolean firstAttached) {

    }

    public interface PresenterCallback<V> {
        void onViewAttached(V view);
    }

}
