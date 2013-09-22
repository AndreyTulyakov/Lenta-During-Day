package mhyhre.lentaduringday;

import java.util.ArrayList;

import mhyhre.lentaduringday.rss.RssItem;

import android.os.AsyncTask;
import android.util.Log;

public class FeedLoaderTask extends AsyncTask<String, Void, Object> {
	
	public interface OnNewsLoadCompleted{
	    void onEndLoad(ArrayList<RssItem> items);
	    void onStartLoad();
	} 
	
	private OnNewsLoadCompleted mListener;
	ArrayList<RssItem> mFeedList = null;
	
	public FeedLoaderTask(OnNewsLoadCompleted listener) {
		this.mListener = listener;
	}
	
	@Override
	protected void onPreExecute() {
		mListener.onStartLoad();
		super.onPreExecute();
	}

	protected Object doInBackground(String... url) {

		try {
			mFeedList = RssItem.getRssItems(url[0]);
			
		} catch (Exception e) {			
			Log.e("MHYHRE", "RetreiveFeedTask:" + e);			
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(Object obj) {
		
		mListener.onEndLoad(mFeedList);		
		super.onPostExecute(obj);
	}
}