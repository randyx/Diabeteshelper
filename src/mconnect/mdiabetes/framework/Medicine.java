package mconnect.mdiabetes.framework;




import mconnect.mdiabetes.framework.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.AdapterView.OnItemClickListener;


public class Medicine extends Activity {
	private Button bt1;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.medicine);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.i_title_1);
        
        bt1=(Button)this.findViewById(R.id.application_back_btn);
        bt1.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
        	
        });
        
        GridView gv = (GridView) findViewById(R.id.application_gride);
        ApplicationAdapter adapter = new ApplicationAdapter(this);   
        gv.setAdapter(adapter);
        
        gv.setOnItemClickListener(
            	new OnItemClickListener(){   
            	@Override
            	public void onItemClick(AdapterView<?>arg0, View arg1,int arg2, long arg3) {
                    
            		if(arg2==0){
            			Intent in = new Intent(Medicine.this, PanelActivity.class);
     	    			startActivity(in);
            		}
            		/*else if(arg2==1){
            			Intent in = new Intent(Medicine.this, Reminder.class);
     	    			startActivity(in);
            		}
            		else if(arg2==2){
            			Intent in = new Intent(Medicine.this, TestTab.class);
     	    			startActivity(in);
            		}
            		else if(arg2==3){
            			Intent in = new Intent(Medicine.this, mainActivity.class);
     	    			startActivity(in);
            		}
            		else if(arg2==4){
            			Intent in = new Intent(Medicine.this, VirtulDoctor.class);
     	    			startActivity(in);
            		}
            		else if(arg2==5){
            			Intent in = new Intent(Medicine.this, MobileDoc.class);
     	    			startActivity(in);
            		}
            		else if(arg2==6){
            			Intent in = new Intent(Medicine.this, MedicineInfo.class);
     	    			startActivity(in);
            		}*/
                }   
                   
            });
    }
}