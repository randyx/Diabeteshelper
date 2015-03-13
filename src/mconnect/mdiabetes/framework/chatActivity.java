package mconnect.mdiabetes.framework;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.UUID;

import mconnect.mdiabetes.framework.Bluetooth.ServerOrCilent;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

@SuppressLint("NewApi")
public class chatActivity extends Activity implements OnItemClickListener ,OnClickListener{
    /** Called when the activity is first created. */
private static String AUTHORITY = "com.chen.provider.diabeteshelper";
	
	public static final int OK_ID = Menu.FIRST;
	public static final int CANCEL_ID=Menu.FIRST+1;
	
	private Button date1;
	private Button time1;
	private Button tag1;
	
	private String s_value;
	private String s_high_value;
	private String s_low_value;
	private String s_pulse_value;
	private String s_note;
	private String s_date;
	private String s_time;
	private String s_tag;
	
	private int year;
	private int month;
	private int day;
	private int hour;
	private int minitue;
	
	private int tag_position=0;
	private int name_position=0;
	
	static final int TIME_DIALOG_ID = 0;
    static final int DATE_DIALOG_ID = 1;
    
    private String b_s_value;
	private String b_s_date;
	private String b_s_time;
	
	private String smonth;
	private String sday;
	private String shour;
	private String sminute;

    static AnimationTabHost mTabHost;
    static String BlueToothAddress = "null";
    static ServerOrCilent serviceOrCilent = ServerOrCilent.NONE;
    static boolean isOpen = false;
	private ListView mListView;
	private ArrayList<deviceListItem>list;
	private Button sendButton;
	private Button disconnectButton;
	private EditText editMsgView;
	deviceListAdapter mAdapter;
	Context mContext;
	//InfoConv informationConvert;
	int inforflag = 0,n=0,count=0;
	
	
	/* 一些常量，代表服务器的名称 */
	public static final String PROTOCOL_SCHEME_L2CAP = "btl2cap";
	public static final String PROTOCOL_SCHEME_RFCOMM = "btspp";
	public static final String PROTOCOL_SCHEME_BT_OBEX = "btgoep";
	public static final String PROTOCOL_SCHEME_TCP_OBEX = "tcpobex";
	
