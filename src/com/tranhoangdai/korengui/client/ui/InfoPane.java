package com.tranhoangdai.korengui.client.ui;

import com.github.gwtbootstrap.client.ui.CellTable;
import com.github.gwtbootstrap.client.ui.Label;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.tranhoangdai.korengui.client.model.Switch;

public class InfoPane<T> extends Composite {

	private static InfoPaneUiBinder uiBinder = GWT.create(InfoPaneUiBinder.class);

	interface InfoPaneUiBinder extends UiBinder<Widget, InfoPane> {
	}

	@UiField(provided = true)
	CellTable<T> cell = new CellTable<T>();

	public InfoPane() {
		initWidget(uiBinder.createAndBindUi(this));

		TextColumn<Switch> idCol = new TextColumn<Switch>() {

			@Override
			public String getValue(Switch object) {
				return String.valueOf(object.getId());
			}
		};
		cell.addColumn((Column<T, ?>) idCol,"id");
		cell.setRowCount(0);
		cell.setEmptyTableWidget(new Label("Get network topololy to see data"));
	}

}
