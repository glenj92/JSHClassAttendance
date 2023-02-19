package com.local.aa;

import org.achartengine.ChartFactory;
import org.achartengine.model.CategorySeries;
import org.achartengine.renderer.DefaultRenderer;
import org.achartengine.renderer.SimpleSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;



public class GraficaPie {
	
	private int[] colors;
	public Intent getinIntent(Context context,double asis,double excu, double fall,String nombre){

	CategorySeries series = new CategorySeries("Pie Graph");
	//int k = 0;
//	for (int value : values) {
		//series.add("Section "+ ++k, value);
	//double i=;
	// int c=(int) Math.pow(100,0);
	double suma=asis+excu+fall;
	
	double t1=Math.rint(asis*100)/suma;
	
	double i1=Math.rint(excu*100)/suma;
	double i2=Math.rint(fall*100)/suma;
	
	
	
		  //numero de asitencias
	if(asis!=0&&excu!=0&&fall!=0){
		series.add("Asistencia ("+ (double)Math.round(t1)+"%)  ",asis);
		series.add("Excusa ("+(double)Math.round(i1)+"%)  ", excu);
		series.add("Falla ("+(double)Math.round(i2)+"%)  ", fall);
	colors = new int[] { Color.parseColor("#33B5E5"), Color.parseColor("#AA66CC"), 
			Color.parseColor("#FA5882")}; 
	
	}else if(asis!=0&&excu!=0){
			series.add("Asistencia ("+ (double)Math.round(t1)+"%)  ",asis);
			series.add("Excusa ("+(double)Math.round(i1)+"%)  ", excu);
			colors = new int[] { Color.parseColor("#33B5E5"), Color.parseColor("#AA66CC")}; 
			}else if(fall!=0&&excu!=0){
				series.add("Excusa ("+(double)Math.round(i1)+"%)  ", excu);
				series.add("Falla ("+(double)Math.round(i2)+"%)  ", fall);
				colors = new int[] {Color.parseColor("#AA66CC"), Color.parseColor("#FA5882")};  
				
			}else if(asis!=0&&fall!=0){
				series.add("Asistencia ("+ (double)Math.round(t1)+"%)  ",asis);
				series.add("Falla ("+(double)Math.round(i2)+"%)  ", fall);
				colors = new int[] { Color.parseColor("#33B5E5"),Color.parseColor("#FA5882")}; 
				
			}else if(excu!=0){
				series.add("Excusa ("+(double)Math.round(i1)+"%)  ", excu);	
				colors = new int[] {Color.parseColor("#AA66CC")}; 
			}else if(fall!=0){
				//numero de excusas
				series.add("Falla ("+(double)Math.round(i2)+"%)  ", fall);
				colors = new int[] {Color.parseColor("#FA5882")}; 
			}else if(asis!=0){
				//numero de excusas
				series.add("Asistencia ("+ (double)Math.round(t1)+"%)  ",asis);
				colors = new int[] { Color.parseColor("#33B5E5")}; 
				
			}
	
	
	
		
		
		//numero de fallas
		
//	}

	/*	series.add("Asistencias ", 5);
		series.add("Excusas ", 1);
		series.add("Fallas ", 2);
	
*/
/*	colors = new int[] { Color.parseColor("#33B5E5"), Color.parseColor("#AA66CC"), 
			Color.parseColor("#FA5882")};   //colore de las diviciones de Tortas
	*/
	DefaultRenderer renderer = new DefaultRenderer();
	for (int color : colors) {
		SimpleSeriesRenderer r = new SimpleSeriesRenderer();
		r.setColor(color);
		
	
		renderer.addSeriesRenderer(r);
	}
	renderer.setChartTitle(nombre); //titulo
	renderer.setChartTitleTextSize(60); //tamaño del titulo
	renderer.setLabelsColor(Color.WHITE);
	renderer.setLabelsTextSize(40);
	renderer.setLegendTextSize(25);
	renderer.setShowLegend(true);
	renderer.setFitLegend(true);
	//renderer.setZoomButtonsVisible(true); //activar zoom
	renderer.setZoomEnabled(true);
	
	renderer.setApplyBackgroundColor(true);
	renderer.setLegendHeight(500);
	renderer.setAntialiasing(true);
	
	renderer.setBackgroundColor(Color.parseColor("#1C1C1C"));
	
	renderer.setMargins(new int[]{0,0,0,0});
	
	
	

	Intent intent = ChartFactory.getPieChartIntent(context, series, renderer, "Asistencias de "+nombre);
	return intent;
	}
	
}
