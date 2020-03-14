package pl.pk99.databasebrowser.data;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

public class CatsDataManager {

    private CatsData catsData;
    Context context;

    public CatsDataManager (Context context) {
        this.context = context;
        catsData = new CatsData(context);
        insertSampleData();
    }

    public void showData () {
        Toast.makeText(context, "Odczytuję dane z bazy!",
                Toast.LENGTH_LONG).show();

        Cursor cursor = catsData.selectAll();
        while(cursor.moveToNext()) {
            for(int x = 0; x < 6; x ++) {
                Log.i("showData() nr " + (cursor.getPosition() + 1), cursor.getString(x));
            }
        }
        cursor.close();
    }

    public void addCat (String name, String breed, String date, String gender, byte microchipped) {
        catsData.insert(name, breed, date, gender, microchipped);
    }

    private void insertSampleData() {
        catsData.insert("Pawełek", "Dachowiec", "2014-02-05", "Male", 0);
        catsData.insert("Koteł", "Brytyjski", "2019-07-12", "Female", 1);
        catsData.insert("Kicia", "Syjamski", "2016-09-26", "Female", 1);
    }
}
