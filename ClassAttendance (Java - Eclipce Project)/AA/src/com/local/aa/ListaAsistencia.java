package com.local.aa;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
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

import android.app.ActionBar;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import android.widget.AdapterView.OnItemClickListener;

public class ListaAsistencia extends Activity {
	private ArrayList<Estu> estu=new ArrayList<Estu>();
	private AdaptadorEstu adaptador;
	private TextView num;
	HttpPost httppost;
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpclient;
    InputStream inputStream;
    SharedPreferences prefe ;
    List<NameValuePair> nameValuePairs;
    private ArrayAdapter<String> adapterjor;
    ProgressDialog dial;
    String d,datoGrupo;
    int cont=0;
    
     ArrayList<String> alista,alista2,alista3;
	 ArrayAdapter<String> aa,aa2,aa3;

	private ListView lv1Mate,lv2Gru,lv3Fecha,lv4Estu;
	private ViewFlipper vf;
	float init_x,init_y;
	@Override
	public boolean onOptionsItemSelected(MenuItem menuItem)
	{       
		
		
		
		if(vf.getDisplayedChild()==1){
			vf.setInAnimation(inFromLeftAnimation());
			vf.setOutAnimation(outToRightAnimation());
			vf.setDisplayedChild(0);
			
			ActionBar actionBar = getActionBar();
			actionBar.setDisplayHomeAsUpEnabled(false);
			}
		if(vf.getDisplayedChild()==2){
			vf.setInAnimation(inFromLeftAnimation());
			vf.setOutAnimation(outToRightAnimation());
			vf.setDisplayedChild(1);
			
			}
	    //onBackPressed();
	    return true;
	}
	
