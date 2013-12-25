package com.tranhoangdai.korengui.client.view.widget;

import com.gargoylesoftware.htmlunit.javascript.host.Window;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.DialogBox;

public class AboutBox extends DialogBox {
	public AboutBox() {
		// Set the dialog box's caption.
		
		setTitle("Koren GUI");
		setText("This application gives users the visual interaction with \nnetwork topology for project KOREN in Kyung Hee University.");

		this.setPopupPosition(Window.WINDOW_WIDTH/2, Window.WINDOW_HEIGHT/2);
		
		
		// Enable animation.
		setAnimationEnabled(true);

		// Enable glass background.
		setGlassEnabled(true);

		// DialogBox is a SimplePanel, so you have to set its widget property to
		// whatever you want its contents to be.
		center();
		Button ok = new Button("OK");
		ok.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				AboutBox.this.hide();
			}
		});
		setWidget(ok);
	}
}
