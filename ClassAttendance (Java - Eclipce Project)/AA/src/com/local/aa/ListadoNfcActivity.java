package com.local.aa;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
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



import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.widget.AbsListView.MultiChoiceModeListener;
import android.widget.AdapterView.OnItemClickListener;

public class ListadoNfcActivity extends Activity implements OnQueryTextListener{

	private AdaptadorEstuNFC mSelecionAdapterNFC;
	private SearchView mSearchView;
	private MenuItem saveItem;
	private int cuenta,cuenta1,cuentaerror,totalacciones,conteo,totalconteo, cuentafallas;
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		//return super.onCreateOptionsMenu(menu);
		getMenuInflater().inflate(R.menu.menu_nfc, menu);
		
		MenuItem searchItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) searchItem.getActionView();
        mSearchView.setQueryHint("Search…");
        mSearchView.setOnQueryTextListener(this);
        
        saveItem = menu.findItem(R.id.action_guardar);
       // saveItem.setEnabled(false);
        
        return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch(item.getItemId()){
		
		case R.id.action_guardar:
			if(vfnfc.getDisplayedChild()==0){
			// Toast.makeText(this, "buscar", Toast.LENGTH_SHORT).show();
			
			if(pers.size()==0){
				AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);  
			        dialogo1.setTitle("MENSAJE");  
			        dialogo1.setMessage("El listado esta vacio \n"); 
			      //obligamos al usuario a pulsar los botones para cerrarlo
			        dialogo1.setCancelable(false);  
			        
			       dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {  
			            public void onClick(DialogInterface dialog, int id) {
			            	
			            	dialog.cancel();
			            }  
			        });  
			                  
			        dialogo1.show(); 
			}else{
				alista2nfc.removeAll(alista2nfc);
				alistanfc.removeAll(alistanfc);
				aanfc.notifyDataSetChanged();
				aa2nfc.notifyDataSetChanged();
			dial= ProgressDialog.show(ListadoNfcActivity.this, "", "Cargando datos...",true);
			dial.setCancelable(true);
				Thread thrmate = new Thread(new Runnable() {
	 			  @Override
	 			  public void run() {
	 											
	 					try{
	 			       	 //Utilizamos la clase Httpclient para conectar
	 			       	 httpclient = new DefaultHttpClient();
	 			       	//Utilizamos la HttpPost para enviar lso datos
	 			       	    //A la url donde se encuentre nuestro archivo receptor
	 			              httppost = new HttpPost("http://"+prefe.getString("ip","")+"/aa/materiadocente.php");
	 			         //Añadimos los datos a enviar 
	 			           nameValuePairs = new ArrayList<NameValuePair>(2);
	 			      // Añadimos los elementos a la lista
	 			         nameValuePairs.add(new BasicNameValuePair("Idd", prefe.getString("doc","").trim()));
	 			        nameValuePairs.add(new BasicNameValuePair("Ano", getDateYears().trim()));
	 			         //Encapsulamos
	 			           httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	 			         //Lanzamos la petición
	 			           //eviaa datos a l doc PHP y compila el codigo
	 			          response = httpclient.execute(httppost);
	 			           //  Obtenemos el contenido de la respuesta
	 			        //Conectamos para recibir datos de respuesta
	 			          HttpEntity ent = response.getEntity();
	 			          String text = EntityUtils.toString(ent);
	 						         
	 			        //Enviamos el resultado LIMPIO al Handler para mostrarlo
	 			          Message sms = new Message();
	 			          sms.obj = text;
	 			          puentemate.sendMessage(sms);
	 			    		 

	 			                
	 			          
	 			       }catch(Exception e){
	 			    	   e.printStackTrace();
	 			    	  dial.dismiss();
	 			    	 Message sms = new Message();
	 			          sms.obj = "cxerror";
	 			          puentemate.sendMessage(sms);
	 			          }
	 				
	 			      
	 				  
	 			  }
	 			  });
	 			 //Arrancamos el Hilo
	 			 thrmate.start();
 			
			}
}else{
			
			AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);  
	        dialogo1.setTitle("ALERTA");  
	        dialogo1.setMessage("Opcion no valida en esta vista"); 
	      //obligamos al usuario a pulsar los botones para cerrarlo
	        dialogo1.setCancelable(false);  
	        
	       dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {  
	            public void onClick(DialogInterface dialog, int id) {
	            	
	            	dialog.cancel();
	            }  
	        });  
	                  
	        dialogo1.show();
		
	}
			break;
			
		case R.id.action_darporvista:
			if(vfnfc.getDisplayedChild()==0){
			dial= ProgressDialog.show(ListadoNfcActivity.this, "", "Cargando datos...",true);
			dial.setCancelable(true);
			Thread thrmate = new Thread(new Runnable() {
 			  @Override
 			  public void run() {
 											
 					try{
 			       	 //Utilizamos la clase Httpclient para conectar
 						 httpclient = new DefaultHttpClient();
 	 			       	//Utilizamos la HttpPost para enviar lso datos
 	 			       	    //A la url donde se encuentre nuestro archivo receptor
 	 			              httppost = new HttpPost("http://"+prefe.getString("ip","")+"/aa/materiadocente.php");
 	 			         //Añadimos los datos a enviar 
 	 			           nameValuePairs = new ArrayList<NameValuePair>(2);
 	 			      // Añadimos los elementos a la lista
 	 			         nameValuePairs.add(new BasicNameValuePair("Idd", prefe.getString("doc","").trim()));
 	 			        nameValuePairs.add(new BasicNameValuePair("Ano", getDateYears().trim()));
 	 			         //Encapsulamos
 	 			           httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
 	 			         //Lanzamos la petición
 	 			           //eviaa datos a l doc PHP y compila el codigo
 	 			          response = httpclient.execute(httppost);
 	 			           //  Obtenemos el contenido de la respuesta
 	 			        //Conectamos para recibir datos de respuesta
 	 			          HttpEntity ent = response.getEntity();
 	 			          String text = EntityUtils.toString(ent);
 						         
 			        //Enviamos el resultado LIMPIO al Handler para mostrarlo
 			          Message sms = new Message();
 			          sms.obj = text;
 			          puentedarporvista.sendMessage(sms);
 			    		 

 			                
 			          
 			       }catch(Exception e){
 			    	   e.printStackTrace();
 			    	  dial.dismiss();
 			    	 Message sms = new Message();
			          sms.obj = "cxerror";
			          puentedarporvista.sendMessage(sms);
			          }
 				
 			      
 				  
 			  }
 			  });
 			 //Arrancamos el Hilo
 			 thrmate.start();
			}else{
				
				AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);  
		        dialogo1.setTitle("ALERTA");  
		        dialogo1.setMessage("Opcion no valida en esta vista"); 
		      //obligamos al usuario a pulsar los botones para cerrarlo
		        dialogo1.setCancelable(false);  
		        
		       dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {  
		            public void onClick(DialogInterface dialog, int id) {
		            	
		            	dialog.cancel();
		            }  
		        });  
		                  
		        dialogo1.show();
			
		}
			
			break;
		
		case R.id.atraznfc:
			if(vfnfc.getDisplayedChild()==1){
				vfnfc.setInAnimation(inFromLeftAnimation());
				vfnfc.setOutAnimation(outToRightAnimation());
				vfnfc.setDisplayedChild(0);
			}else{
				
				AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);  
		        dialogo1.setTitle("ALERTA");  
		        dialogo1.setMessage("Opcion no valida en esta vista"); 
		      //obligamos al usuario a pulsar los botones para cerrarlo
		        dialogo1.setCancelable(false);  
		        
		       dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {  
		            public void onClick(DialogInterface dialog, int id) {
		            	
		            	dialog.cancel();
		            }  
		        });  
		                  
		        dialogo1.show();
			
		}
			break;
		
		
			default:
		}
		
		
		return super.onOptionsItemSelected(item);
	}

	private ArrayList<EstuNFC> estuBuscar=new ArrayList<EstuNFC>();
	
	private static String TAG;
	NfcAdapter adapter;
    PendingIntent pendingIntent;
    IntentFilter readTagFilters[];
    boolean readMode;
    Tag myTag;
    Context context;
    ArrayList<Persona> pers = new ArrayList<Persona>();
	//Se crea una objeto tipo ListView
	private ListView lstpersonanfc;
	//Se crea un objeto de tipo AdaptadorDias
	AdaptadorPersona adaptador;
	private int cont=0,contBuscar=0;
	private String idmate,nommate,d,datoGrupo;
	private Button btnGuardarnfc,bt1buscarNfc;
	
	  HttpPost httppost;
	    StringBuffer buffer;
	    HttpResponse response;
	    HttpClient httpclient;
	    InputStream inputStream;
	    SharedPreferences prefe,prefedoc ;
	    List<NameValuePair> nameValuePairs;
	    String[] itemsmate;
	    List<String> itemsMate=new ArrayList<String>();
	    //List<String> itemsGru=new ArrayList<String>();
	    ProgressDialog dial;
	    
	    ArrayList<String> alistanfc,alista2nfc;
		 ArrayAdapter<String> aanfc,aa2nfc;

		private ListView lv1manfc,lv2grunfc,lv3buscarNfc;
		private ViewFlipper vfnfc;
		private TextView et1buscarNfc,textoNresultados;
		float init_x,init_y;
	    
	  //Creamos el handler puente para mostrar
		  //el mensaje recibido del servidor
		
		 private Handler puentedarporvista = new Handler() {
	    	 @Override
	    	 public void handleMessage(Message msg) {
	    		 try{
	    		 List<String> itemsMate=new ArrayList<String>();
	      		String cad=(String)msg.obj;
	      		if(msg.obj.toString().trim().equals("cxerror")){
	        		AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListadoNfcActivity.this);  
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
	        				 
	    			 	        
	        }else	if(cad.toString().equals("")){
	       			dial.dismiss();
	       			AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListadoNfcActivity.this);  
	    	        dialogo1.setTitle("MENSAJE");  
	    	        dialogo1.setMessage("No tiene Grupos registrados\n¿Desea registrar un nuevo Grupo?"); 
	    	      //obligamos al usuario a pulsar los botones para cerrarlo
	    	        dialogo1.setCancelable(false);  
	    	        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {  
	    	            public void onClick(DialogInterface dialog, int id) {
	    	            	
	    	            	dialog.cancel();
	    	            	finish();
	    	            }  
	    	        }); 
	    	        
	    	       dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {  
	    	            public void onClick(DialogInterface dialog, int id) {
	    	            	
	    	            	dialog.cancel();
	    	            	Intent i=new Intent(ListadoNfcActivity.this, RegistroGrupo.class);
	    					startActivity(i);
	    	            	finish();
	    	            }  
	    	        });  
	    	                  
	    	        dialogo1.show(); 
	       			
	       		}else{
	      		StringTokenizer tok=new StringTokenizer(cad,",");
	        		while(tok.hasMoreTokens()){
	        			itemsMate.add(tok.nextToken().trim());
	        		}
	        		final String[] itemsmate;
	        		itemsmate=itemsMate.toArray(new String[itemsMate.size()]);
	        		dial.dismiss();
	      		 AlertDialog.Builder builder = new AlertDialog.Builder(ListadoNfcActivity.this);
	       		builder.setTitle("Selección")
	       	      .setSingleChoiceItems(itemsmate, -1,
	       	               new DialogInterface.OnClickListener() {
	       	            public void onClick(DialogInterface dialog, final int item) {
	       	            	

			            	StringTokenizer tok1=new StringTokenizer(itemsmate[item],"-");
			          		while(tok1.hasMoreTokens()){
			          		//	Toast.makeText(MainActivity.this, tok1.nextToken().trim(), Toast.LENGTH_LONG).show();
			            	
			            	d=tok1.nextToken();
			            	tok1.nextToken();
			          		}
			            	dial= ProgressDialog.show(ListadoNfcActivity.this, "", "Cargando datos...",true);
			            	dial.setCancelable(true);
			          	   	 Thread thrgru = new Thread(new Runnable() {
			          				  @Override
			          				  public void run() {
			          		
			          												
			          						try{

			          				       	 httpclient = new DefaultHttpClient();
			          				              httppost = new HttpPost("http://"+prefe.getString("ip","")+"/aa/grupoxmate.php");
			          				           nameValuePairs = new ArrayList<NameValuePair>(2);
			          				         nameValuePairs.add(new BasicNameValuePair("Idd", prefe.getString("doc","")));
			          				         nameValuePairs.add(new BasicNameValuePair("Ano", getDateYears().trim()));
			          				       nameValuePairs.add(new BasicNameValuePair("Mate", d.trim()));
			          				           httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			          				          response = httpclient.execute(httppost);
			          				     HttpEntity ent = response.getEntity();
			          				          String text = EntityUtils.toString(ent);
			          				      
			          				          Message sms = new Message();
			          				          sms.obj = text;
			          				          puentegrudarporvisto.sendMessage(sms);
			          				     
			          				                
			          				          
			          				       }catch(Exception e){
			          				    	   e.printStackTrace();
			          				    	 dial.dismiss();
			          				    	Message sms = new Message();
			          				          sms.obj = "cxerror";
			          				          puentegrudarporvisto.sendMessage(sms);
			          				          }
			          				      
			          		
			          					  
			          				  }
			          				  });
			          				 //Arrancamos el Hilo
			          				 thrgru.start();
	      	          		dialog.cancel();
	       	            	
	       	            }
	       	      });
	       		builder.create(); 
	     	      builder.show(); 
	       		}
	      		
	    		 }catch(Exception e){
	       			 e.printStackTrace();
	       			 
	       			// Toast.makeText(MainActivity.this, "Error de conexion"+e.toString(), Toast.LENGTH_LONG).show();
	       			 dial.dismiss();
	       			 
	       			 AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListadoNfcActivity.this);  
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
	    	
		
	    	
	    	 private Handler puentegrudarporvisto = new Handler() {
		    	 @Override
		    	 public void handleMessage(Message msg) {
		    		try{
		    		 List<String> itemsGru=new ArrayList<String>();
		    	   		String cad=(String)msg.obj;
		    	   		if(msg.obj.toString().trim().equals("cxerror")){
		    	    		AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListadoNfcActivity.this);  
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
		    	    				 
		    				 	        
		    	    }else{
		    	   		StringTokenizer tok=new StringTokenizer(cad,",");
		    	   		while(tok.hasMoreTokens()){
		    	     		String datgru=tok.nextToken();
		    	     		itemsGru.add(datgru);
		    	     	}
		    	   		final String[] itemsgru;
		    	   		itemsgru=itemsGru.toArray(new String[itemsGru.size()]);
		    	   		dial.dismiss();
		    	   		
		    	   	 AlertDialog.Builder builder = new AlertDialog.Builder(ListadoNfcActivity.this);
		    			builder.setTitle("Selección")
		    		      .setSingleChoiceItems(itemsgru, -1,
		    		               new DialogInterface.OnClickListener() {
		    		            public void onClick(DialogInterface dialog, final int item) {
		    		            	
		    		            	final String dat1=itemsgru[item].toString();
		    		            	dial= ProgressDialog.show(ListadoNfcActivity.this, "", "Guardando fallas...",true);
		    		            	dial.setCancelable(true);
		    		            	Thread thr = new Thread(new Runnable() {
		    							  @Override
		    							  public void run() {
		    							  //Enviamos el texto escrito a la funcion
		    								  
		    									try{
		    							       	 //Utilizamos la clase Httpclient para conectar
		    							       	 httpclient = new DefaultHttpClient();
		    							       	//Utilizamos la HttpPost para enviar lso datos
		    							       	    //A la url donde se encuentre nuestro archivo receptor
		    							              httppost = new HttpPost("http://"+prefe.getString("ip","")+"/aa/darclaseporvista.php");
		    							         //Añadimos los datos a enviar en este caso solo tres
		    							           //que les llamamos de nombre 'id','user','pass'.
		    							           //La segunda linea podría repetirse tantas veces como queramos
		    							           //siempre cambiando el nombre ('id','user','pass')
		    							           nameValuePairs = new ArrayList<NameValuePair>(2);
		    							      // Añadimos los elementos a la lista
		    							         
		    							             nameValuePairs.add(new BasicNameValuePair("Grupo", dat1.trim()));
		    								         nameValuePairs.add(new BasicNameValuePair("Fecha", getDatePhone().trim()));
		    								         
		    							        
		    								           httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		    								         //Lanzamos la petición
		    								           //eviaa datos a l doc PHP y compila el codigo
		    								          response = httpclient.execute(httppost);
		    							           							         
		    							         
		    							           //  Obtenemos el contenido de la respuesta
		    							        //Conectamos para recibir datos de respuesta
		    							          HttpEntity ent = response.getEntity();
		    							          String text = EntityUtils.toString(ent);
		    							        //Enviamos el resultado LIMPIO al Handler para mostrarlo
		    							          Message sms = new Message();
		    							          sms.obj = text;
		    							          puentedar.sendMessage(sms);
		    							          
		    							                
		    					 							          
		    							       }catch(Exception e){
		    							    	   e.printStackTrace();
		    							    	   dial.dismiss();
		    							    	   Message sms = new Message();
			    							          sms.obj = "cxerror";
			    							          puentedar.sendMessage(sms);
			    							          }
		    							      
		    									
		    									
		    										}

		    							  });
		    							 //Arrancamos el Hilo
		    							 thr.start();
		    		            	
		    		            	
		    		            	dialog.cancel();
		    		            	
		    		            }
		    		      });
		    			 builder.create(); 
		    		      builder.show(); 
		    	    }
		    		}catch(Exception e){
		      			 e.printStackTrace();
		      			// Toast.makeText(MainActivity.this, "Error de conexion"+e.toString(), Toast.LENGTH_LONG).show();
		      			 dial.dismiss();
		      			 
		      			 AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListadoNfcActivity.this);  
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
	    	
		
		    	   private Handler puentedar = new Handler() {
				    	 @Override
				    	 public void handleMessage(Message msg) {
				    		 try{
				    		 
					    		String cad=(String)msg.obj;
					    		if(msg.obj.toString().trim().equals("nofecha")){
					    			
					    			AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListadoNfcActivity.this);  
					    	        dialogo1.setTitle("Alerta");  
					    	        dialogo1.setMessage("La fecha del dispositivo no concuerdan con la del servidor\nConfigure bien la fecha de su telefono"); 
					    	      //obligamos al usuario a pulsar los botones para cerrarlo
					    	        dialogo1.setCancelable(false);  
					    	        
					    	       dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {  
					    	            public void onClick(DialogInterface dialog, int id) {
					    	            	
					    	            	dialog.cancel();
					    	            	
					    	            }  
					    	        });  
					    	                  
					    	        dialogo1.show();
					    			
					    		}else if(msg.obj.toString().trim().equals("cxerror")){
					        		AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListadoNfcActivity.this);  
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
					        				 
					    			 	        
					        }else{
					    		dial.dismiss();
					    		AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListadoNfcActivity.this);  
						        dialogo1.setTitle("Informe");  
						        dialogo1.setMessage(cad); 
						      //obligamos al usuario a pulsar los botones para cerrarlo
						        dialogo1.setCancelable(false);  
						        
						       dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {  
						            public void onClick(DialogInterface dialog, int id) {
						            	
						            	dialog.cancel();
						            }  
						        });  
						                  
						        dialogo1.show(); 
					      			
					        }	
				    		 }catch(Exception e){
				       			 e.printStackTrace();
				       			// Toast.makeText(MainActivity.this, "Error de conexion"+e.toString(), Toast.LENGTH_LONG).show();
				       			 dial.dismiss();
				       			 
				       			 AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListadoNfcActivity.this);  
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
				    	
				    	private Handler puente2 = new Handler() {
					    	 @Override
					    	 public void handleMessage(Message msg) {
					    		 try{
					    		 
						    		String cad=(String)msg.obj;
						    		if(msg.obj.toString().trim().equals("cxerror")){
						        		AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListadoNfcActivity.this);  
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
						        				 
						    			 	        
						        }else{
						    		if(cad.equals("exito")){
							    		
							    		totalconteo++;
							    			
							    		}else if(cad.equals("error")){
							    			totalconteo++;
							    		}else{
							    			totalconteo++;
							    		}
						    		if(totalconteo==conteo){
						    			dial.dismiss();
						    			 AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListadoNfcActivity.this);  
									        dialogo1.setTitle("EXITO");  
									        dialogo1.setMessage("Exito al Guardar"); 
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
					       			 
					       			 AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListadoNfcActivity.this);  
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
				    	
				    	  private Handler puente = new Handler() {
						    	 @Override
						    	 public void handleMessage(Message msg) {
						    		 try{
							    		String cad=(String)msg.obj;
							    		if(msg.obj.toString().trim().equals("nofecha")){
							    			totalacciones++;
										    if(totalacciones==cuenta){
							    			dial.dismiss();
							    			AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListadoNfcActivity.this);  
							    	        dialogo1.setTitle("Alerta");  
							    	        dialogo1.setMessage("La fecha del dispositivo no concuerdan con la del servidor\nConfigure bien la fecha de su telefono"); 
							    	      //obligamos al usuario a pulsar los botones para cerrarlo
							    	        dialogo1.setCancelable(false);  
							    	        
							    	       dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {  
							    	            public void onClick(DialogInterface dialog, int id) {
							    	            	
							    	            	dialog.cancel();
							    	            	
							    	            }  
							    	        });  
							    	                  
							    	        dialogo1.show();
										    }
							    			
							    		}else if(cad.equals("fal")){
							    	dial.dismiss();
							    	AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListadoNfcActivity.this);  
				    		        dialogo1.setTitle("MENSAJE");  
				    		        dialogo1.setMessage("Para colocar fallas a todos los estudiantes\nUtilice la opcion: Dar Clase por Vista "); 
				    		      //obligamos al usuario a pulsar los botones para cerrarlo
				    		        dialogo1.setCancelable(false);  
				    		        
				    		       dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {  
				    		            public void onClick(DialogInterface dialog, int id) {
				    		            	
				    		            	dialog.cancel();
				    		            	
				    		            }  
				    		        });  
				    		                  
				    		        dialogo1.show();
				    		        
							    }else  if(cad.equals("cxerror")){
							    	AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListadoNfcActivity.this);  
				    		        dialogo1.setTitle("Error de conexion");  
				    		        dialogo1.setMessage("Requiere de una conexion para trabajar"); 
				    		      //obligamos al usuario a pulsar los botones para cerrarlo
				    		        dialogo1.setCancelable(false);  
				    		        
				    		       dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {  
				    		            public void onClick(DialogInterface dialog, int id) {
				    		            	
				    		            	dialog.cancel();
				    		            	vfnfc.setInAnimation(inFromLeftAnimation());
				    						vfnfc.setOutAnimation(outToRightAnimation());
				    						vfnfc.setDisplayedChild(0);
				    		            }  
				    		        });  
				    		                  
				    		        dialogo1.show();
				    		        
							    }else  if(cad.equals("falla")){
							    
							    totalacciones++;
							    if(totalacciones==cuenta){
							    	dial.dismiss();
							    	AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListadoNfcActivity.this);  
							        dialogo1.setTitle("MENSAJE");  
							        dialogo1.setMessage("Todos los estudientes del Grupo "+datoGrupo.toString()+" tienen falla en la clase de hoy \n¿Deseas cambiar de opinion?"); 
							      //obligamos al usuario a pulsar los botones para cerrarlo
							        dialogo1.setCancelable(false);  
							        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener()  {
						                     public void onClick(DialogInterface dialog, int id) {
						                    	 dialog.cancel();
						                     } 
						                 });
							        
							       dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {  
							            public void onClick(DialogInterface dialog, int id) {
							            //	Toast.makeText(ListadoNfcActivity.this, "Exito Resgistrados", Toast.LENGTH_LONG).show();
							            	dialog.cancel();
							            	dial= ProgressDialog.show(ListadoNfcActivity.this, "", "Guardando datos...",true);
							            	dial.setCancelable(true);
							            	Thread thr = new Thread(new Runnable() {
												  @Override
												  public void run() {
												  //Enviamos el texto escrito a la funcion
													  conteo=pers.size();
													  totalconteo=0;
													  

																
														try{
															
															for(int i=0; i<pers.size(); i++){
																if(pers.get(i).isChecked()||pers.get(i).isChecked2()){
												       	 //Utilizamos la clase Httpclient para conectar
												       	 httpclient = new DefaultHttpClient();
												       	//Utilizamos la HttpPost para enviar lso datos
												       	    //A la url donde se encuentre nuestro archivo receptor
												              httppost = new HttpPost("http://"+prefe.getString("ip","")+"/aa/registrarasistenciascambiodeopinion.php");
												         //Añadimos los datos a enviar en este caso solo tres
												           //que les llamamos de nombre 'id','user','pass'.
												           //La segunda linea podría repetirse tantas veces como queramos
												           //siempre cambiando el nombre ('id','user','pass')
												           nameValuePairs = new ArrayList<NameValuePair>(2);
												      // Añadimos los elementos a la lista
												         
												           nameValuePairs.add(new BasicNameValuePair("Id", pers.get(i).getIdent()));
													          nameValuePairs.add(new BasicNameValuePair("Nom", pers.get(i).getNombre()));
													        nameValuePairs.add(new BasicNameValuePair("Ape", pers.get(i).getApellido()));
													           nameValuePairs.add(new BasicNameValuePair("Grupo", datoGrupo.trim()));
													         nameValuePairs.add(new BasicNameValuePair("Fecha", getDatePhone().trim()));
													         
												         if(pers.get(i).isChecked()){
												        	
													         nameValuePairs.add(new BasicNameValuePair("Excusa", "0"));
													         //Encapsulamos
													           httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
													         //Lanzamos la petición
													           //eviaa datos a l doc PHP y compila el codigo
													          response = httpclient.execute(httppost);
												         }
												         if(pers.get(i).isChecked2()){
												        	 nameValuePairs.add(new BasicNameValuePair("Excusa", "1"));
												        	 //Encapsulamos
													           httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
													         //Lanzamos la petición
													           //eviaa datos a l doc PHP y compila el codigo
													          response = httpclient.execute(httppost);
												         }
												         
												         
												           //  Obtenemos el contenido de la respuesta
												        //Conectamos para recibir datos de respuesta
												          HttpEntity ent = response.getEntity();
												          String text = EntityUtils.toString(ent);
												        //Enviamos el resultado LIMPIO al Handler para mostrarlo
												          Message sms = new Message();
												          sms.obj = text;
												          puente2.sendMessage(sms);
												          
																}

															}
										 							          
												       }catch(Exception e){
												    	   e.printStackTrace();
												    	   dial.dismiss();
												    	   Message sms = new Message();
													          sms.obj = "cxerror";
													          puente2.sendMessage(sms);
													          }
												      
														
														
														
													  
												  }
												  });
												 //Arrancamos el Hilo
												 thr.start();
							            //	finish();
							            }  
							        });  
							                  
							        dialogo1.show(); 
							        
							       
							    }
							    }else{
							    		if(cad.equals("exito")){
							    		cuenta1++;
							    		totalacciones++;
							    			
							    		}else if(cad.equals("error")){
							    			cuentaerror++;
							    			totalacciones++;
							    			if(cuentaerror==cuenta){
							    			dial.dismiss();
							      			
										  //  Toast.makeText(ListadoNfcActivity.this, "Los estudiantes ya estaban registrados en la clase de hoy", Toast.LENGTH_LONG).show();
										    AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListadoNfcActivity.this);  
									        dialogo1.setTitle("MENSAJE");  
									        dialogo1.setMessage("Todos los estudiantes ya estaban registrados en la clase de hoy"); 
									      //obligamos al usuario a pulsar los botones para cerrarlo
									        dialogo1.setCancelable(false);  
									        
									       dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {  
									            public void onClick(DialogInterface dialog, int id) {
									            	
									            	dialog.cancel();
									            }  
									        });  
									                  
									        dialogo1.show(); 
										    vfnfc.setInAnimation(inFromLeftAnimation());
											vfnfc.setOutAnimation(outToRightAnimation());
											//vf.showNext();
											vfnfc.setDisplayedChild(0);
							    			}
							    		}else{
							    			totalacciones++;
							    		}
							    		
							    		if(cuenta1!=0&&totalacciones==cuenta){
						    				dial.dismiss();
						      			
						    				//Toast.makeText(ListadoNfcActivity.this, "Exito al Guardar", Toast.LENGTH_LONG).show();
						    				 AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListadoNfcActivity.this);  
										        dialogo1.setTitle("EXITO");  
										        dialogo1.setMessage("Exito al Guardar"); 
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
						       			 
						       			 AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListadoNfcActivity.this);  
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
				    	
		    	
		    private Handler puentemate = new Handler() {
		    	 @Override
		    	 public void handleMessage(Message msg) {
		    		 try{
		    		 alistanfc.removeAll(alistanfc);
			    		String cad=(String)msg.obj;
			    		if(msg.obj.toString().trim().equals("cxerror")){
			        		AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListadoNfcActivity.this);  
			    	        dialogo1.setTitle("Error de conexion");  
			    	        dialogo1.setMessage("Requiere de una conexion para trabajar"); 
			    	      //obligamos al usuario a pulsar los botones para cerrarlo
			    	        dialogo1.setCancelable(false);  
			    	        
			    	       dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {  
			    	            public void onClick(DialogInterface dialog, int id) {
			    	            	
			    	            	dialog.cancel();
			    	            	vfnfc.setInAnimation(inFromLeftAnimation());
			    					vfnfc.setOutAnimation(outToRightAnimation());
			    					vfnfc.setDisplayedChild(0);
			    	            	
			    	            }  
			    	        });  
			    	                  
			    	        dialogo1.show();
			        				 
			    			 	        
			        }else	if(cad.toString().equals("")){
			       			dial.dismiss();
			       			AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListadoNfcActivity.this);  
			    	        dialogo1.setTitle("MENSAJE");  
			    	        dialogo1.setMessage("No tiene Grupos registrados\n¿Desea registrar un nuevo Grupo?"); 
			    	      //obligamos al usuario a pulsar los botones para cerrarlo
			    	        dialogo1.setCancelable(false);  
			    	        dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {  
			    	            public void onClick(DialogInterface dialog, int id) {
			    	            	
			    	            	dialog.cancel();
			    	            	finish();
			    	            }  
			    	        }); 
			    	        
			    	       dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {  
			    	            public void onClick(DialogInterface dialog, int id) {
			    	            	
			    	            	dialog.cancel();
			    	            	Intent i=new Intent(ListadoNfcActivity.this, RegistroGrupo.class);
			    					startActivity(i);
			    	            	finish();
			    	            }  
			    	        });  
			    	                  
			    	        dialogo1.show(); 
			       			
			       		}else{
			    		StringTokenizer tok=new StringTokenizer(cad,",");
			      		while(tok.hasMoreTokens()){
			      			alistanfc.add(tok.nextToken().trim().toUpperCase());
			      		}
			      		lv1manfc.setAdapter(aanfc);
			      	
			    		dial.dismiss();
			    		
			    		vfnfc.setInAnimation(inFromRightAnimation());
	    				vfnfc.setOutAnimation(outToLeftAnimation());
	    				vfnfc.setDisplayedChild(1);
	    				
	    			/*	if(vfnfc.getDisplayedChild()==1){
	    					ActionBar actionBar = getActionBar();
	    					actionBar.setDisplayHomeAsUpEnabled(true);
	    					}*/
			       	}
			    		
		    		 }catch(Exception e){
		       			 e.printStackTrace();
		       			// Toast.makeText(MainActivity.this, "Error de conexion"+e.toString(), Toast.LENGTH_LONG).show();
		       			 dial.dismiss();
		       			 
		       			 AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListadoNfcActivity.this);  
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
		    	
		    	
		    	 private Handler puentegru = new Handler() {
			    	 @Override
			    	 public void handleMessage(Message msg) {
			    		 try{
			    	 //Mostramos el mensage recibido del servido en pantalla
			    		 alista2nfc.removeAll(alista2nfc);
			 	   		String cad=(String)msg.obj;
			 	   	if(msg.obj.toString().trim().equals("cxerror")){
			    		AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListadoNfcActivity.this);  
				        dialogo1.setTitle("Error de conexion");  
				        dialogo1.setMessage("Requiere de una conexion para trabajar"); 
				      //obligamos al usuario a pulsar los botones para cerrarlo
				        dialogo1.setCancelable(false);  
				        
				       dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {  
				            public void onClick(DialogInterface dialog, int id) {
				            	
				            	dialog.cancel();
				            	vfnfc.setInAnimation(inFromLeftAnimation());
								vfnfc.setOutAnimation(outToRightAnimation());
								vfnfc.setDisplayedChild(0);
				            	
				            }  
				        });  
				                  
				        dialogo1.show();
			    				 
						 	        
			    }else{
			 	   		StringTokenizer tok=new StringTokenizer(cad,",");
			 	     		while(tok.hasMoreTokens()){
			 	     			cont=0;
			 	     			String da=tok.nextToken();
			 	     			for(int i=0; i<alista2nfc.size(); i++) {
			 	        			if( alista2nfc.get(i).equals(da)) {
			 	        				cont=1;
			 	        			}	
			 	        	}
			 	     			if(cont==0){
			 	            		
			        				alista2nfc.add(da.trim());
			        			}
			 	     			
			 	     			//alista2.add(tok.nextToken().trim());
			 	     		}
			 	     		lv2grunfc.setAdapter(aa2nfc);
			 	     		//vf.showNext();
			 	     		
			 	   		dial.dismiss();
			 	  /* 		vfnfc.setInAnimation(inFromRightAnimation());
			 			vfnfc.setOutAnimation(outToLeftAnimation());
			 			vfnfc.showNext();*/
			    }
			    		 }catch(Exception e){
			       			 e.printStackTrace();
			       			// Toast.makeText(MainActivity.this, "Error de conexion"+e.toString(), Toast.LENGTH_LONG).show();
			       			 dial.dismiss();
			       			 
			       			 AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListadoNfcActivity.this);  
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
			    	
			    	 private Handler puenteBuscarEstNfc = new Handler() {
			    	   	 @Override
			    	   	 public void handleMessage(Message msg) {
			    	   	 //Mostramos el mensage recibido del servido en pantalla
			    	   	//	estuBuscar.removeAll(estuBuscar);
			    	   		 try{
				    		 String cad=(String)msg.obj;
				    		 if(msg.obj.toString().trim().equals("cxerror")){
				    	    		AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListadoNfcActivity.this);  
				    		        dialogo1.setTitle("Error de conexion");  
				    		        dialogo1.setMessage("Requiere de una conexion para trabajar"); 
				    		      //obligamos al usuario a pulsar los botones para cerrarlo
				    		        dialogo1.setCancelable(false);  
				    		        
				    		       dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {  
				    		            public void onClick(DialogInterface dialog, int id) {
				    		            	
				    		            	dialog.cancel();
				    		            	mSearchView.setQuery("", false);
				    	 		    		mSearchView.onActionViewCollapsed();
				    		            	
				    		            }  
				    		        });  
				    		                  
				    		        dialogo1.show();
				    	    				 
				    				 	        
				    	    }else{
				 	   		StringTokenizer tok=new StringTokenizer(cad,",");
				 	 //  	Toast.makeText(ListadoNfcActivity.this, cad, Toast.LENGTH_LONG).show();
				 	     		while(tok.hasMoreTokens()){
				 	     			contBuscar=0;
				 	     			String da=tok.nextToken();
				 	     			String da1=tok.nextToken();
				 	     			String da2=tok.nextToken();
				 	     	    	
				 	     			for(int i=0; i<estuBuscar.size(); i++) {
				 	        			if( estuBuscar.get(i).getIdent().equals(da)) {
				 	        				contBuscar=1;
				 	        			}	
				 	        	}
				 	     			if(contBuscar==0){
				 	            		
				         				estuBuscar.add(new EstuNFC(da1.trim().toUpperCase(),da2.trim().toUpperCase(),da.trim().toUpperCase(),false));
				         				Collections.sort(estuBuscar, new Comparator<EstuNFC>() {
											@Override
											public int compare(EstuNFC p1, EstuNFC p2) {
												// TODO Auto-generated method stub
												return new String(p1.getNombre()).compareToIgnoreCase(new String(p2.getNombre()));
												
											}
										} );
				         				mSelecionAdapterNFC=new AdaptadorEstuNFC(ListadoNfcActivity.this,estuBuscar);

										
				         			}
				 	     			
				 	     			//alista2.add(tok.nextToken().trim());
				 	     			 
				 	     			
				 	     		}
				 	     		lv3buscarNfc.setAdapter(mSelecionAdapterNFC);
				 	     		dial.dismiss();
								lv3buscarNfc.setDividerHeight(3);
								textoNresultados.setText("Se encontraron ("+estuBuscar.size()+") resultados");
							/*	if(vfnfc.getDisplayedChild()==3){
			    					ActionBar actionBar = getActionBar();
			    					actionBar.setDisplayHomeAsUpEnabled(true);
			    					}*/

				    	    }
			    	   		}catch(Exception e){
			    	   			 e.printStackTrace();
			    	   			// Toast.makeText(MainActivity.this, "Error de conexion"+e.toString(), Toast.LENGTH_LONG).show();
			    	   			 dial.dismiss();
			    	   			 
			    	   			 AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListadoNfcActivity.this);  
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
	// TODO XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
	//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX   ONCREATE   XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
	//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
	//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX		    	   	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_listadonfc);
		
		
		context = this;
        //Los elementos que vamos a usar en el layout
        // btnGuardarnfc = (Button)findViewById(R.id.btguardarnfc);
         lstpersonanfc = (ListView) findViewById(R.id.lvasistnfc);
         prefe=getSharedPreferences("datos",Context.MODE_PRIVATE);
        // prefedoc=getSharedPreferences("datos", Context.MODE_PRIVATE);
         lv1manfc=(ListView)findViewById(R.id.lvmatenfc);
 		lv2grunfc=(ListView)findViewById(R.id.lvgruponfc);
 		lv3buscarNfc=(ListView)findViewById(R.id.lbuscar_nfc);
 		textoNresultados= (TextView) findViewById(R.id.resultadosnfcdebusquedas);
 	//	et1buscarNfc= (EditText) findViewById(R.id.etbuscar_nfc);
 		
 		vfnfc = (ViewFlipper) findViewById(R.id.viewFlippernfc);
 		alistanfc=new ArrayList<String>();
 		aanfc =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,alistanfc);
 		alista2nfc=new ArrayList<String>();
 		aa2nfc =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,alista2nfc);
 		
 		
 		//lv1manfc.setOnTouchListener(new ListenerTouchViewFlipper());
 		//lv2grunfc.setOnTouchListener(new ListenerTouchViewFlipper());
 		
 		//alista2nfc.add("vacio");
 		//lv2grunfc.setAdapter(aa2nfc);
 		//aa2nfc.notifyDataSetChanged();
 		
         adapter = NfcAdapter.getDefaultAdapter(this);
         pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
         IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
         tagDetected.addCategory(Intent.CATEGORY_DEFAULT);
         readTagFilters = new IntentFilter[]{tagDetected};
         
         
         
         Boolean nfcEnabled=adapter.isEnabled();
	        if(nfcEnabled){
	        	//Toast.makeText(EscribirTag.this, "encendido", Toast.LENGTH_LONG).show();
	        	
	        }else{
	        	//Toast.makeText(Admin.this, "ACTIVE la opcion NFC de su dispositivo para continuar..", Toast.LENGTH_LONG).show();
	        	AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListadoNfcActivity.this);  
             dialogo1.setTitle("NFC apagado");  
             dialogo1.setMessage("Encienda el hardware NFC del dispositivo para registrar a los estudiantes"); 
           //obligamos al usuario a pulsar los botones para cerrarlo
             dialogo1.setCancelable(false);  
             
            dialogo1.setPositiveButton("Entendido", new DialogInterface.OnClickListener() {  
                 public void onClick(DialogInterface dialog, int id) {
                 	
                 	
                 	//finish();
                 	dialog.cancel();
                 }  
             });  
                       
             dialogo1.show(); 
	        
	        }
         
         //*****************THREAD*******************************************
         
       
        
         
       
 			 lv1manfc.setOnItemClickListener(new OnItemClickListener() {
		            @Override
		            public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
		            	
		            	//lv1.setOnTouchListener(new ListenerTouchViewFlipper());
		            	
		            	
		            	StringTokenizer tok1=new StringTokenizer(lv1manfc.getItemAtPosition(posicion).toString(),"-");
		          		while(tok1.hasMoreTokens()){
		          		//	Toast.makeText(MainActivity.this, tok1.nextToken().trim(), Toast.LENGTH_LONG).show();
		            	
		            	d=tok1.nextToken();
		            	tok1.nextToken();
		          		}
		            	dial= ProgressDialog.show(ListadoNfcActivity.this, "", "Cargando datos...",true);
		            	dial.setCancelable(true);
		          	   	 Thread thrgru = new Thread(new Runnable() {
		          				  @Override
		          				  public void run() {
		          		
		          												
		          						try{

		          				       	 httpclient = new DefaultHttpClient();
		          				              httppost = new HttpPost("http://"+prefe.getString("ip","")+"/aa/grupoxmate.php");
		          				           nameValuePairs = new ArrayList<NameValuePair>(2);
		          				         nameValuePairs.add(new BasicNameValuePair("Idd", prefe.getString("doc","")));
		          				         nameValuePairs.add(new BasicNameValuePair("Ano", getDateYears().trim()));
		          				       nameValuePairs.add(new BasicNameValuePair("Mate", d.trim()));
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
		          				     }
		          				      
		          		
		          					  
		          				  }
		          				  });
		          				 //Arrancamos el Hilo
		          				 thrgru.start();
		            	
		            }
			 });
         
         
 			lv2grunfc.setOnItemClickListener(new OnItemClickListener() {
 	            @Override
 	            public void onItemClick(AdapterView<?> parent, View v, final int posicion, long id) {
 	            	
 	            	//lv1.setOnTouchListener(new ListenerTouchViewFlipper());
 	            	
 	            	
 	           datoGrupo=lv2grunfc.getItemAtPosition(posicion).toString();
 	           
 	          AlertDialog.Builder builder2 =
                      new AlertDialog.Builder(ListadoNfcActivity.this);
       
              builder2.setMessage("¿Esta seguro de guardar en este grupo "+datoGrupo+" ?")
              .setTitle("Confirmacion")
              .setCancelable(false)
              .setNegativeButton("Cancelar", new DialogInterface.OnClickListener()  {
                     public void onClick(DialogInterface dialog, int id) {
                    	 dialog.cancel();
                     } 
                 })
                	 .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
		                     public void onClick(DialogInterface dialog, int id) {
 	          		
 	            dial= ProgressDialog.show(ListadoNfcActivity.this, "", "Guardando datos...",true);
 	           dial.setCancelable(true);
 	            	Thread thr = new Thread(new Runnable() {
						  @Override
						  public void run() {
						  //Enviamos el texto escrito a la funcion
							  cuenta=pers.size();
							  cuenta1=0;
							  cuentaerror=0;
							  totalacciones=0;
							  cuentafallas=0;
							  

										
								try{
									
									for(int i=0; i<pers.size(); i++){
										if(pers.get(i).isChecked()==true||pers.get(i).isChecked2()==true){
										//	Toast.makeText(ListadoNfcActivity.this, "entro en THREAD", Toast.LENGTH_LONG).show();
						       	 //Utilizamos la clase Httpclient para conectar
						       	 httpclient = new DefaultHttpClient();
						       	//Utilizamos la HttpPost para enviar lso datos
						       	    //A la url donde se encuentre nuestro archivo receptor
						              httppost = new HttpPost("http://"+prefe.getString("ip","")+"/aa/registrarasistencias.php");
						         //Añadimos los datos a enviar en este caso solo tres
						           //que les llamamos de nombre 'id','user','pass'.
						           //La segunda linea podría repetirse tantas veces como queramos
						           //siempre cambiando el nombre ('id','user','pass')
						           nameValuePairs = new ArrayList<NameValuePair>(2);
						      // Añadimos los elementos a la lista
						         
						           nameValuePairs.add(new BasicNameValuePair("Id", pers.get(i).getIdent()));
							          nameValuePairs.add(new BasicNameValuePair("Nom", pers.get(i).getNombre()));
							        nameValuePairs.add(new BasicNameValuePair("Ape", pers.get(i).getApellido()));
							           nameValuePairs.add(new BasicNameValuePair("Grupo", datoGrupo.trim()));
							         nameValuePairs.add(new BasicNameValuePair("Fecha", getDatePhone().trim()));
							         
						         if(pers.get(i).isChecked()==true){
						        	
							         nameValuePairs.add(new BasicNameValuePair("Excusa", "0"));
							         //Encapsulamos
							           httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
							         //Lanzamos la petición
							           //eviaa datos a l doc PHP y compila el codigo
							          response = httpclient.execute(httppost);
						         }
						         if(pers.get(i).isChecked2()==true){
						        	 nameValuePairs.add(new BasicNameValuePair("Excusa", "1"));
						        	 //Encapsulamos
							           httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
							         //Lanzamos la petición
							           //eviaa datos a l doc PHP y compila el codigo
							          response = httpclient.execute(httppost);
						         }
						         
						         
						           //  Obtenemos el contenido de la respuesta
						        //Conectamos para recibir datos de respuesta
						          HttpEntity ent = response.getEntity();
						          String text = EntityUtils.toString(ent);
						        //Enviamos el resultado LIMPIO al Handler para mostrarlo
						          Message sms = new Message();
						          sms.obj = text;
						          puente.sendMessage(sms);
						          
										}else {
											totalacciones++;
											cuentafallas++;
											if(cuentafallas==cuenta){
												Message sms = new Message();
										          sms.obj = "fal";
										          puente.sendMessage(sms);
											}
											
											
											
										}
								
									

									}
				 							          
						       }catch(Exception e){
						    	   e.printStackTrace();
						    	   dial.dismiss();
						    	   Message sms = new Message();
							          sms.obj = "cxerror";
							          puente.sendMessage(sms);
						       	// Toast.makeText(ListadoNfcActivity.this, "error"+e.toString(), Toast.LENGTH_LONG).show();
						       }
						      
								
								
								
							  
						  }
						  });
						 //Arrancamos el Hilo
						 thr.start();
		                     }
	                     });
      builder2.create();
         builder2.show();
         
 	            }
 		 });
         
         
 			
 	
        
 			setupActionBar();
		
	}
	
	//en onnewIntent manejamos el intent para encontrar el Tag
    @SuppressLint("NewApi") 
    protected void onNewIntent(Intent intent){
        if(NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())){
            myTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            try{
            StringTokenizer tokens=new StringTokenizer(read(myTag), ":");
    		String d1=tokens.nextToken();
    		String d2=tokens.nextToken();
    		String d3=tokens.nextToken().trim();
    		
    		cont=0;
    		for(int i=0; i<pers.size(); i++) {
    			if( pers.get(i).getIdent().equals(d3)) {
    				cont=1;
    			}
   		
    	}
    			if(cont==0){
        		pers.add(new Persona(d1.toUpperCase(), d2.toUpperCase(),d3.toUpperCase(), true,false));
        		
        		/*Se define un nuevo adaptador de tipo AdaptadorPersona 
        		 * donde se le pasa como argumentos el contexto de la 
        		 * actividad y el arraylist de los dias*/
        		adaptador=new AdaptadorPersona(this,pers);
        		//Se establece el adaptador en la Listview
        		lstpersonanfc.setAdapter(adaptador);
        		/*Esto es mas que nada es a nivel de diseño con el
        		 *  objetivo de crear unas lineas mas anchas entre 
        		 *  item y item*/
        		lstpersonanfc.setDividerHeight(4);
        		/*Se le aplica un Listener donde dira lo que tiene
        		 *  que hacer en caso de que sea pulsado*/
        		 saveItem.setEnabled(true);
    			}
        	
            }catch(IOException e){
        	    Toast.makeText(context, "error 1",Toast.LENGTH_LONG).show();
        	        e.printStackTrace();
        	    }catch(FormatException e){
        	       Toast.makeText(context, "error 2", Toast.LENGTH_LONG).show();
        	    e.printStackTrace();
        	    }
           // Toast.makeText(this, "ok detectado" + myTag.toString(), Toast.LENGTH_LONG).show();
        }
    }
	

  //El método write es el más importante, será el que se encargue de crear el mensaje 
    //y escribirlo en nuestro tag.
    private String read(Tag tag) throws IOException, FormatException{
       	
    	Ndef ndef = Ndef.get(tag);
    	if (ndef == null) {
            // NDEF is not supported by this Tag.
            return null;
        }
    	NdefMessage ndefMessage = ndef.getCachedNdefMessage();
        NdefRecord[] records = ndefMessage.getRecords();
        for (NdefRecord ndefRecord : records) {
            if (ndefRecord.getTnf() == NdefRecord.TNF_EXTERNAL_TYPE && Arrays.equals(ndefRecord.getType(), NdefRecord.RTD_TEXT)) {
                try {
                   return readText(ndefRecord);
                	
                } catch (IOException e) {
                    Log.e(TAG, "Unsupported Encoding", e);
                }
            }
        }
		return null;
  
    	
    }
    
    private String readText(NdefRecord record) throws UnsupportedEncodingException{
       byte[] payload = record.getPayload();
       String textEncoding = ((payload[0] & 128) == 0) ? "UTF-8" : "UTF-16";
       int languageCodeLength = payload[0] & 0063;
       return new String(payload, languageCodeLength + 1, payload.length - languageCodeLength - 1, textEncoding);

   	
   }
    
    public void onPause(){
        super.onPause();
        ReadModeOff();
    }
    public void onResume(){
        super.onResume();
        ReadModeOn();
    }
 
    @SuppressLint("NewApi") private void ReadModeOn(){
        readMode = true;
        adapter.enableForegroundDispatch(this, pendingIntent, readTagFilters, null);
    }
 
    @SuppressLint("NewApi") private void ReadModeOff(){
        readMode = false;
        adapter.disableForegroundDispatch(this);
    }
    
    public String getDateYears()

	{
	    Calendar cal = new GregorianCalendar();
	    Date date = cal.getTime();
	    SimpleDateFormat df = new SimpleDateFormat("yyyy");
	    String formatteDate = df.format(date);
	    return formatteDate;

	}
    public String getDatePhone()

   	{
   	    Calendar cal = new GregorianCalendar();
   	    Date date = cal.getTime();
   	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
   	    String formatteDate = df.format(date);
   	    return formatteDate;

   	}
    
    private Animation inFromRightAnimation() {
    	 
        Animation inFromRight = new TranslateAnimation(
        Animation.RELATIVE_TO_PARENT,  +1.0f, 
        Animation.RELATIVE_TO_PARENT,  0.0f,
        Animation.RELATIVE_TO_PARENT,  0.0f, 
        Animation.RELATIVE_TO_PARENT,   0.0f );
 
        inFromRight.setDuration(500);
        inFromRight.setInterpolator(new AccelerateInterpolator());
 
        return inFromRight;
 
    }
 
    private Animation outToLeftAnimation() {
        Animation outtoLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        outtoLeft.setDuration(500);
        outtoLeft.setInterpolator(new AccelerateInterpolator());
        return outtoLeft;
    }
 
    private Animation inFromLeftAnimation() {
        Animation inFromLeft = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, -1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        inFromLeft.setDuration(500);
        inFromLeft.setInterpolator(new AccelerateInterpolator());
        return inFromLeft;
    }
 
    private Animation outToRightAnimation() {
        Animation outtoRight = new TranslateAnimation(
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, +1.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f,
                Animation.RELATIVE_TO_PARENT, 0.0f);
        outtoRight.setDuration(500);
        outtoRight.setInterpolator(new AccelerateInterpolator());
        return outtoRight;
    }

	@Override
	public boolean onQueryTextChange(String nextText) {
		// TODO Auto-generated method stub
		if(nextText.equals("")){
			if(vfnfc.getDisplayedChild()==3){
			vfnfc.setInAnimation(inFromLeftAnimation());
				vfnfc.setOutAnimation(outToRightAnimation());
				vfnfc.setDisplayedChild(0);
				//mSearchView.setQueryRefinementEnabled(true);
				//mSearchView.setQuery("", false);
				//mSearchView.setQueryHint("otro");
				ActionBar actionBar = getActionBar();
				actionBar.setDisplayHomeAsUpEnabled(false);
			}
			
			
		}
		return false;
	}

	@Override
	public boolean onQueryTextSubmit(final String text) {
		// TODO Auto-generated method stub
		textoNresultados.setText("Resultados de Busqueda");
		estuBuscar.removeAll(estuBuscar);
	        boolean algunDigito = false; 
	        boolean algunaLetra = false; 
	        for (int i = 0; i < text.length(); i++) { 
	            if (Character.isDigit(text.charAt(i))) { 
	                //es un digito 
	                algunDigito = true; 
	            } else { 
	                algunaLetra = true; 
	                //no es un digito 
	            } 
	        } 
	        if(algunDigito && !algunaLetra){ 
	          //  System.out.println("TODO DIGITOS"); 
	       //     Toast.makeText(this, "TODO DIGITOS", Toast.LENGTH_SHORT).show();
	        	dial= ProgressDialog.show(ListadoNfcActivity.this, "", "Buscando por Identificacion...",true);
	        	dial.setCancelable(true);
	            Thread thr = new Thread(new Runnable() {
	  			  @Override
	  			  public void run() {
	  											
	  					try{
	  			       	 //Utilizamos la clase Httpclient para conectar
	  			       	 httpclient = new DefaultHttpClient();
	  			       	//Utilizamos la HttpPost para enviar lso datos
	  			       	    //A la url donde se encuentre nuestro archivo receptor
	  			              httppost = new HttpPost("http://"+prefe.getString("ip","")+"/aa/buscarestunfc.php");
	  			         //Añadimos los datos a enviar 
	  			           nameValuePairs = new ArrayList<NameValuePair>(2);
	  			      // Añadimos los elementos a la lista
	  			         nameValuePairs.add(new BasicNameValuePair("Idd", text.toString().trim()));
	  			          
	  			         
	  			         //Encapsulamos
	  			           httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	  			         //Lanzamos la petición
	  			           //eviaa datos a l doc PHP y compila el codigo
	  			          response = httpclient.execute(httppost);
	  			           //  Obtenemos el contenido de la respuesta
	  			        //Conectamos para recibir datos de respuesta
	  			          HttpEntity ent = response.getEntity();
	  			          String text = EntityUtils.toString(ent);

	  			        //Enviamos el resultado LIMPIO al Handler para mostrarlo
	  			          Message sms = new Message();
	  			          sms.obj = text;
	  			          puenteBuscarEstNfc.sendMessage(sms);

	  			       }catch(Exception e){
	  			    
	  			    	   e.printStackTrace();
	  			    	 dial.dismiss();
	  			    	Message sms = new Message();
	  			          sms.obj = "cxerror";
	  			          puenteBuscarEstNfc.sendMessage(sms);
	  			          }
	  				
	  			      
	  				  
	  			  }
	  			  });
	  			 //Arrancamos el Hilo
	  			 thr.start();
	            
	            
	        }else if (algunDigito){ 
	         //   System.out.println("ALFNUMERICOS"); 
	          //  Toast.makeText(this, "ALFNUMERICOS", Toast.LENGTH_SHORT).show();
	        	dial= ProgressDialog.show(ListadoNfcActivity.this, "", "Buscando Grupo...",true);
	        	dial.setCancelable(true);
	            Thread thr = new Thread(new Runnable() {
		  			  @Override
		  			  public void run() {
		  										
		  					try{
		  			       	 //Utilizamos la clase Httpclient para conectar
		  			       	 httpclient = new DefaultHttpClient();
		  			       	//Utilizamos la HttpPost para enviar lso datos
		  			       	    //A la url donde se encuentre nuestro archivo receptor
		  			              httppost = new HttpPost("http://"+prefe.getString("ip","")+"/aa/buscarestunfcgrupo.php");
		  			         //Añadimos los datos a enviar 
		  			           nameValuePairs = new ArrayList<NameValuePair>(2);
		  			      // Añadimos los elementos a la lista
		  			         nameValuePairs.add(new BasicNameValuePair("Grupo", text.toString().trim()));
		  			          
		  			         
		  			         //Encapsulamos
		  			           httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
		  			         //Lanzamos la petición
		  			           //eviaa datos a l doc PHP y compila el codigo
		  			          response = httpclient.execute(httppost);
		  			           //  Obtenemos el contenido de la respuesta
		  			        //Conectamos para recibir datos de respuesta
		  			          HttpEntity ent = response.getEntity();
		  			          String text = EntityUtils.toString(ent);

		  			        //Enviamos el resultado LIMPIO al Handler para mostrarlo
		  			          Message sms = new Message();
		  			          sms.obj = text;
		  			          puenteBuscarEstNfc.sendMessage(sms);

		  			       }catch(Exception e){
		  			    	   e.printStackTrace();
		  			    	 dial.dismiss();
		  			    	Message sms = new Message();
		  			          sms.obj = "cxerror";
		  			          puenteBuscarEstNfc.sendMessage(sms);
		  			          }
		  				
		  			      
		  				  
		  			  }
		  			  });
		  			 //Arrancamos el Hilo
		  			 thr.start();
		  				
	            
	            
	            
	        }else{ 
	         //   System.out.println("TODO LETRAS"); 
	         //   Toast.makeText(this, "TODO LETRAS", Toast.LENGTH_SHORT).show();
	        	dial= ProgressDialog.show(ListadoNfcActivity.this, "", "Buscando por Nombres...",true);
	        	dial.setCancelable(true);
	            Thread thr = new Thread(new Runnable() {
	  			  @Override
	  			  public void run() {
	  											
	  					try{
	  			       	 //Utilizamos la clase Httpclient para conectar
	  			       	 httpclient = new DefaultHttpClient();
	  			       	//Utilizamos la HttpPost para enviar lso datos
	  			       	    //A la url donde se encuentre nuestro archivo receptor
	  			              httppost = new HttpPost("http://"+prefe.getString("ip","")+"/aa/buscarestunfcnombre.php");
	  			         //Añadimos los datos a enviar 
	  			           nameValuePairs = new ArrayList<NameValuePair>(2);
	  			      // Añadimos los elementos a la lista
	  			         nameValuePairs.add(new BasicNameValuePair("Nom", text.toString().trim()));
	  			          
	  			         
	  			         //Encapsulamos
	  			           httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
	  			         //Lanzamos la petición
	  			           //eviaa datos a l doc PHP y compila el codigo
	  			          response = httpclient.execute(httppost);
	  			           //  Obtenemos el contenido de la respuesta
	  			        //Conectamos para recibir datos de respuesta
	  			          HttpEntity ent = response.getEntity();
	  			          String text = EntityUtils.toString(ent);

	  			        //Enviamos el resultado LIMPIO al Handler para mostrarlo
	  			          Message sms = new Message();
	  			          sms.obj = text;
	  			          puenteBuscarEstNfc.sendMessage(sms);

	  			       }catch(Exception e){
	  			    	   e.printStackTrace();
	  			    	 dial.dismiss();
	  			    	Message sms = new Message();
	  			          sms.obj = "cxerror";
	  			          puenteBuscarEstNfc.sendMessage(sms);
	  			          }
	  				
	  			      
	  				  
	  			  }
	  			  });
	  			 //Arrancamos el Hilo
	  			 thr.start();
	            
	            
	        }  
		
	        if(vfnfc.getDisplayedChild()==0){
	        	vfnfc.setInAnimation(inFromRightAnimation());
	    		vfnfc.setOutAnimation(outToLeftAnimation());
	    		//vf.showNext();
	    		vfnfc.setDisplayedChild(3);
				}
		
		
		return false;
	}
 
	

	private void setupActionBar() {
		//ListView listView = getListView();
		lv3buscarNfc.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		lv3buscarNfc.setMultiChoiceModeListener(new MultiChoiceModeListener() {

			@Override
			public void onItemCheckedStateChanged(ActionMode mode,
					int position, long id, boolean checked) {

				// Si se comprueba elemento, que se añade a la selección, si no, de que sea eliminado
				//String s=String.valueOf(position);
				if (checked) {
					mSelecionAdapterNFC.setNewSelection(position);
					
				//	Toast.makeText(MainActivity.this, "Selecciono "+s, Toast.LENGTH_LONG).show();
				} else {
					mSelecionAdapterNFC.removeSelection(position);
				//	Toast.makeText(MainActivity.this, "Deselecciono "+s, Toast.LENGTH_LONG).show();
				}

				mode.setTitle(mSelecionAdapterNFC.getSelectionCount() + " estudiantes");
				mode.setSubtitle("seleccionados");
			}

			@Override
			public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
				// CAB menu options
				switch (item.getItemId()) {
				case R.id.addagregar:
				/*	Toast.makeText(ListadoNfcActivity.this,
							mSelecionAdapterNFC.getSelectionCount() + " items add",
							Toast.LENGTH_LONG).show();*/
					for(int j=1;j<=2;j++){
						for(int i=0;i<estuBuscar.size();i++){
							if(estuBuscar.get(i).isSeleccion()){
								cont=0;
					    		for(int k=0; k<pers.size(); k++) {
					    			if( pers.get(k).getIdent().equals(estuBuscar.get(i).getIdent())) {
					    				cont=1;
					    			}
					   		
					    	}
					    			if(cont==0){
								
								pers.add(new Persona(estuBuscar.get(i).getNombre(), estuBuscar.get(i).getApellid(), estuBuscar.get(i).getIdent(), false, true));
								adaptador=new AdaptadorPersona(ListadoNfcActivity.this, pers);
			 					lstpersonanfc.setAdapter(adaptador);
			 					saveItem.setEnabled(true);
							}
							}
						}
						}
										 
				        //mSearchView.clearFocus();
					
				
					mSearchView.setQuery("", false);
 		    		mSearchView.onActionViewCollapsed();
					//onQueryTextChange("");
				
					
					
 					
					mode.finish();
					return true;
				default:
					return false;
				}
			}

			@Override
			public boolean onCreateActionMode(ActionMode mode, Menu menu) {

				// CAB is initialized
				MenuInflater inflater = mode.getMenuInflater();
				inflater.inflate(R.menu.main_cab, menu);

				return true;
			}

			@Override
			public void onDestroyActionMode(ActionMode mode) {
				mSelecionAdapterNFC.clearSelection();
			//	Toast.makeText(ListadoNfcActivity.this, "CHULEADO ", Toast.LENGTH_LONG).show();
			}

			@Override
			public boolean onPrepareActionMode(ActionMode mode, Menu menu) {

				return false;
			}
		});
	}
   
    
}
