package mconnect.mdiabetes.framework;

import mconnect.mdiabetes.framework.R;

import android.app.Activity;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ImageList extends BaseAdapter {   
    Activity activity;   
    private Integer[] image ={R.drawable.events_48,R.drawable.clock_48,R.drawable.plus_48,R.drawable.settings_48,R.drawable.set_date_48};    
    //private String[] name={"Record","Reminder","Education","Setting","Help"};
    private int[] name={R.string.function_record,R.string.function_reminder,R.string.function_education,R.string.function_setting,R.string.function_help};
    //construct   
    public ImageList(Activity a ) {   
        activity = a;   
    }   
       
    @Override  
    public int getCount() {   
        // TODO Auto-generated method stub   
        return image.length;   
    }   

    @Override  
    public Object getItem(int position) {   
        // TODO Auto-generated method stub   
        return image[position];   
    }   

    @Override  
    public long getItemId(int position) {   
        // TODO Auto-generated method stub   
        return position;   
    }   

    @Override  
    public View getView(int position, View convertView, ViewGroup parent) {   
        // TODO Auto-generated method stub   
    	LinearLayout layout = new LinearLayout(activity);   
        layout.setOrientation(LinearLayout.VERTICAL);   
        layout.setGravity(Gravity.CENTER);  
        ImageView iv = new ImageView(activity);   
        iv.setImageResource(image[position]);  
        //iv.setGravity(Gravity.CENTER);
        
        layout.addView(iv,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));   
        
        TextView tv = new TextView(activity);   
        tv.setGravity(Gravity.CENTER);   
        tv.setSingleLine(true);   
        tv.setText(name[position]);
        //tv.setText(name[position]);
        tv.setTextColor(Color.WHITE);
        layout.addView(tv,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));   
        
    	

        return layout;   
    } 
    
    
}