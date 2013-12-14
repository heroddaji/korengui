package com.tranhoangdai.korengui.client.controller;

import java.util.List;
import java.util.Map;

import com.allen_sauer.gwt.log.client.Log;
import com.google.gwt.core.client.GWT;
import com.tranhoangdai.korengui.client.EventBus;
import com.tranhoangdai.korengui.client.EventBus.ActionState;
import com.tranhoangdai.korengui.client.model.Host;
import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.model.ModelWithId;
import com.tranhoangdai.korengui.client.model.Switch;
import com.tranhoangdai.korengui.client.service.util.ClientServiceAsync;
import com.tranhoangdai.korengui.client.service.util.ClientServiceHelper;
import com.tranhoangdai.korengui.client.view.InfoPanel;
import com.tranhoangdai.korengui.client.view.SvgPanel;
import com.tranhoangdai.korengui.client.view.svg.AbstractElementSvg;

public class PathFlowEventController extends AbstractEventController {
	
	public static PathFlowEventController INSTANCE = GWT.create(PathFlowEventController.class);

	ModelWithId model1 = null;
	ModelWithId model2 = null;

	@Override
	public void handleEvent(Object source) {
		AbstractElementSvg clickedSvg = (AbstractElementSvg) source;

		if (EventBus.INSTANCE.getState() == ActionState.FLOW1) {
			Log.debug("clicked first node");
			model1 = clickedSvg.getModel();
		}

		if (EventBus.INSTANCE.getState() == ActionState.FLOW2 && model1 != null) {
			Log.debug("clicked second node");
			model2 = clickedSvg.getModel();

			if (model1.getId().equals(model2.getId())) {
				GUIController.INSTANCE.tellSameId();
			} else {
				performAction();
			}
		}
	}

	private void performAction() {
		ClientServiceAsync<Map<String, Switch>, Map<Integer, Link>, Map<String, Host>> callback = new ClientServiceAsync<Map<String, Switch>, Map<Integer, Link>, Map<String, Host>>() {

			@Override
			public void onSuccess(Map<String, Switch> nodes, Map<Integer, Link> links, Map<String, Host> hosts) {
				SvgPanel.INSTANCE.drawPathFlow(links);
				InfoPanel.INSTANCE.drawPathFlow(links);
			}

			@Override
			public void onFailure(Throwable throwable) {
				Log.error(throwable.getMessage());
			}

		};
		ClientServiceHelper.INSTANCE.getPathFlow(callback, model1.getId(), model2.getId());
	}

}
