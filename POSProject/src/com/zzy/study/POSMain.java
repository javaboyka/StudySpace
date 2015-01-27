package com.zzy.study;

public class POSMain {
	private static String printStr = "'ITEM000001-4', 'ITEM000001', 'ITEM000001', "
			+ "'ITEM000001', 'ITEM000001', 'ITEM000003', 'ITEM000003', 'ITEM000003-2', 'ITEM000003', 'ITEM000003', 'ITEM000005', 'ITEM000005', 'ITEM000005',";

	public static void main(String[] args) {
		POSController posController = new POSController();
		posController.countSubString(printStr.trim());
		
	}
}
