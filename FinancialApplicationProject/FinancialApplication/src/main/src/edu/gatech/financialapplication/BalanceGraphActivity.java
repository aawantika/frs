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
import android.util.Pair;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class BalanceGraphActivity extends Activity {

	private String username, accountNumber, finalStart, finalEnd;
	private List<Pair<String, String>> balances;
	private LineGraph lineGraph;
	private DBHelper db;
	private Context context;

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_withdrawal_graph);
		db = new DBHelper(this);
		context = this;
		balances = new ArrayList<Pair<String, String>>();
		
		// pull from intent
		accountNumber = getIntent().getStringExtra("accountNumber");
		username = getIntent().getStringExtra("username");
		finalStart = getIntent().getStringExtra("finalStart");
		finalEnd = getIntent().getStringExtra("finalEnd");
		
		getBalances();
		
		lineGraph = new LineGraph(this);
		Button btn = new Button(this);
		btn.setGravity(Gravity.CENTER);
		btn.setText("Home");
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

	private void getBalances() {
		List<Pair<String, String>> balanceList = db.getAllBalances(accountNumber);
		for (Pair<String, String> balance : balanceList) {
			String date = balance.first;
			if ((finalStart.compareTo(date) <= 0) && (finalEnd.compareTo(date) >= 0)) {
				balances.add(balance);
			}
		}
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
			XYSeries balance = new XYSeries("Balances");
			
			Map<String, Float> balanceData = new TreeMap<String, Float>();
			balanceData.put("04/06/2014", 200.00f);
			balanceData.put("04/08/2014", 400.00f);
			balanceData.put("04/10/2014", 800.00f);
			balanceData.put("04/11/2014", 400.00f);
			balanceData.put("04/12/2014", 200.00f);
			/*for(Pair<String, String> pair : balances) {
				balanceData.put(pair.first, Float.parseFloat(pair.second));
			}
			*/
			Set<String> dates = balanceData.keySet();
			
			for (String date : dates) {
				double balanceAmount = balanceData.get(date);
				date = date.replaceAll("/", "");
				double dateValue = Double.parseDouble(date);
				balance.add(dateValue, balanceAmount);
			}
			
			data.addSeries(balance);

			return data;
		}
			
		public AFreeChart createChart(XYSeriesCollection data) {
			XYDataset dataset = data;
			AFreeChart chart = ChartFactory.createXYLineChart(
					"Balance vs Date", // Title
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
