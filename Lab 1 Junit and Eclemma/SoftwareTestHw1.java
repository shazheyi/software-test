package com.junit4.main;

public class SoftwareTestHw1 {
	/*
	该函数思想为：共有8个货币（1个50,1个20，1个10,2个5,3个1），那么每个金币是否
	取用可以用8位二进制数表示（0代表不用,1代表使用），这样的话，可以遍历0-255，
	并将其转为对应的货币金额
	*/
	public int F(int x) {
		if(x==0) return 0;
		int[] n= {1,1,1,5,5,10,20,50};
		int out=0;
		int cnt=0;
		while(x!=0) {
			out+=n[cnt++]*(x%2);
			x/=2;
		}
		return out;
	}
	//输出函数，遍历所有可能的货币金额，查看输入金额是否有可能
	public String outPut(int in) {
		boolean judge=false;
		String out="";
		if(in<=93&&in>=0) { //最小可能值为0，最大可能值为93，范围之外的直接不可能
			for(int i=0;i<256;i++) {
			  if(in==F(i)) {
				 judge=true;
				 break;
			  }
		    }
		}
		if(judge) {
			out="Yes";
		}else {
			out="No";
		}
		return out;
	}
//	public static void main(String[] args) {
//		SoftwareTestHw1 testHw1=new SoftwareTestHw1(93);
//		testHw1.outPut();
//	}

}
