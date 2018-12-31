package io.madcamp.yh.mc_assignment1;


import android.content.DialogInterface;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
      
        /* 우선 intent_username 택배로 보내둔 정보를 받을게요 */

        String UserName =;



        /* intent_score 에 저장해둔 정보를 받을게요 */



        /* UserName, final_score을 이용하여 땡땡땡의 점수는 final_score 입니다! */



        /* 뒤로가기 버튼 설정 시작!! */

        Button button_back = findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("현재 점수는 초기화 됩니다. 정말 뒤로 가시겠습니까?");
                builder.setTitle("뒤로가기")
                        .setCancelable(false)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                finish(); /* addcontact 할때는 이걸로해서 쉽게 뒤로 갔는데 이번에도 이걸로 될지는 모르겠네요.. */
                                }
                        })
                        .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
    }
}
