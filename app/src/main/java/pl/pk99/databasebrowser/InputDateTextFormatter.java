package pl.pk99.databasebrowser;

import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.widget.EditText;

//Klasa odpowiada za ułatwienie wprowadzenia daty w EditText
//(dodaje myślniki w formacie yyyy-mm-dd)
abstract class InputDateTextFormatter implements TextWatcher {

    static void format (final EditText text) {
        text.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                boolean textIsNotDeleting = before - count != 1;
                if(textIsNotDeleting) {
                    if ((s.length() == 4 || s.length() == 7)) {
                        text.append("-");
                        text.setSelection(s.length());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}
