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

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;


/**
 * Scatter demo chart.
 */
public class TodayScatterChart extends AbstractChart {
  /**
   * Returns the chart name.
   * @return the chart name
   */

  private double[] xv;
  private double[] yv;
  public void setXV(double[] v){
	  xv=v;
  }
  public void setYV(double[] v){
	  yv=v;
  }
  public String getName() {
    return "Scatter chart";
  }

  /**
   * Returns the chart description.
   * @return the chart description
   */
  public String getDesc() {
    return "Randomly generated values for the scatter chart";
  }

  /**
   * Executes the chart demo.
   * @param context the context
   * @return the built intent
   */
  public Intent execute(Context context) {
    //String[] titles = new String[] { "Series 1", "Series 2", "Series 3", "Series 4", "Series 5" };
	String[] titles;
	int[] colors;
	PointStyle[] styles;
	titles = new String[] { "Day 1",};
	colors = new int[] { Color.YELLOW,};
	styles = new PointStyle[] { PointStyle.CIRCLE,};
	List<double[]> x = new ArrayList<double[]>();
    List<double[]> values = new ArrayList<double[]>();
    x.add(xv);
    values.add(yv);

    
    XYMultipleSeriesRenderer renderer = buildRenderer(colors, styles);
    setChartSettings(renderer, "最近一天血压分布", "时间", "血压值(mmHg)", 0.5, 7.5, 0, 300, Color.GREEN,
        Color.GREEN);
    renderer.setXLabels(1);//表示横坐标被分为多少等分
	
	renderer.setYLabels(12);//表示纵坐标被分为多少等分
	renderer.addTextLabel(1, "00:00");
    renderer.addTextLabel(2, "04:00");
    renderer.addTextLabel(3, "08:00");
    renderer.addTextLabel(4, "12:00");
    renderer.addTextLabel(5, "16:00");
    renderer.addTextLabel(6, "20:00");
    renderer.addTextLabel(7, "24:00");
    renderer.setDisplayChartValues(true);
    renderer.setAxesColor(Color.RED);
    renderer.setDisplayChartValues(true);
    renderer.setShowGrid(true);//设置显示水平的网格
    
    renderer.setShowLegend(false);
    
    for (int i = 0; i < 1; i++) {
      ((XYSeriesRenderer) renderer.getSeriesRendererAt(i)).setFillPoints(true);
    }
    return ChartFactory.getScatterChartIntent(context, buildDataset(titles, x, values), renderer);
  }

}
