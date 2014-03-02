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
	private SQLiteDatabase db;
	
	private static final String LOG = "DatabaseHelper";

	// user
	public static final String KEY_ROWID = "_id";
	public static final String KEY_FNAME = "firstname";
	public static final String KEY_LNAME = "lastname";
	public static final String KEY_USER = "username";
	public static final String KEY_PASS = "password";

	public static final String KEY_EMAIL = "email";
	public static final String KEY_SSN = "ssn";
	public static final String KEY_DOB = "dob";
	public static final String KEY_PHONE = "phone";
	public static final String KEY_ACCOUNTS = "accounts";

	public static final String KEY_ADDRESS = "address";
	public static final String KEY_CITY = "city";
	public static final String KEY_STATE = "state";
	public static final String KEY_ZIPCODE = "zipcode";

	// for account
	public static final String KEY_BALANCE = "balance";
	public static final String KEY_ACCOUNT_NUMBER = "account_number";

	// for transaction
	public static final String KEY_TO = "account_to";
	public static final String KEY_FROM = "account_from";
	public static final String KEY_DATE = "date";
	public static final String KEY_AMOUNT = "amount";

	DBHelper DB = null;
	private static final String DATABASE_NAME = "foobarsribshack.db";
	private static final int DATABASE_VERSION = 1;

	// tables
	public static final String DATABASE_TABLE_USER = "frs1";
	public static final String DATABASE_TABLE_ACCOUNT = "frs2";
	public static final String DATABASE_TABLE_TRANSACTION = "frs3";

	private static final String DATABASE_TABLE_CREATE_USER = "CREATE TABLE "
			+ DATABASE_TABLE_USER + "(" + KEY_ROWID + " INTEGER PRIMARY KEY,"
			+ KEY_FNAME + " TEXT," + KEY_LNAME + " TEXT," + KEY_USER + " TEXT,"
			+ KEY_PASS + " TEXT," + KEY_EMAIL + " TEXT," + KEY_SSN
			+ " INTEGER," + KEY_DOB + " DATE," + KEY_ADDRESS + " TEXT,"
			+ KEY_CITY + " TEXT," + KEY_STATE + " TEXT," + KEY_ZIPCODE
			+ " INTEGER," + KEY_PHONE + " INTEGER," + KEY_ACCOUNTS + " TEXT)";

	private static final String DATABASE_TABLE_CREATE_ACCOUNT = "CREATE TABLE "
			+ DATABASE_TABLE_ACCOUNT + "(" + KEY_ROWID
			+ " INTEGER PRIMARY KEY," + KEY_FNAME + " TEXT," + KEY_LNAME
			+ " TEXT," + KEY_USER + " TEXT," + KEY_BALANCE + " DOUBLE,"
			+ KEY_ACCOUNT_NUMBER + " INTEGER)";

	private static final String DATABASE_TABLE_CREATE_TRANSACTION = "CREATE TABLE "
			+ DATABASE_TABLE_TRANSACTION
			+ "("
			+ KEY_ROWID
			+ " INTEGER PRIMARY KEY,"
			+ KEY_TO
			+ " TEXT,"
			+ KEY_FROM
			+ " TEXT, "
			+ KEY_DATE
			+ " DATE,"
			+ KEY_ACCOUNT_NUMBER
			+ " INTEGER," + KEY_AMOUNT + " TEXT)";

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		System.out.println("In constructor");
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		try {
			db.execSQL(DATABASE_TABLE_CREATE_USER);
			db.execSQL(DATABASE_TABLE_CREATE_ACCOUNT);
			db.execSQL(DATABASE_TABLE_CREATE_TRANSACTION);
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

	public void open() {
		getWritableDatabase();
	}

	public void addUser(User user) {
		SQLiteDatabase db = this.getWritableDatabase();

		ContentValues values = new ContentValues();
		values.put(KEY_FNAME, user.getFirstname());
		values.put(KEY_LNAME, user.getLastname());
		values.put(KEY_USER, user.getUsername());
		values.put(KEY_PASS, user.getPassword());
		values.put(KEY_EMAIL, user.getEmail());
		values.put(KEY_SSN, user.getSsn());
		values.put(KEY_DOB, user.getDob());
		values.put(KEY_ADDRESS, user.getAddress());
		values.put(KEY_CITY, user.getCity());
		values.put(KEY_STATE, user.getState());
		values.put(KEY_ZIPCODE, user.getZipcode());
		values.put(KEY_PHONE, user.getPhone());
		values.put(KEY_ACCOUNTS, user.getAccounts());

		try {
			db.insert(DBHelper.DATABASE_TABLE_USER, null, values);
			System.out.println("Inserted");
			Log.d("User adder : ",
					user.getFirstname() + " " + user.getLastname());
			Log.d("User ID: ", "" + user.getId());
			db.close();
		} catch (Exception e) {
			System.out.println("Didn't insert");
			e.printStackTrace();
		}
	}

	public User getUserDetails(int id) throws SQLException {
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor cursor = db.query(DATABASE_TABLE_USER, new String[] { KEY_ROWID,
				KEY_FNAME, KEY_LNAME, KEY_USER, KEY_PASS, KEY_EMAIL, KEY_SSN,
				KEY_DOB, KEY_ADDRESS, KEY_CITY, KEY_STATE, KEY_ZIPCODE,
				KEY_PHONE, KEY_ACCOUNTS }, KEY_ROWID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);

		if (cursor != null) {
			System.out.println("FOUND USER");
			cursor.moveToFirst();
		}
		
		User user = new User(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2), cursor.getString(3),
				cursor.getString(4), cursor.getString(5),
				Integer.parseInt(cursor.getString(6)), cursor.getString(7),
				Integer.parseInt(cursor.getString(8)), cursor.getString(9),
				cursor.getString(10), cursor.getString(11),
				cursor.getString(12), Integer.parseInt(cursor.getString(13)));
		cursor.close();
		db.close();
		return user;
	}

	// Getting All Contacts
	public List<User> getAllUsers() {
		List<User> userList = new ArrayList<User>();
		System.out.println("contactListSize: " + userList.size());
		// Select All Query
		String selectQuery = "SELECT  * FROM " + DATABASE_TABLE_USER;
		System.out.println("selectQuery: " + selectQuery);

		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		
		System.out.println("cursorSize: " + cursor.getCount());

		// looping through all rows and adding to list
		if (cursor.moveToFirst()) {
			System.out.println("Moves to First here!");
			do {
				User user = new User(Integer.parseInt(cursor.getString(0)),
						cursor.getString(1), cursor.getString(2),
						cursor.getString(3), cursor.getString(4),
						cursor.getString(5), Integer.parseInt(cursor
								.getString(6)), cursor.getString(7),
						Integer.parseInt(cursor.getString(8)),
						cursor.getString(9), cursor.getString(10),
						cursor.getString(11), cursor.getString(12),
						Integer.parseInt(cursor.getString(13)));
				if (user != null) {
					System.out.println("GOING TO ADD");
					// Adding contact to list
					userList.add(user);
				}
			} while (cursor.moveToNext());
		}
		return userList;
	}

	private void addAccount(String fname, String lname, String uname,
			String balance, int accountNumber) {

		SQLiteDatabase db = DB.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put(KEY_LNAME, lname);
		values.put(KEY_USER, uname);
		values.put(KEY_BALANCE, balance);
		values.put(KEY_ACCOUNT_NUMBER, accountNumber);

		try {
			db.insert(DBHelper.DATABASE_TABLE_ACCOUNT, null, values);
			Log.d("User adder : ", fname + " " + lname);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void addTransaction(String account_number, String to, String from,
			String date, double amount) {

		SQLiteDatabase db = DB.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put(KEY_TO, to);
		values.put(KEY_FROM, from);
		values.put(KEY_DATE, date);
		values.put(KEY_AMOUNT, amount);
		values.put(KEY_ACCOUNT_NUMBER, account_number);

		try {
			db.insert(DBHelper.DATABASE_TABLE_TRANSACTION, null, values);
			Log.d("Account number : ", account_number);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public Cursor getAccountDetails(String text) throws SQLException {
		Cursor mCursor = db.query(true, DATABASE_TABLE_ACCOUNT, new String[] {
				KEY_LNAME, KEY_USER, KEY_BALANCE, KEY_ACCOUNT_NUMBER },
				KEY_USER + "=" + text, null, null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

	public Cursor getTransactionDetails(String text) throws SQLException {
		Cursor mCursor = db.query(true, DATABASE_TABLE_TRANSACTION,
				new String[] { KEY_TO, KEY_FROM, KEY_DATE, KEY_AMOUNT,
						KEY_ACCOUNT_NUMBER }, KEY_USER + "=" + text, null,
				null, null, null, null, null);
		if (mCursor != null) {
			mCursor.moveToFirst();
		}
		return mCursor;
	}

}
