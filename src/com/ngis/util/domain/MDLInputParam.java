package com.ngis.util.domain;

import java.io.Serializable;
import java.util.List;

public class MDLInputParam implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String stateId;
	private List<String> resEventName;
	private List<String> noresEventName;
	public String getStateId() {
		return stateId;
	}
	public void setStateId(String stateId) {
		this.stateId = stateId;
	}
	public List<String> getResEventName() {
		return resEventName;
	}
	public void setResEventName(List<String> resEventName) {
		this.resEventName = resEventName;
	}
	public List<String> getNoresEventName() {
		return noresEventName;
	}
	public void setNoresEventName(List<String> noresEventName) {
		this.noresEventName = noresEventName;
	}
}
