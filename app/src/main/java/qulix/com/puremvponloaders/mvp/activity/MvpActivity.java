package qulix.com.puremvponloaders.mvp.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import qulix.com.puremvponloaders.mvp.MvpPresenter;
import qulix.com.puremvponloaders.mvp.MvpPresenterLoader;
import qulix.com.puremvponloaders.mvp.MvpView;

/**
 * Base class for create Mvp Activity. Process saving presenter instance when screen configuration changing.
 *
 * @param <V> view interface, that implement current activity
 * @param <P> type of presenter class
 * @see AppCompatActivity
 */
public abstract class MvpActivity<P extends MvpPresenter<V>, V extends MvpView> extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<P> {

    private P presenter;

    /**
     * Presenter инициализирован в OnResume далее
     *
     * @return презентер для текущей view
     */
    public P getPresenter() {
        return presenter;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        Loaders have been deprecated as of Android P (API 28).
        The recommended option for dealing with loading data while handling the Activity and Fragment lifecycles
        is to use a combination of ViewModels and LiveData.
        https://developer.android.com/guide/components/loaders
                    \_0.0_/
         */
        getSupportLoaderManager().initLoader(getLoaderId(), null, this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (presenter != null) {
            onPresenterAttached(presenter);
            try {
                presenter.attachView((V) this);
            } catch (ClassCastException ex) {
                throw new ClassCastException(getClass().getCanonicalName()
                        + " must implement same MvpView as and MvpActivityPresenter interface");
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (presenter != null) {
            presenter.detachView();
        }
    }

    @NonNull
    @Override
    public final Loader<P> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new MvpPresenterLoader<>(this, getNewPresenter());
    }

    @Override
    public final void onLoaderReset(@NonNull Loader<P> loader) {
        // do nothing
    }

    @Override
    public final void onLoadFinished(@NonNull Loader<P> loader, P p) {
        this.presenter = p;
    }

    protected int getLoaderId() {
        return this.getClass().hashCode();
    }

    protected void onPresenterAttached(P presenter) {

    }

    @NonNull
    protected abstract P getNewPresenter();
}
