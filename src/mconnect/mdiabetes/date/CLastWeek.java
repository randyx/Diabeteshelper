package mconnect.mdiabetes.date;

import java.util.Calendar;

public class CLastWeek {
	private String[] week;
	private String[] week_2;

	public String[] getWeek(int y,int mm,int d){
		week=new String[8];
		week_2=new String[8];
		Calendar c=Calendar.getInstance();
	    //int year=c.get(Calendar.YEAR);
	    //int month=c.get(Calendar.MONTH)+1;
	    //int day=c.get(Calendar.DATE);
		int year=y;
	    int month=mm;
	    int day=d;
	    //day=5;
	    String smonth;
	    
	    
	    String sday;
	    
	    if(day>=7){
	    	for(int i=6;i>=0;i--){
	    		if(month<10){
	    	    	smonth="0"+month;
	    	    }
	    	    else{
	    	    	smonth=""+month;
	    	    }
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
	    	day=c.get(Calendar.DATE);
	    	if(day==maxDate){
	    		if(month==12){
	    			week[7]="1/1";
	    			week_2[7]=(year+1)+"-1-1";
	    		}
	    		else
	    		{
	    			if((month+1)<10){
		    	    	smonth="0"+(month+1);
		    	    }
		    	    else{
		    	    	smonth=""+(month+1);
		    	    }
	    			week[7]="1/"+smonth;
	    			week_2[7]=year+"-"+smonth+"-1";
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
	    		
	    		week[7]=""+sday+"/"+smonth;
	    		week_2[7]=year+"-"+smonth+"-"+sday;
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
	    	
	    	week[7]=""+sday+"/"+smonth;
	    	week_2[7]=year+"-"+smonth+"-"+sday;
	    	for(int i=6;day>0;day--,i--){
	    		if(month<10){
	    	    	smonth="0"+month;
	    	    }
	    	    else{
	    	    	smonth=""+month;
	    	    }
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
	    		
	    		int n=7-c.get(Calendar.DATE);
	    		//int n=7-5;
	    		int m=31;
	    		for(int k=n;k>0;k--,m--){
	    			week[k-1]=""+m+"/12";
	    			week_2[k-1]=(year-1)+"-12-"+m;
	    		}
	    	}
	    	else{
	    		c.set(Calendar.MONTH, month-2);
	    		int maxDate=c.getActualMaximum(Calendar.DATE);
	    		//int n=7-5;
	    		int n=7-c.get(Calendar.DATE);
	    		if((month-1)<10){
	    	    	smonth="0"+(month-1);
	    	    }
	    	    else{
	    	    	smonth=""+(month-1);
	    	    }
	    		for(int k=n;k>0;k--,maxDate--){
	    			week[k-1]=""+maxDate+"/"+smonth;
	    			week_2[k-1]=year+"-"+smonth+"-"+maxDate;
	    		}
	    	}
	    }
		return week;
	}
	
	public String[] getWeek2(){
		return week_2;
	}
}
