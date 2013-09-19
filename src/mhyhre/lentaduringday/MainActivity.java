package mhyhre.lentaduringday;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import mhyhre.lentaduringday.rss.RetreiveFeedTask;
import mhyhre.lentaduringday.rss.RssItem;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class MainActivity extends Activity {

	private final String feedUrl = "http://lenta.ru/rss/last24";

	ListView mainList;
	
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
		
		mainList = (ListView) this.findViewById(R.id.FeedListView);
		mainList.setAdapter(adapter);
		
		mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			  @Override
			  public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {

				RssItem  item = (RssItem) mainList.getItemAtPosition(position);
				
				if(item == null){
					return;
				}
				
				Log.i("MHYHRE", "Opening Link:" + item.getLink());
				
				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getLink()));
				startActivity(browserIntent);
			  }
		});
	}

}
