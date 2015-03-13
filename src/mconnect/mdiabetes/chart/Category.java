package mconnect.mdiabetes.chart;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.renderer.DefaultRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

/**
 * Budget demo pie chart.
 */
public class Category extends AbstractChart {
  /**
   * Returns the chart name.
   * @return the chart name
   */
	private double[] value;
  public String getName() {
    return "Budget chart";
  }
  
  /**
   * Returns the chart description.
   * @return the chart description
   */
  public String getDesc() {
    return "The budget per project for this year (pie chart)";
  }
  
  /**
   * Executes the chart demo.
   * @param context the context
   * @return the built intent
   */
  public Intent execute(Context context) {
    //double[] values = new double[] {12, 14, 11, 10, 19};
    int[] colors = new int[] {Color.BLUE, Color.GREEN, Color.MAGENTA, Color.YELLOW, Color.CYAN,Color.WHITE,Color.RED };
    DefaultRenderer renderer = buildCategoryRenderer(colors);
    
    renderer.setApplyBackgroundColor(true);
    
    //renderer.setAxesColor(Color.RED);//设置坐标轴的颜色
    
    //renderer.setBackgroundColor(Color.WHITE);//设置背景颜色
    
    //renderer.setChartTitle("Test");//设置图表的标题
    
    //renderer.setDisplayChartValues(false);//设置是否显示坐标的值
    
    //renderer.setLabelsColor(Color.YELLOW);//设置标签的颜色
    
    //renderer.setOrientation(Orientation.VERTICAL);//设置x与y轴
    
    //renderer.setShowAxes(false);设置是否显示坐标轴
    
    //renderer.setShowGrid(true);//设置显示水平的网格
    
    //renderer.setShowLegend(true);//设置是否显示下面的文字说明信息
    
    renderer.setShowLabels(false);
    
    //renderer.
    
    renderer.setLabelsColor(Color.WHITE);
    String[] titles={"Pre-Breakfast",
	        "Post-Breakfast",
	        "Pre-Lunch",
	        "Post-Lunch",
	        "Pre-Dinner",
	        "Post-Dinner",
	        "Night"};    
    return ChartFactory.getPieChartIntent(context, buildCategoryDataset("Glucose pie chart", value,titles), renderer,"Glucose pie chart");
  }

  @Override
  public void setXV(double[] v) {
	// TODO Auto-generated method stub
	value=v;
  }

  @Override
  public void setYV(double[] v) {
	// TODO Auto-generated method stub
	
  }



}
