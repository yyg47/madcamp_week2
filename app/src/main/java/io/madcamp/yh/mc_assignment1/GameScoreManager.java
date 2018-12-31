package io.madcamp.yh.mc_assignment1;

import android.content.Context;

import org.json.*;

import java.io.*;
import java.util.ArrayList;

public class GameScoreManager {
    private static final String FILE_NAME = "scores.json";

    public static ArrayList<Integer> loadHiScores(Context context) {
        try {
            FileInputStream fis;
            InputStreamReader isr;
            BufferedReader br;
            StringBuilder sb;
            String line;
            fis = context.openFileInput(FILE_NAME);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            sb = new StringBuilder();
            while((line = br.readLine()) != null) {
                sb.append(line);
            }
            fis.close();

            String src = sb.toString();
            ArrayList<Integer> list = new ArrayList<>();
            JSONArray arr = new JSONArray(src);
            for(int i = 0; i < arr.length(); i++) {
                list.add(arr.getInt(i));
            }
            return list;
        } catch(IOException e) {
            return new ArrayList<>();
        } catch(JSONException e) {
            return new ArrayList<>();
        }
    }

    public static void saveHiScore(Context context, int level, int score) {
        ArrayList<Integer> scores = loadHiScores(context);
        while(level >= scores.size()) {
            scores.add(0);
        }
        if(scores.get(level) < score) {
            scores.set(level, score);
            JSONArray arr = new JSONArray();
            for(int i : scores) {
                arr.put(i);
            }
            String contents = arr.toString();
            FileOutputStream fos;
            try {
                fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
                fos.write(contents.getBytes());
                fos.close();
            } catch(IOException e) {
                e.printStackTrace();
            }
        }
    }
}
