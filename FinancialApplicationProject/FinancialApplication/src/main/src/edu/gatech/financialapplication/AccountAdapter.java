package edu.gatech.financialapplication;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AccountAdapter extends ArrayAdapter<Account> {
	private Context context;
	private int rowResourceId;
	private ArrayList<Account> objects;

	public AccountAdapter(Context context, int resource,
			ArrayList<Account> objects) {
		super(context, resource, objects);
		this.context = context;
		this.rowResourceId = resource;
		this.objects = objects;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(rowResourceId, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.textView);
		textView.setTextColor(Color.BLACK);

		textView.setText(objects.get(position).debug());
		// Log.e(objects.get(position).getName(),
		// objects.get(position).debug());

		return rowView;
	}

	@Override
	public int getCount() {
		return objects.size();
	}

	public int getPosition(Account item) {
		return objects.indexOf(item);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public Account getItem(int position) {
		return objects.get(position);
	}

	public boolean hasStableIds() {
		return true;
	}

}