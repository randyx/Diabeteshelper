package mconnect.mdiabetes.education;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import mconnect.mdiabetes.framework.R;
import android.app.Activity;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
public class index1 extends Activity {
	/** Called when the activity is first created. */
	private static String AUTHORITY = "com.chen.provider.diabeteshelper";
	@Override
	public void onCreate(Bundle savedInstanceState) {
	super.onCreate(savedInstanceState);
	Uri CONTENT_URI =Uri.parse("content://" + AUTHORITY + "/BMITable");
	setContentView(R.layout.index1);
	 setTitle("计算体质指数");
	Bundle bunde = this.getIntent().getExtras();
	double height = bunde.getDouble("height");
	double weight = bunde.getDouble("weight");
	String sex = bunde.getString("sex");
	String index = this.getindex(weight,height);
	ContentValues initialValues = new ContentValues();
	initialValues.put("sex", sex);
	initialValues.put("bmi", index);
	getContentResolver().insert(CONTENT_URI, initialValues);
	TextView tv1 = (TextView) findViewById(R.id.t1);
	tv1.setText( "您的身高是：" + height +
	"米\n您的体重是："+ weight+"公斤\n您的体质指数（BMI）是："+index );
	TextView tv2 = (TextView) findViewById(R.id.t2);
	tv2.setText("    肥胖的世界标准是:BMI在18.5至24.9时属正常范围，BMI大于25为超重，BMI大于30为肥胖\n    亚洲人的肥胖标准是：BMI在18.5--22.9时为正常水平，BMI大于23为超重，BMI大于30为肥胖。\n    肥胖的中国标准是：最佳值应该是20--22，BMI大于22.6为超重，BMI大于30为肥胖。 ");
	}
	
	private String format(double num) {
	NumberFormat formatter = new DecimalFormat("0.00");
	String s = formatter.format(num);
	return s;
	}
	private String getindex(double weight, double height) {
		String index = "";
		
		index = format(weight/(height*height));
		return index;
		}
}