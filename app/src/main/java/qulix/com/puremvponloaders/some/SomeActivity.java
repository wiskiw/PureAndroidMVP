package qulix.com.puremvponloaders.some;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import qulix.com.puremvponloaders.R;
import qulix.com.puremvponloaders.mvp.MvpActivity;

public class SomeActivity extends MvpActivity<SomePresenter, SomeView> implements SomeView {

    private TextView textView;
    private EditText editText;
    private Button button;

    @Override
    @NonNull
    protected SomePresenter getNewPresenter() {
        return new SomePresenter();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        button = findViewById(R.id.button);

    }

    @Override
    protected void onPresenterAttached(SomePresenter presenter) {
        super.onPresenterAttached(presenter);

        button.setOnClickListener(v -> presenter.update());

    }

    @Override
    public void showLoading(boolean loading) {
        Log.d("LOG_TAG", "showLoading: " + loading);
        Log.d("LOG_TAG", "showLoading: " + this);
        textView.setText("loading " + loading);
        if (loading) {
            editText.setText("");
        }
    }

    @Override
    public void showLoadedData(String data) {
        Log.d("LOG_TAG", "showLoadedData: " + data);
        editText.setText("data " + data);
        Toast.makeText(this, data, Toast.LENGTH_SHORT).show();
    }


}
