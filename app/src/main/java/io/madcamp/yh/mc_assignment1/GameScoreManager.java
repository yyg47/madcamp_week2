package io.madcamp.yh.mc_assignment1;

import android.content.Context;
import android.util.Log;

import org.json.*;

import java.io.*;
import java.util.ArrayList;

public class GameScoreManager {
    /* 기본 파일 이름 */
    private static final String FILE_NAME = "scores.json";

    /* 이름-점수 Pair를 저장하는 클래스 */
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

    /* Context에서 FILE_NAME으로 파일을 찾아서 읽고 파싱하여 값을 전부 가져오는 함수
     * 만약 실패할 경우 불러오기를 중단하고 초기화합니다. */
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
            lastName = "";
            list = new ArrayList<>();
        } catch(JSONException e) {
            e.printStackTrace();
            lastName = "";
            list = new ArrayList<>();
        }
    }

    /* Context에서 FILE_NAME으로 파일을 찾아서 JSON Format으로 저장합니다. */

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

    /* 특정 레벨의 점수들을 가져옵니다. */
    public ArrayList<ScoreSet> getLevel(int level) {
        if(list == null || level >= list.size()) return new ArrayList<>();
        return list.get(level);
    }

    /* 특정 레벨에 몇 개의 기록이 저장되었는지 가져옵니다.
     * 만약 해당 레벨이 없으면 0이 반환됩니다. */
    public int getSize(int level) {
        if(list == null || level >= list.size()) return 0;
        return list.get(level).size();
    }

    /* 특정 레벨, 특정 등수의 기록의 이름을 가져옵니다.
     * 만약 해당 레벨 해당 등수의 기록이 없으면 null이 반환됩니다. */
    public String getName(int level, int n) {
        if(list == null || level >= list.size() || n >= list.get(level).size())
            return null;
        return list.get(level).get(n).name;
    }

    /* 특정 레벨, 특정 등수의 기록의 점수를 가져옵니다.
     * 만약 해당 레벨 해당 등수의 기록이 없으면 0이 반환됩니다. */
    public int getScore(int level, int n) {
        if(list == null || level >= list.size() || n >= list.get(level).size())
            return 0;
        return list.get(level).get(n).score;
    }

    /* 주어진 점수가 해당 레벨 기록 중 몇등으로 들어갈 수 있을지 추정합니다.
     * 만약 랭킹에 반영될 수가 없는 기록일 경우 -1이 반환됩니다. */
    public int guessRanking(int level, int score) {
        if(score <= 0) return -1;
        int i = 0;
        int n = getSize(level);
        /* Linear Probing */
        for(; i < n; i++) {
            if(getScore(level, i) < score) break;
        }
        return i;
    }

    /* 주어진 레벨의 기록에 주어진 이름과 점수를 추가합니다.
     * 만약 랭킹에 반영될 수가 없는 기록이라면 아무것도 하지 않습니다. */
    public void add(int level, String name, int score) {
        int r = guessRanking(level, score);
        if(r < 0) return;
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
