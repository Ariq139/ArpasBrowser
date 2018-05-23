package com.arpas.arpasbrowser;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.arpas.arpasbrowser.recyclers.HistoryAdapter;
import com.arpas.arpasbrowser.recyclers.HistoryList;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

import im.delight.android.webview.AdvancedWebView;

import static com.arpas.arpasbrowser.MainActivity.PREF_NAME;

public class BrowserHistory extends AppCompatActivity implements java.io.Serializable{

    private RecyclerView mRecyclerView;
    private HistoryAdapter mAdapter;
    public LinkedList<HistoryList> mHistoryList;
    public ArrayList<HistoryList> mHistoryArrayList;
    public AdvancedWebView webview1;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_layout);
        webview1 = findViewById(R.id.webview1);
        if (ListInit()==false){
            mHistoryList = new LinkedList<>();
            mHistoryArrayList = new ArrayList<>(mHistoryList);
        }

        Intent intent = getIntent();
        //mHistoryList = (LinkedList<HistoryList>) intent.getSerializableExtra("list");

        mRecyclerView = findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed.
        // mAdapter = new WordListAdapter(this, mWordList, mWord2List, mImageList);
        mAdapter = new HistoryAdapter(this, mHistoryList);
        // Connect the adapter with the recycler view.
        mRecyclerView.setAdapter(mAdapter);
        // Give the recycler view a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onStart() {
        super.onStart();
    }
    @Override
    public void onRestart() {
        super.onRestart();
    }
    @Override
    public void onResume() {
        super.onResume();
        SharedPreferences preferences = getSharedPreferences(PREF_NAME, 0);

        String LoadedURL = preferences.getString("LoadedURL", "");
        HistoryList list = new HistoryList(webview1.getTitle(),LoadedURL);
        mHistoryList.addFirst(list);
        mHistoryArrayList.add(list);

    }
    @Override
    public void onPause() {
        super.onPause();
    }
    @Override
    public void onStop() {
        super.onStop();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putParcelableArrayList("key",mHistoryArrayList);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        savedInstanceState.getParcelableArrayList("key");
        mHistoryList = new LinkedList<>(mHistoryArrayList);
    }

    public boolean ListInit(){
        if(mHistoryList == null){
            return false;
        }
        else{
            return true;
        }
    }
}
