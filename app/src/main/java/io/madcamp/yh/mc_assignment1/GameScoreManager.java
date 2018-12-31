package io.madcamp.yh.mc_assignment1;

import android.content.Context;
import android.util.Log;

import org.json.*;

import java.io.*;
import java.util.ArrayList;

public class GameScoreManager {
    private static final String FILE_NAME = "scores.json";

    public static class ScoreSet {
        public String name;
        public int score;

        public ScoreSet(String name, int score) {
            this.name = name;
            this.score = score;
        }
    }

    public Context context;
    public String lastName = "";
    public ArrayList<ArrayList<ScoreSet>> list = new ArrayList<>();

    public GameScoreManager(Context context) {
        this.context = context;
        load();
    }

    public void load() {
        try {
            Log.d("Test@ScoreManager", "Loading");
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
            Log.d("Test@Contents", src);

            JSONObject obj = new JSONObject(src);
            lastName = obj.getString("lastName");
            JSONArray arr = obj.getJSONArray("scores");
            list = new ArrayList<>();
            for(int i = 0; i < arr.length(); i++) {
                JSONArray a = arr.getJSONArray(i);
                ArrayList<ScoreSet> l = new ArrayList<>();
                for(int j = 0; j < a.length(); j++) {
                    JSONObject o = a.getJSONObject(j);
                    ScoreSet set = new ScoreSet(o.getString("name"), o.getInt("score"));
                    l.add(set);
                }
                list.add(l);
            }
            Log.d("Test@ScoreManager", "Loaded");
        } catch(IOException e) {
            e.printStackTrace();
        } catch(JSONException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            Log.d("Test@ScoreManager", "Saving");
            JSONObject obj = new JSONObject();
            obj.put("lastName", lastName);
            JSONArray arr = new JSONArray();
            for(ArrayList<ScoreSet> i : list) {
                JSONArray a = new JSONArray();
                for(ScoreSet s : i) {
                    JSONObject o = new JSONObject();
                    o.put("name", s.name);
                    o.put("score", s.score);
                    a.put(o);
                }
                arr.put(a);
            }
            obj.put("scores", arr);

            String contents = obj.toString();
            Log.d("Test@Contents", contents);
            FileOutputStream fos;
            fos = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE);
            fos.write(contents.getBytes());
            fos.close();
            Log.d("Test@ScoreManager", "Saved");
        } catch(IOException e) {
            e.printStackTrace();
        } catch(JSONException e) {
            e.printStackTrace();
        }
    }

    public String getName(int level, int n) {
        if(list == null || level >= list.size() || n >= list.get(level).size())
            return null;
        return list.get(level).get(n).name;
    }

    public int getScore(int level, int n) {
        if(list == null || level >= list.size() || n >= list.get(level).size())
            return 0;
        return list.get(level).get(n).score;
    }

    public int guessRanking(int level, int score) {
        if(score <= 0) return 10;
        int i = 0;
        for(; i < 10; i++) {
            if(getScore(level, i) < score) break;
        }
        return i;
    }

    public void add(int level, String name, int score) {
        int r = guessRanking(level, score);
        if(r >= 10) return;
        while(level >= list.size())
            list.add(new ArrayList<ScoreSet>());
        list.get(level).add(r, new ScoreSet(name, score));
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < list.size(); i++) {
            sb.append(toString(i)).append("\n");
        }
        return sb.toString();
    }

    public String toString(int n) {
        StringBuilder sb = new StringBuilder();
        for(int i = 0; ; i++) {
            String name = getName(n, i);
            if(name == null) break;
            sb.append("#").append(Integer.toString(i + 1)).append(" ")
                    .append(name).append(" ")
                    .append(Integer.toString(getScore(n, i))).append("\n");
        }
        return sb.toString();
    }
}
