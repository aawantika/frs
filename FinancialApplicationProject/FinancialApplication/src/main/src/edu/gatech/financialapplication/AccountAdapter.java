package edu.gatech.financialapplication;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Account adapter to change from one account to another.
 * 
 * @author Team 15
 */
public class AccountAdapter extends ArrayAdapter<Account> {

	private Context context;
	private int rowResourceId;
	private List<Account> objects;
	private TextView textView;
	private View rowView;

	/**
	 * New account adapter.
	 * 
	 * @param aContext New context hides a field.
	 * @param resource Not sure what it does...
	 * @param newObjects ArrayList of accounts.
	 */
	public AccountAdapter(Context aContext, int resource,
			ArrayList<Account> newObjects) {
		super(aContext, resource, newObjects);
		this.context = aContext;
		this.rowResourceId = resource;
		this.objects = newObjects;
	}

	@Override
	public final View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		rowView = inflater.inflate(rowResourceId, parent, false);
		textView = (TextView) rowView.findViewById(R.id.textView);
		textView.setTextColor(Color.BLACK);
		textView.setText(objects.get(position).debug());
		
		if (position == 0) {
			textView.setBackgroundColor(Color.rgb(173, 211, 224));
		}
		return rowView;
	}

	@Override
	public final int getCount() {
		return objects.size();
	}

	@Override
	public final int getPosition(Account item) {
		return objects.indexOf(item);
	}

	@Override
	public final long getItemId(int position) {
		return position;
	}

	@Override
	public final Account getItem(int position) {
		return objects.get(position);
	}

	@Override
	public final boolean hasStableIds() {
		return true;
	}
}
