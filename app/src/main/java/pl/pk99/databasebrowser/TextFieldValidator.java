package pl.pk99.databasebrowser;

import android.util.Log;
import android.widget.EditText;

import java.text.ParseException;
import java.text.SimpleDateFormat;

class TextFieldValidator {

    private ValidationType validationType;
    private EditText editText;

    EditText getEditText() {
        return editText;
    }

    TextFieldValidator(ValidationType validationType, EditText text) {
        this.validationType = validationType;
        this.editText = text;
    }

    boolean validate () {
        boolean value = true;
        switch (validationType) {
            case NOTEMPTY:
                value = editText.getText().length() != 0;
                break;

            case DATE:
                try {
                    SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd");
                    date.parse(editText.getText().toString());
                } catch (ParseException e) {
                    value = false;
                    Log.e("Parse exception: ", e.getMessage());
                }
                break;
        }
        return value;
    }
}
