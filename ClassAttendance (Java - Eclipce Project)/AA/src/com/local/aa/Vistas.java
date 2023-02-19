package com.local.aa;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Vistas extends Activity {
private Button btnListaAsis,btnGeneralListadoVista;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.vistas);
		btnListaAsis=(Button)findViewById(R.id.btListadoAsistencia);
		btnGeneralListadoVista=(Button)findViewById(R.id.listadoGeneralVistaBTN);
		btnListaAsis.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(Vistas.this, ListaAsistencia.class);
				startActivity(i);
			}
		});
		
btnGeneralListadoVista.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i=new Intent(Vistas.this, ListaGeneralAsis.class);
				startActivity(i);
			}
		});
	}

}
