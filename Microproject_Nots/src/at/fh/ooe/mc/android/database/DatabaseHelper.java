package at.fh.ooe.mc.android.database;

import java.util.LinkedList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import at.fh.ooe.mc.android.Note;

public class DatabaseHelper extends SQLiteOpenHelper {

	private final static String LOG_TAG = "DatabaseHelper";
	public static final String KEY_ROWID = "_id";
	public static final String KEY_TITLE = "notetitle";
	public static final String KEY_MESSAGE = "notemessage";
	public static final String KEY_DATE = "notedate";
	private static final String[] COLUMNS = {KEY_ROWID,KEY_TITLE,KEY_MESSAGE, KEY_DATE};
	
	public static final String DATABASE_NAME = "notesdb";
	public static final String DATABASE_TABLE = "TextNote";
	public static final int DATABASE_VERSION = 1;

	public DatabaseHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {

		String CREATE_CONTACTS_TABLE = "CREATE TABLE IF NOT EXISTS "
				+ DATABASE_TABLE + "(" + KEY_ROWID
				+ " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_TITLE
				+ " TEXT, " + KEY_MESSAGE + " TEXT, " + KEY_DATE + " TEXT  )";
		db.execSQL(CREATE_CONTACTS_TABLE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXITS " + DATABASE_TABLE);
		onCreate(db);

	}

	public void addNote(Note note) {
		Log.d("DatabaseHelper", note.toString());
		// 1. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();

		// 2. create ContentValues to add key "column"/value
		ContentValues values = new ContentValues();
		values.put(KEY_TITLE, note.getTitle()); // get title
		values.put(KEY_MESSAGE, note.getText()); // get author

		// 3. insert
		db.insert(DATABASE_TABLE, // table
				null, // nullColumnHack
				values); // key/value -> keys = column names/ values = column
							// values

		// 4. close
		db.close();
	}
	
	public Note getNote(int id){
		 
	    // 1. get reference to readable DB
	    SQLiteDatabase db = this.getReadableDatabase();
	 
	    // 2. build query
	    Cursor cursor = 
	            db.query(DATABASE_TABLE, // a. table
	            COLUMNS, // b. column names
	            " _id = ?", // c. selections 
	            new String[] { String.valueOf(id) }, // d. selections args
	            null, // e. group by
	            null, // f. having
	            null, // g. order by
	            null); // h. limit
	 
	    // 3. if we got results get the first one
	    if (cursor != null)
	        cursor.moveToFirst();
	 
	    // 4. build note object
	    Note note = new Note();
	    note.setId(Integer.parseInt(cursor.getString(0)));
	    note.setTitle(cursor.getString(1));
	    note.setText(cursor.getString(2));
	 
	    //log 
	Log.d("getBook("+id+")", note.toString());
	 
	    // 5. return note
	    return note;
	}

	// Get All Notes
	public List<Note> getAllNotes() {
		List<Note> notes = new LinkedList<Note>();

		// 1. build the query
		String query = "SELECT  * FROM " + DATABASE_TABLE;

		// 2. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);

		// 3. go over each row, build book and add it to list
		Note note = null;
		if (cursor.moveToFirst()) {
			do {
				note = new Note();
				note.setId(Integer.parseInt(cursor.getString(0)));
				note.setTitle(cursor.getString(1));
				note.setText(cursor.getString(2));

				// Add book to books
				notes.add(note);
			} while (cursor.moveToNext());
		}

		Log.d(LOG_TAG, "getallNotes()");

		// return books
		return notes;
	}

	public Cursor getAllNotesCursor() {
		// 1. build the query
		String query = "SELECT  * FROM " + DATABASE_TABLE;

		// 2. get reference to writable DB
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(query, null);
		Log.d(LOG_TAG, "getCursorof All Notes()");

		// return notes
		return cursor;
	}

	public void delete(long id) {
		SQLiteDatabase db = this.getReadableDatabase();
		db.delete(DATABASE_TABLE, KEY_ROWID + " = ?",
				new String[] { String.valueOf(id) });
		db.close();
	}
}