	private BluetoothServerSocket mserverSocket = null;
	private ServerThread startServerThread = null;
	private clientThread clientConnectThread = null;
	private BluetoothSocket socket = null;
	private BluetoothDevice device = null;
	private readThread mreadThread = null;;	
	private BluetoothAdapter mBluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.chat);
        mContext = this;
        init();

    }
    
	private void init() {		   
		list = new ArrayList<deviceListItem>();
		mAdapter = new deviceListAdapter(this, list);
		mListView = (ListView) findViewById(R.id.list);
		mListView.setAdapter(mAdapter);
		mListView.setOnItemClickListener(this);
		mListView.setFastScrollEnabled(true);
		editMsgView= (EditText)findViewById(R.id.MessageText);	
		editMsgView.clearFocus();
		
		sendButton= (Button)findViewById(R.id.btn_msg_send);
		sendButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String msgText = editMsgView.getText().toString();
				if (msgText.length()>0) {
					sendMessageHandle(msgText);	
					editMsgView.setText("");
					editMsgView.clearFocus();
					//close InputMethodManager
					InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE); 
					imm.hideSoftInputFromWindow(editMsgView.getWindowToken(), 0);
				}else
				Toast.makeText(mContext, "发送内容不能为空！", Toast.LENGTH_SHORT).show();
			}
		});
		
		disconnectButton= (Button)findViewById(R.id.btn_disconnect);
		disconnectButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
		        if (Bluetooth.serviceOrCilent == ServerOrCilent.CILENT) 
				{
		        	shutdownClient();
				}
				else if (Bluetooth.serviceOrCilent == ServerOrCilent.SERVICE) 
				{
					shutdownServer();
				}
				Bluetooth.isOpen = false;
				Bluetooth.serviceOrCilent=ServerOrCilent.NONE;
				Toast.makeText(mContext, "已断开连接！", Toast.LENGTH_SHORT).show();
			}
		});	
		date1=(Button)findViewById(R.id.set_date1);
        
        time1=(Button)findViewById(R.id.set_time1);
        System.out.println(R.id.set_date1);

        tag1=(Button)findViewById(R.id.set_tag1);
                
        Calendar c=Calendar.getInstance();
        year=c.get(Calendar.YEAR);
        month=c.get(Calendar.MONTH)+1;
        day=c.get(Calendar.DATE);
        hour=c.get(Calendar.HOUR_OF_DAY);
        minitue=c.get(Calendar.MINUTE);
        
        
        
        if(month<10){
        	smonth="0"+month;
        }
        else{
        	smonth=""+month;
        }
        
        if(day<10){
        	sday="0"+day;
        }
        else{
        	sday=""+day;
        }
        
        if(hour<10){
        	shour="0"+hour;
        }
        else{
        	shour=""+hour;
        }
        
        if(minitue<10){
        	sminute="0"+minitue;
        }
        else{
        	sminute=""+minitue;
        }
        
        s_date=""+year+"-"+smonth+"-"+sday;
        s_time=""+shour+":"+sminute;
        tag1.setText(panduanTag(s_time));
        date1.setText(s_date);
        time1.setText(s_time);
	}    

	private String[] title={"Glocuse","Carbs","Insulin"};
    private String[] value={"ml/dL","    g    ","    u    "};
    
    
    private String array[]={"早饭前",
	        "早饭后",
	        "午饭前",
	        "午饭后",
	        "晚饭前",
	        "晚饭后",
	        "夜晚"};
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case TIME_DIALOG_ID:
                return new TimePickerDialog(this,
                        mTimeSetListener, hour, minitue, false);
            case DATE_DIALOG_ID:
                return new DatePickerDialog(this,
                            mDateSetListener,
                            year, month-1, day);
        }
        return null;
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        switch (id) {
            case TIME_DIALOG_ID:
                ((TimePickerDialog) dialog).updateTime(hour, minitue);
                break;
            case DATE_DIALOG_ID:
                ((DatePickerDialog) dialog).updateDate(year, month-1, day);
                break;
        }
    }    
    
    private DatePickerDialog.OnDateSetListener mDateSetListener =
            new DatePickerDialog.OnDateSetListener() {

                public void onDateSet(DatePicker view, int yea, int monthOfYear,
                        int dayOfMonth) {
                    year = yea;
                    month = monthOfYear+1;
                    day = dayOfMonth;
                    
                    if(month<10){
                    	smonth="0"+month;
                    }
                    else{
                    	smonth=""+month;
                    }
                    
                    if(day<10){
                    	sday="0"+day;
                    }
                    else{
                    	sday=""+day;
                    }
                    
                    s_date=""+year+"-"+smonth+"-"+sday;
                    date1.setText(s_date);
                }
            };

    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
            new TimePickerDialog.OnTimeSetListener() {

                public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                    hour = hourOfDay;
                    minitue = minute;
                    
                    if(hour<10){
                    	shour="0"+hour;
                    }
                    else{
                    	shour=""+hour;
                    }
                    
                    if(minitue<10){
                    	sminute="0"+minitue;
                    }
                    else{
                    	sminute=""+minitue;
                    }
                    
                    s_time=""+shour+":"+sminute;
                    time1.setText(s_time);
                    panduanTag(s_time);
                }
            };
       
    public String panduanTag(String time){
    	Cursor cur = getContentResolver().query(CONTENT_URI_1,PROJECTION_1, null, null, null);
        cur.moveToFirst();
        String s[]=new String[7]; 
        int i=0;
        //System.out.println("time="+time);
        while(!cur.isAfterLast()) {
         	s[i]=cur.getString(1);
         	//System.out.println("cur.getString="+s[i]);
         	cur.moveToNext(); 
         	i++;
        }
        if(time.compareTo(s[6])>0){
        	tag_position=6;
        }
        else if(time.compareTo(s[5])>0){
        	tag_position=5;
        }
        else if(time.compareTo(s[4])>0){
        	tag_position=4;
        }
        else if(time.compareTo(s[3])>0){
        	tag_position=3;
        }
        else if(time.compareTo(s[2])>0){
        	tag_position=2;
        }
        else if(time.compareTo(s[1])>0){
        	tag_position=1;
        }
        else{
        	tag_position=0;
        }
        System.out.println("tag_position="+tag_position);
        s_tag=array[tag_position];
        return s_tag;
    }
    
    public static final Uri CONTENT_URI_1 =Uri.parse("content://" + AUTHORITY + "/MealTime");	
	private static final String[] PROJECTION_1 = new String[] {"name", "time"};
  
	@Override 
	public boolean onCreateOptionsMenu(Menu menu) {
		super.onCreateOptionsMenu(menu);
		menu.add(0, OK_ID, 0, "保存").setIcon(android.R.drawable.ic_menu_save);
		menu.add(0, CANCEL_ID, 0, "取消").setIcon(android.R.drawable.ic_menu_revert);
		return true; 
	}
	
	

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		Uri CONTENT_URI =Uri.parse("content://" + AUTHORITY + "/RecordTable");
    	switch (item.getItemId()) {
    	case OK_ID:
    		if(s_high_value.length()==0 || s_low_value.length()==0 || s_pulse_value.length()==0){
    			new AlertDialog.Builder(chatActivity.this)
                .setIcon(R.drawable.alert_dialog_icon)
                .setTitle("Please measure the value!")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        /* User clicked OK so do some stuff */
                    }
                })
                .create().show();
    		}
    		else{
    			
    			//System.out.println("s_value="+s_value);
    			//System.out.println("s_value.length="+s_value.length());
    			//s_note=(String)notestext.getText().toString();
    		
    			s_value=s_high_value;
    			ContentValues initialValues = new ContentValues();
    			initialValues.put("name", title[name_position]);
    			initialValues.put("value", s_value);
    			initialValues.put("date", s_date);
    			initialValues.put("time", s_time);
    			initialValues.put("tag", ""+tag_position);
    			initialValues.put("note", s_note);
    			initialValues.put("label", ""+(name_position+1));
    			
    			getContentResolver().insert(CONTENT_URI, initialValues);
    			
    			s_value=s_low_value;
    			initialValues.put("name", title[name_position+1]);
    			initialValues.put("value", s_value);
    			initialValues.put("date", s_date);
    			initialValues.put("time", s_time);
    			initialValues.put("tag", ""+tag_position);
    			initialValues.put("note", s_note);
    			initialValues.put("label", ""+(name_position+1));
    			
    			getContentResolver().insert(CONTENT_URI, initialValues);
    			
    			s_value=s_pulse_value;
    			initialValues.put("name", title[name_position+2]);
    			initialValues.put("value", s_value);
    			initialValues.put("date", s_date);
    			initialValues.put("time", s_time);
    			initialValues.put("tag", ""+tag_position);
    			initialValues.put("note", s_note);
    			initialValues.put("label", ""+(name_position+1));
    			
    			getContentResolver().insert(CONTENT_URI, initialValues);

    			Bundle bundle = new Bundle();
    			Intent mIntent = new Intent();
    			mIntent.putExtras(bundle);
    			setResult(RESULT_OK, mIntent);
    			finish(); 
    		}
    		break; 
    	case CANCEL_ID:
    		Bundle bundle = new Bundle();
			Intent mIntent = new Intent();
			mIntent.putExtras(bundle);
			setResult(RESULT_CANCELED, mIntent);
    		finish();
    		break;
    	}
		return super.onOptionsItemSelected(item);
	}

	private Handler LinkDetectedHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
        	//Toast.makeText(mContext, (String)msg.obj, Toast.LENGTH_SHORT).show();
        	if(msg.what==1)
        	{
        		list.add(new deviceListItem((String)msg.obj, true));
        	}
        	else
        	{
        		list.add(new deviceListItem((String)msg.obj, false));
        	}
			mAdapter.notifyDataSetChanged();
			mListView.setSelection(list.size() - 1);
        }
        
    };    
    
    @Override
    public synchronized void onPause() {
        super.onPause();
    }
    @Override
    public synchronized void onResume() {
        super.onResume();
        if(Bluetooth.isOpen)
        {
        	Toast.makeText(mContext, "连接已经打开，可以通信。如果要再建立连接，请先断开！", Toast.LENGTH_SHORT).show();
        	return;
        }
        if(Bluetooth.serviceOrCilent==ServerOrCilent.CILENT)
        {
			String address = Bluetooth.BlueToothAddress;
			if(!address.equals("null"))
			{
				device = mBluetoothAdapter.getRemoteDevice(address);	
				clientConnectThread = new clientThread();
				clientConnectThread.start();
				Bluetooth.isOpen = true;
			}
			else
			{
				Toast.makeText(mContext, "address is null !", Toast.LENGTH_SHORT).show();
			}
        }
        else if(Bluetooth.serviceOrCilent==ServerOrCilent.SERVICE)
        {        	      	
        	startServerThread = new ServerThread();
        	startServerThread.start();
        	Bluetooth.isOpen = true;
        }
    }
	//开启客户端
	private class clientThread extends Thread { 		
		public void run() {
			try {
				//创建一个Socket连接：只需要服务器在注册时的UUID号
				// socket = device.createRfcommSocketToServiceRecord(BluetoothProtocols.OBEX_OBJECT_PUSH_PROTOCOL_UUID);
				socket = device.createRfcommSocketToServiceRecord(UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
				//连接
				Message msg2 = new Message();
				msg2.obj = "请稍候，正在连接服务器:"+Bluetooth.BlueToothAddress;
				msg2.what = 0;
				LinkDetectedHandler.sendMessage(msg2);
				
				socket.connect();
				
				Message msg = new Message();
				msg.obj = "已经连接上服务端！可以发送信息。";
				msg.what = 0;
				LinkDetectedHandler.sendMessage(msg);
				//启动接受数据
				mreadThread = new readThread();
				mreadThread.start();
			} 
			catch (IOException e) 
			{
				Log.e("connect", "", e);
				Message msg = new Message();
				msg.obj = "连接服务端异常！断开连接重新试一试。";
				msg.what = 0;
				LinkDetectedHandler.sendMessage(msg);
			} 
		}
	};

	//开启服务器
	private class ServerThread extends Thread { 
		public void run() {
					
			try {
				/* 创建一个蓝牙服务器 
				 * 参数分别：服务器名称、UUID	 */	
				mserverSocket = mBluetoothAdapter.listenUsingRfcommWithServiceRecord(PROTOCOL_SCHEME_RFCOMM,
						UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));		
				
				Log.d("server", "wait cilent connect...");
				
				Message msg = new Message();
				msg.obj = "请稍候，正在等待客户端的连接...";
				msg.what = 0;
				LinkDetectedHandler.sendMessage(msg);
				
				/* 接受客户端的连接请求 */
				socket = mserverSocket.accept();
				Log.d("server", "accept success !");
				
				Message msg2 = new Message();
				String info = "客户端已经连接上！可以发送信息。";
				msg2.obj = info;
				msg.what = 0;
				LinkDetectedHandler.sendMessage(msg2);
				//启动接受数据
				mreadThread = new readThread();
				mreadThread.start();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	};
	/* 停止服务器 */
	private void shutdownServer() {
		new Thread() {
			public void run() {
				if(startServerThread != null)
				{
					startServerThread.interrupt();
					startServerThread = null;
				}
				if(mreadThread != null)
				{
					mreadThread.interrupt();
					mreadThread = null;
				}				
				try {					
					if(socket != null)
					{
						socket.close();
						socket = null;
					}
					if (mserverSocket != null)
					{
						mserverSocket.close();/* 关闭服务器 */
						mserverSocket = null;
					}
				} catch (IOException e) {
					Log.e("server", "mserverSocket.close()", e);
				}
			};
		}.start();
	}
	/* 停止客户端连接 */
	private void shutdownClient() {
		new Thread() {
			public void run() {
				if(clientConnectThread!=null)
				{
					clientConnectThread.interrupt();
					clientConnectThread= null;
				}
				if(mreadThread != null)
				{
					mreadThread.interrupt();
					mreadThread = null;
				}
				if (socket != null) {
					try {
						socket.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					socket = null;
				}
			};
		}.start();
	}
	//发送数据
	private void sendMessageHandle(String msg) 
	{		
		if (socket == null) 
		{
			Toast.makeText(mContext, "没有连接", Toast.LENGTH_SHORT).show();
			return;
		}
		try {
			byte[] btm=new byte[9];
			btm[0]=83;
			btm[1]=78;
			btm[2]=6;
			btm[3]=2;
			btm[4]=5;
			btm[5]=13;
			OutputStream os = socket.getOutputStream();
			for(int i=0;i<9;i++)
			{
				System.out.println(btm[i]);
				os.write(btm[i]);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}			
		list.add(new deviceListItem(msg, false));
		mAdapter.notifyDataSetChanged();
		mListView.setSelection(list.size() - 1);
	}
	//读取数据
    private class readThread extends Thread { 
        public void run() {
        	
            byte[] buffer = new byte[1024];
            byte[] tmpbuf = new byte[1024];
            int bytes;
            InputStream mmInStream = null;
            String tmp = null;
			try {
				mmInStream = socket.getInputStream();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
            while (true) {
                try {
                	
                    // Read from the InputStream
                    if( (bytes = mmInStream.read(buffer)) > 0 )
                    {                    	
				    	for(int i=0; i<bytes; i++)
				    	{
				    		/*tmp = "接收数据:"+buffer[i]+" "+i;
		    				String st = new String(tmp);
    						tmp = null;
    						Message msg = new Message();
    						msg.obj = st;
    						msg.what = 1;
    						LinkDetectedHandler.sendMessage(msg);*/
				    			if(buffer[i] == (byte)0xfe)
				    			{
				    				inforflag = 0;
				    				if(count != 0)
				    				{
					    				tmpbuf[n] = buffer[i];
					    				System.out.println("结束标志"+tmpbuf[n]);
					    				InfoConv informationConvert=new InfoConv(tmpbuf);
					    				tmp = informationConvert.Convert();
					    				s_high_value=informationConvert.getHighPress();
					    				s_low_value=informationConvert.getLowPress();
					    				s_pulse_value=informationConvert.getPulse();
					    				System.out.println(s_high_value+" "+s_low_value+" "+s_pulse_value);
					    				String s = new String(tmp);
			    						tmp = null;
			    						Message msg1 = new Message();
			    						msg1.obj = s;
			    						msg1.what = 1;
			    						LinkDetectedHandler.sendMessage(msg1);
				    				}
				    				count = 0;
				    			}
				    			if(buffer[i] == (byte)0xff)
				    			{
				    				inforflag = 1;
				    				n = 0;
				    				count = count+1;
				    			}
				    			if(inforflag == 1)
				    			{
				    				tmpbuf[n] = buffer[i];
				    				System.out.println("数据包:"+tmpbuf[n]+" "+n);
				    				
				    				n = n+1;
				    			}				    		
				    	}				    	 	
                    }
                    
                } catch (IOException e) {
                	try {
						mmInStream.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                    break;
                }
            }
            
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (Bluetooth.serviceOrCilent == ServerOrCilent.CILENT) 
		{
        	shutdownClient();
		}
		else if (Bluetooth.serviceOrCilent == ServerOrCilent.SERVICE) 
		{
			shutdownServer();
		}
        Bluetooth.isOpen = false;
		Bluetooth.serviceOrCilent = ServerOrCilent.NONE;
    }
	public class SiriListItem {
		String message;
		boolean isSiri;

		public SiriListItem(String msg, boolean siri) {
			message = msg;
			isSiri = siri;
		}
	}
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
	}
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
	}	
	public class deviceListItem {
		String message;
		boolean isSiri;

		public deviceListItem(String msg, boolean siri) {
			message = msg;
			isSiri = siri;
		}
	}
}