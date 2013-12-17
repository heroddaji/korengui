package com.tranhoangdai.korengui.client.view.widget;

import com.google.gwt.event.dom.client.ContextMenuEvent;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.Widget;

public class ContextMenu extends PopupPanel{
	
	Widget parent = null;
	
	public ContextMenu(Widget _parent){
		super();
		this.parent = _parent;
		add(new HTML("haha"));
		hide();
		
	}
	public void show(ContextMenuEvent event){
		setPopupPosition(event.getNativeEvent().getClientX(), event.getNativeEvent().getClientY());
		show();
	}
}
