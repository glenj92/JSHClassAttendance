package com.local.aa;



import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;


public class AdaptadorEstuNFC extends ArrayAdapter<EstuNFC> {

	/** Selected items in the list */
	private ArrayList<Integer> mSelection = new ArrayList<Integer>();

	Activity contexto;
	ArrayList<EstuNFC> estu;
	
	public AdaptadorEstuNFC(Activity context, ArrayList<EstuNFC> estu) {
		//Llamada al constructor de la clase superior donde 
		//requiere el contexto, el layout y el arraylist
		super(context,R.layout.items_estu,estu);
		this.contexto=context;
		this.estu=estu;
		
	}

	public void setNewSelection(int position) {
		estu.get(position).setSeleccion(true);
		//Toast.makeText(this, mSelection.get(position), Toast.LENGTH_LONG).show();
		notifyDataSetChanged();
	}

/*	public void eliminarSelection() {
		//estu.remove(position);
		for(int j=1;j<=5;j++){
			for(int i=0;i<estu.size();i++){
				if(estu.get(i).isSeleccion()){
					estu.remove(i);
				}
			}
			}
		//Toast.makeText(this, mSelection.get(i), Toast.LENGTH_LONG).show();
		//notifyDataSetChanged();
	}
*/
	/**
	 * Get current selected items
	 * @return list of items
	 */
	public ArrayList<EstuNFC> getCurrentCheckedPosition() {
		return estu;
	}

	/**
	 * Remove an element from selected items
	 * @param position Item position
	 */
	public void removeSelection(int position) {
		estu.get(position).setSeleccion(false);
				
		notifyDataSetChanged();
	}

	/**
	 * Clear current selection
	 */
	public void clearSelection() {
		//mSelection = new ArrayList<Integer>();
		for(int i=0;i<estu.size();i++){
			estu.get(i).setSeleccion(false);
			}
		//mSelection.removeAll(mSelection);
		//for(int i=0;i<mSelection.size();i++){
			//int a = mSelection.get(i);
				
			// mSelection.remove(i);
			
		//	}
			
		//mSelection = new ArrayList<Integer>();
		//notifyDataSetChanged();
	}

	/**
	 * Get number of selected items
	 * @return Selection count
	 */
	public int getSelectionCount() {
		int cont=0;
		for(int i=0;i<estu.size();i++){
		if(estu.get(i).isSeleccion()){
			cont++;
		}
		}
		return cont;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View item = convertView;
		//View item = super.getView(position, convertView, parent);
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
		//return (item);
		
		//View v = super.getView(position, convertView, parent);

		item.setBackgroundColor(getContext().getResources().getColor(
				android.R.color.transparent)); // Default color

		if (estu.get(position).isSeleccion()) {
			item.setBackgroundColor(getContext().getResources().getColor(
					android.R.color.tab_indicator_text)); // color when selected
		}

		//return v;
		return item;
	}

	static class VistaItem {
		TextView nombre;
		TextView ident;
		
		//CheckBox chkEstado;

		}
}