package mconnect.mdiabetes.education;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mconnect.mdiabetes.framework.R;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.Activity;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

public class knowledge extends Activity{
	
	private ListView myListView;
	private String a=("   糖尿病（diabetes）是一种常见的内分泌疾病，是由遗传因素、免疫功能紊乱、微生物感染及其毒素、自由基毒素、精神因素等等各种致病因子作用于机体导致胰岛功能减退、胰岛素抵抗(Insulin Resistance，IR)等而引发的糖、蛋白质、脂肪、水和电解质等一系列代谢紊乱综合征，。\n   胰岛素由人体胰脏中的胰腺分泌，胰岛素能使血中的葡萄糖顺利进入人的各器官组织的细胞中，为它们提供能量。因此正常人血糖浓度虽然随进餐有所波动，但在胰岛素的调节下，能使这种波动保持在一定的范围内。而如果缺少胰岛素或是胰岛素不能正常工作时，就会使血中的葡萄糖无法进入细胞提供能量，血糖因此会升高并引起糖尿病。\n   糖尿病的特征表现为血液中葡萄糖的浓度异常升高及尿中有尿糖。随着糖尿病得病时间的延长，身体内的代谢紊乱如果得不到很好的控制，进一步发展则会引起全身各种严重的急、慢性并发症，甚至危及人的生命。");
	private    String b=("糖尿病类型");
	private String []c={"1型糖尿病","2型糖尿病","妊娠期糖尿病"};
	private  String c1=("   1型糖尿病(型糖尿病)，是由于免疫系统发育不良或免疫应激引发的糖尿病。又名胰岛素依赖型糖尿病(IDDM)或青少年糖尿病，易出现糖尿病酮症酸中毒(DKA)。又叫青年发病型糖尿病，这是因为它常常在35岁以前发病，占糖尿病的10%以下。\n   1型糖尿病是依赖胰岛素治疗的，也就是说患者从发病开始就需使用胰岛素治疗，并且终身使用。原因在于1型糖尿病患者体内胰腺产生胰岛素的细胞已经彻底损坏，从而完全失去了产生胰岛素的功能。在体内胰岛素绝对缺乏的情况下，就会引起血糖水平持续升高，出现糖尿病。 "); 
	private String c2=("   2型糖尿病也叫成人发病型糖尿病，多在35~40岁之后发病，占糖尿病患者90%以上。2型糖尿病患者体内产生胰岛素的能力并非完全丧失，有的患者体内胰岛素甚至产生过多，但胰岛素的作用效果却大打折扣，因此患者体内的胰岛素是一种相对缺乏。可以通过某些口服药物刺激体内胰岛素的分泌。但到后期仍有部分病人需要像1型糖尿病那样进行胰岛素治疗。\n   2型糖尿病中一部分病人以胰岛素抵抗为主，病人多肥胖，因胰岛素抵抗，胰岛素敏感性下降，血中胰岛素增高以补偿其胰岛素抵抗，但相对病人的高血糖而言，胰岛素分泌仍相对不足。此类病人早期症状不明显，常在明确诊断之前就可发生大血管和微血管并发症。另一部分病人以胰岛素分泌缺陷为主，临床上需要补充外源性胰岛素");
	private String c3=("   妊娠糖尿病是糖尿病的一种特殊类型。是指妊娠期妇女由于糖代谢、内分泌的变化引起的糖尿病。\n   在怀孕以后，随着胚胎发育，胎盘逐渐形成，分泌较多的各种激素，如胎盘生乳素、雌激素、孕激素等，并随妊娠周数增加而升高，妊娠30周以后达到高峰，这些激素能够抵抗孕妇体内的胰岛素，加上孕妇体内分泌的肾上腺皮质激素也都能够对抗胰岛素，如果孕妇胰岛素功能本来就不足，再加孕妇体内存在胰岛素对抗的各种激素存在，直接影响胰岛功能，妊娠期妇女就容易发生糖尿病");
	private String []d={"多尿","多饮","多食","消瘦"};
	private  String d1=("   不仅指尿的次数增多，而且尿量也明显增加，24小时内可有20多次，尿量达2—3升甚至10升之多。尿液泡沫多，尿渍发白、发粘。多尿是由于血糖升高，超过肾糖阈、(8．9～10mmol/l)，排入尿中的糖增加，于是尿次数与尿量增多。");
	private String d2=("   尿多之后使体内的水分减少，引起大脑口渴中枢的兴奋而思饮。应当指出的是，糖尿病的诊断标准，明显低于肾糖阈，所以在未出现多尿之前可能就已确诊为糖尿病了。另一方面，老年人肾血管硬化，而使肾糖阈升高，所以血糖很高，而尿中无糖，也不会出现多尿：再者，老年人中枢神经感受性减弱，尽管体内水分丢失明显，而口渴中枢无感觉，也不思饮，这也就是老年人常见、死亡率很高的糖尿病非酮症高渗性昏迷的原因。");
	private String d3=("   由于血糖不能进入细胞，不能为细胞利用，刺激大脑的饥饿中枢兴奋而多食，且进食后无饱腹感，造成进食次数和进食量都明显增多。\n  应当注意的是在2型糖尿病早期，由于高胰岛素血症的关系，使血糖利用加快，而出现餐前的明显饥饿感，甚至出现低血糖，这往往是2型糖尿病的首发症状。");
	private String d4=("   体内葡萄糖利用减少，脂肪分解增加，蛋白质合成不足，分解加快等，均引起消瘦，如有多尿症状，体内水分的丢失更会加重消瘦症状。同样，病程时间越长，血糖越高：病情越重，消瘦也就越明显。");
	private String []e={"空腹血糖检查","葡萄糖耐量试验","胰岛B细胞功能测定试验","糖化血红蛋白测定"};
	   
