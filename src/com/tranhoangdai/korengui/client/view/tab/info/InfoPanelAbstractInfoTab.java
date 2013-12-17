package com.tranhoangdai.korengui.client.view.tab.info;

import com.google.gwt.event.dom.client.ContextMenuEvent;
import com.google.gwt.event.dom.client.ContextMenuHandler;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.tranhoangdai.korengui.client.view.InfoPanel;
import com.tranhoangdai.korengui.client.view.widget.ContextMenu;

public abstract class InfoPanelAbstractInfoTab extends VerticalPanel implements ContextMenuHandler {
	protected InfoPanel parent = null;
	private ContextMenu contextMenu;

	public InfoPanelAbstractInfoTab(InfoPanel _parent) {
		this.parent = _parent;
		this.contextMenu = new ContextMenu(this);

		

		addDomHandler(this, ContextMenuEvent.getType());
		//	addDomHandler(this, MouseDownEvent.getType());
	}

	//	@Override
	//	public void onMouseDown(MouseDownEvent event) {
	//		if (event.getNativeButton() == NativeEvent.BUTTON_RIGHT) {
	//			ContextMenu ctxMenu = new ContextMenu();
	//			ctxMenu.show();
	//		}
	//	}

	@Override
	public void onContextMenu(ContextMenuEvent event) {
		event.preventDefault();
		event.stopPropagation();		
		contextMenu.show(event);
	}

	public abstract void showInfo();

}
