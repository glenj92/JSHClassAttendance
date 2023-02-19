package com.local.aa;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * 
 * String array based Adapter that allows multiple selection in a list. It saves currently selected items
 * and allows to add, remove or get selected items
 * 
 * @author Antonio Leiva Gordillo
 *
 */
public class SelectionAdapter extends ArrayAdapter<String> {

	/** Selected items in the list */
	private ArrayList<Integer> mSelection = new ArrayList<Integer>();

	/**
	 * Class construactor
	 * @param context Execution context
	 * @param resource list item layout
	 * @param textViewResourceId TextView identifier
	 * @param objects Array of list elements
	 */
	public SelectionAdapter(Context context, int resource,
			int textViewResourceId, List<String> objects) {
		super(context, resource, textViewResourceId, objects);
	}

	/**
	 * Adds an element in selection and updates the view
	 * @param position Item position
	 */
	int num;
	public void setNewSelection(int position) {
		if(mSelection.size()==0){
		num=position;
		mSelection.add(position);
		notifyDataSetChanged();
		}
	}
	public int poicion() {
		
		return num;
		
	}

	/**
	 * Get current selected items
	 * @return list of items
	 */
	public ArrayList<Integer> getCurrentCheckedPosition() {
		return mSelection;
	}

	/**
	 * Remove an element from selected items
	 * @param position Item position
	 */
	public void removeSelection(int position) {
		mSelection.remove(Integer.valueOf(position));
		notifyDataSetChanged();
	}

	/**
	 * Clear current selection
	 */
	public void clearSelection() {
		mSelection = new ArrayList<Integer>();
		notifyDataSetChanged();
	}

	/**
	 * Get number of selected items
	 * @return Selection count
	 */
	public int getSelectionCount() {
		return mSelection.size();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = super.getView(position, convertView, parent);

		v.setBackgroundColor(getContext().getResources().getColor(
				android.R.color.transparent)); // Default color

		if (mSelection.contains(position)) {
			v.setBackgroundColor(getContext().getResources().getColor(
					android.R.color.tab_indicator_text)); // color when selected
		}

		return v;
	}
}
