package edu.gatech.financialapplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import org.afree.chart.AFreeChart;
import org.afree.chart.ChartFactory;
import org.afree.chart.axis.NumberAxis;
import org.afree.chart.demo.DemoView;
import org.afree.chart.plot.PlotOrientation;
import org.afree.chart.plot.XYPlot;
import org.afree.data.xy.XYDataset;
import org.afree.data.xy.XYSeries;
import org.afree.data.xy.XYSeriesCollection;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;

public class WithdrawalGraphActivity extends Activity {

	private String username, accountNumber, finalStart, finalEnd;
	private List<Transaction> transactionList;
	private List<Transaction> allWithdrawals;
	private LineGraph lineGraph;
	private DBHelper db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_withdrawal_graph);
		db = new DBHelper(this);
		allWithdrawals = new ArrayList<Transaction>();

		// pull from intent
		accountNumber = getIntent().getStringExtra("accountNumber");
		username = getIntent().getStringExtra("username");
		finalStart = getIntent().getStringExtra("finalStart");
		finalEnd = getIntent().getStringExtra("finalEnd");
		
		lineGraph = new LineGraph(this);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(lineGraph);
	}

	private Map<String, Float> sortWithdrawals() {
		transactionList = db.getAllTransactionsByUsername(username);
		for (Transaction t : transactionList) {
			String date = t.getDate();
			if ((finalStart.compareTo(date) <= 0)
					&& (finalEnd.compareTo(date) >= 0)
					&& t.getAccount().equals(accountNumber)
					&& t.getType().equals("withdrawal")) {
						allWithdrawals.add(t);
			}
		}
		
		Map<String, Float> dates = new TreeMap<String, Float>();
		float amount;
		for (Transaction t : allWithdrawals) {
			if (dates.containsKey(t.getDate())) {
				amount = dates.get(t.getDate());
				dates.put(t.getDate(), amount + t.getAmount());
			} else {
				dates.put(t.getDate(), t.getAmount());
			}
		}
		return dates;
	}

	private class LineGraph extends DemoView {

		public LineGraph(Context context) {
			super(context);
			
			final XYSeriesCollection dataset = getDataSet();
	        final AFreeChart chart = createChart(dataset);

	        setChart(chart);
			
		}

		private XYSeriesCollection getDataSet() {
			XYSeriesCollection data = new XYSeriesCollection();
			XYSeries withdrawals = new XYSeries("Withdrawals");
			
			Map<String, Float> withdrawalData = new TreeMap<String, Float>();
			Set<String> dates = withdrawalData.keySet();
			withdrawalData.put("04/06/2014", 200.00f);
			withdrawalData.put("04/08/2014", 400.00f);
			withdrawalData.put("04/10/2014", 800.00f);
			withdrawalData.put("04/11/2014", 400.00f);
			withdrawalData.put("04/12/2014", 200.00f);
			
			for (String date : dates) {
				double withdrawalAmount = withdrawalData.get(date);
				date = date.replaceAll("/", "");
				double dateValue = Double.parseDouble(date);
				withdrawals.add(dateValue, withdrawalAmount);
			}
			
			data.addSeries(withdrawals);


			return data;
		}
		
		public AFreeChart createChart(XYSeriesCollection data) {
			XYDataset dataset = data;
			AFreeChart chart = ChartFactory.createXYLineChart(
					"Transaction History", // Title
					"Date", // x-axis Label
					"Amount", // y-axis Label
					dataset, // Dataset
					PlotOrientation.VERTICAL, // Orientation
					false, // Don't show Legend
					false, // Don't use tooltips
					false // Don't generate URLs
					);

			XYPlot plot = (XYPlot) chart.getPlot();
			
			plot.setDomainZeroBaselineVisible(true);
			plot.setRangeZeroBaselineVisible(true);
			
			// change the tick unit
			final NumberAxis rangeAxis = (NumberAxis) plot.getRangeAxis();
			rangeAxis.setStandardTickUnits(NumberAxis.createIntegerTickUnits());
			
			//color stuff
			plot.setBackgroundAlpha(Color.CYAN);
			//plot.setDomainGridlineEffect(Color.WHITE);
			//plot.setRangeGridlinePaint(Color.WHITE);

			//final XYLineAndShapeRenderer renderer = new XYLineAndShapeRenderer();
			//renderer.setSeriesLinesVisible(0, false);
			//renderer.setSeriesShapesVisible(1, false);
			//plot.setRenderer(renderer);
			return chart;


		}

	}

	// get the dates
	// get the withdrawal amounts
	// use map
	// show graph

	/*
	 * 
	 * /** Populates a list with transactions that are within date range.
	 */
	/*
	 * private void getProperDeposits() { transactionList =
	 * db.getAllTransactionsByUsername(username); for (Transaction t :
	 * transactionList) { Log.i("MONTH ", t.getDate()); String date =
	 * t.getDate(); if ((finalStart.compareTo(date) <= 0) &&
	 * (finalEnd.compareTo(date) >= 0) && t.getType().equals("deposit")) {
	 * 
	 * Log.i("transaction in cs", t.toString()); withinDates.add(t);
	 * totalDeposits += t.getAmount(); } } }
	 * 
	 * /** On click to go back to the general menu.
	 * 
	 * @param view The view being used.
	 */
	/*
	 * public void onTransactionClick(View view) { Intent intent = new
	 * Intent(this, TransactionActivity.class); Bundle bundle = new Bundle();
	 * bundle.putString("accountNumber", accountNumber);
	 * bundle.putString("username", username); intent.putExtras(bundle);
	 * startActivity(intent); }
	 */

}
