package mconnect.mdiabetes.framework;

import mconnect.mdiabetes.education.*;
import mconnect.mdiabetes.framework.R;
import mconnect.mdiabetes.reminder.Reminder;

import org.miscwidgets.interpolator.ElasticInterpolator;

import org.miscwidgets.interpolator.EasingType.Type;
import org.miscwidgets.widget.Panel;
import org.miscwidgets.widget.Panel.OnPanelListener;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.opengl.Visibility;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

public class PanelActivity extends Activity implements OnPanelListener {

	private Panel bottomPanel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.panel_main);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.i_title);
        //this.setTitle("ÌÇÄò²¡Ð¡ÖúÊÖ");
        final TextView title = (TextView) findViewById(R.id.title);
        final Button leftBtn = (Button) findViewById(R.id.title_left_btn);
        final Button rightBtn = (Button) findViewById(R.id.title_right_btn);
        title.setText("");
        leftBtn.setVisibility(View.GONE);
        rightBtn.setText("ÍË³ö");
        rightBtn.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
        	
        });
        Panel panel;
        
        

        bottomPanel = panel = (Panel) findViewById(R.id.bottomPanel);
        panel.setOnPanelListener(this);
        panel.setInterpolator(new ElasticInterpolator(Type.OUT, 1.0f, 0.3f));//ÈÃ´°¿Ú¶¶¶¯
        
        
        GridView gv = (GridView) findViewById(R.id.gride);   
        ImageList adapter = new ImageList(this);   
        gv.setAdapter(adapter); 
        gv.setBackgroundColor(Color.BLACK);
        
        
        
        gv.setOnItemClickListener(
        	new OnItemClickListener(){   
        	@Override
        	public void onItemClick(AdapterView<?>arg0, View arg1,int arg2, long arg3) {
                
        		if(arg2==0){
        			Intent in = new Intent(PanelActivity.this, Tab1.class);
 	    			startActivity(in);
        		}
        		else if(arg2==1){
        			Intent in = new Intent(PanelActivity.this, Reminder.class);
 	    			startActivity(in);
        		}
        		else if(arg2==2){
        			Intent in = new Intent(PanelActivity.this, index.class);
 	    			startActivity(in);
        		}
        		else if(arg2==3){
        			Intent in = new Intent(PanelActivity.this, Setting.class);
 	    			startActivity(in);
        		}
        		else if(arg2==4){
        			Intent in = new Intent(PanelActivity.this, ApiDemos.class);
 	    			startActivity(in);
        		}
            }   
               
        });
        
       
    }
    
  

	public void onPanelClosed(Panel panel) {
		String panelName = getResources().getResourceEntryName(panel.getId());
		//Log.d("TestPanels", "Panel [" + panelName + "] closed");
	}
	public void onPanelOpened(Panel panel) {
		String panelName = getResources().getResourceEntryName(panel.getId());
		//Log.d("TestPanels", "Panel [" + panelName + "] opened");
	}
}