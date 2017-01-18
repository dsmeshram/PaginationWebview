package opensourcecode.com.paginationview;

import android.graphics.Bitmap;
import android.os.Build;
import android.util.Base64;
import android.webkit.ValueCallback;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by damodhar.meshram on 12/26/2016.
 */

public class PaginationClient extends WebViewClient {

    private PageChangedCallback callback;
    public PaginationClient(PageChangedCallback callback)
    {
        this.callback = callback;
    }

    @Override
    public void onPageStarted(WebView view, String url, Bitmap favicon) {
        super.onPageStarted(view, url, favicon);
    }

    @Override
    public void onPageFinished(WebView view, String url) {


        injectScriptFile(view,"pagination.js");

        if(callback != null){
            callback.onPageLoad();
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            view.evaluateJavascript("paginationmode()", new ValueCallback<String>() {
                @Override
                public void onReceiveValue(String s) {

                }
            });
        } else {
            view.loadUrl("javascript:paginationmode()");
        }



    }

    private void injectScriptFile(WebView view, String scriptFile) {
        InputStream input;
        try {
            input = view.getContext().getAssets().open(scriptFile);
            byte[] buffer = new byte[input.available()];
            input.read(buffer);
            input.close();

            // String-ify the script byte-array using BASE64 encoding !!!
            String encoded = Base64.encodeToString(buffer, Base64.DEFAULT);
            view.loadUrl("javascript:(function() {" +
                    "var parent = document.getElementsByTagName('head').item(0);" +
                    "var script = document.createElement('script');" +
                    "script.type = 'text/javascript';" +
                    // Tell the browser to BASE64-decode the string into your script !!!
                    "script.innerHTML = window.atob('" + encoded + "');" +
                    "parent.appendChild(script)" +
                    "})()");
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
