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
	
	private OnNewsLoadCompleted listener;
	ArrayList<RssItem> feedItems = null;
	
	public FeedLoaderTask(OnNewsLoadCompleted listener) {
		this.listener = listener;
	}
	
	@Override
	protected void onPreExecute() {
		listener.onStartLoad();
		super.onPreExecute();
	}


	protected Object doInBackground(String... url) {

		try {
			feedItems = RssItem.getRssItems(url[0]);
			
		} catch (Exception e) {
			
			Log.e("MHYHRE", "RetreiveFeedTask:" + e);
			
		}
		return null;
	}
	
	@Override
	protected void onPostExecute(Object obj) {
		
		listener.onEndLoad(feedItems);
		
		super.onPostExecute(obj);
	}
}