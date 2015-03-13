package mconnect.mdiabetes.statistics;

import java.text.DecimalFormat;

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



public class GlocuseByCategary extends Activity{
	private int catagarynumber=0;
	private String []categary={"Glocuse","Carbs","Insulin"};
	public static final String AUTHORITY = "com.chen.provider.diabeteshelper";
	public static final Uri CONTENT_URI =Uri.parse("content://" + AUTHORITY + "/RecordTable");
	
	private static final String[] PROJECTION = new String[] {"value"};
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Use an existing ListAdapter that will map an array
        // of strings to TextViews
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.glocusebycategary);
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
        
        Bundle getBundle=getIntent().getExtras();
        catagarynumber=getBundle.getInt("number");
        
        Intent intent=getIntent();
        if(intent.getData()==null)
        	intent.setData(CONTENT_URI);
        Cursor cur = getContentResolver().query(getIntent().getData(),PROJECTION, "name='"+categary[catagarynumber-2]+"' and tag='0'", null, null);
        cur.moveToFirst();
        int num=cur.getCount();
        if(num>0){
        	TextView g_c=(TextView)this.findViewById(R.id.pre_b_c);
        	g_c.setText(""+num);
        	TextView g_v=(TextView)this.findViewById(R.id.pre_b_v);
        	double gv=0.0;
        	while(!cur.isAfterLast()) {
        		gv=gv+Double.parseDouble(cur.getString(0));
        		cur.moveToNext(); 
        	}
        	gv=gv/num;
        	DecimalFormat myformat=new DecimalFormat("#0.0");
        	g_v.setText(myformat.format(gv));
        }
        
        cur = getContentResolver().query(getIntent().getData(),PROJECTION, "name='"+categary[catagarynumber-2]+"' and tag='1'", null, null);
        cur.moveToFirst();
        num=cur.getCount();
        if(num>0){
        	TextView g_c=(TextView)this.findViewById(R.id.post_b_c);
        	g_c.setText(""+num);
        	TextView g_v=(TextView)this.findViewById(R.id.post_b_v);
        	double gv=0.0;
        	while(!cur.isAfterLast()) {
        		gv=gv+Double.parseDouble(cur.getString(0));
        		cur.moveToNext(); 
        	}
        	gv=gv/num;
        	DecimalFormat myformat=new DecimalFormat("#0.0");
        	g_v.setText(myformat.format(gv));
        }
        
        cur = getContentResolver().query(getIntent().getData(),PROJECTION, "name='"+categary[catagarynumber-2]+"' and tag='2'", null, null);
        cur.moveToFirst();
        num=cur.getCount();
        if(num>0){
        	TextView g_c=(TextView)this.findViewById(R.id.pre_l_c);
        	g_c.setText(""+num);
        	TextView g_v=(TextView)this.findViewById(R.id.pre_l_v);
        	double gv=0.0;
        	while(!cur.isAfterLast()) {
        		gv=gv+Double.parseDouble(cur.getString(0));
        		cur.moveToNext(); 
        	}
        	gv=gv/num;
        	DecimalFormat myformat=new DecimalFormat("#0.0");
        	g_v.setText(myformat.format(gv));
        }
        
        cur = getContentResolver().query(getIntent().getData(),PROJECTION, "name='"+categary[catagarynumber-2]+"' and tag='3'", null, null);
        cur.moveToFirst();
        num=cur.getCount();
        if(num>0){
        	TextView g_c=(TextView)this.findViewById(R.id.post_l_c);
        	g_c.setText(""+num);
        	TextView g_v=(TextView)this.findViewById(R.id.post_l_v);
        	double gv=0.0;
        	while(!cur.isAfterLast()) {
        		gv=gv+Double.parseDouble(cur.getString(0));
        		cur.moveToNext(); 
        	}
        	gv=gv/num;
        	DecimalFormat myformat=new DecimalFormat("#0.0");
        	g_v.setText(myformat.format(gv));
        }
        
        cur = getContentResolver().query(getIntent().getData(),PROJECTION, "name='"+categary[catagarynumber-2]+"' and tag='4'", null, null);
        cur.moveToFirst();
        num=cur.getCount();
        if(num>0){
        	TextView g_c=(TextView)this.findViewById(R.id.pre_d_c);
        	g_c.setText(""+num);
        	TextView g_v=(TextView)this.findViewById(R.id.pre_d_v);
        	double gv=0.0;
        	while(!cur.isAfterLast()) {
        		gv=gv+Double.parseDouble(cur.getString(0));
        		cur.moveToNext(); 
        	}
        	gv=gv/num;
        	DecimalFormat myformat=new DecimalFormat("#0.0");
        	g_v.setText(myformat.format(gv));
        }
        
        cur = getContentResolver().query(getIntent().getData(),PROJECTION, "name='"+categary[catagarynumber-2]+"' and tag='5'", null, null);
        cur.moveToFirst();
        num=cur.getCount();
        if(num>0){
        	TextView g_c=(TextView)this.findViewById(R.id.post_d_c);
        	g_c.setText(""+num);
        	TextView g_v=(TextView)this.findViewById(R.id.post_d_v);
        	double gv=0.0;
        	while(!cur.isAfterLast()) {
        		gv=gv+Double.parseDouble(cur.getString(0));
        		cur.moveToNext(); 
        	}
        	gv=gv/num;
        	DecimalFormat myformat=new DecimalFormat("#0.0");
        	g_v.setText(myformat.format(gv));
        }
        
        cur = getContentResolver().query(getIntent().getData(),PROJECTION, "name='"+categary[catagarynumber-2]+"' and tag='6'", null, null);
        cur.moveToFirst();
        num=cur.getCount();
        if(num>0){
        	TextView g_c=(TextView)this.findViewById(R.id.night_c);
        	g_c.setText(""+num);
        	TextView g_v=(TextView)this.findViewById(R.id.night_v);
        	double gv=0.0;
        	while(!cur.isAfterLast()) {
        		gv=gv+Double.parseDouble(cur.getString(0));
        		cur.moveToNext(); 
        	}
        	gv=gv/num;
        	DecimalFormat myformat=new DecimalFormat("#0.0");
        	g_v.setText(myformat.format(gv));
        }
	}

}
