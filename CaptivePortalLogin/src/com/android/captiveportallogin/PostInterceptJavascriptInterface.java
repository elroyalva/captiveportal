package com.android.captiveportallogin;

import android.content.Context;
import android.util.Log;
import android.webkit.JavascriptInterface;

import org.jsoup.Jsoup;

import java.io.IOException;


public class PostInterceptJavascriptInterface {

	    private static final String TAG = "CaptivePortalLogin";
	    private static String mInterceptHeader = null;
            private CaptivePortalLoginActivity.MyWebViewClient myWebViewClient = null;

            public PostInterceptJavascriptInterface(CaptivePortalLoginActivity.MyWebViewClient webViewClient) {
                myWebViewClient = webViewClient;
            }

            public static String enableIntercept(Context context, byte[] data) throws IOException {
                if (mInterceptHeader == null) {
                    mInterceptHeader = new String(IOUtils.readFully(context.getAssets().open(
                            "www/interceptheader.html")));
                }

                Log.d(TAG, "got the JS code in var");
                org.jsoup.nodes.Document doc = Jsoup.parse(new String(data));
                doc.outputSettings().prettyPrint(true);

                // Prefix every script to capture submits
                // Make sure our interception is the first element in the
                // header
                org.jsoup.select.Elements el = doc.getElementsByTag("head");
                if (el.size() > 0) {
                    el.get(0).prepend(mInterceptHeader);
                }

                Log.d(TAG, "JS code added");
                String pageContents = doc.toString();
                return pageContents;
            }
/*
            public class FormRequestContents {
                public String method = "";
                public String json = "";
                public String enctype = "";

                public FormRequestContents(String method, String json, String enctype) {
                    this.method = method;
                    this.json = json;
                    this.enctype = enctype;
                }
            }
*/
	    @JavascriptInterface
	    public void customSubmit(String json, String method, String enctype) {
		Log.d(TAG, "Submit data: " + json + "\t" + method + "\t" + enctype);
		if(myWebViewClient == null) {
			Log.d(TAG,"myWebViewClient is null");
		}
		myWebViewClient.nextMessageIsFormRequest(json);
	    }
}
