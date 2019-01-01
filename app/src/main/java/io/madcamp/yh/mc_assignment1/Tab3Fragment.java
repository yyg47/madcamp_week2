package io.madcamp.yh.mc_assignment1;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;

import java.util.ArrayList;

public class Tab3Fragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;
    private Context context;
    private View top;
    private EditText setname_edittext;

    private Button[] buttons;

    /* TabPagerAdapter에서 Fragment 생성할 때 사용하는 메소드 */
    public static Tab3Fragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        Tab3Fragment fragment = new Tab3Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPage = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        top = inflater.inflate(R.layout.fragment_tab3, container, false);
        this.context = top.getContext();

        /* score에서 이름을 불러올 수 있게 이름값?을 설정해줄게요. 예를 들면 땡땡땡의 점수는 몇점입니다! 이런식으로요 */
        setname_edittext = top.findViewById(R.id.setname_edittext);

        /* 게임 시작 버튼 설정 */
        buttons = new Button[] {
                top.findViewById(R.id.button_start_0),
                top.findViewById(R.id.button_start_1),
                top.findViewById(R.id.button_start_2),
                top.findViewById(R.id.button_start_3),
        };

        for(int i = 0; i < buttons.length; i++) {
            buttons[i].setOnClickListener(new StartButtonOnClickListener(i));
        }

        /* 랭킹 버튼 설정 */
        Button[] rankingButtons = new Button[] {
                top.findViewById(R.id.button_ranking_0),
                top.findViewById(R.id.button_ranking_1),
                top.findViewById(R.id.button_ranking_2),
                top.findViewById(R.id.button_ranking_3),
        };
        for(int i = 0; i < rankingButtons.length; i++) {
            rankingButtons[i].setOnClickListener(new RankingButtonOnClickListener(i));
        }

        return top;
    }

    @Override
    public void onResume() {
        super.onResume();

        GameScoreManager manager = new GameScoreManager(getActivity());
        String[] labels = getResources().getStringArray(R.array.level);
        for(int i = 0; i < buttons.length; i++) {
            buttons[i].setText(labels[i] + " (최고기록: " + manager.getScore(i, 0) + ")");
        }
        setname_edittext.setText(manager.lastName);
    }

    public class StartButtonOnClickListener implements View.OnClickListener {
        int difficulty;

        public StartButtonOnClickListener(int difficulty) {
            this.difficulty = difficulty;
        }

        @Override
        public void onClick(View v) {
            GameScoreManager m = new GameScoreManager(getActivity());
            m.lastName = setname_edittext.getText().toString();
            m.save();

            if(setname_edittext.getText().toString().length() == 0) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setMessage("이름 입력후 진행해 주세요.")
                        .setCancelable(false)
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert = builder.create();
                alert.show();
            } else {
                Intent intent = new Intent(getActivity(), GameActivity.class);
                intent.putExtra("Game_Difficulty", difficulty)
                        .putExtra("UserName", m.lastName)
                        .addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(intent);
            }
        }
    }

    public class RankingButtonOnClickListener implements View.OnClickListener {
        int difficulty;

        public RankingButtonOnClickListener(int difficulty) {
            this.difficulty = difficulty;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), ScoreActivity.class);
            intent.putExtra("level", difficulty)
                    .putExtra("ranking_only", true);
            startActivity(intent);
        }
    }
}
