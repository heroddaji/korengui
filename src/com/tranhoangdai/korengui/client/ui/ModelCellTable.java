package com.tranhoangdai.korengui.client.ui;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.github.gwtbootstrap.client.ui.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.tranhoangdai.korengui.client.model.ModelWithId;

@SuppressWarnings("unused")
public class ModelCellTable<T> extends CellTable<T> {

	public ModelCellTable(String... columnAttributes) {
		for (final String columnAttr : columnAttributes) {
			TextColumn<Object> column = new TextColumn<Object>() {

				@Override
				public String getValue(Object object) {
					ModelWithId model = (ModelWithId) object;
					return model.callMethod(columnAttr);
				}
			};

			addColumn((Column<T, ?>) column,columnAttr);
		}
	}

	public <K, V> void addModelData(Map<K, V> models) {
		setRowCount(models.size());
		List<V> modelsList = new ArrayList<V>();
		modelsList.addAll(models.values());
		setRowData(0, (List<? extends T>) modelsList);
	}

	public <V> void addModelData(V model) {
		setRowCount(1);
		List<V> modelsList = new ArrayList<V>();
		modelsList.add(model);
		setRowData(0, (List<? extends T>) modelsList);
	}
}
