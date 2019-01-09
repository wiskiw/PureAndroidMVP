package qulix.com.puremvponloaders.example.activity;

import qulix.com.puremvponloaders.mvp.MvpViewState;

public class ExampleActivityViewState implements MvpViewState {

    enum State {
        LOADING,
        ERROR,
        OK,
    }

    private State state = State.OK;

    public void setState(State state) {
        this.state = state;
    }

    public State getState() {
        return state;
    }
}
