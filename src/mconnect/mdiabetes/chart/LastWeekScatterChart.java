/**
 * Copyright (C) 2009 SC 4ViewSoft SRL
 *  
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *  
 *      http://www.apache.org/licenses/LICENSE-2.0
 *  
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package mconnect.mdiabetes.chart;

import java.util.ArrayList;


import java.util.List;

import mconnect.mdiabetes.date.CLastWeek;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;


import android.content.Context;
import android.content.Intent;

import android.graphics.Color;



/**
 * Average temperature demo chart.
 */
public class LastWeekScatterChart extends AbstractChart {
  /**
   * Returns the chart name.
   * @return the chart name
   */
	
	private int _y,_m,_d;
	public LastWeekScatterChart(int y,int m,int d){
		this._y=y;
		this._m=m;
		this._d=d;
	}

  private double[] xv;
  private double[] yv;
  
  public void setXV(double[] v){
	  xv=v;
  }
  public void setYV(double[] v){
	  yv=v;
  }
  public String getName() {
    return "Average temperature";
  }
  
  /**
   * Returns the chart description.
   * @return the chart description
   */
 
  public String getDesc() {
    return "The average temperature in 4 Greek islands (line chart)";
  }
  
  /**
   * Executes the chart demo.
   * @param context the context
   * @return the built intent
   */
  public Intent execute(Context context) {

	String[] titles;
	int[] colors;
	PointStyle[] styles;
	titles = new String[] { "Day 1",};
    colors = new int[] { Color.YELLOW,};
    styles = new PointStyle[] { PointStyle.CIRCLE,};
    List<double[]> x = new ArrayList<double[]>();
    x.add(xv);
    
    List<double[]> values = new ArrayList<double[]>();
    values.add(yv);
    XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
    
    for (int i = 0; i < 1; i++) {
      ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
    }
    
    setChartSettings(renderer, "过去一周的血压分布", "时间", "血压值(mmHg)", 0.5, 8.5, 0, 300,
    	    Color.GREEN, Color.GREEN);
    	renderer.setXLabels(1);//表示横坐标被分为多少等分
    	CLastWeek clw=new CLastWeek();
    	String []we=new String[8];
    	we=clw.getWeek(_y,_m,_d);
    	/*for(int i=0;i<8;i++){
    		System.out.println(we[i]);
    	}*/
    	
    	
    	renderer.setYLabels(12);//表示纵坐标被分为多少等分
    	for(int i=0;i<8;i++)
    	{
    		renderer.addTextLabel(i+1, we[i]);
    	}
    	
        renderer.setDisplayChartValues(true);
        renderer.setAntialiasing(true);//no
        
        renderer.setApplyBackgroundColor(true);
        
        renderer.setAxesColor(Color.RED);//设置坐标轴的颜色
        
        renderer.setDisplayChartValues(true);//设置是否显示坐标的值
        
        renderer.setShowGrid(true);//设置显示水平的网格
        
        renderer.setShowLegend(false);//设置是否显示下面的文字说明信息
        
        
    	    
    	Intent intent = ChartFactory.getScatterChartIntent(context, buildDataset(titles, x, values),
    	    renderer, "");
    return intent;
      
    
  }

}
