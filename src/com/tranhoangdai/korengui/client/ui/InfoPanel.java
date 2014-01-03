package com.tranhoangdai.korengui.client.ui;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class InfoPanel extends Composite {

	private static InfoPanelUiBinder uiBinder = GWT.create(InfoPanelUiBinder.class);

	interface InfoPanelUiBinder extends UiBinder<Widget, InfoPanel> {
	}

	public InfoPanel() {
		initWidget(uiBinder.createAndBindUi(this));
	}

}
