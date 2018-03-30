package com.yrtech.wx.capp.framework.core.data;

import java.util.ArrayList;
import java.util.List;

public class EasyTree {
	
	private String id;
	private String text;
	
	private List<EasyTree> children = new ArrayList<EasyTree>();

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public List<EasyTree> getChildren() {
		return children;
	}

	public void setChildren(List<EasyTree> children) {
		this.children = children;
	}

}
