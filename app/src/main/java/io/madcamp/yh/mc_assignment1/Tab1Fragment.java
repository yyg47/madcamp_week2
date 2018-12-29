package io.madcamp.yh.mc_assignment1;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ListView;

import org.json.*;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class Tab1Fragment extends Fragment {
    /* --- Constants --- */
    public static final String ARG_PAGE = "ARG_PAGE";
    public static final int REQUEST_CODE = 524;


    /* --- Member Variables --- */
    private int page;
    private Context context;
    private View top;

    private ListViewAdapter adapter;

    /* --- Header --- */
    /* TabPagerAdapter에서 Fragment 생성할 때 사용하는 메소드 */
    public static Tab1Fragment newInstance(int page) {
        Bundle args = new Bundle();
        args.putInt(ARG_PAGE, page);
        Tab1Fragment fragment = new Tab1Fragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        page = getArguments().getInt(ARG_PAGE);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        top = inflater.inflate(R.layout.fragment_tab1, container, false);
        this.context = top.getContext();

        initializeFloatingActionButton();

        ListView contact_listview = (ListView)top.findViewById(R.id.contact_listview);
        adapter = new ListViewAdapter(context,R.layout.item_text2, contacts);
        contact_listview.setAdapter(adapter);

        return top;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }



    /* --- Floating Action Button --- */
    // isFabOpen: contains whether FloatingActionButton is opened(true) or not(false)
    private boolean isFabOpen = false;
    public void initializeFloatingActionButton() {
        final FloatingActionButton[] fab = new FloatingActionButton[4];

        /* Find every floating action buttons */
        fab[0] = (FloatingActionButton)top.findViewById(R.id.fab);
        fab[1] = (FloatingActionButton)top.findViewById(R.id.fab1);
        fab[2] = (FloatingActionButton)top.findViewById(R.id.fab2);
        fab[3] = (FloatingActionButton)top.findViewById(R.id.fab3);

        /* Initialize isFabOpen as Closed */
        isFabOpen = false;

        /* Add onClickListener for every floating action buttons */
        View.OnClickListener onClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int id = v.getId();
                switch(id) {
                    case R.id.fab: /* Menu button */
                        anim();
                        break;
                    case R.id.fab1: /* Add button */
                        Intent intent = new Intent(context.getApplicationContext(), AddcontactActivity.class);
                        startActivityForResult(intent, REQUEST_CODE);
                        break;
                    case R.id.fab2: /* Load from contacts button */


                        break;
                    case R.id.fab3: /* Load from/save as JSON button */


                        break;
                }
            }

            /* Helper method for animation */
            private void anim() {
                if(isFabOpen) {
                    for(int i = 1; i < fab.length; i++) {
                        Animation a = AnimationUtils.loadAnimation(context, R.anim.fab_close);
                        a.setStartOffset((fab.length - i - 1) * 50);
                        fab[i].startAnimation(a);
                        fab[i].setClickable(false);
                    }
                    isFabOpen = false;
                } else {
                    for(int i = 1; i < fab.length; i++) {
                        Animation a = AnimationUtils.loadAnimation(context, R.anim.fab_open);
                        a.setStartOffset((i - 1) * 50);
                        fab[i].startAnimation(a);
                        fab[i].setClickable(true);
                    }
                    isFabOpen = true;
                }
            }
        };

        for(int i = 0; i < 4; i++) {
            fab[i].setOnClickListener(onClickListener);
        }
    }


    /* --- ListView --- */

    ArrayList<Pair<String, String>> contacts = new ArrayList<>();
    @Override
    public void onActivityResult(int requestCode,int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        switch(requestCode){
            case(REQUEST_CODE):{
                if(resultCode==AddcontactActivity.RESULT_OK){
                    String contact_name = data.getStringExtra("contact_name");
                    String contact_num = data.getStringExtra("contact_num");
                    Pair<String, String> pair = new Pair<String, String>(contact_name, contact_num);
                    Log.d("Test@", "Pair Created 1st=" + contact_name + ", 2nd=" + contact_num);
                    contacts.add(pair);
                    adapter.notifyDataSetChanged();
                }
            }



        }

    }



    /* --- Utility Methods --- */
    /* JSON Parser */
    private String packIntoJSON(ArrayList<Pair<String, String>> arrayList) {
        try {
            JSONArray array = new JSONArray();
            for(Pair<String, String> p : arrayList) {
                JSONObject item = new JSONObject();
                item.put("name", p.first);
                item.put("number", p.second);
                array.put(item);
            }
            return array.toString();
        } catch(JSONException e) {
            return "";
        }
    }

    private ArrayList<Pair<String, String>> unpackFromJSON(String src) {
        return appendFromJSON(new ArrayList<Pair<String, String>>(), src);
    }

    private ArrayList<Pair<String, String>> appendFromJSON(ArrayList<Pair<String, String>> arrayList, String src) {
        try {
            JSONArray array = new JSONArray(src);
            for(int i = 0; i < array.length(); i++) {
                JSONObject item = array.getJSONObject(i);
                Pair<String, String> p = new Pair<>(item.getString("name"), item.getString("number"));
                arrayList.add(p);
            }
        } catch(JSONException e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    /* File Helper */
    private String readInternalFile(String filename) {
        FileInputStream fis;
        InputStreamReader isr;
        BufferedReader br;
        StringBuilder sb;
        String line;
        try {
            fis = context.openFileInput(filename);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            sb = new StringBuilder();
            while((line = br.readLine()) != null) {
                sb.append(line);
            }
            fis.close();
            return sb.toString();
        } catch(Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private void writeInternalFile(String filename, String contents) {
        FileOutputStream fos;
        try {
            fos = context.openFileOutput(filename, Context.MODE_PRIVATE);
            fos.write(contents.getBytes());
            fos.close();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
