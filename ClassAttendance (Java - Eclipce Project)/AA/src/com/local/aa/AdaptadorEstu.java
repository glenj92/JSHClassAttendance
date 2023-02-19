package com.local.aa;

import java.util.ArrayList;



import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
/*Esta clase extiende de ArrayAdapter para poder 
 * personalizarla a nuestro gusto*/
public class AdaptadorEstu extends ArrayAdapter<Estu> {

	private Activity contexto;
	private ArrayList<Estu> estu;
	
	/*Constructor del AdaptadorDias donde se le pasaran 
	 * por parametro el contexto de la aplicacion y el
	 *  ArrayList de las Persona*/
	public AdaptadorEstu(Activity context, ArrayList<Estu> estu) {
		//Llamada al constructor de la clase superior donde 
		//requiere el contexto, el layout y el arraylist
		super(context,R.layout.items_estu,estu);
		this.contexto=context;
		this.estu=estu;
		
	}
	
	/*Este metodo es el que se encarga de dibujar cada Item de 
	 * la lista y se invoca cada vez que se necesita mostrar 
	 * un item.*/
	public View getView(int position, View convertView, ViewGroup parent) {
		View item = convertView;
		/*Creamos esta variable para almacenar posteriormente 
		 * en el la vista que ha dibujado*/
		VistaItem vistaitem;
		/*Si se decide que no existe una vista reutilizable para 
		 * el proximo item entra en la condicion.
//De este modo tambien ahorramos tener que volver a generar 
 * vistas*/
		if (item == null) {
//Obtenemos una referencia de Inflater para poder inflar el diseño
			LayoutInflater inflator=contexto.getLayoutInflater();
//Se le define a la vista (item) el tipo de diseño que tiene que tener
			item=inflator.inflate(R.layout.items_estu, null);
//Creamos un nuevo vistaitem que se almacenara en el
			//tag de la vista
			vistaitem=new VistaItem();
//Almacenamos en el objeto la referencia del TextView 
			//buscandolo por ID			
			vistaitem.nombre=(TextView)item.findViewById(R.id.tvNombreItem);
			vistaitem.ident=(TextView)item.findViewById(R.id.tvidentidadItem);
			//tambien almacenamos en el objeto la referencia 
			//del CheckBox buscandolo por ID
			//vistaitem.chkEstado=(CheckBox)item.findViewById(R.id.chk);
//Ahora si, guardamos en el tag de la vista el objeto vistaitem
			item.setTag(vistaitem);
		}else{
			/*//En caso de que la vista sea ya reutilizable 
			 * se recupera el objeto VistaItem almacenada 
			 * en su tag*/
			vistaitem=(VistaItem)item.getTag();
		}
		//Se cargan los datos desde el ArrayList
		vistaitem.nombre.setText(estu.get(position).getApellid()+" "+estu.get(position).getNombre());
		vistaitem.ident.setText(estu.get(position).getIdent());
		//vistaitem.chkEstado.setChecked(pers.get(position).isChecked());
		//Se devuelve ya la vista nueva o reutilizada que ha sido dibujada
		return (item);
		
		
	}
	
	//Esta clase se usa para almacenar el TextView y el
	//CheckBox de una vista y es donde esta el "truco" 
	//para que las vistas se guarden
	static class VistaItem {
	TextView nombre;
	TextView ident;
	
	//CheckBox chkEstado;

	}
}
