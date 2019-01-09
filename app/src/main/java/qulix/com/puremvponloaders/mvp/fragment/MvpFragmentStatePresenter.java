package qulix.com.puremvponloaders.mvp.fragment;

import android.support.annotation.NonNull;
import qulix.com.puremvponloaders.mvp.MvpStatePresenter;
import qulix.com.puremvponloaders.mvp.MvpView;
import qulix.com.puremvponloaders.mvp.MvpViewState;

/**
 * Mvp Fragment Presenter with saving {@link MvpViewState}.
 *
 * @author Anrey Yablonsky
 */
public abstract class MvpFragmentStatePresenter<V extends MvpView, VS extends MvpViewState>
        extends MvpFragmentPresenter<V> implements MvpStatePresenter<V, VS> {

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

}
