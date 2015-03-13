package mconnect.mdiabetes.education;


import mconnect.mdiabetes.framework.R;
import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.view.View.OnClickListener;
public class weight extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weight);
        setTitle("计算标准体重");
        Button b1=(Button)findViewById(R.id.b1);
        b1.setOnClickListener(new OnClickListener() {
        	public void onClick(View v) {
        	EditText et = (EditText) findViewById(R.id.e1);
        	double height = Double.parseDouble(et.getText().toString());
        	Intent intent = new Intent(weight.this, weight1.class);
        	Bundle bundle = new Bundle();
        	bundle.putDouble("height", height);
        	intent.putExtras(bundle);
        	startActivity(intent);
        	}
        });
      
}
}