package pl.pk99.databasebrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import pl.pk99.databasebrowser.data.CatsDataManager;

public class ShowCatsActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener,
        View.OnClickListener {

    CatsDataManager catsDataManager;
    LinearLayout linearLayout;

    TextView txtTitle;
    CheckBox cboxShowOneCat;
    Button btnShow;
    Button btnBack;

    int queryPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cats);

        catsDataManager = new CatsDataManager(getApplicationContext());
        linearLayout = findViewById(R.id.LinearLayoutCats);

        cboxShowOneCat = findViewById(R.id.cboxShowOneCat);
        btnBack = findViewById(R.id.btnBack);
        btnShow = findViewById(R.id.btnShow);
        txtTitle = findViewById(R.id.showCatsTitle);

        btnBack.setOnClickListener(this);
        btnShow.setOnClickListener(this);

        Spinner spinner = (Spinner) findViewById(R.id.QuerySpinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.queries, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);

        showCats(catsDataManager.getCats());
    }

    void showCats(List<Cat> queryCats) {
        linearLayout.removeAllViews();
        txtTitle.setText(getResources().getStringArray(R.array.queries)[queryPosition]);

        for (Cat cat : queryCats) {
            TextView row = new TextView(getApplicationContext());

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            row.setLayoutParams(params);
            linearLayout.addView(row);

            row.setText(getString(R.string.cat_row, cat.getId(), cat.getName(), cat.getBreed(), cat.getBirthDate(),
                    cat.getGender(), cat.getMicrochipped()));
            row.setTextColor(Color.BLACK);
            row.setTextSize(17);

            TextView rowSeparator = new TextView(getApplicationContext());
            rowSeparator.setLayoutParams(params);

            linearLayout.addView(rowSeparator);
            rowSeparator.setText("---------------------------------------------------");

            if (cboxShowOneCat.isChecked()) break;
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View arg1, int position, long id) {
        queryPosition = position;
    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnShow:
                switch (queryPosition) {
                    case 0:
                        showCats(catsDataManager.getCats());
                        break;
                    case 1:
                        showCats(catsDataManager.getCatsMicrochipped());
                        break;
                    case 2:
                        showCats(catsDataManager.getCatsWithoutMicrochipped());
                        break;
                    case 3:
                        showCats(catsDataManager.getCatsMale());
                        break;
                    case 4:
                        showCats(catsDataManager.getCatsFemale());
                        break;
                }
                break;

            case R.id.btnBack:
                finish();
                break;
        }

    }
}
