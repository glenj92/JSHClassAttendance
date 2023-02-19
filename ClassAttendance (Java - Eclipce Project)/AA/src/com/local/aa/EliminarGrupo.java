package com.local.aa;

import java.io.InputStream;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class EliminarGrupo extends Activity {
	
	private ArrayList<Grupos> grup=new ArrayList<Grupos>();
	private AdaptadorGrupos adaptadorGrupos;
	HttpPost httppost;
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpclient;
    InputStream inputStream;
    SharedPreferences prefe ;
    List<NameValuePair> nameValuePairs;
    ProgressDialog dial;
 
   private int ya=0;

   private ListView listViewElimi;
	private ArrayList<String> arrayList;
	private ArrayAdapter<String> adaptador;
	
	private Handler puentegru = new Handler() {
	   	 @Override
	   	 public void handleMessage(Message msg) {
	   	 //Mostramos el mensage recibido del servido en pantalla
	   	// Toast.makeText(GestionGrupo.this, (String)msg.obj, Toast.LENGTH_LONG).show();

	   		 try{
	   		String cad=(String)msg.obj;
	   		if(msg.obj.toString().trim().equals("cxerror")){
	    		AlertDialog.Builder dialogo1 = new AlertDialog.Builder(EliminarGrupo.this);  
		        dialogo1.setTitle("Error de conexion");  
		        dialogo1.setMessage("Requiere de una conexion para trabajar"); 
		      //obligamos al usuario a pulsar los botones para cerrarlo
		        dialogo1.setCancelable(false);  
		        
		       dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {  
		            public void onClick(DialogInterface dialog, int id) {
		            	
		            	dialog.cancel();
		            	finish();
		            	
		            }  
		        });  
		                  
		        dialogo1.show();
	    				 Editor editor11=prefe.edit();
				 	        editor11.putString("checked", "no");
				 	        editor11.commit();
				 	        
	    }else{
	   		
	   		StringTokenizer tok=new StringTokenizer(cad,",");
	   		while(tok.hasMoreTokens()){
	     		String datgru=tok.nextToken();
	     		String datnomMate=tok.nextToken();
	     		grup.add(new Grupos(datgru, datnomMate));
	     		adaptadorGrupos=new AdaptadorGrupos(EliminarGrupo.this, grup);
	     		
	     		//arrayList.add(datgru+"\n"+datnomMate);
	     		
	     	}
	   		 
	   		listViewElimi.setAdapter(adaptadorGrupos);
	   		dial.dismiss();
	   		
	   		
	   		if(grup.size()==0){
	   			AlertDialog.Builder dialogo1 = new AlertDialog.Builder(EliminarGrupo.this);  
		        dialogo1.setTitle("MENSAJE");  
		        dialogo1.setMessage("No hay Grupos para eliminar \n"); 
		      //obligamos al usuario a pulsar los botones para cerrarlo
		        dialogo1.setCancelable(false);  
		        
		       dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {  
		            public void onClick(DialogInterface dialog, int id) {
		            	
		            	dialog.cancel();
		            	finish();
		            }  
		        });  
		                  
		        dialogo1.show(); 
	   		}
	    }
	   		}catch(Exception e){
   			 e.printStackTrace();
   			// Toast.makeText(MainActivity.this, "Error de conexion"+e.toString(), Toast.LENGTH_LONG).show();
   			 dial.dismiss();
   			 
   			 AlertDialog.Builder dialogo1 = new AlertDialog.Builder(EliminarGrupo.this);  
		        dialogo1.setTitle("Error de conexion");  
		        dialogo1.setMessage("Fallo la conexion con el servidor \n"+e.toString()); 
		      //obligamos al usuario a pulsar los botones para cerrarlo
		        dialogo1.setCancelable(false);  
		        
		       dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {  
		            public void onClick(DialogInterface dialog, int id) {
		            	
		            	dialog.cancel();
		            }  
		        });  
		                  
		        dialogo1.show();
		       
		        
   		 }
	   		
	   	 }
	   	 };
	   	 
	   	 
	   	private Handler puenteeligru = new Handler() {
		   	 @Override
		   	 public void handleMessage(Message msg) {
		   		 
		   		try{ 
		   		String cad=(String)msg.obj;
		   		if(msg.obj.toString().trim().equals("cxerror")){
		    		AlertDialog.Builder dialogo1 = new AlertDialog.Builder(EliminarGrupo.this);  
			        dialogo1.setTitle("Error de conexion");  
			        dialogo1.setMessage("Requiere de una conexion para trabajar"); 
			      //obligamos al usuario a pulsar los botones para cerrarlo
			        dialogo1.setCancelable(false);  
			        
			       dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {  
			            public void onClick(DialogInterface dialog, int id) {
			            	
			            	dialog.cancel();
			            	
			            }  
			        });  
			                  
			        dialogo1.show();
		    				 Editor editor11=prefe.edit();
					 	        editor11.putString("checked", "no");
					 	        editor11.commit();
					 	        
		    }else		if(cad.equals("si")){
		   			dial.dismiss();
		   			if(grup.size()==0){
		   				
			   			AlertDialog.Builder dialogo1 = new AlertDialog.Builder(EliminarGrupo.this);  
				        dialogo1.setTitle("MENSAJE");  
				        dialogo1.setMessage("No hay mas Grupos para eliminar \n"); 
				      //obligamos al usuario a pulsar los botones para cerrarlo
				        dialogo1.setCancelable(false);  
				        
				       dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {  
				            public void onClick(DialogInterface dialog, int id) {
				            	
				            	dialog.cancel();
				            	finish();
				            }  
				        });  
				                  
				        dialogo1.show(); 
			   		}
		   		}else{
		   			dial.dismiss();
		   		}
		   		 
		   	
		   		}catch(Exception e){
	    			 e.printStackTrace();
	    			// Toast.makeText(MainActivity.this, "Error de conexion"+e.toString(), Toast.LENGTH_LONG).show();
	    			 dial.dismiss();
	    			 
	    			 AlertDialog.Builder dialogo1 = new AlertDialog.Builder(EliminarGrupo.this);  
	 		        dialogo1.setTitle("Error de conexion");  
	 		        dialogo1.setMessage("Fallo la conexion con el servidor \n"+e.toString()); 
	 		      //obligamos al usuario a pulsar los botones para cerrarlo
	 		        dialogo1.setCancelable(false);  
	 		        
	 		       dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {  
	 		            public void onClick(DialogInterface dialog, int id) {
	 		            	
	 		            	dialog.cancel();
	 		            }  
	 		        });  
	 		                  
	 		        dialogo1.show();
	 		       
	 		        
	    		 }
		   	 }
		};
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eliminar_grupo);
		prefe=getSharedPreferences("datos",Context.MODE_PRIVATE);
		//Instanciamos el listView
				listViewElimi = (ListView) findViewById(R.id.lvEliminarGrupo);

				//Instanciamos el adaptador, le pasamos el arraylist y al listview la pasamos nuestro adapter como adaptador de contenido
				arrayList = new ArrayList<String>();
				adaptador = new ArrayAdapter<String>( this, android.R.layout.simple_list_item_1, arrayList);
				
				//listView.setAdapter(adaptador);

				//Deslizar item para borrarlo
	/*			SwipeListViewTouchListener touchListener =new SwipeListViewTouchListener(listViewElimi,new SwipeListViewTouchListener.OnSwipeCallback() {
					@Override
					public void onSwipeLeft(ListView listView, int [] reverseSortedPositions) {
						//Aqui ponemos lo que hara el programa cuando deslizamos un item ha la izquierda
						//Toast.makeText(MainActivity.this, "elimidano "+listView.getItemAtPosition(reverseSortedPositions[0]).toString(), Toast.LENGTH_LONG).show();
						

						
						//ya=reverseSortedPositions[0];
						final String y=listView.getItemAtPosition(reverseSortedPositions[0]).toString();
						Thread threligru = new Thread(new Runnable() {
							  @Override
							  public void run() {
									
									try{

							     httpclient = new DefaultHttpClient();
							     httppost = new HttpPost("http://"+prefe.getString("ip","")+"/aa/eliminargrupodocente.php");
							     nameValuePairs = new ArrayList<NameValuePair>(2);
								 nameValuePairs.add(new BasicNameValuePair("IdGru" ,y.trim()));
								 httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
							     response = httpclient.execute(httppost);
							     HttpEntity ent = response.getEntity();
							     String text = EntityUtils.toString(ent);
							      
							     Message sms = new Message();
							     sms.obj = text;
							     puenteeligru.sendMessage(sms);
							     
							       }catch(Exception e){
							       	 Toast.makeText(EliminarGrupo.this, "error"+e.toString(), Toast.LENGTH_LONG).show();
							       }	  
							  }
							  });
							 //Arrancamos el Hilo
							 threligru.start();
							 
								arrayList.remove(reverseSortedPositions[0]);
								adaptador.notifyDataSetChanged();
					}

					@Override
					public void onSwipeRight(ListView listView, int [] reverseSortedPositions) {
						//Aqui ponemos lo que hara el programa cuando deslizamos un item ha la derecha
						
						//ya=reverseSortedPositions[0];
						final String y=listView.getItemAtPosition(reverseSortedPositions[0]).toString();
						Thread threligru = new Thread(new Runnable() {
							  @Override
							  public void run() {
									
									try{

							     httpclient = new DefaultHttpClient();
							     httppost = new HttpPost("http://"+prefe.getString("ip","")+"/aa/eliminargrupodocente.php");
							     nameValuePairs = new ArrayList<NameValuePair>(2);
								 nameValuePairs.add(new BasicNameValuePair("IdGru" ,y.trim()));
								 httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
							     response = httpclient.execute(httppost);
							     HttpEntity ent = response.getEntity();
							     String text = EntityUtils.toString(ent);
							      
							     Message sms = new Message();
							     sms.obj = text;
							     puenteeligru.sendMessage(sms);
							     
							       }catch(Exception e){
							       	 Toast.makeText(EliminarGrupo.this, "error"+e.toString(), Toast.LENGTH_LONG).show();
							       }	  
							  }
							  });
							 //Arrancamos el Hilo
							 threligru.start();
						
						arrayList.remove(reverseSortedPositions[0]);
						adaptador.notifyDataSetChanged();
					}
				},true, false);
				
				//Escuchadores del listView
				listViewElimi.setOnTouchListener(touchListener);
				listViewElimi.setOnScrollListener(touchListener.makeScrollListener());
				*/
				
			//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
				dial= ProgressDialog.show(EliminarGrupo.this, "", "Cargando datos...",true);
				dial.setCancelable(true);
				Thread thrgru = new Thread(new Runnable() {
					  @Override
					  public void run() {
			
													
							try{

					       	 httpclient = new DefaultHttpClient();
					              httppost = new HttpPost("http://"+prefe.getString("ip","")+"/aa/grupodocente4.php");
					           nameValuePairs = new ArrayList<NameValuePair>(2);
					         nameValuePairs.add(new BasicNameValuePair("Ano", getDateYears().trim()));
					         nameValuePairs.add(new BasicNameValuePair("IdDoc", prefe.getString("doc","")));
					           httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
					          response = httpclient.execute(httppost);
					     HttpEntity ent = response.getEntity();
					          String text = EntityUtils.toString(ent);
					      
					          Message sms = new Message();
					          sms.obj = text;
					          puentegru.sendMessage(sms);
					     
					                
					          
					       }catch(Exception e){
					    	   e.printStackTrace();
					    	   dial.dismiss();
					    	   Message sms = new Message();
						          sms.obj = "cxerror";
						          puentegru.sendMessage(sms);
					       //	 Toast.makeText(EliminarGrupo.this, "error"+e.toString(), Toast.LENGTH_LONG).show();
					       }
					      
			
						  
					  }
					  });
					 //Arrancamos el Hilo
					 thrgru.start();
					 
			//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
					 
					 listViewElimi.setOnItemClickListener(new OnItemClickListener() {
							@Override
				            public void onItemClick(AdapterView<?> parent, View v, final int posicion, long id) {
								String cad=listViewElimi.getItemAtPosition(posicion).toString();
								StringTokenizer tok=new StringTokenizer(cad);
						   		
						     		final String datgru=grup.get(posicion).getGrupo().toString();
						     	
								
								AlertDialog.Builder builder2 =
					                      new AlertDialog.Builder(EliminarGrupo.this);
					       
					              builder2.setMessage("¿Seguro desea eliminar el Grupo "+datgru+"?")
					              .setTitle("Confirmacion")
					              .setNegativeButton("Cancelar", new DialogInterface.OnClickListener()  {
					                     public void onClick(DialogInterface dialog, int id) {
					                    	 dialog.cancel();
					                     } 
				                     })
				                    	 .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
			 			                     public void onClick(DialogInterface dialog, int id) {
			 			                    	 
			 			                    	final String y=datgru.trim();
			 			                    	dial= ProgressDialog.show(EliminarGrupo.this, "", "Eliminando Grupo...",true);
			 			                    	dial.setCancelable(true);
			 			                    	Thread threligru = new Thread(new Runnable() {
			 										  @Override
			 										  public void run() {
			 												
			 												try{

			 										     httpclient = new DefaultHttpClient();
			 										     httppost = new HttpPost("http://"+prefe.getString("ip","")+"/aa/eliminargrupodocente.php");
			 										     nameValuePairs = new ArrayList<NameValuePair>(2);
			 											 nameValuePairs.add(new BasicNameValuePair("IdGru" ,y.trim()));
			 											 httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			 										     response = httpclient.execute(httppost);
			 										     HttpEntity ent = response.getEntity();
			 										     String text = EntityUtils.toString(ent);
			 										      
			 										     Message sms = new Message();
			 										     sms.obj = text;
			 										     puenteeligru.sendMessage(sms);
			 										     
			 										       }catch(Exception e){
			 										    	   e.printStackTrace();
			 										    	  dial.dismiss();
			 										    	 Message sms = new Message();
				 										     sms.obj = "cxerror";
				 										     puenteeligru.sendMessage(sms);
			 										       }	  
			 										  }
			 										  });
			 										 //Arrancamos el Hilo
			 										 threligru.start();
			 									
			 									grup.remove(listViewElimi.getItemAtPosition(posicion));
			 									adaptadorGrupos.notifyDataSetChanged();
			 			                           
			 			     					
			 			                              
			 			                              
			 			                         }
			 			                     });
				              builder2.create();
					             builder2.show();
								
								
							}
					 });
					 
	}
	
	public String getDateYears()

	{
	    Calendar cal = new GregorianCalendar();
	    Date date = cal.getTime();
	    SimpleDateFormat df = new SimpleDateFormat("yyyy");
	    String formatteDate = df.format(date);
	    return formatteDate;

	}

}
