package com.tranhoangdai.korengui.client.ui;

import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.Modal;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class AboutBox extends Composite  {

	private static AboutBoxUiBinder uiBinder = GWT.create(AboutBoxUiBinder.class);

	@UiField
	Button closeBtn;

	@UiField
	Modal modal;

	interface AboutBoxUiBinder extends UiBinder<Widget, AboutBox> {
	}

	public AboutBox() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void show(){
		modal.show();
	}

	@UiHandler("closeBtn")
	void onClick(ClickEvent e){
		modal.hide(true);
	}



}
