package com.telecom.gui;

public class InterfaceMode {
	private ModeChangesListener listener;
	private boolean checkMode;
	
	public InterfaceMode(ModeChangesListener listener) {
		this.listener = listener;
		checkMode = true;
	}
	
	public void endOfTrack() {
		if(checkMode) {
			checkMode = false;
			listener.setNextMode();
		}
	}
	public void userEvent() {
		if(checkMode) {
			checkMode = false;
			listener.setNextMode();
		}
		else {
			checkMode = true;
			listener.setCheckMode();
		}
	}
}
