package com.local.aa;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;





import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.ProgressDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.TagLostException;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Bundle;
import android.os.Message;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class EscribirTag extends Activity {
	
	


	 private Admin ad=new Admin();
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		
		switch(item.getItemId()){
		case R.id.cerrar_sesion_esbribir_tag:
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
 	        
 	       Editor editor111111=prefe.edit();
 	        editor111111.putString("tipoDoc", "");
 	        editor111111.commit();
			
			Intent i=new Intent(EscribirTag.this, MainActivity.class);
			startActivity(i);
			finish();
			
			break;	
			
			
		default:
		
		}
		return super.onOptionsItemSelected(item);
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		getMenuInflater().inflate(R.menu.menu_escribir_tag, menu);
		return true;
		
	}
	



	NfcAdapter adapter;
    PendingIntent pendingIntent;
    IntentFilter writeTagFilters[];
    boolean writeMode;
    Tag myTag;
    Context context;
  //  private Button cerrarEscribNfc;
    SharedPreferences prefe;
    ProgressDialog dial;
    private EditText ident,nombre,apellido;
    
    
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.escribir_tag);
	//	cerrarEscribNfc=(Button)findViewById(R.id.botonCerrarSesionEcriNfc);
		ident=(EditText)findViewById(R.id.edtIdentificacionEscriNfc);
		nombre=(EditText)findViewById(R.id.edtNombreCompletoEscriNfc);
		apellido=(EditText)findViewById(R.id.edtApellidoEsciNfc);
		prefe=getSharedPreferences("datos",Context.MODE_PRIVATE);
		
		context = this;
        //Los elementos que vamos a usar en el layout
        Button btnWrite = (Button)findViewById(R.id.botonEscribirTAgNfcDato);
       // final TextView nombre = (TextView)findViewById(R.id.edtNombreCompletoEscriNfc);
       // final TextView ident = (TextView)findViewById(R.id.edtIdentificacionEscriNfc);
      //  final TextView apellido = (TextView)findViewById(R.id.edtApellidoEsciNfc);
        
       
        
        
     
        
        
        //setOnCLickListener hará la acción que necesitamos
        btnWrite.setOnClickListener(new OnClickListener() {
        	
        	   @Override
        	    public void onClick(View v) {
        		   
        		   String nom=nombre.getText().toString();
 	        	  String iden=ident.getText().toString();
 	        	  String ape=apellido.getText().toString();
 	        	 String comple=nom+":"+ape+":"+iden;
        		   
 	        	 if(nom.equals("")||iden.equals("")||ape.equals("")){
	        		   
	 	        		Toast.makeText(EscribirTag.this, "Datos en Blanco no validos", Toast.LENGTH_LONG).show();
	 	        	 }else{
	 	        		Intent i=new Intent(EscribirTag.this, DetectaEscribeTag.class);
		        		   i.putExtra("datoestudiente", comple.toString());
		       			startActivity(i);
		       			nombre.setText("");
		       			ident.setText("");
		       			apellido.setText("");
	 	        	 }
        		   
        	   /*   try{
        	              //Si no existe tag al que escribir, mostramos un mensaje de que no existe.
        	              if(myTag == null){
        	        Toast.makeText(context, "no Existe tag", Toast.LENGTH_LONG).show();
        	          }else{
        	               //Llamamos al método write que definimos más adelante donde le pasamos por
        	               //parámetro el tag que hemos detectado y el mensaje a escribir.
        	        	  String nom=nombre.getText().toString();
        	        	  String iden=ident.getText().toString();
        	        	  String ape=apellido.getText().toString();
        	        	 String comple=nom+":"+ape+":"+iden;
        	        write(comple,myTag);
        	            Toast.makeText(context, "si existe tag", Toast.LENGTH_LONG).show();
        	         }
        	       }catch(IOException e){
        	    Toast.makeText(context, "error 1",Toast.LENGTH_LONG).show();
        	        e.printStackTrace();
        	    }catch(FormatException e){
        	       Toast.makeText(context, "error 2", Toast.LENGTH_LONG).show();
        	    e.printStackTrace();
        	    }*/
        	     }
        	  });
        adapter = NfcAdapter.getDefaultAdapter(this);
        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
        tagDetected.addCategory(Intent.CATEGORY_DEFAULT);
        writeTagFilters = new IntentFilter[]{tagDetected};
        
      
      
     /*   cerrarEscribNfc.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
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
	 	        
	 	       Editor editor111111=prefe.edit();
	 	        editor111111.putString("tipoDoc", "");
	 	        editor111111.commit();
				
				Intent i=new Intent(EscribirTag.this, MainActivity.class);
				startActivity(i);
				finish();
			}
		});*/
        
   /*     nombre.setOnKeyListener(new View.OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				 if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP)
	                {
	                    //TODO: cuando suelta la tecla ENTER
						apellido.setFocusable(true);			 
	                    return true;
	                }
	                if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_DOWN)
	                {
	                    //TODO: cuendo se pulsa la tecla ENTER
	                	
	                    return true;
	                }
	                return false;
			}
		});*/
        apellido.setOnKeyListener(new View.OnKeyListener() {
			
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				// TODO Auto-generated method stub
				 if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER && event.getAction() == KeyEvent.ACTION_UP)
	                {
	                    //TODO: cuando suelta la tecla ENTER
					 String nom=nombre.getText().toString();
	 	        	  String iden=ident.getText().toString();
	 	        	  String ape=apellido.getText().toString();
	 	        	 String comple=nom+":"+ape+":"+iden;
	        		   
	 	        	 if(nom.equals("")||iden.equals("")||ape.equals("")){
		        		   
		 	        		Toast.makeText(EscribirTag.this, "Datos en Blanco no validos", Toast.LENGTH_LONG).show();
		 	        	 }else{
		 	        		Intent i=new Intent(EscribirTag.this, DetectaEscribeTag.class);
			        		   i.putExtra("datoestudiente", comple.toString());
			       			startActivity(i);
			       			nombre.setText("");
			       			ident.setText("");
			       			apellido.setText("");
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
        
        
        
	}
	/*
	//El método write es el más importante, será el que se encargue de crear el mensaje 
    //y escribirlo en nuestro tag.
    private void write(String text, Tag tag) throws IOException, FormatException{
      //Creamos un array de elementos NdefRecord. Este Objeto representa un registro del mensaje NDEF   
      //Para crear el objeto NdefRecord usamos el método createRecord(String s)
      NdefRecord[] records = {createRecord(text)};
      //NdefMessage encapsula un mensaje Ndef(NFC Data Exchange Format). Estos mensajes están 
      //compuestos por varios registros encapsulados por la clase NdefRecord      
      NdefMessage message = new NdefMessage(records);
      //Obtenemos una instancia de Ndef del Tag
      Ndef ndef = Ndef.get(tag);
      ndef.connect();
      ndef.writeNdefMessage(message);
      ndef.close();
    }
    //Método createRecord será el que nos codifique el mensaje para crear un NdefRecord
    @SuppressLint("NewApi") 
    private NdefRecord createRecord(String text) throws UnsupportedEncodingException{
        String lang = "";
        byte[] textBytes = text.getBytes();
        byte[] langBytes = lang.getBytes("US-ASCII");
        int langLength = langBytes.length;
        int textLength = textBytes.length;
        byte[] payLoad = new byte[1 + langLength + textLength];
 
        payLoad[0] = (byte) langLength;
 
        System.arraycopy(langBytes, 0, payLoad, 1, langLength);
        System.arraycopy(textBytes, 0, payLoad, 1+langLength, textLength);
 
       // NdefRecord recordNFC = new NdefRecord(NdefRecord.TNF_WELL_KNOWN, NdefRecord.RTD_TEXT, new byte[0], payLoad);
        NdefRecord recordNFC = new NdefRecord(NdefRecord.TNF_EXTERNAL_TYPE, NdefRecord.RTD_TEXT, new byte[0], payLoad);
        return recordNFC;
    	*/
    /*	
    	//Locale locale= new Locale("en","US");
    	Locale locale= new Locale("");
    	byte[] langBytes = locale.getLanguage().getBytes(Charset.forName("US-ASCII")); 
    	boolean encodeInUtf8=false; 
    	Charset utfEncoding = encodeInUtf8 ? Charset.forName("UTF-8") :   Charset.forName("UTF-16");
    	int utfBit = encodeInUtf8 ? 0 : (1 << 7);
    	char status = (char) (utfBit + langBytes.length); 
    	//String RTD_TEXT= "This is an RTD_TEXT"; 
    	byte[] textBytes = text.getBytes(utfEncoding); 
    	//byte[] data = new byte[1 + langBytes.length + textBytes.length]; 
    	byte[] data = new byte[textBytes.length + 1 + langBytes.length]; 
    	data[0] = (byte) status;
    	System.arraycopy(langBytes, 0, data, 1, langBytes.length);
    	System.arraycopy(textBytes, 0, data, 1 + langBytes.length, textBytes.length); 
    	NdefRecord recordNFC = new NdefRecord(NdefRecord.TNF_WELL_KNOWN,   NdefRecord.RTD_TEXT, new byte[0], data); 
    	//NdefMessage newMessage= new NdefMessage(new NdefRecord[] { textRecord });

        return recordNFC;*/
 
//    }
    //en onnewIntent manejamos el intent para encontrar el Tag
    @SuppressLint("NewApi") 
    protected void onNewIntent(Intent intent){
        if(NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())){
            myTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
           // Toast.makeText(this, "ok detectado" + myTag.toString(), Toast.LENGTH_LONG).show();
            
            try {
                Ndef ndef = Ndef.get(myTag);
                String idtag=byteArrayToHexString2(myTag.getId()).toString();
                String fomateo="NO";
                String puedeformateo="NO";
                String escritura="NO";
                String topotag="DESCONOCIDO";
                String sololectura="NO";
                if(ndef != null) {
                   // ndef.connect();
                	fomateo="SI";
                	puedeformateo="SI";
                   // Toast.makeText(this, "SI esta formateada", Toast.LENGTH_LONG).show();
                	topotag=ndef.getType();
                    if(ndef.isWritable()) {
                     	escritura="SI";
                     	
                     //	Toast.makeText(this, "Si se puede escribir", Toast.LENGTH_LONG).show();
                     //	Toast.makeText(this, ndef.getType(), Toast.LENGTH_LONG).show();
                     	
                     	if(ndef.canMakeReadOnly()==true){
                     		sololectura="SI";
                          //	Toast.makeText(this, "PUEDE SER DE SOLO LECTURA", Toast.LENGTH_LONG).show();
                          }
    	
                     }
    
                }else if(ndef==null){
                	//topotag=ndef.getType();
                	 NdefFormatable format = NdefFormatable.get(myTag);
                	 
                	// fomateo="NO";
                     if(format != null) {
                    
                             puedeformateo="SI";
                             sololectura="DESCDO";
                             escritura="DESCDO";
                           
                }else{
                	 puedeformateo="NO";
                }
              }
               
                AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);  
                dialogo1.setTitle("Informacion de Tag");  
                dialogo1.setMessage("ID TAG: \n"+idtag+" \n\n"+
                					"Esta formateado?:						"+fomateo+" \n" +
                					"Se puede formatear?:			"+puedeformateo+" \n"+
                					"Puede ser escrita?:					"+escritura+" \n"+
                					"Convertir solo lectura?:		"+sololectura+" \n\n"+
                					"TIPO DE TAG: \n"+topotag+" \n"); 
              //obligamos al usuario a pulsar los botones para cerrarlo
                dialogo1.setCancelable(false);  
                
               dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {  
                    public void onClick(DialogInterface dialog, int id) {
                    	
                    	
                    	//finish();
                    	dialog.cancel();
                    }  
                });  
                          
                dialogo1.show(); 
                
                
       } catch(Exception e) {
      	   Toast.makeText(this, "error 4", Toast.LENGTH_LONG).show();
       }
            
 
        }
    }
    
    //***********************************************************************
    

    public void onPause(){
        super.onPause();
        WriteModeOff();
    }
    public void onResume(){
        super.onResume();
        WriteModeOn();
    }
 
    @SuppressLint("NewApi") private void WriteModeOn(){
        writeMode = true;
        adapter.enableForegroundDispatch(this, pendingIntent, writeTagFilters, null);
    }
 
    @SuppressLint("NewApi") private void WriteModeOff(){
        writeMode = false;
        adapter.disableForegroundDispatch(this);
    }
    
    public static String byteArrayToHexString(byte[] tag) throws Exception {
        String result = "";
        for (int i=tag.length-1; i>=0 ; i--) {
            result +=
                Integer.toString( ( tag[i] & 0xFF ) + 0x100, 16).substring( 1 );
        }
        return result;
    }
    
    static final String HEXES = "0123456789ABCDEF";
    public static String byteArrayToHexString2(byte[] tag ) {
        if ( tag == null ) {
          return null;
        }
        final StringBuilder hex = new StringBuilder( 2 * tag.length );
        for ( final byte b : tag ) {
          hex.append(HEXES.charAt((b & 0xF0) >> 4))
             .append(HEXES.charAt((b & 0x0F)));
        }
        return hex.toString();
      }
	

}
