package com.tranhoangdai.korengui.client.view.tab.info;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.tranhoangdai.korengui.client.model.Switch;
import com.tranhoangdai.korengui.client.view.InfoPanel;
import com.tranhoangdai.korengui.client.view.widget.LinkCellTable;
import com.tranhoangdai.korengui.client.view.widget.SwitchCellTable;

public class InfoPanelPathFlowTab extends InfoPanelAbstractInfoTab {

	LinkCellTable cellTablePath = null;
	SwitchCellTable cellTableNode = null;
	TabLayoutPanel parent = null;
	List<Switch> nodes = new ArrayList<Switch>();

	public InfoPanelPathFlowTab(InfoPanel _parent) {
		super(_parent);		
	}

	private void setupCellTablesFlowInit() {

		// UI portion for add nodes to find patflow
		Label lblPathLabel = new Label("Add nodes to get PathFlow");
		add(lblPathLabel);

		cellTableNode = new SwitchCellTable();
		add(cellTableNode);

		

		Label lblEmpty1 = new Label("");
		lblEmpty1.setHeight("20px");
		add(lblEmpty1);

		Button btnGetPathFlow = new Button("Get PathFlow");
		add(btnGetPathFlow);

		btnGetPathFlow.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				if (nodes.size() == 2) {				
					
					Switch node1 = nodes.get(0);
					Switch node2 = nodes.get(1);
					
				}
			}
		});

		Button btnClearPathFlow = new Button("Clear");
		add(btnClearPathFlow);

		btnClearPathFlow.addClickHandler(new ClickHandler() {

			@Override
			public void onClick(ClickEvent event) {
				
			}
		});

		Label lblEmpty2 = new Label("");
		lblEmpty2.setHeight("50px");
		add(lblEmpty2);

	}

	private void setupCellTablesFlowResult() {
		// ui portion for result of getting pathflow
		Label resultLabel = new Label("PathFlow result");
		add(resultLabel);

		cellTablePath = new LinkCellTable();
		add(cellTablePath);
	
	}

	private void addPathFlowNode(Switch node) {
		if (cellTableNode == null) {
			setupCellTablesFlowInit();
		}		
		if (nodes.size() < 2) {
			parent.selectTab(this);
			nodes.add(node);
			cellTableNode.addNodeList(nodes);
		}
	}

	
	@Override
	public void showInfo() {
		// TODO Auto-generated method stub
		
	}

}
