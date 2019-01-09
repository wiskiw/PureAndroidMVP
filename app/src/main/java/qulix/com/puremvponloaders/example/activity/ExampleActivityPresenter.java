package qulix.com.puremvponloaders.example.activity;

import android.support.annotation.NonNull;
import android.util.Log;
import qulix.com.puremvponloaders.mvp.activity.MvpActivityStatePresenter;
import qulix.com.puremvponloaders.example.LongTaskHere;

public class ExampleActivityPresenter extends MvpActivityStatePresenter<ExampleActivityView, ExampleActivityViewState>
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
        getViewState().setState(ExampleActivityViewState.State.OK);
        textData = data;
        ifViewAttached(view -> {
            view.showLoading(false);
            view.showLoadedData(data);
        });
    }

    private void loadTextData(ExampleActivityView view) {
        if (textData == null) {
            getViewState().setState(ExampleActivityViewState.State.LOADING);
            view.showLoading(true);
            LongTaskHere.doLongTask(this);
        } else {
            view.showLoading(false);
        }
    }

    @NonNull
    @Override
    public ExampleActivityViewState newViewState() {
        Log.d(LOG_TAG, "activity newViewState");
        return new ExampleActivityViewState();
    }

    @Override
    public void applyViewState(boolean firstAttach, ExampleActivityView view, ExampleActivityViewState viewState) {
        Log.d(LOG_TAG, "activity applyViewState: " + firstAttach + " - " + viewState.getState());
        switch (viewState.getState()) {
            case LOADING:
                view.showLoading(true);
                return;
            case OK:
                view.showLoading(false);
                return;
            case ERROR:
                // nothing to do
                return;
            default:
        }
    }
}
