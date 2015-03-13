package mconnect.mdiabetes.framework;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


import mconnect.mdiabetes.framework.R;
import mconnect.mdiabetes.statistics.GlocuseAverage;
import mconnect.mdiabetes.statistics.GlocuseByCategary;
import mconnect.mdiabetes.statistics.OverView;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

public class Statistics extends Activity{
	
	private ListView lv;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statistics);
        
        lv=(ListView)this.findViewById(R.id.statistics_lv);
        ArrayList<Map<String, Object>> coll= new ArrayList<Map<String, Object>>();
        Map<String, Object> item;
        for(int i=0;i<5;i++) {
        	item = new HashMap<String, Object>();
        	item.put("name", statisticsv[i]);
        	//item.put("value", "Set with line, the chart will be displayed as line char. Set with scatter, the chart will be display as scatter chart.");
        	item.put("image",R.drawable.rightarrow);
        	coll.add(item);
        }        
        lv.setAdapter(new SimpleAdapter(this, coll,R.layout.basicsetting, new String[] { "name","image" },new int[] {R.id.set_text,R.id.set_image}));
        
        lv.setOnItemClickListener(
        		new AdapterView.OnItemClickListener()  
        		   { 
        		    @Override
					public void onItemClick(AdapterView<?> arg0, View arg1,int arg2, long arg3) {
        		    	if(arg2==0){
        		    		Intent intent=new Intent(Statistics.this,OverView.class);
        		    		startActivity(intent);
        		    	}
        		    	else if(arg2==1){
        		    		Intent intent=new Intent(Statistics.this,GlocuseAverage.class);
        		    		startActivity(intent);
        		    	}
        		    	else if(arg2==2){
        		    		Intent intent=new Intent(Statistics.this,GlocuseByCategary.class);
        		    		Bundle passBundle=new Bundle();
        		    		passBundle.putInt("number", 2);
        		    		intent.putExtras(passBundle);
        		    		startActivity(intent);
        		    	}
        		    	else if(arg2==3){
        		    		Intent intent=new Intent(Statistics.this,GlocuseByCategary.class);
        		    		Bundle passBundle=new Bundle();
        		    		passBundle.putInt("number", 3);
        		    		intent.putExtras(passBundle);
        		    		startActivity(intent);
        		    	}
        		    	else if(arg2==4){
        		    		Intent intent=new Intent(Statistics.this,GlocuseByCategary.class);
        		    		Bundle passBundle=new Bundle();
        		    		passBundle.putInt("number", 4);
        		    		intent.putExtras(passBundle);
        		    		startActivity(intent);
        		    	}
        		    
        		    }
        		   
		});
	}
	
	//private String[] statisticsv={"Overview","Glocuse Average","Glocuse By Categary","Carbs By Categary","Insulin By Categary"};
	//private int[] statisticsv={R.string.overview,R.string.glocuse_average,R.string.glocuse_by_categary,R.string.carb_by_categary,R.string.insuline_by_categary};
	private String[] statisticsv={"整体情况","收缩压平均水平","不同时段收缩压水平","不同时段舒张压水平","不同时段脉搏水平"};
}
