package edu.gatech.financialapplication;

import java.util.ArrayList;
import java.util.List;

import org.afree.chart.AFreeChart;
import org.afree.chart.ChartFactory;
import org.afree.chart.demo.DemoView;
import org.afree.chart.plot.PiePlot;
import org.afree.data.general.DefaultPieDataset;
import org.afree.data.general.PieDataset;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;

public class CategoryChartActivity extends Activity {

	private float totalWithdrawals;
	private float gas, rent, clothing, business, groceries, entertainment;
	private String accountNumber, username, finalStart, finalEnd;
	private DBHelper db;
	private List<Transaction> spendingList, transactionList;
	private PieChart pieChart;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_category_chart);
		
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
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(pieChart);
		
	}
	
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
			totalWithdrawals += t.getAmount();
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
			return chart;
		}
	}
}
