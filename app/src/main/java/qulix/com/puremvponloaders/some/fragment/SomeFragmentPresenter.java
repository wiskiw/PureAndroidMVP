package qulix.com.puremvponloaders.some.fragment;

import android.support.annotation.NonNull;
import android.util.Log;
import qulix.com.puremvponloaders.mvp.fragment.MvpFragmentStatePresenter;
import qulix.com.puremvponloaders.some.LongTaskHere;

public class SomeFragmentPresenter extends MvpFragmentStatePresenter<SomeFragmentView, SomeFragmentViewState> implements LongTaskHere.Callback {

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
        getViewState().setState(SomeFragmentViewState.State.OK);
        textData = data;
        ifViewAttached(view -> {
            view.showLoading(false);
            view.showLoadedData(data);
        });
    }

    private void loadTextData(SomeFragmentView view) {
        if (textData == null) {
            getViewState().setState(SomeFragmentViewState.State.LOADING);
            view.showLoading(true);
            LongTaskHere.doLongTask(this);
        } else {
            view.showLoading(false);
        }
    }

    @NonNull
    @Override
    public SomeFragmentViewState newViewState() {
        return new SomeFragmentViewState();
    }

    @Override
    public boolean applyViewState(boolean firstAttach, SomeFragmentView view, SomeFragmentViewState viewState) {
        Log.d(LOG_TAG, "fragment applyViewState: " + firstAttach + " - " + viewState.getState());
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
