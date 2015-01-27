package com.zzy.study;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class POSController {

	private Map<String, GoodsBean> itemRelation = new HashMap<String, GoodsBean>();
	private Map<String, GoodsBean> boughtGoods = new HashMap<String, GoodsBean>();

	public POSController(){
		initGoodsRelation();
	}
	
	private void initGoodsRelation() {
		GoodsBean badmintonGoods = new GoodsBean("'ITEM000001'", "羽毛球", 1.00f, "个", true);
		GoodsBean appleGoods = new GoodsBean("'ITEM000003'", "苹果", 5.50f, "斤", false);
		GoodsBean ccGoods = new GoodsBean("'ITEM000005'", "可口可乐", 3.00f, "瓶", true);

		itemRelation.put("'ITEM000001'", badmintonGoods);
		itemRelation.put("'ITEM000003'", appleGoods);
		itemRelation.put("'ITEM000005'", ccGoods);
	}

	public void countSubString(String totalString) {
		while (totalString.length() > 0) {
			int index = totalString.indexOf(",");
			String foodName = totalString.substring(0, index + 1);
			String[] splitStrs = foodName.split("-");
			GoodsBean goodsBean = new GoodsBean();
			if (splitStrs.length > 1) {
				goodsBean = countMoreGoodsInfo(splitStrs);
			}else{
				goodsBean = countSingleGoodsInfo(totalString, foodName);
			}
			if(null == goodsBean)return;
			totalString = totalString.replaceAll(foodName, "").trim();
		}
		printBill();
	}
	
	private void addGoods(String goodsId, GoodsBean goodsBean){
		if(boughtGoods.containsKey(goodsId)){
			GoodsBean tempBean = boughtGoods.get(goodsId);
			tempBean.setGoodsNum(tempBean.getGoodsNum() + goodsBean.getGoodsNum());
			boughtGoods.put(goodsId, tempBean);
			goodsBean = tempBean;
		}else{
			boughtGoods.put(goodsId, goodsBean);
		}
	}
	
	private GoodsBean countSingleGoodsInfo(String printStr, String goodsId){
		int count = 0;
		Pattern pattern = Pattern.compile(goodsId);
		Matcher matcher = pattern.matcher(printStr);
		while (matcher.find()) {
			count++;
		}
		goodsId = goodsId.replace(",", "").trim();
		GoodsBean goodsBean = getGoodsInfo(goodsId, count);
		if(null != goodsBean)
			addGoods(goodsId, goodsBean);
		return goodsBean;
	}
	
	private GoodsBean getGoodsInfo(String goodsId, int count){
		GoodsBean goodsBean = new GoodsBean();
		goodsId = goodsId.replace(",", "").trim();
		goodsBean.setGoodsId(goodsId);
		GoodsBean goodsInfo = itemRelation.get(goodsId);
		if(null == goodsInfo){
			System.out.println("商品编号为：" + goodsId+ "的商品不存在，请先添加该商品！");
			return null;
		}
		goodsBean.setGoodsInfo(goodsInfo);
		goodsBean.setGoodsNum(count);
		return goodsBean;
	}
	
	private GoodsBean countMoreGoodsInfo(String[] splitStrs){
		int count = 0;
		String moreName = splitStrs[0].trim() + "'";
		count = Integer.parseInt(splitStrs[1].replace(",", "").replace("'", ""));
		GoodsBean goodsBean = getGoodsInfo(moreName, count);
		if(null != goodsBean)
			addGoods(moreName, goodsBean);
		return goodsBean;
		
	}
	
	private GoodsBean discountGoods(GoodsBean goodsBean){
		int giveCount = (int) Math.floor(goodsBean.getGoodsNum() / 2);
		GoodsBean giveGoods = new GoodsBean();
		if (giveCount > 0) {
			giveGoods.setGoodsInfo(goodsBean);
			giveGoods.setGoodsNum(giveCount);
		}
		return giveGoods;
	}
	
	private void printBill(){
		System.out.println("***<没钱赚商店>购物清单***");
		float payMoney = 0f;
		float saveMoney = 0f;
		ArrayList<GoodsBean> giveGoodsList = new ArrayList<GoodsBean>();
		for(Map.Entry<String, GoodsBean> entry : boughtGoods.entrySet()){
			GoodsBean goodsItem = entry.getValue();
			if (goodsItem.isDiscount) 
				giveGoodsList.add(discountGoods(goodsItem));
			System.out.println("名称：" + goodsItem.getGoodsName() + ", 数量："
					+ goodsItem.getGoodsNum() + goodsItem.getGoodsUnit()
					+ ", 单价：" + goodsItem.getGoodPrice() + "(元)" + ", 小计："
					+ goodsItem.getGoodsNum() * goodsItem.getGoodPrice()+ "(元)");
			payMoney += goodsItem.getGoodsNum() * goodsItem.getGoodPrice();
		}
		saveMoney = printSendGoods(giveGoodsList);
		System.out.println("----------------------");
		System.out.println("总计：" + payMoney + "(元)");
		if(saveMoney > 0)
			System.out.println("节省：" + saveMoney + "(元)");
		System.out.println("**********************");
	}
	
	private float printSendGoods(ArrayList<GoodsBean>  giveGoodsList){
		float saveMoney = 0f;
		if (null != giveGoodsList && giveGoodsList.size() > 0) {
			System.out.println("----------------------\n挥泪赠送商品：");
			for (GoodsBean giveGoods : giveGoodsList) {
				System.out.println("名称：" + giveGoods.getGoodsName() + ", 数量："
						+ giveGoods.getGoodsNum() + giveGoods.getGoodsUnit());
				saveMoney += giveGoods.getGoodsNum() * giveGoods.getGoodPrice();
			}
		}
		return saveMoney;
	}
}
