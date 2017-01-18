package opensourcecode.com.paginationwebview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import opensourcecode.com.paginationview.PaginationWebView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ((PaginationWebView)findViewById(R.id.paginationview)).loadUrl("https://quip.com/I6ZxAC12h07a");
        //((PaginationWebView)findViewById(R.id.paginationview)).setPaginationMode(true);
    }
}
