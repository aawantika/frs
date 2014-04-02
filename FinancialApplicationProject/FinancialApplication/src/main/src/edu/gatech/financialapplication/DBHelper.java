package edu.gatech.financialapplication;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Backend database using S QLite.
 * 
 * @author Team 15
 */
public class DBHelper extends SQLiteOpenHelper {
    /**
     * Used to check if databse works properly or not.
     */
    protected int size = 0;

    /**
     * Database name.
     */
    private static final String DATABASE_NAME = "foobarsribshack.db";

    /**
     * Database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * First name column for user.
     */
    private static final String KEY_FNAME = "firstname";
    /**
     * Last name column for user.
     */
    private static final String KEY_LNAME = "lastname";
    /**
     * Username column for user.
     */
    private static final String KEY_USER = "username";
    /**
     * Password column for user.
     */
    private static final String KEY_PASS = "password";
    /**
     * Email column for user.
     */
    private static final String KEY_EMAIL = "email";

    /**
     * Balance column for account.
     */
    private static final String KEY_BALANCE = "balance";
    /**
     * Account number column for account.
     */
    private static final String KEY_ACCOUNT_NUMBER = "account_number";

    /**
     * Account number column for transaction.
     */
    private static final String KEY_ACCOUNT = "account";
    /**
     * Date column for transaction.
     */
    private static final String KEY_DATE = "date";
    /**
     * Amount column for transaction.
     */
    private static final String KEY_AMOUNT = "amount";
    /**
     * Description column for transaction.
     */
    private static final String KEY_DESCRIPTION = "description";
    /**
     * Category column for transaction.
     */
    private static final String KEY_CATEGORY = "category";
    /**
     * Type column for transaction.
     */
    private static final String KEY_TYPE = "type";

    /**
     * First table - for users.
     */
    public static final String DATABASE_TABLE_USER = "frs1";
    /**
     * Second table - for account.
     */
    public static final String DATABASE_TABLE_ACCOUNT = "frs2";
    /**
     * Third table - for transactions.
     */
    public static final String DATABASE_TABLE_TRANSACTION = "frs3";

    /**
     * User table.
     */
    private static final String TABLE_CREATE_USER = "CREATE TABLE "
            + DATABASE_TABLE_USER + "(" + KEY_FNAME + " TEXT," + KEY_LNAME
            + " TEXT," + KEY_USER + " TEXT," + KEY_PASS + " TEXT," + KEY_EMAIL
            + " TEXT)";

    /**
     * Account table.
     */
    private static final String TABLE_CREATE_ACCOUNT = "CREATE TABLE "
            + DATABASE_TABLE_ACCOUNT + "(" + KEY_FNAME + " TEXT," + KEY_LNAME
            + " TEXT," + KEY_USER + " TEXT," + KEY_BALANCE + " REAL,"
            + KEY_ACCOUNT_NUMBER + " INTEGER)";

    /**
     * Transaction table.
     */
    private static final String TABLE_CREATE_TRANSACTION = "CREATE TABLE "
            + DATABASE_TABLE_TRANSACTION + "(" + KEY_ACCOUNT + " TEXT,"
            + KEY_DATE + " TEXT," + KEY_AMOUNT + " TEXT," + KEY_DESCRIPTION
            + " TEXT," + KEY_CATEGORY + " TEXT," + KEY_TYPE + " TEXT)";

