package com.zzy.study;

public class GoodsBean {

	public String goodsId;
	
	public String goodsName;
	
	public float goodPrice;
	
	public int goodsNum;
	
	public String goodsUnit;
	
	public boolean isDiscount;

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public int getGoodsNum() {
		return goodsNum;
	}

	public void setGoodsNum(int goodsNum) {
		this.goodsNum = goodsNum;
	}

	public float getGoodPrice() {
		return goodPrice;
	}

	public void setGoodPrice(float goodPrice) {
		this.goodPrice = goodPrice;
	}

	public String getGoodsUnit() {
		return goodsUnit;
	}

	public void setGoodsUnit(String goodsUnit) {
		this.goodsUnit = goodsUnit;
	}

	public boolean isDiscount() {
		return isDiscount;
	}

	public void setDiscount(boolean isDiscount) {
		this.isDiscount = isDiscount;
	}

	public GoodsBean() {
	}

	public GoodsBean(String goodsId, String goodsName, float goodPrice,
			String goodsUnit, boolean isDiscount) {
		this.goodsId = goodsId;
		this.goodsName = goodsName;
		this.goodPrice = goodPrice;
		this.goodsUnit = goodsUnit;
		this.isDiscount = isDiscount;
	}
	
	public void setGoodsInfo(GoodsBean goodsBean){
		this.goodsId = goodsBean.getGoodsId();
		this.goodsName = goodsBean.getGoodsName();
		this.goodPrice = goodsBean.getGoodPrice();
		this.goodsUnit = goodsBean.getGoodsUnit();
		this.isDiscount = goodsBean.isDiscount();
	}
}
