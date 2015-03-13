package mconnect.mdiabetes.education;

import mconnect.mdiabetes.framework.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.view.View;
import android.content.Intent;
import android.view.View.OnClickListener;
public class index extends Activity {
    /** Called when the activity is first created. */
	private RadioGroup sex;
	private RadioButton rbtn1,rbtn2;
	private String Sex;
    @Override
    public void onCreate(Bundle savedInstanceState) {   	
        super.onCreate(savedInstanceState);
        setContentView(R.layout.index);  
        setTitle("个人信息");
        sex=(RadioGroup)findViewById(R.id.sex);
        rbtn1=(RadioButton)findViewById(R.id.rbtn1);
        rbtn2=(RadioButton)findViewById(R.id.rbtn2);
        sex.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				// TODO Auto-generated method stub
				if(checkedId==rbtn1.getId())
					Sex="m";
				else
					Sex="f";
				
			}
		});
        Button b1=(Button)findViewById(R.id.b1);
        b1.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        	EditText et = (EditText) findViewById(R.id.e1);
        	double height = Double.parseDouble(et.getText().toString());
        	EditText et1 = (EditText) findViewById(R.id.e2);
        	double weight = Double.parseDouble(et1.getText().toString());
        	Intent intent = new Intent(index.this, index1.class);
        	
        	Bundle bundle = new Bundle();
        	bundle.putDouble("height", height);
        	bundle.putDouble("weight", weight);
        	bundle.putString("sex", Sex);
        	intent.putExtras(bundle);
        	startActivity(intent);
        	finish();
        	}
        });
        Button b2=(Button)findViewById(R.id.b2);
        b2.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				finish();
			}
		});
      
}
}
