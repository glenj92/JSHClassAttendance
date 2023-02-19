package com.local.aa;



import java.io.InputStream;
import java.util.ArrayList;
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
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PrincipalActivity extends Activity {
	
	/*
	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);
		if(intent.ACTION_MANAGE_NETWORK_USAGE.equals(intent.getAction())){
			Toast.makeText(getApplicationContext(), "seperdio datos", 
			    	   Toast.LENGTH_LONG).show();
		}
	}

*/
	HttpPost httppost;
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpclient;
    InputStream inputStream;
    SharedPreferences prefe ,prefedoc;
    List<NameValuePair> nameValuePairs;
    ProgressDialog dial;
    private Button regiGrupo,modifGrupo,elimiGrupo,vistaGrupo;
    

	private Button tomarAsis;
	private TextView tv1bienve;
	
	 public boolean isOnline() {
	        ConnectivityManager cm = 
	             (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	        NetworkInfo netInfo = cm.getActiveNetworkInfo();
	        if (netInfo != null && netInfo.isConnected()) {
	            return true;
	        }
	        return false;
	    }
	
	 private Admin ad=new Admin();
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case R.id.salir:
			// Toast.makeText(this, "buscar", Toast.LENGTH_SHORT).show();
			if(prefe.getString("perfilUser", "").trim().equals("3")){
			ad.aad.finish();
			}
			
			Editor editor1=prefe.edit();
 	        editor1.putString("usuario", "");
 	        editor1.commit();
 	      
 	       	Editor editor11=prefe.edit();
 	        editor11.putString("contrasena", "");
 	        editor11.commit();
 	        
 	       Editor editor111=prefe.edit();
 	        editor111.putString("doc", "");
 	        editor111.commit();
 	        
 	       Editor editor1111=prefe.edit();
 	        editor1111.putString("checked", "");
 	        editor1111.commit();
 	        
 	       Editor editor11111=prefe.edit();
 	        editor11111.putString("perfilUser", "");
 	        editor11111.commit();
 	        
 	     /*  Editor editor111111=prefe.edit();
 	        editor111111.putString("tipoDoc", "");
 	        editor111111.commit();*/
			
			Intent i=new Intent(PrincipalActivity.this, MainActivity.class);
			startActivity(i);
			finish();
			
 			
			break;
		
		
			default:
			
		}
		return super.onOptionsItemSelected(item);
	}
	
	/*
	 private Handler puente = new Handler() {
    	 @Override
    	 public void handleMessage(Message msg) {
    	 //Mostramos el mensage recibido del servido en pantalla
    		try{
    		 String dat= msg.obj.toString();	
    		 if(msg.obj.toString().trim().equals("cxerror")){
    	    		AlertDialog.Builder dialogo1 = new AlertDialog.Builder(PrincipalActivity.this);  
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
     			tv1bienve.setText("Bienvenid(a) "+d1.toUpperCase()+" "+d2.toUpperCase());
     			
     			Editor editor=prefe.edit();
      	        editor.putString("nombreDoc", d1);
      	        editor.commit();
      	        
      	      Editor editor1=prefe.edit();
    	        editor1.putString("apellidoDoc", d2);
    	        editor1.commit();
     		}
    		 
    		 
    		 
  	        
    		 dial.dismiss();
    	    }
    	 }catch(Exception e){
			 e.printStackTrace();
			// Toast.makeText(MainActivity.this, "Error de conexion"+e.toString(), Toast.LENGTH_LONG).show();
			 dial.dismiss();
			 
			 AlertDialog.Builder dialogo1 = new AlertDialog.Builder(PrincipalActivity.this);  
		        dialogo1.setTitle("Error de conexion");  
		        dialogo1.setMessage("Fallo la conexion con el servidor \n"+e.toString()); 
		      //obligamos al usuario a pulsar los botones para cerrarlo
		        dialogo1.setCancelable(false);  
		        
		       dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {  
		            public void onClick(DialogInterface dialog, int id) {
		            	
		            	dialog.cancel();
		            }  
		        });  
		                  
		  
		        
		 }
    		 
    	 }
    	};*/
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_principal);
		tv1bienve=(TextView)findViewById(R.id.bienvenida);
		tomarAsis=(Button)findViewById(R.id.botonasisnfcc);
		
		regiGrupo=(Button)findViewById(R.id.btnregistrogru);
		modifGrupo=(Button)findViewById(R.id.btnModificarUpdateGrupo);
		elimiGrupo=(Button)findViewById(R.id.btneliminargrupo);
		vistaGrupo=(Button)findViewById(R.id.btnvistasgrupo);
		//cerrar=(Button)findViewById(R.id.button2);
		prefe=getSharedPreferences("datos",Context.MODE_PRIVATE);
		
		
		tv1bienve.setText("Bienvenid(a) "+prefe.getString("nombreDoc", "").toUpperCase()+" "+prefe.getString("apellidoDoc", "").toUpperCase());
		//Bundle extras=getIntent().getExtras();
		//ident=extras.getString("identidad");
		//prefedoc=getSharedPreferences("datos", Context.MODE_PRIVATE);
		
		
	  /*  dial= ProgressDialog.show(PrincipalActivity.this, "", "Cargando datos...",true);
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
			          puente.sendMessage(sms);
			    		 

			                
			          
			       }catch(Exception e){
			       	 e.printStackTrace();
			       	 dial.dismiss();
			         Message sms = new Message();
			          sms.obj = "cxerror";
			          puente.sendMessage(sms);
			       }
				
			      
				  
			  }
			  });
			 //Arrancamos el Hilo
			 thr.start();
		
			*/
			 tomarAsis.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Intent i=new Intent(PrincipalActivity.this, ListadoNfcActivity.class);
					startActivity(i);
				}
			});
			
		
			
		
			 regiGrupo.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent i=new Intent(PrincipalActivity.this, RegistroGrupo.class);
						startActivity(i);
					}
				});
				
				modifGrupo.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent i=new Intent(PrincipalActivity.this, ModificargrupoDos.class);
						startActivity(i);
					}
				});
				
				elimiGrupo.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
					
						Intent i=new Intent(PrincipalActivity.this, EliminarGrupo.class);
						startActivity(i);
						
					}
				});
				
				
				vistaGrupo.setOnClickListener(new View.OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						Intent i=new Intent(PrincipalActivity.this, Vistas.class);
						startActivity(i);
					}
				});
			 
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_principal, menu);
		return true;
	}
	
	

}
