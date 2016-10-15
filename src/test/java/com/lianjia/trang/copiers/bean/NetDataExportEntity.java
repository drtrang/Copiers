package com.lianjia.trang.copiers.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.google.common.base.MoreObjects;

/**
 * 网签数据导出
 * 
 * @author trang
 */
public class NetDataExportEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	private String netHouseCode;
	private String bizNo;
	private String contractNo;
	private Boolean isLogout;
	private String signCompany;
	private String signCompanyAlias;
	private Date signTime;
	private BigDecimal contractTotalPrice;
	private BigDecimal houseUnitPrice;
	private BigDecimal transUnitPrice;
	private BigDecimal houseTotalPrice;
	private String moneyTransfer;
	private String isLoan;
	private String houseProperty;
	private String houseType;
	private String housePropertyType;
	private String buildYear;
	private String houseOwnershipNo;
	private String district;
	private String street;
	private String yardNo;
	private String buildingNo;
	private String houseNo;
	private String marketingArea;
	private String floor;
	private String totalFloors;
	private BigDecimal buildArea;
	private String buildAreaRegion;
	private String layout;
	private String decorationStandard;
	private String planningPurpose;
	private String sellerProperty;
	private String purchaserProperty;
	private String netMonth;
	private Long sequenceNumber;
	private String detailedAddress;
	private String shopName;
	private String shopCode;
	private String areaName;
	private String areaCode;
	private String regionName;
	private String regionCode;
	private String resblockName;
	//前七家
	private String isLianjia;
	private String dataType;
	private Boolean isDrop;
	private String dropReason;
	private Boolean isNotTarget;
	private String notTargetReason;
	private String otherComment;
	//导出时把null替换为空字符串
	private Integer auditStatus;
	private String auditOpinion;
	private Long creatorUcid;
	private String creatorName;
	private String creatorMobile;
	private Long auditorUcid;
	private String auditorName;
	private String auditorMobile;

	/**
	 * 下面为业务方不需要字段
	 */
	//@Excel(name="责任店组")
	private String teamName;
	//@Excel(name="店组编码")
	private String teamCode;
	//@Excel(name="房屋ID")
	private Long houseId;
	//@Excel(name="楼幢ID")
	private Long buildingId;
	//@Excel(name="楼盘ID")
	private Long resblockId;
	//@Excel(name="链家房源编号")
	private String housedelId;
	//@Excel(name="佣金")
	private BigDecimal commission;
	//@Excel(name="成交价")
	private BigDecimal realPrice;

	public NetDataExportEntity() {}
	
	@Override
	public String toString() {
	    return MoreObjects.toStringHelper(this).omitNullValues()
	        .add("netHouseCode", netHouseCode)
	        .add("bizNo", bizNo)
	        .add("contractNo", contractNo)
	        .add("isLogout", isLogout)
	        .add("signCompany", signCompany)
	        .add("signCompanyAlias", signCompanyAlias)
	        .add("signTime", signTime)
	        .add("contractTotalPrice", contractTotalPrice)
	        .add("houseUnitPrice", houseUnitPrice)
	        .add("transUnitPrice", transUnitPrice)
	        .add("houseTotalPrice", houseTotalPrice)
	        .add("moneyTransfer", moneyTransfer)
	        .add("isLoan", isLoan)
	        .add("houseProperty", houseProperty)
	        .add("houseType", houseType)
	        .add("housePropertyType", housePropertyType)
	        .add("buildYear", buildYear)
	        .add("houseOwnershipNo", houseOwnershipNo)
	        .add("district", district)
	        .add("street", street)
	        .add("yardNo", yardNo)
	        .add("buildingNo", buildingNo)
	        .add("houseNo", houseNo)
	        .add("marketingArea", marketingArea)
	        .add("floor", floor)
	        .add("totalFloors", totalFloors)
	        .add("buildArea", buildArea)
	        .add("buildAreaRegion", buildAreaRegion)
	        .add("layout", layout)
	        .add("decorationStandard", decorationStandard)
	        .add("planningPurpose", planningPurpose)
	        .add("sellerProperty", sellerProperty)
	        .add("purchaserProperty", purchaserProperty)
	        .add("netMonth", netMonth)
	        .add("sequenceNumber", sequenceNumber)
	        .add("detailedAddress", detailedAddress)
	        .add("shopName", shopName)
	        .add("shopCode", shopCode)
	        .add("areaName", areaName)
	        .add("areaCode", areaCode)
	        .add("regionName", regionName)
	        .add("regionCode", regionCode)
	        .add("resblockName", resblockName)
	        .add("isLianjia", isLianjia)
	        .add("dataType", dataType)
	        .add("isDrop", isDrop)
	        .add("dropReason", dropReason)
	        .add("isNotTarget", isNotTarget)
	        .add("notTargetReason", notTargetReason)
	        .add("otherComment", otherComment)
	        .add("auditOpinion", auditOpinion)
	        .add("auditStatus", auditStatus)
	        .add("creatorUcid", creatorUcid)
	        .add("creatorName", creatorName)
	        .add("creatorMobile", creatorMobile)
	        .add("auditorUcid", auditorUcid)
	        .add("auditorName", auditorName)
	        .add("auditorMobile", auditorMobile)
	        .add("teamName", teamName)
	        .add("teamCode", teamCode)
	        .add("houseId", houseId)
	        .add("buildingId", buildingId)
	        .add("resblockId", resblockId)
	        .add("housedelId", housedelId)
	        .add("commission", commission)
	        .add("realPrice", realPrice)
	        .toString();
	}
	
	public String getNetMonth() {
		return netMonth;
	}
	public void setNetMonth(String netMonth) {
		this.netMonth = netMonth;
	}
	public String getIsLianjia() {
		return isLianjia;
	}
	public void setIsLianjia(String isLianjia) {
		this.isLianjia = isLianjia;
	}
	public String getDistrict() {
	    return district;
	}
	public void setDistrict(String district) {
	    this.district = district;
	}
	public String getStreet() {
	    return street;
	}
	public void setStreet(String street) {
	    this.street = street;
	}
	public String getYardNo() {
	    return yardNo;
	}
	public void setYardNo(String yardNo) {
	    this.yardNo = yardNo;
	}
	public String getBuildingNo() {
	    return buildingNo;
	}
	public void setBuildingNo(String buildingNo) {
	    this.buildingNo = buildingNo;
	}
	public String getHouseNo() {
	    return houseNo;
	}
	public void setHouseNo(String houseNo) {
	    this.houseNo = houseNo;
	}
	public String getFloor() {
	    return floor;
	}
	public void setFloor(String floor) {
	    this.floor = floor;
	}
	public String getTotalFloors() {
	    return totalFloors;
	}
	public void setTotalFloors(String totalFloors) {
	    this.totalFloors = totalFloors;
	}
	public String getSignCompany() {
	    return signCompany;
	}
	public void setSignCompany(String signCompany) {
	    this.signCompany = signCompany;
	}
	public Date getSignTime() {
	    return signTime;
	}
	public void setSignTime(Date signTime) {
	    this.signTime = signTime;
	}
	public String getNetHouseCode() {
	    return netHouseCode;
	}
	public void setNetHouseCode(String netHouseCode) {
	    this.netHouseCode = netHouseCode;
	}
	public String getHouseOwnershipNo() {
	    return houseOwnershipNo;
	}
	public void setHouseOwnershipNo(String houseOwnershipNo) {
	    this.houseOwnershipNo = houseOwnershipNo;
	}
	public String getContractNo() {
	    return contractNo;
	}
	public void setContractNo(String contractNo) {
	    this.contractNo = contractNo;
	}
	public BigDecimal getContractTotalPrice() {
	    return contractTotalPrice;
	}
	public void setContractTotalPrice(BigDecimal contractTotalPrice) {
	    this.contractTotalPrice = contractTotalPrice;
	}
	public BigDecimal getHouseTotalPrice() {
	    return houseTotalPrice;
	}
	public void setHouseTotalPrice(BigDecimal houseTotalPrice) {
	    this.houseTotalPrice = houseTotalPrice;
	}
	public BigDecimal getHouseUnitPrice() {
	    return houseUnitPrice;
	}
	public void setHouseUnitPrice(BigDecimal houseUnitPrice) {
	    this.houseUnitPrice = houseUnitPrice;
	}
	public String getIsLoan() {
	    return isLoan;
	}
	public void setIsLoan(String isLoan) {
	    this.isLoan = isLoan;
	}
	public String getHouseProperty() {
	    return houseProperty;
	}
	public void setHouseProperty(String houseProperty) {
	    this.houseProperty = houseProperty;
	}
	public String getHouseType() {
	    return houseType;
	}
	public void setHouseType(String houseType) {
	    this.houseType = houseType;
	}
	public String getBuildYear() {
	    return buildYear;
	}
	public void setBuildYear(String buildYear) {
	    this.buildYear = buildYear;
	}
	public String getPlanningPurpose() {
	    return planningPurpose;
	}
	public void setPlanningPurpose(String planningPurpose) {
	    this.planningPurpose = planningPurpose;
	}
	public String getSellerProperty() {
	    return sellerProperty;
	}
	public void setSellerProperty(String sellerProperty) {
	    this.sellerProperty = sellerProperty;
	}
	public String getPurchaserProperty() {
	    return purchaserProperty;
	}
	public void setPurchaserProperty(String purchaserProperty) {
	    this.purchaserProperty = purchaserProperty;
	}
	public BigDecimal getBuildArea() {
	    return buildArea;
	}
	public void setBuildArea(BigDecimal buildArea) {
	    this.buildArea = buildArea;
	}
	public String getLayout() {
	    return layout;
	}
	public void setLayout(String layout) {
	    this.layout = layout;
	}
	public String getDetailedAddress() {
	    return detailedAddress;
	}
	public void setDetailedAddress(String detailedAddress) {
	    this.detailedAddress = detailedAddress;
	}
	public String getResblockName() {
	    return resblockName;
	}
	public void setResblockName(String resblockName) {
	    this.resblockName = resblockName;
	}
	public String getTeamName() {
	    return teamName;
	}
	public void setTeamName(String teamName) {
	    this.teamName = teamName;
	}
	public String getTeamCode() {
	    return teamCode;
	}
	public void setTeamCode(String teamCode) {
	    this.teamCode = teamCode;
	}
	public String getShopName() {
	    return shopName;
	}
	public void setShopName(String shopName) {
	    this.shopName = shopName;
	}
	public String getShopCode() {
	    return shopCode;
	}
	public void setShopCode(String shopCode) {
	    this.shopCode = shopCode;
	}
	public String getAreaName() {
	    return areaName;
	}
	public void setAreaName(String areaName) {
	    this.areaName = areaName;
	}
	public String getAreaCode() {
	    return areaCode;
	}
	public void setAreaCode(String areaCode) {
	    this.areaCode = areaCode;
	}
	public String getRegionName() {
	    return regionName;
	}
	public void setRegionName(String regionName) {
	    this.regionName = regionName;
	}
	public String getRegionCode() {
	    return regionCode;
	}
	public void setRegionCode(String regionCode) {
	    this.regionCode = regionCode;
	}
	public String getDataType() {
	    return dataType;
	}
	public void setDataType(String dataType) {
	    this.dataType = dataType;
	}
	public Boolean getIsDrop() {
	    return isDrop;
	}
	public void setIsDrop(Boolean isDrop) {
	    this.isDrop = isDrop;
	}
	public String getDropReason() {
	    return dropReason;
	}
	public void setDropReason(String dropReason) {
	    this.dropReason = dropReason;
	}
	public Boolean getIsNotTarget() {
	    return isNotTarget;
	}
	public void setIsNotTarget(Boolean isNotTarget) {
	    this.isNotTarget = isNotTarget;
	}
	public String getNotTargetReason() {
	    return notTargetReason;
	}
	public void setNotTargetReason(String notTargetReason) {
	    this.notTargetReason = notTargetReason;
	}
	public String getOtherComment() {
	    return otherComment;
	}
	public void setOtherComment(String otherComment) {
	    this.otherComment = otherComment;
	}
	public String getSignCompanyAlias() {
	    return signCompanyAlias;
	}
	public void setSignCompanyAlias(String signCompanyAlias) {
	    this.signCompanyAlias = signCompanyAlias;
	}
	public BigDecimal getTransUnitPrice() {
	    return transUnitPrice;
	}
	public void setTransUnitPrice(BigDecimal transUnitPrice) {
	    this.transUnitPrice = transUnitPrice;
	}
	public Long getSequenceNumber() {
	    return sequenceNumber;
	}
	public void setSequenceNumber(Long sequenceNumber) {
	    this.sequenceNumber = sequenceNumber;
	}
	public String getMarketingArea() {
	    return marketingArea;
	}
	public void setMarketingArea(String marketingArea) {
	    this.marketingArea = marketingArea;
	}
	public Boolean getIsLogout() {
	    return isLogout;
	}
	public void setIsLogout(Boolean isLogout) {
	    this.isLogout = isLogout;
	}
	public String getDecorationStandard() {
	    return decorationStandard;
	}
	public void setDecorationStandard(String decorationStandard) {
	    this.decorationStandard = decorationStandard;
	}
	public String getMoneyTransfer() {
	    return moneyTransfer;
	}
	public void setMoneyTransfer(String moneyTransfer) {
	    this.moneyTransfer = moneyTransfer;
	}
	public String getHousePropertyType() {
	    return housePropertyType;
	}
	public void setHousePropertyType(String housePropertyType) {
	    this.housePropertyType = housePropertyType;
	}
	public String getBizNo() {
	    return bizNo;
	}
	public void setBizNo(String bizNo) {
	    this.bizNo = bizNo;
	}
	public Long getHouseId() {
	    return houseId;
	}
	public void setHouseId(Long houseId) {
	    this.houseId = houseId;
	}
	public Long getBuildingId() {
	    return buildingId;
	}
	public void setBuildingId(Long buildingId) {
	    this.buildingId = buildingId;
	}
	public Long getResblockId() {
	    return resblockId;
	}
	public void setResblockId(Long resblockId) {
	    this.resblockId = resblockId;
	}
	public String getHousedelId() {
	    return housedelId;
	}
	public void setHousedelId(String housedelId) {
	    this.housedelId = housedelId;
	}
	public BigDecimal getRealPrice() {
	    return realPrice;
	}
	public void setRealPrice(BigDecimal realPrice) {
	    this.realPrice = realPrice;
	}
	public BigDecimal getCommission() {
	    return commission;
	}
	public void setCommission(BigDecimal commission) {
	    this.commission = commission;
	}
	public String getBuildAreaRegion() {
		return buildAreaRegion;
	}
	public void setBuildAreaRegion(String buildAreaRegion) {
		this.buildAreaRegion = buildAreaRegion;
	}
	public Integer getAuditStatus() {
		return auditStatus;
	}
	public void setAuditStatus(Integer auditStatus) {
		this.auditStatus = auditStatus;
	}
	public Long getCreatorUcid() {
		return creatorUcid;
	}
	public void setCreatorUcid(Long creatorUcid) {
		this.creatorUcid = creatorUcid;
	}
	public String getCreatorName() {
		return creatorName;
	}
	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}
	public String getCreatorMobile() {
		return creatorMobile;
	}
	public void setCreatorMobile(String creatorMobile) {
		this.creatorMobile = creatorMobile;
	}
	public Long getAuditorUcid() {
		return auditorUcid;
	}
	public void setAuditorUcid(Long auditorUcid) {
		this.auditorUcid = auditorUcid;
	}
	public String getAuditorName() {
		return auditorName;
	}
	public void setAuditorName(String auditorName) {
		this.auditorName = auditorName;
	}
	public String getAuditorMobile() {
		return auditorMobile;
	}
	public void setAuditorMobile(String auditorMobile) {
		this.auditorMobile = auditorMobile;
	}
	public String getAuditOpinion() {
		return auditOpinion;
	}
	public void setAuditOpinion(String auditOpinion) {
		this.auditOpinion = auditOpinion;
	}
}
