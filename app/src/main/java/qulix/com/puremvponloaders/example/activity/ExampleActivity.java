package qulix.com.puremvponloaders.example.activity;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import qulix.com.puremvponloaders.R;
import qulix.com.puremvponloaders.mvp.activity.MvpActivity;
import qulix.com.puremvponloaders.example.fragment.ExampleFragment;

public class ExampleActivity extends MvpActivity<ExampleActivityPresenter, ExampleActivityView>
        implements ExampleActivityView {

    private static final String LOG_TAG = "LOG_TAG_ACT";

    private TextView textView;
    private EditText editText;
    private Button button;

    @Override
    @NonNull
    protected ExampleActivityPresenter getNewPresenter() {
        return new ExampleActivityPresenter();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);


        String frgTag = "frg";
        FragmentManager fragmentManager = getSupportFragmentManager();
        ExampleFragment exampleFragment = (ExampleFragment) fragmentManager.findFragmentByTag(frgTag);
        if (exampleFragment == null) {
            exampleFragment = new ExampleFragment();
        }
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frameLayout, exampleFragment, frgTag)
                .commit();

    }

    @Override
    protected void onPresenterAttached(ExampleActivityPresenter presenter) {
        super.onPresenterAttached(presenter);

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
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }


}
