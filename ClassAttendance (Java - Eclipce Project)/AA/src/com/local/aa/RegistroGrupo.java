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
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class RegistroGrupo extends Activity {

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		//super.onActivityResult(requestCode, resultCode, data);
		 if(requestCode==111 && resultCode==RESULT_OK){
			  String res=data.getExtras().getString("resultado");
			  StringTokenizer tok=new StringTokenizer(res,"-");
         		while(tok.hasMoreTokens()){
         			curregi.setText(tok.nextToken().trim());
         			tok.nextToken();
         			materegi.setText("");
         		}
			  
		  }
		 if(requestCode==222 && resultCode==RESULT_OK){
			  String res=data.getExtras().getString("resultado");
			  StringTokenizer tok=new StringTokenizer(res,"-");
        		while(tok.hasMoreTokens()){
          	materegi.setText(tok.nextToken().trim());
          	tok.nextToken();
        		}
		  }
	}


	private EditText anoregi;
	//private EditText nomdocregis;
	private Spinner jorregi,perregi;
	private Button btnguardarregistro;
	private EditText materegi,curregi;
	private ImageButton btnimgcurso,btnimgmate;
	
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

	
	 
	 private Handler puentesavegru = new Handler() {
    	 @Override
    	 public void handleMessage(Message msg) {
    		 try{
    	 //Mostramos el mensage recibido del servido en pantalla
    	// Toast.makeText(getApplicationContext(), (String)msg.obj, Toast.LENGTH_LONG).show();
    		String cad=(String)msg.obj;
    		if(msg.obj.toString().trim().equals("nofecha")){
    			
    			AlertDialog.Builder dialogo1 = new AlertDialog.Builder(RegistroGrupo.this);  
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
        		AlertDialog.Builder dialogo1 = new AlertDialog.Builder(RegistroGrupo.this);  
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
    		
    		//Toast.makeText(RegistroGrupo.this, cad, Toast.LENGTH_LONG).show();
    		AlertDialog.Builder dialogo1 = new AlertDialog.Builder(RegistroGrupo.this);  
	        dialogo1.setTitle("MENSAJE");  
	        dialogo1.setMessage(cad); 
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
       			 
       			 AlertDialog.Builder dialogo1 = new AlertDialog.Builder(RegistroGrupo.this);  
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
		setContentView(R.layout.activity_registro_grupo);
		prefe=getSharedPreferences("datos",Context.MODE_PRIVATE);
		btnimgcurso=(ImageButton)findViewById(R.id.ibCursoRegi);
		btnimgmate=(ImageButton)findViewById(R.id.ibMateRegi);
		btnguardarregistro=(Button)findViewById(R.id.btGuardarNewRegi);
		//nomdocregis=(EditText)findViewById(R.id.nombreRegi);
		materegi=(EditText)findViewById(R.id.materia1Regi);
		curregi=(EditText)findViewById(R.id.curso1Regi);
		jorregi=(Spinner)findViewById(R.id.jornadaRegi);
		perregi=(Spinner)findViewById(R.id.periodoRegi);
		anoregi=(EditText)findViewById(R.id.etanoRegi);
		// nomdocregis.setEnabled(false);
		 materegi.setEnabled(false);
		 curregi.setEnabled(false);
		 anoregi.setEnabled(false);
		 
		// nomdocregis.setText(prefe.getString("nombreDoc", "").toUpperCase()+" "+prefe.getString("apellidoDoc", "").toUpperCase());

		 if(prefe.getString("perfilUser", "").equals("2")||prefe.getString("perfilUser", "").equals("3")){
			 String []opjor={"(ninguno)","MANANA","TARDE","NOCHE"};
			 ArrayAdapter<String> adapterjor = new ArrayAdapter<String>(RegistroGrupo.this,android.R.layout.simple_spinner_item, opjor);
		      	jorregi.setAdapter(adapterjor);
		 String []opper={"(ninguno)","1","2"};
		 ArrayAdapter<String> adapterper = new ArrayAdapter<String>(RegistroGrupo.this,android.R.layout.simple_spinner_item, opper);
	      	perregi.setAdapter(adapterper);
		 }
		 if(prefe.getString("perfilUser", "").equals("1")){
			 String []opjor={"(ninguno)","UNICA","MANANA","TARDE","NOCHE"};
			 ArrayAdapter<String> adapterjor = new ArrayAdapter<String>(RegistroGrupo.this,android.R.layout.simple_spinner_item, opjor);
		      	jorregi.setAdapter(adapterjor);
			 String []opper={"(ninguno)","1","2","3","4"};
			 ArrayAdapter<String> adapterper = new ArrayAdapter<String>(RegistroGrupo.this,android.R.layout.simple_spinner_item, opper);
		      	perregi.setAdapter(adapterper);
			 }
		anoregi.setText(getDateYears());
		 
      	
				 btnimgcurso.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							
							Intent i=new Intent(RegistroGrupo.this, ListaCursoRegistrar.class);
							startActivityForResult(i, 111);
							//startActivity(i);
							
					
							
						}
					});
					 
					btnimgmate.setOnClickListener(new View.OnClickListener() {
						
						@Override
						public void onClick(View v) {
							// TODO Auto-generated method stub
							if(curregi.getText().toString().equals("")){
						//mensaje debe selecionar un curso
						Toast.makeText(RegistroGrupo.this, "Debe escoger un Curso o Programa", Toast.LENGTH_LONG).show();
							
							}else{
								Intent i=new Intent(RegistroGrupo.this, ListaAsignaturaRegistrar.class);
								i.putExtra("programa", curregi.getText().toString());
								startActivityForResult(i, 222);
								//startActivity(i);
							}
				
						}
					}); 
					 
						 
						 btnguardarregistro.setOnClickListener(new View.OnClickListener() {
							
							@Override
							public void onClick(View v) {
								// TODO Auto-generated method stub
								 
							if(jorregi.getSelectedItem().toString().equals("(ninguno)")||
										 perregi.getSelectedItem().toString().equals("(ninguno)")||
										 anoregi.getText().toString().equals("")||
										 curregi.getText().toString().equals("")||
										 materegi.getText().toString().equals("")||
										 prefe.getString("doc","").toString().equals("")){
								
									 
								AlertDialog.Builder dialogo1 = new AlertDialog.Builder(RegistroGrupo.this);  
							      dialogo1.setTitle("MENSAJE");  
							      dialogo1.setMessage("No se permiten datos en blanco"); 
							    //obligamos al usuario a pulsar los botones para cerrarlo
							      dialogo1.setCancelable(false);  
							      
							     dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {  
							          public void onClick(DialogInterface dialog, int id) {
							          	
							          	
							          	
							          	dialog.cancel();
							          }  
							      });  
							                
							      dialogo1.show();
							}else{		
							
								dial= ProgressDialog.show(RegistroGrupo.this, "", "Guardando Grupo...",true);
								dial.setCancelable(true);
								 Thread thrsavegru = new Thread(new Runnable() {
									  @Override
									  public void run() {
							
																	
											try{

									       	 httpclient = new DefaultHttpClient();
									              httppost = new HttpPost("http://"+prefe.getString("ip","")+"/aa/registrargrupo.php");
									           nameValuePairs = new ArrayList<NameValuePair>(2);
									         nameValuePairs.add(new BasicNameValuePair("Jornada", jorregi.getSelectedItem().toString()));
									         nameValuePairs.add(new BasicNameValuePair("Periodo", perregi.getSelectedItem().toString()));
									         nameValuePairs.add(new BasicNameValuePair("Ano", anoregi.getText().toString().trim()));
									         nameValuePairs.add(new BasicNameValuePair("CodCurso", curregi.getText().toString().trim()));
									         nameValuePairs.add(new BasicNameValuePair("CodMate", materegi.getText().toString().trim()));
									         nameValuePairs.add(new BasicNameValuePair("IdDoc", prefe.getString("doc","")));
									           httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
									          response = httpclient.execute(httppost);
									     HttpEntity ent = response.getEntity();
									          String text = EntityUtils.toString(ent);
									      
									          Message sms = new Message();
									          sms.obj = text;
									          puentesavegru.sendMessage(sms);
									     
									                
									          
									       }catch(Exception e){
									    	   e.printStackTrace();
									    	   dial.dismiss();
									    	   Message sms = new Message();
										          sms.obj = "cxerror";
										          puentesavegru.sendMessage(sms);
									       }
									      
							
										  
									  }
									  });
									 //Arrancamos el Hilo
									 thrsavegru.start();
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
