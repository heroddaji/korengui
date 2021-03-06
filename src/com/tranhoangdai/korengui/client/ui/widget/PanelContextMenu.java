package com.tranhoangdai.korengui.client.ui.widget;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.MouseOutEvent;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Widget;
import com.tranhoangdai.korengui.client.EventBus;
import com.tranhoangdai.korengui.client.Korengui;

public class PanelContextMenu extends ContextMenu{

	public PanelContextMenu(Widget _parent) {
		super(_parent);
		Button closeBtn = new Button("Close tab");
		closeBtn.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				Korengui.INSTANCE.getEventBus().deliverEventUserClickCloseTabContextMenu(parent);
				hide();
			}
		});

		SvgElementTooltip tooltip = new SvgElementTooltip();
		//addPopupElement(tooltip);
		addPopupElement(closeBtn);
		hide();

		addDomHandler(this, MouseOutEvent.getType());
	}

}
