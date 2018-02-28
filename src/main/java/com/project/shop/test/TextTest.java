package com.project.shop.test;

public class TextTest {
	public static void main(String[] args) {
		String a = "000112";
		int num = Integer.parseInt(a);
		System.out.println(num);
		System.out.println( String.format("%06d", 112) );
	}
}
