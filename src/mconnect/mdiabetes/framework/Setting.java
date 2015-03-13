package mconnect.mdiabetes.framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mconnect.mdiabetes.framework.R;




import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;

import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.AdapterView.OnItemClickListener;

public class Setting extends Activity {
    /** Called when the activity is first created. */
	public static final String AUTHORITY = "com.chen.provider.diabeteshelper";
	public static final Uri CONTENT_URI =Uri.parse("content://" + AUTHORITY + "/GlucoseRange");	
	private static final String[] PROJECTION = new String[] {"name", "value"};
	
	public static final Uri CONTENT_URI_1 =Uri.parse("content://" + AUTHORITY + "/MealTime");	
	private static final String[] PROJECTION_1 = new String[] {"name", "time"};
	
	private ArrayList<Map<String, Object>> coll;
	
	private ListView lv1;
	private EditText[] gtlv;
	
	//private String[] Strings = {"Pre-Breakfast","Post-Breakfast","Pre-Lunch","Post-Lunch","Pre-Dinner","Post-Dinner","Night"};
	private String Strings[]={"早饭前",
	        "早饭后",
	        "午饭前",
	        "午饭后",
	        "晚饭前",
	        "晚饭后",
	        "夜晚"};
	//private ListView lv2;
	private int hour;
	private int minute;
	
	private String shour;
	private String sminute;
	
	private int mealtimeposition=0;
	
