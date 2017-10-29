/*
 * This class used to show the video on Youtube which introduces the ULPA.
 */

package com.example.testing;
import android.app.Activity;
import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by CRK on 2017/10/26.
 */

public class more_about_ulpa extends Activity {

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.more_about_ulpa);

        WebView videoView = (WebView) findViewById(R.id.video_show);

        WebChromeClient mWebChromeClient = new WebChromeClient(){

            public void onProgressChanged(WebView view, int newProgress) {}};

        videoView.getSettings().setJavaScriptEnabled(true);

        videoView.setWebChromeClient(new WebChromeClient());

        //below html is the "embedded" information of the Youtube video not the URL link of the video on browser
        String html ="</body></html>Video show how ULPA working"+
                "<iframe  width=\"380\" height=\"200\" src=\"https://www.youtube.com/embed/EnPIjgQal_Y\" frameborder=\"0\" " +
                "allowfullscreen></iframe>" +
                "</body></html>";

        videoView.getSettings().setPluginState(WebSettings.PluginState.ON);

        videoView.getSettings().setJavaScriptEnabled(true);

        videoView.getSettings().setAllowFileAccess(true);

        videoView.setWebChromeClient(new WebChromeClient());

        videoView.loadData(html, "text/html", "utf-8");

    }
}



