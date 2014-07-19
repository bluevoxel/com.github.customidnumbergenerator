package com.github.customidnumbergenerator;

import javafx.scene.control.Label;

public class LabelCustom extends Label {
	
	private String type;
	private String inputText;
	
	LabelCustom(String t) {
		super();
		
		this.type = t;
	}
	
	public String getType() {
		return type;
	}
	
	public void setInputText(String input) {
		inputText = input;
	}
	
	public String getInputText() {
		return inputText;
	}
}
