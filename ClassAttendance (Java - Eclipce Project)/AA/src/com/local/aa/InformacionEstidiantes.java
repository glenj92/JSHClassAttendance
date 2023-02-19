package com.local.aa;



import java.util.StringTokenizer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class InformacionEstidiantes extends Activity {
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		//super.onActivityResult(requestCode, resultCode, data);
		 if(requestCode==222 && resultCode==RESULT_OK){
			  String res=data.getExtras().getString("resul");
			  
         			Intent intent=new Intent();
			   		intent.putExtra("resultado", res);
			   		setResult(RESULT_OK, intent);
         			
         		
			  
		  }
		 
	}

	private Button asis,falla,excu;
	private TextView nomestuinfo;
	private Bundle bundle;
	private String did,dnom,dgru;
	private double as,fa,ex;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.informacion_estudiantes);
		
		asis=(Button)findViewById(R.id.button1asisestu);
		excu=(Button)findViewById(R.id.button2excuestu);
		falla=(Button)findViewById(R.id.button3fallasestu);
		nomestuinfo=(TextView)findViewById(R.id.textView1infornombreidentestudiante);
		
		bundle = getIntent().getExtras();
		
		did= bundle.getString("idEstudiante");
		dnom= bundle.getString("NombreEstudiante");
		dgru= bundle.getString("codGrupo");
		
		as= bundle.getDouble("asiste");
		fa= bundle.getDouble("falla");
		ex= bundle.getDouble("excu");
		
		nomestuinfo.setText(dnom.toString());
		
		asis.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(InformacionEstidiantes.this, ListaAsistenciasEstudiantes.class);
				
				   i.putExtra("idEstudiante", did.toString().toString().trim());
				   i.putExtra("NombreEstudiante", dnom.toString().trim());
				   i.putExtra("codGrupo", dgru.toString().trim());
				   startActivityForResult(i, 222);
				   //startActivity(i);
				
			}
		});
		
		excu.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(InformacionEstidiantes.this, ListaEscusasEstudiantes.class);
				
				   i.putExtra("idEstudiante", did.toString().toString().trim());
				   i.putExtra("NombreEstudiante", dnom.toString().trim());
				   i.putExtra("codGrupo", dgru.toString().trim());
				   startActivityForResult(i, 222);
				   //startActivity(i);
			}
		});
		
		falla.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(InformacionEstidiantes.this, ListaFallasEstudiantes.class);
				
				   i.putExtra("idEstudiante", did.toString().toString().trim());
				   i.putExtra("NombreEstudiante", dnom.toString().trim());
				   i.putExtra("codGrupo", dgru.toString().trim());
				   startActivityForResult(i, 222);
			//	startActivity(i);
			}
		});
		

		
	}
	
	
	public void grafica(View v){
		GraficaPie pie=new GraficaPie();
		Intent pieIntent=pie.getinIntent(this, as, ex, fa,dnom.toString().trim());
		startActivity(pieIntent);
		
	}

}
