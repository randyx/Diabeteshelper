package mconnect.mdiabetes.education;

import android.app.AlertDialog;
import android.app.ExpandableListActivity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.SimpleExpandableListAdapter;
import android.widget.ExpandableListView.OnChildClickListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mconnect.mdiabetes.framework.R;
/**
 * Demonstrates expandable lists backed by a Simple Map-based adapter
 */
public class medicine1 extends ExpandableListActivity implements OnChildClickListener{
	private String[]menu1 = {"磺酰脲类(sulfonylurea)","双胍类(biguanide)","α-葡糖苷酶抑制剂(α-glucosidase inhibitor)","非磺脲类胰岛素促进剂(sulfonylurea insulin promotion)","胰岛素增敏剂(insulin sensitizer)"};
	private String[][]menu2={
			                   {"常用药品及降糖原理","优缺点分析"},
			                   {"常用药品及降糖原理","优缺点分析"},
			                   {"常用药品及降糖原理","优缺点分析"},
			                   {"常用药品及降糖原理","优缺点分析"},
			                   {"常用药品及降糖原理","优缺点分析"},
			                  
	                          };
    private ExpandableListAdapter mAdapter;
   
    private static final String NAME = "NAME";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        List<Map<String,String>> groupData = new ArrayList<Map<String, String>>();
        List<List<Map<String, String>>> childData = new ArrayList<List<Map<String, String>>>();
        for (int i = 0; i < 5; i++) {
            Map<String, String> curGroupMap = new HashMap<String, String>();
            groupData.add(curGroupMap);
            curGroupMap.put(NAME,  menu1[i]);
            
            
            List<Map<String, String>> children = new ArrayList<Map<String, String>>();
            for (int j = 0; j < menu2[i].length; j++) {
                Map<String, String> curChildMap = new HashMap<String, String>();
                children.add(curChildMap);
                curChildMap.put(NAME, menu2[i][j]);
                
            }
            childData.add(children);
        }
        mAdapter = new SimpleExpandableListAdapter(
                this,
                groupData,
                android.R.layout.simple_expandable_list_item_1,
                new String[] { NAME },
                new int[] { android.R.id.text1 },
                childData,
                android.R.layout.simple_expandable_list_item_1,
                new String[] { NAME },
                new int[] { android.R.id.text1}
                );
        
