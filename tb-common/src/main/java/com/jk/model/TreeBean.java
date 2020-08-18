package com.jk.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class TreeBean implements Serializable {

	private Integer id;
	
	private String text;
	
	private String path;
	
	private Integer pid;
	
	private List<TreeBean> nodes;
	/**
	 * 表示是否是子节点可以被选中
	 */
	private String selectable;
	
}
