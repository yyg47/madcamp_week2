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
        final EditText setname_edittext = (EditText) top.findViewById(R.id.setname_edittext);

        /* 난이도 별로 눌렀을때 intent 택배에 난이도 값을 저장해주었는데 맞는지는 모르겠네요.. 난이도 값은 위에 public static final int로 저장해두었습니다 */
        top.findViewById(R.id.button_start_0).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GameActivity.class);
                intent.putExtra("Game_Difficulty", Difficulty_Easy);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.putExtra("UserName",setname_edittext.getText().toString());
                if(setname_edittext.getText().toString().length() == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("이름 입력후 진행해 주세요.");
                    builder.setCancelable(false)
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else {
                    startActivity(intent);
                }

            }
        });

        top.findViewById(R.id.button_start_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GameActivity.class);
                intent.putExtra("Game_Difficulty", Difficulty_Normal);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.putExtra("UserName",setname_edittext.getText().toString());
                if(setname_edittext.getText().toString().length() == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("이름 입력후 진행해 주세요.");
                    builder.setCancelable(false)
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else {
                    startActivity(intent);
                }
            }
        });

        top.findViewById(R.id.button_start_2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), GameActivity.class);
                intent.putExtra("Game_Difficulty", Difficulty_Hard);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                intent.putExtra("UserName",setname_edittext.getText().toString());
                if(setname_edittext.getText().toString().length() == 0) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setMessage("이름 입력후 진행해 주세요.");
                    builder.setCancelable(false)
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }
                else {
                    startActivity(intent);
                }
            }
        });

        return top;
    }

    @Override
    public void onResume() {
        super.onResume();
    }
}
