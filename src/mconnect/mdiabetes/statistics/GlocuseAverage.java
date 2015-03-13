package mconnect.mdiabetes.statistics;

import java.text.DecimalFormat;
import java.util.Calendar;

import mconnect.mdiabetes.date.CLastNightDays;
import mconnect.mdiabetes.date.CLastThirtyDays;
import mconnect.mdiabetes.date.CLastWeek;
import mconnect.mdiabetes.framework.R;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;



public class GlocuseAverage extends Activity{
	
	public static final String AUTHORITY = "com.chen.provider.diabeteshelper";
	public static final Uri CONTENT_URI =Uri.parse("content://" + AUTHORITY + "/RecordTable");
	
	private static final String[] PROJECTION = new String[] {"value"};
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Use an existing ListAdapter that will map an array
        // of strings to TextViews
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.glocuseaveragesta);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.i_title);
        
        Button leftBtn = (Button) findViewById(R.id.title_left_btn);
        Button rightBtn = (Button) findViewById(R.id.title_right_btn);
        //title.setText("ÌÇÄò²¡ÐÅÏ¢");
        leftBtn.setVisibility(View.GONE);
        rightBtn.setText("·µ»Ø");
        rightBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
        	
        });
        
        Calendar c=Calendar.getInstance();
        int year=c.get(Calendar.YEAR);
        int month=c.get(Calendar.MONTH)+1;
        int day=c.get(Calendar.DATE);
        String smonth;
        String sday;
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
        String today=year+"-"+smonth+"-"+sday;
        
        Intent intent=getIntent();
        if(intent.getData()==null)
        	intent.setData(CONTENT_URI);
        Cursor cur = getContentResolver().query(getIntent().getData(),PROJECTION, "name='Glocuse' and date='"+today+"'", null, null);
        cur.moveToFirst();
        int today_g_c=cur.getCount();
        //System.out.println("todya="+today_g_c);
        if(today_g_c>0){
        	TextView g_c=(TextView)this.findViewById(R.id.today_g_c);
        	g_c.setText(""+today_g_c);
        	TextView g_v=(TextView)this.findViewById(R.id.today_g_v);
        	double gv=0.0;
        	while(!cur.isAfterLast()) {
        		gv=gv+Double.parseDouble(cur.getString(0));
        		cur.moveToNext(); 
        	}
        	gv=gv/today_g_c;
        	DecimalFormat myformat=new DecimalFormat("#0.0");
        	g_v.setText(myformat.format(gv));
        }
        
        CLastWeek clw=new CLastWeek();
    	String []week=new String[8];
    	clw.getWeek(year,month,day);
    	week=clw.getWeek2();
        cur = getContentResolver().query(getIntent().getData(),PROJECTION, "name='Glocuse' and date in ('"+week[0]+"','"+week[1]+"','"+week[2]+"','"+week[3]+"','"+week[4]+"','"+week[5]+"','"+week[6]+"')", null, null);
        cur.moveToFirst();
        int week_g_c=cur.getCount();
        //System.out.println("week="+week_g_c);
        if(week_g_c>0){
        	TextView g_c=(TextView)this.findViewById(R.id.week_g_c);
        	g_c.setText(""+week_g_c);
        	TextView g_v=(TextView)this.findViewById(R.id.week_g_v);
        	double gv=0.0;
        	while(!cur.isAfterLast()) {
        		gv=gv+Double.parseDouble(cur.getString(0));
        		cur.moveToNext(); 
        	}
        	gv=gv/week_g_c;
        	DecimalFormat myformat=new DecimalFormat("#0.0");
        	g_v.setText(myformat.format(gv));
        }
        
        CLastThirtyDays clt=new CLastThirtyDays();
    	String []thirty=new String[31];
    	clt.getThirtyDay(year,month,day);
    	thirty=clt.getThirtyDay2();
    	cur = getContentResolver().query(getIntent().getData(),PROJECTION, "name='Glocuse' and date in ('"+thirty[0]+"','"+thirty[1]+"','"+thirty[2]+"','"+thirty[3]+"','"+thirty[4]+"','"+thirty[5]+"','"+thirty[6]+"','"+thirty[7]+"','"+thirty[8]+"','"+thirty[9]+"','"+thirty[10]+"','"+thirty[11]+"','"+thirty[12]+"','"+thirty[13]+"','"+thirty[14]+"','"+thirty[15]+"','"+thirty[16]+"','"+thirty[17]+"','"+thirty[18]+"','"+thirty[19]+"','"+thirty[20]+"','"+thirty[21]+"','"+thirty[22]+"','"+thirty[23]+"','"+thirty[24]+"','"+thirty[25]+"','"+thirty[26]+"','"+thirty[27]+"','"+thirty[28]+"','"+thirty[29]+"')", null, null);
        cur.moveToFirst();
        int thirty_g_c=cur.getCount();
        //System.out.println("thirty="+thirty_g_c);
        if(thirty_g_c>0){
        	TextView g_c=(TextView)this.findViewById(R.id.thirth_g_c);
        	g_c.setText(""+thirty_g_c);
        	TextView g_v=(TextView)this.findViewById(R.id.thirth_g_v);
        	double gv=0.0;
        	while(!cur.isAfterLast()) {
        		gv=gv+Double.parseDouble(cur.getString(0));
        		cur.moveToNext(); 
        	}
        	gv=gv/thirty_g_c;
        	DecimalFormat myformat=new DecimalFormat("#0.0");
        	g_v.setText(myformat.format(gv));
        }
        
        CLastNightDays clnd=new CLastNightDays();
		String []night=new String[91];
		clnd.getlastNightDays(year,month,day);
		night=clnd.getlastNightDays2();
		cur = getContentResolver().query(getIntent().getData(),PROJECTION, "name='Glocuse' and date in ('"+night[0]+"','"+night[1]+"','"+night[2]+"','"+night[3]+"','"+night[4]+"','"+night[5]+"','"+night[6]+"','"+night[7]+"','"+night[8]+"','"+night[9]+"','"+night[10]+"','"+night[11]+"','"+night[12]+"','"+night[13]+"','"+night[14]+"','"+night[15]+"','"+night[16]+"','"+night[17]+"','"+night[18]+"','"+night[19]+"','"+night[20]+"','"+night[21]+"','"+night[22]+"','"+night[23]+"','"+night[24]+"','"+night[25]+"','"+night[26]+"','"+night[27]+"','"+night[28]+"','"+night[29]+"','"+night[30]+"','"+night[31]+"','"+night[32]+"','"+night[33]+"','"+night[34]+"','"+night[35]+"','"+night[36]+"','"+night[37]+"','"+night[38]+"','"+night[39]+"','"+night[40]+"','"+night[41]+"','"+night[42]+"','"+night[43]+"','"+night[44]+"','"+night[45]+"','"+night[46]+"','"+night[47]+"','"+night[48]+"','"+night[49]+"','"+night[50]+"','"+night[51]+"','"+night[52]+"','"+night[53]+"','"+night[54]+"','"+night[55]+"','"+night[56]+"','"+night[57]+"','"+night[58]+"','"+night[59]+"','"+night[60]+"','"+night[61]+"','"+night[62]+"','"+night[63]+"','"+night[64]+"','"+night[65]+"','"+night[66]+"','"+night[67]+"','"+night[68]+"','"+night[69]+"','"+night[70]+"','"+night[71]+"','"+night[72]+"','"+night[73]+"','"+night[74]+"','"+night[75]+"','"+night[76]+"','"+night[77]+"','"+night[78]+"','"+night[79]+"','"+night[80]+"','"+night[81]+"','"+night[82]+"','"+night[83]+"','"+night[84]+"','"+night[85]+"','"+night[86]+"','"+night[87]+"','"+night[88]+"','"+night[89]+"')", null, null);
        cur.moveToFirst();
        int night_g_c=cur.getCount();
        //System.out.println("night="+night_g_c);
        if(night_g_c>0){
        	TextView g_c=(TextView)this.findViewById(R.id.night_g_c);
        	g_c.setText(""+night_g_c);
        	TextView g_v=(TextView)this.findViewById(R.id.night_g_v);
        	double gv=0.0;
        	while(!cur.isAfterLast()) {
        		gv=gv+Double.parseDouble(cur.getString(0));
        		cur.moveToNext(); 
        	}
        	gv=gv/night_g_c;
        	DecimalFormat myformat=new DecimalFormat("#0.0");
        	g_v.setText(myformat.format(gv));
        }
        
        
	}

}
