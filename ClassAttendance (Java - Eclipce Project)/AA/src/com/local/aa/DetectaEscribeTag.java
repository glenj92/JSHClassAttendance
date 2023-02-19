package com.local.aa;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

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
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.TagLostException;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class DetectaEscribeTag extends Activity {
	
	NfcAdapter adapter;
    PendingIntent pendingIntent;
    IntentFilter writeTagFilters[];
    boolean writeMode;
    Tag myTag;
    Context context;
    private Button cerrarEscribNfc;
    SharedPreferences prefe;
    ProgressDialog dial;
    Bundle bundle;
   private String dato;
   private TextView mensajetag;
   private Button cancelescrbirtag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.detecta_escribe_tag);
		mensajetag=(TextView)findViewById(R.id.mensajedeetiqueta);
		cancelescrbirtag=(Button)findViewById(R.id.btcancelarescribirtag);
		context = this;
		
		bundle = getIntent().getExtras();
		 dato=bundle.getString("datoestudiente");
		
		 adapter = NfcAdapter.getDefaultAdapter(this);
	        pendingIntent = PendingIntent.getActivity(this, 0, new Intent(this,getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), 0);
	        IntentFilter tagDetected = new IntentFilter(NfcAdapter.ACTION_TAG_DISCOVERED);
	        tagDetected.addCategory(Intent.CATEGORY_DEFAULT);
	        writeTagFilters = new IntentFilter[]{tagDetected};
	        
	        cancelescrbirtag.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					finish();
					
				}
			});
	}
	
	//El método write es el más importante, será el que se encargue de crear el mensaje 
    //y escribirlo en nuestro tag.
    private void write(String text, Tag tag) throws IOException, FormatException{
      NdefRecord[] records = {createRecord(text)}; 
      NdefMessage message = new NdefMessage(records);
      try {
    	  Ndef ndef = Ndef.get(tag);
    	  if(ndef != null) {
    		  
    		  if(ndef.isWritable()) {
    			  ndef.connect();
			      ndef.writeNdefMessage(message);
			      ndef.close();
			      mensajetag.setText("Tag Escrita con exito");
			      
			      AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);  
			      dialogo1.setTitle("Cambio exitoso");  
			      dialogo1.setMessage("Tag Escrita \n"); 
			    //obligamos al usuario a pulsar los botones para cerrarlo
			      dialogo1.setCancelable(false);  
			      
			     dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {  
			          public void onClick(DialogInterface dialog, int id) {
			          	
			          	
			          	finish();
			          	dialog.cancel();
			          }  
			      });  
			                
			      dialogo1.show(); 
    		  }else{
    			  AlertDialog.Builder dialogo1 = new AlertDialog.Builder(this);  
			      dialogo1.setTitle("Alerta");  
			      dialogo1.setMessage("Tag No se puede Escribir en la Tag"); 
			    //obligamos al usuario a pulsar los botones para cerrarlo
			      dialogo1.setCancelable(false);  
			      
			     dialogo1.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {  
			          public void onClick(DialogInterface dialog, int id) {
			          	
			          	
			          	
			          	dialog.cancel();
			          }  
			      });  
			                
			      dialogo1.show(); 
    		  }
			      
			     
    	  
    	  }else if(ndef==null){
    		  NdefFormatable format = NdefFormatable.get(myTag);
              if(format != null) {
	            	  try {
	                      format.connect();
	                     format.format(message);
	                      Toast.makeText(this, "HA SIDO FORMATEADA Y ESCRITA", Toast.LENGTH_LONG).show();
	                     // format.close();
	                     finish();
	   
	                  } catch (TagLostException tle) {
	                	  tle.printStackTrace();
	                 	// Toast.makeText(this, "error 5", Toast.LENGTH_LONG).show();
	                     
	                  } catch (IOException ioe) {
	                	  ioe.printStackTrace();
	                 	// Toast.makeText(this, "error 6", Toast.LENGTH_LONG).show();
	                     
	                  }catch (FormatException fe) {
	                	  fe.printStackTrace();
	                 // Toast.makeText(this, "error 3" + myTag.toString(), Toast.LENGTH_LONG).show();
	                  }
            	  
              }else{
            	  Toast.makeText(this, "NO SE A FORMATEADO", Toast.LENGTH_LONG).show();
              }
        	  
          }
      
      } catch(Exception e) {
    	  e.printStackTrace();
   	  // Toast.makeText(this, "error 4", Toast.LENGTH_LONG).show();
    }
      
      //finish();
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
 
    }
    //en onnewIntent manejamos el intent para encontrar el Tag
    @SuppressLint("NewApi") 
    protected void onNewIntent(Intent intent){
    	try{
    	if(NfcAdapter.ACTION_TAG_DISCOVERED.equals(intent.getAction())){
            myTag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
         //   Toast.makeText(this, "ok detectado" + myTag.toString(), Toast.LENGTH_LONG).show();
          
	              //Si no existe tag al que escribir, mostramos un mensaje de que no existe.
	           if(myTag == null){
	        Toast.makeText(context, "no Existe tag", Toast.LENGTH_LONG).show();
	          }else{
	               //Llamamos al método write que definimos más adelante donde le pasamos por
	               //parámetro el tag que hemos detectado y el mensaje a escribir.
	        	  
	        	
	        write(dato.toUpperCase(),myTag);
	          //  Toast.makeText(context, "si existe tag", Toast.LENGTH_LONG).show();
	        
	         }
          }
	       }catch(IOException e){
	    Toast.makeText(context, "error 1",Toast.LENGTH_LONG).show();
	        e.printStackTrace();
	    }catch(FormatException e){
	       Toast.makeText(context, "error 2", Toast.LENGTH_LONG).show();
	    e.printStackTrace();
	    }
            
 
        
    }
 
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

}
