package edu.gatech.financialapplication;

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
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

public class CompareTransactionsActivity extends Activity {

	private DBHelper db;
	private String username, accountNumber, finalStart, finalEnd;
	private float depositValue, withdrawalValue;
	private List<Transaction> transactionList;
	private PieChart pieChart;
	private Context context;
	
	@SuppressWarnings({ "deprecation"})
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		context = this;
		db = new DBHelper(this);
		
		// pull from intent
		accountNumber = getIntent().getStringExtra("accountNumber");
		username = getIntent().getStringExtra("username");
		finalStart = getIntent().getStringExtra("finalStart");
		finalEnd = getIntent().getStringExtra("finalEnd");
		
		getAllTransactions();
		
		pieChart = new PieChart(this);
		Button btn = new Button(this);
		btn.setGravity(Gravity.CENTER);
		btn.setText("Home");
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
	
	private void getAllTransactions() {
		transactionList = db.getAllTransactionsByUsername(username);
		for (Transaction t : transactionList) {
			String date = t.getDate();
			if ((finalStart.compareTo(date) <= 0)
					&& (finalEnd.compareTo(date) >= 0)
					&& t.getAccount().equals(accountNumber)) {
				if (t.getType().equals("deposit")) {
					depositValue += t.getAmount();
				} else if (t.getType().equals("withdrawal")) {
					withdrawalValue += t.getAmount();
				}
			}
		}
	}
	
	private class PieChart extends DemoView {

		public PieChart(Context context) {
			super(context);
			PieDataset dataset = createDataSet();
	        AFreeChart chart = createChart(dataset);
	        setChart(chart);
		}

		private PieDataset createDataSet() {
			DefaultPieDataset data = new DefaultPieDataset();
			data.setValue("Deposits", depositValue);
			data.setValue("Withdrawals", withdrawalValue);

			return data;
		}

		public AFreeChart createChart(PieDataset data) {
			
			AFreeChart chart = ChartFactory.createPieChart(
					"Deposits vs. Withdrawals", //Title
					data, //data
					false, //no legend
					false, //no tooltips
					false); //no urls
			PiePlot plot = (PiePlot) chart.getPlot();
			plot.setSectionPaintType("Deposits", new SolidColor(Color.rgb(112, 146, 190)));
			plot.setSectionPaintType("Withdrawals", new SolidColor(Color.rgb(57, 131, 204)));
			return chart;
		}
	}
}
