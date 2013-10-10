package com.tranhoangdai.korengui.client.imp;

import org.vectomatic.dom.svg.ui.ExternalSVGResource;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.resources.client.ClientBundle;

public interface SvgResources extends ClientBundle{

	public static final SvgResources INSTANCE = GWT.create(SvgResources.class);
	
	@Source("switch.svg")
	public ExternalSVGResource theSwitch(); 
	
}
