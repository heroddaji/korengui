package com.tranhoangdai.korengui.client.service.util;

public interface ClientServiceAsync<N,L,H> {
	void onSuccess(N nodes, L links, H hosts);
	void onFailure(Throwable throwable);
}
