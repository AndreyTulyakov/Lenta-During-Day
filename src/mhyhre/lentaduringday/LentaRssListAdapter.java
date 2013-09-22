package mhyhre.lentaduringday;

import java.util.ArrayList;
import mhyhre.lentaduringday.rss.RssItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LentaRssListAdapter extends ArrayAdapter<RssItem> {
	
	private final Context mContext;
	private final ArrayList<RssItem> mValues;

	public LentaRssListAdapter(Context context, ArrayList<RssItem> values) {
		super(context, R.layout.rss_row_layout, values);
		this.mContext = context;
		this.mValues = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(R.layout.rss_row_layout, parent, false);
		
		TextView titleView = (TextView) rowView.findViewById(R.id.textTitle);
		TextView textView = (TextView) rowView.findViewById(R.id.textContent);
		TextView dateView = (TextView) rowView.findViewById(R.id.textDate);
		TextView categoryView = (TextView) rowView.findViewById(R.id.textCategory);
		
		ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
		
		titleView.setText(mValues.get(position).getTitle());
		textView.setText(mValues.get(position).getDescription());
		categoryView.setText(mValues.get(position).getCategory());
		dateView.setText(mValues.get(position).getPubDate().toString());

		imageView.setImageBitmap(mValues.get(position).getImage());
		
		return rowView;
	}
}
