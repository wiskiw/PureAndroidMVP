package qulix.com.puremvponloaders.some.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import qulix.com.puremvponloaders.R;
import qulix.com.puremvponloaders.mvp.fragment.MvpFragment;

public class SomeFragment extends MvpFragment<SomeFragmentPresenter, SomeFragmentView> implements SomeFragmentView {

    private static final String LOG_TAG = "LOG_TAG_FRG";

    private TextView textView;
    private EditText editText;
    private Button button;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_some, null);

        textView = rootView.findViewById(R.id.textView);
        editText = rootView.findViewById(R.id.editText);
        button = rootView.findViewById(R.id.button);

        return rootView;
    }

    @NonNull
    @Override
    protected SomeFragmentPresenter getNewPresenter() {
        Log.d(LOG_TAG, "getNewPresenter SomeFragmentPresenter");
        return new SomeFragmentPresenter();
    }

    @Override
    protected void onPresenterAttached(SomeFragmentPresenter presenter) {
        super.onPresenterAttached(presenter);
        Log.d(LOG_TAG, "onPresenterAttached: " + presenter);
        button.setOnClickListener(v -> presenter.update());
    }

    @Override
    public void showLoading(boolean loading) {
        Log.d(LOG_TAG, "showLoading: " + loading);
        textView.setText("loading " + loading);
        if (loading) {
            editText.setText("");
        }
    }

    @Override
    public void showLoadedData(String data) {
        Log.d(LOG_TAG, "showLoadedData: " + data);
        editText.setText("data " + data);
        Toast.makeText(getContext(), "fragment: " + data, Toast.LENGTH_SHORT).show();
    }
}
