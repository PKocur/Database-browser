package pl.pk99.databasebrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import pl.pk99.databasebrowser.data.CatsData;
import pl.pk99.databasebrowser.data.CatsDataManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CatsDataManager catsDataManager;
    Button btnAddCat;
    Button btnShowCats;

    EditText etxtName;
    EditText etxtBreed;
    EditText etxtBirth;
    RadioButton rbtnMale;
    RadioButton rbtnFemale;
    CheckBox cboxMicrochipped;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        catsDataManager = new CatsDataManager(getApplicationContext());

        etxtName = (EditText) findViewById(R.id.etxtName);
        etxtBreed = (EditText) findViewById(R.id.etxtBreed);
        etxtBirth = (EditText) findViewById(R.id.etxtBirth);

        rbtnMale = (RadioButton) findViewById(R.id.rbtnMale);
        rbtnFemale = (RadioButton) findViewById(R.id.rbtnFemale);

        cboxMicrochipped = (CheckBox) findViewById(R.id.cboxMicrochipped);

        btnAddCat = (Button) findViewById(R.id.addCatButton);
        btnShowCats = (Button) findViewById(R.id.showCatsButton);

        btnAddCat.setOnClickListener(this);
        btnShowCats.setOnClickListener(this);
    }

    public void onClick (View view) {
        switch (view.getId()) {
            case R.id.showCatsButton:
                catsDataManager.showData();
                break;

            case R.id.addCatButton:
                String gender = rbtnMale.isChecked() ? "Male" : "Female";
                byte microchipped = (byte)(cboxMicrochipped.isChecked() ? 1: 0);

                catsDataManager.addCat(etxtName.getText().toString(), etxtBreed.getText().toString(),
                        etxtBirth.getText().toString(), gender, microchipped);
                break;
        }

    }


}
