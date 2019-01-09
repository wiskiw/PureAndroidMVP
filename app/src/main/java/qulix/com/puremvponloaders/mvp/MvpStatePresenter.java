package qulix.com.puremvponloaders.mvp;

import android.support.annotation.NonNull;

/**
 * Describe MVP-Presenter class with saving view-state {@link VS}.
 *
 * @param <V>  type of mvp view class
 * @param <VS> type of view state class
 * @author Anrey Yablonsky
 * @see MvpPresenter
 */
public interface MvpStatePresenter<V extends MvpView, VS extends MvpViewState> {

    /**
     * Return existing view state object
     *
     * @return non-null view state object
     */
    @NonNull
    VS getViewState();


    /**
     * Create new ViewState object
     *
     * @return non-null view state object
     */
    @NonNull
    VS newViewState();

    /**
     * Here you must apply your #viewState to your #view
     *
     * @param firstAttach true, if it first view attach
     * @param view        view, that view state's must bee restored
     * @param viewState   view state that must be restored
     */
    void applyViewState(boolean firstAttach, V view, VS viewState);

}
