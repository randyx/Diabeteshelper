package mconnect.mdiabetes.date;

import java.util.Calendar;

public class CLastThirtyDays {
	private String[] week;
	private String[] week_2;
	public String[] getThirtyDay(int y,int mm,int d){
		week=new String[31];
		week_2=new String[31];
		Calendar c=Calendar.getInstance();
	    //int year=c.get(Calendar.YEAR);
	    //int month=c.get(Calendar.MONTH)+1;
	    //int day=c.get(Calendar.DATE);
		
		int year=y;
	    int month=mm;
	    int day=d;
	    
	    String smonth;
	    String sday;
	    if((month==3)&&(day==1)){
	    	Calendar c1=Calendar.getInstance();
	    	c1.set(Calendar.MONTH, 1);
	    	int dayof2=c1.getActualMaximum(Calendar.DATE);
	    	if(dayof2==28){
	    		week[0]="31/01";
	    		week[29]="01/03";
	    		week[30]="02/03";
	    		week_2[0]=year+"-01-31";
	    		week_2[29]=year+"-03-01";
	    		week_2[30]=year+"-03-02";
	    		for(int i=0;i<28;i++){
	    			if((i+1)<10){
	    				sday="0"+(i+1);
	    			}
	    			else{
	    				sday=""+(i+1);
	    			}
	    			week[i+1]=sday+"/02";
	    			week[i+1]=year+"-02-"+sday;
	    		}
	    		return week;
	    	}
	    }
	    if(day>=30){
	    	if(month<10){
	    		smonth="0"+month;
	    	}
	    	else{
	    		smonth=""+month;
	    	}
	    	for(int i=29;i>=0;i--){
	    		if(day<10){
	    			sday="0"+day;
	    		}
	    		else{
	    			sday=""+day;
	    		}
	    		week[i]=""+sday+"/"+smonth;
	    		week_2[i]=year+"-"+smonth+"-"+sday;
	    		day--;
	    	}
	    	int maxDate=c.getActualMaximum(Calendar.DATE);
	    	System.out.println("maxDate="+maxDate);
	    	day=c.get(Calendar.DATE);
	    	System.out.println("day="+day);
	    	if(day==maxDate){
	    		if(month==12){
	    			week[30]="01/01";
	    			week_2[30]=(year+1)+"-01-01";
	    		}
	    		else
	    		{
	    			if((month+1)<10){
	    				smonth="0"+(month+1);
	    			}
	    			else{
	    				smonth=""+(month+1);
	    			}
	    			week[30]="01/"+smonth;
	    			week_2[30]=year+"-"+smonth+"-01";
	    		}
	    	}
	    	else{
	    		if(month<10){
	    			smonth="0"+month;
	    		}
	    		else{
	    			smonth=""+month;
	    		}
	    		week[30]="31/"+smonth;
	    		week_2[30]=year+"-"+smonth+"-31";
	    	}
	    }
	    else{
	    	if(month<10){
	    		smonth="0"+month;
	    	}
	    	else{
	    		smonth=""+month;
	    	}
	    	
	    	if((day+1)<10){
	    		sday="0"+(day+1);
	    		
	    	}
	    	else{
	    		sday=""+(day+1);
	    	}
	    	week[30]=""+sday+"/"+smonth;
	    	week_2[30]=year+"-"+smonth+"-"+sday;
	    	for(int i=29;day>0;day--,i--){
	    		if(day<10){
	    			sday="0"+day;
	    		}
	    		else{
	    			sday=""+day;
	    		}
	    		week[i]=""+sday+"/"+smonth;
	    		week_2[i]=year+"-"+smonth+"-"+sday;
	    	}
	    	if(month==1){
	    		
	    		int n=30-c.get(Calendar.DATE);
	    		//int n=7-5;
	    		int m=31;
	    		for(int k=n;k>0;k--,m--){
	    			if(m<10){
	    				sday="0"+m;
	    			}
	    			else{
	    				sday=""+m;
	    			}
	    			week[k-1]=""+sday+"/12";
	    			week_2[k-1]=(year-1)+"-12-"+sday;
	    		}
	    	}
	    	else{
	    		c.set(Calendar.MONTH, month-2);
	    		int maxDate=c.getActualMaximum(Calendar.DATE);
	    		//int n=7-5;
	    		int n=30-c.get(Calendar.DATE);
	    		if((month-1)<10){
	    			smonth="0"+(month-1);
	    		}
	    		else{
	    			smonth=""+(month-1);
	    		}
	    		for(int k=n;k>0;k--,maxDate--){
	    			if(maxDate<10){
	    				sday="0"+maxDate;
	    			}
	    			else{
	    				sday=""+maxDate;
	    			}
	    			week[k-1]=""+sday+"/"+smonth;
	    			week_2[k-1]=year+"-"+smonth+"-"+sday;
	    		}
	    	}
	    }
		return week;
	}
	
	public String[] getThirtyDay2(){
		return week_2;
	}
	
	public double[] getThirtyDayXV(String[] a, String[] b, String[] c,int n){
		double[] value=new double[n];
		CTime ct=new CTime();
		for(int i=0;i<n;i++){
			for(int j=0;j<30;j++){
				if(a[i].equals(c[j])){
					double d=ct.getTime(b[i]);
					value[i]=((double)(j)+d)*6.0/30.0+1.0;
					System.out.println("vx="+value[i]);
					break;
				}
			}
		}
		return value;
	}
}
