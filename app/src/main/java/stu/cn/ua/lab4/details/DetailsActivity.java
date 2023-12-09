package stu.cn.ua.lab4.details;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import java.text.SimpleDateFormat;

import stu.cn.ua.lab4.App;
import stu.cn.ua.lab4.R;
import stu.cn.ua.lab4.model.Meteorite;

public class DetailsActivity extends AppCompatActivity {

    public static final String EXTRA_REPOSITORY_ID = "REPOSITORY_ID";

    private TextView nameTextView;
    private TextView fallTextView;
    private TextView yearTextView;
    private DetailsViewModel viewModel;
    private ProgressBar progressBar;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        nameTextView = findViewById(R.id.nameTextView);
        yearTextView = findViewById(R.id.yearTextView);
        fallTextView = findViewById(R.id.fallTextView);
        progressBar = findViewById(R.id.progress);

        long meteoriteId = getIntent().getLongExtra(EXTRA_REPOSITORY_ID, 1);
        if (meteoriteId == -1) {
            throw new RuntimeException("There is no meteorite ID");
        }

        App app = (App) getApplication();
        ViewModelProvider viewModelProvider = new ViewModelProvider(this, app.getViewModelFactory());
        viewModel = viewModelProvider.get(DetailsViewModel.class);

        viewModel.loadMeteoriteById(meteoriteId);
        viewModel.getResults().observe(this, result -> {
            switch (result.getStatus()) {
                case SUCCESS:
                    Meteorite meteorite = result.getData();
                    nameTextView.setText(meteorite.getName());
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS");
                    yearTextView.setText(sdf.format(meteorite.getYear()));
                    fallTextView.setText(meteorite.getFall());
                    progressBar.setVisibility(View.GONE);
                    break;
                case EMPTY:
                    nameTextView.setText("");
                    yearTextView.setText("");
                    fallTextView.setText("");
                    progressBar.setVisibility(View.GONE);
                    break;
                case LOADING:
                    nameTextView.setText("");
                    yearTextView.setText("");
                    fallTextView.setText("");
                    progressBar.setVisibility(View.VISIBLE);
                    break;
            }
        });
    }
}
