package edu.gatech.financialapplication;

import java.util.ArrayList;
import java.util.List;

import org.afree.chart.AFreeChart;
import org.afree.chart.ChartFactory;
import org.afree.chart.demo.DemoView;
import org.afree.chart.plot.PiePlot;
import org.afree.data.general.DefaultPieDataset;
import org.afree.data.general.PieDataset;
import org.afree.graphics.SolidColor;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class CategoryChartActivity extends Activity {

	private float gas, rent, clothing, business, groceries, entertainment;
	private String accountNumber, username, finalStart, finalEnd;
	private DBHelper db;
	private List<Transaction> spendingList, transactionList;
	private Context context;
	private PieChart pieChart;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category_chart);
		context = this;
		db = new DBHelper(this);
		spendingList = new ArrayList<Transaction>();
		
		// pull from intent
		accountNumber = getIntent().getStringExtra("accountNumber");
		username = getIntent().getStringExtra("username");
		finalStart = getIntent().getStringExtra("finalStart");
		finalEnd = getIntent().getStringExtra("finalEnd");
		
		populateCorrectList();
		populateCashCategories();

		pieChart = new PieChart(this);
		Button btn = new Button(this);
		btn.setGravity(Gravity.CENTER);
		btn.setText("Transactions");
		btn.setOnClickListener(myhandler);
		
		LinearLayout ll = new LinearLayout(this);
	    ll.setOrientation(LinearLayout.VERTICAL);
	    ll.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
	    ll.setGravity(Gravity.CENTER);
	    ll.addView(btn);
	    ll.addView(pieChart);
	    setContentView(ll);
	}
	
	View.OnClickListener myhandler = new View.OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent(context, TransactionActivity.class);
			Bundle bundle = new Bundle();
			bundle.putString("accountNumber", accountNumber);
			bundle.putString("username", username);
			intent.putExtras(bundle);
			startActivity(intent);
		}
	};
	
	/**
	 * Populates a list with transactions that are within date range.
	 */
	private void populateCorrectList() {
		transactionList = db.getAllTransactionsByUsername(username);
		for (Transaction t : transactionList) {
			Log.i("MONTH ", t.getDate());
			String date = t.getDate();
			if ((finalStart.compareTo(date) <= 0)
					&& (finalEnd.compareTo(date) >= 0)
					&& t.getType().equals("withdrawal")) {
				spendingList.add(t);
			}
		}
	}
	
	/**
	 * Fills in money buckets with corresponding transaction values.
	 */
	private void populateCashCategories() {
		for (Transaction t : spendingList) {
			if (t.getCategory().equals("Gas")) {
				gas += t.getAmount();
			} else if (t.getCategory().equals("Rent")) {
				rent += t.getAmount();
			} else if (t.getCategory().equals("Clothing")) {
				clothing += t.getAmount();
			} else if (t.getCategory().equals("Business")) {
				business += t.getAmount();
			} else if (t.getCategory().equals("Groceries")) {
				groceries += t.getAmount();
			} else if (t.getCategory().equals("Entertainment")) {
				entertainment += t.getAmount();
			}
		}
	}
	
	/**
	 * Creates the pie chart of all the spending in each category.
	 *
	 */
	private class PieChart extends DemoView {
		public PieChart(Context context) {
			super(context);
			
			final PieDataset dataset = createDataSet();
	        final AFreeChart chart = createChart(dataset);

	        setChart(chart);
		}
		
		private PieDataset createDataSet() {
			DefaultPieDataset data = new DefaultPieDataset();
			data.setValue("Gas", gas);
			data.setValue("Rent", rent);
			data.setValue("Clothing", clothing);
			data.setValue("Business", business);
			data.setValue("Groceries", groceries);
			data.setValue("Entertainment", entertainment);
			return data;
		}
		
		private AFreeChart createChart(PieDataset data) {
			AFreeChart chart = ChartFactory.createPieChart(
					"Spending per Category", 
					data, 
					false,
					false,
					false);
			PiePlot plot = (PiePlot) chart.getPlot();
			plot.setSectionPaintType("Gas", new SolidColor(Color.rgb(112, 146, 190)));
			plot.setSectionPaintType("Rent", new SolidColor(Color.rgb(57, 131, 204)));
			plot.setSectionPaintType("Clothing", new SolidColor(Color.rgb(146, 188, 186)));
			plot.setSectionPaintType("Business", new SolidColor(Color.rgb(60, 185, 185)));
			plot.setSectionPaintType("Groceries", new SolidColor(Color.rgb(79, 116, 153)));
			plot.setSectionPaintType("Entertainment", new SolidColor(Color.rgb(171, 173, 239)));
			
			return chart;
		}
	}
}
