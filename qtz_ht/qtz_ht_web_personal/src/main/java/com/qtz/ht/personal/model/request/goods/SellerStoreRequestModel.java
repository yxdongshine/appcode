package com.qtz.ht.personal.model.request.goods;

import javax.validation.constraints.NotNull;

import org.springframework.util.StringUtils;

import com.mall.core.common.DateUtil;
import com.wordnik.swagger.annotations.ApiModelProperty;

/**
 * 
 * <p>
 * Title:SellerStoreRequestModel
 * </p>
 * <p>
 * Description:(商家店铺请求模型)
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: 深圳市擎天柱信息科技有限公司
 * </p>
 * 
 * @author 涂鑫 - changbo.li
 * @version v1.0 2015年8月31日
 */
public class SellerStoreRequestModel {
  /**
   * 店铺id
   */
  @ApiModelProperty(value = "店铺id")
  private Long dmId;
  /**
   * 是否派送
   */
  @ApiModelProperty(value = "是否派送 ")
  private Boolean isSend;

  /**
   * 是否到店
   */
  @ApiModelProperty(value = "是否到店 ")
  private Boolean isStop;
  /**
   * 餐盒费
   */
  @ApiModelProperty(value = "餐盒费")
  private Double mealFee;

  /**
   * 派送费
   */
  @ApiModelProperty(value = "派送费")
  private Double sendFee;
  /**
   * 起送费
   */
  @ApiModelProperty(value = "起送")
  private Double sendOutUpFee=0d;

  /**
   * 停业开始时间
   */
  private String cBStartTime;
  /**
   * 停业结束时间
   */
  private String cBEndTime;
  
  /**
   * 免派送费
   */
  private Double freeSendFee;
  /**
   * 免包装费
   */
  private Double freeMealFee;
  /**
   * 店铺分类id
   */
  @NotNull(message = "店铺分类不能为null")
  private Long storeType;

  public Boolean getIsSend() {
    return isSend;
  }

  public void setIsSend(Boolean isSend) {
    this.isSend = isSend;
  }

  public Double getSendFee() {
    return sendFee;
  }

  public void setSendFee(Double sendFee) {
    this.sendFee = sendFee;
  }

  public Double getSendOutUpFee() {
    return sendOutUpFee;
  }

  public void setSendOutUpFee(Double sendOutUpFee) {
    this.sendOutUpFee = sendOutUpFee;
  }

  public Double getMealFee() {
    return mealFee;
  }

  public void setMealFee(Double mealFee) {
    this.mealFee = mealFee;
  }

  public Long getStoreType() {
    return storeType;
  }

  public void setStoreType(Long storeType) {
    this.storeType = storeType;
  }

  public Long getDmId() {
    return dmId;
  }

  public void setDmId(Long dmId) {
    this.dmId = dmId;
  }

  public Boolean getIsStop() {
    return isStop;
  }

  public void setIsStop(Boolean isStop) {
    this.isStop = isStop;
  }

  public String getcBStartTime() {
    if(!StringUtils.isEmpty(cBStartTime)){
      return  DateUtil.getStrDataTimes(cBStartTime)+"";
     }
    return null;
  }

  public void setcBStartTime(String cBStartTime) {
    this.cBStartTime = cBStartTime;
  }

  public String getcBEndTime() {
    if(!StringUtils.isEmpty(cBEndTime)){
      return  DateUtil.getStrDataTimes(cBEndTime)+"";
     }
    return null;
  }

  public void setcBEndTime(String cBEndTime) {
   
   
    this.cBEndTime = cBEndTime;
  }

  public Double getFreeSendFee() {
    return freeSendFee;
  }

  public void setFreeSendFee(Double freeSendFee) {
    this.freeSendFee = freeSendFee;
  }

  public Double getFreeMealFee() {
    return freeMealFee;
  }

  public void setFreeMealFee(Double freeMealFee) {
    this.freeMealFee = freeMealFee;
  }
  
}
