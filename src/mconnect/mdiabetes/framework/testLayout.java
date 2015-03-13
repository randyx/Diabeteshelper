package mconnect.mdiabetes.framework;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import mconnect.mdiabetes.framework.R;


import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;

import android.graphics.Color;

import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.Button;




import android.widget.TextView;
import android.widget.Toast;


public class testLayout extends Activity implements OnClickListener{
	
	private int[] color={0xFF66CD00,0xFF90EE90,0xFFCAFF70,0xFFFFA07A,0xFFFF4040,0xFFFF0000,R.drawable.green7,R.drawable.green8,R.drawable.green9,R.drawable.green10,R.drawable.green11,};
	
	public static final String AUTHORITY = "com.chen.provider.diabeteshelper";
	public static final Uri CONTENT_URI =Uri.parse("content://" + AUTHORITY + "/RecordTable");
	public static final Uri CONTENT_URI1 =Uri.parse("content://" + AUTHORITY + "/BMITable");
	
	private static final String[] PROJECTION = new String[] { "value",};
	private static final String[] PROJECTION1 = new String[] { "sex","bmi"};
	
	
	
	private TextView year_text;
	private TextView month_text;
	
	private float smoke=0,p=0,press=0,sum=0,bmi;
	private int cla=0,num=0;
	
	
	private static int year,tmp_year;
	private static int month,tmp_month;
	private static int day,tmp_day;
	
	private TextView[] text;
	
	private Button previous;
	private Button next;
	
	private int count;
	
	public static final int OK_ID = Menu.FIRST;
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Use an existing ListAdapter that will map an array
        // of strings to TextViews
        setContentView(R.layout.calendar2);
        
        Calendar c = Calendar.getInstance();
        day=c.get(Calendar.DAY_OF_MONTH);
        c.set(Calendar.DAY_OF_MONTH, 1);


        int i=c.get(Calendar.DAY_OF_WEEK)-1;
        int maxDate=c.getActualMaximum(Calendar.DATE);
        year=c.get(Calendar.YEAR);
        tmp_year=year;
        month=c.get(Calendar.MONTH);
        tmp_month=month;
        System.out.println(i+" "+day);
        
        text=new TextView[42];
        for(int me=0;me<8;me++)
        {
        	System.out.println(color[me]);
        }
        
        for(int k=0;k<42;k++)
        {
        	text[k]=(TextView)this.findViewById(textView[k]);
        }
        
        for(int n=0;n<i;n++){
        	text[n].setText("");
        	text[n].setBackgroundColor(Color.TRANSPARENT);
        }
        for(int j=i;j<maxDate+i;j++){
        	
        	text[j].setText(""+(j-i+1));
        	text[j].setTextColor(Color.RED);
        	text[j].setBackgroundColor(Color.GRAY);
        	Intent intent=getIntent();
	        if(intent.getData()==null)
	        	intent.setData(CONTENT_URI);
	        String smonth;
	        String sday;
	        if((month+1)<10){
	        	smonth="0"+(month+1);
	        }
	        else{
	        	smonth=""+(month+1);
	        }
	        
	        if((j-i+1)<10){
	        	sday="0"+(j-i+1);
	        }
	        else{
	        	sday=""+(j-i+1);
	        }
	        String nowdate=year+"-"+smonth+"-"+sday;
	        
	        Intent intent1=getIntent();
	        if(intent1.getData()==null)
	        	intent1.setData(CONTENT_URI1);
	        Cursor cur1 = getContentResolver().query(CONTENT_URI1,PROJECTION1, null, null, null);
	        Map<String, Object> item;
	        cur1.moveToFirst();
	        while(!cur1.isAfterLast()) {
	        	item = new HashMap<String, Object>();
	        	System.out.println(cur1.getColumnCount());
	        	item.put("sex", cur1.getString(0));
	        	item.put("bmi", cur1.getString(1));
	        	if(cur1.getString(0)=="m")
	        		smoke=1;
	        	else
	        		smoke=0;
	        	bmi=Float.parseFloat(cur1.getString(1));
	        	cur1.moveToNext(); 
	        }
	        if(bmi<25)
	        {
	        	bmi=-40;
	        	if(smoke==0)
	        		cla=0;
	        	else
	        		cla=1;
	        }
	        else if(bmi<30)
	        {
	        	bmi=-20;
	        	if(smoke==0)
	        		cla=2;
	        	else
	        		cla=3;
	        }
	        else
	        {
	        	bmi=2;
	        	if(smoke==0)
	        		cla=4;
	        	else
	        		cla=5;
	        }
	        System.out.println(color[cla]);
	        text[i+day].setBackgroundColor(color[cla]);
	        
	        /*Cursor cur = getContentResolver().query(getIntent().getData(),PROJECTION, "date='"+nowdate+"'", null, null);
	        
	        if((count=cur.getCount())>0){
	        	if(count>10){
	        		text[j].setBackgroundResource(color[3]);
	        	}
	        	else{
	        		text[j].setBackgroundResource(color[count-1]);
	        	}
	        
	        	
	        }*/
        	
        }
        for(int m=maxDate+i;m<42;m++){
        	text[m].setText("");
        	text[m].setBackgroundColor(Color.TRANSPARENT);
        }
        
       
        
