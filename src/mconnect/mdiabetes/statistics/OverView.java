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



public class OverView extends Activity{
	public static final String AUTHORITY = "com.chen.provider.diabeteshelper";
	public static final Uri CONTENT_URI =Uri.parse("content://" + AUTHORITY + "/RecordTable");
	
	private static final String[] PROJECTION = new String[] {"value"};
	
	
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.overview);
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
        Intent intent=getIntent();
        if(intent.getData()==null)
        	intent.setData(CONTENT_URI);
        Cursor cur = getContentResolver().query(getIntent().getData(),PROJECTION, "name='Glocuse'", null, null);
        cur.moveToFirst();
        int g=cur.getCount();
        if(g>0){
        	TextView g_c=(TextView)this.findViewById(R.id.g_c);
        	g_c.setText(""+g);
        	TextView g_v=(TextView)this.findViewById(R.id.g_v);
        	double gv=0.0;
        	while(!cur.isAfterLast()) {
        		gv=gv+Double.parseDouble(cur.getString(0));
        		cur.moveToNext(); 
        	}
        	gv=gv/g;
        	DecimalFormat myformat=new DecimalFormat("#0.0");
        	g_v.setText(myformat.format(gv));
        }
        
        cur = getContentResolver().query(getIntent().getData(),PROJECTION, "name='Carbs'", null, null);
        cur.moveToFirst();
        int c=cur.getCount();
        if(c>0){
        	TextView c_c=(TextView)this.findViewById(R.id.c_c);
        	c_c.setText(""+c);
        	TextView c_v=(TextView)this.findViewById(R.id.c_v);
        	double cv=0.0;
        	while(!cur.isAfterLast()) {
        		cv=cv+Double.parseDouble(cur.getString(0));
        		cur.moveToNext(); 
        	}
        	cv=cv/c;
        	DecimalFormat myformat=new DecimalFormat("#0.0");
        	c_v.setText(myformat.format(cv));
        }
        
        cur = getContentResolver().query(getIntent().getData(),PROJECTION, "name='Insulin'", null, null);
        cur.moveToFirst();
        int i=cur.getCount();
        if(i>0){
        	TextView i_c=(TextView)this.findViewById(R.id.i_c);
        	i_c.setText(""+i);
        	TextView i_v=(TextView)this.findViewById(R.id.i_v);
        	double iv=0.0;
        	while(!cur.isAfterLast()) {
        		iv=iv+Double.parseDouble(cur.getString(0));
        		cur.moveToNext(); 
        	}
        	iv=iv/i;
        	DecimalFormat myformat=new DecimalFormat("#0.0");
        	i_v.setText(myformat.format(iv));
        }
    
	}

}
