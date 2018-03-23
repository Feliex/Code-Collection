package com.feliex;

import java.util.Scanner;

/**
 * 汉得笔试题，猜数字游戏
 * @author Feliex
 * @version 2018-3-23
 */
public class HdExamination {
	private static int num=6;//题目条件个数

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("输入"+ num +"个题目条件(例如：1234,2A0B)：");
		
		Scanner in = new Scanner(System.in);
		String[] conds = new String[num];
		for(int i=0;i<num;i++){
			conds[i] = in.nextLine();
		}
		in.close();
		
		new HdExamination().run(conds);
	}
	
	/**
	 * 猜数字
	 * @param cond1
	 * @param cond2
	 * @param cond3
	 * @param cond4
	 */
	public void run(String[] conds){
		int target = 123;
		while(true){
			String targetstr = target+"";
			if(target>9999){
				System.out.println("no target!");
				break;
			}
			
			if(containRepeatChar(targetstr)){
				target++;
				continue;
			}
			if(targetstr.length()<4){
				targetstr = completion(targetstr);
			}
			
			boolean status = false;
			for(int i=0;i<num;i++){
				if(!judge(targetstr,conds[i].substring(0, 4),conds[i].substring(5))){
					target++;
					status=true;
					break;
				}
			}
			if(status)
				continue;
			System.out.println("target is :"+target);
			break;
		}
	}
	
	/**
	 * 判断该目标值是否符合题目要求
	 * @param target
	 * @param cond_1
	 * @param cond_2
	 * @return
	 */
	public boolean judge(String target,String cond_1,String cond_2){
		char[] targets=(target).toCharArray();
		char[] cond_1s=cond_1.toCharArray();
		char[] cond_2s=cond_2.toCharArray();
		int A = 0,B=0,i=0;
		while(i<4){
			if(targets[i]==cond_1s[i]){
				A++;
			}
			else if(cond_1.indexOf(targets[i])!=-1){
				B++;
			}
			i++;	
		}
		if(A == Integer.valueOf(cond_2s[0]+"") && B == Integer.valueOf(cond_2s[2]+""))
			return true;
		else
			return false;
		
	}
	
	/**
     * 判断字符串是否包含重复字符
     * @param str
     * @return
     */
    public  boolean containRepeatChar(String str){
        if(str==null||str.isEmpty()){
            return false;
        }
        char[] elements=str.toCharArray();    
        for(char e:elements){
            if(str.indexOf(e)!=str.lastIndexOf(e)){
                return true;
            }
        }
        return false;
    }
    
    /**
     * 自动补全四位数字，不足四位的前面补0
     * @param target
     * @return
     */
    public String completion(String target){
    	int length = target.length();
    	StringBuilder str = new StringBuilder();
    	for(int i = 0; i < 4-length; i++){
    		str.append("0");
    	}
    	str.append(target);
		return str.toString();
    }

}