        previous=(Button)this.findViewById(R.id.previous_btn);
        next=(Button)this.findViewById(R.id.next_btn);
        previous.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				month=month-1;
				if(month<0){
					month=11;
					year=year-1;
					year_text.setText(""+year+" 年");
				}
				month_text.setText(""+(month+1)+" 月");
				//System.out.println("month="+month);
				//System.out.println("year="+year);
				Calendar c = Calendar.getInstance();
		        c.set(Calendar.DAY_OF_MONTH, 1);
		        c.set(Calendar.MONTH, month);
		        c.set(Calendar.YEAR, year);
		        int i=c.get(Calendar.DAY_OF_WEEK)-1;
		        
		        int maxDate=c.getActualMaximum(Calendar.DATE);
		        //System.out.println("i="+i);
				//System.out.println("maxDate="+maxDate);
		        for(int n=0;n<i;n++){
		        	text[n].setText("");
		        	text[n].setBackgroundColor(Color.TRANSPARENT);
		        }
		        String smonth;
		        if((month+1)<10){
		        	smonth="0"+(month+1);
		        }
		        else{
		        	smonth=""+(month+1);
		        }
		        String sday;
		        for(int j=i;j<maxDate+i;j++){
		        	
		        	text[j].setText(""+(j-i+1));
		        	text[j].setTextColor(Color.RED);
		        	text[j].setBackgroundColor(Color.GRAY);
		        	
			        
			        
			        
			        if((j-i+1)<10){
			        	sday="0"+(j-i+1);
			        }
			        else{
			        	sday=""+(j-i+1);
			        }
			        if(month==tmp_month&&year==tmp_year)
			        {
			        Intent intent1=getIntent();
			        if(intent1.getData()==null)
			        	intent1.setData(CONTENT_URI1);
			        Cursor cur1 = getContentResolver().query(CONTENT_URI1,PROJECTION1, null, null, null);
			        Map<String, Object> item;
			        cur1.moveToFirst();
			        while(!cur1.isAfterLast()) {
			        	item = new HashMap<String, Object>();
			        	item.put("sex", cur1.getString(0));
			        	item.put("bmi", cur1.getString(1));
			        	if(cur1.getString(0)=="m")
			        		smoke=1;
			        	else
			        		smoke=0;
			        	bmi=Float.parseFloat(cur1.getString(1));
			        	cur1.moveToNext(); 
			        }
			        if(bmi<25)
			        {
			        	bmi=-40;
			        	if(smoke==0)
			        		cla=0;
			        	else
			        		cla=1;
			        }
			        else if(bmi<30)
			        {
			        	bmi=-20;
			        	if(smoke==0)
			        		cla=2;
			        	else
			        		cla=3;
			        }
			        else
			        {
			        	bmi=2;
			        	if(smoke==0)
			        		cla=4;
			        	else
			        		cla=5;
			        }
			        text[i+day].setBackgroundColor(color[cla]);
			        }
			        else
			        {
			        String nowdate=year+"-"+smonth+"-"+sday;
		        	Intent intent=getIntent();
			        if(intent.getData()==null)
			        	intent.setData(CONTENT_URI);
			        
			        
			        Cursor cur = getContentResolver().query(getIntent().getData(),PROJECTION, "date='"+nowdate+"'", null, null);
			        
			        if((count=cur.getCount())>0){
			        	if(count>10){
			        		text[j].setBackgroundResource(color[10]);
			        	}
			        	else{
			        		text[j].setBackgroundResource(color[count-1]);
			        	}
		        	
			        	
			        }
			        }
		        }
		        for(int m=maxDate+i;m<42;m++){
		        	text[m].setText("");
		        	text[m].setBackgroundColor(Color.TRANSPARENT);
		        }
			}
		});
        
        next.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				month=month+1;
				if(month>11){
					month=0;
					year=year+1;
					year_text.setText(""+year+" 年");
				}
				month_text.setText(""+(month+1)+" 月");
				
				Calendar c = Calendar.getInstance();
		        c.set(Calendar.DAY_OF_MONTH, 1);
		        c.set(Calendar.MONTH, month);
		        c.set(Calendar.YEAR, year);
		        int i=c.get(Calendar.DAY_OF_WEEK)-1;
		        
		        int maxDate=c.getActualMaximum(Calendar.DATE);
		        
		        for(int n=0;n<i;n++){
		        	text[n].setText("");
		        	text[n].setBackgroundColor(Color.TRANSPARENT);
		        }
		        String smonth;
		        if((month+1)<10){
		        	smonth="0"+(month+1);
		        }
		        else{
		        	smonth=""+(month+1);
		        }
		        String sday;
		        for(int j=i;j<maxDate+i;j++){
		        	
		        	text[j].setText(""+(j-i+1));
		        	//text[j].setTextColor(Color.RED);
		        	text[j].setBackgroundColor(Color.GRAY);
		        	if((j-i+1)<10){
			        	sday="0"+(j-i+1);
			        }
			        else{
			        	sday=""+(j-i+1);
			        }
		        	if(month==tmp_month&&year==tmp_year)
			        {
			        Intent intent1=getIntent();
			        if(intent1.getData()==null)
			        	intent1.setData(CONTENT_URI1);
			        Cursor cur1 = getContentResolver().query(CONTENT_URI1,PROJECTION1, null, null, null);
			        Map<String, Object> item;
			        cur1.moveToFirst();
			        while(!cur1.isAfterLast()) {
			        	item = new HashMap<String, Object>();
			        	item.put("sex", cur1.getString(0));
			        	item.put("bmi", cur1.getString(1));
			        	if(cur1.getString(0)=="m")
			        		smoke=1;
			        	else
			        		smoke=0;
			        	bmi=Float.parseFloat(cur1.getString(1));
			        	cur1.moveToNext(); 
			        }
			        if(bmi<25)
			        {
			        	bmi=-40;
			        	if(smoke==0)
			        		cla=0;
			        	else
			        		cla=1;
			        }
			        else if(bmi<30)
			        {
			        	bmi=-20;
			        	if(smoke==0)
			        		cla=2;
			        	else
			        		cla=3;
			        }
			        else
			        {
			        	bmi=2;
			        	if(smoke==0)
			        		cla=4;
			        	else
			        		cla=5;
			        }
			        text[i+day].setBackgroundColor(color[cla]);
			        }
			        else
			        {
		        	Intent intent=getIntent();
			        if(intent.getData()==null)
			        	intent.setData(CONTENT_URI);
			        String nowdate=year+"-"+smonth+"-"+sday;
			        //System.out.println("nowdate="+nowdate);
			        Cursor cur = getContentResolver().query(getIntent().getData(),PROJECTION, "date='"+nowdate+"'", null, null);
			        //System.out.println("count="+cur.getCount());
			        if((count=cur.getCount())>0){
			        	if(count>10){
			        		text[j].setBackgroundResource(color[10]);
			        	}
			        	else{
			        		text[j].setBackgroundResource(color[count-1]);
			        	}
			        	//text[j].setOnClickListener(this);
			        }
			        }
		        }
		        for(int m=maxDate+i;m<42;m++){
		        	text[m].setText("");
		        	text[m].setBackgroundColor(Color.TRANSPARENT);
		        }
			}
		});
        
        previous.setBackgroundResource(R.drawable.previous);
        next.setBackgroundResource(R.drawable.next);
        
        year_text=(TextView)this.findViewById(R.id.year_text);
        year_text.setText(""+year+" 年");
        month_text=(TextView)this.findViewById(R.id.month_text);
        month_text.setText(""+(month+1)+" 月");
        
        //year_text.setText("August");
        //month_text.setText("2010");
    }
	
	@Override 
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, OK_ID, 0, "Update").setIcon(android.R.drawable.ic_menu_upload);
		
		return true; 
	}
	
	@Override 
	public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
    	case OK_ID:
    		Calendar c = Calendar.getInstance();
            c.set(Calendar.DAY_OF_MONTH, 1);


            int i=c.get(Calendar.DAY_OF_WEEK)-1;
            int maxDate=c.getActualMaximum(Calendar.DATE);
            year=c.get(Calendar.YEAR);
            month=c.get(Calendar.MONTH);
            
            text=new TextView[42];
            for(int k=0;k<42;k++)
            {
            	text[k]=(TextView)this.findViewById(textView[k]);
            }
            
            for(int n=0;n<i;n++){
            	text[n].setText("");
            	text[n].setBackgroundColor(Color.TRANSPARENT);
            }
            for(int j=i;j<maxDate+i;j++){
            	
            	text[j].setText(""+(j-i+1));
            	text[j].setTextColor(Color.RED);
            	text[j].setBackgroundColor(Color.GRAY);
            	Intent intent=getIntent();
    	        if(intent.getData()==null)
    	        	intent.setData(CONTENT_URI);
    	        String smonth;
    	        String sday;
    	        if((month+1)<10){
    	        	smonth="0"+(month+1);
    	        }
    	        else{
    	        	smonth=""+(month+1);
    	        }
    	        
    	        if((j-i+1)<10){
    	        	sday="0"+(j-i+1);
    	        }
    	        else{
    	        	sday=""+(j-i+1);
    	        }
    	        String nowdate=year+"-"+smonth+"-"+sday;
    	        //System.out.println("nowdate="+nowdate);
    	        Cursor cur = getContentResolver().query(getIntent().getData(),PROJECTION, "date='"+nowdate+"'", null, null);
    	        //System.out.println("count="+cur.getCount());
    	        if((count=cur.getCount())>0){
    	        	if(count>10){
    	        		text[j].setBackgroundResource(color[10]);
    	        	}
    	        	else{
    	        		text[j].setBackgroundResource(color[count-1]);
    	        	}
    	        	//text[j].setOnClickListener(this);
    	        	
    	        }
            	
            }
            for(int m=maxDate+i;m<42;m++){
            	text[m].setText("");
            	text[m].setBackgroundColor(Color.TRANSPARENT);
            }
    	}
    	return super.onOptionsItemSelected(item);
	}
	
	public void onClick(View v) {
		
		for(int i=0;i<42;i++){
			if(v==text[i]){
				Toast mToast=null;
				if (mToast != null) {
					mToast.cancel();
				}
				mToast = Toast.makeText(testLayout.this, "I am "+text[i].getText().toString(),
						Toast.LENGTH_SHORT);
				//mToast.show();
			}
		}
		
	}
	
	private int[] textView={R.id.widget1,R.id.widget2,R.id.widget3,R.id.widget4,R.id.widget5,R.id.widget6,R.id.widget7,R.id.widget8,R.id.widget9,R.id.widget10,R.id.widget11,R.id.widget12,R.id.widget13,R.id.widget14,R.id.widget15,R.id.widget16,R.id.widget17,R.id.widget18,R.id.widget19,R.id.widget20,R.id.widget21,R.id.widget22,R.id.widget23,R.id.widget24,R.id.widget25,R.id.widget26,R.id.widget27,R.id.widget28,R.id.widget29,R.id.widget30,R.id.widget31,R.id.widget32,R.id.widget33,R.id.widget34,R.id.widget35,R.id.widget36,R.id.widget37,R.id.widget38,R.id.widget39,R.id.widget40,R.id.widget41,R.id.widget42};
	
	
}
