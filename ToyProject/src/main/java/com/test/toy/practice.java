package com.test.toy;

import java.util.Arrays;
import java.util.stream.IntStream;

public class practice {

	
	public static void main(String[] args) {
		
		int arr[] = {1,2,3,4,6};
		
		double answer = ((double)IntStream.of(arr).sum())/arr.length;
		
		System.out.println(answer);
			        
		String a = "12345";
		
		a.chars().map(n -> n - '0').forEach(n-> System.out.println(n));
		
		a.chars().map(n -> n - '0').forEach(n-> System.out.println(n));
		
		int c = (int) Arrays.stream(arr).average().orElse(0);
	
		System.out.println(c);
	}
	
}
