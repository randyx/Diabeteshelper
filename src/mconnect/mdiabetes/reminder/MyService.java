package mconnect.mdiabetes.reminder;

import java.sql.Date;

import java.util.Calendar;

import java.util.Timer;
import java.util.TimerTask;

import mconnect.mdiabetes.framework.PanelActivity;
import mconnect.mdiabetes.framework.R;


import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.database.Cursor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

import android.widget.Toast;


public class MyService extends Service{
	//private static final String TAG="MyService";
	public static final String AUTHORITY = "com.chen.provider.diabeteshelper";
	public static final Uri CONTENT_URI =Uri.parse("content://" + AUTHORITY + "/reminder");
	
	private static final String[] PROJECTION = new String[] {"name","dosage","time","music"};
	
	MediaPlayer player;
	
	private String shour;
	private String sminute;
	
	private String nhour;
	private String nminute;
	
	private static int notificationnum=1;
	
	private Notification notification;   
    private NotificationManager notificationManager;   
    private Intent intent;   
    private PendingIntent pendIntent;
    private int music;
    
    //private static int state=0;
	
	@Override
	public IBinder onBind(Intent intent){
		return null;
	}
	
	
	
	@Override
	public void onCreate(){
		
		
		player=MediaPlayer.create(this, R.raw.braincandy);
		player.setLooping(false);
		
		notificationManager = (NotificationManager) this.getSystemService(NOTIFICATION_SERVICE);
		intent = new Intent(this, PanelActivity.class);
		pendIntent = PendingIntent.getActivity(this, 0, intent, 0);   
		
	}
	
	@Override
	public void onStart(Intent intent, int startid){
		
		
		Date curDate=new Date(System.currentTimeMillis());
		
		timer.schedule(task, curDate,300000); 
		
	}
	
	@Override
	public void onDestroy(){
		Toast.makeText(this, "My Service Stoped", Toast.LENGTH_LONG).show();
		player.stop();
	}
	
	Timer timer = new Timer();   
	Handler handler = new Handler(){   
	public void handleMessage(Message msg) {   
		switch (msg.what) {       
			case 1:       
				System.out.println("hear me?");
				
				Date date=new Date(System.currentTimeMillis()+300000);
				Calendar c=Calendar.getInstance();
				if(c.get(Calendar.HOUR_OF_DAY)<10){
		        	nhour="0"+c.get(Calendar.HOUR_OF_DAY);
		        }
		        else{
		        	nhour=""+c.get(Calendar.HOUR_OF_DAY);
		        }
				
				if(c.get(Calendar.MINUTE)<10){
		        	nminute="0"+c.get(Calendar.MINUTE);
		        }
		        else{
		        	nminute=""+c.get(Calendar.MINUTE);
		        }
				
		        c.setTime(date);
		        if(c.get(Calendar.HOUR_OF_DAY)<10){
		        	shour="0"+c.get(Calendar.HOUR_OF_DAY);
		        }
		        else{
		        	shour=""+c.get(Calendar.HOUR_OF_DAY);
		        }
		        if(c.get(Calendar.MINUTE)<10){
		        	sminute="0"+c.get(Calendar.MINUTE);
		        }
		        else{
		        	sminute=""+c.get(Calendar.MINUTE);
		        }
		        
		        Cursor cur = getContentResolver().query(CONTENT_URI,PROJECTION, "time>='"+nhour+":"+nminute+"' and time<='"+shour+":"+sminute+"'", null, null);
		        cur.moveToFirst();
		        
		        if(cur.getCount()>0){
		        	music=Integer.parseInt(cur.getString(3));
		        	if(music==0){
		        		player.start();
		        	}
		        
		        	while(!cur.isAfterLast()) {
		        		notification = new Notification();
		        		notification.icon = R.drawable.pill;  
		        		notification.tickerText = "DiabetesHelper Reminder.......";   
		        		notification.defaults = Notification.DEFAULT_SOUND;
		              
		        		notification.setLatestEventInfo(MyService.this,cur.getString(0), "At "+cur.getString(2)+", take "+cur.getString(0)+", dosage is "+cur.getString(1)+".", pendIntent); 
		              
		        		notificationManager.notify(notificationnum, notification);
		        		notificationnum++;
		        		cur.moveToNext(); 
		        	}
		        }
		        
				
	            break;       
	        }       
	        super.handleMessage(msg);   
		}   
	};   
	
	TimerTask task = new TimerTask(){   
	
	public void run() {   
	    Message message = new Message();       
	    message.what = 1;       
	    handler.sendMessage(message);     
	}   
	        
};   

}
