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
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.SearchView.OnQueryTextListener;

public class ListaCursoModificar extends Activity implements OnQueryTextListener {
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		//return super.onCreateOptionsMenu(menu);
getMenuInflater().inflate(R.menu.menu_buscar, menu);
		
		MenuItem searchItem = menu.findItem(R.id.action_buscarr);
        mSearchView = (SearchView) searchItem.getActionView();
        mSearchView.setQueryHint("Search…");
        mSearchView.setOnQueryTextListener(this);
        return true;
	}


	private SearchView mSearchView;
	private MenuItem saveItem;
	
	HttpPost httppost;
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpclient;
    InputStream inputStream;
    SharedPreferences prefe ;
    List<NameValuePair> nameValuePairs;
    String[] itemmate;
    List<String>itemsmate=new ArrayList<String>();
    List<String>itemscur=new ArrayList<String>();
    ProgressDialog dial;
    private ListView lvcurso;
    private ArrayList<String> listaCurso;
	private ArrayAdapter<String> adaptCurso;
	
	
	private Handler puentecur = new Handler() {
	   	 @Override
	   	 public void handleMessage(Message msg) {
	   	 //Mostramos el mensage recibido del servido en pantalla
	   	// Toast.makeText(getApplicationContext(), (String)msg.obj, Toast.LENGTH_LONG).show();
	   		 try{
	   			 String cad=(String)msg.obj;
	   			if(msg.obj.toString().trim().equals("cxerror")){
	   	    		AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListaCursoModificar.this);  
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
	     			listaCurso.add(tok.nextToken().trim().toUpperCase());
	     		}
	     		lvcurso.setAdapter(adaptCurso);
	     	
	   		dial.dismiss();
	   	    }
	   		}catch(Exception e){
	   			 e.printStackTrace();
	   			// Toast.makeText(MainActivity.this, "Error de conexion"+e.toString(), Toast.LENGTH_LONG).show();
	   			 dial.dismiss();
	   			 
	   			 AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListaCursoModificar.this);  
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
		setContentView(R.layout.lista_curso_modificar);
		prefe=getSharedPreferences("datos",Context.MODE_PRIVATE);
		
		lvcurso=(ListView)findViewById(R.id.lvlistacursomodificarcursossssss);
		listaCurso=new ArrayList<String>();
		adaptCurso =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listaCurso);
		
		dial= ProgressDialog.show(ListaCursoModificar.this, "", "Cargando datos...",true);
		dial.setCancelable(true);
		  Thread thrcurso = new Thread(new Runnable() {
			  @Override
			  public void run() {
	
											
					try{

			       	 httpclient = new DefaultHttpClient();
			              httppost = new HttpPost("http://"+prefe.getString("ip","")+"/aa/cargarCurso.php");
			          response = httpclient.execute(httppost);
			    HttpEntity ent = response.getEntity();
			          String text = EntityUtils.toString(ent);
			      
			          Message sms = new Message();
			          sms.obj = text;
			          puentecur.sendMessage(sms);
			     
			               
			          
			       }catch(Exception e){
			    	   e.printStackTrace();
			    	   dial.dismiss();
			    	   Message sms = new Message();
				          sms.obj = "cxerror";
				          puentecur.sendMessage(sms);   
			       }
			  
			  }
			  });
			 //Arrancamos el Hilo
			 thrcurso.start();
			 
			 
			 lvcurso.setOnItemClickListener(new OnItemClickListener() {
		            @Override
		            public void onItemClick(AdapterView<?> parent, View v, final int posicion, long id) {
		            	
		            	//lv1.setOnTouchListener(new ListenerTouchViewFlipper());
		            	
		            	
		           String d=lvcurso.getItemAtPosition(posicion).toString();
		           Intent intent=new Intent();
		   		intent.putExtra("resultado", d);
		   		setResult(RESULT_OK, intent);
		   		finish();
		            	
		            	
		            }
			 });
			 
			 
	}



	@Override
	public boolean onQueryTextChange(String newText) {
		// TODO Auto-generated method stub
		return false;
	}



	@Override
	public boolean onQueryTextSubmit(final String query) {
		// TODO Auto-generated method stub
		listaCurso.removeAll(listaCurso);
		 boolean algunDigito = false; 
	        boolean algunaLetra = false; 
	        for (int i = 0; i < query.length(); i++) { 
	            if (Character.isDigit(query.charAt(i))) { 
	                //es un digito 
	                algunDigito = true; 
	            } else { 
	                algunaLetra = true; 
	                //no es un digito 
	            } 
	        } 
	        if(algunDigito && !algunaLetra){ 
	        	//NUMERICO
	        	dial= ProgressDialog.show(ListaCursoModificar.this, "", "Buscando por Codigo...",true);
	        	dial.setCancelable(true);
	            Thread thr = new Thread(new Runnable() {
	  			  @Override
	  			  public void run() {
	  											
	  					try{
	  			       	 //Utilizamos la clase Httpclient para conectar
	  			       	 httpclient = new DefaultHttpClient();
	  			       	//Utilizamos la HttpPost para enviar lso datos
	  			       	    //A la url donde se encuentre nuestro archivo receptor
	  			              httppost = new HttpPost("http://"+prefe.getString("ip","")+"/aa/buscarcargarCurso.php");
	  			         //Añadimos los datos a enviar 
	  			           nameValuePairs = new ArrayList<NameValuePair>(2);
	  			      // Añadimos los elementos a la lista
	  			         nameValuePairs.add(new BasicNameValuePair("Idc", query.toString().trim()));
	  			          
	  			         
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
	  			          puentecur.sendMessage(sms);

	  			       }catch(Exception e){
	  			    
	  			    	   e.printStackTrace();
	  			    	 dial.dismiss();
	  			    	Message sms = new Message();
	  			          sms.obj = "cxerror";
	  			          puentecur.sendMessage(sms);
	  			          }
	  				
	  			      
	  				  
	  			  }
	  			  });
	  			 //Arrancamos el Hilo
	  			 thr.start();
	        	
	        }else if (!algunDigito && algunaLetra){ 
	        	//LETRA
	        	dial= ProgressDialog.show(ListaCursoModificar.this, "", "Buscando por Nombres...",true);
	        	dial.setCancelable(true);
	        	//dial.setCancelable(true);
	            Thread thr = new Thread(new Runnable() {
	  			  @Override
	  			  public void run() {
	  											
	  					try{
	  			       	 //Utilizamos la clase Httpclient para conectar
	  			       	 httpclient = new DefaultHttpClient();
	  			       	//Utilizamos la HttpPost para enviar lso datos
	  			       	    //A la url donde se encuentre nuestro archivo receptor
	  			              httppost = new HttpPost("http://"+prefe.getString("ip","")+"/aa/buscarcargarCurso.php");
	  			         //Añadimos los datos a enviar 
	  			           nameValuePairs = new ArrayList<NameValuePair>(2);
	  			      // Añadimos los elementos a la lista
	  			         nameValuePairs.add(new BasicNameValuePair("Nom", query.toString().trim()));
	  			          
	  			         
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
	  			          puentecur.sendMessage(sms);

	  			       }catch(Exception e){
	  			    	   e.printStackTrace();
	  			    	 dial.dismiss();
	  			    	Message sms = new Message();
	  			          sms.obj = "cxerror";
	  			          puentecur.sendMessage(sms);
	  			          }
	  				
	  			      
	  				  
	  			  }
	  			  });
	  			 //Arrancamos el Hilo
	  			 thr.start();
	        	
	        }
	        mSearchView.setQuery("", false);
	    		mSearchView.onActionViewCollapsed();
		return false;
	}
	
	

}
