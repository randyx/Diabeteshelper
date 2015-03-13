package mconnect.mdiabetes.education;

import mconnect.mdiabetes.framework.R;
import android.app.Dialog;
import android.app.ExpandableListActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AbsListView;
import android.widget.BaseExpandableListAdapter;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ExpandableListView.ExpandableListContextMenuInfo;



// ExpandableList - 可展开/收缩列表

// 继承 ExpandableListActivity 以实现列表的可展开/收缩的功能



public class education extends ExpandableListActivity {

    

    private ExpandableListAdapter mAdapter;
    private Intent intent;
    private double height;
	private EditText mEditText;
    @Override
    protected Dialog onCreateDialog(int id) {
		if (id == 1) {
			return new AlertDialog.Builder(education.this)
	          .setIcon(R.drawable.check)
			.setTitle(R.string.alert_dialog_two_buttons_title)
			.setMessage(R.string.alert_dialog_two_buttons2_msg)
			.setPositiveButton(
					R.string.alert_dialog_ok,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {

							/* User clicked OK so do some stuff */
						}
					})
			// .setNegativeButton(R.string.alert_dialog_cancel, new
			// DialogInterface.OnClickListener() {
					// public void onClick(DialogInterface dialog, int
					// whichButton) {

					/* User clicked Cancel so do some stuff */
					// }
					// })
					.create();
		} else if (id == 2) {
			return new AlertDialog.Builder(education.this)
					.setTitle(R.string.select_dialog1)
					.setItems(R.array.select_dialog_items2,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									if (which == 0) {
										intent = new Intent(
												education.this,
												k1200.class);
										startActivity(intent);
										overridePendingTransition(R.anim.fade,
												R.anim.hold);
									} else if (which == 1) {
										intent = new Intent(
												education.this,
												k1400.class);
										startActivity(intent);
										overridePendingTransition(R.anim.fade,
												R.anim.hold);
									} else if (which == 2) {
										intent = new Intent(
												education.this,
												k1600.class);
										startActivity(intent);
										overridePendingTransition(R.anim.fade,
												R.anim.hold);
									} else if (which == 3) {
										intent = new Intent(
												education.this,
												k1800.class);
										startActivity(intent);
										overridePendingTransition(R.anim.fade,
												R.anim.hold);
									} else if (which == 4) {
										intent = new Intent(
												education.this,
												k2000.class);
										startActivity(intent);
										overridePendingTransition(R.anim.fade,
												R.anim.hold);
									} else if (which == 5) {
										intent = new Intent(
												education.this,
												k2200.class);
										startActivity(intent);
										overridePendingTransition(R.anim.fade,
												R.anim.hold);
									}
									/* User clicked so do some stuff */
									// String[] items =
									// getResources().getStringArray(R.array.select_dialog_items);
									// new
									// AlertDialog.Builder(whatIsDiabetes.this)
									// .setMessage("You selected: " + which +
									// " , " + items[which])
									// .show();
								}
							}).create();

		} else if (id == 3) {
			LayoutInflater factory = LayoutInflater.from(this);
			final View textEntryView = factory.inflate(
					R.layout.alert_dialog_text_entry, null);

			return new AlertDialog.Builder(education.this).setIcon(
					R.drawable.alert_dialog_icon).setTitle(
					R.string.alert_dialog_text_entry).setView(textEntryView)
					.setPositiveButton(R.string.alert_dialog_ok,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {

									mEditText = (EditText) textEntryView
											.findViewById(R.id.username_edit);
									if (mEditText.getText().toString().length() == 0) {

										new AlertDialog.Builder(
												education.this)
												.setMessage("请輸入您的年龄")
												.setTitle("提示")
												.setNeutralButton(
														"确定",
														new DialogInterface.OnClickListener() {
															@Override
															public void onClick(
																	DialogInterface dialog,
																	int which) {
																// TODO
																// Auto-generated
																// method stub
																mEditText
																		.setHighlightColor(Color.RED);
															}
														}).create().show();
										return;
									}

									height = Double.parseDouble(mEditText
											.getText().toString());

									Intent intent1 = new Intent();

									intent1.setClass(
											education.this,
											Next.class);

									Bundle bun = new Bundle();
									bun.putDouble("Height", height);

									intent1.putExtras(bun);
									startActivity(intent1);
									education.this.finish();
								}
							}).setNegativeButton(R.string.alert_dialog_cancel,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int whichButton) {

									/* User clicked cancel so do some stuff */
								}
							}).create();
		} else if (id == 4) {
			return new AlertDialog.Builder(education.this)
					.setTitle(R.string.select_dialog2).setItems(
							R.array.select_dialog_items3,
							new DialogInterface.OnClickListener() {
								public void onClick(DialogInterface dialog,
										int which) {
									if (which == 0) {
										intent = new Intent(
												education.this,
												walk.class);
										startActivity(intent);
										overridePendingTransition(R.anim.fade,
												R.anim.hold);
									} else if (which == 1) {
										intent = new Intent(
												education.this,
												swimming.class);
										startActivity(intent);
										overridePendingTransition(R.anim.fade,
												R.anim.hold);
									} else if (which == 2) {
										intent = new Intent(
												education.this,
												taiji.class);
										startActivity(intent);
										overridePendingTransition(R.anim.fade,
												R.anim.hold);
									} else if (which == 3) {
										intent = new Intent(
												education.this,
												jog.class);
										startActivity(intent);
										overridePendingTransition(R.anim.fade,
												R.anim.hold);
									} else if (which == 4) {
										intent = new Intent(
												education.this,
												yoga.class);
										startActivity(intent);
										overridePendingTransition(R.anim.fade,
												R.anim.hold);
									} else if (which == 5) {
										intent = new Intent(
												education.this,
												atHome.class);
										startActivity(intent);
										overridePendingTransition(R.anim.fade,
												R.anim.hold);
									}
									/* User clicked so do some stuff */
									// String[] items =
									// getResources().getStringArray(R.array.select_dialog_items);
									// new
									// AlertDialog.Builder(whatIsDiabetes.this)
									// .setMessage("You selected: " + which +
									// " , " + items[which])
									// .show();
								}
							}).create();

		} else
			return null;

	}
    @Override

    protected void onCreate(Bundle savedInstanceState) {

        // TODO Auto-generated method stub

        super.onCreate(savedInstanceState);



        setTitle("糖尿病知识");

        
        mAdapter=new MyExpandableListAdapter();
        

        setListAdapter(mAdapter);

        registerForContextMenu(this.getExpandableListView());

    }



    // 为列表的每一项创建上下文菜单（即长按后呼出的菜单） 

    @Override

    public void onCreateContextMenu(ContextMenu menu, View v,

            ContextMenuInfo menuInfo) {

        menu.setHeaderTitle("ContextMenu");

        menu.add(0, 0, 0, "ContextMenu");

    }



    // 单击上下文菜单后的逻辑

    @Override

    public boolean onContextItemSelected(MenuItem item) {

        ExpandableListContextMenuInfo info = (ExpandableListContextMenuInfo) item.getMenuInfo();

        String title = ((TextView) info.targetView).getText().toString();



        int type = ExpandableListView.getPackedPositionType(info.packedPosition);

        if (type == ExpandableListView.PACKED_POSITION_TYPE_CHILD) {

            int groupPos = ExpandableListView.getPackedPositionGroup(info.packedPosition);

            int childPos = ExpandableListView.getPackedPositionChild(info.packedPosition);

            

            Toast.makeText(this, title + " - Group Index: " + groupPos + " Child Index: " + childPos, Toast.LENGTH_SHORT).show();

            

            return true;

        } else if (type == ExpandableListView.PACKED_POSITION_TYPE_GROUP) {

            int groupPos = ExpandableListView.getPackedPositionGroup(info.packedPosition);

            Toast.makeText(this, title + " - Group Index: " + groupPos, Toast.LENGTH_SHORT).show();

            

            return true;

        }



        return false;

    }
    
       
    
    public class MyExpandableListAdapter extends BaseExpandableListAdapter {



        // 父列表数据

        private String[] groups = 

        { 

        		"基础知识","饮食治疗","运动治疗","医药治疗","自我监测"

        };

        // 子列表数据

        private String[][] children = 

        {

        		{"糖尿病浅知","糖尿病心理治疗"},
                {"饮食须知","饮食量计算","三餐推荐"},
                {"运动须知","运动强度控制","推荐运动"},
                {"口服降糖药","胰岛素"},
                {"血糖监测","体重监测"},
        };

        

        @Override

        public Object getChild(int groupPosition, int childPosition) {

            return children[groupPosition][childPosition];

        }



        @Override

        public long getChildId(int groupPosition, int childPosition) {

            return childPosition;

        }



        @Override

        public int getChildrenCount(int groupPosition) {

            return children[groupPosition].length;

        }



        // 取子列表中的某一项的 View

        @Override

        public View getChildView(int groupPosition, int childPosition,

        		boolean isLastChild,View convertView, ViewGroup parent) {

            TextView textView = getGenericView();

            textView.setText(getChild(groupPosition, childPosition).toString());
            return textView;
            

        }



        @Override

        public Object getGroup(int groupPosition) {

            return groups[groupPosition];

        }



        @Override

        public int getGroupCount() {

            return groups.length;

        }



        @Override

        public long getGroupId(int groupPosition) {

            return groupPosition;

        }



        // 取父列表中的某一项的 View

        @Override

        public View getGroupView(int groupPosition, boolean isExpanded,

                View convertView, ViewGroup parent) {

            TextView textView = getGenericView();

            textView.setText(getGroup(groupPosition).toString());

            return textView;

        }



        @Override

        public boolean hasStableIds() {

            return true;

        }
        
       String []a={"标准体重计算","体质指数计算"};   
       @Override
        public boolean isChildSelectable(int groupPosition, int childPosition) {
        //public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id){	
            if(groupPosition==0&childPosition==0){
        		Intent in = new Intent(education.this,knowledge.class);
	         		 startActivity(in);}
        	else if(groupPosition==0&childPosition==1){
        		Intent in1 = new Intent(education.this,psychological.class);
        		 startActivity(in1);
        		}
        	else if(groupPosition==1&childPosition==0){
        		Intent in = new Intent(education.this,dietPlan.class);
				startActivity(in);
				//overridePendingTransition(R.anim.fade, R.anim.hold);
        	    }
            else if(groupPosition==1&childPosition==1){
            	Intent intent = new Intent(education.this,
						dietCounting.class);
				startActivity(intent);
				overridePendingTransition(R.anim.fade, R.anim.hold);
        	    }
           else if(groupPosition==1&childPosition==2){
        	   showDialog(2);
                }
            else if(groupPosition==2&childPosition==0){
            	Intent intent = new Intent(education.this,
						exerciseTip.class);
				startActivity(intent);
        	    }
            else if(groupPosition==2&childPosition==1){
            	showDialog(3);
                }
            else if(groupPosition==2&childPosition==2){
            	showDialog(4);
                }
            else if(groupPosition==3&childPosition==0){
            	 Intent in = new Intent(education.this,medicine.class);
         		 startActivity(in);
         		 }
            else if(groupPosition==3&childPosition==1){
           	 Intent in = new Intent(education.this,insulin.class);
        		 startActivity(in);
        		 }
            else if(groupPosition==4&childPosition==0){
            	Intent in = new Intent(education.this,glucose.class);
       		         startActivity(in);
       		      }
            
            else if(groupPosition==4&childPosition==1){
            	new AlertDialog.Builder(education.this)
                .setTitle("体重监测")
                .setItems(a, new DialogInterface.OnClickListener() {
                  public void onClick(DialogInterface dialog, int which) {
                	  if(which==0){
                   	   Intent in = new Intent(education.this,weight.class);
                 		 startActivity(in);
                 		 }
                	  else if(which==1){
                   	   Intent in = new Intent(education.this,index.class);
                 		 startActivity(in);
                 		 }   
                       
                       
                    }
                })
                .create().show();
            }
            	 
            	 /*new AlertDialog.Builder(eduction.this)
                   .setIcon(R.drawable.alert_dialog_icon)
                   .setTitle("口服降糖药品")
        		   .setSingleChoiceItems(a,0,new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int p1) {
                                
                                   
                               }
                           })
                         .create().show();*/
        		   
            return true;
       }
                         
                

       // 获取某一项的 View 的逻辑

        private TextView getGenericView() {

            AbsListView.LayoutParams lp = new AbsListView.LayoutParams(

                    ViewGroup.LayoutParams.FILL_PARENT, 64);

            TextView textView = new TextView(education.this);

            textView.setLayoutParams(lp);

            textView.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);

            textView.setPadding(72, 12, 0, 12);
            textView.setTextSize(32);

            return textView;

        }
       
  
    }
    
}