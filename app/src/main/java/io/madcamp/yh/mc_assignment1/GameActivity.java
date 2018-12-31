package io.madcamp.yh.mc_assignment1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import io.github.kexanie.library.MathView;
import io.lumiknit.mathp.*;

public class GameActivity extends AppCompatActivity {

    /* 게임 난이도 */
    private int level;

    /* 현재까지 점수 */
    private int score;
    private TextView scoreTextView;


    /* 답안 카드 */
    private View[] ansCards;
    private MathView problemMathView;
    private MathView[] ansMathViews;

    /* 정답 오답 표기용 */
    private Toast resultToast;
    private View.OnClickListener correctOnClick;
    private View.OnClickListener wrongOnClick;

    /* 문제 */
    private ProblemSet problemSet;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        /* Intent에서 게임 난이도 불러오기 */
        Intent intent = getIntent();

        level = intent.getIntExtra("level", 0);
        switch(level) {
            case 0: problemSet = new ComplexArithmeticProblemSet(0); break;
            case 1: break;
            case 2: break;
        }


        /* 점수 초기화 */
        scoreTextView = findViewById(R.id.text_view_score);
        updateScore(0);

        initializeAnsCards();
        nextProblem();

        /* -- 30초 시간제한 (시간 지나면 자동으로 ScoreActivity로 점수 전송) -- */
    }


    /* 답안 카드에 대한 것들을 초기화합니다. */
    private void initializeAnsCards() {
        /* MathView 찾기 */
        problemMathView = (MathView)findViewById(R.id.math_problem);
        ansMathViews = new MathView[4];
        ansMathViews[0] = findViewById(R.id.math_ans1);
        ansMathViews[1] = findViewById(R.id.math_ans2);
        ansMathViews[2] = findViewById(R.id.math_ans3);
        ansMathViews[3] = findViewById(R.id.math_ans4);

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
                updateScore(score + 10);
                nextProblem();
            }
        };
        wrongOnClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultToast.setText("오답");
                resultToast.show();
                updateScore(score - 5);
                nextProblem();
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

    private void nextProblem() {
        Set set = problemSet.generate();
        problemMathView.setText("\\[" + set.problem.toTex() + "\\]");
        int[] a = new int[]{0, 1, 2, 3};
        int n = (int)Range.pickFrom(0, 4);
        a[n] = 0;
        a[0] = n;
        for(int i = 0; i < 4; i++) {
            ansMathViews[i].setText("\\[" + set.answers[a[i]].toTex() + "\\]");
        }
        setAnswer(n);
    }

    private void updateScore(int newScore) {
        score = newScore;
        scoreTextView.setText("" + score);
    }
}
