package mconnect.mdiabetes.education;

import mconnect.mdiabetes.framework.R;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;

public class glucose extends ListActivity{
	private String[]menu1 = {"血糖监测目的","血糖正常值","血糖监测时间","注意事项"};
		

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,menu1);
         setListAdapter(adapter);
         //setOnItemClickListener(this);
         setTitle("血糖自我监测");
    }  ;
    String a=("    血糖监测是糖尿病管理中的重要组成部分，可被用来反映饮食控制、运动治疗和药物治疗的效果并指导对治疗方案的调整。\n    监测频率取决于治疗方法、治疗的目标、病情和个人的经济条件。监测的基本形式是患者的自我血糖监测。");
    String b=("    一般血糖正常值如下：\n空腹：3.9-6.1mmol/L；\n餐后2小时：≤7.8mmol/L。\n    对于老年人，血糖的正常值为：\n空腹：≤7.8mmol/L；\n餐后：≤11.1mmol/L");
    String c=("    血糖监测的时间为：\n（1）餐前半小时：利于检出低血糖；\n（2）餐后2小时：利于检出高血糖；\n（3）夜间血糖监测：利于发现夜间低血糖或空腹高血糖。 ");
    String d=("    注意事项：\n（1）注射胰岛素或使用促胰岛素分泌剂的患者可每日监测血糖1-4次；\n（2）1型糖尿病患者应每日至少监测血糖3-4次；\n（3）伴发其他疾病期间或血糖＞16.7mmol/L（300mg/dl）时，应测定血、尿酮体；\n（4）血糖控制良好或稳定的病人应每周监测一天或两天，具有良好并稳定血糖控制者监测的次数可更少；\n（5）血糖控制差/不稳定的病人或患其他急性病者应每天监测直到血糖得到良好控制。");
    
    int p1;
 
    
    public void onListItemClick(ListView l, View v, int position, long id){
    	p1=position;
        if (p1 == 0) {
        	new AlertDialog.Builder(glucose.this)
            .setIcon(R.drawable.ic_popup_reminder)
            .setTitle("血糖监测的目的")
            .setMessage(a)
            .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
            	public void onClick(DialogInterface dialog, int whichButton) {
                    
                }
            })
            
            .create().show();
        }
        else if(p1 == 1){ 
        	new AlertDialog.Builder(glucose.this)
            .setIcon(R.drawable.ic_popup_reminder)
            .setTitle("血糖正常值")
            .setMessage(b)
            .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
            	public void onClick(DialogInterface dialog, int whichButton) {
                    
                }
            })
            
            .create().show();
        }
        else if(p1 == 2){ 
        	new AlertDialog.Builder(glucose.this)
            .setIcon(R.drawable.ic_popup_reminder)
            .setTitle("血糖监测时间")
            .setMessage(c)
            .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
            	public void onClick(DialogInterface dialog, int whichButton) {
            	
            	}  
            })
          
            .create().show();
           }	
        else if(p1 == 3){ 
        	new AlertDialog.Builder(glucose.this)
            .setIcon(R.drawable.ic_popup_reminder)
            .setTitle("血糖监测注意事项")
            .setMessage(d)
            .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
            	public void onClick(DialogInterface dialog, int whichButton) {
                   
                }
            })
            
            .create().show();
               }	
       }
    }

