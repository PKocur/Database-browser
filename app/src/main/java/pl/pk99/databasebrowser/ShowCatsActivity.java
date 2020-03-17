package pl.pk99.databasebrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pl.pk99.databasebrowser.data.CatsDataManager;

public class ShowCatsActivity extends AppCompatActivity {

    CatsDataManager catsDataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_cats);

        catsDataManager = new CatsDataManager(getApplicationContext());

        LinearLayout linearLayout = findViewById(R.id.LinearLayoutCats);

        List<Cat> cats = catsDataManager.getCats();
        for(Cat cat : cats) {
            TextView row  = new TextView(this);

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT);
            params.setMargins(16, 0, 0, 0);
            row.setLayoutParams(params);
            linearLayout.addView(row);

            row.setText(getString(R.string.cat_row, cat.getId(), cat.getName(), cat.getBreed(), cat.getBirthDate(),
                    cat.getGender(), cat.getMicrochipped()));
            row.setTextColor(Color.BLACK);
            row.setTextSize(17);

            TextView rowSeparator  = new TextView(this);
            rowSeparator.setLayoutParams(params);

            linearLayout.addView(rowSeparator);
            rowSeparator.setText("---------------------------------------------------");
        }
    }
}
