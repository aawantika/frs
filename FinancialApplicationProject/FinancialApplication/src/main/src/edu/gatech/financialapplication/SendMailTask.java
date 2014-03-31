package edu.gatech.financialapplication;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

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

    /**
     * A new progress dialog.
     */
    private ProgressDialog statusDialog;
    /**
     * A new activity.
     */
    private Activity sendMailActivity;

    /**
     * Creates a new send mail task with an activity.
     * 
     * @param activity The activity associated with the send mail task.
     */
    public SendMailTask(Activity activity) {
        sendMailActivity = activity;

    }

    /**
     * Shows a getting ready message.
     */
    protected void onPreExecute() {
        statusDialog = new ProgressDialog(sendMailActivity);
        statusDialog.setMessage("Getting ready...");
        statusDialog.setIndeterminate(false);
        statusDialog.setCancelable(false);
        statusDialog.show();
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Object doInBackground(Object... args) {
        try {
            Log.i("SendMailTask", "About to instantiate GMail...");
            publishProgress("Processing input....");
            GMail androidEmail = new GMail((List) args[0], args[1].toString());
            publishProgress("Preparing mail message....");
            androidEmail.createEmailMessage();
            publishProgress("Sending email....");
            androidEmail.sendEmail();
            publishProgress("Email Sent.");
            Log.i("SendMailTask", "Mail Sent.");
        } catch (MessagingException e) {
            publishProgress(e.getMessage());
            Log.e("SendMailTask", e.getMessage(), e);
        } catch (UnsupportedEncodingException e) {
            publishProgress(e.getMessage());
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
