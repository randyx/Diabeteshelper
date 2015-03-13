package mconnect.mdiabetes.framework;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;



public class DataProvider extends ContentProvider{
	private static final String DATABASE_NAME = "DiabetesHelper.db";
	private static final int DATABASE_VERSION = 2;
	private static final String TABLE_NAME_1 = "RecordTable";
	private static final String TABLE_NAME = "reminder";
	private static final String TABLE_NAME_2 = "GlucoseRange";
	private static final String TABLE_NAME_3 = "MealTime";
	private static final String TABLE_NAME_4 = "BMITable";
	
	private static class DatabaseHelper extends SQLiteOpenHelper {
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			
		}
		@Override public void onCreate(SQLiteDatabase db) {
			
			Cursor cur=db.query("SQLITE_MASTER", null, null, null, null, null, null);
			
			Integer n=cur.getCount();
			
			
			if(n==1){
				db.execSQL("CREATE TABLE " + TABLE_NAME_1 + " ("+ "name" + " TEXT," + "value" + " TEXT,"+"date"+" TEXT,"+"time"+" TEXT,"+"tag"+" TEXT,"+"note"+" TEXT,"+"label"+" TEXT" +");");
				db.execSQL("CREATE TABLE " + TABLE_NAME + " ("+ "name" + " TEXT," + "dosage" + " TEXT,"+"time"+" TEXT,"+"music"+" TEXT" + ");");
				db.execSQL("CREATE TABLE " + TABLE_NAME_2 + " ("+ "name" + " TEXT," +"value"+" TEXT" + ");");
				db.execSQL("CREATE TABLE " + TABLE_NAME_3 + " ("+ "name" + " TEXT," +"time"+" TEXT" + ");");
				db.execSQL("CREATE TABLE " + TABLE_NAME_4 + " ("+ "sex" + " TEXT," +"bmi"+" TEXT" + ");");
				try {
					
					db.execSQL("insert into "+ TABLE_NAME_2 +" (name, value) values('0', '90');"); 
					db.execSQL("insert into "+ TABLE_NAME_2 +" (name, value) values('1', '140');");
					
					db.execSQL("insert into "+ TABLE_NAME_2 +" (name, value) values('2', '90');"); 
					db.execSQL("insert into "+ TABLE_NAME_2 +" (name, value) values('3', '180');");
					
					db.execSQL("insert into "+ TABLE_NAME_2 +" (name, value) values('4', '90');"); 
					db.execSQL("insert into "+ TABLE_NAME_2 +" (name, value) values('5', '140');");
					
					db.execSQL("insert into "+ TABLE_NAME_2 +" (name, value) values('6', '90');"); 
					db.execSQL("insert into "+ TABLE_NAME_2 +" (name, value) values('7', '180');");
					
					db.execSQL("insert into "+ TABLE_NAME_2 +" (name, value) values('8', '90');"); 
					db.execSQL("insert into "+ TABLE_NAME_2 +" (name, value) values('9', '140');");
					
					db.execSQL("insert into "+ TABLE_NAME_2 +" (name, value) values('10', '90');"); 
					db.execSQL("insert into "+ TABLE_NAME_2 +" (name, value) values('11', '180');");
					
					db.execSQL("insert into "+ TABLE_NAME_2 +" (name, value) values('12', '90');"); 
					db.execSQL("insert into "+ TABLE_NAME_2 +" (name, value) values('13', '140');");
					
					
					db.execSQL("insert into "+ TABLE_NAME_3 +" (name, time) values('0', '04:00');");
					db.execSQL("insert into "+ TABLE_NAME_3 +" (name, time) values('1', '07:00');");
					db.execSQL("insert into "+ TABLE_NAME_3 +" (name, time) values('2', '10:00');");
					db.execSQL("insert into "+ TABLE_NAME_3 +" (name, time) values('3', '13:00');");
					db.execSQL("insert into "+ TABLE_NAME_3 +" (name, time) values('4', '16:00');");
					db.execSQL("insert into "+ TABLE_NAME_3 +" (name, time) values('5', '19:00');");
					db.execSQL("insert into "+ TABLE_NAME_3 +" (name, time) values('6', '22:00');");
					
					/*db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','158','2012-05-13','03:32','0','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','178','2012-05-13','07:02','1','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','112','2012-05-13','11:12','2','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','124','2012-05-13','13:12','3','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','92','2012-05-13','17:12','4','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','106','2012-05-13','19:17','5','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','86','2012-05-13','23:02','6','','1');");
					
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','128','2012-05-14','03:32','0','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','166','2012-05-14','07:03','1','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','168','2012-05-14','11:12','2','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','108','2012-05-14','13:12','3','','1');");
					//db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','92','2011-02-26','17:12','4','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','102','2012-05-14','20:51','5','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','116','2012-05-14','23:02','6','','1');");
					
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','88','2012-05-15','03:32','0','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','102','2012-05-15','07:02','1','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','108','2012-05-15','11:12','2','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','130','2012-05-15','13:12','3','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','154','2012-05-15','17:12','4','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','140','2012-05-15','19:17','5','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','84','2012-05-15','23:02','6','','1');");
					
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','172','2012-05-16','03:32','0','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','182','2012-05-16','07:02','1','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','160','2012-05-16','11:12','2','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','102','2012-05-16','13:12','3','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','118','2012-05-16','17:12','4','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','72','2012-05-16','19:17','5','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','150','2012-05-16','23:02','6','','1');");
					
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','230','2012-05-17','03:32','0','','1');");
					//db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','178','2011-02-23','07:02','1','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','160','2012-05-17','11:12','2','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','178','2012-05-17','13:12','3','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','92','2012-05-17','17:12','4','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','92','2012-05-17','19:17','5','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','66','2012-05-17','23:02','6','','1');");
					
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','236','2012-05-18','03:32','0','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','86','2012-05-18','07:02','1','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','196','2012-05-18','11:12','2','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','164','2012-05-18','13:12','3','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','154','2012-05-18','17:12','4','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','180','2012-05-18','19:17','5','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','150','2012-05-18','23:02','6','','1');");
					
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','140','2012-05-19','03:51','0','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','108','2012-05-19','07:06','1','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','136','2012-05-19','11:12','2','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','216','2012-05-19','13:12','3','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','136','2012-05-19','17:12','4','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','136','2012-05-19','19:17','5','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','172','2012-05-19','23:02','6','','1');");
					
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','94','2012-05-20','03:32','0','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','84','2012-05-20','08:24','1','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','164','2012-05-20','11:12','2','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','128','2012-05-20','13:12','3','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','92','2012-05-20','17:12','4','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','172','2012-05-20','19:17','5','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','130','2012-05-20','23:02','6','','1');");
				
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','94','2012-05-21','03:32','0','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','84','2012-05-21','08:24','1','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','164','2012-05-21','11:12','2','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','128','2012-05-21','13:12','3','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','92','2012-05-21','17:12','4','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','172','2012-05-21','19:17','5','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','130','2012-05-21','23:02','6','','1');");
					
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','94','2012-05-22','03:32','0','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','84','2012-05-22','08:24','1','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','164','2012-05-22','11:12','2','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','128','2012-05-22','13:12','3','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','92','2012-05-22','17:12','4','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','172','2012-05-22','19:17','5','','1');");
					db.execSQL("insert into "+TABLE_NAME_1+ " (name,value,date,time,tag,note,label) values('Glocuse','130','2012-05-22','23:02','6','','1');");*/
				} 
				catch (SQLException e) { 
					Log.e("ERROR", e.toString()); 
				}
			}
		}
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			
		}

		
	}
	private DatabaseHelper mOpenHelper;
	@Override public boolean onCreate() {
	mOpenHelper = new DatabaseHelper(getContext());
	return true;
	}
	
	
	
	@Override public Cursor query(Uri uri, String[] projection, String selection,String[] selectionArgs, String sortOrder) {
		SQLiteDatabase db = mOpenHelper.getReadableDatabase();
		
		String name=uri.toString().split("/")[3];
		
		Cursor c = db.query(name, projection, selection, null, null, null, sortOrder);
		return c;
	}
	@Override public String getType(Uri uri){ 
		return null; 
	}
	@Override public Uri insert(Uri uri, ContentValues initialValues)
	{ 
		String name=uri.toString().split("/")[3];
		mOpenHelper.getWritableDatabase().insert(name, "", initialValues);
		return null;
	}
	@Override public int delete(Uri uri, String where, String[] whereArgs)
	{ 
		String name=uri.toString().split("/")[3];
		mOpenHelper.getWritableDatabase().delete(name, where, whereArgs);
		return 0;
	}
	@Override public int update(Uri uri, ContentValues values,String where, String[] whereArgs)
	{ 
		String name=uri.toString().split("/")[3];
		mOpenHelper.getWritableDatabase().update(name, values,where, whereArgs);
		return 0; 
	}

}