        //mAdapter.isChildSelectable(groupPosition, childPosition);
        setListAdapter(mAdapter);
        getExpandableListView().setOnChildClickListener(this);
        /*getExpandableListView().setOnChildClickListener(new OnChildClickListener(){

			@Override
			public boolean onChildClick(ExpandableListView parent, View v,
					int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
				return false;
			}
        	
        });*/
    } 
    String a =("   磺酰脲类是用得较早的一类降糖药，第一代有Ｄ８６０（甲苯磺丁脲）、氯磺丙脲等，因其服用剂量大，降糖作用弱，副作用大，临床上基本被淘汰。第二代有格列苯脲（优降糖）、格列吡嗪（美吡达）、格列齐特（达美康）、格列美脲（亚莫利）等，临床应用较为广泛和普通。\n   降糖原理：主要作用是通过刺激胰岛β细胞分泌胰岛素而降糖。应用此类药的前提条件是患者胰岛细胞有一定的功能，即是指２型糖尿病轻、中度患者。");
    String b= ("   优点和缺点：\n   优点是此类药降糖作用强，降糖时间快，部分药价格低廉，易被患者接受等。\n   缺点是此类药降糖作用依赖血糖浓度，容易低血糖，易导致腹型肥胖；增加胰岛β细胞负荷，导致胰岛功能衰竭；长效药导致严重的低血糖难以纠正等。\n   应用此类药物应注意其肾毒性，每个药各有特点，如糖适平只有５％肾脏代谢，肾毒性小；格列吡嗪作用时间短，低血糖发生几率小，适合老年人；格列齐特有改善微循环作用等，临床应用应根据患者的胰岛功能状况和个体特点选择，一般以小剂量开始，逐渐加量。");
    String c= ("   双胍类起初应用不是降糖而是减肥药，近年逐渐应用到糖尿病领域。第一代药物是苯乙双胍（降糖灵、降糖片），容易导致乳酸性酸中毒，死亡率达８０％以上，已淘汰。第二代药物是二甲双胍（格华止、美迪康、迪化唐锭、甲福明、君力达）。\n   降糖原理：包括增加外周组织如肌肉、脂肪等对糖的作用，抑制糖异生和糖原分解；减轻胰岛素抵抗，增加胰岛素敏感性等作用。适用于肥胖２型糖尿病患者，对正常血糖无影响。");
    String d= ("   优点和缺点：\n   优点是可以促进外周组织对葡萄糖的摄取，增加胰岛素的敏感性。\n   缺点是对胃肠道刺激大，易引起胃肠道和消化道不良反应；易导致消瘦，导致营养不良，使身体抵抗力下降。\n   因此，禁用于胃肠道疾病患者、身体消瘦者、有严重并发神经病变和糖尿病足及孕妇和哺乳期妇女。临床应用应注意，此类药应用剂量过大可引起乳酸性酸中毒，肾功能不全者应不用或禁用。");
    String e= ("   α糖苷酶抑制剂主要包括阿卡波糖（拜糖平）、伏格列波糖（倍欣）。属于抗高血糖剂。\n   降糖原理：碳水化合物的吸收要靠小肠黏膜刷状缘的α?糖苷酶，才能分解为单糖而吸收入血。该类药即是抑制这一类作用，可延缓碳水化合物的吸收而起到降糖作用，适用于餐后血糖高者。");
    String f= ("   优点和缺点：\n   优点是安全可靠，可降低餐后血糖等。\n   缺点是可引起胃肠道反应如腹胀（气）等；引起四肢消瘦、营养不良等。\n   临床应用应注意必须与进餐同时服用，不进食碳水化合物则不起作用。单用一般不会引起低血糖，联合其他药应用一旦出现低血糖应直接口服葡萄糖或静推葡萄糖，进食一般食物无效。");
    String g= ("   非磺脲类胰岛素促进剂主要包括瑞格列奈（诺和龙、孚来迪）和那格列奈（唐力）。该药不增加胰岛细胞负担，属快速胰岛素分泌剂。\n   降糖原理与磺脲类药相似，但化学结构中没有磺脲类部分，促胰岛素分泌快而短暂，模拟胰岛素分泌。主要应用于控制餐后高血糖。");
    String h= ("   优点和缺点：\n   优点是不增加胰岛细胞负担、无低血糖反应、不进食不服药等。\n   缺点是价格高，患者难接受。");
    String i= ("   胰岛素增敏又称噻唑烷二酮类药，包括罗格列酮（文迪雅、太罗、维戈洛）、吡格列酮（艾汀、瑞彤、安可妥）。\n   降糖原理为增加胰岛素的敏感性，减轻胰岛素抵抗。可单独应用或联合其他类药应用。");
    String j= ("   优点和缺点：\n   优点是该药直接作用于糖尿病的病理因素，提高胰岛素敏感性。\n   缺点是价格高，应用前提是胰岛功能尚可而胰岛素受体结合率低下者、肝毒性大者。 ");
    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id){
    	if(groupPosition==0&childPosition==0){
    			new AlertDialog.Builder(medicine1.this)
                .setIcon(R.drawable.alert_dialog_icon)
                .setTitle("磺酰脲类常用药")
                .setMessage(a)
                .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                	public void onClick(DialogInterface dialog, int whichButton) {
                        
                    }
                })
                
                .create().show(); 
    		}
    	else if(groupPosition==0&childPosition==1){
    			new AlertDialog.Builder(medicine1.this)
                .setIcon(R.drawable.alert_dialog_icon)
                .setTitle("磺酰脲类药品优缺点")
                .setMessage(b)
                .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                	public void onClick(DialogInterface dialog, int whichButton) {
                        
                    }
                })
                
                .create().show(); 
    		}
    	else if(groupPosition==0&childPosition==1){
			new AlertDialog.Builder(medicine1.this)
            .setIcon(R.drawable.alert_dialog_icon)
            .setTitle("双胍类常用药")
            .setMessage(c)
            .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
            	public void onClick(DialogInterface dialog, int whichButton) {
                 
                }
            })
            
            .create().show(); 
		}
    	else if(groupPosition==0&childPosition==1){
			new AlertDialog.Builder(medicine1.this)
            .setIcon(R.drawable.alert_dialog_icon)
            .setTitle("双胍类药品优缺点")
            .setMessage(d)
            .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
            	public void onClick(DialogInterface dialog, int whichButton) {
                    
                }
            })
            
            .create().show(); 
		}
    	else if(groupPosition==0&childPosition==1){
			new AlertDialog.Builder(medicine1.this)
            .setIcon(R.drawable.alert_dialog_icon)
            .setTitle("α-葡糖苷酶抑制剂常用药")
            .setMessage(e)
            .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
            	public void onClick(DialogInterface dialog, int whichButton) {
                  
                }
            })
            
            .create().show(); 
		}
    	else if(groupPosition==0&childPosition==1){
			new AlertDialog.Builder(medicine1.this)
            .setIcon(R.drawable.alert_dialog_icon)
            .setTitle("α-葡糖苷酶抑制剂药品优缺点")
            .setMessage(f)
            .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
            	public void onClick(DialogInterface dialog, int whichButton) {
                  
                }
            })
            
            .create().show(); 
		}
    	else if(groupPosition==0&childPosition==1){
			new AlertDialog.Builder(medicine1.this)
            .setIcon(R.drawable.alert_dialog_icon)
            .setTitle("非磺脲类胰岛素促进剂常用药")
            .setMessage(g)
            .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
            	public void onClick(DialogInterface dialog, int whichButton) {
                   
                }
            })
            
            .create().show(); 
		}
    	else if(groupPosition==0&childPosition==1){
			new AlertDialog.Builder(medicine1.this)
            .setIcon(R.drawable.alert_dialog_icon)
            .setTitle("非磺脲类胰岛素促进剂常药品优缺点")
            .setMessage(h)
            .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
            	public void onClick(DialogInterface dialog, int whichButton) {
                    
                }
            })
            
            .create().show(); 
		}
    	else if(groupPosition==0&childPosition==1){
			new AlertDialog.Builder(medicine1.this)
            .setIcon(R.drawable.alert_dialog_icon)
            .setTitle("胰岛素增敏剂常用药")
            .setMessage(i)
            .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
            	public void onClick(DialogInterface dialog, int whichButton) {
                   
                }
            })
            
            .create().show(); 
		}
    	else if(groupPosition==0&childPosition==1){
			new AlertDialog.Builder(medicine1.this)
            .setIcon(R.drawable.alert_dialog_icon)
            .setTitle("胰岛素增敏剂药品优缺点")
            .setMessage(j)
            .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
            	public void onClick(DialogInterface dialog, int whichButton) {
                    
                }
            })
            
            .create().show(); 
		}
    	return true;
    }
   
}
    
