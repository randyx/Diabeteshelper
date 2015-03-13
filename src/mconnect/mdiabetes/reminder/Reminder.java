package mconnect.mdiabetes.reminder;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.Window;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;


import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import android.content.DialogInterface;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View.OnClickListener;

import android.net.Uri;
import android.database.Cursor;
import java.util.HashMap;

import mconnect.mdiabetes.framework.Bluetooth;
import mconnect.mdiabetes.framework.R;
import mconnect.mdiabetes.framework.recordActivity;


public class Reminder extends Activity implements OnItemClickListener{
    /** Called when the activity is first created. */
	public static final int ADD_ID = Menu.FIRST;
	private ArrayList<Map<String, Object>> coll;
	private ArrayList<Map<String, Object>> coll1;
	public static final String AUTHORITY = "com.chen.provider.diabeteshelper";
	public static final Uri CONTENT_URI =Uri.parse("content://" + AUTHORITY + "/reminder");
	static final int PICKUP_REQUEST = 0;
	static final int PICKUP_REQUEST_RESULT = 0;
	
	private static final String[] PROJECTION = new String[] {"distinct name"};
	private static final String[] PROJECTION1 = new String[] {"dosage","time","music"};
	
	private int itemPosition;
	
	private ListView r_name;
	private ListView r_dosage;
	private ListView r_music;
	private ListView r_time;
	
	private String b_name;
	//private String n_name;
	private int b_dosage;
	//private int n_dosage;
	private int b_music;
	//private int n_music;
	private String[] b_time;
	
	private Button bt1,bt2;
	//private String[] n_time;
	
	//String array[]={"On", "Off", };
	String array[]={"开", "关", };
	String array1[]={"1/4", "1/2", "1", "1 1/2", "2", "3", "4","5", "6", "7", "8", "9", "10"};
	
	private ListView reminderlist;
	private GridView gv;

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        this.setContentView(R.layout.reminderlist);
        Cursor cur = getContentResolver().query(CONTENT_URI,PROJECTION, null, null, null);
        coll= new ArrayList<Map<String, Object>>();
        Map<String, Object> item;
        cur.moveToFirst();
        while(!cur.isAfterLast()) {
        	item = new HashMap<String, Object>();
        	item.put("name", cur.getString(0));
        	item.put("image", R.drawable.timer);
        	coll.add(item);
        	cur.moveToNext(); 
        }
        
        reminderlist=(ListView)this.findViewById(R.id.reminder_list);
        reminderlist.setAdapter(new SimpleAdapter(this, coll,R.layout.howoftentimer, new String[] { "name","image" },new int[] {R.id.set_timer_text,R.id.set_timer_image}));
        reminderlist.setOnItemClickListener(this);
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
        
        gv = (GridView)this.findViewById(R.id.reminder_grid);
		String[] menubar = new String[]{  "添加提醒",   };
		int[] menuIcon = new int[]{
				R.drawable.plus_32,
				};
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
						Intent in = new Intent(Reminder.this, TestCustomTitle.class);
						startActivityForResult(in,PICKUP_REQUEST); 
						
