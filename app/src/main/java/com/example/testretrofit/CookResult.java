package com.example.testretrofit;

public class CookResult extends BaseModel {

	private BaseModel baseModel;
	private String reason;
	private int error_code;


	public BaseModel getBaseModel() {
		return baseModel;
	}

	public void setBaseModel(BaseModel baseModel) {
		this.baseModel = baseModel;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public int getError_code() {
		return error_code;
	}

	public void setError_code(int error_code) {
		this.error_code = error_code;
	}
}
