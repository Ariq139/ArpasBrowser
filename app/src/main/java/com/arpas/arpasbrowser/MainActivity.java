package com.arpas.arpasbrowser;

import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.webkit.WebHistoryItem;

import com.arpas.arpasbrowser.recyclers.HistoryList;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    //private final LinkedList<String> WebTitleList = new LinkedList<>();
    //private final LinkedList<Bitmap> WebIconList = new LinkedList<>();
    //private final LinkedList<String> WebURLList = new LinkedList<>();
    private final LinkedList<HistoryList> mHistoryList = new LinkedList<>();

    final WebHistoryItem item = new WebHistoryItem() {
        @Override
        public String getUrl() {
            return null;
        }

        @Override
        public String getOriginalUrl() {
            return null;
        }

        @Override
        public String getTitle() {
            return null;
        }

        @Nullable
        @Override
        public Bitmap getFavicon() {
            return null;
        }

        @Override
        protected WebHistoryItem clone() {
            return null;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        WebView webview1 = findViewById(R.id.webview1);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //webview section
        webview1.setWebViewClient(new WebViewClient());

        webview1.getSettings().setJavaScriptEnabled(true);
        webview1.setVerticalScrollBarEnabled(false);
//This statement hides the Vertical scroll bar and does not remove it.

        webview1.setHorizontalScrollBarEnabled(false);
//This statement hides the Horizontal scroll bar and does not remove it.

        webview1.loadUrl("https://www.duckduckgo.com/");


        /*Intent intent = getIntent();
        Uri uri = intent.getData();
        if (uri != null) {
            webview1.loadUrl(uri.toString());
        }
        else if (!uri.toString().startsWith("http://") || !uri.toString().startsWith("https://")) {
            webview1.loadUrl(uri.toString());
        }
        else*/
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_new) {

            return true;
        }
        else if (id == R.id.action_new_untrack) {

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onPageStarted(String url, String title) {//, Bitmap favicon) {

    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_downloads) {

        } else if (id == R.id.nav_history) {
            Intent intent = new Intent(MainActivity.this, BrowserHistory.class);
            intent.putExtra("list",mHistoryList);
            startActivity(intent);

        } else if (id == R.id.nav_manage) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