						break;
					}
				}
				
			}
			
		});
    }
	@Override public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, ADD_ID, 0, "添加提醒").setIcon(android.R.drawable.ic_menu_add);
		return true; 
	}
	
	protected void onListItemClick(ListView l, View v, int position, long id) {
		LayoutInflater factory = LayoutInflater.from(this);
        final View textEntryView = factory.inflate(R.layout.reminder_modifier, null);
        itemPosition=position;
        b_name=coll.get(position).get("name").toString();
        //System.out.println("b_name="+b_name);
        Intent intent=getIntent();        
        if(intent.getData()==null)
        	intent.setData(CONTENT_URI);
        Cursor cur = getContentResolver().query(getIntent().getData(),PROJECTION1, "name='"+b_name+"'", null, null);
        
        //Map<String, Object> item;
        cur.moveToFirst();
        b_dosage=Integer.parseInt(cur.getString(0));
        //System.out.println("b_dosage="+b_dosage);
        b_music=Integer.parseInt(cur.getString(2));
        //System.out.println("b_music="+b_music);
        b_time=new String[cur.getCount()];
        int k=0;
        while(!cur.isAfterLast()) {
        	b_time[k]=cur.getString(1);
        	//System.out.println("b_time="+b_time[k]);
        	k++;
        	cur.moveToNext(); 
        }
        
        //System.out.println("Success 1!");
        r_name=(ListView)textEntryView.findViewById(R.id.rr_name_lv);
        coll1= new ArrayList<Map<String, Object>>();
        Map<String, Object> item=new HashMap<String, Object>();
        item.put("name", b_name);
		item.put("image", R.drawable.pill);
		coll1.add(item);
        r_name.setAdapter(new SimpleAdapter(textEntryView.getContext(), coll1,R.layout.howoftentimer, new String[] { "name","image" },new int[] {R.id.set_timer_text,R.id.set_timer_image}));
        //System.out.println("Success 2!");
        
        r_dosage=(ListView)textEntryView.findViewById(R.id.rr_dosage_lv);
        coll1= new ArrayList<Map<String, Object>>();
        item=new HashMap<String, Object>();
        item.put("name", array1[b_dosage]);
		item.put("image", R.drawable.dosage);
		coll1.add(item);
        r_dosage.setAdapter(new SimpleAdapter(textEntryView.getContext(), coll1,R.layout.howoftentimer, new String[] { "name","image" },new int[] {R.id.set_timer_text,R.id.set_timer_image}));
        //System.out.println("Success 3!");
        
        r_music=(ListView)textEntryView.findViewById(R.id.rr_music_lv);
        coll1= new ArrayList<Map<String, Object>>();
        item=new HashMap<String, Object>();
        item.put("name", array[b_music]);
		item.put("image", R.drawable.music);
		coll1.add(item);
        r_music.setAdapter(new SimpleAdapter(textEntryView.getContext(), coll1,R.layout.howoftentimer, new String[] { "name","image" },new int[] {R.id.set_timer_text,R.id.set_timer_image}));
        //System.out.println("Success 4!");
        
        r_time=(ListView)textEntryView.findViewById(R.id.rr_time_lv);
        coll1= new ArrayList<Map<String, Object>>();
        //System.out.println("cur.getCount()="+cur.getCount());
        for(int m=0;m<b_time.length;m++){
        	item=new HashMap<String, Object>();
        	item.put("name", b_time[m]);
        	item.put("image", R.drawable.rrtime);
        	coll1.add(item);
        }
        r_time.setAdapter(new SimpleAdapter(textEntryView.getContext(), coll1,R.layout.howoftentimer, new String[] { "name","image" },new int[] {R.id.set_timer_text,R.id.set_timer_image}));
        //System.out.println("Success 5!");
        
        
		
        new AlertDialog.Builder(Reminder.this)
            //.setIcon(R.drawable.stopwatch)
            .setTitle("提醒")
            .setView(textEntryView)
            .setPositiveButton("删除", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) 
                {
                	
                	coll.remove(itemPosition);
                	updatelist();
                	getContentResolver().delete(CONTENT_URI, "name='"+b_name+"'", null);
                	
            		
            		
            		

                   
                }
            })
            .setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                    
                }
            })
            .create().show();
        
        
	}
	
	public void updatelist(){
		reminderlist.setAdapter(new SimpleAdapter(this, coll,R.layout.howoftentimer, new String[] { "name","image" },new int[] {R.id.set_timer_text,R.id.set_timer_image}));
        
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case ADD_ID:
				Intent in = new Intent(Reminder.this, TestCustomTitle.class);
				startActivityForResult(in,PICKUP_REQUEST); 
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
	@Override protected void onActivityResult(int requestCode, int resultCode,Intent data) {
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
		        	item.put("image", R.drawable.timer);
		        	coll.add(item);
		        	cur.moveToNext(); 
		        }

		        reminderlist.setAdapter(new SimpleAdapter(this, coll,R.layout.howoftentimer, new String[] { "name","image" },new int[] {R.id.set_timer_text,R.id.set_timer_image}));
		        
			}
		}
		
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		// TODO Auto-generated method stub
		LayoutInflater factory = LayoutInflater.from(this);
        final View textEntryView = factory.inflate(R.layout.reminder_modifier, null);
        itemPosition=position;
        b_name=coll.get(position).get("name").toString();
        //System.out.println("b_name="+b_name);
        Intent intent=getIntent();        
        if(intent.getData()==null)
        	intent.setData(CONTENT_URI);
        Cursor cur = getContentResolver().query(getIntent().getData(),PROJECTION1, "name='"+b_name+"'", null, null);
        
        //Map<String, Object> item;
        cur.moveToFirst();
        b_dosage=Integer.parseInt(cur.getString(0));
        //System.out.println("b_dosage="+b_dosage);
        b_music=Integer.parseInt(cur.getString(2));
        //System.out.println("b_music="+b_music);
        b_time=new String[cur.getCount()];
        int k=0;
        while(!cur.isAfterLast()) {
        	b_time[k]=cur.getString(1);
        	//System.out.println("b_time="+b_time[k]);
        	k++;
        	cur.moveToNext(); 
        }
        
        //System.out.println("Success 1!");
        r_name=(ListView)textEntryView.findViewById(R.id.rr_name_lv);
        coll1= new ArrayList<Map<String, Object>>();
        Map<String, Object> item=new HashMap<String, Object>();
        item.put("name", b_name);
		item.put("image", R.drawable.pill);
		coll1.add(item);
        r_name.setAdapter(new SimpleAdapter(textEntryView.getContext(), coll1,R.layout.howoftentimer, new String[] { "name","image" },new int[] {R.id.set_timer_text,R.id.set_timer_image}));
        //System.out.println("Success 2!");
        
        r_dosage=(ListView)textEntryView.findViewById(R.id.rr_dosage_lv);
        coll1= new ArrayList<Map<String, Object>>();
        item=new HashMap<String, Object>();
        item.put("name", array1[b_dosage]);
		item.put("image", R.drawable.dosage);
		coll1.add(item);
        r_dosage.setAdapter(new SimpleAdapter(textEntryView.getContext(), coll1,R.layout.howoftentimer, new String[] { "name","image" },new int[] {R.id.set_timer_text,R.id.set_timer_image}));
        //System.out.println("Success 3!");
        
        r_music=(ListView)textEntryView.findViewById(R.id.rr_music_lv);
        coll1= new ArrayList<Map<String, Object>>();
        item=new HashMap<String, Object>();
        item.put("name", array[b_music]);
		item.put("image", R.drawable.music);
		coll1.add(item);
        r_music.setAdapter(new SimpleAdapter(textEntryView.getContext(), coll1,R.layout.howoftentimer, new String[] { "name","image" },new int[] {R.id.set_timer_text,R.id.set_timer_image}));
        //System.out.println("Success 4!");
        
        r_time=(ListView)textEntryView.findViewById(R.id.rr_time_lv);
        coll1= new ArrayList<Map<String, Object>>();
        //System.out.println("cur.getCount()="+cur.getCount());
        for(int m=0;m<b_time.length;m++){
        	item=new HashMap<String, Object>();
        	item.put("name", b_time[m]);
        	item.put("image", R.drawable.rrtime);
        	coll1.add(item);
        }
        r_time.setAdapter(new SimpleAdapter(textEntryView.getContext(), coll1,R.layout.howoftentimer, new String[] { "name","image" },new int[] {R.id.set_timer_text,R.id.set_timer_image}));
        //System.out.println("Success 5!");
        
        
		
        new AlertDialog.Builder(Reminder.this)
            //.setIcon(R.drawable.stopwatch)
            .setTitle("提醒")
            .setView(textEntryView)
            .setPositiveButton("删除", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) 
                {
                	
                	coll.remove(itemPosition);
                	updatelist();
                	getContentResolver().delete(CONTENT_URI, "name='"+b_name+"'", null);
                	
            		
            		
            		

                   
                }
            })
            .setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                    
                }
            })
            .create().show();
	}
}