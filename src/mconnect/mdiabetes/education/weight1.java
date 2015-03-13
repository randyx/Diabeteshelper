package mconnect.mdiabetes.education;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import mconnect.mdiabetes.framework.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
public class weight1 extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	setContentView(R.layout.weight1);
	 setTitle("计算标准体重");
	Bundle bunde = this.getIntent().getExtras();
	double height = bunde.getDouble("height");	
	String weight = this.getWeight("",height);
	TextView tv1 = (TextView) findViewById(R.id.t1);
	tv1.setText( "你的身高是：" + height +
	"厘米\n你的标准体重是："+ weight + "公斤");
	}
	/* 四舍五入的method */
	private String format(double num) {
	NumberFormat formatter = new DecimalFormat("0.00");
	String s = formatter.format(num);
	return s;
	}
	private String getWeight(String sex, double height) {
		String weight = "";
		
		weight = format(height -105);
		return weight;
		}
		}