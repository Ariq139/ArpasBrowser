package com.arpas.arpasbrowser.recyclers;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ImageView;

import java.io.Serializable;

public class HistoryList implements Serializable, Parcelable {
    // public Bitmap WebFavicon;
    public String WebName;
    public String WebLink;

    public HistoryList(/*Bitmap WebFavicon,*/ String WebName, String WebLink){
        // this.WebFavicon = WebFavicon;
        this.WebName = WebName;
        this.WebLink = WebLink;
    }

    protected HistoryList(Parcel in) {
        WebName = in.readString();
        WebLink = in.readString();
    }

    public static final Creator<HistoryList> CREATOR = new Creator<HistoryList>() {
        @Override
        public HistoryList createFromParcel(Parcel in) {
            return new HistoryList(in);
        }

        @Override
        public HistoryList[] newArray(int size) {
            return new HistoryList[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(WebName);
        parcel.writeString(WebLink);
    }
}
