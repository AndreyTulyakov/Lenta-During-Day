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
	
	private final Context context;
	
	private final ArrayList<RssItem> values;

	public LentaRssListAdapter(Context context, ArrayList<RssItem> values) {
		super(context, R.layout.rowlayout, values);
		this.context = context;
		this.values = values;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
		
		TextView titleView = (TextView) rowView.findViewById(R.id.textTitle);
		TextView textView = (TextView) rowView.findViewById(R.id.textContent);
		TextView dateView = (TextView) rowView.findViewById(R.id.textDate);
		TextView categoryView = (TextView) rowView.findViewById(R.id.textCategory);
		
		ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
		
		titleView.setText(values.get(position).getTitle());
		textView.setText(values.get(position).getDescription());
		categoryView.setText(values.get(position).getCategory());
		dateView.setText(values.get(position).getPubDate().toString());

		imageView.setImageBitmap(values.get(position).getImage());
		

		return rowView;
	}
}
