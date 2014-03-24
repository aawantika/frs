package edu.gatech.financialapplication;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
	protected int size = 0;
	private static final String DATABASE_NAME = "foobarsribshack.db";
	private static final int DATABASE_VERSION = 1;

	// user
	private static final String KEY_FNAME = "firstname";
	private static final String KEY_LNAME = "lastname";
	private static final String KEY_USER = "username";
	private static final String KEY_PASS = "password";
	private static final String KEY_EMAIL = "email";

	// for account
	private static final String KEY_BALANCE = "balance";
	private static final String KEY_ACCOUNT_NUMBER = "account_number";

	// for transaction
	private static final String KEY_ACCOUNT = "account";
	private static final String KEY_DATE = "date";
	private static final String KEY_AMOUNT = "amount";
	private static final String KEY_DESCRIPTION = "description";
	private static final String KEY_CATEGORY = "category";
	private static final String KEY_TYPE = "type";

	// tables
	public static final String DATABASE_TABLE_USER = "frs1";
	public static final String DATABASE_TABLE_ACCOUNT = "frs2";
	public static final String DATABASE_TABLE_TRANSACTION = "frs3";

	private static final String TABLE_CREATE_USER = "CREATE TABLE "
			+ DATABASE_TABLE_USER + "(" + KEY_FNAME + " TEXT," + KEY_LNAME
			+ " TEXT," + KEY_USER + " TEXT," + KEY_PASS + " TEXT," + KEY_EMAIL
			+ " TEXT)";

	private static final String TABLE_CREATE_ACCOUNT = "CREATE TABLE "
			+ DATABASE_TABLE_ACCOUNT + "(" + KEY_FNAME + " TEXT," + KEY_LNAME
			+ " TEXT," + KEY_USER + " TEXT," + KEY_BALANCE + " REAL,"
			+ KEY_ACCOUNT_NUMBER + " INTEGER)";

	private static final String TABLE_CREATE_TRANSACTION = "CREATE TABLE "
			+ DATABASE_TABLE_TRANSACTION + "(" + KEY_ACCOUNT + " TEXT,"
			+ KEY_DATE + " TEXT," + KEY_AMOUNT + " TEXT," + KEY_DESCRIPTION
			+ " TEXT," + KEY_CATEGORY + " TEXT," + KEY_TYPE + " TEXT)";

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(TABLE_CREATE_USER);
			db.execSQL(TABLE_CREATE_ACCOUNT);
			db.execSQL(TABLE_CREATE_TRANSACTION);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// on upgrade drop older tables
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_USER);
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_ACCOUNT);
		db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE_TRANSACTION);

		// create new tables
		onCreate(db);
	}

	public void addUser(User user) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_FNAME, user.getFirstname());
		values.put(KEY_LNAME, user.getLastname());
		values.put(KEY_USER, user.getUsername());
		values.put(KEY_PASS, user.getPassword());
		values.put(KEY_EMAIL, user.getEmail());

		try {
			db.insert(DATABASE_TABLE_USER, null, values);
			Log.d("Database User Inserted", "Inserted Properly, username "
					+ user.getUsername());
			db.close();
		} catch (Exception e) {
			Log.d("Databse User Inserted", "Not inserted properly");
			e.printStackTrace();
		}
	}

	public User getUserByUsername(String username) throws SQLException {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(DATABASE_TABLE_USER, new String[] { KEY_FNAME,
				KEY_LNAME, KEY_USER, KEY_PASS, KEY_EMAIL }, KEY_USER + "=?",
				new String[] { String.valueOf(username) }, null, null, null,
				null);

		User user = null;
		if (cursor != null && cursor.getCount() > 0) {
			cursor.moveToFirst();
			user = new User(cursor.getString(0), cursor.getString(1),
					cursor.getString(2), cursor.getString(3),
					cursor.getString(4));
		}

		return user;
	}

	public boolean addAccount(Account account) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_FNAME, account.getFirstname());
		values.put(KEY_LNAME, account.getLastname());
		values.put(KEY_USER, account.getUsername());
		values.put(KEY_BALANCE, account.getBalance());
		values.put(KEY_ACCOUNT_NUMBER, account.getAccountNumber() + "");

		try {
			db.insert(DATABASE_TABLE_ACCOUNT, null, values);
			Log.d("Database Account Inserted",
					"username " + account.getUsername());
			db.close();
			return true;
		} catch (Exception e) {
			Log.d("Database Account Inserted", "not inserted");
			e.printStackTrace();
		}
		return false;
	}

	public boolean hasAccount(User user) {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(true, DATABASE_TABLE_ACCOUNT, null, KEY_USER
				+ "=?", new String[] { String.valueOf(user.getUsername()) },
				null, null, null, null);
		if (cursor == null || cursor.getCount() == 0)
			return false;
		return true;
	}

	public ArrayList<Account> getAccountsByUsername(String username)
			throws SQLException {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = null;
		if (username.equalsIgnoreCase("admin")) {
			cursor = db.query(true, DATABASE_TABLE_ACCOUNT, null, null, null,
					null, null, KEY_ACCOUNT_NUMBER + " ASC", null);
			Log.i("Admin ", "Getting all account");
		} else {
			cursor = db.query(true, DATABASE_TABLE_ACCOUNT, null, KEY_USER
					+ "=?", new String[] { username }, null, null,
					KEY_ACCOUNT_NUMBER + " ASC", null);
		}

		ArrayList<Account> accounts = new ArrayList<Account>();
		if (cursor != null) {
			Log.d("cursor size: ", cursor.getCount() + "");
			for (int i = 0; i < cursor.getCount(); i++) {
				if (cursor.moveToNext()) {
					Account account = new Account(cursor.getString(0),
							cursor.getString(1), cursor.getString(2),
							cursor.getDouble(3) + "", cursor.getInt(4) + "");
					accounts.add(account);
				}
			}
		}

		return accounts;
	}

	// Used by updateAccount
	public Account getAccountByAccountNumber(String accountNumber)
			throws SQLException {

		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(true, DATABASE_TABLE_ACCOUNT,
				new String[] { KEY_FNAME, KEY_LNAME, KEY_USER, KEY_BALANCE,
						KEY_ACCOUNT_NUMBER }, KEY_ACCOUNT_NUMBER + "=?",
				new String[] { accountNumber }, null, null, null, null);

		Account account = null;
		if (cursor != null) {
			cursor.moveToFirst();
			account = new Account(cursor.getString(0), cursor.getString(1),
					cursor.getString(2), cursor.getString(3),
					cursor.getString(4));
		}

		return account;
	}

	// Updates an account with new balance, used by addTransaction
	public void updateAccount(Transaction transaction) throws SQLException {
		SQLiteDatabase db = this.getWritableDatabase();

		Account account = getAccountByAccountNumber(transaction.getAccount());
		float transactionAmount = transaction.getAmount();
		float accountAmount = Float.parseFloat(account.getBalance());

		String transactionType = transaction.getType();
		if (transactionType.equals("deposit")) {
			accountAmount += transactionAmount;
		} else {
			accountAmount = accountAmount - transactionAmount;
		}

		String accountAmountString = Float.toString(accountAmount);
		account.setBalance(accountAmountString);

		ContentValues values = new ContentValues();
		values.put(KEY_BALANCE, account.getBalance());
		db.update(DATABASE_TABLE_ACCOUNT, values, KEY_ACCOUNT_NUMBER + "=?",
				new String[] { account.getAccountNumber() });
	}

	public void addTransaction(Transaction transaction) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_ACCOUNT, transaction.getAccount());
		values.put(KEY_DATE, transaction.getDate());
		values.put(KEY_AMOUNT, Float.toString(transaction.getAmount()));
		values.put(KEY_DESCRIPTION, transaction.getDescription());
		values.put(KEY_CATEGORY, transaction.getCategory());
		values.put(KEY_TYPE, transaction.getType());

		try {
			db.insert(DATABASE_TABLE_TRANSACTION, null, values);
			size++;
			updateAccount(transaction);
			Log.d("Database Transaction Inserted", "yay ");
			
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Getting All Transactions based on account number
	public ArrayList<Transaction> getAllTransactions(String accountNumber) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = null;
		
		cursor = db.query(true, DATABASE_TABLE_TRANSACTION, null, KEY_ACCOUNT
				+ "=?", new String[] { accountNumber }, null, null,
				KEY_ACCOUNT + " ASC", null);		
		ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
		
		if (cursor != null) {
			Log.d("cursor size - transaction: ", cursor.getCount() + "");
			for (int i = 0; i < cursor.getCount(); i++) {
				if (cursor.moveToNext()) {
					Transaction transaction = new Transaction(cursor.getString(0),
							cursor.getString(1), Float.parseFloat(cursor
									.getString(2)), cursor.getString(3),
							cursor.getString(4), cursor.getString(5));
					transactionList.add(transaction);
				}
			}
		}
		return transactionList;
	}
	
	public ArrayList<Transaction> getAllTransactionsByUsername(String username) {
		ArrayList<Transaction> fullTransactionList = new ArrayList<Transaction>();
		ArrayList<Account> accountList = getAccountsByUsername(username);
		
		for (Account a : accountList) {
			String accountNumber = a.getAccountNumber();
			ArrayList<Transaction> transactionList = getAllTransactions(accountNumber);
			for (Transaction t : transactionList) {
				fullTransactionList.add(t);
			}
		}
		
		return fullTransactionList;
	}
	
	public ArrayList<Transaction> getAllDeposits() {

		ArrayList<Transaction> depositList = new ArrayList<Transaction>();
		String selectQuery = "SELECT  * FROM " + DATABASE_TABLE_TRANSACTION;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				Transaction transaction = new Transaction(cursor.getString(0),
						cursor.getString(1), Float.parseFloat(cursor
								.getString(2)), cursor.getString(3),
						cursor.getString(4), cursor.getString(5));
				if (transaction.getType().equals("deposit")) {
					depositList.add(transaction);
				}
			} while (cursor.moveToNext());
		}

		return depositList;
	}
	
	public ArrayList<Transaction> getAllWithdrawals() {

		ArrayList<Transaction> withdrawalList = new ArrayList<Transaction>();
		String selectQuery = "SELECT  * FROM " + DATABASE_TABLE_TRANSACTION;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				Transaction transaction = new Transaction(cursor.getString(0),
						cursor.getString(1), Float.parseFloat(cursor
								.getString(2)), cursor.getString(3),
						cursor.getString(4), cursor.getString(5));
				if (transaction.getType().equals("withdrawal")) {
					withdrawalList.add(transaction);
				}
			} while (cursor.moveToNext());
		}

		return withdrawalList;
	}
}
