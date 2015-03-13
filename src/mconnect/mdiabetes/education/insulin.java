package mconnect.mdiabetes.education;

import android.app.AlertDialog;
import android.app.ExpandableListActivity;
import android.content.DialogInterface;
import android.content.Intent;
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
public class insulin extends ExpandableListActivity implements OnChildClickListener{
	private String[]menu1 = {"胰岛素分类","胰岛素使用","注意事项"};
	private String[][]menu2={
			                   {"按来源分类","按药效时间分类","按给药装置分类","胰岛素标识识别"},
			                   {"药理作用","初始计量计算","注射方法"},
			                   {"存放方法","注射须知"}
	                          };
    private ExpandableListAdapter mAdapter;
   
    private static final String NAME = "NAME";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         setTitle("胰岛素详知");
        List<Map<String,String>> groupData = new ArrayList<Map<String, String>>();
        List<List<Map<String, String>>> childData = new ArrayList<List<Map<String, String>>>();
        for (int i = 0; i < 3; i++) {
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
    String a =("    胰岛素制剂可分为:\n   人胰岛素、猪胰岛素、牛胰岛素。\n   动物胰岛素与人胰岛素的区别在于结构上氨基酸序列的不同，因而动物胰岛素存在一定的免疫原性，可能在人体产生抗体而致过敏反应。另外，动物胰岛素的效价低，由动物胰岛素换用人胰岛素时，剂量应减少15％～20％，否则会增加低血糖风险。");
    String []c= {"注射式","非注射式"};
    String c1= ("    注射式胰岛素包括：\n1.普通注射液（西林瓶）\n2.胰岛素笔芯（卡式瓶）\n3.特充装置（钢笔形）\n4.胰岛素持续皮下注射泵（或腹腔植入型泵）。");
    String c2= ("    非注射式胰岛素目前已上市或正在研发的品种有：\n1.吸入剂（仅Exubera上市半年后已退出市场）\n2.口腔喷雾剂\n3.口服制剂\n4.鼻腔喷雾剂\n5.透皮释药制剂。");
    String d=  ("    市售胰岛素有多种剂型和规格，使用前务必要分清。\n    常见的标识有：\n一、类型标识：\nRI（简写R）代表短效胰岛素；\nNPH（简写N）代表中效胰岛素；\nPZI代表长效胰岛素；\n30R（或70/30）表示由30％短效胰岛素和70％中效胰岛素的预混胰岛素；\n\50R（或50/50）表示由50％短效胰岛素和50％中效胰岛素的预混胰岛素。\n二、浓度标志：\nU-40表示胰岛素的浓度是40U/ml；\nU-100表示胰岛素的浓度是100U/ml。");
    String e=  ("    胰岛素对物质代谢的调节起着重要作用。\n    对葡萄糖之进入组织细胞、氧化以及由糖转变成糖元和脂肪有促进作用，其结果可使血糖含量降低；\n    此外，它还能使氨基酸进入细胞的速度加快，促进细胞内的蛋白质合成。 ");
    String []f= {"初始计量总要求","根据尿糖多少选择","根据血糖值选择","根据磺脲类降糖药用量选择","根据经验选择"};
    String f1= ("    在开始打胰岛素以前，第一件事就是得决定一上来到底打多少胰岛素。一般来说，开始打胰岛素时多每天3～4次，以早餐前剂量最大，晚餐前剂量次之，午餐前剂量较小的方法注射，如果需要睡前加打一针的话，其剂量最小。\n    选择剂量注射胰岛素数天后，再根据血糖控制的水平进一步加以调整。  ");
    String f2= ("    按尿糖的“＋”号来决定胰岛素的使用剂量，一般尿糖一个“＋”，一次注射3～4单位胰岛素。 ");
    String f3= ("    全天胰岛素总量＝0.003×〔血糖（毫克/分升）－100〕×体重（千克）");
    String f4= ("    按每片磺脲类降糖药合5个单位胰岛素来计算：如早饭前吃两片优降糖，可以改为10个单位胰岛素；");
    String f5= ("    可根据血糖的高低决定在三餐前打8、4、6或者10、6、8个单位的胰岛素作为胰岛素的初始剂量，这是一个比较简单可又实用的方法。");
    String g=  ("一、正确选择注射部位和工具 \n    每次注射部位都应轮换，可按照以下原则：选左右对称的部位轮流注射，如先选左右上臂，并左右对称轮换注射。待轮完后，换左右腹部。这样可避免因不同部位胰岛素吸收不同而造成血糖波动。\n    常用注射部位有上臂外侧、腹部、大腿外侧、臀部，不同部位胰岛素吸收由快至慢，依次为腹部、上臂、大腿、臀部，如果偶尔吃饭时间提前，则选腹部脐外五厘米以外之处；如果推迟，则选臀部注射。\n二、注射工具选择\n    应选用胰岛素专用注射器或胰岛素笔，上述注射工具操作简单，剂量准确，针头幼细，大大减轻了疼痛感。在这里，特别要提醒正在注射胰岛素的糖尿病朋友，无论是专用注射器还是笔用针头，均应为一次性使用，重复使用会使针头变钝，产生肉眼不易察觉的缺口和倒钩，增加了疼痛感，甚至有断针和皮肤感染的危险。");
    String h=  ("    1、胰岛素须保存在10℃以下的冷藏器内，在2℃~8℃温度的冰箱中可保持活性不变2~3年，即使已部分抽吸使用的胰岛素也是如此。使用时，温度不超过30℃和大于2℃的地方均可，但必须避开阳光，以防失效。\n    2、正在使用中的胰岛素，只要放在室内阴凉处就可以了。开瓶使用中的瓶装胰岛素可以放在冰箱的冷藏室中，保存约3个月。使用中的胰岛素笔芯不要和胰岛素笔一起放回冷藏室中，可随身携带保存4周。\n    3、混浊型胰岛素若是被震摇几个小时或是没有适当保存时便可能会形成团块，这时胰岛素就应该丢弃。");
    String i=  ("    1、胰岛素过量可使血糖过低。其症状视血糖降低的程度和速度而定，可出现饥饿感、精神不安、脉搏加快、瞳孔散大、焦虑、头晕、共济失调、震颤、昏迷，甚至惊厥。必须及时给予食用糖类。出现低血糖休克时，静注50%葡萄糖溶液50ml。必要时，再静滴5%葡萄糖液。\n    2、注射部位可有皮肤发红、皮下结节和皮下脂肪萎缩等局部反应。故需经常更换注射部位。\n    3、少数可发生荨麻疹等，偶有过敏性休克（可用肾上腺素抢救）。 \n    4、极少数病人可产生胰岛素耐受性：即在没有酮症酸中毒的情况下，每日胰岛素需用量高于200单位。其主要原因可能为感染、使用皮质激素或体内存在有胰岛素抗体，能和胰岛素结合。此时可更换用不同动物种属的制剂或加服口服降血糖药。\n    5、低血糖、肝硬变、溶血性黄疸、胰腺炎、肾炎等病人忌用。 \n    6、注射液中多含有防腐剂，一般不宜用于静注。静注宜用针剂安瓿胰岛素制剂。");
    @Override
    public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id){
    	if(groupPosition==0&childPosition==0){
    		new AlertDialog.Builder(insulin.this)
            .setIcon(R.drawable.alert_dialog_icon)
            .setTitle("来源分类")
            .setMessage(a)
            .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {
                	
                }
            })
            
