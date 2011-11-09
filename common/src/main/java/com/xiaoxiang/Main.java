package com.xiaoxiang;

import java.util.ArrayList;
import java.util.List;

/**
 * 测试类
 * 
 * @author xiang.xiaox
 */

public class Main {
	private List<String> list = new ArrayList<String>();

	@Override
	public String toString() {
		return "435345786肖祥7890890890890890890";
	}

	public List<String> getList() {		
		for (int i = 0; i < 10; i++) {
			list.add("次夺" + i);
		}
		return list;
	}
}
