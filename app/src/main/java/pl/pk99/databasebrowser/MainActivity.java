package pl.pk99.databasebrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import pl.pk99.databasebrowser.data.CatsData;

public class MainActivity extends AppCompatActivity {

    CatsData catsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        catsData = new CatsData(this);
        insertSampleData();
    }

    public void onClick (View view) {
        showData(catsData.selectAll());
    }

    public void showData (Cursor cursor) {
        Toast.makeText(getApplication().getBaseContext(), "Odczytuję dane z bazy!",
                Toast.LENGTH_LONG).show();
        while(cursor.moveToNext()) {
            for(int x = 0; x < 6; x ++) {
                Log.i("showData() nr " + (cursor.getPosition() + 1), cursor.getString(x));
            }
        }
        cursor.close();
    }

    public void insertSampleData () {
        catsData.insert("Kotek", "Brytyjski", "2020-01-01", "Male", 1);
        catsData.insert("Kotek 2", "Brytyjski 2", "2019-02-01", "Female", 0);
        catsData.insert("Kotek 3", "Brytyjski", "2015-03-05", "Male", 1);
    }
}
