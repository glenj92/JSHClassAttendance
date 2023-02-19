package com.local.aa;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.nfc.NfcAdapter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Admin extends Activity {
	
	SharedPreferences prefe;
	private Button btescr,btasis;
	
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		switch(item.getItemId()){
		case R.id.salir:
			// Toast.makeText(this, "buscar", Toast.LENGTH_SHORT).show();
			
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
			
			Intent i=new Intent(Admin.this, MainActivity.class);
			startActivity(i);
			finish();
			
 			
			break;
		
		
			default:
			
		}
		return super.onOptionsItemSelected(item);
	}

	public static Activity aad;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		aad=this;
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_admin);
		
		prefe=getSharedPreferences("datos",Context.MODE_PRIVATE);
		btescr=(Button)findViewById(R.id.btadminescri);
		btasis=(Button)findViewById(R.id.btadminasis);
		
		btescr.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				NfcAdapter adapter = NfcAdapter.getDefaultAdapter(Admin.this);
				Boolean nfcEnabled=adapter.isEnabled();
		        if(nfcEnabled){
		        	//Toast.makeText(EscribirTag.this, "encendido", Toast.LENGTH_LONG).show();
		        	Intent i=new Intent(Admin.this, EscribirTag.class);
					startActivity(i);
		        }else{
		        	//Toast.makeText(Admin.this, "ACTIVE la opcion NFC de su dispositivo para continuar..", Toast.LENGTH_LONG).show();
		        	AlertDialog.Builder dialogo1 = new AlertDialog.Builder(Admin.this);  
	                dialogo1.setTitle("NFC apagado");  
	                dialogo1.setMessage("Para continuar encienda el hardware NFC del dispositivo"); 
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
				
			}
		});
		
		btasis.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(Admin.this, PrincipalActivity.class);
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
