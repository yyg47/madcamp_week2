package io.madcamp.yh.mc_assignment1;


import android.content.DialogInterface;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        /* 우선 intent_username 택배로 보내둔 정보를 받을게요 */

        String UserName =  getIntent().getStringExtra("UserName");

        /* intent_score 에 저장해둔 정보를 받을게요 */
        int score = getIntent().getIntExtra("Game_Score",-1);

        /* UserName, final_score을 이용하여 땡땡땡의 점수는 final_score 입니다! */

        TextView text_view_score = (TextView) findViewById(R.id.text_view_score);
        text_view_score.setText(score + "점 입니다!!");


        /* 뒤로가기 버튼 설정 시작!! */

        Button button_back = findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /* Load score information */
        int level = getIntent().getIntExtra("level", 0);
        GameScoreManager manager = new GameScoreManager(this);
        manager.load();
        int ranking = manager.guessRanking(level, score);
        if(0 <= ranking && ranking < 10) {
            ((TextView)findViewById(R.id.text_view_ranking))
                    .setText("#" + (ranking + 1));
            manager.add(level, UserName, score);
            manager.save();
        } else {
            ((TextView)findViewById(R.id.text_view_ranking))
                    .setText("");
        }
        ((TextView)findViewById(R.id.text_view_score_board))
                .setText(manager.toString(level));
    }

}
