package pl.pk99.databasebrowser.data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

//Klasa operująca bezpośrednio na bazie danych za pomocą SQL
public class CatsData {
    private SQLiteDatabase db;
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "Cats.db";

    private static final String TABLE_NAME = "Cats";
    private static final String TABLE_ROW_ID = "ID";
    private static final String TABLE_ROW_NAME = "Name";
    private static final String TABLE_ROW_BREED = "Breed";
    private static final String TABLE_ROW_GENDER = "Gender";
    private static final String TABLE_ROW_CHIP = "Microchipped";
    private static final String TABLE_ROW_BIRTH = "Birth_Date";

    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    TABLE_ROW_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL," +
                    TABLE_ROW_NAME + " TEXT NOT NULL," +
                    TABLE_ROW_BREED + " TEXT NOT NULL," +
                    TABLE_ROW_BIRTH+ " DATE NOT NULL," +
                    TABLE_ROW_GENDER + " TEXT NOT NULL," +
                    TABLE_ROW_CHIP + " TEXT NOT NULL" + ");";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + CatsData.TABLE_NAME;

    public class CatDataHelper extends SQLiteOpenHelper {
        CatDataHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(SQL_CREATE_ENTRIES);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }

    CatsData(Context context) {
        CatDataHelper catDataHelper = new CatDataHelper(context);
        db = catDataHelper.getWritableDatabase();
    }

    boolean isDatabaseEmpty() {
        return !selectAll().moveToFirst();
    }

    void insert(String name, String breed, String date, String gender, String chip) {
        String query = "INSERT INTO " + TABLE_NAME + " (" +
                TABLE_ROW_NAME + ", " + TABLE_ROW_BREED + ", " +
                TABLE_ROW_BIRTH + ", " +
                TABLE_ROW_GENDER + ", " + TABLE_ROW_CHIP + ") " +
                "VALUES (" +
                "'" + name + "'" + ", " + "'" + breed + "'" +
                ", " + "'" + date + "'" +
                ", " + "'" + gender + "', " + "'" + chip + "');";
        Log.i("insert() = ", query);
        db.execSQL(query);
    }

    public void delete(int id) {
        String query = "DELETE FROM " + TABLE_NAME +
                " WHERE " + TABLE_ROW_ID + " = " +
                id + ";";

        Log.i("delete() = ", query);
        db.execSQL(query);
    }

    Cursor selectAll() {
        return db.rawQuery("SELECT *" + " FROM " + TABLE_NAME, null);
    }

    Cursor findByGender(boolean isMale) {
        String gender = isMale ? "Male" : "Female";
        return findBy(TABLE_ROW_GENDER, gender);
    }

    Cursor findByChip(boolean hasChip) {
        String chip = hasChip ? "Yes" : "No";
        return findBy(TABLE_ROW_CHIP, chip);
    }

    private Cursor findBy(String tableRow, String arg) {
        String query = "SELECT " + TABLE_ROW_ID + ", " + TABLE_ROW_NAME + ", " + TABLE_ROW_BREED +
                ", " + TABLE_ROW_BIRTH +
                ", " + TABLE_ROW_GENDER + ", " + TABLE_ROW_CHIP +
                " FROM " + TABLE_NAME + " WHERE " + tableRow + " = '" + arg + "';";
        Log.i("searchByChip() = ", query);
        return db.rawQuery(query, null);
    }
}

