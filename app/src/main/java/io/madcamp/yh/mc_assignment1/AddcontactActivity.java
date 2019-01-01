package io.madcamp.yh.mc_assignment1;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
                EditText number_editText = (EditText)findViewById(R.id.number_edittext);
                setResult(AddcontactActivity.RESULT_OK,resultIntent);

                if(name_editText.getText().toString().length() == 0 || number_editText.getText().toString().length() == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(AddcontactActivity.this);
                    builder.setMessage("두 항목 모두 작성해주세요.");
                    builder.setCancelable(false)
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else{
                    resultIntent.putExtra("contact_name",name_editText.getText().toString());
                    resultIntent.putExtra("contact_num", number_editText.getText().toString());
                    finish();
                }
            }
        });


    }
}
