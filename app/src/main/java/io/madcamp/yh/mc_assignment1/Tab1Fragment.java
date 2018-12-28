package io.madcamp.yh.mc_assignment1;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.json.*;

public class Tab1Fragment extends Fragment {
    public static final String ARG_PAGE = "ARG_PAGE";
    private int page;
    private Context context;
    private View top;

    private static final int PERMISSION_REQ_CODE = 1;

    private ArrayList<String> contacts;
    private ArrayAdapter<String> adapter;
    private ListView listView;

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

        /* -- 여기서부터 작성해주세요 -- */
        Button button = top.findViewById(R.id.button_sync);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryLoadContacts();
            }
        });

        ListView listView = (ListView)top.findViewById(R.id.list_view);
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                final long idx = id;
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Title");
                builder.setItems(new CharSequence[]{"삭제"},
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch(which) {
                                    case 0:
                                        Toast.makeText(context, "Delete " + idx, Toast.LENGTH_SHORT).show();
                                        contacts.remove((int)idx);
                                        Log.d("Deleted", contacts.toString());
                                        updateListView();
                                        break;
                                }
                            }
                        });
                builder.show();
                return false;
            }
        });

        loadContactsFromInternal();
        updateListView();

        return top;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        saveContactsToInternal();
    }

    private void tryLoadContacts() {
        if(ContextCompat.checkSelfPermission(context, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},
                    PERMISSION_REQ_CODE);
        } else {
            loadContacts();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch(requestCode) {
            case PERMISSION_REQ_CODE:
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    tryLoadContacts();
                } else {
                    Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void loadContacts() {
        contacts = new ArrayList<>();

        Cursor c = context.getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null,
                ContactsContract.Contacts.DISPLAY_NAME_PRIMARY + " asc");

        while(c.moveToNext()) {
            String id = c.getString(c.getColumnIndex(ContactsContract.Contacts._ID));
            String name = c.getString(c.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME));
            Cursor phoneCursor = context.getContentResolver().query(
                    ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                    null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = " + id,
                    null, null);
            if(phoneCursor.moveToFirst()) {
                String number = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                contacts.add(name + ": " + number);
            }
        }

        updateListView();

        packJSON();
    }

    private void updateListView() {
        adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1);
        if(contacts != null) {
            adapter.addAll(contacts);
        }
        ((ListView)top.findViewById(R.id.list_view)).setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    private void unpackJSON(String src) {
        try {
            JSONArray arr = new JSONArray(src);
            contacts = new ArrayList<>();
            for(int i = 0; i < arr.length(); i++) {
                contacts.add(arr.getString(i));
            }
        } catch(JSONException e) {
            Log.d("Exception", "JSON Parsing Failed");
        }
    }

    private String packJSON() {
        JSONArray array = new JSONArray();
        if(contacts != null) {
            for (String i : contacts) {
                array.put(i);
            }
        }
        String result = array.toString();
        Log.d("PACK JSON RESULT", result);
        return result;
    }

    final static String path = "contacts.json";

    private void saveContactsToInternal() {
        FileOutputStream fos;
        try {
            fos = context.openFileOutput(path, Context.MODE_PRIVATE);
            fos.write(packJSON().getBytes());
            fos.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    private void loadContactsFromInternal() {
        FileInputStream fis;
        InputStreamReader isr;
        BufferedReader br;
        StringBuilder sb;
        String line;
        try {
            fis = context.openFileInput(path);
            isr = new InputStreamReader(fis);
            br = new BufferedReader(isr);
            sb = new StringBuilder();
            while((line = br.readLine()) != null) {
                sb.append(line);
            }
            fis.close();
            String src = sb.toString();
            unpackJSON(src);
            updateListView();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
