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
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AbsListView.MultiChoiceModeListener;

public class ListaEscusasEstudiantes extends Activity {
	private SelectionAdapter mAdapter;
	private TextView taexcunu;
	private ListView lvexcusa;
	
	private Bundle bundle;
	private String did,dnom,dgru;
	
	ArrayList<String> listaexcu;
	 ArrayAdapter<String> adaptexcu;
	
	HttpPost httppost;
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpclient;
    InputStream inputStream;
    SharedPreferences prefe ;
    List<NameValuePair> nameValuePairs;
  //  private  ArrayAdapter<String> adapterjor;
    ProgressDialog dial;
    
    private Handler puenteExcu = new Handler() {
     	 @Override
     	 public void handleMessage(Message msg) {
     		// listaMate.removeAll(listaMate);
     		 try{
     		String cad=(String)msg.obj;
     		if(msg.obj.toString().trim().equals("cxerror")){
        		AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListaEscusasEstudiantes.this);  
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
     			AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListaEscusasEstudiantes.this);  
  	        dialogo1.setTitle("MENSAJE");  
  	        dialogo1.setMessage("No tiene Excusas registradas"); 
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
       			listaexcu.add(tok.nextToken().trim());
       		}
       		mAdapter=new SelectionAdapter(ListaEscusasEstudiantes.this,android.R.layout.simple_list_item_1, android.R.id.text1, listaexcu);
    		lvexcusa.setAdapter(mAdapter);
       		//lvexcusa.setAdapter(adaptexcu);
       		if(listaexcu.size()!=0){
    			taexcunu.setText(listaexcu.size()+" Dias de Excusas");
    		}
       	
     		dial.dismiss();
     		}
      	 
     		}catch(Exception e){
      			 e.printStackTrace();
      			// Toast.makeText(MainActivity.this, "Error de conexion"+e.toString(), Toast.LENGTH_LONG).show();
      			 dial.dismiss();
      			 
      			 AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListaEscusasEstudiantes.this);  
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
   
   private Handler puenteExcufalla = new Handler() {
    	 @Override
    	 public void handleMessage(Message msg) {
    		 try{
    		// listaMate.removeAll(listaMate);
    		String cad=(String)msg.obj;
    		if(cad.toString().equals("fallacambio")){
    			dial.dismiss();
    			mAdapter.notifyDataSetChanged();
    			AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListaEscusasEstudiantes.this);  
 	        dialogo1.setTitle("Asistencia eliminada");  
 	        dialogo1.setMessage("Fecha Cambio a falla"); 
 	      //obligamos al usuario a pulsar los botones para cerrarlo
 	        dialogo1.setCancelable(false);  
 	        
 	       dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {  
 	            public void onClick(DialogInterface dialog, int id) {
 	            	Intent intent=new Intent();
			   		intent.putExtra("resul", "1");
			   		setResult(RESULT_OK, intent);
			   		listaexcu.remove(mAdapter.poicion());
 	            	dialog.cancel();
 	            	mAdapter.notifyDataSetChanged();
 	            	if(listaexcu.size()==0){
 	            	finish();
 	            	}
 	            }  
 	        });   
 	                  
 	        dialogo1.show(); 
    			
    		}
    		}catch(Exception e){
    			 e.printStackTrace();
    			// Toast.makeText(MainActivity.this, "Error de conexion"+e.toString(), Toast.LENGTH_LONG).show();
    			 dial.dismiss();
    			 
    			 AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListaEscusasEstudiantes.this);  
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
  
  
  private Handler puenteExcuasis = new Handler() {
    	 @Override
    	 public void handleMessage(Message msg) {
    		 try{
    		// listaMate.removeAll(listaMate);
    		String cad=(String)msg.obj;
    		if(cad.toString().equals("asiscambio")){
    			dial.dismiss();
    			mAdapter.notifyDataSetChanged();
    			AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListaEscusasEstudiantes.this);  
 	        dialogo1.setTitle("Asistencia eliminada");  
 	        dialogo1.setMessage("Fecha Cambio a asistencia"); 
 	      //obligamos al usuario a pulsar los botones para cerrarlo
 	        dialogo1.setCancelable(false);  
 	        
 	       dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {  
 	            public void onClick(DialogInterface dialog, int id) {
 	            	Intent intent=new Intent();
			   		intent.putExtra("resul", "1");
			   		setResult(RESULT_OK, intent);
			   		listaexcu.remove(mAdapter.poicion());
 	            	dialog.cancel();
 	            	mAdapter.notifyDataSetChanged();
 	            	if(listaexcu.size()==0){
 	            	finish();
 	            	}
 	            }  
 	        });   
 	                  
 	        dialogo1.show(); 
    			
    		}
    		}catch(Exception e){
    			 e.printStackTrace();
    			// Toast.makeText(MainActivity.this, "Error de conexion"+e.toString(), Toast.LENGTH_LONG).show();
    			 dial.dismiss();
    			 
    			 AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListaEscusasEstudiantes.this);  
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
		setContentView(R.layout.excusas_estudiantes);
		
		prefe=getSharedPreferences("datos",Context.MODE_PRIVATE);
		lvexcusa=(ListView)findViewById(R.id.listView1excusasestudiantesssss);
		taexcunu=(TextView)findViewById(R.id.textView1excusasestudiantesss);
		
		listaexcu=new ArrayList<String>();
		adaptexcu =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listaexcu);
		
		bundle = getIntent().getExtras();
		
		did= bundle.getString("idEstudiante");
		dnom= bundle.getString("NombreEstudiante");
		dgru= bundle.getString("codGrupo");
		setupActionBar();
		dial= ProgressDialog.show(ListaEscusasEstudiantes.this, "", "Cargando datos...",true);
		dial.setCancelable(true);
	   	 Thread thrmate = new Thread(new Runnable() {
				  @Override
				  public void run() {
		
												
						try{

				       	 httpclient = new DefaultHttpClient();
				              httppost = new HttpPost("http://"+prefe.getString("ip","")+"/aa/diasexcusaestudiante.php");
				           nameValuePairs = new ArrayList<NameValuePair>(2);
				         nameValuePairs.add(new BasicNameValuePair("IdE", did.toString()));
				         nameValuePairs.add(new BasicNameValuePair("Grupo", dgru.toString()));
				           httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				          response = httpclient.execute(httppost);
				     HttpEntity ent = response.getEntity();
				          String text = EntityUtils.toString(ent);
				      
				          Message sms = new Message();
				          sms.obj = text;
				          puenteExcu.sendMessage(sms);
				     
				                
				          
				       }catch(Exception e){
				    	   e.printStackTrace();
				    	   dial.dismiss();
				    
				    	   Message sms = new Message();
					          sms.obj = "cxerror";
					          puenteExcu.sendMessage(sms);
				       }
				      
		
					  
				  }
				  });
				 //Arrancamos el Hilo
				 thrmate.start();
	}

	
	
	private void setupActionBar() {
		//ListView listView = getListView();
		lvexcusa.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE_MODAL);
		lvexcusa.setMultiChoiceModeListener(new MultiChoiceModeListener() {

			@Override
			public void onItemCheckedStateChanged(ActionMode mode,
					int position, long id, boolean checked) {

				// If element is checked, it is added to selection; if not, it's
				// deleted
				if (checked) {
					mAdapter.setNewSelection(position);
				} else {
					mAdapter.removeSelection(position);
				}

				mode.setTitle(mAdapter.getSelectionCount() + " items selected");
			}

			@Override
			public boolean onActionItemClicked(final ActionMode mode, MenuItem item) {
				// CAB menu options
				switch (item.getItemId()) {
				case R.id.cambiar_a_faltas:
					AlertDialog.Builder builder2 =
                    new AlertDialog.Builder(ListaEscusasEstudiantes.this);
     
            builder2.setMessage("¿Seguro desea quitar esta excusa "+listaexcu.get(mAdapter.poicion()).toString()+"?")
            .setTitle("pasar a Fallas")
            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener()  {
                   public void onClick(DialogInterface dialog, int id) {
                  	 dialog.cancel();
                   } 
               })
              	 .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
	                     public void onClick(DialogInterface dialog, int id) {
	                    	 
	                     
					dial= ProgressDialog.show(ListaEscusasEstudiantes.this, "", "Cargando datos...",true);
					dial.setCancelable(true);
					Thread thr = new Thread(new Runnable() {
							  @Override
							  public void run() {
					
															
									try{

							       	 httpclient = new DefaultHttpClient();
							              httppost = new HttpPost("http://"+prefe.getString("ip","")+"/aa/diasexcuestudiantecambiarfalla.php");
							           nameValuePairs = new ArrayList<NameValuePair>(2);
							         nameValuePairs.add(new BasicNameValuePair("IdE", did.toString()));
							         nameValuePairs.add(new BasicNameValuePair("Grupo", dgru.toString()));
							         nameValuePairs.add(new BasicNameValuePair("Fec", listaexcu.get(mAdapter.poicion()).toString()));
							           httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
							          response = httpclient.execute(httppost);
							     HttpEntity ent = response.getEntity();
							          String text = EntityUtils.toString(ent);
							      
							          Message sms = new Message();
							          sms.obj = text;
							          puenteExcufalla.sendMessage(sms);
							     
							                
							          
							       }catch(Exception e){
							    	   e.printStackTrace();
							    	   dial.dismiss();
							    	   Message sms = new Message();
								          sms.obj = "cxerror";
								          puenteExcu.sendMessage(sms);   
							       }
							      
					
								  
							  }
							  });
							 //Arrancamos el Hilo
							 thr.start();
							 
				/*	Toast.makeText(ListaAsistenciasEstudiantes.this,
							mAdapter.poicion() + " items deleted",
							Toast.LENGTH_LONG).show();
					
					Toast.makeText(ListaAsistenciasEstudiantes.this,
							listaasis.get(mAdapter.poicion()).toString() + " ",
							Toast.LENGTH_LONG).show();*/
				//	mAdapter.clearSelection();
					
					mode.finish();
	                     }
                  });
  builder2.create();
     builder2.show();
     mode.finish();
	
					return true;
				case R.id.cambiar_a_asis:
					AlertDialog.Builder builder3 =
                    new AlertDialog.Builder(ListaEscusasEstudiantes.this);
     
            builder3.setMessage("¿Seguro desea quitar esta excusa "+listaexcu.get(mAdapter.poicion()).toString()+"?")
            .setTitle("pasar a Asistencias")
            .setNegativeButton("Cancelar", new DialogInterface.OnClickListener()  {
                   public void onClick(DialogInterface dialog, int id) {
                  	 dialog.cancel();
                   } 
               })
              	 .setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
	                     public void onClick(DialogInterface dialog, int id) {
					dial= ProgressDialog.show(ListaEscusasEstudiantes.this, "", "Cargando datos...",true);
				   dial.setCancelable(true);
					Thread thr1 = new Thread(new Runnable() {
							  @Override
							  public void run() {
					
															
									try{

							       	 httpclient = new DefaultHttpClient();
							              httppost = new HttpPost("http://"+prefe.getString("ip","")+"/aa/diasexcuestudiantecambiarasis.php");
							           nameValuePairs = new ArrayList<NameValuePair>(2);
							         nameValuePairs.add(new BasicNameValuePair("IdE", did.toString()));
							         nameValuePairs.add(new BasicNameValuePair("Grupo", dgru.toString()));
							         nameValuePairs.add(new BasicNameValuePair("Fec", listaexcu.get(mAdapter.poicion()).toString()));
							           httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
							          response = httpclient.execute(httppost);
							     HttpEntity ent = response.getEntity();
							          String text = EntityUtils.toString(ent);
							      
							          Message sms = new Message();
							          sms.obj = text;
							          puenteExcuasis.sendMessage(sms);
							     
							                
							          
							       }catch(Exception e){
							    	   e.printStackTrace();
							    	   dial.dismiss();
							    	   Message sms = new Message();
								          sms.obj = "cxerror";
								          puenteExcu.sendMessage(sms);   
							       }
							      
					
								  
							  }
							  });
							 //Arrancamos el Hilo
							 thr1.start();
	                     }
                 });
 builder3.create();
    builder3.show();
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
				inflater.inflate(R.menu.menu_excu, menu);

				return true;
			}

			@Override
			public void onDestroyActionMode(ActionMode mode) {
				mAdapter.clearSelection();
			}

			@Override
			public boolean onPrepareActionMode(ActionMode mode, Menu menu) {

				return false;
			}
		});
	}
	
}
