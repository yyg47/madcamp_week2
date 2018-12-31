package io.madcamp.yh.mc_assignment1;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import io.github.kexanie.library.MathView;
import io.lumiknit.mathe.Text;
import io.lumiknit.mathp.*;

public class GameActivity extends AppCompatActivity {
    private static final int NEXT_PROBLEM_DELAY = 1000;

    /* 게임 난이도 */
    private int level;

    /* 현재까지 점수 */
    private int score;
    private TextView scoreTextView;


    /* 답안 카드 */
    private CardView[] ansCards;
    private MathView problemMathView;
    private MathView[] ansMathViews;
    private int currentAnswer;

    private Handler nextProblemHandler;
    private Runnable nextProblemRunnable;

    /* 정답 오답 표기용 */
    private Toast resultToast;
    private View.OnClickListener correctOnClick;
    private View.OnClickListener wrongOnClick;

    /* 문제 */
    private ProblemSet problemSet;

    /* 타이머 */
    private CountDownTimer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        /* Intent에서 게임 난이도 불러오기 */
        Intent intent = getIntent();

        level = intent.getIntExtra("Game_Difficulty", -1);
        switch(level) {
            case 0: problemSet = new AddSubProblemSet(0); break;
            case 1: problemSet = new ArithmeticProblemSet(0); break;
            case 2: problemSet = new EqProblemSet(1); break;
            case 3: problemSet = new IntProblemSet(); break;
            default: problemSet = new AddSubProblemSet(0); break;
        }

        /* 점수 초기화 */
        scoreTextView = findViewById(R.id.text_view_score);
        updateScore(0);

        initializeAnsCards();
        nextProblem();

        /* -- 30초 시간제한 (시간 지나면 자동으로 ScoreActivity로 점수 전송) -- */
        timer = new CountDownTimer(6000,1000) {
            public void onTick(long millisUntilFinished){
                TextView textview_timeleft = findViewById(R.id.text_view_timeleft);
                textview_timeleft.setText(millisUntilFinished/1000 + "초 남았습니다!!");
            }
            public void onFinish(){
                TextView textview_timeleft = findViewById(R.id.text_view_timeleft);
                textview_timeleft.setText("끝!!");

                AlertDialog.Builder builder3 = new AlertDialog.Builder(GameActivity.this);

                if (score > 100) {
                    builder3.setMessage("당신의 점수는!!" + score + "점 입니다!!! \n 난이도를 높여보세요!");
                }

                else if (score > 70) {
                    builder3.setMessage("당신의 점수는!!" + score + "점 입니다!!! \n 조금만 더 노력해봐요!");
                }

                else {
                    builder3.setMessage("당신의 점수는!!" + score + "점 입니다!!! \n 차라리 계산기를 두들기는게...");
                }

                builder3.setTitle("짜라란!")
                        .setCancelable(false)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                openScoreActivity();
                            }
                        });
                AlertDialog alert3 = builder3.create();
                alert3.show();

            }
        };
        timer.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    public void openScoreActivity() {
        Intent intent_score = new Intent(this, ScoreActivity.class);
        intent_score.putExtra("Game_Score",score);
        /* score에서 이름을 불러올 수 있게 이름값?을 설정해줄게요. 예를 들면 땡땡땡의 점수는 몇점입니다! 이런식으로요 */
        String User = new String(getIntent().getStringExtra("UserName"));
        intent_score.putExtra("UserName",User);
        intent_score.putExtra("level", level);
        startActivity(intent_score);
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
        ansCards = new CardView[4];
        ansCards[0] = findViewById(R.id.card_ans1);
        ansCards[1] = findViewById(R.id.card_ans2);
        ansCards[2] = findViewById(R.id.card_ans3);
        ansCards[3] = findViewById(R.id.card_ans4);

        nextProblemHandler = new Handler();
        nextProblemRunnable = new Runnable() {
            @Override
            public void run() {
                nextProblem();
            }
        };

        /* onClickListener 만들기 */
        resultToast = Toast.makeText(this, "", Toast.LENGTH_SHORT);
        correctOnClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentAnswer >= 0) {
                    resultToast.setText("정답");
                    resultToast.show();
                    updateScore(score + 10);
                    int i;
                    for (i = 3; i > 0 && ansCards[i] != v; i--) ;
                    showAnswer(i);
                    currentAnswer = -1;
                    nextProblemHandler.postDelayed(nextProblemRunnable, NEXT_PROBLEM_DELAY);
                }
            }
        };
        wrongOnClick = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentAnswer >= 0) {
                    resultToast.setText("오답");
                    resultToast.show();
                    updateScore(score - 5);
                    int i;
                    for (i = 3; i > 0 && ansCards[i] != v; i--) ;
                    showAnswer(i);
                    currentAnswer = -1;
                    nextProblemHandler.postDelayed(nextProblemRunnable, NEXT_PROBLEM_DELAY);
                }
            }
        };

        /* 정답 버튼에 onClickListener 추가 */
        setAnswer(1);
    }

    /* n번째(0-3) 카드를 정답으로 설정합니다. */
    private void setAnswer(int n) {
        /* n의 범위는 0-3 */
        if(n < 0 || n >= 4) return;
        currentAnswer = n;
        for(View v : ansCards) {
            v.setOnClickListener(wrongOnClick);
        }
        ansCards[currentAnswer].setOnClickListener(correctOnClick);
    }

    private void showAnswer(int n) {
        int to = n == currentAnswer ?
                getResources().getColor(R.color.colorButtonCorrect):
                getResources().getColor(R.color.colorButtonWrong);
        if(Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
            ColorDrawable[] color = {
                    new ColorDrawable(getResources().getColor(R.color.colorButton)), new ColorDrawable(to)};
            TransitionDrawable trans = new TransitionDrawable(color);
            ansCards[n].setBackground(trans);
            trans.startTransition(150);
        } else {
            ansCards[n].setCardBackgroundColor(to);
        }
    }

    private void nextProblem() {
        Set set = problemSet.generate();
        problemMathView.setText("\\[" + set.problem.toTex() + "\\]");
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(0); arr.add(1); arr.add(2); arr.add(3);
        int[] a = new int[4];
        for(int i = 0; i < 4; i++) {
            int j = (int)Range.pickFrom(0, arr.size());
            a[i] = arr.remove(j);
        }
        int ans = 0;
        while(a[ans] != 0) ans++;
        for(int i = 0; i < 4; i++) {
            ansMathViews[i].setText("\\[" + set.answers[a[i]].toTex() + "\\]");
            ansCards[i].setCardBackgroundColor(getResources().getColor(R.color.colorButton));
            if(Build.VERSION.SDK_INT > Build.VERSION_CODES.ICE_CREAM_SANDWICH_MR1) {
                ansCards[i].setBackground(new ColorDrawable(getResources().getColor(R.color.colorButton)));

            }
        }
        setAnswer(ans);
    }

    private void updateScore(int newScore) {
        score = newScore;
        scoreTextView.setText("" + score);
    }
}
