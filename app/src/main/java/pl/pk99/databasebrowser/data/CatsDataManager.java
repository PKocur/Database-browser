package pl.pk99.databasebrowser.data;

import android.content.Context;
import android.database.Cursor;
import java.util.ArrayList;
import java.util.List;

import pl.pk99.databasebrowser.Cat;

//Klasa zarządzająca bazą danych
public class CatsDataManager {
    private CatsData catsData;

    public CatsDataManager(Context context) {
        catsData = new CatsData(context);

        if(catsData.isDatabaseEmpty())
            insertSampleData();
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

    public List<Cat> getCatsWithoutMicrochip() {
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
        catsData.insert("Pan Kot", "Brytyjski", "2018-05-11", "Male", "Yes");
        catsData.insert("Lukrecja", "Dachowiec", "2017-11-12", "Female", "No");
    }
}
