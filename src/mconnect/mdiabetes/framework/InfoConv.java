package mconnect.mdiabetes.framework;
import java.io.Serializable;

import android.app.Service;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;

public class InfoConv {
	private byte Info[];
	/*数据包中的信息变量*/
	private int Flag,Sum,User,HighPress,LowPress,Month,Day,Hour,Pulse,Year,ResultDataDA,ResultDataHO,Minute;//CheckSum;
	/*各信息在包中的位置*/
	private int FlagIndex,SumIndex,UserIndex,HighPressIndex,LowPressIndex,PulseIndex,YearIndex,ResultDataDAIndex,ResultDataHOIndex,MinuteIndex/*,CheckSumIndex*/;
	private int begin=0,end=0;
	public InfoConv()
	{
		//byte tmp[]={0x11,(byte)0xfe,0x34,0x32,0x78,0x12,0x6a,0x63,0x76,0x23,0x45,0x67,0x68,0x08,0x69,0x20,0x01,0x6d,0x64,(byte)0xb5,0x67,0x67,0x20,0x68,(byte)0xff,0x65};		
//		int cnt;
		Info=new byte[1024];
//		for(cnt=0;cnt<tmp.length;cnt++)
//		{
//			Info[cnt]=tmp[cnt];
//		};
	};
	public InfoConv(byte tmp[]) 
	{
		int cnt;
		Info = new byte[tmp.length];
		for(cnt=0;cnt<tmp.length;cnt++)
		{
			Info[cnt] = tmp[cnt];
		}
	};
	public String Convert()
	{
		int cnt;
		String returnInfo;
		FlagIndex=1;
		UserIndex=9;
		SumIndex=7;
		HighPressIndex=10;
		LowPressIndex=11;
		PulseIndex=12;
		YearIndex=13;
		ResultDataDAIndex=14;
		ResultDataHOIndex=15;
		MinuteIndex=16;
		//CheckSumIndex=21;
		for(cnt=0;cnt<Info.length;cnt++)
		{
			//System.out.println(" "+Info[cnt]);
			if(Info[cnt]==(byte)0xfe)
			{
				end=cnt;
				break;
			}
			if(Info[cnt]==(byte)0xff)
				begin = cnt;
		}
		/*数据包内容标志*/
		Flag=Info[begin+FlagIndex];
		Flag=(Flag>0)?Flag:(256+Flag);
		/*有效数据总个数*/
		Sum=Info[begin+SumIndex];
		Sum=(Sum>0)?Sum:(256+Sum);
		/*用户编号*/
		User=Info[begin+UserIndex]&((byte)0x0f);
		User=(User>=0)?User:(256+User);
		HighPress=Info[begin+HighPressIndex];
		HighPress=(HighPress>0)?HighPress:(256+HighPress);
		LowPress=Info[begin+LowPressIndex];
		LowPress=(LowPress>0)?LowPress:(256+LowPress);
		Pulse=Info[begin+PulseIndex];
		Pulse=(Pulse>0)?Pulse:(256+Pulse);
		ResultDataDA=Info[begin+ResultDataDAIndex];
		ResultDataDA=(ResultDataDA>0)?ResultDataDA:(256+ResultDataDA);
		ResultDataHO=Info[begin+ResultDataHOIndex];
		ResultDataHO=(ResultDataHO>0)?ResultDataHO:(256+ResultDataHO);
		Year=Info[begin+YearIndex];
		Year=(Year>0)?Year:(256+Year);
		Minute=Info[begin+MinuteIndex];
		Minute=(Minute>0)?Minute:(256+Minute);
		Month = (ResultDataDA & 0xc0)/64 + (ResultDataHO & 0xc0)/16;
		Day = ResultDataDA & 0x3f;
		Hour =ResultDataHO & 0x3f;
		returnInfo="用户编号："+User+'\n'+"高压："+HighPress+'\n'+"低压："+LowPress+'\n'+"脉搏："+Pulse+'\n'+"日期："+Year+"年"+Month+"月"+Day+"日"+Hour+":"+Minute+'\n';		
		return returnInfo;
	};
	public void setInfo(byte tmp[])
	{
		int cnt;
		System.out.println("数组初始化！");
//		Info=new byte[tmp.length];
		for(cnt=0;cnt<tmp.length;cnt++)
		{
			Info[cnt]=tmp[cnt];
		}
	};
	public String getHighPress()
	{
		return ""+HighPress;
	}
	
	public String getLowPress()
	{
		return ""+LowPress;
	}
	
	public String getPulse()
	{
		return ""+Pulse;
	}

}
