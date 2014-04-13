package edu.gatech.financialapplication;

import java.util.List;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

/**
 * Transaction history adapter that displays all transactions for an account.
 * 
 * @author Team 15
 */
public class TransactionHistoryAdapter extends ArrayAdapter<Transaction> {

	private Context context;
	private int rowResourceId;
	private List<Transaction> objects;
	private TextView textView;
	private View rowView;

	/**
	 * New account adapter.
	 * 
	 * @param aContext New context hides a field.
	 * @param resource Not sure what it does...
	 * @param newObjects ArrayList of accounts.
	 */
	public TransactionHistoryAdapter(Context aContext, int resource,
			List<Transaction> newObjects) {
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
		//textView.setTextColor(Color.BLACK);
		textView.setText(objects.get(position).debugHistory());
		
		if (objects.get(position).getType().equals("withdrawal")) {
			textView.setTextColor(Color.RED);
		} else if (objects.get(position).getType().equals("deposit")) {
			textView.setTextColor(Color.rgb(34,139,34));
		}
		return rowView;
	}

	@Override
	public final int getCount() {
		return objects.size();
	}

	@Override
	public final int getPosition(Transaction item) {
		return objects.indexOf(item);
	}

	@Override
	public final long getItemId(int position) {
		return position;
	}

	@Override
	public final Transaction getItem(int position) {
		return objects.get(position);
	}

	@Override
	public final boolean hasStableIds() {
		return true;
	}
}
