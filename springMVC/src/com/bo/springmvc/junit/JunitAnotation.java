package com.bo.springmvc.junit;

/**
 * @Description Junit注解测试
 * @author 王博
 * @version 2017年8月24日　下午3:16:53
 */
public class JunitAnotation {
	
	public int divide(int a, int b){
		return a / b;
	}
	
	public void loop(){
		while(true){
			System.out.println("run forever..");
		}
	}
	
	public void readFile(){
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void ignore(){
		System.out.println("method ignore execute!");
	}
	
	public void bubbleSort(int[] arr){
		boolean flag = true;
		for(int i = 0; i < arr.length-1 && flag; i++){ //控制比较轮数
			flag = false;
			for(int j = 0; j < arr.length-1-i;j++){ //控制每轮的两两比较次数
				if(arr[j] > arr[j+1]){
					int temp = arr[j+1];
					arr[j+1] = arr[j];
					arr[j] = temp;
					flag = true;
				}
			}
		}
	}
	
	public void quickSort(int[] arr, int left, int right){
		if(left < right){
			int base = arr[left];
			int i = left;
			int j = right;
			while(i < j){// 按照基准数完成1轮分割
				while(i < j && arr[j] >= base){
					j--;
				}
				
				while(i < j && arr[i] <= base){
					i++;
				}
				
				if(i < j){
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
			// 此时i=j 为基准数的索引位置
			if(i != left){
				arr[left] = arr[i];
				arr[i] = base;
			}
			
			quickSort(arr, left, i - 1);
			quickSort(arr, i + 1, right);
		}
	}
}
