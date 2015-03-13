package mconnect.mdiabetes.framework;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import mconnect.mdiabetes.framework.R;


import android.widget.GridView;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class recordActivity extends Activity implements OnItemClickListener{
	
	public static final String AUTHORITY = "com.chen.provider.diabeteshelper";
	public static final Uri CONTENT_URI =Uri.parse("content://" + AUTHORITY + "/RecordTable");
	
	public static final Uri CONTENT_URI1 =Uri.parse("content://" + AUTHORITY + "/GlucoseRange");	
	private static final String[] PROJECTION1 = new String[] {"name", "value"};
	//public static final Uri CONTENT_URI =Uri.parse("RecordTable");
	
	private static final String[] PROJECTION = new String[] {"name", "value","date","time","tag","note","label"};
	
	static final int PICKUP_REQUEST = 0;
	static final int PICKUP_REQUEST_RESULT = 1;
	
	//private Button addbtn;
	//private Button searchbtn;
	//private Button showallbtn;
	private ListView lv;
	
	private int year;
	private int month;
	private int day;
	
	private String smonth;
	private String sday;
	
	private int tag_po;
	
	private int image2_v;
	private float datevalues;
	
	private ArrayList<Map<String, Object>> coll;
	
	private String array[]={"Pre-Breakfast",
	        "Post-Breakfast",
	        "Pre-Lunch",
	        "Post-Lunch",
	        "Pre-Dinner",
	        "Post-Dinner",
	        "Night"};
	
	private float [] glocuseValue;
	private GridView gv;
	
	private int image2[]={R.drawable.lowalert,R.drawable.normal,R.drawable.highalert};
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record);
        
        gv = (GridView)this.findViewById(R.id.shipin);
		String[] menubar = new String[]{  "Ìí¼Ó",  "¼ìË÷","ÏÔÊ¾" };
		int[] menuIcon = new int[]{
				R.drawable.plan_24,
				R.drawable.magnifying_glass_24,
				R.drawable.add_record,};
		ArrayList<Map<String , Object>> gv_array = new ArrayList<Map<String , Object>>();
		for(int i=0;i<menubar.length;i++){
			Map<String , Object> gv_item = new HashMap<String , Object>();
			gv_item.put("menuIcon", menuIcon[i]);
			gv_item.put("menuOrder", menubar[i]);
			System.out.println("menuOrder="+menubar[i]);
			gv_array.add(gv_item);
			//System.out.println("gv_item="+gv_item.toString());
		}
		SimpleAdapter gvAdapter = new SimpleAdapter(this, gv_array, R.layout.griditem, 
				new String[]{"menuIcon","menuOrder"}, 
				new int[]{R.id.gv_icon,R.id.gv_text});
		
        gv.setAdapter(gvAdapter);
        gv.setOnItemClickListener(new OnItemClickListener(){

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				// TODO Auto-generated method stub
				switch(arg2){
				case 0:{//mainActivity
					Intent in = new Intent(recordActivity.this,Bluetooth.class);
					startActivity(in);
					break;
				}case 1:{//back
					Calendar c=Calendar.getInstance();
	                year=c.get(Calendar.YEAR);
	                month=c.get(Calendar.MONTH);
	                day=c.get(Calendar.DATE);
	        		showDialog(0);
					break;
				}case 2:{
					Intent intent=getIntent();
			        if(intent.getData()==null)
			        	intent.setData(CONTENT_URI);
			        Cursor cur = getContentResolver().query(getIntent().getData(),PROJECTION, null, null, null);
			        coll= new ArrayList<Map<String, Object>>();
			        Map<String, Object> item;
			        cur.moveToFirst();
			        while(!cur.isAfterLast()) {
			        	item = new HashMap<String, Object>();
			        	item.put("name", cur.getString(0));
			        	item.put("value", cur.getString(1));
			        	datevalues=Float.parseFloat(cur.getString(1));
			        	item.put("date", cur.getString(2));
			        	item.put("time", cur.getString(3));
			        	tag_po=Integer.parseInt(cur.getString(4));
			        	item.put("tag", array[tag_po]);
			        	
			        	item.put("note", cur.getString(5));
			        	item.put("label", cur.getString(6));
			        	item.put("image", image[Integer.parseInt(cur.getString(6))-1]);
			        	if(cur.getString(6).equals("1")){
			        		setimage2(datevalues,tag_po);
			            	item.put("image2",image2[image2_v] );
			        	}
			        	coll.add(item);
			        	cur.moveToNext(); 
			        }
			        
			        lv.setAdapter(new SimpleAdapter(getBaseContext(), coll,R.layout.layouttest, new String[] { "name","value","date","time","tag","image","image2" },new int[] {R.id.name_text,R.id.value_text,R.id.date_text,R.id.time_text,R.id.note_text,R.id.image_label,R.id.widget211}));
			        
					break;
				}
				}
				
			}
			
		});
        
        
        Cursor cur = getContentResolver().query(CONTENT_URI1,PROJECTION1, null, null, null);
        cur.moveToFirst();
        glocuseValue=new float[14];
        int k=0;
        while(!cur.isAfterLast()) {
        	glocuseValue[k]=Float.parseFloat(cur.getString(1));
        	k++;
        	cur.moveToNext(); 
        }
        
        
        cur = getContentResolver().query(CONTENT_URI,PROJECTION, null, null, null);
        coll= new ArrayList<Map<String, Object>>();
        Map<String, Object> item;
        cur.moveToFirst();
        
        while(!cur.isAfterLast()) {
        	item = new HashMap<String, Object>();
        	item.put("name", cur.getString(0));
        	
        	item.put("value", cur.getString(1));
        	datevalues=Float.parseFloat(cur.getString(1));
        	item.put("date", cur.getString(2));
        	item.put("time", cur.getString(3));
        	tag_po=Integer.parseInt(cur.getString(4));
        	item.put("tag", array[tag_po]);
        	item.put("note", cur.getString(5));
        	item.put("label", cur.getString(6));
        	item.put("image", image[Integer.parseInt(cur.getString(6))-1]);
        	if(cur.getString(6).equals("1")){
        		setimage2(datevalues,tag_po);
            	item.put("image2",image2[image2_v] );
        	}
        	
        	
        	coll.add(item);
        	
        	cur.moveToNext(); 
        }
        
        lv=(ListView)this.findViewById(R.id.records_list);
        lv.setAdapter(new SimpleAdapter(this, coll,R.layout.layouttest, new String[] { "name","value","date","time","tag","image","image2" },new int[] {R.id.name_text,R.id.value_text,R.id.date_text,R.id.time_text,R.id.note_text,R.id.image_label,R.id.widget211}));
        lv.setOnItemClickListener(this);
        
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.icon);     
        Drawable drawable = new BitmapDrawable(bitmap); 
        drawable.setBounds(10, 10, 10, 10);
        
        //addbtn=(Button)this.findViewById(R.id.add_rc_btn);
        
        /*addbtn.setOnClickListener(new OnClickListener(){
        	public void onClick(View v) {
        		Intent in = new Intent(recordActivity.this,GalleryTest.class);
	    	    startActivityForResult(in,PICKUP_REQUEST);
			}
        });
        
        addbtn.setCompoundDrawables(drawable, null, null, null);
        
        searchbtn=(Button)this.findViewById(R.id.date_search_btn);
        searchbtn.setOnClickListener(new OnClickListener(){
        	public void onClick(View v) {
        		Calendar c=Calendar.getInstance();
                year=c.get(Calendar.YEAR);
                month=c.get(Calendar.MONTH);
                day=c.get(Calendar.DATE);
        		showDialog(0);
			}
        });
        
        showallbtn=(Button)this.findViewById(R.id.showall_btn);
        showallbtn.setOnClickListener(new OnClickListener(){
        	public void onClick(View v) {
        		Intent intent=getIntent();
		        if(intent.getData()==null)
		        	intent.setData(CONTENT_URI);
		        Cursor cur = getContentResolver().query(getIntent().getData(),PROJECTION, null, null, null);
		        coll= new ArrayList<Map<String, Object>>();
		        Map<String, Object> item;
		        cur.moveToFirst();
		        while(!cur.isAfterLast()) {
		        	item = new HashMap<String, Object>();
		        	item.put("name", cur.getString(0));
		        	item.put("value", cur.getString(1));
		        	datevalues=Float.parseFloat(cur.getString(1));
		        	item.put("date", cur.getString(2));
		        	item.put("time", cur.getString(3));
		        	tag_po=Integer.parseInt(cur.getString(4));
		        	item.put("tag", array[tag_po]);
		        	
		        	item.put("note", cur.getString(5));
		        	item.put("label", cur.getString(6));
		        	item.put("image", image[Integer.parseInt(cur.getString(6))-1]);
		        	if(cur.getString(6).equals("1")){
		        		setimage2(datevalues,tag_po);
		            	item.put("image2",image2[image2_v] );
		        	}
		        	coll.add(item);
		        	cur.moveToNext(); 
		        }
		        
		        lv.setAdapter(new SimpleAdapter(getBaseContext(), coll,R.layout.layouttest, new String[] { "name","value","date","time","tag","image","image2" },new int[] {R.id.name_text,R.id.value_text,R.id.date_text,R.id.time_text,R.id.note_text,R.id.image_label,R.id.widget211}));
		        
			}
        });*/
        
        
    }
	
	@Override 
	protected void onActivityResult(int requestCode, int resultCode,Intent data) {
		if (requestCode == PICKUP_REQUEST) {
			if (resultCode == RESULT_CANCELED)
			{
				//setTitle("Canceled...");
			}
			else if(resultCode == RESULT_OK) {
				//setTitle("OK");
			
				Intent intent=getIntent();
		        if(intent.getData()==null)
		        	intent.setData(CONTENT_URI);
		        Cursor cur = getContentResolver().query(getIntent().getData(),PROJECTION, null, null, null);
		        coll= new ArrayList<Map<String, Object>>();
		        Map<String, Object> item;
		        cur.moveToFirst();
		        while(!cur.isAfterLast()) {
		        	item = new HashMap<String, Object>();
		        	item.put("name", cur.getString(0));
		        	item.put("value", cur.getString(1));
		        	datevalues=Float.parseFloat(cur.getString(1));
		        	item.put("date", cur.getString(2));
		        	item.put("time", cur.getString(3));
		        	tag_po=Integer.parseInt(cur.getString(4));
		        	item.put("tag", array[tag_po]);
		        	item.put("note", cur.getString(5));
		        	item.put("label", cur.getString(6));
		        	item.put("image", image[Integer.parseInt(cur.getString(6))-1]);
		        	if(cur.getString(6).equals("1")){
		        		setimage2(datevalues,tag_po);
		            	item.put("image2",image2[image2_v] );
		        	}
		        	coll.add(item);
		        	cur.moveToNext(); 
		        }

		        lv.setAdapter(new SimpleAdapter(this, coll,R.layout.layouttest, new String[] { "name","value","date","time","tag","image","image2" },new int[] {R.id.name_text,R.id.value_text,R.id.date_text,R.id.time_text,R.id.note_text,R.id.image_label,R.id.widget211}));
		        
			}
		}
	}
	
	private int[] image={R.drawable.glocusechart,R.drawable.food,R.drawable.insulin};


	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		
		Intent in = new Intent(recordActivity.this,Bluetooth.class);
		Bundle passBundle=new Bundle();
		passBundle.putString("value", coll.get(arg2).get("value").toString());
		passBundle.putString("date", ""+coll.get(arg2).get("date").toString());
		passBundle.putString("time", ""+coll.get(arg2).get("time").toString());
		
		String s=coll.get(arg2).get("tag").toString();
		int i;
		for(i=0;i<7;i++){
			if(s.equals(array[i])){
				break;
			}
		}
		passBundle.putString("tag", ""+i);
		passBundle.putString("note", ""+coll.get(arg2).get("note").toString());
		passBundle.putString("label", ""+coll.get(arg2).get("label").toString());
		in.putExtras(passBundle);
		startActivityForResult(in,PICKUP_REQUEST);
	}
	
	@Override
    protected Dialog onCreateDialog(int id) {
        return new DatePickerDialog(this,
                            mDateSetListener,
                            year, month, day);
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        ((DatePickerDialog) dialog).updateDate(year, month, day);
    }    

    
    

    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int yea, int monthOfYear,
                        int dayOfMonth) {
                    year = yea;
                    month = monthOfYear+1;
                    day = dayOfMonth;
                    if(month<10){
                    	smonth="0"+month;
                    }
                    else{
                    	smonth=""+month;
                    }
                    
                    if(day<10){
                    	sday="0"+day;
                    }
                    else{
                    	sday=""+day;
                    }
                    Intent intent=getIntent();
    		        if(intent.getData()==null)
    		        	intent.setData(CONTENT_URI);
    		        Cursor cur = getContentResolver().query(getIntent().getData(),PROJECTION, null, null, null);
    		        coll= new ArrayList<Map<String, Object>>();
    		        Map<String, Object> item;
    		        cur.moveToFirst();
    		        String ss=year+"-"+smonth+"-"+sday;
    		        while(!cur.isAfterLast()) {
    		        	System.out.println("choise time="+ss);
    		        	System.out.println("time="+cur.getString(2));
    		        	if(ss.equals(cur.getString(2))){
    		        	//if(ss.compareTo(cur.getString(2))>0){
    		        		item = new HashMap<String, Object>();
    		        		item.put("name", cur.getString(0));
    		        		item.put("value", cur.getString(1));
    		        		datevalues=Float.parseFloat(cur.getString(1));
    		        		item.put("date", cur.getString(2));
    		        		item.put("time", cur.getString(3));
    		        		tag_po=Integer.parseInt(cur.getString(4));
    		        		item.put("tag", array[tag_po]);
    		        		item.put("note", cur.getString(5));
    		        		item.put("label", cur.getString(6));
    		        		item.put("image", image[Integer.parseInt(cur.getString(6))-1]);
    		        		if(cur.getString(6).equals("1")){
    		            		setimage2(datevalues,tag_po);
    		                	item.put("image2",image2[image2_v] );
    		            	}
    		        		coll.add(item);
    		        	}
    		        	cur.moveToNext(); 
    		        }
    		        
    		        lv.setAdapter(new SimpleAdapter(getBaseContext(), coll,R.layout.layouttest, new String[] { "name","value","date","time","tag","image","image2" },new int[] {R.id.name_text,R.id.value_text,R.id.date_text,R.id.time_text,R.id.note_text,R.id.image_label,R.id.widget211}));
    		        
                }
            };
            
     public void setimage2(float datevalue,int b){
    	 switch(b){
        	case 0:
        		if(datevalue<glocuseValue[0]){
        			image2_v=0;
        		}
        		else if(datevalue>glocuseValue[1]){
        			image2_v=2;
        		}
        		else{
        			image2_v=1;
        		}
        		break;
        	case 1:
        		if(datevalue<glocuseValue[2]){
        			image2_v=0;
        		}
        		else if(datevalue>glocuseValue[3]){
        			image2_v=2;
        		}
        		else{
        			image2_v=1;
        		}
        		break;
        	case 2:
        		if(datevalue<glocuseValue[4]){
        			image2_v=0;
        		}
        		else if(datevalue>glocuseValue[5]){
        			image2_v=2;
        		}
        		else{
        			image2_v=1;
        		}
        		break;
        	case 3:
        		if(datevalue<glocuseValue[6]){
        			image2_v=0;
        		}
        		else if(datevalue>glocuseValue[7]){
        			image2_v=2;
        		}
        		else{
        			image2_v=1;
        		}
        		break;
        	case 4:
        		if(datevalue<glocuseValue[8]){
        			image2_v=0;
        		}
        		else if(datevalue>glocuseValue[9]){
        			image2_v=2;
        		}
        		else{
        			image2_v=1;
        		}
        		break;
        	case 5:
        		if(datevalue<glocuseValue[10]){
        			image2_v=0;
        		}
        		else if(datevalue>glocuseValue[11]){
        			image2_v=2;
        		}
        		else{
        			image2_v=1;
        		}
        		break;
        	case 6:
        		if(datevalue<glocuseValue[12]){
        			image2_v=0;
        		}
        		else if(datevalue>glocuseValue[13]){
        			image2_v=2;
        		}
        		else{
        			image2_v=1;
        		}
        		break;
        	}
     }
     
     
}
