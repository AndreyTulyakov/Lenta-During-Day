package mhyhre.lentaduringday;

import java.util.ArrayList;
import mhyhre.lentaduringday.rss.RssItem;

import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends Activity implements
		FeedLoaderTask.OnNewsLoadCompleted {

	private final String feedUrl = "http://lenta.ru/rss/last24";

	ListView mainList;
	ProgressBar loadingProgressBar;
	ArrayList<RssItem> newsFeed = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);

		loadingProgressBar = (ProgressBar) this
				.findViewById(R.id.LoadingProgressBar);

		loadNews();

	}

	private void loadNews() {

		FeedLoaderTask feedTask = new FeedLoaderTask(this);

		feedTask.execute(feedUrl);

	}

	private void updateNewsList() {

		loadingProgressBar.setVisibility(View.GONE);

		if (newsFeed == null) {
			Toast.makeText(getApplicationContext(), "Can't load information!",
					Toast.LENGTH_LONG).show();
			return;
		}

		// Create and fill adapter

		LentaRssListAdapter adapter = new LentaRssListAdapter(this,newsFeed);

		// Update list view by new news

		mainList = (ListView) findViewById(R.id.FeedListView);
		mainList.setAdapter(adapter);
		mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long arg3) {

				RssItem item = (RssItem) mainList.getItemAtPosition(position);

				if (item == null) {
					return;
				}

				Log.i("MHYHRE", "Opening Link:" + item.getLink());

				Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri
						.parse(item.getLink()));
				startActivity(browserIntent);
			}
		});

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater menuInflater = getMenuInflater();
		menuInflater.inflate(R.layout.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.menu_update:

			loadNews();
			return true;

		default:
			return super.onOptionsItemSelected(item);
		}
	}

	@Override
	public void onStartLoad() {
		loadingProgressBar.setVisibility(View.VISIBLE);

	}

	@Override
	public void onEndLoad(ArrayList<RssItem> items) {
		newsFeed = items;
		updateNewsList();
	}

}