	private ListView lv;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.settingmain);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.i_title);
        TextView title = (TextView) findViewById(R.id.title);
        Button leftBtn = (Button) findViewById(R.id.title_left_btn);
        Button rightBtn = (Button) findViewById(R.id.title_right_btn);
        //title.setText("糖尿病信息");
        leftBtn.setVisibility(View.GONE);
        rightBtn.setText("返回");
        rightBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
        	
        });
        
        
        LinearLayout t=new LinearLayout(this);
        t.setVerticalScrollBarEnabled(true);
        
        lv1=(ListView)this.findViewById(R.id.list_range);
        lv1.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, mStrings));
        
        lv1.setOnItemClickListener(
        	new AdapterView.OnItemClickListener()  
     		{ 
     		    @Override
				public void onItemClick(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
     		    	if(arg2==0){
     		    		//Intent in = new Intent(Setting.this, MealScheadul.class);
     	    			//startActivity(in);
     		    		LayoutInflater factory = LayoutInflater.from(getBaseContext());
     		    		final View textEntryView = factory.inflate(R.layout.mealtime, null);
     		    		lv=(ListView)textEntryView.findViewById(R.id.mealtime_lv);
     		    		Cursor cur = getContentResolver().query(CONTENT_URI_1,PROJECTION_1, null, null, null);
     	                cur.moveToFirst();
     	                coll= new ArrayList<Map<String, Object>>();
     	                Map<String, Object> item;
     	                int i=0;
     	                while(!cur.isAfterLast()) {
     	                	item=new HashMap<String, Object>();
     	                	item.put("name", Strings[i]);
     	                	item.put("time", cur.getString(1));
     	                	
     	                	coll.add(item);
     	                	i++;
     	                	cur.moveToNext(); 
     	                }
     	               lv.setAdapter(new SimpleAdapter(textEntryView.getContext(), coll,R.layout.scheadul, new String[] { "name","time" },new int[] {R.id.scheadul_name,R.id.scheadul_text}));
     	               lv.setOnItemClickListener(new OnItemClickListener(){

     	            	   @Override
     	            	   public void onItemClick(AdapterView<?> arg0, View arg1,
     	            			   int arg2, long arg3) {
     	            		   // TODO Auto-generated method stub
     	            		   mealtimeposition=arg2;
     	            		   showDialog(1);
     	            	   }
     	            	   
     	               });
     	               
     	              new AlertDialog.Builder(Setting.this)
     	             //.setIcon(R.drawable.stopwatch)
     	             .setTitle("进餐时间")
     	             .setView(textEntryView)
     	             .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
     	                 public void onClick(DialogInterface dialog, int whichButton) 
     	                 {
     	                	ContentValues initialValues;
     	                	 for(int j=0;j<7;j++){
     	                		initialValues= new ContentValues();
	                        		//initialValues.put("name", title[name_position]);
	                        	initialValues.put("time", coll.get(j).get("time").toString());
	                        	getContentResolver().update(CONTENT_URI_1, initialValues, "name='"+j+"'", null);
     	                	 }
     	                 }
     	             })
     	             .setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
     	                 public void onClick(DialogInterface dialog, int whichButton) {

     	                     
     	                 }
     	             })
     	             .create().show();
     		    	}
     		    	else if(arg2==1){
     		    		LayoutInflater factory = LayoutInflater.from(getBaseContext());
     	                final View textEntryView = factory.inflate(R.layout.glucosetargets, null);
     	                gtlv=new EditText[14];
     	                for(int i=0;i<14;i++){
     	                	gtlv[i]=(EditText)textEntryView.findViewById(gtl_value[i]);
     	                }
     	                
     	                Cursor cur = getContentResolver().query(CONTENT_URI,PROJECTION, null, null, null);
     	                cur.moveToFirst();
     	                int k=0;
     	                while(!cur.isAfterLast()) {
     	                	gtlv[k].setText(cur.getString(1));
     	                	k++;
     	                	cur.moveToNext(); 
     	                }
     	                new AlertDialog.Builder(Setting.this)
     	                    .setTitle("血压范围")
     	                    .setView(textEntryView)
     	                    .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
     	                    	
     	                        public void onClick(DialogInterface dialog, int whichButton) {
     	                        	ContentValues initialValues;
     	                        	for(int j=0;j<14;j++){
     	                        		initialValues= new ContentValues();
     	                        		//initialValues.put("name", title[name_position]);
     	                        		initialValues.put("value", gtlv[j].getText().toString());
     	                        		getContentResolver().update(CONTENT_URI, initialValues, "name='"+j+"'", null);
     	                        	}
     	                        	      
     	                        }
     	                    })
     	                    .setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
     	                        public void onClick(DialogInterface dialog, int whichButton) {

     	                            
     	                        }
     	                    })
     	                    .create().show();
     		    	}
     		    	else if(arg2==2){
     		    		
     	                new AlertDialog.Builder(Setting.this)
     	                    .setIcon(android.R.drawable.arrow_down_float)
     	                    .setTitle("您确定要导入数据吗？")
     	                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
     	                        public void onClick(DialogInterface dialog, int whichButton) {
     	                        	
     	                        	addRecord();
     	        
     	                           
     	                        }
     	                    })
     	                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
     	                        public void onClick(DialogInterface dialog, int whichButton) {

     	                            
     	                        }
     	                    })
     	                    .create().show();
     		    	}
     		    	else if(arg2==3){
     		    		new AlertDialog.Builder(Setting.this)
 	                    .setIcon(android.R.drawable.arrow_down_float)
 	                    .setTitle("您确定要清除数据吗？")
 	                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
 	                        public void onClick(DialogInterface dialog, int whichButton) {
 	                        	
 	                        	deleteRecord();
 	        
 	                           
 	                        }
 	                    })
 	                    .setNegativeButton("取消", new DialogInterface.OnClickListener() {
 	                        public void onClick(DialogInterface dialog, int whichButton) {

 	                            
 	                        }
 	                    })
 	                    .create().show();
     		    	}
     		    }
     		}
        );
    }
    
    private void deleteRecord(){
    	Uri RECORD_URI =Uri.parse("content://" + AUTHORITY + "/RecordTable");
    	getContentResolver().delete(RECORD_URI, "", null);
    }
    
    private void addRecord(){
    	Uri RECORD_URI =Uri.parse("content://" + AUTHORITY + "/RecordTable");
    	ContentValues initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "124.4");
		initialValues.put("date", "2012-03-31");
		initialValues.put("time", "05:00");
		initialValues.put("tag", "5");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "199.8");
		initialValues.put("date", "2012-03-29");
		initialValues.put("time", "07:02");
		initialValues.put("tag", "1");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "118.8");
		initialValues.put("date", "2012-03-28");
		initialValues.put("time", "05:03");
		initialValues.put("tag", "2");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "79.2");
		initialValues.put("date", "2012-03-28");
		initialValues.put("time", "05:10");
		initialValues.put("tag", "4");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "126.0");
		initialValues.put("date", "2012-03-28");
		initialValues.put("time", "05:26");
		initialValues.put("tag", "5");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "124");
		initialValues.put("date", "2012-03-27");
		initialValues.put("time", "05:28");
		initialValues.put("tag", "5");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "82.7");
		initialValues.put("date", "2012-03-27");
		initialValues.put("time", "05:29");
		initialValues.put("tag", "4");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "104.4");
		initialValues.put("date", "2012-03-26");
		initialValues.put("time", "05:30");
		initialValues.put("tag", "3");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "79.2");
		initialValues.put("date", "2012-03-25");
		initialValues.put("time", "05:31");
		initialValues.put("tag", "0");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "147.6");
		initialValues.put("date", "2012-03-24");
		initialValues.put("time", "05:33");
		initialValues.put("tag", "3");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "131.4");
		initialValues.put("date", "2012-03-23");
		initialValues.put("time", "05:34");
		initialValues.put("tag", "3");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "147.6");
		initialValues.put("date", "2012-03-22");
		initialValues.put("time", "05:36");
		initialValues.put("tag", "3");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "118.8");
		initialValues.put("date", "2012-03-22");
		initialValues.put("time", "05:36");
		initialValues.put("tag", "2");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "79.2");
		initialValues.put("date", "2012-03-21");
		initialValues.put("time", "05:37");
		initialValues.put("tag", "0");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "88.2");
		initialValues.put("date", "2012-03-20");
		initialValues.put("time", "05:38");
		initialValues.put("tag", "4");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "124.2");
		initialValues.put("date", "2012-03-19");
		initialValues.put("time", "05:39");
		initialValues.put("tag", "5");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "127.7");
		initialValues.put("date", "2012-03-18");
		initialValues.put("time", "05:39");
		initialValues.put("tag", "3");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "104.4");
		initialValues.put("date", "2012-03-17");
		initialValues.put("time", "05:39");
		initialValues.put("tag", "6");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "70.2");
		initialValues.put("date", "2012-03-16");
		initialValues.put("time", "05:40");
		initialValues.put("tag", "0");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "46.8");
		initialValues.put("date", "2012-03-15");
		initialValues.put("time", "05:41");
		initialValues.put("tag", "6");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "158.4");
		initialValues.put("date", "2012-03-14");
		initialValues.put("time", "05:41");
		initialValues.put("tag", "5");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "158.4");
		initialValues.put("date", "2012-03-13");
		initialValues.put("time", "05:42");
		initialValues.put("tag", "5");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "104.4");
		initialValues.put("date", "2012-03-12");
		initialValues.put("time", "05:43");
		initialValues.put("tag", "3");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "39.6");
		initialValues.put("date", "2012-03-11");
		initialValues.put("time", "05:43");
		initialValues.put("tag", "6");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "208.8");
		initialValues.put("date", "2012-03-11");
		initialValues.put("time", "05:43");
		initialValues.put("tag", "3");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "176.4");
		initialValues.put("date", "2012-03-10");
		initialValues.put("time", "05:43");
		initialValues.put("tag", "3");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "91.8");
		initialValues.put("date", "2012-03-09");
		initialValues.put("time", "05:43");
		initialValues.put("tag", "3");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "145.8");
		initialValues.put("date", "2012-03-08");
		initialValues.put("time", "05:44");
		initialValues.put("tag", "5");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "133.2");
		initialValues.put("date", "2012-03-07");
		initialValues.put("time", "05:44");
		initialValues.put("tag", "5");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "111.6");
		initialValues.put("date", "2012-03-06");
		initialValues.put("time", "05:44");
		initialValues.put("tag", "2");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "147.6");
		initialValues.put("date", "2012-03-05");
		initialValues.put("time", "05:44");
		initialValues.put("tag", "0");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "95.4");
		initialValues.put("date", "2012-03-04");
		initialValues.put("time", "05:44");
		initialValues.put("tag", "0");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "147.3");
		initialValues.put("date", "2012-03-03");
		initialValues.put("time", "05:45");
		initialValues.put("tag", "0");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "136");
		initialValues.put("date", "2012-03-02");
		initialValues.put("time", "05:45");
		initialValues.put("tag", "0");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "86.5");
		initialValues.put("date", "2012-03-01");
		initialValues.put("time", "05:45");
		initialValues.put("tag", "0");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "161");
		initialValues.put("date", "2012-02-29");
		initialValues.put("time", "05:46");
		initialValues.put("tag", "5");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "151");
		initialValues.put("date", "2012-02-28");
		initialValues.put("time", "05:46");
		initialValues.put("tag", "3");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "84.2");
		initialValues.put("date", "2012-02-27");
		initialValues.put("time", "05:46");
		initialValues.put("tag", "0");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "140.4");
		initialValues.put("date", "2012-02-26");
		initialValues.put("time", "05:47");
		initialValues.put("tag", "5");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "167");
		initialValues.put("date", "2012-02-25");
		initialValues.put("time", "05:47");
		initialValues.put("tag", "5");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "142");
		initialValues.put("date", "2012-02-24");
		initialValues.put("time", "05:47");
		initialValues.put("tag", "3");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "121");
		initialValues.put("date", "2012-02-23");
		initialValues.put("time", "05:47");
		initialValues.put("tag", "0");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "90.1");
		initialValues.put("date", "2012-02-21");
		initialValues.put("time", "05:48");
		initialValues.put("tag", "0");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "65.1");
		initialValues.put("date", "2012-02-20");
		initialValues.put("time", "05:48");
		initialValues.put("tag", "6");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "161");
		initialValues.put("date", "2012-02-19");
		initialValues.put("time", "05:49");
		initialValues.put("tag", "5");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "138.1");
		initialValues.put("date", "2012-02-18");
		initialValues.put("time", "05:49");
		initialValues.put("tag", "2");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "81");
		initialValues.put("date", "2012-02-17");
		initialValues.put("time", "05:49");
		initialValues.put("tag", "0");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "118.5");
		initialValues.put("date", "2012-02-16");
		initialValues.put("time", "05:50");
		initialValues.put("tag", "4");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "141");
		initialValues.put("date", "2012-02-15");
		initialValues.put("time", "05:50");
		initialValues.put("tag", "2");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "97.1");
		initialValues.put("date", "2012-02-14");
		initialValues.put("time", "05:50");
		initialValues.put("tag", "0");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "164.1");
		initialValues.put("date", "2012-02-13");
		initialValues.put("time", "05:50");
		initialValues.put("tag", "0");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "109.1");
		initialValues.put("date", "2012-02-12");
		initialValues.put("time", "05:50");
		initialValues.put("tag", "6");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "101");
		initialValues.put("date", "2012-02-11");
		initialValues.put("time", "05:50");
		initialValues.put("tag", "4");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "112.1");
		initialValues.put("date", "2012-02-10");
		initialValues.put("time", "05:50");
		initialValues.put("tag", "4");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "134.1");
		initialValues.put("date", "2012-02-09");
		initialValues.put("time", "05:51");
		initialValues.put("tag", "4");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "189.2");
		initialValues.put("date", "2012-02-08");
		initialValues.put("time", "05:51");
		initialValues.put("tag", "0");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "191");
		initialValues.put("date", "2012-02-07");
		initialValues.put("time", "05:51");
		initialValues.put("tag", "3");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "132");
		initialValues.put("date", "2012-02-06");
		initialValues.put("time", "05:51");
		initialValues.put("tag", "4");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "42.2");
		initialValues.put("date", "2012-02-05");
		initialValues.put("time", "05:51");
		initialValues.put("tag", "0");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "86.2");
		initialValues.put("date", "2012-02-04");
		initialValues.put("time", "05:51");
		initialValues.put("tag", "0");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "151");
		initialValues.put("date", "2012-02-03");
		initialValues.put("time", "05:52");
		initialValues.put("tag", "3");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "84.2");
		initialValues.put("date", "2012-02-02");
		initialValues.put("time", "05:52");
		initialValues.put("tag", "6");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "101");
		initialValues.put("date", "2012-02-01");
		initialValues.put("time", "05:52");
		initialValues.put("tag", "2");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "73.1");
		initialValues.put("date", "2012-01-31");
		initialValues.put("time", "05:52");
		initialValues.put("tag", "6");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "118.8");
		initialValues.put("date", "2012-01-30");
		initialValues.put("time", "05:52");
		initialValues.put("tag", "3");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "127.1");
		initialValues.put("date", "2012-01-29");
		initialValues.put("time", "05:53");
		initialValues.put("tag", "3");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "95.1");
		initialValues.put("date", "2012-01-28");
		initialValues.put("time", "05:53");
		initialValues.put("tag", "3");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "145.1");
		initialValues.put("date", "2012-01-27");
		initialValues.put("time", "05:53");
		initialValues.put("tag", "3");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "104.1");
		initialValues.put("date", "2012-01-26");
		initialValues.put("time", "05:53");
		initialValues.put("tag", "3");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "135.1");
		initialValues.put("date", "2012-01-25");
		initialValues.put("time", "05:53");
		initialValues.put("tag", "3");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "81.2");
		initialValues.put("date", "2012-01-24");
		initialValues.put("time", "05:54");
		initialValues.put("tag", "2");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "142.2");
		initialValues.put("date", "2012-01-23");
		initialValues.put("time", "05:54");
		initialValues.put("tag", "0");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "122.4");
		initialValues.put("date", "2012-01-22");
		initialValues.put("time", "05:54");
		initialValues.put("tag", "2");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "142");
		initialValues.put("date", "2012-01-21");
		initialValues.put("time", "05:57");
		initialValues.put("tag", "3");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "134");
		initialValues.put("date", "2012-01-20");
		initialValues.put("time", "05:57");
		initialValues.put("tag", "3");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "158.6");
		initialValues.put("date", "2012-01-19");
		initialValues.put("time", "05:57");
		initialValues.put("tag", "3");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "165.6");
		initialValues.put("date", "2012-01-18");
		initialValues.put("time", "05:57");
		initialValues.put("tag", "3");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "198");
		initialValues.put("date", "2012-01-17");
		initialValues.put("time", "05:58");
		initialValues.put("tag", "2");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "144.2");
		initialValues.put("date", "2012-01-16");
		initialValues.put("time", "05:58");
		initialValues.put("tag", "3");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "132");
		initialValues.put("date", "2012-01-15");
		initialValues.put("time", "05:58");
		initialValues.put("tag", "2");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "172.8");
		initialValues.put("date", "2012-01-14");
		initialValues.put("time", "05:58");
		initialValues.put("tag", "3");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "122.4");
		initialValues.put("date", "2012-01-13");
		initialValues.put("time", "05:58");
		initialValues.put("tag", "3");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "172.8");
		initialValues.put("date", "2012-01-12");
		initialValues.put("time", "05:58");
		initialValues.put("tag", "3");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "88.2");
		initialValues.put("date", "2012-01-11");
		initialValues.put("time", "05:58");
		initialValues.put("tag", "0");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "122.1");
		initialValues.put("date", "2012-01-10");
		initialValues.put("time", "05:59");
		initialValues.put("tag", "2");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "84.6");
		initialValues.put("date", "2012-01-09");
		initialValues.put("time", "05:59");
		initialValues.put("tag", "6");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "167.1");
		initialValues.put("date", "2012-01-08");
		initialValues.put("time", "05:59");
		initialValues.put("tag", "5");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "161");
		initialValues.put("date", "2012-01-07");
		initialValues.put("time", "05:59");
		initialValues.put("tag", "3");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "172.1");
		initialValues.put("date", "2012-01-06");
		initialValues.put("time", "05:59");
		initialValues.put("tag", "1");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "118.1");
		initialValues.put("date", "2012-01-04");
		initialValues.put("time", "06:00");
		initialValues.put("tag", "3");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "73.1");
		initialValues.put("date", "2012-01-03");
		initialValues.put("time", "06:00");
		initialValues.put("tag", "0");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "141");
		initialValues.put("date", "2012-01-02");
		initialValues.put("time", "06:00");
		initialValues.put("tag", "3");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
		
		initialValues = new ContentValues();
		initialValues.put("name", "Glocuse");
		initialValues.put("value", "171");
		initialValues.put("date", "2012-01-01");
		initialValues.put("time", "06:00");
		initialValues.put("tag", "5");
		initialValues.put("note", "");
		initialValues.put("label", "1");
		getContentResolver().insert(RECORD_URI, initialValues);
    }
    
    //private String[] mStrings = {"Meal Schedule","Target Ranges","Hypo/Hypr Limits"};
    private String[] mStrings = {"进餐时间","血压范围","导入数据","清除数据"};
    private int[] gtl_value={R.id.gtl_1,R.id.gtl_2,R.id.gtl_3,R.id.gtl_4,R.id.gtl_5,R.id.gtl_6,R.id.gtl_7,R.id.gtl_8,R.id.gtl_9,R.id.gtl_10,R.id.gtl_11,R.id.gtl_12,R.id.gtl_13,R.id.gtl_14,};

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case 1:
                return new TimePickerDialog(this,
                        mTimeSetListener, hour, minute, false);
        }
        return null;
    }
    
    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
        new TimePickerDialog.OnTimeSetListener() {

            public void onTimeSet(TimePicker view, int hourOfDay, int minut) {
                hour = hourOfDay;
                minute = minut;
                if(hour<10){
                	shour="0"+hour;
                }
                else{
                	shour=""+hour;
                }
                if(minute<10){
                	sminute="0"+minute;
                }
                else {
                	sminute=""+minute;
                }
                coll.get(mealtimeposition).put("time", shour+":"+sminute);
                lv.setAdapter(new SimpleAdapter(getBaseContext(), coll,R.layout.scheadul, new String[] { "name","time" },new int[] {R.id.scheadul_name,R.id.scheadul_text}));
	               
                
            }
        };
}