            .create().show();
    	}
       else if(groupPosition==0&childPosition==1){
    		Intent in = new Intent(insulin.this,insulin1.class);
    		 startActivity(in);
    }
       else if(groupPosition==0&childPosition==2){
    	   new AlertDialog.Builder(insulin.this)
           .setTitle("给药装置分类")
           .setItems(c, new DialogInterface.OnClickListener() {
               public void onClick(DialogInterface dialog, int p) {
                   if(p==0){
                	   new AlertDialog.Builder(insulin.this)
                       .setIcon(R.drawable.alert_dialog_icon)
                       .setTitle("注射式胰岛素")
                       .setMessage(c1)
                       .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                           public void onClick(DialogInterface dialog, int whichButton) {     
                           }
                       })
                       
                       .create().show();
                	   
                   }
                   else if(p==1){
                	   new AlertDialog.Builder(insulin.this)
                       .setIcon(R.drawable.alert_dialog_icon)
                       .setTitle("非注射式胰岛素")
                       .setMessage(c2)
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
        else if(groupPosition==0&childPosition==3){
        	new AlertDialog.Builder(insulin.this)
            .setIcon(R.drawable.alert_dialog_icon)
            .setTitle("如何识别胰岛素标识")
            .setMessage(d)
            .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {    
                }
            })
            
            .create().show();
    	}
        else if(groupPosition==1&childPosition==0){
        	new AlertDialog.Builder(insulin.this)
            .setIcon(R.drawable.ic_popup_reminder)
            .setTitle("药理作用")
            .setMessage(e)
            .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {    
                }
            })
            
            .create().show();
        }
        else if(groupPosition==1&childPosition==1){
        	new AlertDialog.Builder(insulin.this)
            .setTitle("初始计量选择参考方法")
            .setItems(f, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int p) {
                	 if(p==0){
                  	   new AlertDialog.Builder(insulin.this)
                         .setIcon(R.drawable.ic_popup_reminder)
                         .setTitle("初始计量选择须知")
                         .setMessage(f1)
                         .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                             public void onClick(DialogInterface dialog, int whichButton) {   
                             }
                         })
                         
                         .create().show();
                  	   
                     } 
                	   else if(p==1){
                 	    new AlertDialog.Builder(insulin.this)
                        .setIcon(R.drawable.ic_popup_reminder)
                        .setTitle("尿糖依据")
                        .setMessage(f2)
                        .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {     
                            }
                        })
                        
                        .create().show();
                 	   
                    }
                    /* User clicked so do some stuff */
                      else if(p==2){
                 	     new AlertDialog.Builder(insulin.this)
                        .setIcon(R.drawable.ic_popup_reminder)
                        .setTitle("血糖依据")
                        .setMessage(f3)
                        .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {   
                            }
                        })
                        
                        .create().show();
                 	   
                    } 
                      else if(p==3){
                  	    new AlertDialog.Builder(insulin.this)
                         .setIcon(R.drawable.ic_popup_reminder)
                         .setTitle("降糖药依据")
                         .setMessage(f4)
                         .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                             public void onClick(DialogInterface dialog, int whichButton) {   
                             }
                         })
                         
                         .create().show();
                  	   
                     } 
                       else if(p==4){
                  	     new AlertDialog.Builder(insulin.this)
                         .setIcon(R.drawable.ic_popup_reminder)
                         .setTitle("经验依据")
                         .setMessage(f5)
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
        else if(groupPosition==1&childPosition==2){
        	new AlertDialog.Builder(insulin.this)
            .setIcon(R.drawable.ic_popup_reminder)
            .setTitle("如何正确、安全地注射胰岛素")
            .setMessage(g)
            .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {     
                }
            })
            
            .create().show();
     	   }
        else if(groupPosition==2&childPosition==0){
        	new AlertDialog.Builder(insulin.this)
            .setIcon(R.drawable.ic_popup_reminder)
            .setTitle("胰岛素存放")
            .setMessage(h)
            .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {     
                }
            })
            
            .create().show();
     	   }
        else if(groupPosition==2&childPosition==1){
        	new AlertDialog.Builder(insulin.this)
            .setIcon(R.drawable.ic_popup_reminder)
            .setTitle("注射须知")
            .setMessage(i)
            .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int whichButton) {     
                }
            })
            
            .create().show();
     	   }
    return true;
    }
}