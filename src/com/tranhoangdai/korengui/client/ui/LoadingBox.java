package com.tranhoangdai.korengui.client.ui;

import com.github.gwtbootstrap.client.ui.Modal;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class LoadingBox extends Composite {

	private static LoadingBoxUiBinder uiBinder = GWT.create(LoadingBoxUiBinder.class);

	interface LoadingBoxUiBinder extends UiBinder<Widget, LoadingBox> {
	}

	@UiField
	Modal modal;

	public LoadingBox() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	public void show(){
		modal.show(true);
	}

	public void hide(){
		modal.hide(true);
	}

}
