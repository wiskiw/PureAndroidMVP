package qulix.com.puremvponloaders.some;

import qulix.com.puremvponloaders.mvp.state.MvpViewState;

public class SomeViewState implements MvpViewState {

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
