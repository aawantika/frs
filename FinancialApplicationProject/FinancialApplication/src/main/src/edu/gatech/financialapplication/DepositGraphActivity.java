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
import android.os.Bundle;

public class DepositGraphActivity extends Activity {

	private String username, accountNumber, finalStart, finalEnd;
	private List<Transaction> transactionList;
	private List<Transaction> allDeposits;
	private LineGraph lineGraph;
	private DBHelper db;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_deposit_graph);
		db = new DBHelper(this);
		allDeposits = new ArrayList<Transaction>();

		// pull from intent
		accountNumber = getIntent().getStringExtra("accountNumber");
		username = getIntent().getStringExtra("username");
		finalStart = getIntent().getStringExtra("finalStart");
		finalEnd = getIntent().getStringExtra("finalEnd");

		lineGraph = new LineGraph(this);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(lineGraph);
	}

	private Map<String, Float> sortDeposits() {
		transactionList = db.getAllTransactionsByUsername(username);
		for (Transaction t : transactionList) {
			String date = t.getDate();
			if ((finalStart.compareTo(date) <= 0)
					&& (finalEnd.compareTo(date) >= 0)
					&& t.getAccount().equals(accountNumber)
					&& t.getType().equals("deposit")) {
				allDeposits.add(t);
			}
		}

		Map<String, Float> dates = new TreeMap<String, Float>();
		float amount;
		for (Transaction t : allDeposits) {
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

			XYSeriesCollection dataset = getDataSet(sortDeposits());
			AFreeChart chart = createChart(dataset);

			setChart(chart);

		}

		private XYSeriesCollection getDataSet(Map<String, Float> depositData) {
			XYSeriesCollection data = new XYSeriesCollection();
			Set<String> dates = depositData.keySet();

			// take stuff and get the x and y values
			XYSeries graph = new XYSeries("Deposits");

			for (String date : dates) {
				float withdrawalAmount = depositData.get(date);
				float dateValue = Float.parseFloat(date);
				graph.add(withdrawalAmount, dateValue);
			}

			data.addSeries(graph);

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

			return chart;

		}

	}
}
