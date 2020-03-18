package pl.pk99.databasebrowser;

import android.util.Log;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;

//Klasa waliduje pole typu data (czy ma odpowiedni format)
class TextFieldValidatorDate extends TextFieldValidator {

    TextFieldValidatorDate(EditText text) {
        super(text);
    }

    @Override
    boolean validate() {
        boolean value = true;
        try {
            SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
            date.parse(getEditText().getText().toString());
        } catch (ParseException e) {
            value = false;
            Log.e("Parse exception: ", e.getMessage());
        }

        return value;
    }
}
