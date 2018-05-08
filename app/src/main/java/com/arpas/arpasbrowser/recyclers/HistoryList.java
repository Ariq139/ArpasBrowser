package com.arpas.arpasbrowser.recyclers;

import android.graphics.Bitmap;
import android.widget.ImageView;

public class HistoryList {
    // public Bitmap WebFavicon;
    public String WebName;
    public String WebLink;

    public HistoryList(/*Bitmap WebFavicon,*/ String WebName, String WebLink){
        // this.WebFavicon = WebFavicon;
        this.WebName = WebName;
        this.WebLink = WebLink;
    }
}
