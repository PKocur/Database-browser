package pl.pk99.databasebrowser.data;

import android.app.IntentService;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pl.pk99.databasebrowser.Cat;

public class CatsDataManager {
    private CatsData catsData;
    private Context context;

    public CatsDataManager(Context context) {
        this.context = context;
        catsData = new CatsData(context);

        if(catsData.isDatabaseEmpty())
            insertSampleData();
    }

    public void showData() {
        Toast.makeText(context, "Odczytuję dane z bazy!",
                Toast.LENGTH_LONG).show();

        Cursor cursor = catsData.selectAll();
        while (cursor.moveToNext()) {
            for (int x = 0; x < 6; x++) {
                Log.i("showData() nr " + (cursor.getPosition() + 1), cursor.getString(x));
            }
        }
        cursor.close();
    }

    public List<Cat> getCats() {
        List<Cat> cats = new ArrayList<Cat>();
        Cursor cursor = catsData.selectAll();
        while (cursor.moveToNext()) {
            Cat cat = new Cat(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getString(5));
            cats.add(cat);
        }
        cursor.close();

        return cats;
    }

    public List<Cat> getCatsMale() {
        return getCatsWithQuery(catsData.findByGender(true));
    }

    public List<Cat> getCatsFemale() {
        return getCatsWithQuery(catsData.findByGender(false));
    }

    public List<Cat> getCatsMicrochipped() {
        return getCatsWithQuery(catsData.findByChip(true));
    }

    public List<Cat> getCatsWithoutMicrochipped() {
        return getCatsWithQuery(catsData.findByChip(false));
    }


    private List<Cat> getCatsWithQuery(Cursor cursor) {
        List<Cat> cats = new ArrayList<Cat>();
        while (cursor.moveToNext()) {
            Cat cat = new Cat(cursor.getInt(0), cursor.getString(1), cursor.getString(2),
                    cursor.getString(3), cursor.getString(4), cursor.getString(5));
            cats.add(cat);
        }
        cursor.close();
        return cats;
    }

    public void addCat(Cat cat) {
        catsData.insert(cat.getName(), cat.getBreed(), cat.getBirthDate(), cat.getGender(),
                cat.getMicrochipped());
    }

    private void insertSampleData() {
        catsData.insert("Pawełek", "Dachowiec", "2014-02-05", "Male", "No");
        catsData.insert("Koteł", "Brytyjski", "2019-07-12", "Male", "Yes");
        catsData.insert("Igiełka", "Syjamski", "2016-09-26", "Female", "Yes");
    }
}
