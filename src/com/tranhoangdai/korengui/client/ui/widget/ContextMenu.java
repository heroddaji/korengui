package com.tranhoangdai.korengui.client.ui.widget;

import com.google.gwt.event.dom.client.ContextMenuEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
@SuppressWarnings("unused")
public class ContextMenu extends PopupPanel implements MouseOutHandler{

	Widget parent = null;
	VerticalPanel panel = new VerticalPanel();
	ContextMenuEvent contextEvent = null;

	public ContextMenu(Widget _parent){
		super();
		this.parent = _parent;
		add(panel);

	}

	public void addPopupElement(Widget popupItem){
		panel.add(popupItem);
	}
	public void show(int x, int y){
		setPopupPosition(x, y);
		show();
	}

	@Override
	public void onMouseOut(MouseOutEvent event) {
		hide();
	}
}
