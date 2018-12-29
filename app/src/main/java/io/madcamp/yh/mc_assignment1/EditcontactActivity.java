package io.madcamp.yh.mc_assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class EditcontactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editcontact);

        EditText name_editText = (EditText)findViewById(R.id.name_edittext);
        name_editText.setText(getIntent().getStringExtra("contact_name"));
        EditText number_editText = (EditText)findViewById(R.id.number_edittext);
        number_editText.setText(getIntent().getStringExtra("contact_number"));

        Button edit_button = findViewById(R.id.edit_button);
        edit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                EditText name_editText = (EditText)findViewById(R.id.name_edittext);
                resultIntent.putExtra("contact_name",name_editText.getText().toString());

                EditText number_editText = (EditText)findViewById(R.id.number_edittext);
                resultIntent.putExtra("contact_num", number_editText.getText().toString());

                resultIntent.putExtra("contact_position",getIntent().getIntExtra("contact_position",0));

                setResult(AddcontactActivity.RESULT_OK,resultIntent);
                finish();
            }
        });




    }
}
