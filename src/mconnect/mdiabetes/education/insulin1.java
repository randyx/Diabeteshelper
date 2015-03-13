package mconnect.mdiabetes.education;



import mconnect.mdiabetes.framework.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
public class insulin1 extends Activity {
	TextView t1,t2,t3,t4,t11,t12,t13,t14,t15,t21,t22,t23,t24,t25,t31,t32,t33,t34,t35,t41,t42,t43,t44,t45;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.insulin1);
        setTitle("药效时间分类");
        t1=(TextView)findViewById(R.id.t1);
        t2=(TextView)findViewById(R.id.t2);
        t3=(TextView)findViewById(R.id.t3);
        t4=(TextView)findViewById(R.id.t4);
        t11=(TextView)findViewById(R.id.t11);
        t12=(TextView)findViewById(R.id.t12);
        t13=(TextView)findViewById(R.id.t13);
        t14=(TextView)findViewById(R.id.t14);
        t15=(TextView)findViewById(R.id.t15);
        t21=(TextView)findViewById(R.id.t21);
        t22=(TextView)findViewById(R.id.t22);
        t23=(TextView)findViewById(R.id.t23);
        t24=(TextView)findViewById(R.id.t24);
        t25=(TextView)findViewById(R.id.t25);
        t31=(TextView)findViewById(R.id.t31);
        t32=(TextView)findViewById(R.id.t32);
        t33=(TextView)findViewById(R.id.t33);
        t34=(TextView)findViewById(R.id.t34);
        t35=(TextView)findViewById(R.id.t35);
        t41=(TextView)findViewById(R.id.t41);
        t42=(TextView)findViewById(R.id.t42);
        t43=(TextView)findViewById(R.id.t43);
        t44=(TextView)findViewById(R.id.t44);
        t45=(TextView)findViewById(R.id.t45);
        Button b1 =(Button)findViewById(R.id.b1);
        b1.setOnClickListener(new OnClickListener(){
      	   public void onClick(View v) {
      		   finish();
      	   }
        });
        }
    
}