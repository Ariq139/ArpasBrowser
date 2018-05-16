package com.arpas.arpasbrowser;

// https://github.com/haojian/MobileBrowser/tree/master/src/com/android/erowser
// https://www.programcreek.com/java-api-examples/?code=aabognah/LoRaWAN-Smart-Parking
// /LoRaWAN-Smart-Parking-master/MobileApp/platforms/android/CordovaLib/src/org/apache/cordova/CordovaWebView.java

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebViewClient;
import android.webkit.WebHistoryItem;

import com.arpas.arpasbrowser.recyclers.HistoryList;

import java.util.LinkedList;

import im.delight.android.webview.AdvancedWebView;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdvancedWebView.Listener{
    private final LinkedList<HistoryList> mHistoryList = new LinkedList<HistoryList>();
    private AdvancedWebView webview1;

    String loadedUrl; // for loadUrl
    int loadUrlTimeout = 0;

    // private BrowserHistory BHis;
    // public WebHistoryItem WHItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navview_main);

        //webview section
        webview1 = findViewById(R.id.webview1);
        webview1.setListener(this, this);
        webview1.setWebViewClient(new WebViewClient());
        webview1.getSettings().setJavaScriptEnabled(true);
        webview1.setVerticalScrollBarEnabled(false); //hide vertical scrollbar
        webview1.setHorizontalScrollBarEnabled(false); //hide horizontal scrollbar
        if (webview1.getUrl()==null || webview1.getUrl()=="about:blank"){
            webview1.loadUrl("https://www.duckduckgo.com");
        }
        else {
            webview1.loadUrl(webview1.getUrl());
        }

        /* for fragment

        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my, container, false);

        mWebView = (AdvancedWebView) rootView.findViewById(R.id.webview);
        mWebView.setListener(this, this);
        // mWebView.setListener(getActivity(), this); // for fragment v4
        mWebView.loadUrl("http://www.example.org/");

        // ...

        return rootView;
        }   */

        /* to fragment activity
            @Override
            public void onActivityResult(int requestCode, int resultCode, Intent intent) {
                super.onActivityResult(requestCode, resultCode, intent);
                if (mFragment != null) {
                     mFragment.onActivityResult(requestCode, resultCode, intent);
                }
            }
        */


        /*Intent intent = getIntent();
        Uri uri = intent.getData();
        if (uri != null) {
            webview1.loadUrl(uri.toString());
        }
        else if (!uri.toString().startsWith("http://") || !uri.toString().startsWith("https://")) {
            webview1.loadUrl(uri.toString());
        }
        else*/


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }
    /*
    public void loadUrl(String url) {
        if (url.equals("about:blank") || url.startsWith("javascript:")) {
            this.loadUrlNow(url);
        }
        else {
            this.loadUrlIntoView(url);
        }
    }

    @Deprecated
    public void loadUrl(final String url, int time) {
        if(url == null)
        {
            this.loadUrlIntoView("https://www.duckduckgo.com");
        }
        else
        {
            this.loadUrlIntoView(url);
        }
    }

    public void loadUrlIntoView(final String url) {
        loadUrlIntoView(url, true);
    }


    public void loadUrlIntoView(final String url, boolean recreatePlugins) {


        // Create a timeout timer for loadUrl
        final MainActivity me = this;
        final int currentLoadUrlTimeout = me.loadUrlTimeout;
        final int loadUrlTimeoutValue = Integer.parseInt(this.getProperty("LoadUrlTimeoutValue", "20000"));

        // Timeout error method
        final Runnable loadError = new Runnable() {
            public void run() {
                me.stopLoading();
                LOG.e(TAG, "CordovaWebView: TIMEOUT ERROR!");
                if (viewClient != null) {
                    viewClient.onReceivedError(me, -6, "The connection to the server was unsuccessful.", url);
                }
            }
        };

        // Timeout timer method
        final Runnable timeoutCheck = new Runnable() {
            public void run() {
                try {
                    synchronized (this) {
                        wait(loadUrlTimeoutValue);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                // If timeout, then stop loading and handle error
                if (me.loadUrlTimeout == currentLoadUrlTimeout) {
                    me.cordova.getActivity().runOnUiThread(loadError);
                }
            }
        };

        // Load url
        this.cordova.getActivity().runOnUiThread(new Runnable() {
            public void run() {
                cordova.getThreadPool().execute(timeoutCheck);
                me.loadUrlNow(url);
            }
        });
    }

    void loadUrlNow(String url) {
        if (LOG.isLoggable(LOG.DEBUG) && !url.startsWith("javascript:")) {
            LOG.d(TAG, ">>> loadUrlNow()");
        }
        if (url.startsWith("file://") || url.startsWith("javascript:") || url.startsWith("about:") || internalWhitelist.isUrlWhiteListed(url)) {
            super.loadUrl(url);
        }
    }

    public void loadUrlIntoView(final String url, final int time) {

        // If not first page of app, then load immediately
        // Add support for browser history if we use it.
        if ((url.startsWith("javascript:")) || this.canGoBack()) {
        }

        // If first page, then show splashscreen
        else {

            LOG.d(TAG, "loadUrlIntoView(%s, %d)", url, time);
        }

        // Load url
        this.loadUrlIntoView(url);
    }

    public void stopLoading() {
        viewClient.isCurrentlyLoading = false;
        super.stopLoading();
    }*/



    //ADVANCEDWEBVIEW OVERRIDE - BEGIN//
    @SuppressLint("NewApi")
    @Override
    protected void onResume() {
        super.onResume();
        webview1.onResume();
        // ...
    }

    @SuppressLint("NewApi")
    @Override
    protected void onPause() {
        webview1.onPause();
        // ...
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        webview1.onDestroy();
        // ...
        super.onDestroy();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        super.onActivityResult(requestCode, resultCode, intent);
        webview1.onActivityResult(requestCode, resultCode, intent);
        // ...
    }

    @Override
    public void onPageStarted(String url, Bitmap favicon) {
        HistoryList list = new HistoryList("Title",url);
        mHistoryList.addFirst(list);
        // Toast.makeText(this, "TEST", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onPageFinished(String url) {

    }

    @Override
    public void onPageError(int errorCode, String description, String failingUrl) {

    }

    @Override
    public void onDownloadRequested(String url, String suggestedFilename, String mimeType, long contentLength, String contentDisposition, String userAgent) {

    }

    @Override
    public void onExternalPageRequest(String url) {

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START); //close drawer
        }
        else if (!webview1.onBackPressed()) {
            return;
        }
        else {
            super.onBackPressed();
        }
    }

    //ADVANCEDWEBVIEW OVERRIDE - END//

    //NAV - BEGIN//
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.actionbar_main, menu);
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
        else if (id == R.id.action_history) {
            Intent intent = new Intent(MainActivity.this, BrowserHistory.class);
            //intent.putExtra("list", mHistoryList);
            startActivity(intent);
            return true;
        }
        else if (id == R.id.action_downloads) {
            Intent intent = new Intent(MainActivity.this, BrowserDownload.class);
            //intent.putExtra("list", mHistoryList);
            startActivity(intent);
        }
        else if (id == R.id.action_history) {
            Intent intent = new Intent(MainActivity.this, BrowserHistory.class);
            //intent.putExtra("list", mHistoryList);
            startActivity(intent);

        }
        else if (id == R.id.action_manage) {
            Intent intent = new Intent(MainActivity.this, BrowserSetting.class);
            //intent.putExtra("list", mHistoryList);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.action_downloads) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    //NAV - END//
}
