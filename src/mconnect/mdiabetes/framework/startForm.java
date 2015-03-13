package mconnect.mdiabetes.framework;

import mconnect.mdiabetes.framework.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler; 
import android.os.Message;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView; 

public class startForm extends Activity{
	private Handler mHandler=new Handler();

	ImageView imageview; 
	
	int alpha=255;
	int b=0;
	public void onCreate(Bundle savedInstanceState){  
		super.onCreate(savedInstanceState);  
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.startui);   
		imageview=(ImageView)this.findViewById(R.id.ImageView01);  
		
 
		Log.v("ColaBox","ColaBoxstart...");
		imageview.setAlpha(alpha);

		new Thread(new Runnable(){ 
			public void run(){
				initApp();

				while(b<2){ 
					try{  
						if(b==0){
							Thread.sleep(1000);
							b=1;
						}
						else{ 
							Thread.sleep(50); 
						} 
 
						updateApp(); 

					}catch(InterruptedException e){ 
						e.printStackTrace();
					} 
				}
			}
		}).start(); 

		mHandler=new Handler(){ 
			@Override 
			public void handleMessage(Message msg){ 
				super.handleMessage(msg); 
				imageview.setAlpha(alpha); 
				imageview.invalidate();
			}
		}; 
	} 

	public void updateApp(){ 
		alpha-=7;

		if(alpha<=0){ 
			b=2;
			Intent in=new Intent(this,PanelActivity.class); 
			startActivity(in); 
			finish();
		}  
		mHandler.sendMessage(mHandler.obtainMessage()); 
		
	}

	public void initApp(){

	}
}