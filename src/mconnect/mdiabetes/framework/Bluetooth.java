package mconnect.mdiabetes.framework;


import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Toast;
import android.widget.TabHost.OnTabChangeListener;

public class Bluetooth extends TabActivity{
    public static final int OK_ID = Menu.FIRST;
	public static final int CANCEL_ID=Menu.FIRST+1;
	
	enum ServerOrCilent{
		NONE,
		SERVICE,
		CILENT
	};
    private Context mContext;
    static AnimationTabHost mTabHost;
    static String BlueToothAddress = "null";
    static ServerOrCilent serviceOrCilent = ServerOrCilent.NONE;
    static boolean isOpen = false;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);        
        
        mContext = this;        
    	setContentView(R.layout.main3);
        //实例化
    	mTabHost = (AnimationTabHost) getTabHost();         
        mTabHost.addTab(mTabHost.newTabSpec("Tab1")
        		.setIndicator("设备列表",getResources().getDrawable(android.R.drawable.ic_menu_add))
        		.setContent(new Intent(mContext, deviceActivity.class)));      
        mTabHost.addTab(mTabHost.newTabSpec("Tab2").
        		setIndicator("对话列表",getResources().getDrawable(android.R.drawable.ic_menu_add))
        		.setContent(new Intent(mContext, chatActivity.class)));            
        mTabHost.setOnTabChangedListener(new OnTabChangeListener(){
        	public void onTabChanged(String tabId) {
        		// TODO Auto-generated method stub    
        		if(tabId.equals("Tab1"))
        		{        			
        		}
        	}         
        });
        mTabHost.setCurrentTab(0); 
    }
	  public void onActivityResult(int requestCode, int resultCode, Intent data) {

		  Toast.makeText(mContext, "address:", Toast.LENGTH_SHORT).show();

	    }

    @Override
    protected void onDestroy() {
        /* unbind from the service */
    	    	    
        super.onDestroy();
    }      
	public class SiriListItem {
		String message;
		boolean isSiri;

		public SiriListItem(String msg, boolean siri) {
			message = msg;
			isSiri = siri;
		}
	}   
}