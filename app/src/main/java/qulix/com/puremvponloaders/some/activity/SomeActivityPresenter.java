package qulix.com.puremvponloaders.some.activity;

import android.support.annotation.NonNull;
import android.util.Log;
import qulix.com.puremvponloaders.mvp.activity.MvpActivityStatePresenter;
import qulix.com.puremvponloaders.some.LongTaskHere;

public class SomeActivityPresenter extends MvpActivityStatePresenter<SomeActivityView, SomeActivityViewState>
        implements LongTaskHere.Callback {

    private static final String LOG_TAG = "LOG_TAG_ACT_P";

    private String textData;

    public void update() {
        Log.d(LOG_TAG, "activity update");

        ifViewAttached(view -> {
            textData = null;
            loadTextData(view);
        });
    }

    @Override
    public void longTaskDone(String data) {
        getViewState().setState(SomeActivityViewState.State.OK);
        textData = data;
        ifViewAttached(view -> {
            view.showLoading(false);
            view.showLoadedData(data);
        });
    }

    private void loadTextData(SomeActivityView view) {
        if (textData == null) {
            getViewState().setState(SomeActivityViewState.State.LOADING);
            view.showLoading(true);
            LongTaskHere.doLongTask(this);
        } else {
            view.showLoading(false);
        }
    }

    @NonNull
    @Override
    public SomeActivityViewState newViewState() {
        Log.d(LOG_TAG, "activity newViewState");
        return new SomeActivityViewState();
    }

    @Override
    public boolean applyViewState(boolean firstAttach, SomeActivityView view, SomeActivityViewState viewState) {
        Log.d(LOG_TAG, "activity applyViewState: " + firstAttach + " - " + viewState.getState());
        switch (viewState.getState()) {
            case LOADING:
                view.showLoading(true);
                return true;
            case OK:
                view.showLoading(false);
                return true;
            case ERROR:
                // nothing to do
                return true;
            default:
                return false;
        }
    }
}
