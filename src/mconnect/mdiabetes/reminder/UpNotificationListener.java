package mconnect.mdiabetes.reminder;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class UpNotificationListener extends BroadcastReceiver
{
  @Override
  public void onReceive(Context context, Intent intent)
  {
    // TODO Auto-generated method stub
    Intent i = new Intent(context, MyService.class);

    
    context.startService(i);
    
  }
}
