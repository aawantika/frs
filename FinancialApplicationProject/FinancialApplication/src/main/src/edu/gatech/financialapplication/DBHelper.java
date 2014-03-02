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
	private static final String KEY_ROWID = "_id";
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
	private static final String KEY_TO = "account_to";
	private static final String KEY_FROM = "account_from";
	private static final String KEY_DATE = "date";
	private static final String KEY_AMOUNT = "amount";

	// tables
	public static final String DATABASE_TABLE_USER = "frs1";
	public static final String DATABASE_TABLE_ACCOUNT = "frs2";
	public static final String DATABASE_TABLE_TRANSACTION = "frs3";

	private static final String DATABASE_TABLE_CREATE_USER = "CREATE TABLE "
			+ DATABASE_TABLE_USER + "(" + KEY_ROWID + " INTEGER PRIMARY KEY,"
			+ KEY_FNAME + " TEXT," + KEY_LNAME + " TEXT," + KEY_USER + " TEXT,"
			+ KEY_PASS + " TEXT," + KEY_EMAIL + " TEXT," + KEY_SSN
			+ " INTEGER," + KEY_DOB + " DATE," + KEY_PHONE + " INTEGER,"
			+ KEY_ACCOUNTS + " TEXT," + KEY_ADDRESS + " TEXT," + KEY_CITY
			+ " TEXT," + KEY_STATE + " TEXT," + KEY_ZIPCODE + " INTEGER)";

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
			+ " TEXT,"
			+ KEY_DATE
			+ " DATE,"
			+ KEY_AMOUNT
			+ " DOUBLE,"
			+ KEY_ACCOUNT_NUMBER + " INTEGER)";

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
		values.put(KEY_PHONE, user.getPhone());
		values.put(KEY_ACCOUNTS, user.getAccounts());
		values.put(KEY_ADDRESS, user.getAddress());
		values.put(KEY_CITY, user.getCity());
		values.put(KEY_STATE, user.getState());
		values.put(KEY_ZIPCODE, user.getZipcode());

		try {
			db.insert(DATABASE_TABLE_USER, null, values);
			Log.d("Databse User Inserted", "yay");
			db.close();
		} catch (Exception e) {
			Log.d("Databse User Inserted", "boo");
			e.printStackTrace();
		}
	}

	public User getUserDetails(int id) throws SQLException {
		SQLiteDatabase db = this.getReadableDatabase();

		// NEED TO CHANGE FROM ROWID TO USERNAME HERE!!!!!!!!!!!!!
		Cursor cursor = db.query(DATABASE_TABLE_USER, new String[] { KEY_ROWID,
				KEY_FNAME, KEY_LNAME, KEY_USER, KEY_PASS, KEY_EMAIL, KEY_SSN,
				KEY_DOB, KEY_PHONE, KEY_ACCOUNTS, KEY_ADDRESS, KEY_CITY,
				KEY_STATE, KEY_ZIPCODE }, KEY_ROWID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);

		if (cursor != null) {
			cursor.moveToFirst();
		}

		User user = new User(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2), cursor.getString(3),
				cursor.getString(4), cursor.getString(5),
				Integer.parseInt(cursor.getString(6)), cursor.getString(7),
				Integer.parseInt(cursor.getString(8)), cursor.getString(9),
				cursor.getString(10), cursor.getString(11),
				cursor.getString(12), Integer.parseInt(cursor.getString(13)));
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

	public void addAccount(Account account) {

		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();

		values.put(KEY_FNAME, account.getFirstname());
		values.put(KEY_LNAME, account.getLastname());
		values.put(KEY_USER, account.getUsername());
		values.put(KEY_BALANCE, account.getBalance());
		values.put(KEY_ACCOUNT_NUMBER, account.getAccountNumber());

		try {
			db.insert(DBHelper.DATABASE_TABLE_ACCOUNT, null, values);
			Log.d("Databse User Inserted", "yay");
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public Account getAccountDetails(int id) throws SQLException {
		SQLiteDatabase db = this.getReadableDatabase();
		
		Cursor cursor = db.query(true, DATABASE_TABLE_ACCOUNT, new String[] {
				KEY_FNAME, KEY_LNAME, KEY_USER, KEY_BALANCE, KEY_ACCOUNT_NUMBER },
				KEY_ROWID + "=?",
				new String[] { String.valueOf(id) }, null, null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
		}
		
		Account account = new Account(Integer.parseInt(cursor.getString(0)),
				cursor.getString(1), cursor.getString(2), cursor.getString(3),
				Double.parseDouble(cursor.getString(4)), Integer.parseInt(cursor.getString(5)));
		return account;
	}

	public void addTransaction(Transaction transaction) {
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		
		values.put(KEY_TO, transaction.getAccountTo());
		values.put(KEY_FROM, transaction.getAccountFrom());
		values.put(KEY_DATE, transaction.getDate());
		values.put(KEY_AMOUNT, transaction.getAmount());
		values.put(KEY_ACCOUNT_NUMBER, transaction.getAccountNumber());
		
		try {
			db.insert(DBHelper.DATABASE_TABLE_TRANSACTION, null, values);
			Log.d("Database Transaction Inserted", "yay");
			db.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public Transaction getTransactionDetails(int accountNum) throws SQLException {
		SQLiteDatabase db = this.getReadableDatabase();

		Cursor cursor = db.query(true, DATABASE_TABLE_TRANSACTION,
				new String[] {KEY_TO, KEY_FROM, KEY_DATE, KEY_AMOUNT,
						KEY_ACCOUNT_NUMBER }, KEY_ACCOUNT_NUMBER + "=?",
						new String[] {String.valueOf(accountNum)}, null, null, null, null);
		if (cursor != null) {
			cursor.moveToFirst();
		}
		Transaction transaction = new Transaction(cursor.getString(0),
				cursor.getString(1), cursor.getString(2), Double.parseDouble(cursor.getString(3)),
				Integer.parseInt(cursor.getString(4)));
		return transaction;
	}
}
