package edu.gatech.financialapplication;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class WithdrawalGraphActivity extends Activity {

	private String username, accountNumber, finalStart, finalEnd;
	private List<Transaction> transactionList;
	private List<Transaction> allWithdrawals;
	private Map<String, Float> withdrawalGraphData;
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
		
		withdrawalGraphData = sortWithdrawals();

		lineGraph = new LineGraph(this);
		//requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(lineGraph);
	}

	private void getWithdrawals() {
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
	}

	private Map<String, Float> sortWithdrawals() {
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
			
			final XYSeriesCollection dataset = getDataSet(null);
	        final AFreeChart chart = createChart(dataset);

	        setChart(chart);
			
		}

		private XYSeriesCollection getDataSet(Map<String, Float> stuff) {
			XYSeriesCollection data = new XYSeriesCollection();
			//take stuff and get the x and y values
			XYSeries series3 = new XYSeries("Thing");
			XYSeries withdrawals = new XYSeries("Withdrawals");
			series3.add(3.0, 4.0);
			series3.add(4.0, 3.0);
			series3.add(5.0, 2.0);
			series3.add(6.0, 3.0);
			series3.add(7.0, 6.0);
			series3.add(8.0, 3.0);
			series3.add(9.0, 4.0);
			series3.add(10.0, 3.0);
			

			data.addSeries(series3);

			// get data sets with date and amount

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
