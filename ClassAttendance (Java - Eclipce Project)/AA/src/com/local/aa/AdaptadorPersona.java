package com.local.aa;

import java.util.ArrayList;







import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
/*Esta clase extiende de ArrayAdapter para poder 
 * personalizarla a nuestro gusto*/
public class AdaptadorPersona extends ArrayAdapter<Persona> {

	Activity contexto;
	ArrayList<Persona> pers;
	private int option;
	
	/*Constructor del AdaptadorDias donde se le pasaran 
	 * por parametro el contexto de la aplicacion y el
	 *  ArrayList de las Persona*/
	public AdaptadorPersona(Activity context, ArrayList<Persona> pers) {
		//Llamada al constructor de la clase superior donde 
		//requiere el contexto, el layout y el arraylist
		super(context,R.layout.activity_itemnfc,pers);
		this.contexto=context;
		this.pers=pers;
		
	}
	
	/*Este metodo es el que se encarga de dibujar cada Item de 
	 * la lista y se invoca cada vez que se necesita mostrar 
	 * un item.*/
	public View getView(final int position, View convertView, ViewGroup parent) {
		View item = convertView;
		/*Creamos esta variable para almacenar posteriormente 
		 * en el la vista que ha dibujado*/
		final VistaItem vistaitem;
		/*Si se decide que no existe una vista reutilizable para 
		 * el proximo item entra en la condicion.
//De este modo tambien ahorramos tener que volver a generar 
 * vistas*/
		if (item == null) {
//Obtenemos una referencia de Inflater para poder inflar el diseño
			LayoutInflater inflator=contexto.getLayoutInflater();
//Se le define a la vista (item) el tipo de diseño que tiene que tener
			item=inflator.inflate(R.layout.activity_itemnfc, null);
//Creamos un nuevo vistaitem que se almacenara en el
			//tag de la vista
			vistaitem=new VistaItem();
//Almacenamos en el objeto la referencia del TextView 
			//buscandolo por ID			
			vistaitem.nombreCompleto=(TextView)item.findViewById(R.id.name);
			vistaitem.ident=(TextView)item.findViewById(R.id.ident);
			//tambien almacenamos en el objeto la referencia 
			//del CheckBox buscandolo por ID
			
			//vistaitem.asistio=(TextView) item.findViewById(R.id.asistiotv1);
			//vistaitem.excusa=(TextView) item.findViewById(R.id.excusatv1);
			
			vistaitem.chkEstado=(ImageView)item.findViewById(R.id.imgvestado);
			//vistaitem.chkExcu = (CheckBox) item.findViewById(R.id.checkexcusa1);
//Ahora si, guardamos en el tag de la vista el objeto vistaitem
			item.setTag(vistaitem);
		}else{
			/*//En caso de que la vista sea ya reutilizable 
			 * se recupera el objeto VistaItem almacenada 
			 * en su tag*/
			vistaitem=(VistaItem)item.getTag();
		}
		//Se cargan los datos desde el ArrayList
		vistaitem.nombreCompleto.setText(pers.get(position).getApellido()+" "+pers.get(position).getNombre());
		vistaitem.ident.setText(pers.get(position).getIdent());
		
		
		
	//	vistaitem.chkExcu.setChecked(pers.get(position).isChecked2());
		
	//	vistaitem.asistio.setTextColor(Color.RED);
	//	vistaitem.excusa.setTextColor(Color.RED);
		
	
if (pers.get(position).isChecked()==true) {
			
			vistaitem.chkEstado.setImageResource(R.drawable.chulo1);
			pers.get(position).setChecked(true);
			pers.get(position).setChecked2(false);
		//	option=1;
			
		
		}
if (pers.get(position).isChecked2()==true) {
	
	vistaitem.chkEstado.setImageResource(R.drawable.document_512);
	pers.get(position).setChecked2(true);
	pers.get(position).setChecked(false);
	//option=2;

}
if(pers.get(position).isChecked()==false && pers.get(position).isChecked2()==false){
	vistaitem.chkEstado.setImageResource(R.drawable.x_mark_svg);
	pers.get(position).setChecked(false);
	pers.get(position).setChecked2(false);
}
vistaitem.chkEstado.setOnClickListener(new View.OnClickListener() {
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(pers.get(position).isChecked()==true){
			vistaitem.chkEstado.setImageResource(R.drawable.document_512);
			pers.get(position).setChecked2(true);
			pers.get(position).setChecked(false);
			// option=2;
			}else if(pers.get(position).isChecked2()==true){
				vistaitem.chkEstado.setImageResource(R.drawable.x_mark_svg);
				pers.get(position).setChecked(false);
				pers.get(position).setChecked2(false);
			//	 option=3;
			}else if(pers.get(position).isChecked()==false && pers.get(position).isChecked2()==false){
				vistaitem.chkEstado.setImageResource(R.drawable.chulo1);
				pers.get(position).setChecked(true);
				pers.get(position).setChecked2(false);
				// option=1;
			}	
		
	
	}
});


	
		
		//Se devuelve ya la vista nueva o reutilizada que ha sido dibujada
		return (item);
		
		
	}
	
	//Esta clase se usa para almacenar el TextView y el
	//CheckBox de una vista y es donde esta el "truco" 
	//para que las vistas se guarden
	static class VistaItem {
	TextView nombreCompleto;
	TextView ident;
	ImageView chkEstado;
	//ImageView chkExcu;
	//TextView asistio;
	//TextView excusa;

	}
}
