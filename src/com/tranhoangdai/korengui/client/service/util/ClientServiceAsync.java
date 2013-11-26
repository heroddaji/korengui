package com.tranhoangdai.korengui.client.service.util;

public interface ClientServiceAsync<T> {
	void onSuccess(T result);
	void onFailure(Throwable throwable);
}
