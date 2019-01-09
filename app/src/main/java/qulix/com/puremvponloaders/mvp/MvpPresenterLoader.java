package qulix.com.puremvponloaders.mvp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.Loader;

/**
 * Android loader class for saving MVP presenter when screen configuration changing.
 *
 * @param <P> type of {@link MvpPresenter} class
 * @author Andrey Yablonsky
 * @see Loader
 */
public class MvpPresenterLoader<P extends MvpPresenter> extends Loader<P> {

    @NonNull
    private P presenter;

    public MvpPresenterLoader(@NonNull Context context, @NonNull P presenter) {
        super(context);
        this.presenter = presenter;
    }

    @Override
    protected void onStartLoading() {
        deliverResult(presenter);
    }

}
