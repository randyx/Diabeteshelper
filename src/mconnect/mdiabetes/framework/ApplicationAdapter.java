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

public class ApplicationAdapter extends BaseAdapter {   
    Activity activity;   
    private Integer[] image ={R.drawable.diabeteshelper,R.drawable.medicinetimer,R.drawable.personamedical,R.drawable.healthchannel,R.drawable.virtuldoctor,R.drawable.mobilehealth,R.drawable.medicineinfo,R.drawable.healthgame,R.drawable.help,};    
    //private String[] name={"Record","Reminder","Education","Setting","Help"};
    private String[] name={"糖尿病小助手","医药闹钟","个人健康档案","保健频道","虚拟医生","移动就医","药物信息","寓医于乐","帮助"};
    //construct   
    public ApplicationAdapter(Activity a ) {   
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
        tv.setTextSize(15);
        //tv.setText(name[position]);
        tv.setTextColor(Color.WHITE);
        layout.addView(tv,new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));   
        
    	

        return layout;   
    } 
    
    
}