    /**
     * Creates a new DB Helper.
     * 
     * @param context
     *            The context it's created in.
     */
    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        try {
            db.execSQL(TABLE_CREATE_USER);
            db.execSQL(TABLE_CREATE_ACCOUNT);
            db.execSQL(TABLE_CREATE_TRANSACTION);
        } catch (SQLiteException e) {
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

    /**
     * Adds a new user.
     * 
     * @param user
     *            The user being added.
     */
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
        } catch (SQLiteException e) {
            Log.d("Databse User Inserted", "Not inserted properly");
            e.printStackTrace();
        }
    }

    /**
     * Gets user by username.
     * 
     * @param username
     *            The username being searched for.
     * @return The user that is found.
     * @throws SQLException
     *             If the username isn't in the database.
     */
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

    /**
     * Adds a new account.
     * 
     * @param account
     *            The new account being added.
     * @return If account is added successfully.
     */
    public boolean addAccount(Account account) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_FNAME, account.getFirstname());
        values.put(KEY_LNAME, account.getLastname());
        values.put(KEY_USER, account.getUsername());
        values.put(KEY_BALANCE, account.getBalance());
        values.put(KEY_ACCOUNT_NUMBER, account.getAccountNumber());

        try {
            db.insert(DATABASE_TABLE_ACCOUNT, null, values);
            Log.d("Database Account Inserted Properly",
                    "username " + account.getUsername());
            db.close();
            return true;
        } catch (SQLiteException e) {
            Log.d("Database Account Inserted", "not inserted");
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Checks if there is an account.
     * 
     * @param user
     *            The user being looked for.
     * @return If the user is found or not.
     */
    public boolean hasAccount(User user) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(true, DATABASE_TABLE_ACCOUNT, null, KEY_USER
                + "=?", new String[] { String.valueOf(user.getUsername()) },
                null, null, null, null);
        if (cursor == null || cursor.getCount() == 0) {
            return false;
        }
        return true;
    }

    /**
     * Gets account by username.
     * 
     * @param username
     *            The username being searched with.
     * @return All the accounts associated with the username.
     * @throws SQLException
     *             If the userame isn't there.
     */
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
            Account account = null;

            for (int i = 0; i < cursor.getCount(); i++) {
                if (cursor.moveToNext()) {
                    account = new Account(cursor.getString(0),
                            cursor.getString(1), cursor.getString(2),
                            cursor.getDouble(3) + "", cursor.getInt(4) + "");
                    accounts.add(account);
                }
            }
        }

        return accounts;
    }

    /**
     * Gets account by account number.
     * 
     * @param accountNumber
     *            The account number being searched with.
     * @return The account looking for.
     * @throws SQLException
     *             If account is not there.
     */
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

    /**
     * Updates the respective account with a transaction.
     * 
     * @param transaction
     *            The transaction being added.
     * @throws SQLException
     *             If the transaction is not added properly.
     */
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

        String amountString = Float.toString(accountAmount);
        account.setBalance(amountString);

        ContentValues values = new ContentValues();
        values.put(KEY_BALANCE, account.getBalance());
        db.update(DATABASE_TABLE_ACCOUNT, values, KEY_ACCOUNT_NUMBER + "=?",
                new String[] { account.getAccountNumber() });
    }

    /**
     * Add a transaction.
     * 
     * @param transaction
     *            The transaction being added.
     */
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
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    /**
     * Gets all transactions by account number.
     * 
     * @param accountNumber
     *            The account number being searched for.
     * @return An arraylist of transactions.
     */
    public ArrayList<Transaction> getAllTransactions(String accountNumber) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        cursor = db.query(true, DATABASE_TABLE_TRANSACTION, null, KEY_ACCOUNT
                + "=?", new String[] { accountNumber }, null, null, KEY_ACCOUNT
                + " ASC", null);

        ArrayList<Transaction> transactionList = new ArrayList<Transaction>();
        Transaction transaction = null;

        if (cursor != null) {
            Log.d("cursor size - transaction: ", cursor.getCount() + "");
            for (int i = 0; i < cursor.getCount(); i++) {
                if (cursor.moveToNext()) {
                    transaction = new Transaction(cursor.getString(0),
                            cursor.getString(1), Float.parseFloat(cursor
                                    .getString(2)), cursor.getString(3),
                            cursor.getString(4), cursor.getString(5));
                    transactionList.add(transaction);
                }
            }
        }
        return transactionList;
    }

    /**
     * Gets all transactions by username.
     * 
     * @param username
     *            The username being searched for.
     * @return An arraylist of transactions.
     */
    public ArrayList<Transaction> getAllTransactionsByUsername(String username) {
        ArrayList<Transaction> fullTransactionList = new ArrayList<Transaction>();
        List<Account> accountList = getAccountsByUsername(username);

        for (Account a : accountList) {
            String accountNumber = a.getAccountNumber();
            ArrayList<Transaction> transactionList = getAllTransactions(accountNumber);
            for (Transaction t : transactionList) {
                fullTransactionList.add(t);
            }
        }

        return fullTransactionList;
    }
}
