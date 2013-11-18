package com.tranhoangdai.korengui.client;

import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.TabLayoutPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.tranhoangdai.korengui.client.extendgui.LinkCellTable;
import com.tranhoangdai.korengui.client.extendgui.NodeCellTable;
import com.tranhoangdai.korengui.client.imp.Utility;
import com.tranhoangdai.korengui.client.imp.node.Node;
import com.tranhoangdai.korengui.client.imp.node.zoom.ZoomableNode;
import com.tranhoangdai.korengui.client.interf.ZoomNotifier;

public class InfoPanelZoomTab extends VerticalPanel implements ZoomNotifier {

	private TabLayoutPanel parent = null;
	private ZoomableNode zoomNode = null;
	NodeCellTable cellTableNode = null;
	LinkCellTable cellTableLink = null;

	public InfoPanelZoomTab(TabLayoutPanel _parent) {
		parent = _parent;
		Utility.INSTANCE.addZoomAble(this);
	}

	@Override
	public void zoomIn(Node _zoomNode) {
		if (zoomNode != null) {
			if (zoomNode.equals(_zoomNode)) {
				parent.selectTab(this);
			}
		} else {
			zoomNode = (ZoomableNode) _zoomNode;
			setupCellTables();
			parent.selectTab(this);
		}
	}

	private void setupCellTables() {
		// table for display nodes information
		Label nodeLabel = new Label("Nodes information");
		add(nodeLabel);

		cellTableNode = new NodeCellTable();
		add(cellTableNode);

		// add empty lable for nice layout
		Label emptyLabel = new Label("");
		emptyLabel.setHeight("50px");
		add(emptyLabel);

		// table for display link
		Label linkLabel = new Label("Links information");
		add(linkLabel);

		cellTableLink = new LinkCellTable();
		add(cellTableLink);

		cellTableNode.addNodeMap(zoomNode.getChildNodes());
		cellTableLink.addLinkMap(zoomNode.getChildLinks());

	}

	@Override
	public Node getZoomNode() {
		return zoomNode;
	}

}
