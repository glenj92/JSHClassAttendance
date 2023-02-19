package com.local.aa;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.http.util.ExceptionUtils;




import android.annotation.SuppressLint;
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
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.Ndef;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.renderscript.Type;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity {
	private String user="",pass="",text,datoip;
	private Button login;
	private CheckBox che;
	private EditText et1,et2,etip;
	
	private static String TAG;
	NfcAdapter adapter;
    PendingIntent pendingIntent;
    IntentFilter readTagFilters[];
    boolean readMode;
    Tag myTag;
    Context context;
	
	HttpPost httppost;
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpclient;
    InputStream inputStream;
    SharedPreferences prefe, prefedocente ;
    ProgressDialog dial;
    
    List<NameValuePair> nameValuePairs;
   private EditText textoBusqueda;

    
   
   
    
    
  	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case R.id.saveip:
			// Toast.makeText(this, "buscar", Toast.LENGTH_SHORT).show();
			  AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);  
			  textoBusqueda = new EditText(this);
			  
			  textoBusqueda.setText(prefe.getString("ip",""));
		        dialogo1.setTitle("Direccion IP del Servidor");  
		        dialogo1.setMessage("Escriba la Direccion IP de su Base de datos"); 
		        dialogo1.setView(textoBusqueda);
		      //obligamos al usuario a pulsar los botones para cerrarlo
		        dialogo1.setCancelable(false);  
		        
		       dialogo1.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {  
		            public void onClick(DialogInterface dialog, int id) {
		            	
		            	SharedPreferences preferencias=getSharedPreferences("datos",Context.MODE_PRIVATE);
		             
		        		Editor editor=preferencias.edit();
		                
		                editor.putString("ip", textoBusqueda.getText().toString());
		                
		                editor.commit();
		                  dialog.cancel();
		            }  
		        });  
		       
		       dialogo1.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {  
		            public void onClick(DialogInterface dialog, int id) {
		            	
		           
		                  dialog.cancel();
		            }  
		        });
		                  
		        dialogo1.show();  
 			
			break;
		
		
			default:
			
		}
		return super.onOptionsItemSelected(item);
	}
    
  	private Handler puente1 = new Handler() {
   	 @Override
   	 public void handleMessage(Message msg) {
   	 //Mostramos el mensage recibido del servido en pantalla
   		try{
   		 String dat= msg.obj.toString();	
   		 if(msg.obj.toString().trim().equals("cxerror")){
   	    		AlertDialog.Builder dialogo1 = new AlertDialog.Builder(MainActivity.this);  
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
   		 StringTokenizer tok=new StringTokenizer(dat,",");
   		 String d1;
      		String d2;
    		while(tok.hasMoreTokens()){
    			
    			d1=tok.nextToken().trim();
    			d2=tok.nextToken().trim();
    			//tv1bienve.setText("Bienvenid(a) "+d1.toUpperCase()+" "+d2.toUpperCase());
    			
    			Editor editor=prefe.edit();
     	        editor.putString("nombreDoc", d1);
     	        editor.commit();
     	        
     	      Editor editor1=prefe.edit();
   	        editor1.putString("apellidoDoc", d2);
   	        editor1.commit();
   	     if(prefe.getString("perfilUser", "").trim().equals("3")){
	 			 Intent i=new Intent(MainActivity.this, Admin.class);
	    			// i.putExtra("identidad", user.trim());
	    			 startActivity(i);
	    			 dial.dismiss();
	    			finish();
	 		}
 			
 		if(prefe.getString("perfilUser", "").trim().equals("2")){	
		 Intent i=new Intent(MainActivity.this, PrincipalActivity.class);
		// i.putExtra("identidad", user.trim());
		 startActivity(i);
		 dial.dismiss();
		finish();
 		}
 		if(prefe.getString("perfilUser", "").trim().equals("1")){
 			 Intent i=new Intent(MainActivity.this, EscribirTag.class);
    			// i.putExtra("identidad", user.trim());
    			 startActivity(i);
    			 dial.dismiss();
    			finish();
 		}
    		}
   		 
   		 
   		 
 	        
   		 dial.dismiss();
   	    }
   	 }catch(Exception e){
			 e.printStackTrace();
			// Toast.makeText(MainActivity.this, "Error de conexion"+e.toString(), Toast.LENGTH_LONG).show();
			 dial.dismiss();
			 
			 AlertDialog.Builder dialogo1 = new AlertDialog.Builder(MainActivity.this);  
		        dialogo1.setTitle("Error de conexion");  
		        dialogo1.setMessage("Fallo la conexion con el servidor \n"+e.toString()); 
		      //obligamos al usuario a pulsar los botones para cerrarlo
		        dialogo1.setCancelable(false);  
		        
		       dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {  
		            public void onClick(DialogInterface dialog, int id) {
		            	
		            	dialog.cancel();
		            }  
		        });  
		                  
		   /*     dialogo1.show();
		       Editor editor11=prefe.edit();
	        editor11.putString("checked", "no");
	        editor11.commit();*/
		        
		 }
   		 
   	 }
   	};
    
	 private Handler puente = new Handler() {
    	 @Override
    	 public void handleMessage(Message msg) {
    	 //Mostramos el mensage recibido del servido en pantalla
    		 try{
    	if(msg.obj.toString().trim().equals("cxerror")){
    		AlertDialog.Builder dialogo1 = new AlertDialog.Builder(MainActivity.this);  
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
			 	        
    }else	 if(msg.obj.toString().trim().equals("NO")){
    			 dial.dismiss();
    			 Toast.makeText(getApplicationContext(), "Su Usuario o Contraseña son incorrectos", 
    			    	   Toast.LENGTH_LONG).show();
    		 }else{
    		 
    		String dat;
    		String d2;
    		String dato3;
    		 StringTokenizer tok=new StringTokenizer(msg.obj.toString(),",");
     		while(tok.hasMoreTokens()){
     			dat=tok.nextToken().trim();
     		//	d2=tok.nextToken().trim();
     			dato3=tok.nextToken().trim();
     		
    		
    		 if(dat.trim().equals("OK")){
    			 prefedocente=getSharedPreferences("datos", Context.MODE_PRIVATE);
    	 	       	Editor editor=prefedocente.edit();
    	 	        editor.putString("doc", user.trim());
    	 	        editor.commit();
 
    	 	    Editor editor4=prefe.edit();
   	 	        editor4.putString("perfilUser", dato3.toString().trim());
   	 	        editor4.commit();
    	 	     
    	 	       String chekeo=prefe.getString("checked","");
    	 		
    	 			if(chekeo.equals("si")){
    	 				
    	 			
    	 	   	Editor editor1=prefe.edit();
	 	        editor1.putString("usuario", et1.getText().toString().trim());
	 	        editor1.commit();
	 	      
	 	       	Editor editor11=prefe.edit();
	 	        editor11.putString("contrasena", et2.getText().toString().trim());
	 	        editor11.commit();
	 	        
	 	       Editor editor111=prefe.edit();
	 	        editor111.putString("perfilUser", dato3.toString().trim());
	 	        editor111.commit();
    	 			}
    	 			
    	 			/*PERFILES
    	 			 * Adminitrador  			 1
    	 			 * Docente 	Universitario	 2
					 * Docente 	Colegio			 3
    	 			 * Escrito					 4
    	 			 * Evento					 5*/
    	 			
    	 			// dial= ProgressDialog.show(MainActivity.this, "", "Cargando datos...",true);
    	 			Thread thr = new Thread(new Runnable() {
    	 				  @Override
    	 				  public void run() {
    	 												
    	 						try{
    	 				       	 //Utilizamos la clase Httpclient para conectar
    	 				       	 httpclient = new DefaultHttpClient();
    	 				       	//Utilizamos la HttpPost para enviar lso datos
    	 				       	    //A la url donde se encuentre nuestro archivo receptor
    	 				              httppost = new HttpPost("http://"+prefe.getString("ip","")+"/aa/identificadocente.php");
    	 				         //Añadimos los datos a enviar 
    	 				           nameValuePairs = new ArrayList<NameValuePair>(2);
    	 				      // Añadimos los elementos a la lista
    	 				         nameValuePairs.add(new BasicNameValuePair("id", prefe.getString("doc","").trim()));
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
    	 				          puente1.sendMessage(sms);
    	 				    		 

    	 				                
    	 				          
    	 				       }catch(Exception e){
    	 				       	 e.printStackTrace();
    	 				       	 dial.dismiss();
    	 				         Message sms = new Message();
    	 				          sms.obj = "cxerror";
    	 				          puente1.sendMessage(sms);
    	 				       }
    	 					
    	 				      
    	 					  
    	 				  }
    	 				  });
    	 				 //Arrancamos el Hilo
    	 				 thr.start();
    	 		
    	 	
    		 }
    		
    		 
     		}
    		 }
    		 }catch(Exception e){
    			 e.printStackTrace();
    			// Toast.makeText(MainActivity.this, "Error de conexion"+e.toString(), Toast.LENGTH_LONG).show();
    			 dial.dismiss();
    			 
    			 AlertDialog.Builder dialogo1 = new AlertDialog.Builder(MainActivity.this);  
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
 		       Editor editor11=prefe.edit();
	 	        editor11.putString("checked", "no");
	 	        editor11.commit();
 		        
    		 }
    	 }
    	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et1=(EditText)findViewById(R.id.username);
		et2=(EditText)findViewById(R.id.password);
		login=(Button)findViewById(R.id.login);
	//	etip=(EditText)findViewById(R.id.nombre);
		che=(CheckBox)findViewById(R.id.check);
		prefe=getSharedPreferences("datos",Context.MODE_PRIVATE);
		// etip.setText(prefe.getString("ip",""));
		 
		adapter = NfcAdapter.getDefaultAdapter(this);
        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        tagDetected.addCategory(Intent.CATEGORY_DEFAULT);
        readTagFilters = new IntentFilter[]{tagDetected};
		
		String chekeo=prefe.getString("checked","");
		
		
		
		
		if(chekeo.equals("si")){
			et1.setText(prefe.getString("usuario",""));
			et2.setText(prefe.getString("contrasena",""));
			che.setChecked(true);
			
			if(prefe.getString("perfilUser", "").trim().equals("3")){
	 			 Intent i=new Intent(MainActivity.this, Admin.class);
	    			// i.putExtra("identidad", user.trim());
	    			 startActivity(i);
	    			finish();
	 		}
			
			if(prefe.getString("perfilUser", "").trim().equals("2")){	
   			 Intent i=new Intent(MainActivity.this, PrincipalActivity.class);
   			// i.putExtra("identidad", user.trim());
   			 startActivity(i);
   			finish();
   	 		}
   	 		if(prefe.getString("perfilUser", "").trim().equals("1")){
   	 			 Intent i=new Intent(MainActivity.this, EscribirTag.class);
   	    			// i.putExtra("identidad", user.trim());
   	    			 startActivity(i);
   	    			finish();
   	 		}
		}
	/*	if(chekeo.equals("no")){
			et1.setText("");
			et2.setText("");
			che.setChecked(false);
		}*/
		
		et2.setOnKeyListener(new View.OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				 if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP)
	                {
	                    //TODO: cuando suelta la tecla ENTER
					
					
								/*		if(textoBusqueda.equals("")){
							Toast.makeText(MainActivity.this, "Indique direccion Ip del servidro", Toast.LENGTH_LONG).show();
										}
										else{*/
										
									
									user = et1.getText().toString();
									pass = et2.getText().toString();
									 if(user.equals("")|| pass.equals(""))
						                {
						                     Toast.makeText(MainActivity.this, "Datos en Blanco no validos", Toast.LENGTH_LONG).show();
						                }else{
						                	dial= ProgressDialog.show(MainActivity.this, "", "Iniciando...",true);
						                	dial.setCancelable(true);
						                	Thread thr = new Thread(new Runnable() {
										  @Override
										  public void run() {
																		
												try{
										       	 //Utilizamos la clase Httpclient para conectar
										       	 httpclient = new DefaultHttpClient();
										       	//Utilizamos la HttpPost para enviar lso datos
										       	    //A la url donde se encuentre nuestro archivo receptor
										              httppost = new HttpPost("http://"+prefe.getString("ip","")+"/aa/login.php");
										         //Añadimos los datos a enviar 
										           nameValuePairs = new ArrayList<NameValuePair>(2);
										      // Añadimos los elementos a la lista
										         nameValuePairs.add(new BasicNameValuePair("user", user.trim()));
										          nameValuePairs.add(new BasicNameValuePair("pass", pass.trim()));
										         
										         //Encapsulamos
										           httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
										         //Lanzamos la petición
										           //eviaa datos a l doc PHP y compila el codigo
										          response = httpclient.execute(httppost);
										           //  Obtenemos el contenido de la respuesta
										        //Conectamos para recibir datos de respuesta
										          HttpEntity ent = response.getEntity();
										          text = EntityUtils.toString(ent);
										          
										          
										       //   Thread.sleep(15000);
										          dial.setCancelable(true);
										        //Enviamos el resultado LIMPIO al Handler para mostrarlo
										          Message sms = new Message();
										          sms.obj = text;
										          puente.sendMessage(sms);
										    		 

										                
										          
										       }catch(Exception e){
										    	   e.printStackTrace();
										       	 //Toast.makeText(MainActivity.this, "error"+e.toString(), Toast.LENGTH_LONG).show();
										       dial.dismiss();
										       Message sms = new Message();
										          sms.obj = "cxerror";
										          puente.sendMessage(sms);
										       }
											
										      
											  
										  }
										  });
										 //Arrancamos el Hilo
										 thr.start();
									
									}
									
					 
	                    return true;
	                }
	                if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)
	                {
	                    //TODO: cuendo se pulsa la tecla ENTER
	                	
	                    return true;
	                }
	                return false;
			}
		});
		
		che.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(((CheckBox) v).isChecked()){
					
			 	       
			 	      
			 	       	Editor editor11=prefe.edit();
			 	        editor11.putString("checked", "si");
			 	        editor11.commit();
				 }else{
					 
			 	       			 	      
			 	       	Editor editor11=prefe.edit();
			 	        editor11.putString("checked", "no");
			 	        editor11.commit();
				 }
			}
		});
		 
		 
		 
		
		login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			
			/*		if(textoBusqueda.equals("")){
		Toast.makeText(MainActivity.this, "Indique direccion Ip del servidro", Toast.LENGTH_LONG).show();
					}
					else{*/
					
				
				user = et1.getText().toString();
				pass = et2.getText().toString();
				 if(user.equals("")|| pass.equals(""))
	                {
	                     Toast.makeText(MainActivity.this, "Datos en Blanco no validos", Toast.LENGTH_LONG).show();
	                }else{
	                	dial= ProgressDialog.show(MainActivity.this, "", "Iniciando...",true);
	                //	dial.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
	        	    //    dial.setMessage("Iniciando...");
	        	    //    dial.setMax(100);
	        	    //    dial.setProgress(0);
	        	        dial.setCancelable(true);
	        	   //     dial.show();
				Thread thr = new Thread(new Runnable() {
					  @Override
					  public void run() {
													
							try{
								//Thread.sleep(20000);
					       	 //Utilizamos la clase Httpclient para conectar
					       	 httpclient = new DefaultHttpClient();
					       	//Utilizamos la HttpPost para enviar lso datos
					       	    //A la url donde se encuentre nuestro archivo receptor
					              httppost = new HttpPost("http://"+prefe.getString("ip","")+"/aa/login.php");
					       	
					         //Añadimos los datos a enviar 
					           nameValuePairs = new ArrayList<NameValuePair>(2);
					      // Añadimos los elementos a la lista
					         nameValuePairs.add(new BasicNameValuePair("user", user.trim()));
					          nameValuePairs.add(new BasicNameValuePair("pass", pass.trim()));
					         
					         //Encapsulamos
					           httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
					         //Lanzamos la petición
					           //eviaa datos a l doc PHP y compila el codigo
					          response = httpclient.execute(httppost);
					           //  Obtenemos el contenido de la respuesta
					        //Conectamos para recibir datos de respuesta
					          HttpEntity ent = response.getEntity();
					          text = EntityUtils.toString(ent);
					          
					         // Thread.sleep(15000);
					          dial.setCancelable(true);
					         
					        //Enviamos el resultado LIMPIO al Handler para mostrarlo
					          Message sms = new Message();
					          sms.obj = text;
					          puente.sendMessage(sms);
					    		 

					        //  Thread.sleep(20000);
					          
							}catch(Exception e){
						    	  e.printStackTrace();
						       	 //Toast.makeText(MainActivity.this, "error"+e.toString(), Toast.LENGTH_LONG).show();
						       dial.dismiss();
						       Message sms = new Message();
						          sms.obj = "cxerror";
						          puente.sendMessage(sms);
						   	
						       
						       }
						
					      
						  
					  }
					  });
					 //Arrancamos el Hilo
					 thr.start();
					
				
				}
				}
			
			//}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
//	public void guardaip(View v){
	//	SharedPreferences preferencias=getSharedPreferences("datos",Context.MODE_PRIVATE);
        /*crear un objeto de la clase Editor y obtener la referencia del objeto
         *  de la clase SharedPreferences que acabamos de crear.*/
//		Editor editor=preferencias.edit();
        /*Mediante el método putString almacenamos en mail el valor 
         * del String cargado en el EditText. */
  //      editor.putString("ip", etip.getText().toString());
        /*llamar al método commit de la clase editor para que el dato quede 
         * almacenado en forma permanente en el archivo de preferencias. 
         * Esto hace que cuando volvamos a arrancar la aplicación se recupere
         *  el último mail ingresado*/
  //      editor.commit();
        //datoip=preferencias.getString("ip", "");
       // finish();
//	}
	
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
    		
    		et1.setText(d3);
        	
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

}
