package edu.gatech.financialapplication;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DBHelper extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "foobarsribshack.db";
	private static final int DATABASE_VERSION = 1;

	// user
	private static final String KEY_FNAME = "firstname";
	private static final String KEY_LNAME = "lastname";
	private static final String KEY_USER = "username";
	private static final String KEY_PASS = "password";

	private static final String KEY_EMAIL = "email";
	private static final String KEY_SSN = "ssn";
	private static final String KEY_DOB = "dob";
	private static final String KEY_PHONE = "phone";
	private static final String KEY_ACCOUNTS = "accounts";

	private static final String KEY_ADDRESS = "address";
	private static final String KEY_CITY = "city";
	private static final String KEY_STATE = "state";
	private static final String KEY_ZIPCODE = "zipcode";

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
			+ " TEXT," + KEY_SSN + " INTEGER," + KEY_DOB + " DATE," + KEY_PHONE
			+ " INTEGER," + KEY_ACCOUNTS + " TEXT," + KEY_ADDRESS + " TEXT,"
			+ KEY_CITY + " TEXT," + KEY_STATE + " TEXT," + KEY_ZIPCODE
			+ " INTEGER)";

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
		// values.put(KEY_SSN, user.getSsn());
		// values.put(KEY_DOB, user.getDob());
		// values.put(KEY_PHONE, user.getPhone());
		// values.put(KEY_ACCOUNTS, user.getAccounts());
		// values.put(KEY_ADDRESS, user.getAddress());
		// values.put(KEY_CITY, user.getCity());
		// values.put(KEY_STATE, user.getState());
		// values.put(KEY_ZIPCODE, user.getZipcode());

		try {
			db.insert(DATABASE_TABLE_USER, null, values);
			Log.d("Database User Inserted",
					"yay - inserted properly " + user.getLastname());
			db.close();
		} catch (Exception e) {
			Log.d("Databse User Inserted", "boo");
			e.printStackTrace();
		}
	}

	// smaller user
	public User getUserDetailsByUsername(String username) throws SQLException {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(DATABASE_TABLE_USER, new String[] { KEY_FNAME,
				KEY_LNAME, KEY_USER, KEY_PASS, KEY_EMAIL, KEY_SSN, KEY_DOB,
				KEY_PHONE, KEY_ACCOUNTS, KEY_ADDRESS, KEY_CITY, KEY_STATE,
				KEY_ZIPCODE }, KEY_USER + "=?",
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

	// full comprehensive user
	public User getUserDetails(String username) throws SQLException {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(DATABASE_TABLE_USER, new String[] { KEY_FNAME,
				KEY_LNAME, KEY_USER, KEY_PASS, KEY_EMAIL, KEY_SSN, KEY_DOB,
				KEY_PHONE, KEY_ACCOUNTS, KEY_ADDRESS, KEY_CITY, KEY_STATE,
				KEY_ZIPCODE }, KEY_USER	 + "=?", new String[] { username },
				null, null, null, null);

		if (cursor != null) {
			cursor.moveToFirst();
		}

		User user = new User(cursor.getString(0), cursor.getString(1),
				cursor.getString(2), cursor.getString(3), cursor.getString(4),
				Integer.parseInt(cursor.getString(5)), cursor.getString(6),
				Integer.parseInt(cursor.getString(7)), cursor.getString(8),
				cursor.getString(9), cursor.getString(10),
				cursor.getString(11), Integer.parseInt(cursor.getString(12)));
		return user;
	}
	
	public User getUserObject(String username) throws SQLException {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(DATABASE_TABLE_USER, new String[] { KEY_FNAME,
				KEY_LNAME, KEY_USER, KEY_PASS, KEY_EMAIL}, KEY_USER	 + "=?", new String[] { username },
				null, null, null, null);

		if (cursor != null) {
			cursor.moveToFirst();
		}

		User user = new User(cursor.getString(0), cursor.getString(1),
				cursor.getString(2), cursor.getString(3), cursor.getString(4));
		return user;
	}

	public List<User> getAllUsers() {
		List<User> userList = new ArrayList<User>();
		String selectQuery = "SELECT  * FROM " + DATABASE_TABLE_USER;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		if (cursor.moveToFirst()) {
			do {
				User user = new User();
				user.setId(Integer.parseInt(cursor.getString(0)));
				user.setFirstname(cursor.getString(1));
				user.setLastname(cursor.getString(2));
				user.setUsername(cursor.getString(3));
				user.setPassword(cursor.getString(4));
				user.setEmail(cursor.getString(5));
				user.setSsn(Integer.parseInt(cursor.getString(6)));
				user.setDob(cursor.getString(7));
				user.setPhone(Integer.parseInt(cursor.getString(8)));
				user.setAccounts(cursor.getString(9));
				user.setAddress(cursor.getString(10));
				user.setCity(cursor.getString(11));
				user.setState(cursor.getString(12));
				user.setZipcode(Integer.parseInt(cursor.getString(13)));
				userList.add(user);
			} while (cursor.moveToNext());
		}
		return userList;
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
			Log.d("Database Account Inserted", "yay " + account.getUsername());
			db.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public Account getAccount(User user) throws SQLException {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(true, DATABASE_TABLE_ACCOUNT,
				new String[] { KEY_FNAME, KEY_LNAME, KEY_USER, KEY_BALANCE,
						KEY_ACCOUNT_NUMBER }, KEY_USER + "=?",
				new String[] { String.valueOf(user.getUsername()) }, null,
				null, null, null);
		Account account = null;
		if (cursor != null) {
			cursor.moveToFirst();
			account = new Account(cursor.getString(0), cursor.getString(1),
					cursor.getString(2), cursor.getString(3),
					cursor.getString(4));
		}
		return account;
	}
	
	public boolean hasAccount(User user) {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = db.query(true, DATABASE_TABLE_ACCOUNT,null, 
				KEY_USER + "=?", new String[] {String.valueOf(user.getUsername())},
				null, null, null, null);
		if (cursor == null || cursor.getCount() == 0) 
			return false;
		return true;
	}

	/**
	 * 
	 * @param username
	 * @return Account of firstOne
	 * @throws SQLException
	 */
	public Account getAccountDetails(String username) throws SQLException {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(true, DATABASE_TABLE_ACCOUNT,
				new String[] { KEY_FNAME, KEY_LNAME, KEY_USER, KEY_BALANCE,
						KEY_ACCOUNT_NUMBER }, KEY_USER + "=?",
				new String[] { username }, null, null, null, null);
		Account account = null;
		if (cursor != null) {
			cursor.moveToFirst();
			account = new Account(cursor.getString(0), cursor.getString(1),
					cursor.getString(2), cursor.getString(3),
					cursor.getString(4));
		}
		return account;
	}
	
	/**
	 * 
	 * @param username
	 * @return list of account
	 * @throws SQLException
	 */
	public ArrayList<Account> getAccountsByUsername(String username) throws SQLException {
		SQLiteDatabase db = this.getReadableDatabase();
		Cursor cursor = null;
		if (username.equalsIgnoreCase("admin")) {
			cursor = db.query(true, DATABASE_TABLE_ACCOUNT,null, null ,null, null, null, 
					KEY_ACCOUNT_NUMBER + " ASC", null);
			Log.i("Admin ", "Getting all account");
		}else {
			cursor = db.query(true, DATABASE_TABLE_ACCOUNT,null, KEY_USER + "=?",
					new String[] { username }, null, null, KEY_ACCOUNT_NUMBER + " ASC", null);
		}

		ArrayList<Account> acc = new ArrayList<Account> ();
		if (cursor != null) {
			Log.d("cursor size: " , cursor.getCount()+"");
			for(int i = 0; i < cursor.getCount(); i++) { 
				if(cursor.moveToNext()) {
					Account account = new Account(cursor.getString(0), cursor.getString(1),
							cursor.getString(2), cursor.getDouble(3)+"", cursor.getInt(4)+"");
					acc.add(account);
				}
			}
		}
		return acc;
	}

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

	public void updateAccount(Transaction transaction) throws SQLException {
		SQLiteDatabase db = this.getWritableDatabase();

		Account account = getAccountByAccountNumber(transaction.getAccount());
		float transactionAmount = transaction.getAmount();
		float accountAmount = Float.parseFloat(account.getBalance());

		String transactionType = transaction.getType();
		if (transactionType.equals("deposit")) {
			accountAmount += transactionAmount;
//			System.out.println("deposited! " + accountAmount);
		} else {
			accountAmount = accountAmount - transactionAmount;
		}
		String accountAmountString = Float.toString(accountAmount);
		account.setBalance(accountAmountString);

//		System.out.println("account balance: " + account.getBalance());

		ContentValues values = new ContentValues();
		values.put(KEY_FNAME, account.getFirstname());
		values.put(KEY_LNAME, account.getLastname());
		values.put(KEY_USER, account.getUsername());
		values.put(KEY_BALANCE, account.getBalance());
		values.put(KEY_ACCOUNT_NUMBER, account.getAccountNumber() + "");

		db.update(DATABASE_TABLE_ACCOUNT, values, KEY_USER + "=?",
				new String[] { account.getUsername() });
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
			updateAccount(transaction);
			Log.d("Database Transaction Inserted", "yay ");
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Transaction getTransactionDetails(int accountNum)
			throws SQLException {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(true, DATABASE_TABLE_TRANSACTION,
				new String[] { KEY_ACCOUNT, KEY_DATE, KEY_AMOUNT,
						KEY_DESCRIPTION, KEY_CATEGORY, KEY_TYPE }, KEY_ACCOUNT
						+ "=?", new String[] { String.valueOf(accountNum) },
				null, null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
		}
		Transaction transaction = new Transaction(cursor.getString(0),
				cursor.getString(1), Float.parseFloat(cursor.getString(2)),
				cursor.getString(3), cursor.getString(4), cursor.getString(5));
		return transaction;
	}

	// Getting All Transactions based on account number
	public List<Transaction> getAllTransactions(String accountNumber) {

		List<Transaction> transactionList = new ArrayList<Transaction>();
		String selectQuery = "SELECT  * FROM " + DATABASE_TABLE_TRANSACTION;

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			do {
				Transaction transaction = new Transaction(cursor.getString(0),
						cursor.getString(1), Float.parseFloat(cursor
								.getString(2)), cursor.getString(3),
						cursor.getString(4), cursor.getString(5));
				if (transaction.getAccount().equals(accountNumber)) {
					transactionList.add(transaction);
				}
			} while (cursor.moveToNext());
		}

		return transactionList;
	}
}
