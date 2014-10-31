package com.cse308.projectaim.beans;

import com.cse308.projectaim.hibernate.AIMEntity;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AIMEntityListBean implements Serializable {

	private List<AIMEntity> list = new ArrayList<AIMEntity>();

	public AIMEntityListBean() { }

	public List<AIMEntity> getList() { return list; }
	public void setList(List<AIMEntity> list) { this.list = list; }
	
}
