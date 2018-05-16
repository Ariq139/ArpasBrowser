package com.arpas.arpasbrowser.recyclers;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.arpas.arpasbrowser.R;

import java.util.LinkedList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.HistoryHolder>  {

    private final LinkedList<HistoryList> mHistoryList;

    private final LayoutInflater mInflater;

    class HistoryHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public final TextView WebNameView;
        public final TextView WebLinkView;
        //public final Bitmap WebFaviconView;
        final HistoryAdapter mAdapter;

        public HistoryHolder(View itemView, HistoryAdapter adapter) {
            super(itemView);
            WebNameView =  itemView.findViewById(R.id.word);
            WebLinkView =  itemView.findViewById(R.id.word2);
            //WebFaviconView = itemView.findViewById(R.id.imageView);
            this.mAdapter = adapter;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {

        }
    }

    public HistoryAdapter(Context context, LinkedList<HistoryList> mHistoryList) {
        this.mHistoryList=mHistoryList;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public HistoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View mItemView = mInflater.inflate(R.layout.general_item, parent, false);
        return new HistoryHolder(mItemView, this);
    }

    @Override
    public void onBindViewHolder(HistoryHolder holder, int position) {
        // Retrieve the data for that position.
        HistoryList mCurrent = mHistoryList.get(position);
        // Add the data to the view holder.
        holder.WebNameView.setText(mCurrent.WebName);
        holder.WebLinkView.setText(mCurrent.WebLink);
        //holder.WebFaviconView.set(mCurrent.WebFavicon);
    }

    @Override
    public int getItemCount() {
        if (mHistoryList == null){
            return 0;
        }
        else{
            return mHistoryList.size();
        }
    }
}
