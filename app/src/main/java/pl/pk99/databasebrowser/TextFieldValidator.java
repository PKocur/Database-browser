package pl.pk99.databasebrowser;

import android.widget.EditText;

abstract class TextFieldValidator {
    private EditText editText;

    EditText getEditText() {
        return editText;
    }

    TextFieldValidator(EditText text) {
        this.editText = text;
    }

    abstract boolean validate ();
}
