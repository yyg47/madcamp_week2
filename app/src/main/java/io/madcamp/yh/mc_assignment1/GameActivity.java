package io.madcamp.yh.mc_assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class GameActivity extends AppCompatActivity {

    /* 게임 난이도 */
    private int level;

    /* 현재까지 점수 */
    private int score;


    /* 답안 카드 */
    private View[] ansCards;

    /* 정답 오답 표기용 */
    private Toast resultToast;
    private View.OnClickListener correctOnClick;
    private View.OnClickListener wrongOnClick;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        /* Intent에서 게임 난이도 불러오기 */
        Intent intent = getIntent();

        level = intent.getIntExtra("level", -1);

        /* 점수 초기화 */
        score = 0;

        initializeAnsCards();

        /* -- 30초 시간제한 (시간 지나면 자동으로 ScoreActivity로 점수 전송) -- */
    }


    /* 답안 카드에 대한 것들을 초기화합니다. */
    private void initializeAnsCards() {
        /* 답안 카드 찾기 */
        ansCards = new View[4];
        ansCards[0] = findViewById(R.id.card_ans1);
        ansCards[1] = findViewById(R.id.card_ans2);
        ansCards[2] = findViewById(R.id.card_ans3);
        ansCards[3] = findViewById(R.id.card_ans4);

        /* onClickListener 만들기 */
        resultToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        correctOnClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultToast.setText("정답");
                resultToast.show();
                score += 10;
            }
        };
        wrongOnClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultToast.setText("오답");
                resultToast.show();
                score -= 5;
            }
        };

        /* 정답 버튼에 onClickListener 추가 */
        setAnswer(1);
    }

    /* n번째(0-3) 카드를 정답으로 설정합니다. */
    private void setAnswer(int n) {
        /* n의 범위는 0-3 */
        if(n < 0 || n >= 4) return;
        for(View v : ansCards) {
            v.setOnClickListener(wrongOnClick);
        }
        ansCards[n].setOnClickListener(correctOnClick);
    }
}
