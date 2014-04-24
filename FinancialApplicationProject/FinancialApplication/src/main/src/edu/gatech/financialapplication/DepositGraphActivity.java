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
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class DepositGraphActivity extends Activity {

	private String username, accountNumber, finalStart, finalEnd;
	private List<Transaction> transactionList;
	private List<Transaction> allDeposits;
	private LineGraph lineGraph;
	private DBHelper db;
	private Context context;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		db = new DBHelper(this);
		context = this;
		allDeposits = new ArrayList<Transaction>();

		// pull from intent
		accountNumber = getIntent().getStringExtra("accountNumber");
		username = getIntent().getStringExtra("username");
		finalStart = getIntent().getStringExtra("finalStart");
		finalEnd = getIntent().getStringExtra("finalEnd");

		lineGraph = new LineGraph(this);
		Button btn = new Button(this);
		btn.setGravity(Gravity.CENTER);
		btn.setText("Transactions");
		btn.setOnClickListener(myhandler);
		
		LinearLayout ll = new LinearLayout(this);
	    ll.setOrientation(LinearLayout.VERTICAL);
	    ll.setLayoutParams(new LayoutParams(LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT));
	    ll.setGravity(Gravity.CENTER);
	    ll.addView(btn);
	    ll.addView(lineGraph);
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
	
	private Map<String, Float> sortDeposits() {
		transactionList = new ArrayList<Transaction>();
		transactionList.add(new Deposit(accountNumber, "04062014", 200.00f, "reason1"));
		transactionList.add(new Deposit(accountNumber, "04082014", 400.00f, "reason1"));
		transactionList.add(new Deposit(accountNumber, "04102014", 800.00f, "reason1"));
		transactionList.add(new Deposit(accountNumber, "04112014", 400.00f, "reason1"));
		transactionList.add(new Deposit(accountNumber, "04122014", 200.00f, "reason1"));
		
		//transactionList = db.getAllTransactionsByUsername(username);
		for (Transaction t : transactionList) {
			String date = t.getDate();
			if ((finalStart.compareTo(date) <= 0) && (finalEnd.compareTo(date) >= 0)
					&& t.getAccount().equals(accountNumber) && t.getType().equals("deposit")) {
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

			XYSeriesCollection dataset = getDataSet();
			AFreeChart chart = createChart(dataset);

			setChart(chart);

		}

		private XYSeriesCollection getDataSet() {
			XYSeriesCollection data = new XYSeriesCollection();
			XYSeries deposits = new XYSeries("Deposits");
			
			Map<String, Float> depositData = sortDeposits();
			Set<String> dates = depositData.keySet();
			
			for (String date : dates) {
				double withdrawalAmount = depositData.get(date);
				double dateValue = Double.parseDouble(date);
				deposits.add(dateValue, withdrawalAmount);
			}

			data.addSeries(deposits);

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
