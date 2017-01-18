package opensourcecode.com.paginationview;

import android.util.Log;
import android.webkit.ConsoleMessage;
import android.webkit.WebChromeClient;

/**
 * Created by damodhar.meshram on 1/3/2017.
 */

public class PaginatinCromeClient extends WebChromeClient
{
    @Override
    public boolean onConsoleMessage(ConsoleMessage consoleMessage) {
        Log.i(PaginatinCromeClient.this.getClass().toString(),consoleMessage.message());
        return super.onConsoleMessage(consoleMessage);
    }
}
