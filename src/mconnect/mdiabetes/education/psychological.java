package mconnect.mdiabetes.education;

import mconnect.mdiabetes.framework.R;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
public class psychological extends ListActivity{
	private String[]menu1 = {"糖尿病心理因素","糖尿病人心理特征","糖尿病心理治疗方法"};
	

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,menu1);
         setListAdapter(adapter);
         //setOnItemClickListener(this);
         setTitle("糖尿病心理问题");
    } ;
    private String a=("   研究发现，糖尿病在发病上不仅与生理病理学上的因素有关，还与社会环境、心理因素有关，如工作学习长期过度紧张、人际关系不协调、心理上的不良刺激和生活中的突发不幸事件等。都加重糖尿病。\n   另外，单独使用口服降糖药或注射胰岛紊疗效往往不够理想，如果同时配合心理疗法，采取形神合一、身心同治的方法进行治疗，常能收到事半功倍或单纯药物达不到的效果。因此说糖尿病是一种身心疾病，心理、社会因素在糖尿病的发生、发展中具有重要的作用。");
    private String []b={"怀疑和否认心理","失望和无助感","焦虑恐惧心理","自责自罪心理","悲观厌世和自杀心理"}; 
    private String []c={"说理开导法","转移注意法","情志相胜法","静志安神法","怡悦开怀法"};
  public void onListItemClick(ListView l, View v, int position, long id){
    if(position==0){
    	new AlertDialog.Builder(psychological.this)
        .setIcon(R.drawable.alert_dialog_icon)
        .setTitle("糖尿病心理")
        .setMessage(a)
        .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
        	public void onClick(DialogInterface dialog, int whichButton) {
             
            }
        })
        
        .create().show();
    }
    else if(position==1){
    	
    	new AlertDialog.Builder(psychological.this)
        .setTitle("糖尿病心理特征")
        .setItems(b, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                      
            }
        })
        .create().show();
    }
    else if(position==2){
    	new AlertDialog.Builder(psychological.this)
        .setTitle("糖尿病心理治疗方法")
        .setItems(c, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                      
            }
        })
        .create().show();
    }
  }
}
