package edu.sdu.online.rengepeiyang.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestSort {
	public static void main(String[] args) {
		String orign = "第一组 第四组 第二组 第八组 第六组";
		String array[] = orign.split(" ");
		List<Data> list = new ArrayList<Data>();
		for(int i=0; i<array.length; i++) {
			list.add(new Data(array[i]));
		}
		Collections.sort(list);
		for(int i=0; i<list.size(); i++) {
			System.out.println(list.get(i));
		}
		System.out.println("八".compareTo("六"));
	}
}
class Data implements Comparable<Data>{
	private String data;
	private String sortData;
	public Data(String data) {
		this.data = data;
		this.sortData = data.substring(1, this.data.length()-1);
	}
	public void replace(String s) {
		s.replaceAll("一", "1");
		s.replaceAll("二", "2");
		s.replaceAll("三", "1");
		s.replaceAll("四", "3");
		s.replaceAll("五", "4");
		s.replaceAll("六", "5");
		s.replaceAll("七", "1");
		s.replaceAll("八", "1");
		s.replaceAll("九", "1");
		s.replaceAll("十", "1");
	}
	public int compareTo(Data o) {
		
		int i= data.substring(1, this.data.length()-1).compareTo(o.data.substring(1,o.data.length()-1));
		System.out.println(data+"===="+o.data+"===="+i);
		if(i== 0) {
			return 0;
		} else if(i >0) {
			return 1;
		} else {
			return -1;
		}
	}
	public String toString() {
		return data;
	}
} 
