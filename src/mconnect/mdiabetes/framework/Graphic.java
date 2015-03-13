package mconnect.mdiabetes.framework;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import mconnect.mdiabetes.chart.Category;
import mconnect.mdiabetes.chart.IChart;
import mconnect.mdiabetes.chart.LastNightDays;
import mconnect.mdiabetes.chart.LastThirtyDays;
import mconnect.mdiabetes.chart.LastThirtyDaysScatter;
import mconnect.mdiabetes.chart.LastWeek;
import mconnect.mdiabetes.chart.LastWeekScatterChart;
import mconnect.mdiabetes.chart.Limit;
import mconnect.mdiabetes.chart.TodayChart;
import mconnect.mdiabetes.chart.TodayScatterChart;
import mconnect.mdiabetes.date.CLastNightDays;
import mconnect.mdiabetes.date.CLastThirtyDays;
import mconnect.mdiabetes.date.CLastWeek;
import mconnect.mdiabetes.date.CTime;
import mconnect.mdiabetes.framework.R;





import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;

import android.net.Uri;
import android.os.Bundle;

import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.SimpleExpandableListAdapter;
import android.widget.Toast;

import android.widget.ExpandableListView.OnChildClickListener;

public class Graphic extends Activity implements OnChildClickListener{
    /** Called when the activity is first created. */
    
	public static final String AUTHORITY = "com.chen.provider.diabeteshelper";
	public static final Uri CONTENT_URI =Uri.parse("content://" + AUTHORITY + "/RecordTable");
	
	public static final Uri CONTENT_URI1 =Uri.parse("content://" + AUTHORITY + "/GlucoseRange");	
	private static final String[] PROJECTION1 = new String[] {"name", "value"};
	
	private static final String[] PROJECTION = new String[] { "value","time",};
	private static final String[] PROJECTIONWEEK = new String[] { "value","date","time",};
	
	private static final String[] PROJECTION2 = new String[] {"value","tag",};
	
	private ListView lv1;
    private ExpandableListView lv3;
    
    private static int lv1_position=0;
    private static int lv2_position=0;
    
    private static final String NAME = "NAME";
    
    private ExpandableListAdapter mAdapter;
    
    //private String[] pattern={"Display pattern","Display catalog"};
    private String[] pattern={"显示方式","数据类型"};
    //private int[] pattern={R.string.display_pattern,R.string.display_catlog};
    private int[] image={R.drawable.basicseting,R.drawable.catalog};
    
    //private String array[]={"Line","Scatter"};
    private String array[]={"线","点"};
    private String catalog[]={"Glocuse","Carbs","Insulin"};
    private String cata[]={"收缩压","舒张压","脉搏"};
    //private String catalog[]={"血糖水平","碳水化合物","胰岛素"};
    
    private float [] glocuseValue;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        lv1=(ListView)this.findViewById(R.id.list_basic);
        
