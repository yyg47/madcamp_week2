package io.madcamp.yh.mc_assignment1;


import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Build;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

import java.util.ArrayList;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        final Intent intent = getIntent();

        /* intent에 저장된 정보를 불러옴 */
        String UserName = intent.getStringExtra("UserName");
        int score = intent.getIntExtra("Game_Score",-1);
        int level = intent.getIntExtra("level", 0);
        boolean rankingOnly = intent.getBooleanExtra("ranking_only", false);

        /* View 가져오기 */
        final TextView messageTextView = findViewById(R.id.text_view_message);
        final TextView rankingTextView = findViewById(R.id.text_view_ranking);
        final TextView scoreTextView = findViewById(R.id.text_view_score);
        final Button buttonBack = findViewById(R.id.button_back);
        final View listHeader = findViewById(R.id.view_header);
        final ListView listView = findViewById(R.id.list_view_ranking);

        /* 뒤로가기 버튼 설정 */
        buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /* 랭킹 불러와서 등수 계산 */
        GameScoreManager manager = new GameScoreManager(this);
        int ranking = manager.guessRanking(level, score);

        if (ranking == 0||ranking == 2|| ranking==1){
            final LottieAnimationView animationView1 = (LottieAnimationView) findViewById(R.id.animation_view_trophy);
            final LottieAnimationView animationView2= (LottieAnimationView) findViewById(R.id.animation_view_fireworks);
            final LottieAnimationView animationView3 = (LottieAnimationView) findViewById(R.id.animation_view_ribbon);
            animationView1.playAnimation();
            animationView2.playAnimation();
            animationView3.playAnimation();

            animationView1.setSpeed(0.4f);
            animationView1.addAnimatorUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                @Override
                public void onAnimationUpdate(ValueAnimator animation) {
                    Log.d("Test@Anim", "" + animation.getCurrentPlayTime() + " " + animation.getDuration());

                    if(animation.getCurrentPlayTime() > animation.getDuration()) {
                        ((ViewGroup)animationView1.getParent()).removeView(animationView1);
                    }
                }
            });
        }

        if(rankingOnly) { /* 랭킹으로 들어온 경우에는 점수 메시지 표시 안 함 */
            String levelName = getResources().getStringArray(R.array.level)[level];
            messageTextView.setText(levelName + " 랭킹");
            rankingTextView.setText("");
            scoreTextView.setText("");
        } else {
            /* UserName, final_score을 이용하여 땡땡땡의 점수는 final_score 입니다! */
            scoreTextView.setText(score + "점 입니다!!");
            if(0 <= ranking) {
                rankingTextView.setText("#" + (ranking + 1));
                manager.add(level, UserName, score);
                manager.save();
            } else {
                rankingTextView.setText("");
            }
        }

        /* Adapter 설정 */
        RankingListAdapter adapter = new RankingListAdapter(ScoreActivity.this,
                R.layout.item_ranking, manager.getLevel(level), ranking);
        listView.setAdapter(adapter);

        /* List Header 추가 */
        ViewGroup group = (ViewGroup)listHeader.getParent();
        int index = group.indexOfChild(listHeader);
        group.removeViewAt(index);
        group.addView(adapter.createHeader(), index);


    }

    private static class RankingListAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private int highlightColor;
        private ArrayList<GameScoreManager.ScoreSet> data;
        private int layout;
        private int highlight;

        public RankingListAdapter(Context context, int layout, ArrayList<GameScoreManager.ScoreSet> data, int hilight) {
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.highlightColor = context.getResources().getColor(R.color.colorPrimary);
            this.layout = layout;
            this.data = data;
            this.highlight = hilight;
        }

        @Override
        public int getCount() {
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = inflater.inflate(layout, parent, false);
            }
            GameScoreManager.ScoreSet set = (GameScoreManager.ScoreSet) getItem(position);
            final TextView ranking = convertView.findViewById(R.id.text_view_ranking);
            final TextView name = convertView.findViewById(R.id.text_view_name);
            final TextView score = convertView.findViewById(R.id.text_view_score);
            SpannableStringBuilder[] sb = new SpannableStringBuilder[]{
                    new SpannableStringBuilder("#" + (1 + position)),
                    new SpannableStringBuilder("" + set.score),
                    new SpannableStringBuilder(set.name),
            };

            if(position == highlight) {
                final ForegroundColorSpan span = new ForegroundColorSpan(highlightColor);
                for(SpannableStringBuilder s : sb)
                    s.setSpan(span, 0, s.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            ranking.setText(sb[0]);
            score.setText(sb[1]);
            name.setText(sb[2]);
            return convertView;
        }

        private View createHeader() {
            final View convertView = inflater.inflate(layout, null, false);
            final TextView ranking = convertView.findViewById(R.id.text_view_ranking);
            final TextView name = convertView.findViewById(R.id.text_view_name);
            final TextView score = convertView.findViewById(R.id.text_view_score);

            SpannableStringBuilder sb1 = new SpannableStringBuilder("#");
            sb1.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, sb1.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            ranking.setText(sb1);

            SpannableStringBuilder sb2 = new SpannableStringBuilder("점수");
            sb2.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, sb2.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            score.setText(sb2);

            SpannableStringBuilder sb3 = new SpannableStringBuilder("이름");
            sb3.setSpan(new StyleSpan(android.graphics.Typeface.BOLD), 0, sb3.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            name.setText(sb3);

            if(Build.VERSION.SDK_INT >= 17) {
                ranking.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
                score.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
                name.setTextAlignment(TextView.TEXT_ALIGNMENT_CENTER);
            }
            return convertView;
        }


    }

}
