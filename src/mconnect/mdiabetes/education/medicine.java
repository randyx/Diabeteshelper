package mconnect.mdiabetes.education;


import mconnect.mdiabetes.framework.R;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.View;
public class medicine extends ListActivity{
	private String[]menu1 = {"用药对象","药品分类","用药时间及次数"};
	

	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("口服降糖药详知");
         ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,menu1);
         setListAdapter(adapter);
         //setOnItemClickListener(this);
         setTitle("口服降糖药品");
    } ;
    private String a=("   2型糖尿病是胰岛素工作效率低或是差导致,胰岛素抵抗是大多数2型糖尿病的病因，合理饮食计划和运动控制体重是减轻胰岛素抵抗的基础。\n   2型糖尿病起病时，其胰岛B细胞功能仅为正常人的50%左右，以后每5年丧失50%的速度衰竭，因此起病时常用口服降糖药，或联合胰岛素治疗。后期降糖药继发性失效，改用胰岛素代替。");
    String[]b={"口服降糖药总则","餐前半小时服药须知","餐前一分钟服药须知","餐中或餐后服药须知"};
    String c=("   很多糖尿病患者在吃口服降糖药时都小心翼翼，到底怎么吃，何时吃，用多少量都是一个很大的问题，稍不慎防就会造成很严重的后果。\n   目前为止，口服降糖药基本可分为五大类。当患者选择用药时，首先应根据自身肥胖与否进行划分，肥胖和超重者用二甲双胍，否则选用其他类降糖药。药物从小剂量开始服用，增加到中等剂量后，如果降糖效果不好，加用另一类降糖药。注意同类药物不能叠加，且磺脲类药物不能和格列奈类同服，其余可相互配伍。若使用二三种口服药效果仍不理想，应使用胰岛素治疗。\n   需要注意的是，为有效发挥降糖药的作用，糖尿病患者应在医生指导下择时服用各种降糖药物");
    String d=("   餐前半小时 主要为磺酰脲类降糖药，如格列苯脲（优降糖）、格列奇特（达美康）、消渴丸、格列吡嗪控释片（瑞易宁）等。\n   这些药有促进胰岛素分泌的作用，起效时间为服药后30分钟。因此，一般应在饭前30分钟左右服用。有些药还特别讲究，最好安排在每天同一时间服药，如瑞易宁。\n   另外，服用这些药物应从1片开始，以后逐渐增加剂量，每次增加半片到1片，3~7天调整一次，直至血糖恢复正常。在试用期间还需要警惕低血糖的发生。");
    String e=("   餐前1分钟 服用胰岛素分泌促进剂，如诺合龙，这是最新的快速胰岛素分泌促进剂，作用迅速而短暂，被称为餐时血糖调节剂。\n   又如α—糖苷酶抑制剂类药物，如拜糖平、倍欣等，主要用于降低餐后高血糖。\n   在服用该类药时，必须与每餐的第一口饭同时嚼碎服下。否则，起不到降低餐后高血糖的作用。\n   还有一类胰岛素增敏剂，如罗格列酮、吡格列酮等，该类药主要通过增强胰岛素作用而降低血糖，作用时间较长，一次服药，降糖作用可以维持24小时。因此，每日仅需服药一次，以每日早餐前1分钟服药效果最好。");
    String f=("   餐中或餐后 主要为双胍降糖药，如二甲双胍、苯乙双胍（降糖灵）。\n   二甲双胍是惟一在餐中或餐后服用的降糖药，这是因为二甲双胍能够渗入胃黏膜，引起胃部不适，如果在每餐饭的中间或在餐后立刻服药，可避免药物对胃的刺激，减少副作用。二甲双胍治疗糖尿病在降糖的同时不增加体重，有轻度减肥作用，也不引起低血糖，因此使用较广泛，对体型肥胖的糖尿病患者尤其适宜。苯乙双胍（降糖灵）易引起威胁生命的乳酸中毒，故近年来逐渐被二甲双胍所取代");
public void onListItemClick(ListView l, View v, int position, long id){
    if(position==0){
    	new AlertDialog.Builder(medicine.this)
        .setIcon(R.drawable.alert_dialog_icon)
        .setTitle("用药对象")
        .setMessage(a)
        .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
        	public void onClick(DialogInterface dialog, int whichButton) {
            
            }
        })
        
        .create().show();
    }
    else if(position==1){
    	Intent in = new Intent(medicine.this,medicine1.class);
		 startActivity(in);}
    else if(position==2){
    	new AlertDialog.Builder(medicine.this)
        .setTitle("服药时间及次数")
        .setItems(b, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
            	if (which==0){
            		new AlertDialog.Builder(medicine.this)
                    .setIcon(R.drawable.alert_dialog_icon)
                    .setTitle("用药总则")
                    .setMessage(c)
                    .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                    	public void onClick(DialogInterface dialog, int whichButton) {
                          
                        }
                    })
                    
                    .create().show(); 
            	   }
            	else if (which==1){
            		new AlertDialog.Builder(medicine.this)
                    .setIcon(R.drawable.alert_dialog_icon)
                    .setTitle("餐前半小时服药须知")
                    .setMessage(d)
                    .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                    	public void onClick(DialogInterface dialog, int whichButton) {
                            
                        }
                    })
                    
                    .create().show(); 
            	   }
            	else if (which==2){
            		new AlertDialog.Builder(medicine.this)
                    .setIcon(R.drawable.alert_dialog_icon)
                    .setTitle("餐前一分钟服药须知")
                    .setMessage(e)
                    .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                    	public void onClick(DialogInterface dialog, int whichButton) {
                            
                        }
                    })
                    
                    .create().show(); 
            	   }
            	else if (which==3){
            		new AlertDialog.Builder(medicine.this)
                    .setIcon(R.drawable.alert_dialog_icon)
                    .setTitle("餐中或餐后服药须知")
                    .setMessage(f)
                    .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                    	public void onClick(DialogInterface dialog, int whichButton) {
                            
                        }
                    })
                    
                    .create().show(); 
            	}
               
            }
        })
        .create().show();
    }
 }
}

