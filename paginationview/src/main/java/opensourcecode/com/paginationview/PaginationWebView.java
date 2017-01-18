package opensourcecode.com.paginationview;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.ValueCallback;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Created by damodhar.meshram on 12/26/2016.
 */

public class PaginationWebView extends WebView implements PageChangedCallback
{

    private PageChangedCallback mPageChangedCallback;
    private PaginationClient mWebViewClient;
    private int mPageCount = 0;

    public enum RENDERINGMODE {
        NORMALMODE,PAGINATIONMODE
    }

    public PaginationWebView(Context context)
    {
        super(context);

        initOthers();
    }

    public int getPageCount()
    {
        return mPageCount;
    }

    public PaginationWebView(Context context, AttributeSet attrs) {
        super(context, attrs);

        initOthers();
    }

    private void initOthers()
    {
        mWebViewClient = new PaginationClient(PaginationWebView.this);
        this.setWebViewClient(mWebViewClient);
        this.setWebChromeClient(new PaginatinCromeClient());
        this.addJavascriptInterface(new JSBridge(),"pagination");

        getSettings().setDomStorageEnabled(true);
        getSettings().setJavaScriptEnabled(true);
        getSettings().setJavaScriptCanOpenWindowsAutomatically(true);
        getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
        getSettings().setBuiltInZoomControls(false);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            setLayerType(View.LAYER_TYPE_HARDWARE, null);
        }

        getSettings().setAllowFileAccess(true);
    }

    class JSBridge
    {
        @JavascriptInterface
        public void pageCount(int pagecount)
        {
            Log.i("page count ",""+pagecount);
            mPageCount = pagecount;
        }
    }


    public void moveNext()
    {

    }

    public void movePrevious()
    {

    }

    public void setCurrentPage(int index)
    {
        if(index > mPageCount)
        {

        }
        else
        {
            throw new IndexOutOfBoundsException();
        }
    }

    @Override
    public void onPageChanged(int pageno) {

    }

    @Override
    public void onPageLoad() {

    }

    public void setPageChangeCallBack(PageChangedCallback pagechangecallback)
    {
        this.mPageChangedCallback = pagechangecallback;
    }

    public void setMode(RENDERINGMODE paginationmode)
    {
        if(paginationmode == RENDERINGMODE.PAGINATIONMODE)
        {
            paginationmode();
        }
        else
        {
            defaultmode();
        }
    }

    private void paginationmode()
    {
        this.post(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    evaluateJavascript("paginationmode()", new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String s) {

                        }
                    });
                } else {
                    loadUrl("javascript:paginationmode()");
                }
            }
        });

    }

    private void defaultmode()
    {
        this.post(new Runnable() {
            @Override
            public void run() {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                    evaluateJavascript("defaultmode()", new ValueCallback<String>() {
                        @Override
                        public void onReceiveValue(String s) {

                        }
                    });
                } else {
                    loadUrl("javascript:defaultmode()");
                }
            }
        });
    }

}
