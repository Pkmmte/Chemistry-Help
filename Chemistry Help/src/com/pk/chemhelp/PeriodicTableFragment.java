package com.pk.chemhelp;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.actionbarsherlock.app.SherlockFragment;

public class PeriodicTableFragment extends SherlockFragment
{
	public WebView webView;
	LinearLayout ContentLoad;
	ProgressBar Progress;
	
	boolean ShowProgress;
	boolean ClickableElements;

	// the method to call from the html button click
	public void TestMethod()
	{
		Intent webIntent = new Intent(getActivity().getBaseContext(), Settings.class);
		webIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		startActivity(webIntent);
		webView.loadUrl("javascript:ClientSideMethod('activity method called');");
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View view = inflater.inflate(R.layout.periodictablefragment, container, false);
		
		webView = (WebView) view.findViewById(R.id.webview);
		ContentLoad = (LinearLayout) view.findViewById(R.id.contentL);
		Progress = (ProgressBar) view.findViewById(R.id.progressBar);
	    
		ShowProgress = MySingleton.getInstance().getPeriodicShowProgress();
		ClickableElements = MySingleton.getInstance().getPeriodicClickableElements();
		
	    Toast.makeText(getActivity().getBaseContext(), "Pinch to Zoom", Toast.LENGTH_SHORT).show();
		
	    if(ShowProgress)
	    {
	    	webView.setWebViewClient(new WebViewClient()
			{
				public void onPageStarted(WebView wview, String url, Bitmap favicon)
				{
					MySingleton.getInstance().addLog("Started listener...");
					ContentLoad.setVisibility(View.VISIBLE);
				}
				
				public void onPageFinished(WebView turtle, String url)
				{
					MySingleton.getInstance().addLog("Finished...");
					ContentLoad.setVisibility(View.GONE);
				}
			});
	    	
	    	webView.setWebChromeClient(new WebChromeClient()
			{
				public void onProgressChanged(WebView view, int progress)
				{
					int prgss = view.getProgress();
					MySingleton.getInstance().addLog("Progress: " + prgss);
					Progress.setProgress(prgss);
				}
			});
	    }

		// requires javascript
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setBuiltInZoomControls(true);
		webView.getSettings().setLoadWithOverviewMode(true);
		webView.getSettings().setUseWideViewPort(true);

		// Load...
		webView.loadUrl("file:///android_asset/web/index.html");
		
		// make this activity accessible to javascript
		webView.addJavascriptInterface(new WebAppInterface(getActivity().getBaseContext()), "activity");
		
		return view;
	}
	
	public class WebAppInterface
	{
		Context mContext;

		/** Instantiate the interface and set the context */
		WebAppInterface(Context c)
		{
			mContext = c;
		}

		/** Show a toast from the web page */
		@JavascriptInterface
		public void showToast(String toast)
		{
			Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
		}
		
		@JavascriptInterface
		public void showElement(String eposition)
		{
			if(ClickableElements)
			{
				int position = Integer.parseInt(eposition);
				Intent elementIntent = new Intent(mContext, ViewElement.class);
				elementIntent.putExtra("Element Position", position);
				startActivity(elementIntent);
			}
		}
	}
}
