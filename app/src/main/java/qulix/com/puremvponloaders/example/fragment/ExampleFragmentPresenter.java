package qulix.com.puremvponloaders.example.fragment;

import android.support.annotation.NonNull;
import android.util.Log;
import qulix.com.puremvponloaders.mvp.fragment.MvpFragmentStatePresenter;
import qulix.com.puremvponloaders.example.LongTaskHere;

public class ExampleFragmentPresenter extends MvpFragmentStatePresenter<ExampleFragmentView, ExampleFragmentViewState>
        implements LongTaskHere.Callback {

    private static final String LOG_TAG = "LOG_TAG_FRG_P";
    private String textData;


    public void update() {
        Log.d(LOG_TAG, "fragment update");
        ifViewAttached(view -> {
            textData = null;
            loadTextData(view);
        });
    }

    @Override
    public void longTaskDone(String data) {
        getViewState().setState(ExampleFragmentViewState.State.OK);
        textData = data;
        ifViewAttached(view -> {
            view.showLoading(false);
            view.showLoadedData(data);
        });
    }

    private void loadTextData(ExampleFragmentView view) {
        if (textData == null) {
            getViewState().setState(ExampleFragmentViewState.State.LOADING);
            view.showLoading(true);
            LongTaskHere.doLongTask(this);
        } else {
            view.showLoading(false);
        }
    }

    @NonNull
    @Override
    public ExampleFragmentViewState newViewState() {
        return new ExampleFragmentViewState();
    }

    @Override
    public void applyViewState(boolean firstAttach, ExampleFragmentView view, ExampleFragmentViewState viewState) {
        Log.d(LOG_TAG, "fragment applyViewState: " + firstAttach + " - " + viewState.getState());
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
