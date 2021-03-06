package edu.gatech.financialapplication;

import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;

/**
 * Send mail task to send a forgot password email.
 * 
 * @author Team 15
 * 
 */
@SuppressWarnings("rawtypes")
public class SendMailTask extends AsyncTask {

	private ProgressDialog statusDialog;
	private Activity sendMailActivity;

	public SendMailTask(Activity activity) {
		sendMailActivity = activity;

	}

	protected void onPreExecute() {
		statusDialog = new ProgressDialog(sendMailActivity);
		statusDialog.setMessage("Sending Email...");
		statusDialog.setIndeterminate(false);
		statusDialog.setCancelable(false);
		statusDialog.show();
	}

	@Override
	protected Object doInBackground(Object... args) {
		try {
			Log.i("SendMailTask", "About to instantiate GMail...");
			@SuppressWarnings("unchecked")
			GMail androidEmail = new GMail((List) args[0], (String) args[1]);
			androidEmail.createEmailMessage();
			androidEmail.sendEmail();
			Log.i("SendMailTask", "Mail Sent.");
		} catch (Exception e) {
			Log.e("SendMailTask", e.getMessage(), e);
		}
		return null;
	}

	@Override
	public void onProgressUpdate(Object... values) {
		statusDialog.setMessage(values[0].toString());

	}

	@Override
	public void onPostExecute(Object result) {
		statusDialog.dismiss();
	}

}
