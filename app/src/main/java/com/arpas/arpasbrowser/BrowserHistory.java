package com.arpas.arpasbrowser;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.arpas.arpasbrowser.recyclers.HistoryAdapter;
import com.arpas.arpasbrowser.recyclers.HistoryList;

import java.util.LinkedList;

public class BrowserHistory extends AppCompatActivity{

    private RecyclerView mRecyclerView;
    private HistoryAdapter mAdapter;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.history_layout);

        Intent intent = getIntent();
        LinkedList<HistoryList> mHistoryList = intent.getExtras("list");

        mRecyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        // Create an adapter and supply the data to be displayed.
        // mAdapter = new WordListAdapter(this, mWordList, mWord2List, mImageList);
        mAdapter = new HistoryAdapter(this, mHistoryList);
        // Connect the adapter with the recycler view.
        mRecyclerView.setAdapter(mAdapter);
        // Give the recycler view a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
