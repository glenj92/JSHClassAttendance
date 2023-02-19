package com.local.aa;

import java.io.InputStream;
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
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ModificargrupoDos extends Activity {
	
	private ArrayList<Grupos> grup=new ArrayList<Grupos>();
	private AdaptadorGrupos adaptadorGrupos;

	private TextView tvgruposmodicardos;
	private ListView lvmodigrupodos;
	
	HttpPost httppost;
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpclient;
    InputStream inputStream;
    SharedPreferences prefe ;
    List<NameValuePair> nameValuePairs;
    ArrayAdapter<String> adapterjor;
    ProgressDialog dial;
    
    private Handler puentegru = new Handler() {
      	 @Override
      	 public void handleMessage(Message msg) {
      		 try{
      	 //Mostramos el mensage recibido del servido en pantalla
      	// Toast.makeText(getApplicationContext(), (String)msg.obj, Toast.LENGTH_LONG).show();
String cad=(String)msg.obj;
if(msg.obj.toString().trim().equals("cxerror")){
	AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ModificargrupoDos.this);  
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
	     		String datgru=tok.nextToken();
	     		String datnomMate=tok.nextToken();
	     		grup.add(new Grupos(datgru, datnomMate.toUpperCase()));
	     		adaptadorGrupos=new AdaptadorGrupos(ModificargrupoDos.this, grup);
	     		
	     		//arrayList.add(datgru+"\n"+datnomMate);
	     		
	     	}
	   		 
	   		lvmodigrupodos.setAdapter(adaptadorGrupos);
	   		dial.dismiss();
	   		
	   		
	   		if(grup.size()==0){
	   			AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ModificargrupoDos.this);  
		        dialogo1.setTitle("MENSAJE");  
		        dialogo1.setMessage("No hay Grupos para modificar \n"); 
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
      			 
      			 AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ModificargrupoDos.this);  
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
		setContentView(R.layout.activity_modificar_grupo_dos);
		tvgruposmodicardos=(TextView)findViewById(R.id.tvmodificargruposdos);
		lvmodigrupodos=(ListView)findViewById(R.id.lvmodificargruposdossss);
		prefe=getSharedPreferences("datos",Context.MODE_PRIVATE);
		
		
		dial= ProgressDialog.show(ModificargrupoDos.this, "", "Cargando Grupos...",true);
		dial.setCancelable(true);
		  Thread thrgru = new Thread(new Runnable() {
			  @Override
			  public void run() {
	
											
					try{

			       	 httpclient = new DefaultHttpClient();
			              httppost = new HttpPost("http://"+prefe.getString("ip","")+"/aa/grupodocente2.php");
			           nameValuePairs = new ArrayList<NameValuePair>(2);
			         nameValuePairs.add(new BasicNameValuePair("Ano", getDateYears().trim()));
			         nameValuePairs.add(new BasicNameValuePair("Idd", prefe.getString("doc","")));
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
		
			 
			 lvmodigrupodos.setOnItemClickListener(new OnItemClickListener() {
					@Override
		            public void onItemClick(AdapterView<?> parent, View v, final int posicion, long id) {
						
						Intent i=new Intent(ModificargrupoDos.this, ModificarGrupo.class);
		        		   i.putExtra("datoGrupo", grup.get(posicion).getGrupo().toString());
		       			startActivity(i);
		       			finish();
						
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
