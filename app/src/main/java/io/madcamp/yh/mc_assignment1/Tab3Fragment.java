package io.madcamp.yh.mc_assignment1;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

public class Tab3Fragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    private int mPage;
    private Context context;
    private View top;

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

        buttons = new Button[3];
        buttons[0] = top.findViewById(R.id.button_start_0);
        buttons[1] = top.findViewById(R.id.button_start_1);
        buttons[2] = top.findViewById(R.id.button_start_2);



        for(int i = 0; i < buttons.length; i++) {
            buttons[i].setOnClickListener(new StartButtonOnClickListener(i));
        }


        return top;
    }

    @Override
    public void onResume() {
        super.onResume();

        ArrayList<Integer> scores = GameScoreManager.loadHiScores(getActivity());
        for(int i = 0; i < buttons.length; i++) {
            int score = i < scores.size() ? scores.get(i) : 0;
            buttons[i].setText(BUTTON_LABELS[i] + " (최고기록: " + score + ")");
        }
    }

    private class StartButtonOnClickListener implements View.OnClickListener {
        private int level;

        public StartButtonOnClickListener(int level) {
            this.level = level;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(getActivity(), GameActivity.class);
            intent.putExtra("level", level);
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        }
    }
}
