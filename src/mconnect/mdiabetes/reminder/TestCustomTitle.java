package mconnect.mdiabetes.reminder;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mconnect.mdiabetes.framework.Bluetooth;
import mconnect.mdiabetes.framework.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ViewAnimator;
import android.widget.AdapterView.OnItemClickListener;


public class TestCustomTitle extends Activity implements OnClickListener{
	
	public static final String AUTHORITY = "com.chen.provider.diabeteshelper";
	public static final Uri CONTENT_URI =Uri.parse("content://" + AUTHORITY + "/reminder");
	private static final String[] PROJECTION = new String[] {"name","dosage","time","music"};
	
	private EditText r_name;
	private Button howMany;
	private Button howOften;
	private Button soundFile;
	
	//private int many_position;
	
	private int hour;
	private int minute;
	
	private String shour;
	private String sminute;
	
	private ArrayList<Map<String, Object>> coll;
	private Map<String, Object> item;
	private ListView lv;
	private int deletetimer;
	
	private String timer[];
	private int dosage=0;
	private String reminder_name;
	private int music=0;
	
	//String array[]={"On", "Off", };
	String array[]={"开", "关", };
	String array1[]={"1/4", "1/2", "1", "1 1/2", "2", "3", "4","5", "6", "7", "8", "9", "10"};

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
        setContentView(R.layout.i_main);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.i_title);

        final TextView title = (TextView) findViewById(R.id.title);
        final Button leftBtn = (Button) findViewById(R.id.title_left_btn);
        final Button rightBtn = (Button) findViewById(R.id.title_right_btn);
        title.setText("1");
        leftBtn.setText("离开");
        rightBtn.setText("继续");
        
        
        
        howMany=(Button)this.findViewById(R.id.how_many);
		howMany.setOnClickListener(this);
		howOften=(Button)this.findViewById(R.id.how_often);
		howOften.setOnClickListener(this);
		
		soundFile=(Button)this.findViewById(R.id.sound_file);
		soundFile.setOnClickListener(this);
		r_name=(EditText)this.findViewById(R.id.reminder_name);
		
		
        
        final ViewAnimator animator = (ViewAnimator) findViewById(R.id.animator);
        final Animation slideInLeft = AnimationUtils.loadAnimation(this, R.anim.i_slide_in_left);
        final Animation slideInRight = AnimationUtils.loadAnimation(this, R.anim.i_slide_in_right);
        final Animation slideOutLeft = AnimationUtils.loadAnimation(this, R.anim.i_slide_out_left);
        final Animation slideOutRight = AnimationUtils.loadAnimation(this, R.anim.i_slide_out_right);
        
        leftBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				int number=animator.getDisplayedChild();
				if (number== 0) {
					finish();
				} else {
			        title.setText(""+(number));
			        if(number==1){
			        	leftBtn.setText("离开");
			        }
			        else
			        {
			        	leftBtn.setText("返回");
			        }
			        rightBtn.setText("继续");
					animator.setInAnimation(slideInRight);
					animator.setOutAnimation(slideOutRight);
					animator.showPrevious();
				}
			}
        });
        rightBtn.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
				int number=animator.getDisplayedChild();
				reminder_name=r_name.getText().toString();
				if (number== 3) {
					//System.out.println("timer.length="+timer.length);
					if((reminder_name.length()==0)||timer==null||timer.length==0){
                		new AlertDialog.Builder(TestCustomTitle.this)
                        .setIcon(R.drawable.alert_dialog_icon)
                        .setTitle("请填写名称或添加时钟!")
                        .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {

                                /* User clicked OK so do some stuff */
                            }
                        })
                        .create().show();
                	}
                	else{
                		
			        
					String msg = "名称  :  "+reminder_name+"\n剂量  :  "+array1[dosage]+"\n时间  :  ";
					for(int k=0;k<timer.length;k++){
						if(k==(timer.length-1)){
							msg=msg+timer[k]+"\n";
						}
						else{
							msg=msg+timer[k]+"\n              ";
						}
					}
					msg=msg+"铃声  :  "+array[music];
					new AlertDialog.Builder(TestCustomTitle.this)
	                .setTitle("设置提醒:")
	                .setMessage(msg)
	                .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int whichButton) {
	                    	
	                    		ContentValues initialValues;
	                    	for(int m=0;m<timer.length;m++){
	                    			initialValues = new ContentValues();
	                    			initialValues.put("name", reminder_name);
	                    		initialValues.put("dosage", dosage);
	                    		initialValues.put("time", timer[m]);
	                    		initialValues.put("music", music);
	                    		        	
	                		
	                    		getContentResolver().insert(CONTENT_URI, initialValues);
	                    	}
	                    	Bundle bundle = new Bundle();
	                		Intent mIntent = new Intent();
	                		mIntent.putExtras(bundle);
	                		setResult(RESULT_OK, mIntent);
	                    	finish();
	                    	}
	                        /* User clicked OK so do some stuff */
	                    
	                })
	                .setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int whichButton) {

	                        /* User clicked Cancel so do some stuff */
	                    }
	                })
	                .create().show();
                	}
					
				} else {
					title.setText(""+(number+2));
					leftBtn.setText("返回");
					if(number==2){
						rightBtn.setText("结束");
					}
					else{
						rightBtn.setText("继续");
					}
					animator.setInAnimation(slideInLeft);
					animator.setOutAnimation(slideOutLeft);
					animator.showNext();
				}
			}
        });
    }
	
	@Override
	public void onClick(View v) {

		if (v == howMany){ 
			
			new AlertDialog.Builder(TestCustomTitle.this)
            .setTitle("请选择剂量")
            .setSingleChoiceItems(array1, 0, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                	dosage=whichButton;
                    /* User clicked on a radio button do some stuff */
                }
            })
            .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                	howMany.setText(array1[dosage]);
                	Toast mToast=null;
                	if (mToast != null) {
                        mToast.cancel();
                    }
                    mToast = Toast.makeText(TestCustomTitle.this, "You choose Group "+dosage,
                            Toast.LENGTH_SHORT);
                    mToast.show();
                    /* User clicked Yes so do some stuff */
                }
            })
            .setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                    /* User clicked No so do some stuff */
                }
            })
           .create().show();
		}
		else if (v == howOften){ 
			LayoutInflater factory = LayoutInflater.from(this);
            final View textEntryView = factory.inflate(R.layout.howoften, null);
            Button add=(Button)textEntryView.findViewById(R.id.addtimer);
            lv=(ListView)textEntryView.findViewById(R.id.timerlist);
            coll= new ArrayList<Map<String, Object>>();
            if(timer!=null){
            	for(int j=0;j<timer.length;j++){
            		item = new HashMap<String, Object>();
            		item.put("name", timer[j]);
            		item.put("image", R.drawable.timer);
            		coll.add(item);
            	}
            }
            lv.setAdapter(new SimpleAdapter(textEntryView.getContext(), coll,R.layout.howoftentimer, new String[] { "name","image" },new int[] {R.id.set_timer_text,R.id.set_timer_image,}));
	        lv.setOnItemClickListener(new OnItemClickListener(){
	        	@Override
				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {
	        		deletetimer=arg2;
	        		new AlertDialog.Builder(TestCustomTitle.this)
	                .setIcon(R.drawable.alert_dialog_icon)
	                .setTitle("您要删除这一项吗?")
	                .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int whichButton) {
	                    	coll.remove(deletetimer);
	    	        		lv.setAdapter(new SimpleAdapter(textEntryView.getContext(), coll,R.layout.howoftentimer, new String[] { "name","image" },new int[] {R.id.set_timer_text,R.id.set_timer_image,}));
	    	    	        
	                        /* User clicked OK so do some stuff */
	                    }
	                })
	                .setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
	                    public void onClick(DialogInterface dialog, int whichButton) {

	                        /* User clicked Cancel so do some stuff */
	                    }
	                })
	                .create().show();
	        		
				}
	        });
    		add.setOnClickListener(new OnClickListener(){
    			@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
    				showDialog(0);
                	
                	
			        
			        
			        
				}
    			
    		});
            new AlertDialog.Builder(TestCustomTitle.this)
                .setIcon(R.drawable.stopwatch)
                .setTitle("设置提醒时间")
                .setView(textEntryView)
                .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) 
                    {
                    	
                    	
                    	
                    	timer=new String[coll.size()];
                    	for(int i=0;i<coll.size();i++){
                    		timer[i]=coll.get(i).get("name").toString();
                    		System.out.println("timer"+timer[i]);
                    	}
                		
                		
                		
    
                       
                    }
                })
                .setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {

                        
                    }
                })
                .create().show();
			
			
		}
		else if(v==soundFile){
			
			new AlertDialog.Builder(TestCustomTitle.this)
            .setIcon(android.R.drawable.alert_light_frame)
            .setTitle("请设置铃声")
            .setSingleChoiceItems(array, 0, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                	music=whichButton;
                	
                    /* User clicked on a radio button do some stuff */
                }
            })
            .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                	soundFile.setText(array[music]);
                	Toast mToast=null;
                	if (mToast != null) {
                        mToast.cancel();
                    }
                    mToast = Toast.makeText(TestCustomTitle.this, "The music is "+array[music],
                            Toast.LENGTH_SHORT);
                    mToast.show();
                    /* User clicked Yes so do some stuff */
                }
            })
            .setNegativeButton(R.string.alert_dialog_cancel, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {

                    /* User clicked No so do some stuff */
                }
            })
           .create().show();
		}
	}
	
	@Override
    protected Dialog onCreateDialog(int id) {
            
                return new TimePickerDialog(this,
                        mTimeSetListener, hour, minute, false);                 
    }

    @Override
    protected void onPrepareDialog(int id, Dialog dialog) {
        
                ((TimePickerDialog) dialog).updateTime(hour, minute);
                
          
    }    
    
    private TimePickerDialog.OnTimeSetListener mTimeSetListener =
        new TimePickerDialog.OnTimeSetListener() {

            public void onTimeSet(TimePicker view, int hourOfDay, int minut) {
                hour = hourOfDay;
                minute = minut;
                
                if(hour<10){
                	shour="0"+hour;
                }
                else{
                	shour=""+hour;
                }
                
                if(minute<10){
                	sminute="0"+minute;
                }
                else{
                	sminute=""+minute;
                }
                
                item = new HashMap<String, Object>();
		        item.put("name", shour+":"+sminute);
		        item.put("image", R.drawable.timer);
		        coll.add(item);
		        lv.setAdapter(new SimpleAdapter(getBaseContext(), coll,R.layout.howoftentimer, new String[] { "name","image" },new int[] {R.id.set_timer_text,R.id.set_timer_image,}));
		        
                
            }
        };
}
