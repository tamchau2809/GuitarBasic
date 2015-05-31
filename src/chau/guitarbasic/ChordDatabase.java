package chau.guitarbasic;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class ChordDatabase extends SQLiteOpenHelper {

	// Báº£ng sv
	public static final String tb_Sinhvien = "qlsv";

	public static String DB_PATH = "/data/data/chau.guitarbasic/databases/";
	private static String DB_NAME = "chords.db";
	private SQLiteDatabase database;
	private final Context mContext;

	public ChordDatabase(Context con) {
		super(con, DB_NAME, null, 1);
		this.mContext = con;
	}

	/**
	 * copy database from assets to the device if not existed
	 * 
	 * @return true if not exist and create database success
	 * @throws IOException
	 */
	public boolean isCreatedDatabase() throws IOException {
		// Default lÃ  Ä‘Ã£ cÃ³ DB
		boolean result = true;
		// Náº¿u chÆ°a tá»“n táº¡i DB thÃ¬ copy tá»« Asses vÃ o Data
		if (!checkExistDataBase()) {
			 this.getReadableDatabase();
			try {
				copyDataBase();
				result = false;
			} catch (Exception e) {
				throw new Error("Error copying database");
			}
		}

		return result;
	}

	/**
	 * check whether database exist on the device?
	 * 
	 * @return true if existed
	 */
	private boolean checkExistDataBase() {

		try {
			String myPath = DB_PATH + DB_NAME;
			File fileDB = new File(myPath);

			if (fileDB.exists()) {
				return true;
			} else
				return false;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * copy database from assets folder to the device
	 * 
	 * @throws IOException
	 */
	private void copyDataBase() throws IOException {
		InputStream myInput = mContext.getAssets().open(DB_NAME);
		OutputStream myOutput = new FileOutputStream(DB_PATH + DB_NAME);
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}

		myOutput.flush();
		myOutput.close();
		myInput.close();
	}

	/**
	 * delete database file
	 * 
	 * @return
	 */
	public boolean deleteDatabase() {
		File file = new File(DB_PATH + DB_NAME);
		return file.delete();
	}

	/**
	 * open database
	 * 
	 * @throws SQLException
	 */
	public void openDataBase() throws SQLException {
		database = SQLiteDatabase.openDatabase(DB_PATH + DB_NAME, null,
				SQLiteDatabase.OPEN_READWRITE);
	}

	@Override
	public synchronized void close() {
		if (database != null)
			database.close();
		super.close();
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// do nothing
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// do nothing
		db.execSQL(DB_NAME);
		onCreate(db);
	}

	public int deleteData_From_Table(String tbName) {

		int result = 0;
		try {
			openDataBase();
			database.beginTransaction();
			result = database.delete(tbName, null, null);
			if (result >= 0) {
				database.setTransactionSuccessful();
			}
		} catch (Exception e) {
			database.endTransaction();
			close();
		} finally {
			database.endTransaction();
			close();
		}

		return result;
	}

	/**
	 * la danh sach hop am
	 * 
	 * @return
	 */
	public ArrayList<Object> getAllChord() {
		ArrayList<Object> rs = new ArrayList<Object>();

		try {
			openDataBase();

			String[] columns = { "ID", "Ten", "HopAm" };
			
			Cursor cursor = database.query(tb_Sinhvien, columns, null, null,
					null, null, null);

			
			while (cursor.moveToNext()) {
				ChordItem emp = new ChordItem();
				emp.setiD(cursor.getString(0));

				emp.setTen(cursor.getString(1));
				
				emp.setHA(cursor.getString(2));

				
				rs.add(emp);
			}
			cursor.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close();
		}
		return rs;
	}
	
	public Cursor getStudentByID(String inputText) throws SQLException {
		  Cursor mCursor = null;
		  if (inputText == null  ||  inputText.length () == 0)  {
		   mCursor = database.query(tb_Sinhvien, new String[] {"ID", "Ten", "HopAm"}, 
		     null, null, null, null, null);
		 
		  }
		  else {
		   mCursor = database.query(true, tb_Sinhvien, new String[] {"ID", "Ten", "HopAm"}, 
		     "ID" + " like '%" + inputText + "%'", null,
		     null, null, null, null);
		  }
		  if (mCursor != null) {
		   mCursor.moveToFirst();
		  }
		  return mCursor;
		 
		 }
}
