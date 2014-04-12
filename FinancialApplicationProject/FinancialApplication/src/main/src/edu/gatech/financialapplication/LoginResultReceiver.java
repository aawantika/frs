package edu.gatech.financialapplication;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

/**
 * ResultReceiver that checks specifically for the login formatting.
 * @author Team 15
 */
public class LoginResultReceiver extends ResultReceiver {
	/**
	 * The Receiver that reads in user input.
	 */
    private Receiver mReceiver;

    /**
     * Default constructor for LoginResultReceiver that sets the handler.
     * @param handler a new handler
     */
    @SuppressLint("Instantiatable")
    public LoginResultReceiver(Handler handler) {
        super(handler);
    }

    /**
     * The Receiver interface.
     * @author Team 15
     */
    public interface Receiver {
    	/**
    	 * Sets the action for the Receiver.
    	 * @param resultCode the result to be received
    	 * @param resultData the data as a bundle
    	 */
        void onReceiveResult(int resultCode, Bundle resultData);

    }

    /**
     * Sets the receiver.
     * @param receiver the receiver being used
     */
    public void setmReceiver(Receiver receiver) {
        mReceiver = receiver;
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (mReceiver != null) {
            mReceiver.onReceiveResult(resultCode, resultData);
        }
    }
}