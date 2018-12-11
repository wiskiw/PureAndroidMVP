package qulix.com.puremvponloaders.mvp.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import qulix.com.puremvponloaders.mvp.MvpPresenter;
import qulix.com.puremvponloaders.mvp.MvpPresenterLoader;
import qulix.com.puremvponloaders.mvp.MvpView;

public abstract class MvpFragment<P extends MvpPresenter<V>, V extends MvpView>
        extends Fragment implements LoaderManager.LoaderCallbacks<P> {

    @Nullable
    private P presenter;

    @NonNull
    public P getPresenter() {
        if (presenter == null) {
            presenter = getNewPresenter();
        }
        return presenter;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*
        Loaders have been deprecated as of Android P (API 28).
        The recommended option for dealing with loading data while handling the Activity and Fragment lifecycles
        is to use a combination of ViewModels and LiveData.
        https://developer.android.com/guide/components/loaders
                    \_0.0_/
         */
        getLoaderManager().initLoader(getLoaderId(), null, this);
    }

    @Override
    public void onResume() {
        super.onResume();
        onPresenterAttached(getPresenter());
        try {
            getPresenter().attachView((V) this);
        } catch (ClassCastException ex) {
            throw new ClassCastException(getClass().getCanonicalName()
                    + " must implement same MvpView as and MvpFragmentPresenter interface");
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        getPresenter().detachView();
    }

    protected int getLoaderId() {
        return this.getClass().hashCode();
    }

    protected void onPresenterAttached(P presenter) {

    }

    @NonNull
    protected abstract P getNewPresenter();


    @NonNull
    @Override
    public final Loader<P> onCreateLoader(int i, @Nullable Bundle bundle) {
        return new MvpPresenterLoader<>(getContext(), getNewPresenter());
    }

    @Override
    public final void onLoaderReset(@NonNull Loader<P> loader) {
        // do nothing
    }

    @Override
    public final void onLoadFinished(@NonNull Loader<P> loader, P p) {
        this.presenter = p;
    }

}
