package pl.pk99.databasebrowser;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pl.pk99.databasebrowser.data.CatsData;
import pl.pk99.databasebrowser.data.CatsDataManager;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    CatsDataManager catsDataManager;
    Button btnAddCat;
    Button btnShowCats;

    TextFieldValidator etxtName;
    TextFieldValidator etxtBreed;
    TextFieldValidator etxtBirth;
    RadioButton rbtnMale;
    RadioButton rbtnFemale;
    CheckBox cboxMicrochipped;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        catsDataManager = new CatsDataManager(getApplicationContext());

        etxtName = new TextFieldValidatorNotEmpty((EditText) findViewById(R.id.etxtName));
        etxtBreed = new TextFieldValidatorNotEmpty((EditText) findViewById(R.id.etxtBreed));
        etxtBirth = new TextFieldValidatorDate((EditText) findViewById(R.id.etxtBirth));
        InputDateTextFormatter.format(etxtBirth.getEditText());

        rbtnMale = findViewById(R.id.rbtnMale);
        rbtnFemale = findViewById(R.id.rbtnFemale);

        cboxMicrochipped = findViewById(R.id.cboxMicrochipped);

        btnAddCat = findViewById(R.id.addCatButton);
        btnShowCats = findViewById(R.id.showCatsButton);

        btnAddCat.setOnClickListener(this);
        btnShowCats.setOnClickListener(this);
    }

    public void onClick (View view) {
        switch (view.getId()) {
            case R.id.showCatsButton:
                showCats();
                break;

            case R.id.addCatButton:
                addCat();
                break;
        }
    }

    void showCats () {
        Intent intent = new Intent(this, ShowCatsActivity.class);
        startActivity(intent);
    }

    void addCat () {
        if(validateFields(etxtName, etxtBreed, etxtBirth)) {
            String gender = rbtnMale.isChecked() ? "Male" : "Female";
            String microchipped = cboxMicrochipped.isChecked() ? "Yes" : "No";
            Cat cat = new Cat(etxtName.getEditText().getText().toString(),
                    etxtBreed.getEditText().getText().toString(),
                    etxtBirth.getEditText().getText().toString(), gender, microchipped);
            catsDataManager.addCat(cat);
            resetAddCatForm();

            Toast.makeText(getApplicationContext(), R.string.cat_added,
                    Toast.LENGTH_LONG).show();
        } else {
            Toast.makeText(getApplicationContext(), R.string.cat_error,
                    Toast.LENGTH_LONG).show();
        }
    }

    void resetAddCatForm () {
        etxtName.getEditText().setText(null);
        etxtBreed.getEditText().setText(null);
        etxtBirth.getEditText().setText(null);
        rbtnMale.setEnabled(true);
        cboxMicrochipped.setChecked(false);
    }

    boolean validateFields(TextFieldValidator... textFieldValidators) {
        boolean value = true;
        for (TextFieldValidator textFieldValidator : textFieldValidators) {
            if(textFieldValidator.validate()) {
                textFieldValidator.getEditText().setBackgroundTintList
                        (getApplicationContext().getColorStateList(R.color.colorPrimaryDark));
            } else {
                textFieldValidator.getEditText().setBackgroundTintList
                        (getApplicationContext().getColorStateList(R.color.colorRed));
                value = false;
            }
        }
        return value;
    }

}
