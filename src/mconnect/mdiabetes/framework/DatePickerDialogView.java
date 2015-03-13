package mconnect.mdiabetes.framework;

import java.util.Calendar;   
import java.util.GregorianCalendar;   

import mconnect.mdiabetes.framework.R;

   
   
import android.content.Context;   
import android.util.AttributeSet;   
import android.view.LayoutInflater;   
import android.view.View;   
import android.widget.DatePicker;   
import android.widget.LinearLayout;   
import android.widget.DatePicker.OnDateChangedListener;   
   
public class DatePickerDialogView extends LinearLayout {   
   
 
    private static Context mContext;   
   
    private DatePicker mDatePicker;   
   
    private Calendar mC;   
   
    public DatePickerDialogView(Context context, AttributeSet attrs) {   
        super(context, attrs);   
        initialize(context);   
    }   
   
    public DatePickerDialogView(Context context) {   
        super(context);   
        initialize(context);   
    }   
   
    private void initialize(Context context) {   
        mC = Calendar.getInstance();   
   
        mContext = context;   
        View view = LayoutInflater.from(mContext).inflate(R.layout.choise_date, null);   
        mDatePicker = (DatePicker) view.findViewById(R.id.datepicker_date);   
        mDatePicker.init(mC.get(Calendar.YEAR), mC.get(Calendar.MONTH), mC.get(Calendar.DAY_OF_MONTH), onDateChangedListener);   
   
        addView(view);   
    }   
   
    /**  
     * 日期更改时监听事件  
     */   
    private OnDateChangedListener onDateChangedListener = new OnDateChangedListener() {   
   
        @Override   
        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {   
   
            //判断闰年   
            boolean mIsLeapYear = ((GregorianCalendar)mC).isLeapYear(year);   
   
            if(mIsLeapYear && monthOfYear == 1 && dayOfMonth > 29) {   
               
                view.init(year, monthOfYear, 29, onDateChangedListener);   
            } else if(mIsLeapYear && monthOfYear == 1) {   
                
                view.init(year, monthOfYear, dayOfMonth, onDateChangedListener);   
            } else if(!mIsLeapYear && monthOfYear == 1 && dayOfMonth > 28) {   
               
                view.init(year, monthOfYear, 28, onDateChangedListener);   
            } else if(!mIsLeapYear && (monthOfYear != 1) && ((monthOfYear + 1) % 2 == 0) && (dayOfMonth > 30)) {   
                
                view.init(year, monthOfYear, 30, onDateChangedListener);   
            } else if((monthOfYear != 1) && ((monthOfYear + 1) % 2 == 0) && (dayOfMonth > 30)) {   
                
                view.init(year, monthOfYear, 30, onDateChangedListener);   
            }   
        }   
    };   
   
    /**  
     * 获得日期  
     * @return String  
     */   
    public String getDate() {   
        return mDatePicker.getYear() + "-" + (mDatePicker.getMonth() + 1) + "-" + mDatePicker.getDayOfMonth();   
    }   
   
}   
