package com.telecom.misc;

public class BooleanHolder {
	boolean bool;
	
	public BooleanHolder(boolean bool) {
		this.bool = bool;
	}
	
	public boolean isTrue() {
		return bool;
	}
	
	public void setTrue() {
		bool = true;
	}
	public void setFalse() {
		bool = false;
	}
}
