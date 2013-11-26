package com.tranhoangdai.korengui.client.service.util;

public interface ClientServiceAsync<N,L,H> {
	void onSuccess(N n, L l , H h);
	void onFailure(Throwable throwable);
}
