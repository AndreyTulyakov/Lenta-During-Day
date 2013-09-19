package mhyhre.lentaduringday;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import mhyhre.lentaduringday.rss.RetreiveFeedTask;
import mhyhre.lentaduringday.rss.RssItem;

import android.os.Bundle;
import android.app.Activity;
import android.widget.ListView;

public class MainActivity extends Activity {

	private final String feedUrl = "http://lenta.ru/rss/last24";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		ArrayList<RssItem> newsFeed = null;

		RetreiveFeedTask feedTask = new RetreiveFeedTask();
		try {
			newsFeed = feedTask.execute(feedUrl).get();
		} catch (InterruptedException e) {
			e.printStackTrace();
			return;
		} catch (ExecutionException e) {
			e.printStackTrace();
			return;
		}

		LentaRssListAdapter adapter = new LentaRssListAdapter(this, newsFeed);
		
		ListView mainList = (ListView) this.findViewById(R.id.FeedListView);
		mainList.setAdapter(adapter);
	}

}
