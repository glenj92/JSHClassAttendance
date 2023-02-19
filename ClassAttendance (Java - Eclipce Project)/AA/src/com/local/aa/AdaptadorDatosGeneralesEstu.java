package com.local.aa;

import java.util.ArrayList;



import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class AdaptadorDatosGeneralesEstu extends ArrayAdapter<DatosGenralesEstu> {
	Activity contexto;
	ArrayList<DatosGenralesEstu> Dato_Gneral;

	//Constructor del AdaptadorDias donde se le pasaran por parametro el contexto de la aplicacion y el ArrayList de los dias 
	public AdaptadorDatosGeneralesEstu(Activity context, ArrayList<DatosGenralesEstu> DatoGen) {
		//Llamada al constructor de la clase superior donde requiere el contexto, el layout y el arraylist
		super(context, R.layout.items_datos_general_asis, DatoGen);
		this.contexto = context;
		this.Dato_Gneral = DatoGen;

	}

	//Este metodo es el que se encarga de dibujar cada Item de la lista
	//y se invoca cada vez que se necesita mostrar un nuevo item.
	//En el se pasan parametros como la posicion del elemento mostrado, la vista (View) del elemento mostrado y el conjunto de vistas
	public View getView(int position, View convertView, ViewGroup parent) {
		View item = convertView;

		//Creamos esta variable para almacen posteriormente en el la vista que ha dibujado
		VistaItem vistaitem;

		//Si se decide que no existe una vista reutilizable para el proximo item entra en la condicion.
		//De este modo tambien ahorramos tener que volver a generar vistas
		if (item == null) {

			//Obtenemos una referencia de Inflater para poder inflar el diseño
			LayoutInflater inflador = contexto.getLayoutInflater();

			//Se le define a la vista (item) el tipo de diseño que tiene que tener
			item = inflador.inflate(R.layout.items_datos_general_asis, null);

			//Creamos un nuevo vistaitem que se almacenara en el tag de la vista
			vistaitem = new VistaItem();

			//Almacenamos en el objeto la referencia del TextView buscandolo por ID
			vistaitem.nombre = (TextView) item.findViewById(R.id.tvNomDt);
			vistaitem.ident = (TextView) item.findViewById(R.id.tvnoIdentDt);
			vistaitem.ape = (TextView) item.findViewById(R.id.tvApeDt);
			vistaitem.asi = (TextView) item.findViewById(R.id.tvnoAsisDt);
			vistaitem.fall = (TextView) item.findViewById(R.id.tvnoFallaDt);
			vistaitem.excu = (TextView) item.findViewById(R.id.tvnoExcuDt);

			//tambien almacenamos en el objeto la referencia del CheckBox buscandolo por ID
			

			//Ahora si, guardamos en el tag de la vista el objeto vistaitem 
			item.setTag(vistaitem);

		} else {
			//En caso de que la vista sea ya reutilizable se recupera el objeto VistaItem almacenada en su tag
			vistaitem = (VistaItem) item.getTag();
		}

		//Se cargan los datos desde el ArrayList
		vistaitem.nombre.setText(Dato_Gneral.get(position).getNombre());
		vistaitem.ident.setText(Dato_Gneral.get(position).getIdent());
		vistaitem.ape.setText(Dato_Gneral.get(position).getApellido());
		vistaitem.asi.setText(Dato_Gneral.get(position).getAsiste());
		vistaitem.fall.setText(Dato_Gneral.get(position).getFallas());
		vistaitem.excu.setText(Dato_Gneral.get(position).getExusa());
		
		
		//Se devuelve ya la vista nueva o reutilizada que ha sido dibujada
		return (item);
	}


	//Esta clase se usa para almacenar el TextView y el CheckBox de una vista y es donde esta el "truco" para que las vistas se guarden
	static class VistaItem {
		TextView nombre;
		TextView ident;
		TextView ape;
		TextView asi;
		TextView fall;
		TextView excu;
		
	}

}
