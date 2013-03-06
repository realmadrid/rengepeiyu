package edu.sdu.online.rengepeiyang.test;

import java.util.Calendar;

public class TestLevel {
	private int[] level = new int[11]; //每一项典型表现的情况
	private int[] meixiangtishu = {3,2,3,1,3,3,3,2,2,1,1};
	private int[] meileixiangshu = {3,2,6};
	public int[] initLevel(String answer) {
		int index = 0; //记录第几题
		int index1 = 0; //记录第几项典型行为
		int temp1 = 0;
		int temp2 = 0;
		int l1 = 0;
		int l2 = 0;
		int l3 = 0;
		char temp = ' ';
		for(int i=0; i<meileixiangshu.length; i++) { //三类：i表示第几类
			temp1 = index1;
			for(int j=temp1; j<temp1+meileixiangshu[i]; j++) { //每一类多少项典型行为：j记录第几项典型行为
				temp2 = index;
				l1 = 0;
				l2 = 0; 
				l3 = 0;
				for(int k=temp2; k<temp2+meixiangtishu[j]; k++) { //k记录第几题
					temp = answer.charAt(index);  //判断每一类典型行为的题目选择的优,良,弱个数
					if(temp == '1') {
						l1 ++; //优秀
					} else if(temp == '2') {
						l2 ++; //良好
					} else {
						l3 ++; //弱项
					}
					index ++;
				}
				//判断每一典型行为的级别
				if(l2==0 && l3==0) {
					level[index1] = 1; //优 
				} else if(l3 != 0) {
					level[index1] = 3; //弱
				} else {
					level[index1] = 2; //良
				}
				index1 ++;
			}
		}
		for(int i=0; i<level.length; i++) {
			System.out.print(level[i]+" ");
		}
		return level;
	}
	public int[] getState(int states[]) {
		int res[] = new int[2];
		int result = -1;
		int temp = -1;
		int level1 = 0; //优
		int level2 = 0; //良
		int level3 = 0; //弱
		//统计前两类，即前5项典型表现的情况
		for(int i=0; i<5; i++) {
			if(states[i] == 1) {
				level1 ++; //优
			} else if(states[i] == 2) {
				level2 ++; //良
			} else {
				level3 ++; //弱
			}
		}
		//根据前两类的表现情况
		if(level3 >0) { //有弱项
			result = 4;
		} else { //无弱项，可以继续
			if(level1 == 5) { //优=5
				result = 1;
			} else if(level1 >= 3) { //优>=3，且无弱项
				result = 2;
			} else { //无弱项,A<3
				result = 3;
			}
		}
		/*if(result == 4) //不能继续了，一，二类表现中有C
			return result;*/
		res[0] = result;
		//在一，二类判断的基础上
		level1 = 0;
		level2 = 0;
		level3 = 0;
		for(int i=5; i<11; i++) {
			if(states[i] == 1) {
				level1 ++; //优
			} else if(states[i] == 2) {
				level2 ++; //良
			} else {
				level3 ++; //弱
			}
		}
		System.out.println("level1="+level1+"level2="+level2+"level3="+level3);
		/*if(result == 4)  //第一二类结果中出现了C，则不需要继续往下了，结果就是4
			return result;*/

		//在第一，二类结果+第三类结果来计算最终结果
		if(result == 1) {
			if(level1 == 6) {
				result = 5;
			} else {
				if(level3==0) {
					if(level1>=3)result = 6;
					else if(level3==0)result=7;
				} 
			}
		} 
		if(result<5){
			if(result<=2){
				if(level3<=2){
					result=8;
				}
			}
			if(result<=3){
				if(level3<=4)result=9;
			}
		}
		res[1] = result;
		return res;
	}
	/**
	 * 第一类：8个题
	 * 		  1.1（1-3）
	 * 		  1.2（4-5）
	 * 		  1.3（6-8）
	 * 第二类：4个题
	 *        2.1(9)
	 *        2.2(10-12)
	 * 第三类：12个题
	 * 		  3.1（13-15）
	 * 		  3.2（16-18）
	 * 		  3.3（19-20）
	 * 	      3.4（21-22）
	 *        3.5（23）
	 *        3.6（24）
	 * @author pingguoilove
	 *
	 */
	public static void main(String args[]) {
		String answer = "111113111111111111111132";
		TestLevel tl = new TestLevel();
		//int states[] = tl.initLevel(answer);
		int states[] = {1,2,2,1,1,/**/3,3,3,1,1,1};
		System.out.println();
		int stat[] = tl.getState(states);
		System.out.println(stat[0]+"===="+stat[1]);
		Calendar ca = Calendar.getInstance();
		System.out.println("currentYear==="+ca.get(Calendar.YEAR));
		for(int i=5; i<=7; i++) {
			System.out.println("=="+i);
		}
		/*String a = "2009001";
		System.out.println(Integer.valueOf("2009"));*/
	}
}
