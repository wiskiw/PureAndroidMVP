package qulix.com.puremvponloaders.some;

import android.support.annotation.NonNull;
import android.util.Log;
import qulix.com.puremvponloaders.mvp.state.MvpStatePresenter;

public class SomePresenter extends MvpStatePresenter<SomeView, SomeViewState>
        implements LongTaskHere.Callback {

    private String textData;

    public void update() {
        ifViewAttached(view -> {
            textData = null;
            loadTextData(view);
        });
    }

    @Override
    public void longTaskDone(String data) {
        getViewState().setState(SomeViewState.State.OK);
        textData = data;
        ifViewAttached(view -> {
            view.showLoading(false);
            view.showLoadedData(data);
        });
    }

    private void loadTextData(SomeView view) {
        if (textData == null) {
            getViewState().setState(SomeViewState.State.LOADING);
            view.showLoading(true);
            LongTaskHere.doLongTask(this);
        }
    }

    @NonNull
    @Override
    protected SomeViewState newViewState() {
        return new SomeViewState();
    }

    @Override
    protected void applyViewState(boolean firstAttach, SomeView view, SomeViewState viewState) {
        Log.d("LOG_TAG", "applyViewState: " + firstAttach + " - " + viewState.getState());
        switch (viewState.getState()) {
            case LOADING:
                view.showLoading(true);
                break;
            case OK:
                loadTextData(view);
                break;
            case ERROR:
                // nothing to do
                break;
        }
    }
}
