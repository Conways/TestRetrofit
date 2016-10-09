package com.example.testretrofit;

public class HttpResult {

	public HttpResult() {
		super();
		// TODO Auto-generated constructor stub
	}

	private BaseModel baseModel;

	private int code;

	private String state;

	public BaseModel getBaseModel() {
		return baseModel;
	}

	public void setBaseModel(BaseModel baseModel) {
		this.baseModel = baseModel;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "HttpResult [baseModel=" + baseModel + ", code=" + code + ", state=" + state + "]";
	}

}
