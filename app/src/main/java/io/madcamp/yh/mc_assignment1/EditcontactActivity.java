package io.madcamp.yh.mc_assignment1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditcontactActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editcontact);

        final Intent intent = getIntent();

        final EditText name_editText = findViewById(R.id.name_edittext);
        final EditText number_editText = findViewById(R.id.number_edittext);

        /* Load current informations */
        name_editText.setText(intent.getStringExtra("contact_name"));
        number_editText.setText(intent.getStringExtra("contact_number"));

        Button edit_button = findViewById(R.id.edit_button);
        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /* When some editText was not filled */
                boolean isNameEmpty = name_editText.getText().toString().length() == 0;
                boolean isNumberEmpty = number_editText.getText().toString().length() == 0;
                if(isNameEmpty || isNumberEmpty) {
                    if(isNameEmpty) ((TextInputLayout) findViewById(R.id.text_input_layout_name)).setError("빈칸을 채워주세요!!");
                    else ((TextInputLayout) findViewById(R.id.text_input_layout_name)).setError("");
                    if(isNumberEmpty) ((TextInputLayout) findViewById(R.id.text_input_layout_number)).setError("빈칸을 채워주세요!!");
                    else ((TextInputLayout) findViewById(R.id.text_input_layout_number)).setError("");
                } else {
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("contact_name",name_editText.getText().toString());
                    resultIntent.putExtra("contact_num", number_editText.getText().toString());
                    resultIntent.putExtra("contact_position", intent.getIntExtra("contact_position", 0));
                    setResult(AddcontactActivity.RESULT_OK, resultIntent);
                    finish();
                }
            }
        });

        name_editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ((TextInputLayout) findViewById(R.id.text_input_layout_name)).setError("");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        number_editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ((TextInputLayout) findViewById(R.id.text_input_layout_number)).setError("");
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }
}
