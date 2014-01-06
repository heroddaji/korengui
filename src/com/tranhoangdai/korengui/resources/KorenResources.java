package com.tranhoangdai.korengui.resources;

import com.google.gwt.core.client.GWT;
import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.resources.client.TextResource;

public interface KorenResources extends ClientBundle{
	public static final KorenResources INSTANCE = GWT.create(KorenResources.class);

	@Source("my.css")
	//@CssResource.NotStrict
	public MyStyle css();

	@Source("config.xml")
	public TextResource config();

	@Source("i18n.txt")
	public TextResource i18n();


}
