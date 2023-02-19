package com.local.aa;

import java.util.ArrayList;
import java.util.List;

import org.achartengine.ChartFactory;
import org.achartengine.chart.BarChart.Type;
import org.achartengine.model.CategorySeries;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

public class BarraGrp {


	private String[] asis,exc,falla;
	private String[] id,nom,ape;
	
	public Intent getIntent(Context context,List<String>nide,List<String>nnom,List<String>nape, List<String> nasi, List<String> nexc, List<String> nfal,String datoGrupo) 
	{	
		// Bar 1
		asis = nasi.toArray(new String[nasi.size()]);
		CategorySeries series = new CategorySeries("Asistencias ");
		for (int i = 0; i < asis.length; i++) {
			series.add("Bar "+i, Integer.parseInt(asis[i]));
		}
		
		// Bar 2
		exc = nexc.toArray(new String[nexc.size()]);
		CategorySeries series2 = new CategorySeries("Excusas ");
		for (int i = 0; i < asis.length; i++) {
			series2.add("Bar "+i, Integer.parseInt(exc[i]));
		}
		
		// Bar 2
		falla = nfal.toArray(new String[nfal.size()]);
				CategorySeries series3 = new CategorySeries("Fallas ");
				for (int i = 0; i < asis.length; i++) {
					series3.add("Bar "+i, Integer.parseInt(falla[i]));
				}
				
				
				/*
				CategorySeries series = new CategorySeries("Asistencias");
				CategorySeries series2 = new CategorySeries("Excusas");
				CategorySeries series3 = new CategorySeries("Fallas");
				//for(int i=0;i<Dato_Gneral.size();i++){
					series.add("",Integer.parseInt(Dato_Gneral.get(0).getAsiste().toString().trim()));
					series2.add("",Integer.parseInt(Dato_Gneral.get(0).getExusa().toString().trim()));
					series3.add("",Integer.parseInt(Dato_Gneral.get(0).getFallas().toString().trim()));
				//}
				
		*/
		XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();
		dataset.addSeries(series.toXYSeries());
		dataset.addSeries(series2.toXYSeries());
		dataset.addSeries(series3.toXYSeries());

		// This is how the "Graph" itself will look like
		XYMultipleSeriesRenderer mRenderer = new XYMultipleSeriesRenderer();
		mRenderer.setChartTitle("GRUPO DE CLASE "+datoGrupo);
		mRenderer.setChartTitleTextSize(30);
	//	mRenderer.setXTitle("ESTUDIANTES");
		mRenderer.setYTitle("DIAS");
		mRenderer.setAxesColor(Color.WHITE);
	    mRenderer.setLabelsColor(Color.YELLOW);
	    mRenderer.setZoomButtonsVisible(false);
	   
	    mRenderer.setLegendTextSize(20);
		   mRenderer.setLegendHeight(100);
		  mRenderer.setXLabelsPadding(15);
		  mRenderer.setYLabelsPadding(20);
		 // mRenderer.setBarWidth(30);
		    mRenderer.setLabelsTextSize(17);
	    
	    
	    mRenderer.setApplyBackgroundColor(true);
	    mRenderer.setBackgroundColor(Color.parseColor("#1C1C1C"));
	   // mRenderer.setAntialiasing(true);
	    mRenderer.setAxisTitleTextSize(12);
	   // mRenderer.setAntialiasing(true);
	    mRenderer.setBarSpacing(0.5);
	    nom = nnom.toArray(new String[nnom.size()]);
	    ape= nape.toArray(new String[nape.size()]);
	    for(int i=0;i<asis.length;i++){
		    mRenderer.addXTextLabel(i+1,nom[i]+" "+ape[i]);
		    }
	    //mRenderer.setXRoundedLabels(true);
	   // mRenderer.setFitLegend(true);
	    // Customize bar 1
		XYSeriesRenderer renderer = new XYSeriesRenderer();
		renderer.setColor(Color.parseColor("#33B5E5"));
	    renderer.setDisplayChartValues(true);
	    renderer.setChartValuesSpacing((float) 0.5);
	    renderer.setChartValuesTextSize(17);
	    mRenderer.addSeriesRenderer(renderer);
	    // Customize bar 2
	    XYSeriesRenderer renderer2 = new XYSeriesRenderer();
	    renderer2.setColor(Color.parseColor("#AA66CC"));
	    renderer2.setDisplayChartValues(true);
	    renderer2.setChartValuesSpacing((float) 0.5);
	    renderer2.setChartValuesTextSize(17);
	    mRenderer.addSeriesRenderer(renderer2);
	    
	    XYSeriesRenderer renderer3 = new XYSeriesRenderer();
	    renderer3.setColor(Color.parseColor("#FA5882"));
	    renderer3.setDisplayChartValues(true);
	    renderer3.setChartValuesSpacing((float) 0.5);
	    renderer3.setChartValuesTextSize(17);
	    mRenderer.addSeriesRenderer(renderer3);
	    
		Intent intent = ChartFactory.getBarChartIntent(context, dataset,mRenderer, Type.DEFAULT);
		return intent;
	}

}
