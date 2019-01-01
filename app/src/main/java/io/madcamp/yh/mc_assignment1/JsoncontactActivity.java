package io.madcamp.yh.mc_assignment1;

import android.app.Activity;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class JsoncontactActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsoncontact);

        Intent intent = getIntent();
        String json = intent.getStringExtra("JSON");

        EditText editText = findViewById(R.id.edit_text);
        editText.setText(json);

        Button buttonCancel = findViewById(R.id.button_cancel);
        buttonCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        Button buttonUpdate = findViewById(R.id.button_update);
        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editText = findViewById(R.id.edit_text);
                String json = editText.getText().toString();
                if(validateJSON(json)) {
                    Intent intent = new Intent();
                    intent.putExtra("JSON", json);
                    setResult(Activity.RESULT_OK, intent);
                    finish();
                } else {
                    ((TextInputLayout)findViewById(R.id.text_input_layout)).setError("Wrong JSON Format");
                }
            }
        });
    }

    public static boolean validateJSON(String json) {
        try {
            JSONArray arr = new JSONArray(json);
            for(int i = 0; i < arr.length(); i++) {
                JSONObject a = arr.getJSONObject(i);
                a.getString("name");
                a.getString("phoneNumber");
            }
            return true;
        } catch(JSONException e) {
            return false;
        }
    }
}
