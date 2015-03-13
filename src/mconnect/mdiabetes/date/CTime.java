package mconnect.mdiabetes.date;

public class CTime {
	public double getTime(String ss){
		double time=0.0;
		String[] value=ss.split(":");
		
		value[1]="0."+value[1];
		
		
		time=(Double.parseDouble(value[0])+Double.parseDouble(value[1]))/24.0;
		
		return time;
	}
}
