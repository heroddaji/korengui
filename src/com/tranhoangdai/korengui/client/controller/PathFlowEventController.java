package com.tranhoangdai.korengui.client.controller;

import java.util.Map;

import com.allen_sauer.gwt.log.client.Log;
import com.tranhoangdai.korengui.client.EventBus.ActionState;
import com.tranhoangdai.korengui.client.Korengui;
import com.tranhoangdai.korengui.client.model.Host;
import com.tranhoangdai.korengui.client.model.Link;
import com.tranhoangdai.korengui.client.model.ModelWithId;
import com.tranhoangdai.korengui.client.model.Switch;
import com.tranhoangdai.korengui.client.service.util.ClientServiceAsync;
import com.tranhoangdai.korengui.client.service.util.ClientServiceHelper;
import com.tranhoangdai.korengui.client.ui.SvgPanel;
import com.tranhoangdai.korengui.client.ui.svg.AbstractElementSvg;

public class PathFlowEventController extends AbstractEventController {

	ModelWithId model1 = null;
	ModelWithId model2 = null;

	@Override
	public void handleEvent(Object source) {
		AbstractElementSvg clickedSvg = (AbstractElementSvg) source;

		if (Korengui.INSTANCE.getEventBus().getState() == ActionState.FLOW1) {
			Log.debug("clicked first node");
			model1 = clickedSvg.getModel();
		}

		if (Korengui.INSTANCE.getEventBus().getState() == ActionState.FLOW2 && model1 != null) {
			Log.debug("clicked second node");
			model2 = clickedSvg.getModel();

			if (model1.getId().equals(model2.getId())) {
			} else {
				performAction();
			}
		}
	}

	private void performAction() {
		ClientServiceAsync<Map<String, Switch>, Map<Integer, Link>, Map<String, Host>> callback = new ClientServiceAsync<Map<String, Switch>, Map<Integer, Link>, Map<String, Host>>() {

			@Override
			public void onSuccess(Map<String, Switch> nodes, Map<Integer, Link> links, Map<String, Host> hosts) {

			}

			@Override
			public void onFailure(Throwable throwable) {
				Log.error(throwable.getMessage());
			}

		};
		ClientServiceHelper.INSTANCE.getPathFlow(callback, model1.getId(), model2.getId());
	}

}
