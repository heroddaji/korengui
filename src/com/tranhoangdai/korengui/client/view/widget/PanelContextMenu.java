package com.tranhoangdai.korengui.client.view.widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ContextMenuEvent;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.event.dom.client.MouseOutHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.PopupPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.tranhoangdai.korengui.client.EventBus;
import com.tranhoangdai.korengui.client.view.AbstractPanel;
import com.tranhoangdai.korengui.client.view.tab.svg.SvgPanelAbstractDrawTab;
@SuppressWarnings("unused")
public class PanelContextMenu extends PopupPanel implements MouseOutHandler{
	
	Widget parent = null;
	VerticalPanel panel = new VerticalPanel();
	ContextMenuEvent contextEvent = null;
	
	public PanelContextMenu(Widget _parent){
		super();
		this.parent = _parent;		
		add(panel);		
		
		Button closeBtn = new Button("Close tab");
		closeBtn.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				EventBus.INSTANCE.deliverEventUserClickCloseTabContextMenu(parent);
				hide();
			}
		});		
		
		panel.add(closeBtn);		
		hide();
		
		addDomHandler(this, MouseOutEvent.getType());
		
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
