package io.madcamp.yh.mc_assignment1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
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
    public static final int Difficulty_Easy = 0;
    public static final int Difficulty_Normal = 1;
    public static final int Difficulty_Hard = 2;

    private static final String[] BUTTON_LABELS = {
            "덧셈/뺄셈", "복합", "적분"
    };
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
        EditText setname_edittext = (EditText) top.findViewById(R.id.setname_edittext);
        Intent intent_username = new Intent(getActivity(),GameActivity.class);
        intent_username.putExtra("UserName",setname_edittext.getText().toString());

        buttons = new Button[3];
        buttons[0] = (Button)top.findViewById(R.id.button_start_0);
        buttons[1] = (Button)top.findViewById(R.id.button_start_1);
        buttons[2] = (Button)top.findViewById(R.id.button_start_2);

        /* 난이도 별로 눌렀을때 intent 택배에 난이도 값을 저장해주었는데 맞는지는 모르겠네요.. 난이도 값은 위에 public static final int로 저장해두었습니다 */
        for(int i = 0; i < buttons.length; i++) {
            buttons[i].setOnClickListener(new StartButtonOnClickListener(i));
        }

        return top;
    }

    @Override
    public void onResume() {
        super.onResume();
        
        GameScoreManager manager = new GameScoreManager(getActivity());
        for(int i = 0; i < buttons.length; i++) {
            buttons[i].setText(BUTTON_LABELS[i] + " (최고기록: " + manager.getScore(i, 0) + ")");
        }
    }

    public class StartButtonOnClickListener implements View.OnClickListener {
        int difficulty;

        public StartButtonOnClickListener(int difficulty) {
            this.difficulty = difficulty;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), GameActivity.class);
            intent.putExtra("Game_Difficulty", difficulty);
            startActivity(intent);
        }
    }
}