	private Handler puentegru = new Handler() {
	   	 @Override
	   	 public void handleMessage(Message msg) {
	   		 try{
	   		alista2.removeAll(alista2);
	   		String cad=(String)msg.obj;
	   		if(msg.obj.toString().trim().equals("cxerror")){
	    		AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListaAsistencia.this);  
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
	    				 
				 	        
	    }else{
	   		StringTokenizer tok=new StringTokenizer(cad,",");
	     		while(tok.hasMoreTokens()){
	     			cont=0;
	     			String da=tok.nextToken();
	     			for(int i=0; i<alista2.size(); i++) {
	        			if( alista2.get(i).equals(da)) {
	        				cont=1;
	        			}	
	        	}
	     			if(cont==0){
	            		
       				alista2.add(da.trim());
       			}
	     			
	     			//alista2.add(tok.nextToken().trim());
	     		}
	     		lv2Gru.setAdapter(aa2);
	     		//vf.showNext();
	     		
	   		dial.dismiss();
	   	/*	vf.setInAnimation(inFromRightAnimation());
			vf.setOutAnimation(outToLeftAnimation());
			vf.showNext();*/
	    }
	   		}catch(Exception e){
   			 e.printStackTrace();
   			// Toast.makeText(MainActivity.this, "Error de conexion"+e.toString(), Toast.LENGTH_LONG).show();
   			 dial.dismiss();
   			 
   			 AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListaAsistencia.this);  
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
	    		 alista.removeAll(alista);
	    		String cad=(String)msg.obj;
	    		if(msg.obj.toString().trim().equals("cxerror")){
	        		AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListaAsistencia.this);  
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
	        				
	    			 	        
	        }else	if(cad.toString().equals("")){
	       			dial.dismiss();
	       			AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListaAsistencia.this);  
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
	    	            	Intent i=new Intent(ListaAsistencia.this, RegistroGrupo.class);
	    					startActivity(i);
	    	            	finish();
	    	            }  
	    	        });  
	    	                  
	    	        dialogo1.show(); 
	       			
	       		}else{
	    		StringTokenizer tok=new StringTokenizer(cad,",");
	      		while(tok.hasMoreTokens()){
	      			alista.add(tok.nextToken().trim().toUpperCase());
	      		}
	      		lv1Mate.setAdapter(aa);
	      	
	    		dial.dismiss();
	    		
	       		}
	    		
	    		 }catch(Exception e){
	       			 e.printStackTrace();
	       			// Toast.makeText(MainActivity.this, "Error de conexion"+e.toString(), Toast.LENGTH_LONG).show();
	       			 dial.dismiss();
	       			 
	       			 AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListaAsistencia.this);  
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
	  
		private Handler puentefecha = new Handler() {
	    	 @Override
	    	 public void handleMessage(Message msg) {
	    		 try{
	    		 alista3.removeAll(alista3);
	    		 String cad=(String)msg.obj;
	    		 if(msg.obj.toString().trim().equals("cxerror")){
	    	    		AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListaAsistencia.this);  
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
	    	    				 
	    				 	        
	    	    }else	 if(cad.toString().equals("")){
	    	   			dial.dismiss();
	    	   			AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListaAsistencia.this);  
	    		        dialogo1.setTitle("MENSAJE");  
	    		        dialogo1.setMessage("No se han registrado Nuevas clases"); 
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
	 	     			cont=0;
	 	     			String da=tok.nextToken();
	 	     			for(int i=0; i<alista3.size(); i++) {
	 	        			if( alista3.get(i).equals(da)) {
	 	        				cont=1;
	 	        			}	
	 	        	}
	 	     			if(cont==0){
	 	            		
	         				alista3.add(da.trim());
	         			}
	 	     			
	 	     			//alista2.add(tok.nextToken().trim());
	 	     		}
	 	     		lv3Fecha.setAdapter(aa3);
	 	     		//vf.showNext();
	 	     		
	 	   		dial.dismiss();
	 	   	vf.setInAnimation(inFromRightAnimation());
			vf.setOutAnimation(outToLeftAnimation());
			vf.setDisplayedChild(1);

			if(vf.getDisplayedChild()==1){
				ActionBar actionBar = getActionBar();
				actionBar.setDisplayHomeAsUpEnabled(true);
				}
	       	}
	    		 }catch(Exception e){
	       			 e.printStackTrace();
	       			// Toast.makeText(MainActivity.this, "Error de conexion"+e.toString(), Toast.LENGTH_LONG).show();
	       			 dial.dismiss();
	       			 
	       			 AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListaAsistencia.this);  
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
	  
	  private Handler puenteestu = new Handler() {
	    	 @Override
	    	 public void handleMessage(Message msg) {
	    		 try{
	    		 estu.removeAll(estu);
	    		 String cad=(String)msg.obj;
	    		 if(msg.obj.toString().trim().equals("cxerror")){
	    	    		AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListaAsistencia.this);  
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
	    	    				 
	    				 	        
	    	    }else	 if(cad.toString().equals("")){
	    	   			dial.dismiss();
	    	   			AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListaAsistencia.this);  
	    		        dialogo1.setTitle("MENSAJE");  
	    		        dialogo1.setMessage("Todos los estudiantes tienen falla en esta clse"); 
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
	 	     			cont=0;
	 	     			String da=tok.nextToken();
	 	     			String da1=tok.nextToken();
	 	     			String da2=tok.nextToken();
	 	     			for(int i=0; i<estu.size(); i++) {
	 	        			if( estu.get(i).getIdent().equals(da)) {
	 	        				cont=1;
	 	        			}	
	 	        	}
	 	     			if(cont==0){
	 	            		
	         				estu.add(new Estu(da2.trim().toUpperCase(),da1.trim().toUpperCase(),da.trim().toUpperCase()));
	         				Collections.sort(estu, new Comparator<Estu>() {
								@Override
								public int compare(Estu p1, Estu p2) {
									// TODO Auto-generated method stub
									return new String(p1.getNombre()).compareToIgnoreCase(new String(p2.getNombre()));
									
								}
							} );
	         				adaptador=new AdaptadorEstu(ListaAsistencia.this,estu);

							
	         			}
	 	     			
	 	     			//alista2.add(tok.nextToken().trim());
	 	     		}
	 	     		lv4Estu.setAdapter(adaptador);
					lv4Estu.setDividerHeight(3);
					num.setText("("+estu.size()+")"+" Estudiantes");
	 	     		//lv3.setAdapter(aa3);
	 	     		//vf.showNext();
	 	   		dial.dismiss();

	 	    	vf.setInAnimation(inFromRightAnimation());
				vf.setOutAnimation(outToLeftAnimation());
				vf.setDisplayedChild(2);
		     	 
				if(vf.getDisplayedChild()==2){
					ActionBar actionBar = getActionBar();
					actionBar.setDisplayHomeAsUpEnabled(true);
					}
	    	   		}
	    		 }catch(Exception e){
	       			 e.printStackTrace();
	       			// Toast.makeText(MainActivity.this, "Error de conexion"+e.toString(), Toast.LENGTH_LONG).show();
	       			 dial.dismiss();
	       			 
	       			 AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListaAsistencia.this);  
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
		setContentView(R.layout.listaasistencia);
		num=(TextView)findViewById(R.id.tvestudiantelistadoparafechasdegrupo);
		prefe=getSharedPreferences("datos",Context.MODE_PRIVATE);
		vf = (ViewFlipper) findViewById(R.id.viewFlipperListaAsistencias);
		lv1Mate=(ListView)findViewById(R.id.lvmate);
		lv2Gru=(ListView)findViewById(R.id.lvgrupo);
		lv3Fecha=(ListView)findViewById(R.id.lvfecha);
		lv4Estu=(ListView)findViewById(R.id.lvestu);
		alista=new ArrayList<String>();
		aa =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,alista);
		alista2=new ArrayList<String>();
		aa2 =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,alista2);
		alista3=new ArrayList<String>();
		aa3 =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,alista3);
		
	//	lv1Mate.setOnTouchListener(new ListenerTouchViewFlipper1());
	//	lv2Gru.setOnTouchListener(new ListenerTouchViewFlipper1());
	//	lv3Fecha.setOnTouchListener(new ListenerTouchViewFlipper3());
	//	lv4Estu.setOnTouchListener(new ListenerTouchViewFlipper());
		
		dial= ProgressDialog.show(ListaAsistencia.this, "", "Cargando datos...",true);
		dial.setCancelable(true);
	   	 Thread thrmate = new Thread(new Runnable() {
				  @Override
				  public void run() {
		
												
						try{

				       	 httpclient = new DefaultHttpClient();
				              httppost = new HttpPost("http://"+prefe.getString("ip","")+"/aa/materiadocente.php");
				           nameValuePairs = new ArrayList<NameValuePair>(2);
				         nameValuePairs.add(new BasicNameValuePair("Idd", prefe.getString("doc","")));
				         nameValuePairs.add(new BasicNameValuePair("Ano", getDateYears().trim()));
				           httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				          response = httpclient.execute(httppost);
				     HttpEntity ent = response.getEntity();
				          String text = EntityUtils.toString(ent);
				      
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
		
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXX INSERTAR EN CONSTRUCTOR Y LISTVIEW XXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
				 
				 
						
						lv3Fecha.setOnItemClickListener(new OnItemClickListener() {
							@Override
				            public void onItemClick(AdapterView<?> parent, View v, final int posicion, long id) {
								
								dial= ProgressDialog.show(ListaAsistencia.this, "", "Cargando datos...",true);
								dial.setCancelable(true);
								Thread threstu = new Thread(new Runnable() {
				          				  @Override
				          				  public void run() {
				          		
				          												
				          						try{

				          				       	 httpclient = new DefaultHttpClient();
				          				              httppost = new HttpPost("http://"+prefe.getString("ip","")+"/aa/estudiantesxpgruppofecha.php");
				          				           nameValuePairs = new ArrayList<NameValuePair>(2);
				          				         nameValuePairs.add(new BasicNameValuePair("Fecha", lv3Fecha.getItemAtPosition(posicion).toString().trim()));
				          				       nameValuePairs.add(new BasicNameValuePair("Grupo",datoGrupo.trim()));
				          				         httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				          				          response = httpclient.execute(httppost);
				          				     HttpEntity ent = response.getEntity();
				          				          String text = EntityUtils.toString(ent);
				          				      
				          				          Message sms = new Message();
				          				          sms.obj = text;
				          				          puenteestu.sendMessage(sms);
				          				     
				          				                
				          				          
				          				       }catch(Exception e){
				          				    	   e.printStackTrace();
				          				    	 dial.dismiss();

				          				          Message sms = new Message();
				          				          sms.obj = "cxerror";
				          				          puenteestu.sendMessage(sms);	
				          				       }
				          				      
				          		
				          					  
				          				  }
				          				  });
				          				 //Arrancamos el Hilo
				          				 threstu.start();
				            	
								

							}
						});
				 
				 
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
				 
				 lv1Mate.setOnItemClickListener(new OnItemClickListener() {
			            @Override
			            public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
			            	
			            	//lv1.setOnTouchListener(new ListenerTouchViewFlipper());
			            	
			            	
			            	StringTokenizer tok1=new StringTokenizer(lv1Mate.getItemAtPosition(posicion).toString(),"-");
			          		while(tok1.hasMoreTokens()){
			          		//	Toast.makeText(MainActivity.this, tok1.nextToken().trim(), Toast.LENGTH_LONG).show();
			            	
			            	d=tok1.nextToken();
			            	tok1.nextToken();
			          		}
			            	dial= ProgressDialog.show(ListaAsistencia.this, "", "Cargando datos...",true);
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
		
//XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
		lv2Gru.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, final int posicion, long id) {
            	
            	//lv1.setOnTouchListener(new ListenerTouchViewFlipper());
            	
            	
           datoGrupo=lv2Gru.getItemAtPosition(posicion).toString();
          		
            	dial= ProgressDialog.show(ListaAsistencia.this, "", "Cargando datos...",true);
            	dial.setCancelable(true);
            	Thread thrfecha = new Thread(new Runnable() {
          				  @Override
          				  public void run() {
          		
          												
          						try{

          				       	 httpclient = new DefaultHttpClient();
          				              httppost = new HttpPost("http://"+prefe.getString("ip","")+"/aa/fechaclasexgrupo.php");
          				           nameValuePairs = new ArrayList<NameValuePair>(2);
          				         nameValuePairs.add(new BasicNameValuePair("Grupo", datoGrupo.trim()));
          				           httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
          				          response = httpclient.execute(httppost);
          				     HttpEntity ent = response.getEntity();
          				          String text = EntityUtils.toString(ent);
          				      
          				          Message sms = new Message();
          				          sms.obj = text;
          				          puentefecha.sendMessage(sms);
          				     
          				                
          				          
          				       }catch(Exception e){
          				    	   e.printStackTrace();
          				    	 dial.dismiss();
          				    	 Message sms = new Message();
         				          sms.obj = "cxerror";
         				          puentefecha.sendMessage(sms);
          				       }
          				      
          		
          					  
          				  }
          				  });
          				 //Arrancamos el Hilo
          				 thrfecha.start();
            	
            }
	 });
		
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
    
    public String getDateYears()

	{
	    Calendar cal = new GregorianCalendar();
	    Date date = cal.getTime();
	    SimpleDateFormat df = new SimpleDateFormat("yyyy");
	    String formatteDate = df.format(date);
	    return formatteDate;

	}
	

}
