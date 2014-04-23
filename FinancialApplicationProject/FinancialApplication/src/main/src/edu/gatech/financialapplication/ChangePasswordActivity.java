package edu.gatech.financialapplication;

import java.util.ArrayList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ChangePasswordActivity extends Activity {

	private ArrayList<User> users;
	private ArrayAdapter<User> adapter;
	private DBHelper db;
	private ListView listview;
	private String username;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_change_password);
		listview = (ListView) findViewById(R.id.changePasswordLV);
		db = new DBHelper(this);

		users = db.getAllUsers();
		System.out.println("USERS: " + users);
		adapter = new UserAdapter(this, R.layout.user_row, users);
		listview.setAdapter(adapter);
		adapter.notifyDataSetChanged();

		if (users.size() > 0) {
			listview.setSelection(0);
			username = users.get(0).getUsername();

			listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				public void onItemClick(AdapterView<?> parentAdapter,
						View view, int position, long id) {

					if (position != 0) {
						View rowView = listview.getChildAt(0);
						TextView textView = (TextView) rowView
								.findViewById(R.id.textView);
						textView.setBackgroundColor(Color.rgb(227, 236, 239));
					} else {
						View rowView = listview.getChildAt(0);
						TextView textView = (TextView) rowView
								.findViewById(R.id.textView);
						textView.setBackgroundColor(Color.rgb(173, 211, 224));
					}

					view.setSelected(true);
					User userFromClick = users.get(position);
					username = userFromClick.getUsername();
				}
			});
		}

	}

	public void onPasswordClick(View view) {
		if (users.size() == 0) {
			new AlertDialog.Builder(this)
					.setTitle("Password Error.")
					.setMessage("There are no users to select from!")
					.setPositiveButton(android.R.string.ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
								}
							}).show();
		} else {
			Intent intent = new Intent(this, NewPasswordActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("username", username);
			intent.putExtras(bundle);
			startActivity(intent);
		}
	}

	public void onBackClick(View view) {
		Intent intent = new Intent(this, WelcomeActivity.class);
		startActivity(intent);
	}
}
