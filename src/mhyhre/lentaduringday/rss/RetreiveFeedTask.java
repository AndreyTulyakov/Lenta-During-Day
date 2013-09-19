package mhyhre.lentaduringday.rss;

import java.util.ArrayList;

import android.os.AsyncTask;
import android.util.Log;

public class RetreiveFeedTask extends AsyncTask<String, Void, ArrayList<RssItem>> {

	protected ArrayList<RssItem> doInBackground(String... url) {

		try {
			return RssItem.getRssItems(url[0]);
			
		} catch (Exception e) {
			Log.e("MHYHRE", "RetreiveFeedTask:" + e);
			return null;
		}
	}
}
