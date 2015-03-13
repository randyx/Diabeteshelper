package mconnect.mdiabetes.framework;


import mconnect.mdiabetes.framework.R;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;

import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;
import android.view.Gravity;
import android.view.Window;

import android.view.View;
import android.view.View.OnClickListener;


/**
 * An example of tabs that uses labels ({@link TabSpec#setIndicator(CharSequence)})
 * for its indicators and views by id from a layout file ({@link TabSpec#setContent(int)}).
 */
public class Tab1 extends TabActivity {
	
	public static final String Tab1 = "Tab1";   
	public static final String Tab2 = "Tab2";   
	public static final String Tab3 = "Tab3";   
	public static final String Tab4 = "Tab4";   
	


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        TabHost tHost = this.getTabHost();
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
        
        //LayoutInflater.from(this).inflate(R.layout.tabs1, tHost.getTabContentView(), true);
        
        tHost.addTab(tHost.newTabSpec(Tab1).setIndicator("").setContent(new Intent(this, recordActivity.class)));   
        tHost.addTab(tHost.newTabSpec(Tab2).setIndicator("").setContent(new Intent(this, Graphic.class)));   
        tHost.addTab(tHost.newTabSpec(Tab3).setIndicator("").setContent(new Intent(this, testLayout.class))); 
        tHost.addTab(tHost.newTabSpec(Tab4).setIndicator("").setContent(new Intent(this, Statistics.class)));
        
        LinearLayout ll=(LinearLayout)tHost.getChildAt(0);
        TabWidget tw =(TabWidget)ll.getChildAt(0);
        RelativeLayout rl =(RelativeLayout)tw.getChildAt(0);
        rl.addView(composeLayout("记录",R.drawable.records));
        
        rl =(RelativeLayout)tw.getChildAt(1);
        rl.addView(composeLayout("统计图",R.drawable.chart));
        
        rl =(RelativeLayout)tw.getChildAt(2);
        rl.addView(composeLayout("日历",R.drawable.calendar));
        
        rl =(RelativeLayout)tw.getChildAt(3);
        rl.addView(composeLayout("统计表",R.drawable.dice));
        
        //tHost.getTabWidget(2).setOnClickListener(this);
        this.setTitle(R.string.diabetes_information);
    }
    
    public View composeLayout(String s, int i){   
        LinearLayout layout = new LinearLayout(this);   
        layout.setOrientation(LinearLayout.VERTICAL);   
        layout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,LinearLayout.LayoutParams.FILL_PARENT));   
        layout.setGravity(Gravity.CENTER);  
        ImageView iv = new ImageView(this);   
        iv.setImageResource(i);  
        
        layout.addView(iv,    
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, 22));   
        
        TextView tv = new TextView(this);   
        tv.setGravity(Gravity.CENTER);   
        tv.setSingleLine(true);   
        tv.setText(s);   
        tv.setTextColor(Color.WHITE);
        layout.addView(tv,    
                new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT));   
        
        return layout;   
    } 
    
    

}