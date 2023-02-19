package com.local.aa;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Random;
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
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
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

public class ListaGeneralAsis extends Activity {
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		//super.onActivityResult(requestCode, resultCode, data);
		 if(requestCode==111 && resultCode==RESULT_OK){
			  String res=data.getExtras().getString("resultado");
			  
         		if(res.equals("1")){
         			thrGrupo();
         		}
			  
		  }
		 
	}
	
	private ArrayList<DatosGenralesEstu> datoG_estu=new ArrayList<DatosGenralesEstu>();
	private AdaptadorDatosGeneralesEstu adaptadorG;
	
	private ListView lMate,lGrupo,listaGeneral;
	private ViewFlipper vfGeneral;
	private TextView uno,dos,tres;
	
	ArrayList<String> listaMate,listaGrupo;
	 ArrayAdapter<String> adaptMate,adaptgrupo;
	
	HttpPost httppost;
    StringBuffer buffer;
    HttpResponse response;
    HttpClient httpclient;
    InputStream inputStream;
    SharedPreferences prefe ;
    List<NameValuePair> nameValuePairs;
    private  ArrayAdapter<String> adapterjor;
    ProgressDialog dial;
   private String d,datoGrupo,otrod;
  private int cont=0;
    
    private Handler puentemate = new Handler() {
   	 @Override
   	 public void handleMessage(Message msg) {
   		// listaMate.removeAll(listaMate);
   		 try{
   		String cad=(String)msg.obj;
   		if(msg.obj.toString().trim().equals("cxerror")){
    		AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListaGeneralAsis.this);  
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
   			AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListaGeneralAsis.this);  
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
	            	Intent i=new Intent(ListaGeneralAsis.this, RegistroGrupo.class);
					startActivity(i);
	            	finish();
	            }  
	        });   
	                  
	        dialogo1.show(); 
   			
   		}else{
   		StringTokenizer tok=new StringTokenizer(cad,",");
     		while(tok.hasMoreTokens()){
     			listaMate.add(tok.nextToken().trim().toUpperCase());
     		}
     		lMate.setAdapter(adaptMate);
     	
   		dial.dismiss();
   		}
   		}catch(Exception e){
  			 e.printStackTrace();
  			// Toast.makeText(MainActivity.this, "Error de conexion"+e.toString(), Toast.LENGTH_LONG).show();
  			 dial.dismiss();
  			 
  			 AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListaGeneralAsis.this);  
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
   		listaGrupo.removeAll(listaGrupo);
   		String cad=(String)msg.obj;
   		if(msg.obj.toString().trim().equals("cxerror")){
    		AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListaGeneralAsis.this);  
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
     			for(int i=0; i<listaGrupo.size(); i++) {
        			if( listaGrupo.get(i).equals(da)) {
        				cont=1;
        			}	
        	}
     			if(cont==0){
            		
   				listaGrupo.add(da.trim());
   			}
     			
     			//alista2.add(tok.nextToken().trim());
     		}
     		lGrupo.setAdapter(adaptgrupo);
     		//vf.showNext();
     		
   		dial.dismiss();
  /* 	vfGeneral.setInAnimation(inFromRightAnimation());
		vfGeneral.setOutAnimation(outToLeftAnimation());
		vfGeneral.showNext();*/
    }
   		}catch(Exception e){
  			 e.printStackTrace();
  			// Toast.makeText(MainActivity.this, "Error de conexion"+e.toString(), Toast.LENGTH_LONG).show();
  			 dial.dismiss();
  			 
  			 AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListaGeneralAsis.this);  
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
		 datoG_estu.removeAll(datoG_estu);
		 String cad=(String)msg.obj;
		 if(msg.obj.toString().trim().equals("cxerror")){
	    		AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListaGeneralAsis.this);  
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
	    				 
				 	        
	    }else if(cad.toString().equals("")){
	   			dial.dismiss();
	   			AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListaGeneralAsis.this);  
		        dialogo1.setTitle("MENSAJE");  
		        dialogo1.setMessage("No hay Informacion de estudiantes en este Grupo"); 
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
	     			String da3=tok.nextToken();
	     			String da4=tok.nextToken();
	     			String da5=tok.nextToken();
	     			String da6=tok.nextToken();
	     			tres.setText(da6.toString().trim());
	     			for(int i=0; i<datoG_estu.size(); i++) {
	        			if( datoG_estu.get(i).getIdent().equals(da)) {
	        				cont=1;
	        			}	
	        	}
	     			if(cont==0){
	            		
     				datoG_estu.add(new DatosGenralesEstu(" "+da2.trim().toUpperCase(), da1.trim().toUpperCase(), da.trim().toUpperCase(), da3.trim().toUpperCase(), da4.trim().toUpperCase(), da5.trim().toUpperCase()));
     				Collections.sort(datoG_estu, new Comparator<DatosGenralesEstu>() {
						@Override
						public int compare(DatosGenralesEstu p1, DatosGenralesEstu p2) {
							// TODO Auto-generated method stub
							return new String(p1.getApellido()).compareToIgnoreCase(new String(p2.getApellido()));
							
						}
					} );
     				adaptadorG=new AdaptadorDatosGeneralesEstu(ListaGeneralAsis.this,datoG_estu);

					
     			}
	     			
	     			//alista2.add(tok.nextToken().trim());
	     		}
	     		listaGeneral.setAdapter(adaptadorG);
			listaGeneral.setDividerHeight(3);
			uno.setText(otrod.toString().trim());
			dos.setText(datoGrupo.toString().trim());
			//tres.setText("");
	     		//lv3.setAdapter(aa3);
	     		//vf.showNext();
	   		dial.dismiss();

	   		if(vfGeneral.getDisplayedChild()!=1){
	    	vfGeneral.setInAnimation(inFromRightAnimation());
		vfGeneral.setOutAnimation(outToLeftAnimation());
		vfGeneral.setDisplayedChild(1);
	   		}
	/*	if(vfGeneral.getDisplayedChild()==1){
			ActionBar actionBar = getActionBar();
			actionBar.setDisplayHomeAsUpEnabled(true);
			}
     	 */
	   		}
		 }catch(Exception e){
   			 e.printStackTrace();
   			// Toast.makeText(MainActivity.this, "Error de conexion"+e.toString(), Toast.LENGTH_LONG).show();
   			 dial.dismiss();
   			 
   			 AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListaGeneralAsis.this);  
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
public boolean onOptionsItemSelected(MenuItem menuItem)
{
	
/*	if(vfGeneral.getDisplayedChild()==1){
	vfGeneral.setInAnimation(inFromLeftAnimation());
	vfGeneral.setOutAnimation(outToRightAnimation());
	vfGeneral.setDisplayedChild(0);
	}
	
	if(vfGeneral.getDisplayedChild()==0){
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(false);
		}*/
    //onBackPressed();
	
	if(vfGeneral.getDisplayedChild()==1){
	switch(menuItem.getItemId()){
	case R.id.atraslistge:
		vfGeneral.setInAnimation(inFromLeftAnimation());
		vfGeneral.setOutAnimation(outToRightAnimation());
		vfGeneral.setDisplayedChild(0);
	break;
	case R.id.azarrestu:
		Random r=new Random();
		int nrand = r.nextInt(datoG_estu.size());
		
		 AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListaGeneralAsis.this);  
	        dialogo1.setTitle("AFORTUNADO!");  
	        dialogo1.setMessage(datoG_estu.get(nrand).getNombre()+" "+datoG_estu.get(nrand).getApellido()); 
	      //obligamos al usuario a pulsar los botones para cerrarlo
	        dialogo1.setCancelable(false);  
	        
	       dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {  
	            public void onClick(DialogInterface dialog, int id) {
	            	
	            	dialog.cancel();
	            }  
	        });  
	                  
	        dialogo1.show();
		
		break;
		
	case R.id.graficaagrupo:
		
		List<String>ide=new ArrayList<String>();
		List<String>nom=new ArrayList<String>();
		List<String>ape=new ArrayList<String>();
		
		List<String>asi=new ArrayList<String>();
		List<String>exc=new ArrayList<String>();
		List<String>fal=new ArrayList<String>();
		for(int i=0;i<datoG_estu.size();i++){
			ide.add(datoG_estu.get(i).getIdent());
			
			nom.add(datoG_estu.get(i).getNombre());
			
			ape.add(datoG_estu.get(i).getApellido());
			
			asi.add(datoG_estu.get(i).getAsiste());
			
			exc.add(datoG_estu.get(i).getExusa());
			
			fal.add(datoG_estu.get(i).getFallas());
		}
		
		BarraGrp bar=new BarraGrp();
		Intent barIntent=bar.getIntent(this, ide, nom, ape, asi, exc, fal,datoGrupo);
		startActivity(barIntent);
		
		break;	
		
		
	default:
	
}
	}else{
		 AlertDialog.Builder dialogo1 = new AlertDialog.Builder(ListaGeneralAsis.this);  
	        dialogo1.setTitle("MENSAJE");  
	        dialogo1.setMessage("Opcion no valida en esta Vista"); 
	      //obligamos al usuario a pulsar los botones para cerrarlo
	        dialogo1.setCancelable(false);  
	        
	       dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {  
	            public void onClick(DialogInterface dialog, int id) {
	            	
	            	dialog.cancel();
	            }  
	        });  
	                  
	        dialogo1.show();
	}
	
    return true;
}
 
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.lista_general_asis_main);
		
		prefe=getSharedPreferences("datos",Context.MODE_PRIVATE);
		vfGeneral= (ViewFlipper) findViewById(R.id.viewFlipperListaGeneralAsisMain);
		lMate=(ListView)findViewById(R.id.lvmate);
		lGrupo=(ListView)findViewById(R.id.lvgrupo);
		listaGeneral=(ListView)findViewById(R.id.LVlstaGeneralAsis);
		uno=(TextView)findViewById(R.id.asignatitulodedatos);
		dos=(TextView)findViewById(R.id.grupotitulodedatos);
		tres=(TextView)findViewById(R.id.totalclasestitulodedatos);
		
		listaMate=new ArrayList<String>();
		adaptMate =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listaMate);
		listaGrupo=new ArrayList<String>();
		adaptgrupo =new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listaGrupo);
		
		dial= ProgressDialog.show(ListaGeneralAsis.this, "", "Cargando datos...",true);
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
				 
				 
				 
				 
				 lMate.setOnItemClickListener(new OnItemClickListener() {
			            @Override
			            public void onItemClick(AdapterView<?> parent, View v, int posicion, long id) {
			            	
			            	//lv1.setOnTouchListener(new ListenerTouchViewFlipper());
			            	
			            	
			            	StringTokenizer tok1=new StringTokenizer(lMate.getItemAtPosition(posicion).toString(),"-");
			          		while(tok1.hasMoreTokens()){
			          		//	Toast.makeText(MainActivity.this, tok1.nextToken().trim(), Toast.LENGTH_LONG).show();
			            	
			            	d=tok1.nextToken();
			            	otrod=tok1.nextToken();
			          		}
			            	dial= ProgressDialog.show(ListaGeneralAsis.this, "", "Cargando datos...",true);
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
			          				//dial.dismiss();
			            }
				 });
				 
				 
				 lGrupo.setOnItemClickListener(new OnItemClickListener() {
			            @Override
			            public void onItemClick(AdapterView<?> parent, View v, final int posicion, long id) {
			            	
			            	//lv1.setOnTouchListener(new ListenerTouchViewFlipper());
			            	
			            	
			           datoGrupo=lGrupo.getItemAtPosition(posicion).toString();
			          		
			            	
			          	   	thrGrupo();
			            	
			            }
				 });
				 
				 listaGeneral.setOnItemClickListener(new OnItemClickListener() {
					 @Override
			            public void onItemClick(AdapterView<?> parent, View v, final int posicion, long id) {
						 
						 String ide=datoG_estu.get(posicion).getIdent().toString();
						 String nom=datoG_estu.get(posicion).getNombre().toString();
						 String ape=datoG_estu.get(posicion).getApellido().toString();
						 double as=Double.parseDouble(datoG_estu.get(posicion).getAsiste().toString());
						 double fal=Double.parseDouble(datoG_estu.get(posicion).getFallas().toString());
						 double es=Double.parseDouble(datoG_estu.get(posicion).getExusa().toString());
						 Intent i=new Intent(ListaGeneralAsis.this, InformacionEstidiantes.class);
						   i.putExtra("idEstudiante", ide.trim());
						   i.putExtra("NombreEstudiante", nom.trim()+" "+ape.trim());
						   i.putExtra("codGrupo", datoGrupo.toString().trim());
						   
						   i.putExtra("asiste", as);
						   i.putExtra("falla", fal);
						   i.putExtra("excu", es);
						   startActivityForResult(i, 111);   
					//	startActivity(i);
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
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_lista_general, menu);
		return true;
	}
    
    
    public void thrGrupo(){
    	
    	dial= ProgressDialog.show(ListaGeneralAsis.this, "", "Cargando datos...",true);
    	dial.setCancelable(true);
    	 Thread thr = new Thread(new Runnable() {
				  @Override
				  public void run() {
		
												
						try{

				       	 httpclient = new DefaultHttpClient();
				              httppost = new HttpPost("http://"+prefe.getString("ip","")+"/aa/datosGeneralAsistencias.php");
				           nameValuePairs = new ArrayList<NameValuePair>(2);
				         nameValuePairs.add(new BasicNameValuePair("CodGrupo", datoGrupo.trim()));
				           httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
				          response = httpclient.execute(httppost);
				     HttpEntity ent = response.getEntity();
				          String text = EntityUtils.toString(ent);
				      
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
    }
	

}
