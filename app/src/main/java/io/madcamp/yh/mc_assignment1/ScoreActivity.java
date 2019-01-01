package io.madcamp.yh.mc_assignment1;


import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ScoreActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        /* 우선 intent_username 택배로 보내둔 정보를 받을게요 */
        String UserName =  getIntent().getStringExtra("UserName");

        /* intent_score 에 저장해둔 정보를 받을게요 */
        int score = getIntent().getIntExtra("Game_Score",-1);
        int level = getIntent().getIntExtra("level", 0);
        boolean rankingOnly = getIntent().getBooleanExtra("ranking_only", false);

        if(rankingOnly) {
            ((TextView)findViewById(R.id.text_view_message)).setText(getString(Tab3Fragment.BUTTON_LABELS[level]) + " 랭킹");
            ((TextView)findViewById(R.id.text_view_ranking)).setText("");
            ((TextView)findViewById(R.id.text_view_score)).setText("");
        } else {
            /* UserName, final_score을 이용하여 땡땡땡의 점수는 final_score 입니다! */
            TextView text_view_score = (TextView) findViewById(R.id.text_view_score);
            text_view_score.setText(score + "점 입니다!!");
        }

        /* 뒤로가기 버튼 설정 시작!! */

        Button button_back = findViewById(R.id.button_back);
        button_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /* Load score information */
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

        ListView listView = findViewById(R.id.list_view_ranking);
        RankingListAdapter adapter = new RankingListAdapter(ScoreActivity.this,
                R.layout.item_ranking, manager.get(level), ranking);
        listView.setAdapter(adapter);
        listView.addHeaderView(adapter.createHeader());
    }

    private static class RankingListAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private int hilightColor;
        private ArrayList<GameScoreManager.ScoreSet> data;
        private int layout;
        private int hilight;

        public RankingListAdapter(Context context, int layout, ArrayList<GameScoreManager.ScoreSet> data, int hilight) {
            this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            this.hilightColor = context.getResources().getColor(R.color.colorPrimary);
            this.layout = layout;
            this.data = data;
            this.hilight = hilight;
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
            TextView ranking = convertView.findViewById(R.id.text_view_ranking);
            TextView name = convertView.findViewById(R.id.text_view_name);
            TextView score = convertView.findViewById(R.id.text_view_score);
            SpannableStringBuilder sb1 = new SpannableStringBuilder("#" + (1 + position));
            SpannableStringBuilder sb2 = new SpannableStringBuilder("" + set.score);
            SpannableStringBuilder sb3 = new SpannableStringBuilder(set.name);
            if(position == hilight) {
                sb1.setSpan(new ForegroundColorSpan(hilightColor), 0, sb1.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                sb2.setSpan(new ForegroundColorSpan(hilightColor), 0, sb2.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                sb3.setSpan(new ForegroundColorSpan(hilightColor), 0, sb3.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
            ranking.setText(sb1);
            score.setText(sb2);
            name.setText(sb3);
            return convertView;
        }

        private View createHeader() {
            View convertView = inflater.inflate(layout, null, false);
            TextView ranking = convertView.findViewById(R.id.text_view_ranking);
            TextView name = convertView.findViewById(R.id.text_view_name);
            TextView score = convertView.findViewById(R.id.text_view_score);

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
