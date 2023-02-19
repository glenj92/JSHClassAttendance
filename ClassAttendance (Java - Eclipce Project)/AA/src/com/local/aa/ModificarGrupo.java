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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ModificarGrupo extends Activity {
	private Spinner jormod,permod;
	private EditText anomod,curmod,matemod;
	private Button btnUpdate;
	private ImageButton btncursomod,btnmatemod;
	private String codGrupo;
	private CheckBox cb1,cb2;
	
	HttpPost httppost;
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpclient;
    InputStream inputStream;
    SharedPreferences prefe ;
    List<NameValuePair> nameValuePairs;
    ArrayAdapter<String> adapterjor;
    ProgressDialog dial;
    
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		//super.onActivityResult(requestCode, resultCode, data);
		 if(requestCode==111 && resultCode==RESULT_OK){
			  String res=data.getExtras().getString("resultado");
			  StringTokenizer tok=new StringTokenizer(res,"-");
         		while(tok.hasMoreTokens()){
         			curmod.setText(tok.nextToken().trim());
         			tok.nextToken();
         			matemod.setText("");
         		}
			  
		  }
		 if(requestCode==222 && resultCode==RESULT_OK){
			  String res=data.getExtras().getString("resultado");
			  StringTokenizer tok=new StringTokenizer(res,"-");
        		while(tok.hasMoreTokens()){
          	matemod.setText(tok.nextToken().trim());
          	tok.nextToken();
        		}
		  }
	}
    
    private Handler puentegrupdate = new Handler() {
     	 @Override
     	 public void handleMessage(Message msg) {
     		try{
     		 
     		String cad=(String)msg.obj;
     		dial.dismiss();
if(msg.obj.toString().trim().equals("nofecha")){
    			
    			AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ModificarGrupo.this);  
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
        		AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ModificarGrupo.this);  
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
        				 
    			 	        
        }else	if(cad.equals("error")){
     			AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ModificarGrupo.this);  
    	        dialogo1.setTitle("ALERTA");  
    	        dialogo1.setMessage("Ya existe un Grupo con los mismos datos!\nPor favor intente nuevamente"); 
    	      //obligamos al usuario a pulsar los botones para cerrarlo
    	        dialogo1.setCancelable(false);  
    	        
    	       dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {  
    	            public void onClick(DialogInterface dialog, int id) {
    	            	
    	            	dialog.cancel();
    	            	
    	            }  
    	        });  
    	                  
    	        dialogo1.show(); 
     		}else if(cad.equals("1")){
     		AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ModificarGrupo.this);  
	        dialogo1.setTitle("EXITO");  
	        dialogo1.setMessage("Actualizacion exitosa"); 
	      //obligamos al usuario a pulsar los botones para cerrarlo
	        dialogo1.setCancelable(false);  
	        
	       dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {  
	            public void onClick(DialogInterface dialog, int id) {
	            	
	            	dialog.cancel();
	            	finish();
	            }  
	        });  
	                  
	        dialogo1.show(); 
	        
     		}else if(cad.equals("0")){
     			AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ModificarGrupo.this);  
    	        dialogo1.setTitle("MENSAJE");  
    	        dialogo1.setMessage("No hubo modificaciones"); 
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
     		}catch(Exception e){
      			 e.printStackTrace();
      			// Toast.makeText(MainActivity.this, "Error de conexion"+e.toString(), Toast.LENGTH_LONG).show();
      			 dial.dismiss();
      			 
      			 AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ModificarGrupo.this);  
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
    
    
    
    
    private Handler puentegru1 = new Handler() {
   	 @Override
   	 public void handleMessage(Message msg) {
   	 //Mostramos el mensage recibido del servido en pantalla
   	// Toast.makeText(getApplicationContext(), (String)msg.obj, Toast.LENGTH_LONG).show();
   		 try{
   		String cad=(String)msg.obj;
   		if(msg.obj.toString().trim().equals("cxerror")){
    		AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ModificarGrupo.this);  
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
     		String datcur=tok.nextToken();
     		String datmate=tok.nextToken();
     		String datano=tok.nextToken();
     		String datper=tok.nextToken().trim();
     		String datjor=tok.nextToken().trim();
     		
     		if(prefe.getString("perfilUser", "").equals("2")||prefe.getString("perfilUser", "").equals("3")){	
     			
	     		if(datjor.equals("MANANA")){
	     			jormod.setSelection(1);
	     		}
	     		if(datjor.equals("TARDE")){
	     			jormod.setSelection(2);
	     		}
	     		if(datjor.equals("NOCHE")){
	     			jormod.setSelection(3);
	     		}
	     		
	     		if(datper.equals("1")){
	     			permod.setSelection(1);
	     		}
	     		if(datper.equals("2")){
	     			permod.setSelection(2);
	     		}
	     		
     		}
     		if(prefe.getString("perfilUser", "").equals("1")){	
     			if(datjor.equals("UNICA")){
	     			jormod.setSelection(1);
	     		}
	     		if(datjor.equals("MANANA")){
	     			jormod.setSelection(2);
	     		}
	     		if(datjor.equals("TARDE")){
	     			jormod.setSelection(3);
	     		}
	     		if(datjor.equals("NOCHE")){
	     			jormod.setSelection(4);
	     		}
	     		
	     		if(datper.equals("1")){
	     			permod.setSelection(1);
	     		}
	     		if(datper.equals("2")){
	     			permod.setSelection(2);
	     		}
	     		if(datper.equals("3")){
	     			permod.setSelection(1);
	     		}
	     		if(datper.equals("4")){
	     			permod.setSelection(2);
	     		}
     		}
     		anomod.setText(datano.trim());
     		curmod.setText(datcur.trim());
     		matemod.setText(datmate.trim());
     		//dial.dismiss();
     		
     	}
   		dial.dismiss();
    }
   		}catch(Exception e){
  			 e.printStackTrace();
  			// Toast.makeText(MainActivity.this, "Error de conexion"+e.toString(), Toast.LENGTH_LONG).show();
  			 dial.dismiss();
  			 
  			 AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ModificarGrupo.this);  
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
		setContentView(R.layout.activity_modifcar_grupo);
		prefe=getSharedPreferences("datos",Context.MODE_PRIVATE);
		
		btnUpdate=(Button)findViewById(R.id.btnUpdateMod);
		btncursomod=(ImageButton)findViewById(R.id.ibCursoMod);
		btnmatemod=(ImageButton)findViewById(R.id.ibMateMod);
		anomod=(EditText)findViewById(R.id.etano1TvMod);
		curmod=(EditText)findViewById(R.id.etcursoModTv);
		matemod=(EditText)findViewById(R.id.ettvMateMod);
		jormod=(Spinner)findViewById(R.id.spJorMod);
		permod=(Spinner)findViewById(R.id.spPeriodoMod);
		
		anomod.setEnabled(false);
		curmod.setEnabled(false);
		matemod.setEnabled(false);
		
		if(prefe.getString("perfilUser", "").equals("2")||prefe.getString("perfilUser", "").equals("3")){
			String []opjor={"(ninguno)","MANANA","TARDE","NOCHE"};
			String []opper={"(ninguno)","1","2"};
			adapterjor = new ArrayAdapter<String>(ModificarGrupo.this,android.R.layout.simple_spinner_item, opjor);
		    jormod.setAdapter(adapterjor);
		    ArrayAdapter<String> adapterper = new ArrayAdapter<String>(ModificarGrupo.this,android.R.layout.simple_spinner_item, opper);
		    permod.setAdapter(adapterper);
		}
		if(prefe.getString("perfilUser", "").equals("1")){
			String []opjor={"(ninguno)","UNICA","MANANA","TARDE","NOCHE"};
			String []opper={"(ninguno)","1","2","3","4"};
			adapterjor = new ArrayAdapter<String>(ModificarGrupo.this,android.R.layout.simple_spinner_item, opjor);
		    jormod.setAdapter(adapterjor);
		    ArrayAdapter<String> adapterper = new ArrayAdapter<String>(ModificarGrupo.this,android.R.layout.simple_spinner_item, opper);
		    permod.setAdapter(adapterper);
		}
	
		Bundle bundle = getIntent().getExtras();
		codGrupo=bundle.getString("datoGrupo");
		dial= ProgressDialog.show(ModificarGrupo.this, "", "Cargando datos...",true);
		dial.setCancelable(true);
   	 Thread thrgru1 = new Thread(new Runnable() {
			  @Override
			  public void run() {
	
											
					try{

			       	 httpclient = new DefaultHttpClient();
			              httppost = new HttpPost("http://"+prefe.getString("ip","")+"/aa/grupodocente3.php");
			           nameValuePairs = new ArrayList<NameValuePair>(2);
			         nameValuePairs.add(new BasicNameValuePair("IdG", codGrupo.trim()));
			           httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
			          response = httpclient.execute(httppost);
			     HttpEntity ent = response.getEntity();
			          String text = EntityUtils.toString(ent);
			      
			          Message sms = new Message();
			          sms.obj = text;
			          puentegru1.sendMessage(sms);
			     
			                
			          
			       }catch(Exception e){
			    	   e.printStackTrace();
			    	   dial.dismiss();
			    	   Message sms = new Message();
				          sms.obj = "cxerror";
				          puentegru1.sendMessage(sms);
			       }
			      
	
				  
			  }
			  });
			 //Arrancamos el Hilo
			 thrgru1.start();
			 
			 
			 btnUpdate.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					dial= ProgressDialog.show(ModificarGrupo.this, "", "Cargando datos...",true);
					dial.setCancelable(true);
					  Thread thrgrupdate = new Thread(new Runnable() {
						  @Override
						  public void run() {
				
														
								try{

						       	 httpclient = new DefaultHttpClient();
						              httppost = new HttpPost("http://"+prefe.getString("ip","")+"/aa/modificargrupodocente.php");
						           nameValuePairs = new ArrayList<NameValuePair>(2);
						         nameValuePairs.add(new BasicNameValuePair("Ano", anomod.getText().toString().trim()));
						         nameValuePairs.add(new BasicNameValuePair("Jornada", jormod.getSelectedItem().toString()));
						         nameValuePairs.add(new BasicNameValuePair("Periodo", permod.getSelectedItem().toString()));
						         nameValuePairs.add(new BasicNameValuePair("CodCurso", curmod.getText().toString().trim()));
						         nameValuePairs.add(new BasicNameValuePair("CodMate", matemod.getText().toString().trim()));
						         nameValuePairs.add(new BasicNameValuePair("IdDoc", prefe.getString("doc","")));
						         nameValuePairs.add(new BasicNameValuePair("IdGru", codGrupo));
						           httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
						          response = httpclient.execute(httppost);
						     HttpEntity ent = response.getEntity();
						          String text = EntityUtils.toString(ent);
						      
						          Message sms = new Message();
						          sms.obj = text;
						          puentegrupdate.sendMessage(sms);
						     
						               
						          
						       }catch(Exception e){
						    	   e.printStackTrace();
						    	   dial.dismiss();
						    	   Message sms = new Message();
							          sms.obj = "cxerror";
							          puentegrupdate.sendMessage(sms);
						       }
						  
						  }
						  });
						 //Arrancamos el Hilo
						 thrgrupdate.start();
				}
			});
	    
			 
			 btncursomod.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					
					Intent i=new Intent(ModificarGrupo.this, ListaCursoModificar.class);
					startActivityForResult(i, 111);
					
					
				}
			});
			 
			btnmatemod.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(curmod.getText().toString().equals("")){
						//mensaje debe selecionar un curso
						Toast.makeText(ModificarGrupo.this, "Debe escoger un Curso o Programa", Toast.LENGTH_LONG).show();
					
					}else{
						Intent i=new Intent(ModificarGrupo.this, ListaAsignaturaModificar.class);
						i.putExtra("programa", curmod.getText().toString());
						startActivityForResult(i, 222);
					}
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
