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
 * 
 */
public class AccountAdapter extends ArrayAdapter<Account> {

    /**
     * New context.
     */
    private Context context;
    /**
     * New int.
     */
    private int rowResourceId;
    /**
     * New arraylist of accounts.
     */
    private List<Account> objects;
    /**
     * New textview textview.
     */
    private TextView textView;
    /**
     * New view rowview.
     */
    private View rowView; 

    /**
     * New account adapter.
     * 
     * @param aContext  New context hides a field.
     * @param resource 
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
        // Log.e(objects.get(position).getName(),
        // objects.get(position).debug());

        return rowView;
    }

    @Override
    public final int getCount() {
        return objects.size();
    }

    @Override
    public final int getPosition(final Account item) {
        return objects.indexOf(item);
    }

    @Override
    public final long getItemId(final int position) {
        return position;
    }

    @Override
    public final Account getItem(final int position) {
        return objects.get(position);
    }

    @Override
    public final boolean hasStableIds() {
        return true;
    }
}
