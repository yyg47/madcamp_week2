package io.madcamp.yh.mc_assignment1;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class AddcontactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcontact);

        Button add_button = findViewById(R.id.add_button);
        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent resultIntent = new Intent();
                EditText name_editText = (EditText)findViewById(R.id.name_edittext);
                resultIntent.putExtra("contact_name",name_editText.getText().toString());

                EditText number_editText = (EditText)findViewById(R.id.number_edittext);
                resultIntent.putExtra("contact_num", number_editText.getText().toString());

                setResult(AddcontactActivity.RESULT_OK,resultIntent);
                finish();
            }
        });


    }
}