        ArrayList<Map<String, Object>> coll= new ArrayList<Map<String, Object>>();
        Map<String, Object> item;
        for(int i=0;i<2;i++) {
        	item = new HashMap<String, Object>();
        	item.put("name", pattern[i]);
        	//item.put("value", "Set with line, the chart will be displayed as line char. Set with scatter, the chart will be display as scatter chart.");
        	item.put("image",image[i]);
        	coll.add(item);
        }        
        lv1.setAdapter(new SimpleAdapter(this, coll,R.layout.basicsetting, new String[] { "name","image" },new int[] {R.id.set_text,R.id.set_image}));
        //lv1.setOnItemClickListener(this);
        lv1.setOnItemClickListener(
        		new AdapterView.OnItemClickListener()  
        		   { 
        		    @Override
					public void onItemClick(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
						// TODO Auto-generated method stub
        		    	if(arg2==0){
        		    	
            			new AlertDialog.Builder(Graphic.this)
                        .setTitle(R.string.choose_display_pattern)
                        .setSingleChoiceItems(array, lv1_position, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            	lv1_position=whichButton;
                                
                            }
                        })
                        .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            	
                            	Toast mToast=null;
                            	if (mToast != null) {
                                    mToast.cancel();
                                }
                                mToast = Toast.makeText(Graphic.this, "You choose "+array[lv1_position]+" pattern",
                                        Toast.LENGTH_SHORT);
                                //mToast.show();
                               
                            }
                        })
                        .setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                              
                            }
                        })
                       
                       .create().show();
					} 
        		    else if(arg2==1){
        		    	new AlertDialog.Builder(Graphic.this)
                        .setTitle(R.string.choose_display_catlog)
                        .setSingleChoiceItems(cata, lv2_position, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            	lv2_position=whichButton;
                                
                            }
                        })
                        .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                            	
                            	Toast mToast=null;
                            	if (mToast != null) {
                                    mToast.cancel();
                                }
                                mToast = Toast.makeText(Graphic.this, "You choose "+catalog[lv2_position]+" catalog",
                                        Toast.LENGTH_SHORT);
                                //mToast.show();
                               
                            }
                        })
                        .setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                              
                            }
                        })
                       .create().show();
            		}
             }
        		    
          } 
        );
        
        
       

        lv3=(ExpandableListView)this.findViewById(R.id.list_unit_2);
        List<Map<String, String>> groupData = new ArrayList<Map<String, String>>();
        List<List<Map<String, String>>> childData = new ArrayList<List<Map<String, String>>>();
        Map<String, String> curGroupMap = new HashMap<String, String>();
        groupData.add(curGroupMap);
        curGroupMap.put(NAME, "数据的时间范围");
        //curGroupMap.put(IS_EVEN, "Set the unit of displayed chart with day");
        List<Map<String, String>> children = new ArrayList<Map<String, String>>();
        Map<String, String> curChildMap = new HashMap<String, String>();
        children.add(curChildMap);
        curChildMap.put(NAME, "今天");
        
        curChildMap = new HashMap<String, String>();
        children.add(curChildMap);
        curChildMap.put(NAME, "最近一周");
        
        curChildMap = new HashMap<String, String>();
        children.add(curChildMap);
        curChildMap.put(NAME, "最近30天");
        
        curChildMap = new HashMap<String, String>();
        children.add(curChildMap);
        curChildMap.put(NAME, "最近90天");
        
        childData.add(children);
        
        curGroupMap = new HashMap<String, String>();
        groupData.add(curGroupMap);
        curGroupMap.put(NAME, "血压水平分布");
        //curGroupMap.put(IS_EVEN, "Set the unit of displayed chart with day");
        children = new ArrayList<Map<String, String>>();
        curChildMap = new HashMap<String, String>();
        children.add(curChildMap);
        curChildMap.put(NAME, "通过血压值");
        
        curChildMap = new HashMap<String, String>();
        children.add(curChildMap);
        curChildMap.put(NAME, "通过时间段");
        
        curChildMap = new HashMap<String, String>();
        children.add(curChildMap);
        curChildMap.put(NAME, "通过时间段和血压值");
        
        childData.add(children);
        
        
        
        
        
        
        mAdapter = new SimpleExpandableListAdapter(
                this,
                groupData,
                android.R.layout.simple_expandable_list_item_1,
                new String[] { NAME,  },
                new int[] { android.R.id.text1,  },
                childData,
                android.R.layout.simple_expandable_list_item_1,
                new String[] { NAME,  },
                new int[] { android.R.id.text1, }
                );
        lv3.setAdapter(mAdapter);
        lv3.setOnChildClickListener(this);
        
        
        Cursor cur = getContentResolver().query(CONTENT_URI1,PROJECTION1, null, null, null);
        cur.moveToFirst();
        glocuseValue=new float[14];
        int k=0;
        while(!cur.isAfterLast()) {
        	glocuseValue[k]=Float.parseFloat(cur.getString(1));
        	k++;
        	cur.moveToNext(); 
        }
    }
    
    private void displayChart(int groupPosition, int childPosition,int year,int month,int day){
    	if(groupPosition==0){
    		if(childPosition==0){
    			String smonth,sday;
    			if(_month<10){
                	smonth="0"+_month;
                }
                else{
                	smonth=_month+"";
                }
                if(_day<10){
                	sday="0"+_day;
                }
                else{
                	sday=_day+"";
                }
		    String todaydate=year+"-"+smonth+"-"+sday;
		    Intent intent=getIntent();
	        if(intent.getData()==null)
	        	intent.setData(CONTENT_URI);
	        Cursor cur = getContentResolver().query(getIntent().getData(),PROJECTION, "name='"+catalog[lv2_position]+"' and date='"+todaydate+"'", null, "time ASC");
	        double xv[];
	        double yv[];
	        CTime ct=new CTime();
	        if(cur.getCount()>0){
	        	cur.moveToFirst();
	        	xv=new double[cur.getCount()];
	        	yv=new double[cur.getCount()];
	        	//System.out.println("cur.getCount()="+cur.getCount());
	        	int k=0;
	        	
	        	while(!cur.isAfterLast()) {
	        		yv[k]=Double.parseDouble(cur.getString(0));
	        		
	        		xv[k]=1.0+6*ct.getTime(cur.getString(1));
	        		//System.out.println("yv[k]"+xv[k]);
	        		k++;
	        		cur.moveToNext(); 
	        	}
	        }
	        else{
	        	System.out.println("cur.getCount()="+cur.getCount());
	        	xv=new double[]{-2.0};
	        	yv=new double[]{-2.0};
	        }
		      
	        if(lv1_position==0){
	        	IChart[] mCharts = new IChart[] {new TodayChart()};
	        	mCharts[0].setXV(xv);
	        	mCharts[0].setYV(yv);
	        	
	        	Intent intent1 = mCharts[0].execute(this);
	        	startActivity(intent1);
	        }
	        else if(lv1_position==1){
	        	IChart[] mCharts = new IChart[] {new TodayScatterChart()};
	        	
	        	mCharts[0].setXV(xv);
	        	mCharts[0].setYV(yv);
	        	
	        	Intent intent2 = mCharts[0].execute(this);
	        	startActivity(intent2);
	        }
    		}
    		else if(childPosition==1){
    			
    			double xv[];
    	        double yv[];
    	        CLastWeek clw=new CLastWeek();
    	    	String []week=new String[8];
    	    	clw.getWeek(_year,_month,_day);
    	    	week=clw.getWeek2();
    	    	CTime ct=new CTime();
    	    	//ct.getTime("0:23");
    	    	Intent intent=getIntent();
    	        if(intent.getData()==null)
    	        	intent.setData(CONTENT_URI);
    	        Cursor cur = getContentResolver().query(getIntent().getData(),PROJECTIONWEEK, "name='"+catalog[lv2_position]+"' and date in ('"+week[0]+"','"+week[1]+"','"+week[2]+"','"+week[3]+"','"+week[4]+"','"+week[5]+"','"+week[6]+"')", null, "date,time ASC");
    	        if(cur.getCount()>0){
    	        	cur.moveToFirst();
    	        	xv=new double[cur.getCount()];
    	        	yv=new double[cur.getCount()];
    	        	//System.out.println("cur.getCount()="+cur.getCount());
    	        	int k=0;
    	        	//String s="24:00";
    	        	while(!cur.isAfterLast()) {
    	        		yv[k]=Double.parseDouble(cur.getString(0));
    	        		//System.out.println("yv[k]="+yv[k]);
    	        		if(week[0].equals(cur.getString(1))){
    	        			xv[k]=1.0+ct.getTime(cur.getString(2));
    	        		}
    	        		else if(week[1].equals(cur.getString(1))){
    	        			xv[k]=2.0+ct.getTime(cur.getString(2));
    	        		}
    	        		else if(week[2].equals(cur.getString(1))){
    	        			xv[k]=3.0+ct.getTime(cur.getString(2));
    	        		}
    	        		else if(week[3].equals(cur.getString(1))){
    	        			xv[k]=4.0+ct.getTime(cur.getString(2));
    	        		}
    	        		else if(week[4].equals(cur.getString(1))){
    	        			xv[k]=5.0+ct.getTime(cur.getString(2));
    	        		}
    	        		else if(week[5].equals(cur.getString(1))){
    	        			xv[k]=6.0+ct.getTime(cur.getString(2));
    	        		}
    	        		else if(week[6].equals(cur.getString(1))){
    	        			xv[k]=7.0+ct.getTime(cur.getString(2));
    	        		}
    	        		
    	        	
    	        		k++;
    	        		cur.moveToNext(); 
    	        	}
    	        }
    	        else{
    	        	//System.out.println("cur.getCount()="+cur.getCount());
    	        	xv=new double[]{-2.0};
    	        	yv=new double[]{-2.0};
    	        }
    	    	
    			if(lv1_position==0){
    	        	IChart[] mCharts = new IChart[] {new LastWeek(_year,_month,_day)};
    	        	mCharts[0].setXV(xv);
    	        	mCharts[0].setYV(yv);
    	        	
    	        	Intent intent1 = mCharts[0].execute(this);
    	        	startActivity(intent1);
    	        }
    	        else if(lv1_position==1){
    	        	IChart[] mCharts = new IChart[] {new LastWeekScatterChart(_year,_month,_day)};
    	        	
    	        	mCharts[0].setXV(xv);
    	        	mCharts[0].setYV(yv);
    	        	
    	        	Intent intent2 = mCharts[0].execute(this);
    	        	startActivity(intent2);
    	        }
    		}
    		else if(childPosition==2){
    			double []xv;
    	        double []yv;
    	        
    	        CLastThirtyDays clw=new CLastThirtyDays();
    	    	String []thirty=new String[31];
    	    	clw.getThirtyDay(_year,_month,_day);
    	    	thirty=clw.getThirtyDay2();
    	        Intent intent=getIntent();
    	        if(intent.getData()==null)
    	        	intent.setData(CONTENT_URI);
    	        Cursor cur = getContentResolver().query(getIntent().getData(),PROJECTIONWEEK, "name='"+catalog[lv2_position]+"' and date in ('"+thirty[0]+"','"+thirty[1]+"','"+thirty[2]+"','"+thirty[3]+"','"+thirty[4]+"','"+thirty[5]+"','"+thirty[6]+"','"+thirty[7]+"','"+thirty[8]+"','"+thirty[9]+"','"+thirty[10]+"','"+thirty[11]+"','"+thirty[12]+"','"+thirty[13]+"','"+thirty[14]+"','"+thirty[15]+"','"+thirty[16]+"','"+thirty[17]+"','"+thirty[18]+"','"+thirty[19]+"','"+thirty[20]+"','"+thirty[21]+"','"+thirty[22]+"','"+thirty[23]+"','"+thirty[24]+"','"+thirty[25]+"','"+thirty[26]+"','"+thirty[27]+"','"+thirty[28]+"','"+thirty[29]+"')", null, "date,time ASC");
    	        if(cur.getCount()>0){
    	        	//System.out.println("cur.getCount()="+cur.getCount());
    	        	cur.moveToFirst();
    	        	xv=new double[cur.getCount()];
    	        	yv=new double[cur.getCount()];
    	        	String a[]=new String[cur.getCount()];
    	        	String b[]=new String[cur.getCount()];
    	        	int k=0;
    	        	while(!cur.isAfterLast()) {
    	        		yv[k]=Double.parseDouble(cur.getString(0));
    	        		a[k]=cur.getString(1);
    	        		//System.out.println("a[k]="+a[k]);
    	        		b[k]=cur.getString(2);
    	        		//System.out.println("b[k]="+b[k]);
    	        		k++;
    	        		cur.moveToNext(); 
    	        	}
    	        	xv=clw.getThirtyDayXV(a, b, thirty, k);
    	        }
    	        else{
    	        	xv=new double[]{-2.0};
    	        	yv=new double[]{-2.0};
    	        }
    	        IChart[] mCharts;
    	        if(lv1_position==0){
    	        	mCharts = new IChart[] {new LastThirtyDays(_year,_month,_day)};
    	        }
    	        else{
    	        	mCharts = new IChart[] {new LastThirtyDaysScatter(_year,_month,_day)};
    	        }
	        	
	        	mCharts[0].setXV(xv);
	        	mCharts[0].setYV(yv);
	        	
	        	Intent intent2 = mCharts[0].execute(this);
	        	startActivity(intent2);
    		}
    		else if(childPosition==3){
    			CLastNightDays clnd=new CLastNightDays();
    			String []night=new String[91];
    			clnd.getlastNightDays(_year,_month,_day);
    			night=clnd.getlastNightDays2();
    			/*for(int i=90;i>=0;i--){
    				System.out.println(we[i]);
    			}*/
    			
    			double []xv;
    	        double []yv;
    	        Intent intent=getIntent();
    	        if(intent.getData()==null)
    	        	intent.setData(CONTENT_URI);
    	        Cursor cur = getContentResolver().query(getIntent().getData(),PROJECTIONWEEK, "name='"+catalog[lv2_position]+"' and date in ('"+night[0]+"','"+night[1]+"','"+night[2]+"','"+night[3]+"','"+night[4]+"','"+night[5]+"','"+night[6]+"','"+night[7]+"','"+night[8]+"','"+night[9]+"','"+night[10]+"','"+night[11]+"','"+night[12]+"','"+night[13]+"','"+night[14]+"','"+night[15]+"','"+night[16]+"','"+night[17]+"','"+night[18]+"','"+night[19]+"','"+night[20]+"','"+night[21]+"','"+night[22]+"','"+night[23]+"','"+night[24]+"','"+night[25]+"','"+night[26]+"','"+night[27]+"','"+night[28]+"','"+night[29]+"','"+night[30]+"','"+night[31]+"','"+night[32]+"','"+night[33]+"','"+night[34]+"','"+night[35]+"','"+night[36]+"','"+night[37]+"','"+night[38]+"','"+night[39]+"','"+night[40]+"','"+night[41]+"','"+night[42]+"','"+night[43]+"','"+night[44]+"','"+night[45]+"','"+night[46]+"','"+night[47]+"','"+night[48]+"','"+night[49]+"','"+night[50]+"','"+night[51]+"','"+night[52]+"','"+night[53]+"','"+night[54]+"','"+night[55]+"','"+night[56]+"','"+night[57]+"','"+night[58]+"','"+night[59]+"','"+night[60]+"','"+night[61]+"','"+night[62]+"','"+night[63]+"','"+night[64]+"','"+night[65]+"','"+night[66]+"','"+night[67]+"','"+night[68]+"','"+night[69]+"','"+night[70]+"','"+night[71]+"','"+night[72]+"','"+night[73]+"','"+night[74]+"','"+night[75]+"','"+night[76]+"','"+night[77]+"','"+night[78]+"','"+night[79]+"','"+night[80]+"','"+night[81]+"','"+night[82]+"','"+night[83]+"','"+night[84]+"','"+night[85]+"','"+night[86]+"','"+night[87]+"','"+night[88]+"','"+night[89]+"')", null, "date,time ASC");
    	        if(cur.getCount()>0){
    	        	//System.out.println("cur.getCount()="+cur.getCount());
    	        	cur.moveToFirst();
    	        	xv=new double[cur.getCount()];
    	        	yv=new double[cur.getCount()];
    	        	String a[]=new String[cur.getCount()];
    	        	String b[]=new String[cur.getCount()];
    	        	int k=0;
    	        	while(!cur.isAfterLast()) {
    	        		yv[k]=Double.parseDouble(cur.getString(0));
    	        		a[k]=cur.getString(1);
    	        		//System.out.println("a[k]="+a[k]);
    	        		b[k]=cur.getString(2);
    	        		//System.out.println("b[k]="+b[k]);
    	        		k++;
    	        		cur.moveToNext(); 
    	        	}
    	        	xv=clnd.getNightDayXV(a, b, night, k);
    	        }
    	        else{
    	        	xv=new double[]{-2.0};
    	        	yv=new double[]{-2.0};
    	        }
    	        IChart[] mCharts;
    	        if(lv1_position==0){
    	        	mCharts = new IChart[] {new LastNightDays(_year,_month,_day)};
    	        }
    	        else{
    	        	mCharts = new IChart[] {new LastThirtyDaysScatter(_year,_month,_day)};
    	        }
	        	
	        	mCharts[0].setXV(xv);
	        	mCharts[0].setYV(yv);
	        	
	        	Intent intent2 = mCharts[0].execute(this);
	        	startActivity(intent2);
    		}
    	}
    	else if(groupPosition==1){
    		
    		if(childPosition==0){
    			double []val=new double[3];
    			val[0]=val[1]=val[2]=0.0;
    			Cursor cur = getContentResolver().query(CONTENT_URI,PROJECTION2, "name='Glocuse'", null, null);
    			float datevalues;
    			int tag_po;
    			cur.moveToFirst();
    			System.out.println("Success");
    			int bo;
    			while(!cur.isAfterLast()) {
    				datevalues=Float.parseFloat(cur.getString(0));
    				tag_po=Integer.parseInt(cur.getString(1));
    				if((bo=setimage2(datevalues,tag_po))==0){
    					val[0]=val[0]+1.0;
    				}
    				else if(bo==1){
    					val[1]=val[1]+1.0;
    				}
    				else if(bo==2){
    					val[2]=val[2]+1.0;
    				}
    				
    				cur.moveToNext();
    			}
    			IChart[] mCharts = new IChart[] {new Limit()};
    			mCharts[0].setXV(val);
    			for(int i=0;i<3;i++){
    				System.out.println("val[]="+val[i]);
    			}
        	
    			Intent intent2 = mCharts[0].execute(this);
    			startActivity(intent2);
    		}
    		else if(childPosition==1){
    			double []val=new double[7];
    			val[0]=val[1]=val[2]=val[3]=val[4]=val[5]=val[6]=0.0;
    			Cursor cur = getContentResolver().query(CONTENT_URI,PROJECTION2, "name='Glocuse'", null, null);
    			
    			int tag_po;
    			cur.moveToFirst();
    			System.out.println("Success");
    			int bo;
    			while(!cur.isAfterLast()) {
    				
    				tag_po=Integer.parseInt(cur.getString(1));
    				val[tag_po]=val[tag_po]+1.0;
    				
    				cur.moveToNext();
    			}
    			
    			IChart[] mCharts = new IChart[] {new Category()};
    			mCharts[0].setXV(val);
    			
    			Intent intent2 = mCharts[0].execute(this);
    			startActivity(intent2);
    		}
    		else if(childPosition==2){
    			new AlertDialog.Builder(Graphic.this)
                .setTitle(R.string.choose_display_catlog)
                .setItems(titles, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        /* User clicked so do some stuff */
                    	Cursor cur = getContentResolver().query(CONTENT_URI,PROJECTION2, "name='Glocuse' and tag='"+which+"'", null, null);
                    	double []val=new double[3];
            			val[0]=val[1]=val[2]=0.0;
                    	float datevalues;
            			int tag_po;
            			cur.moveToFirst();
            			System.out.println("Success");
            			int bo;
            			while(!cur.isAfterLast()) {
            				datevalues=Float.parseFloat(cur.getString(0));
            				tag_po=Integer.parseInt(cur.getString(1));
            				if((bo=setimage2(datevalues,tag_po))==0){
            					val[0]=val[0]+1.0;
            				}
            				else if(bo==1){
            					val[1]=val[1]+1.0;
            				}
            				else if(bo==2){
            					val[2]=val[2]+1.0;
            				}
            				
            				cur.moveToNext();
            			}
            			IChart[] mCharts = new IChart[] {new Limit()};
            			mCharts[0].setXV(val);
            			for(int i=0;i<3;i++){
            				System.out.println("val[]="+val[i]);
            			}
                	
            			Intent intent2 = mCharts[0].execute(getBaseContext());
            			startActivity(intent2);
                    }
                })
                .create().show();
    		}
    	}
    }
    
    int _year;
    int _month;
    int _day;
    
    int _groupPosition;
    int _childPosition;
    
    @Override
    protected Dialog onCreateDialog(int id) {
        // TODO Auto-generated method stub
    	Calendar c=Calendar.getInstance();
    	_year=c.get(Calendar.YEAR);
        _month=c.get(Calendar.MONTH);
        _day=c.get(Calendar.DATE);
    	if (id == 0) {
        	
            return new DatePickerDialog(this, new OnDateSetListener() {

                @Override
                public void onDateSet(DatePicker view, int year,
                        int monthOfYear, int dayOfMonth) {
                    // TODO Auto-generated method stub
                    _year = year;
                    _month = monthOfYear+1;
                    _day = dayOfMonth;
                    Toast.makeText(getBaseContext(), _year+":"+_month+":"+_day, Toast.LENGTH_SHORT).show();
                    String m,d;
                    if(_month<10){
                    	m="0"+_month;
                    }
                    else{
                    	m=_month+"";
                    }
                    if(_day<10){
                    	d="0"+_day;
                    }
                    else{
                    	d=_day+"";
                    }
                    displayChart(_groupPosition,_childPosition,_year,_month,_day);
                }

            }, _year, _month, _day);
        } 
        return null;
    }

    
    
    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id){
    	
    	_groupPosition=groupPosition;
    	_childPosition=childPosition;
    	
    	showDialog(0);
    	/*if(groupPosition==0){
    		if(childPosition==0){
    		Calendar c=Calendar.getInstance();
		    int year=c.get(Calendar.YEAR);
		    int month=c.get(Calendar.MONTH)+1;
		    int day=c.get(Calendar.DATE);
		    String smonth;
		    if(month<10){
		    	smonth="0"+month;
		    }
		    else{
		    	smonth=""+month;
		    }
		    
		    String sday;
		    if(day<10){
		    	sday="0"+day;
		    }
		    else{
		    	sday=""+day;
		    }
		    smonth="05";
		    sday="20";
		    String todaydate=year+"-"+smonth+"-"+sday;
		    //System.out.println("today="+todaydate);
		    Intent intent=getIntent();
	        if(intent.getData()==null)
	        	intent.setData(CONTENT_URI);
	        Cursor cur = getContentResolver().query(getIntent().getData(),PROJECTION, "name='"+catalog[lv2_position]+"' and date='"+todaydate+"'", null, "time ASC");
	        double xv[];
	        double yv[];
	        CTime ct=new CTime();
	        if(cur.getCount()>0){
	        	cur.moveToFirst();
	        	xv=new double[cur.getCount()];
	        	yv=new double[cur.getCount()];
	        	//System.out.println("cur.getCount()="+cur.getCount());
	        	int k=0;
	        	
	        	while(!cur.isAfterLast()) {
	        		yv[k]=Double.parseDouble(cur.getString(0));
	        		
	        		xv[k]=1.0+6*ct.getTime(cur.getString(1));
	        		//System.out.println("yv[k]"+xv[k]);
	        		k++;
	        		cur.moveToNext(); 
	        	}
	        }
	        else{
	        	System.out.println("cur.getCount()="+cur.getCount());
	        	xv=new double[]{-2.0};
	        	yv=new double[]{-2.0};
	        }
		      
	        if(lv1_position==0){
	        	IChart[] mCharts = new IChart[] {new TodayChart()};
	        	mCharts[0].setXV(xv);
	        	mCharts[0].setYV(yv);
	        	
	        	Intent intent1 = mCharts[0].execute(this);
	        	startActivity(intent1);
	        }
	        else if(lv1_position==1){
	        	IChart[] mCharts = new IChart[] {new TodayScatterChart()};
	        	
	        	mCharts[0].setXV(xv);
	        	mCharts[0].setYV(yv);
	        	
	        	Intent intent2 = mCharts[0].execute(this);
	        	startActivity(intent2);
	        }
    		}
    		else if(childPosition==1){
    			
    			double xv[];
    	        double yv[];
    	        CLastWeek clw=new CLastWeek();
    	    	String []week=new String[8];
    	    	clw.getWeek();
    	    	week=clw.getWeek2();
    	    	CTime ct=new CTime();
    	    	//ct.getTime("0:23");
    	    	Intent intent=getIntent();
    	        if(intent.getData()==null)
    	        	intent.setData(CONTENT_URI);
    	        Cursor cur = getContentResolver().query(getIntent().getData(),PROJECTIONWEEK, "name='"+catalog[lv2_position]+"' and date in ('"+week[0]+"','"+week[1]+"','"+week[2]+"','"+week[3]+"','"+week[4]+"','"+week[5]+"','"+week[6]+"')", null, "date,time ASC");
    	        if(cur.getCount()>0){
    	        	cur.moveToFirst();
    	        	xv=new double[cur.getCount()];
    	        	yv=new double[cur.getCount()];
    	        	//System.out.println("cur.getCount()="+cur.getCount());
    	        	int k=0;
    	        	//String s="24:00";
    	        	while(!cur.isAfterLast()) {
    	        		yv[k]=Double.parseDouble(cur.getString(0));
    	        		//System.out.println("yv[k]="+yv[k]);
    	        		if(week[0].equals(cur.getString(1))){
    	        			xv[k]=1.0+ct.getTime(cur.getString(2));
    	        		}
    	        		else if(week[1].equals(cur.getString(1))){
    	        			xv[k]=2.0+ct.getTime(cur.getString(2));
    	        		}
    	        		else if(week[2].equals(cur.getString(1))){
    	        			xv[k]=3.0+ct.getTime(cur.getString(2));
    	        		}
    	        		else if(week[3].equals(cur.getString(1))){
    	        			xv[k]=4.0+ct.getTime(cur.getString(2));
    	        		}
    	        		else if(week[4].equals(cur.getString(1))){
    	        			xv[k]=5.0+ct.getTime(cur.getString(2));
    	        		}
    	        		else if(week[5].equals(cur.getString(1))){
    	        			xv[k]=6.0+ct.getTime(cur.getString(2));
    	        		}
    	        		else if(week[6].equals(cur.getString(1))){
    	        			xv[k]=7.0+ct.getTime(cur.getString(2));
    	        		}
    	        		
    	        	
    	        		k++;
    	        		cur.moveToNext(); 
    	        	}
    	        }
    	        else{
    	        	//System.out.println("cur.getCount()="+cur.getCount());
    	        	xv=new double[]{-2.0};
    	        	yv=new double[]{-2.0};
    	        }
    	    	
    			if(lv1_position==0){
    	        	IChart[] mCharts = new IChart[] {new LastWeek()};
    	        	mCharts[0].setXV(xv);
    	        	mCharts[0].setYV(yv);
    	        	
    	        	Intent intent1 = mCharts[0].execute(this);
    	        	startActivity(intent1);
    	        }
    	        else if(lv1_position==1){
    	        	IChart[] mCharts = new IChart[] {new LastWeekScatterChart()};
    	        	
    	        	mCharts[0].setXV(xv);
    	        	mCharts[0].setYV(yv);
    	        	
    	        	Intent intent2 = mCharts[0].execute(this);
    	        	startActivity(intent2);
    	        }
    		}
    		else if(childPosition==2){
    			double []xv;
    	        double []yv;
    	        
    	        CLastThirtyDays clw=new CLastThirtyDays();
    	    	String []thirty=new String[31];
    	    	clw.getThirtyDay();
    	    	thirty=clw.getThirtyDay2();
    	        Intent intent=getIntent();
    	        if(intent.getData()==null)
    	        	intent.setData(CONTENT_URI);
    	        Cursor cur = getContentResolver().query(getIntent().getData(),PROJECTIONWEEK, "name='"+catalog[lv2_position]+"' and date in ('"+thirty[0]+"','"+thirty[1]+"','"+thirty[2]+"','"+thirty[3]+"','"+thirty[4]+"','"+thirty[5]+"','"+thirty[6]+"','"+thirty[7]+"','"+thirty[8]+"','"+thirty[9]+"','"+thirty[10]+"','"+thirty[11]+"','"+thirty[12]+"','"+thirty[13]+"','"+thirty[14]+"','"+thirty[15]+"','"+thirty[16]+"','"+thirty[17]+"','"+thirty[18]+"','"+thirty[19]+"','"+thirty[20]+"','"+thirty[21]+"','"+thirty[22]+"','"+thirty[23]+"','"+thirty[24]+"','"+thirty[25]+"','"+thirty[26]+"','"+thirty[27]+"','"+thirty[28]+"','"+thirty[29]+"')", null, "date,time ASC");
    	        if(cur.getCount()>0){
    	        	//System.out.println("cur.getCount()="+cur.getCount());
    	        	cur.moveToFirst();
    	        	xv=new double[cur.getCount()];
    	        	yv=new double[cur.getCount()];
    	        	String a[]=new String[cur.getCount()];
    	        	String b[]=new String[cur.getCount()];
    	        	int k=0;
    	        	while(!cur.isAfterLast()) {
    	        		yv[k]=Double.parseDouble(cur.getString(0));
    	        		a[k]=cur.getString(1);
    	        		//System.out.println("a[k]="+a[k]);
    	        		b[k]=cur.getString(2);
    	        		//System.out.println("b[k]="+b[k]);
    	        		k++;
    	        		cur.moveToNext(); 
    	        	}
    	        	xv=clw.getThirtyDayXV(a, b, thirty, k);
    	        }
    	        else{
    	        	xv=new double[]{-2.0};
    	        	yv=new double[]{-2.0};
    	        }
    	        IChart[] mCharts;
    	        if(lv1_position==0){
    	        	mCharts = new IChart[] {new LastThirtyDays()};
    	        }
    	        else{
    	        	mCharts = new IChart[] {new LastThirtyDaysScatter()};
    	        }
	        	
	        	mCharts[0].setXV(xv);
	        	mCharts[0].setYV(yv);
	        	
	        	Intent intent2 = mCharts[0].execute(this);
	        	startActivity(intent2);
    		}
    		else if(childPosition==3){
    			CLastNightDays clnd=new CLastNightDays();
    			String []night=new String[91];
    			clnd.getlastNightDays();
    			night=clnd.getlastNightDays2();
    			
    			
    			double []xv;
    	        double []yv;
    	        Intent intent=getIntent();
    	        if(intent.getData()==null)
    	        	intent.setData(CONTENT_URI);
    	        Cursor cur = getContentResolver().query(getIntent().getData(),PROJECTIONWEEK, "name='"+catalog[lv2_position]+"' and date in ('"+night[0]+"','"+night[1]+"','"+night[2]+"','"+night[3]+"','"+night[4]+"','"+night[5]+"','"+night[6]+"','"+night[7]+"','"+night[8]+"','"+night[9]+"','"+night[10]+"','"+night[11]+"','"+night[12]+"','"+night[13]+"','"+night[14]+"','"+night[15]+"','"+night[16]+"','"+night[17]+"','"+night[18]+"','"+night[19]+"','"+night[20]+"','"+night[21]+"','"+night[22]+"','"+night[23]+"','"+night[24]+"','"+night[25]+"','"+night[26]+"','"+night[27]+"','"+night[28]+"','"+night[29]+"','"+night[30]+"','"+night[31]+"','"+night[32]+"','"+night[33]+"','"+night[34]+"','"+night[35]+"','"+night[36]+"','"+night[37]+"','"+night[38]+"','"+night[39]+"','"+night[40]+"','"+night[41]+"','"+night[42]+"','"+night[43]+"','"+night[44]+"','"+night[45]+"','"+night[46]+"','"+night[47]+"','"+night[48]+"','"+night[49]+"','"+night[50]+"','"+night[51]+"','"+night[52]+"','"+night[53]+"','"+night[54]+"','"+night[55]+"','"+night[56]+"','"+night[57]+"','"+night[58]+"','"+night[59]+"','"+night[60]+"','"+night[61]+"','"+night[62]+"','"+night[63]+"','"+night[64]+"','"+night[65]+"','"+night[66]+"','"+night[67]+"','"+night[68]+"','"+night[69]+"','"+night[70]+"','"+night[71]+"','"+night[72]+"','"+night[73]+"','"+night[74]+"','"+night[75]+"','"+night[76]+"','"+night[77]+"','"+night[78]+"','"+night[79]+"','"+night[80]+"','"+night[81]+"','"+night[82]+"','"+night[83]+"','"+night[84]+"','"+night[85]+"','"+night[86]+"','"+night[87]+"','"+night[88]+"','"+night[89]+"')", null, "date,time ASC");
    	        if(cur.getCount()>0){
    	        	//System.out.println("cur.getCount()="+cur.getCount());
    	        	cur.moveToFirst();
    	        	xv=new double[cur.getCount()];
    	        	yv=new double[cur.getCount()];
    	        	String a[]=new String[cur.getCount()];
    	        	String b[]=new String[cur.getCount()];
    	        	int k=0;
    	        	while(!cur.isAfterLast()) {
    	        		yv[k]=Double.parseDouble(cur.getString(0));
    	        		a[k]=cur.getString(1);
    	        		//System.out.println("a[k]="+a[k]);
    	        		b[k]=cur.getString(2);
    	        		//System.out.println("b[k]="+b[k]);
    	        		k++;
    	        		cur.moveToNext(); 
    	        	}
    	        	xv=clnd.getNightDayXV(a, b, night, k);
    	        }
    	        else{
    	        	xv=new double[]{-2.0};
    	        	yv=new double[]{-2.0};
    	        }
    	        IChart[] mCharts;
    	        if(lv1_position==0){
    	        	mCharts = new IChart[] {new LastNightDays()};
    	        }
    	        else{
    	        	mCharts = new IChart[] {new LastThirtyDaysScatter()};
    	        }
	        	
	        	mCharts[0].setXV(xv);
	        	mCharts[0].setYV(yv);
	        	
	        	Intent intent2 = mCharts[0].execute(this);
	        	startActivity(intent2);
    		}
    	}
    	else if(groupPosition==1){
    		
    		if(childPosition==0){
    			double []val=new double[3];
    			val[0]=val[1]=val[2]=0.0;
    			Cursor cur = getContentResolver().query(CONTENT_URI,PROJECTION2, "name='Glocuse'", null, null);
    			float datevalues;
    			int tag_po;
    			cur.moveToFirst();
    			System.out.println("Success");
    			int bo;
    			while(!cur.isAfterLast()) {
    				datevalues=Float.parseFloat(cur.getString(0));
    				tag_po=Integer.parseInt(cur.getString(1));
    				if((bo=setimage2(datevalues,tag_po))==0){
    					val[0]=val[0]+1.0;
    				}
    				else if(bo==1){
    					val[1]=val[1]+1.0;
    				}
    				else if(bo==2){
    					val[2]=val[2]+1.0;
    				}
    				
    				cur.moveToNext();
    			}
    			IChart[] mCharts = new IChart[] {new Limit()};
    			mCharts[0].setXV(val);
    			for(int i=0;i<3;i++){
    				System.out.println("val[]="+val[i]);
    			}
        	
    			Intent intent2 = mCharts[0].execute(this);
    			startActivity(intent2);
    		}
    		else if(childPosition==1){
    			double []val=new double[7];
    			val[0]=val[1]=val[2]=val[3]=val[4]=val[5]=val[6]=0.0;
    			Cursor cur = getContentResolver().query(CONTENT_URI,PROJECTION2, "name='Glocuse'", null, null);
    			
    			int tag_po;
    			cur.moveToFirst();
    			System.out.println("Success");
    			int bo;
    			while(!cur.isAfterLast()) {
    				
    				tag_po=Integer.parseInt(cur.getString(1));
    				val[tag_po]=val[tag_po]+1.0;
    				
    				cur.moveToNext();
    			}
    			
    			IChart[] mCharts = new IChart[] {new Category()};
    			mCharts[0].setXV(val);
    			
    			Intent intent2 = mCharts[0].execute(this);
    			startActivity(intent2);
    		}
    		else if(childPosition==2){
    			new AlertDialog.Builder(Graphic.this)
                .setTitle(R.string.choose_display_catlog)
                .setItems(titles, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                       
                    	Cursor cur = getContentResolver().query(CONTENT_URI,PROJECTION2, "name='Glocuse' and tag='"+which+"'", null, null);
                    	double []val=new double[3];
            			val[0]=val[1]=val[2]=0.0;
                    	float datevalues;
            			int tag_po;
            			cur.moveToFirst();
            			System.out.println("Success");
            			int bo;
            			while(!cur.isAfterLast()) {
            				datevalues=Float.parseFloat(cur.getString(0));
            				tag_po=Integer.parseInt(cur.getString(1));
            				if((bo=setimage2(datevalues,tag_po))==0){
            					val[0]=val[0]+1.0;
            				}
            				else if(bo==1){
            					val[1]=val[1]+1.0;
            				}
            				else if(bo==2){
            					val[2]=val[2]+1.0;
            				}
            				
            				cur.moveToNext();
            			}
            			IChart[] mCharts = new IChart[] {new Limit()};
            			mCharts[0].setXV(val);
            			for(int i=0;i<3;i++){
            				System.out.println("val[]="+val[i]);
            			}
                	
            			Intent intent2 = mCharts[0].execute(getBaseContext());
            			startActivity(intent2);
                    }
                })
                .create().show();
    		}
    	}*/
    	return true;
    }
    
    public int setimage2(float datevalue,int b){
    	int a=-1;
   	 switch(b){
       	case 0:
       		if(datevalue<glocuseValue[0]){
       			a=0;
       		}
       		else if(datevalue>glocuseValue[1]){
       			a=2;
       		}
       		else{
       			a=1;
       		}
       		break;
       	case 1:
       		if(datevalue<glocuseValue[2]){
       			a=0;
       		}
       		else if(datevalue>glocuseValue[3]){
       			a=2;
       		}
       		else{
       			a=1;
       		}
       		break;
       	case 2:
       		if(datevalue<glocuseValue[4]){
       			a=0;
       		}
       		else if(datevalue>glocuseValue[5]){
       			a=2;
       		}
       		else{
       			a=1;
       		}
       		break;
       	case 3:
       		if(datevalue<glocuseValue[6]){
       			a=0;
       		}
       		else if(datevalue>glocuseValue[7]){
       			a=2;
       		}
       		else{
       			a=1;
       		}
       		break;
       	case 4:
       		if(datevalue<glocuseValue[8]){
       			a=0;
       		}
       		else if(datevalue>glocuseValue[9]){
       			a=2;
       		}
       		else{
       			a=1;
       		}
       		break;
       	case 5:
       		if(datevalue<glocuseValue[10]){
       			a=0;
       		}
       		else if(datevalue>glocuseValue[11]){
       			a=2;
       		}
       		else{
       			a=1;
       		}
       		break;
       	case 6:
       		if(datevalue<glocuseValue[12]){
       			a=0;
       		}
       		else if(datevalue>glocuseValue[13]){
       			a=2;
       		}
       		else{
       			a=1;
       		}
       		break;
       	}
   	 return a;
    }
    
    /*String[] titles={"Pre-Breakfast",
	        "Post-Breakfast",
	        "Pre-Lunch",
	        "Post-Lunch",
	        "Pre-Dinner",
	        "Post-Dinner",
	        "Night"}; */
    private String titles[]={"早饭前",
	        "早饭后",
	        "午饭前",
	        "午饭后",
	        "晚饭前",
	        "晚饭后",
	        "夜晚"};

}