package qulix.com.puremvponloaders.mvp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.Loader;

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