    @Override
    protected Dialog onCreateDialog(int id) {
    	if (id == 1) {
    		return new AlertDialog.Builder(knowledge.this)
            .setIcon(R.drawable.alert_dialog_icon)
            .setTitle("1型糖尿病")
            .setMessage(c1)
            .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
            	public void onClick(DialogInterface dialog, int whichButton) {
                    
                }
            })
            
            .create();		
    	}
    	else if (id == 2) {
    		return new AlertDialog.Builder(knowledge.this)
            .setIcon(R.drawable.alert_dialog_icon)
            .setTitle("2型糖尿病")
            .setMessage(c2)
            .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
            	public void onClick(DialogInterface dialog, int whichButton) {
                   
                }
            })
            
            .create();
    	}
    	else if(id==3){
    		return new AlertDialog.Builder(knowledge.this)
            .setIcon(R.drawable.alert_dialog_icon)
            .setTitle("妊娠期糖尿病")
            .setMessage(c3)
            .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
            	public void onClick(DialogInterface dialog, int whichButton) {
                   
                }
            })
            
            .create();
    	}
    	else if (id==4){
    		return new AlertDialog.Builder(knowledge.this)
            .setIcon(R.drawable.alert_dialog_icon)
            .setTitle("多尿")
            .setMessage(d1)
            .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
            	public void onClick(DialogInterface dialog, int whichButton) {
                    
                }
            })
            
            .create();
    	}
    	else if (id==5){
    		return new AlertDialog.Builder(knowledge.this)
            .setIcon(R.drawable.alert_dialog_icon)
            .setTitle("多饮")
            .setMessage(d2)
            .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
            	public void onClick(DialogInterface dialog, int whichButton) {
                   
                }
            })
            
            .create();
    	}
    	else if (id==6){
    		return new AlertDialog.Builder(knowledge.this)
            .setIcon(R.drawable.alert_dialog_icon)
            .setTitle("多食")
            .setMessage(d3)
            .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
            	public void onClick(DialogInterface dialog, int whichButton) {
                
                }
            })
            
            .create();
    	}
    	else if (id==7){
    		return new AlertDialog.Builder(knowledge.this)
            .setIcon(R.drawable.alert_dialog_icon)
            .setTitle("消瘦")
            .setMessage(d4)
            .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
            	public void onClick(DialogInterface dialog, int whichButton) {
                    
                }
            })
            
            .create();
    	}
    	else if (id == 8) {
			return  new AlertDialog.Builder(knowledge.this)
            .setIcon(R.drawable.alert_dialog_icon)
            .setTitle("糖尿病是什么")
            .setMessage(a)
            .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
            	public void onClick(DialogInterface dialog, int whichButton) {
                    
                    
                }
            })

		.create();
		}
		else if (id == 9) {
			return new AlertDialog.Builder(knowledge.this)
            .setTitle(b)
            .setItems(c, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                           
                           if (which==0){
                        	   showDialog(1);
                           }
                           else if (which==1){
                        	   showDialog(2);
                           }
                           else if (which==2){
                        	   showDialog(3);
                           }
                }
            }) 
            .create();
		}
		else if (id == 10) {
			return new  AlertDialog.Builder(knowledge.this)
            .setTitle("典型症状表现")
            .setItems(d, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                           
                           if (which==0){
                        	   showDialog(4);
                           }
                           else if (which==1){
                        	   showDialog(5);
                           }
                           else if (which==2){
                        	   showDialog(6);
                           }
                           else if (which==3){
                        	   showDialog(7);
                           }
                }
            }) 
            .create();
		}
		else if (id==11){
			return new AlertDialog.Builder(knowledge.this)
            .setTitle("检查项目")
            .setItems(e, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    
                     }
                  })
              .create();
		}
				return null;

		}	
	
    @Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main2);

		myListView = (ListView) findViewById(R.id.ListView01);

		SimpleAdapter adapter = new SimpleAdapter(this, getData(),
				R.layout.vlist, new String[] { "title", "info","img" },
				new int[] { R.id.title, R.id.info, R.id.img });
		myListView.setAdapter(adapter);
		 myListView.setOnItemClickListener(new OnItemClickListener() {

				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {
		    
		        if (arg2 == 0){    showDialog(8); 	
		        	/*new AlertDialog.Builder(knowledge.this)
		            .setIcon(R.drawable.alert_dialog_icon)
		            .setTitle("糖尿病是什么")
		            .setMessage(a)
		            .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
		            	public void onClick(DialogInterface dialog, int whichButton) {
		                    
		                    
		                }
		            })
		            
		            .create().show();*/
		        }
		        else if(arg2 == 1){   
		        	showDialog(9);
		        }
		        else if(arg2==2){
		        	showDialog(10);
		        }
		        else if(arg2==3){
		        	showDialog(11);
		        }
				}

			});
		}
		       /* else if(arg2 == 1){ 
		        	new AlertDialog.Builder(knowledge.this)
		            .setTitle(b)
		            .setItems(c, new DialogInterface.OnClickListener() {
		                public void onClick(DialogInterface dialog, int which) {
		                           which=which;
		                      if(which==0){
		                    	  new AlertDialog.Builder(knowledge.this)
		                          .setIcon(R.drawable.alert_dialog_icon)
		                          .setTitle("1型糖尿病")
		                          .setMessage(c1)
		                          .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
		                          	public void onClick(DialogInterface dialog, int whichButton) {
		                                  
		                              }
		                          })
		                          
		                          .create().show(); 
		                      }
		                      else if(which==1){
		                     	 new AlertDialog.Builder(knowledge.this)
		                          .setIcon(R.drawable.alert_dialog_icon)
		                          .setTitle("2型糖尿病")
		                          .setMessage(c2)
		                          .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
		                          	public void onClick(DialogInterface dialog, int whichButton) {
		                                 
		                              }
		                          })
		                          
		                          .create().show(); 
		                      }
		                      else if(which==2){
		                    	  new AlertDialog.Builder(knowledge.this)
		                          .setIcon(R.drawable.alert_dialog_icon)
		                          .setTitle("妊娠期糖尿病")
		                          .setMessage(c3)
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
		        else if(arg2 == 2){ 
		        	new AlertDialog.Builder(knowledge.this)
		            .setTitle("典型症状表现")
		            .setItems(d, new DialogInterface.OnClickListener() {
		                public void onClick(DialogInterface dialog, int which) {
		                           which=which;
		                      if(which==0){
		                    	  new AlertDialog.Builder(knowledge.this)
		                          .setIcon(R.drawable.alert_dialog_icon)
		                          .setTitle("多尿")
		                          .setMessage(d1)
		                          .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
		                          	public void onClick(DialogInterface dialog, int whichButton) {
		                                  
		                              }
		                          })
		                          
		                          .create().show(); 
		                         }
		                      else if(which==1){
		                    	  new AlertDialog.Builder(knowledge.this)
		                          .setIcon(R.drawable.alert_dialog_icon)
		                          .setTitle("多饮")
		                          .setMessage(d2)
		                          .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
		                          	public void onClick(DialogInterface dialog, int whichButton) {
		                                 
		                              }
		                          })
		                          
		                          .create().show();  
		                      }
		                      else if(which==2){
		                    	  new AlertDialog.Builder(knowledge.this)
		                          .setIcon(R.drawable.alert_dialog_icon)
		                          .setTitle("多食")
		                          .setMessage(d3)
		                          .setPositiveButton(R.string.alert_dialog_ok, new DialogInterface.OnClickListener() {
		                          	public void onClick(DialogInterface dialog, int whichButton) {
		                              
		                              }
		                          })
		                          
		                          .create().show();  
		                      }
		                      else if(which==3){
		                    	  new AlertDialog.Builder(knowledge.this)
		                          .setIcon(R.drawable.alert_dialog_icon)
		                          .setTitle("消瘦")
		                          .setMessage(d4)
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
		        else if(arg2 == 3){ 
		        	new AlertDialog.Builder(knowledge.this)
		            .setTitle("检查项目")
		            .setItems(e, new DialogInterface.OnClickListener() {
		                public void onClick(DialogInterface dialog, int which) {
		                    
		                     }
		                  })
		              .create().show();
		               }	
				});
				}*/
		private List<Map<String, Object>> getData() {
			List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();

			Map<String, Object> map = new HashMap<String, Object>();
			map = new HashMap<String, Object>();
			map.put("title", "糖尿病概述");
			map.put("info", "");
			map.put("img", R.drawable.gaishu);
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("title", "糖尿病类型");
			map.put("info", "");
			map.put("img", R.drawable.fenlei);
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("title", "临床典型症状");
			map.put("info", "");
			map.put("img", R.drawable.zhengzhuang);
			list.add(map);

			map = new HashMap<String, Object>();
			map.put("title", "诊断检查");
			map.put("info", "");
			map.put("img", R.drawable.jiancha);
			list.add(map);

			return list;
	

	
	
	/*private String[]menu1 = {"糖尿病概述","糖尿病类型","临床典型症状","诊断检查"};
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         ArrayAdapter adapter = new ArrayAdapter(this,android.R.layout.simple_list_item_1,menu1);
         setListAdapter(adapter);
         //setOnItemClickListener(this);
         setTitle("糖尿病知识");
    }  ;*/
   
    
		}
       }
		 
    
    
