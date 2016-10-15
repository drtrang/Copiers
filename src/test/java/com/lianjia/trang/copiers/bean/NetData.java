package com.lianjia.trang.copiers.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.google.common.base.MoreObjects;

public class NetData implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 唯一表示，目前是sequence_number
     */
    private Long netDataId;

    /**
     * 数据标识，代表第几次上传
     */
    private Integer netIndex;

    /**
     * 数据状态 1-月度 2-周度
     */
    private Integer netType;

    /**
     * 数据月份
     */
    private String netMonth;

    /**
     * 数据来源序号
     */
    private Long sequenceNumber;

    /**
     * 网签房源编号
     */
    private String netHouseCode;

    /**
     * 业务编号
     */
    private String bizNo;

    /**
     * 房屋id
     */
    private Long houseId;

    /**
     * 匹配的楼盘字典楼幢id
     */
    private Long buildingId;

    /**
     * 匹配的楼盘字典楼盘id
     */
    private Long resblockId;

    /**
     * 房源委托id
     */
    private String housedelId;

    /**
     * 区县名称
     */
    private String district;

    /**
     * 街道名称
     */
    private String street;

    /**
     * 院门牌号
     */
    private String yardNo;

    /**
     * 楼栋号
     */
    private String buildingNo;

    /**
     * 房间号
     */
    private String houseNo;

    /**
     * 所在层数
     */
    private String floor;

    /**
     * 总楼层
     */
    private String totalFloors;

    /**
     * 签约机构
     */
    private String signCompany;

    /**
     * 签约公司简称
     */
    private String signCompanyAlias;

    /**
     * 签约时间
     */
    private Date signTime;

    /**
     * 房屋所有权证号
     */
    private String houseOwnershipNo;

    /**
     * 合同编号
     */
    private String contractNo;

    /**
     * 合同总价
     */
    private BigDecimal contractTotalPrice;

    /**
     * 房屋总价
     */
    private BigDecimal houseTotalPrice;

    /**
     * 房屋单价
     */
    private BigDecimal houseUnitPrice;

    /**
     * 是否贷款
     */
    private String isLoan;

    /**
     * 房屋性质
     */
    private String houseProperty;

    /**
     * 房屋类型（楼房平房）
     */
    private String houseType;

    /**
     * 建筑年代
     */
    private String buildYear;

    /**
     * 规划用途
     */
    private String planningPurpose;

    /**
     * 卖方性质
     */
    private String sellerProperty;

    /**
     * 买方性质
     */
    private String purchaserProperty;

    /**
     * 建筑面积
     */
    private BigDecimal buildArea;

    /**
     * 面积档位
     */
    private String buildAreaRegion;

    /**
     * 户型
     */
    private String layout;

    /**
     * 详细物业地址
     */
    private String detailedAddress;

    /**
     * 楼盘名
     */
    private String resblockName;

    /**
     * 责任店组名称
     */
    private String teamName;

    /**
     * 责任店组OrgCode
     */
    private String teamCode;

    /**
     * 责任门店名称
     */
    private String shopName;

    /**
     * 责任门店OrgCode
     */
    private String shopCode;

    /**
     * 大区名称
     */
    private String areaName;

    /**
     * 大区OrgCode
     */
    private String areaCode;

    /**
     * 管理大区名称
     */
    private String regionName;

    /**
     * 管理大区OrgCode
     */
    private String regionCode;

    /**
     * 数据类型
     */
    private String dataType;

    /**
     * 是否剔除
     */
    private Boolean isDrop;

    /**
     * 剔除原因
     */
    private String dropReason;

    /**
     * 是否非目标
     */
    private Boolean isNotTarget;

    /**
     * 非目标原因
     */
    private String notTargetReason;

    /**
     * 其他说明
     */
    private String otherComment;

    /**
     * 市占区域
     */
    private String marketingArea;

    /**
     * 链家占有率 链家/其它
     */
    private String isLianjia;

    /**
     * 是否注销
     */
    private Boolean isLogout;

    /**
     * 装修标准
     */
    private String decorationStandard;

    /**
     * 资金划转
     */
    private String moneyTransfer;

    /**
     * 房产类别
     */
    private String housePropertyType;

    /**
     * 实际成交单价
     */
    private BigDecimal transUnitPrice;

    /**
     * 链家佣金
     */
    private BigDecimal commission;

    /**
     * 真实成交价
     */
    private BigDecimal realPrice;

    /**
     * 审核状态 0-无状态 1-待审核 2-审核通过 3-驳回
     */
    private Integer auditStatus;

    /**
     * 申请人ucid
     */
    private Long creatorUcid;

    /**
     * 申请人姓名
     */
    private String creatorName;

    /**
     * 申请人手机号
     */
    private String creatorMobile;

    /**
     * 审核人ucid
     */
    private Long auditorUcid;

    /**
     * 审核人姓名
     */
    private String auditorName;

    /**
     * 审核人手机号
     */
    private String auditorMobile;

    /**
     * 审核意见
     */
    private String auditOpinion;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private Iterable<String> regionCodes;
    private Iterable<String> areaCodes;
    private Iterable<String> shopCodes;
    private Iterable<Integer> auditStatuses;
    
    private static final long serialVersionUID = 1L;

    /**
     * 获取主键
     *
     * @return id - 主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置主键
     *
     * @param id 主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取唯一表示，目前是sequence_number
     *
     * @return net_data_id - 唯一表示，目前是sequence_number
     */
    public Long getNetDataId() {
        return netDataId;
    }

    /**
     * 设置唯一表示，目前是sequence_number
     *
     * @param netDataId 唯一表示，目前是sequence_number
     */
    public void setNetDataId(Long netDataId) {
        this.netDataId = netDataId;
    }

    /**
     * 获取数据标识，代表第几次上传
     *
     * @return net_index - 数据标识，代表第几次上传
     */
    public Integer getNetIndex() {
        return netIndex;
    }

    /**
     * 设置数据标识，代表第几次上传
     *
     * @param netIndex 数据标识，代表第几次上传
     */
    public void setNetIndex(Integer netIndex) {
        this.netIndex = netIndex;
    }

    /**
     * 获取数据状态 1-月度 2-周度
     *
     * @return net_status - 数据状态 1-月度 2-周度
     */
    public Integer getNetType() {
        return netType;
    }

    /**
     * 设置数据状态 1-月度 2-周度
     *
     * @param netType 数据状态 1-月度 2-周度
     */
    public void setNetType(Integer netType) {
        this.netType = netType;
    }

    /**
     * 获取数据月份
     *
     * @return net_month - 数据月份
     */
    public String getNetMonth() {
        return netMonth;
    }

    /**
     * 设置数据月份
     *
     * @param netMonth 数据月份
     */
    public void setNetMonth(String netMonth) {
        this.netMonth = netMonth == null ? null : netMonth.trim();
    }

    /**
     * 获取数据来源序号
     *
     * @return sequence_number - 数据来源序号
     */
    public Long getSequenceNumber() {
        return sequenceNumber;
    }

    /**
     * 设置数据来源序号
     *
     * @param sequenceNumber 数据来源序号
     */
    public void setSequenceNumber(Long sequenceNumber) {
        this.sequenceNumber = sequenceNumber;
        this.netDataId = sequenceNumber;
    }

    /**
     * 获取网签房源编号
     *
     * @return net_house_code - 网签房源编号
     */
    public String getNetHouseCode() {
        return netHouseCode;
    }

    /**
     * 设置网签房源编号
     *
     * @param netHouseCode 网签房源编号
     */
    public void setNetHouseCode(String netHouseCode) {
        this.netHouseCode = netHouseCode == null ? null : netHouseCode.trim();
    }

    /**
     * 获取业务编号
     *
     * @return biz_no - 业务编号
     */
    public String getBizNo() {
        return bizNo;
    }

    /**
     * 设置业务编号
     *
     * @param bizNo 业务编号
     */
    public void setBizNo(String bizNo) {
        this.bizNo = bizNo == null ? null : bizNo.trim();
    }

    /**
     * 获取房屋id
     *
     * @return house_id - 房屋id
     */
    public Long getHouseId() {
        return houseId;
    }

    /**
     * 设置房屋id
     *
     * @param houseId 房屋id
     */
    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    /**
     * 获取匹配的楼盘字典楼幢id
     *
     * @return building_id - 匹配的楼盘字典楼幢id
     */
    public Long getBuildingId() {
        return buildingId;
    }

    /**
     * 设置匹配的楼盘字典楼幢id
     *
     * @param buildingId 匹配的楼盘字典楼幢id
     */
    public void setBuildingId(Long buildingId) {
        this.buildingId = buildingId;
    }

    /**
     * 获取匹配的楼盘字典楼盘id
     *
     * @return resblock_id - 匹配的楼盘字典楼盘id
     */
    public Long getResblockId() {
        return resblockId;
    }

    /**
     * 设置匹配的楼盘字典楼盘id
     *
     * @param resblockId 匹配的楼盘字典楼盘id
     */
    public void setResblockId(Long resblockId) {
        this.resblockId = resblockId;
    }

    /**
     * 获取房源委托id
     *
     * @return housedel_id - 房源委托id
     */
    public String getHousedelId() {
        return housedelId;
    }

    /**
     * 设置房源委托id
     *
     * @param housedelId 房源委托id
     */
    public void setHousedelId(String housedelId) {
        this.housedelId = housedelId;
    }

    /**
     * 获取区县名称
     *
     * @return district - 区县名称
     */
    public String getDistrict() {
        return district;
    }

    /**
     * 设置区县名称
     *
     * @param district 区县名称
     */
    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    /**
     * 获取街道名称
     *
     * @return street - 街道名称
     */
    public String getStreet() {
        return street;
    }

    /**
     * 设置街道名称
     *
     * @param street 街道名称
     */
    public void setStreet(String street) {
        this.street = street == null ? null : street.trim();
    }

    /**
     * 获取院门牌号
     *
     * @return yard_no - 院门牌号
     */
    public String getYardNo() {
        return yardNo;
    }

    /**
     * 设置院门牌号
     *
     * @param yardNo 院门牌号
     */
    public void setYardNo(String yardNo) {
        this.yardNo = yardNo == null ? null : yardNo.trim();
    }

    /**
     * 获取楼栋号
     *
     * @return building_no - 楼栋号
     */
    public String getBuildingNo() {
        return buildingNo;
    }

    /**
     * 设置楼栋号
     *
     * @param buildingNo 楼栋号
     */
    public void setBuildingNo(String buildingNo) {
        this.buildingNo = buildingNo == null ? null : buildingNo.trim();
    }

    /**
     * 获取房间号
     *
     * @return house_no - 房间号
     */
    public String getHouseNo() {
        return houseNo;
    }

    /**
     * 设置房间号
     *
     * @param houseNo 房间号
     */
    public void setHouseNo(String houseNo) {
        this.houseNo = houseNo == null ? null : houseNo.trim();
    }

    /**
     * 获取所在层数
     *
     * @return floor - 所在层数
     */
    public String getFloor() {
        return floor;
    }

    /**
     * 设置所在层数
     *
     * @param floor 所在层数
     */
    public void setFloor(String floor) {
        this.floor = floor == null ? null : floor.trim();
    }

    /**
     * 获取总楼层
     *
     * @return total_floors - 总楼层
     */
    public String getTotalFloors() {
        return totalFloors;
    }

    /**
     * 设置总楼层
     *
     * @param totalFloors 总楼层
     */
    public void setTotalFloors(String totalFloors) {
        this.totalFloors = totalFloors == null ? null : totalFloors.trim();
    }

    /**
     * 获取签约机构
     *
     * @return sign_company - 签约机构
     */
    public String getSignCompany() {
        return signCompany;
    }

    /**
     * 设置签约机构
     *
     * @param signCompany 签约机构
     */
    public void setSignCompany(String signCompany) {
        this.signCompany = signCompany == null ? null : signCompany.trim();
    }

    /**
     * 获取签约公司简称
     *
     * @return sign_company_alias - 签约公司简称
     */
    public String getSignCompanyAlias() {
        return signCompanyAlias;
    }

    /**
     * 设置签约公司简称
     *
     * @param signCompanyAlias 签约公司简称
     */
    public void setSignCompanyAlias(String signCompanyAlias) {
        this.signCompanyAlias = signCompanyAlias == null ? null : signCompanyAlias.trim();
    }

    /**
     * 获取签约时间
     *
     * @return sign_time - 签约时间
     */
    public Date getSignTime() {
        return signTime;
    }

    /**
     * 设置签约时间
     *
     * @param signTime 签约时间
     */
    public void setSignTime(Date signTime) {
        this.signTime = signTime;
    }

    /**
     * 获取房屋所有权证号
     *
     * @return house_ownership_no - 房屋所有权证号
     */
    public String getHouseOwnershipNo() {
        return houseOwnershipNo;
    }

    /**
     * 设置房屋所有权证号
     *
     * @param houseOwnershipNo 房屋所有权证号
     */
    public void setHouseOwnershipNo(String houseOwnershipNo) {
        this.houseOwnershipNo = houseOwnershipNo == null ? null : houseOwnershipNo.trim();
    }

    /**
     * 获取合同编号
     *
     * @return contract_no - 合同编号
     */
    public String getContractNo() {
        return contractNo;
    }

    /**
     * 设置合同编号
     *
     * @param contractNo 合同编号
     */
    public void setContractNo(String contractNo) {
        this.contractNo = contractNo == null ? null : contractNo.trim();
    }

    /**
     * 获取合同总价
     *
     * @return contract_total_price - 合同总价
     */
    public BigDecimal getContractTotalPrice() {
        return contractTotalPrice;
    }

    /**
     * 设置合同总价
     *
     * @param contractTotalPrice 合同总价
     */
    public void setContractTotalPrice(BigDecimal contractTotalPrice) {
        this.contractTotalPrice = contractTotalPrice;
    }

    /**
     * 获取房屋总价
     *
     * @return house_total_price - 房屋总价
     */
    public BigDecimal getHouseTotalPrice() {
        return houseTotalPrice;
    }

    /**
     * 设置房屋总价
     *
     * @param houseTotalPrice 房屋总价
     */
    public void setHouseTotalPrice(BigDecimal houseTotalPrice) {
        this.houseTotalPrice = houseTotalPrice;
    }

    /**
     * 获取房屋单价
     *
     * @return house_unit_price - 房屋单价
     */
    public BigDecimal getHouseUnitPrice() {
        return houseUnitPrice;
    }

    /**
     * 设置房屋单价
     *
     * @param houseUnitPrice 房屋单价
     */
    public void setHouseUnitPrice(BigDecimal houseUnitPrice) {
        this.houseUnitPrice = houseUnitPrice;
    }

    /**
     * 获取是否贷款
     *
     * @return is_loan - 是否贷款
     */
    public String getIsLoan() {
        return isLoan;
    }

    /**
     * 设置是否贷款
     *
     * @param isLoan 是否贷款
     */
    public void setIsLoan(String isLoan) {
        this.isLoan = isLoan == null ? null : isLoan.trim();
    }

    /**
     * 获取房屋性质
     *
     * @return house_property - 房屋性质
     */
    public String getHouseProperty() {
        return houseProperty;
    }

    /**
     * 设置房屋性质
     *
     * @param houseProperty 房屋性质
     */
    public void setHouseProperty(String houseProperty) {
        this.houseProperty = houseProperty == null ? null : houseProperty.trim();
    }

    /**
     * 获取房屋类型（楼房平房）
     *
     * @return house_type - 房屋类型（楼房平房）
     */
    public String getHouseType() {
        return houseType;
    }

    /**
     * 设置房屋类型（楼房平房）
     *
     * @param houseType 房屋类型（楼房平房）
     */
    public void setHouseType(String houseType) {
        this.houseType = houseType == null ? null : houseType.trim();
    }

    /**
     * 获取建筑年代
     *
     * @return build_year - 建筑年代
     */
    public String getBuildYear() {
        return buildYear;
    }

    /**
     * 设置建筑年代
     *
     * @param buildYear 建筑年代
     */
    public void setBuildYear(String buildYear) {
        this.buildYear = buildYear == null ? null : buildYear.trim();
    }

    /**
     * 获取规划用途
     *
     * @return planning_purpose - 规划用途
     */
    public String getPlanningPurpose() {
        return planningPurpose;
    }

    /**
     * 设置规划用途
     *
     * @param planningPurpose 规划用途
     */
    public void setPlanningPurpose(String planningPurpose) {
        this.planningPurpose = planningPurpose == null ? null : planningPurpose.trim();
    }

    /**
     * 获取卖方性质
     *
     * @return seller_property - 卖方性质
     */
    public String getSellerProperty() {
        return sellerProperty;
    }

    /**
     * 设置卖方性质
     *
     * @param sellerProperty 卖方性质
     */
    public void setSellerProperty(String sellerProperty) {
        this.sellerProperty = sellerProperty == null ? null : sellerProperty.trim();
    }

    /**
     * 获取买方性质
     *
     * @return purchaser_property - 买方性质
     */
    public String getPurchaserProperty() {
        return purchaserProperty;
    }

    /**
     * 设置买方性质
     *
     * @param purchaserProperty 买方性质
     */
    public void setPurchaserProperty(String purchaserProperty) {
        this.purchaserProperty = purchaserProperty == null ? null : purchaserProperty.trim();
    }

    /**
     * 获取建筑面积
     *
     * @return build_area - 建筑面积
     */
    public BigDecimal getBuildArea() {
        return buildArea;
    }

    /**
     * 设置建筑面积
     *
     * @param buildArea 建筑面积
     */
    public void setBuildArea(BigDecimal buildArea) {
        this.buildArea = buildArea;
    }

    /**
     * 获取面积档位
     *
     * @return build_area_region - 面积档位
     */
    public String getBuildAreaRegion() {
        return buildAreaRegion;
    }

    /**
     * 设置面积档位
     *
     * @param buildAreaRegion 面积档位
     */
    public void setBuildAreaRegion(String buildAreaRegion) {
        this.buildAreaRegion = buildAreaRegion == null ? null : buildAreaRegion.trim();
    }

    /**
     * 获取户型
     *
     * @return layout - 户型
     */
    public String getLayout() {
        return layout;
    }

    /**
     * 设置户型
     *
     * @param layout 户型
     */
    public void setLayout(String layout) {
        this.layout = layout == null ? null : layout.trim();
    }

    /**
     * 获取详细物业地址
     *
     * @return detailed_address - 详细物业地址
     */
    public String getDetailedAddress() {
        return detailedAddress;
    }

    /**
     * 设置详细物业地址
     *
     * @param detailedAddress 详细物业地址
     */
    public void setDetailedAddress(String detailedAddress) {
        this.detailedAddress = detailedAddress == null ? null : detailedAddress.trim();
    }

    /**
     * 获取楼盘名
     *
     * @return resblock_name - 楼盘名
     */
    public String getResblockName() {
        return resblockName;
    }

    /**
     * 设置楼盘名
     *
     * @param resblockName 楼盘名
     */
    public void setResblockName(String resblockName) {
        this.resblockName = resblockName == null ? null : resblockName.trim();
    }

    /**
     * 获取责任店组名称
     *
     * @return team_name - 责任店组名称
     */
    public String getTeamName() {
        return teamName;
    }

    /**
     * 设置责任店组名称
     *
     * @param teamName 责任店组名称
     */
    public void setTeamName(String teamName) {
        this.teamName = teamName == null ? null : teamName.trim();
    }

    /**
     * 获取责任店组OrgCode
     *
     * @return team_code - 责任店组OrgCode
     */
    public String getTeamCode() {
        return teamCode;
    }

    /**
     * 设置责任店组OrgCode
     *
     * @param teamCode 责任店组OrgCode
     */
    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode == null ? null : teamCode.trim();
    }

    /**
     * 获取责任门店名称
     *
     * @return shop_name - 责任门店名称
     */
    public String getShopName() {
        return shopName;
    }

    /**
     * 设置责任门店名称
     *
     * @param shopName 责任门店名称
     */
    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    /**
     * 获取责任门店OrgCode
     *
     * @return shop_code - 责任门店OrgCode
     */
    public String getShopCode() {
        return shopCode;
    }

    /**
     * 设置责任门店OrgCode
     *
     * @param shopCode 责任门店OrgCode
     */
    public void setShopCode(String shopCode) {
        this.shopCode = shopCode == null ? null : shopCode.trim();
    }

    /**
     * 获取大区名称
     *
     * @return area_name - 大区名称
     */
    public String getAreaName() {
        return areaName;
    }

    /**
     * 设置大区名称
     *
     * @param areaName 大区名称
     */
    public void setAreaName(String areaName) {
        this.areaName = areaName == null ? null : areaName.trim();
    }

    /**
     * 获取大区OrgCode
     *
     * @return area_code - 大区OrgCode
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * 设置大区OrgCode
     *
     * @param areaCode 大区OrgCode
     */
    public void setAreaCode(String areaCode) {
        this.areaCode = areaCode == null ? null : areaCode.trim();
    }

    /**
     * 获取管理大区名称
     *
     * @return region_name - 管理大区名称
     */
    public String getRegionName() {
        return regionName;
    }

    /**
     * 设置管理大区名称
     *
     * @param regionName 管理大区名称
     */
    public void setRegionName(String regionName) {
        this.regionName = regionName == null ? null : regionName.trim();
    }

    /**
     * 获取管理大区OrgCode
     *
     * @return region_code - 管理大区OrgCode
     */
    public String getRegionCode() {
        return regionCode;
    }

    /**
     * 设置管理大区OrgCode
     *
     * @param regionCode 管理大区OrgCode
     */
    public void setRegionCode(String regionCode) {
        this.regionCode = regionCode == null ? null : regionCode.trim();
    }

    /**
     * 获取数据类型
     *
     * @return data_type - 数据类型
     */
    public String getDataType() {
        return dataType;
    }

    /**
     * 设置数据类型
     *
     * @param dataType 数据类型
     */
    public void setDataType(String dataType) {
        this.dataType = dataType == null ? null : dataType.trim();
    }

    /**
     * 获取是否剔除
     *
     * @return is_drop - 是否剔除
     */
    public Boolean getIsDrop() {
        return isDrop;
    }

    /**
     * 设置是否剔除
     *
     * @param isDrop 是否剔除
     */
    public void setIsDrop(Boolean isDrop) {
        this.isDrop = isDrop;
    }

    /**
     * 获取剔除原因
     *
     * @return drop_reason - 剔除原因
     */
    public String getDropReason() {
        return dropReason;
    }

    /**
     * 设置剔除原因
     *
     * @param dropReason 剔除原因
     */
    public void setDropReason(String dropReason) {
        this.dropReason = dropReason == null ? null : dropReason.trim();
    }

    /**
     * 获取是否非目标
     *
     * @return is_not_target - 是否非目标
     */
    public Boolean getIsNotTarget() {
        return isNotTarget;
    }

    /**
     * 设置是否非目标
     *
     * @param isNotTarget 是否非目标
     */
    public void setIsNotTarget(Boolean isNotTarget) {
        this.isNotTarget = isNotTarget;
    }

    /**
     * 获取非目标原因
     *
     * @return not_target_reason - 非目标原因
     */
    public String getNotTargetReason() {
        return notTargetReason;
    }

    /**
     * 设置非目标原因
     *
     * @param notTargetReason 非目标原因
     */
    public void setNotTargetReason(String notTargetReason) {
        this.notTargetReason = notTargetReason == null ? null : notTargetReason.trim();
    }

    /**
     * 获取其他说明
     *
     * @return other_comment - 其他说明
     */
    public String getOtherComment() {
        return otherComment;
    }

    /**
     * 设置其他说明
     *
     * @param otherComment 其他说明
     */
    public void setOtherComment(String otherComment) {
        this.otherComment = otherComment == null ? null : otherComment.trim();
    }

    /**
     * 获取市占区域
     *
     * @return marketing_area - 市占区域
     */
    public String getMarketingArea() {
        return marketingArea;
    }

    /**
     * 设置市占区域
     *
     * @param marketingArea 市占区域
     */
    public void setMarketingArea(String marketingArea) {
        this.marketingArea = marketingArea == null ? null : marketingArea.trim();
    }

    /**
     * 获取链家占有率 链家/其它
     *
     * @return is_lianjia - 链家占有率 链家/其它
     */
    public String getIsLianjia() {
        return isLianjia;
    }

    /**
     * 设置链家占有率 链家/其它
     *
     * @param isLianjia 链家占有率 链家/其它
     */
    public void setIsLianjia(String isLianjia) {
        this.isLianjia = isLianjia == null ? null : isLianjia.trim();
    }

    /**
     * 获取是否注销
     *
     * @return is_logout - 是否注销
     */
    public Boolean getIsLogout() {
        return isLogout;
    }

    /**
     * 设置是否注销
     *
     * @param isLogout 是否注销
     */
    public void setIsLogout(Boolean isLogout) {
        this.isLogout = isLogout;
    }

    /**
     * 获取装修标准
     *
     * @return decoration_standard - 装修标准
     */
    public String getDecorationStandard() {
        return decorationStandard;
    }

    /**
     * 设置装修标准
     *
     * @param decorationStandard 装修标准
     */
    public void setDecorationStandard(String decorationStandard) {
        this.decorationStandard = decorationStandard == null ? null : decorationStandard.trim();
    }

    /**
     * 获取资金划转
     *
     * @return money_transfer - 资金划转
     */
    public String getMoneyTransfer() {
        return moneyTransfer;
    }

    /**
     * 设置资金划转
     *
     * @param moneyTransfer 资金划转
     */
    public void setMoneyTransfer(String moneyTransfer) {
        this.moneyTransfer = moneyTransfer == null ? null : moneyTransfer.trim();
    }

    /**
     * 获取房产类别
     *
     * @return house_property_type - 房产类别
     */
    public String getHousePropertyType() {
        return housePropertyType;
    }

    /**
     * 设置房产类别
     *
     * @param housePropertyType 房产类别
     */
    public void setHousePropertyType(String housePropertyType) {
        this.housePropertyType = housePropertyType == null ? null : housePropertyType.trim();
    }

    /**
     * 获取实际成交单价
     *
     * @return trans_unit_price - 实际成交单价
     */
    public BigDecimal getTransUnitPrice() {
        return transUnitPrice;
    }

    /**
     * 设置实际成交单价
     *
     * @param transUnitPrice 实际成交单价
     */
    public void setTransUnitPrice(BigDecimal transUnitPrice) {
        this.transUnitPrice = transUnitPrice;
    }

    /**
     * 获取链家佣金
     *
     * @return commission - 链家佣金
     */
    public BigDecimal getCommission() {
        return commission;
    }

    /**
     * 设置链家佣金
     *
     * @param commission 链家佣金
     */
    public void setCommission(BigDecimal commission) {
        this.commission = commission;
    }

    /**
     * 获取真实成交价
     *
     * @return real_price - 真实成交价
     */
    public BigDecimal getRealPrice() {
        return realPrice;
    }

    /**
     * 设置真实成交价
     *
     * @param realPrice 真实成交价
     */
    public void setRealPrice(BigDecimal realPrice) {
        this.realPrice = realPrice;
    }

    /**
     * 获取审核状态 0-无状态 1-待审核 2-审核通过 3-驳回
     *
     * @return audit_status - 审核状态 0-无状态 1-待审核 2-审核通过 3-驳回
     */
    public Integer getAuditStatus() {
        return auditStatus;
    }

    /**
     * 设置审核状态 0-无状态 1-待审核 2-审核通过 3-驳回
     *
     * @param auditStatus 审核状态 0-无状态 1-待审核 2-审核通过 3-驳回
     */
    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    /**
     * 获取申请人ucid
     *
     * @return creator_ucid - 申请人ucid
     */
    public Long getCreatorUcid() {
        return creatorUcid;
    }

    /**
     * 设置申请人ucid
     *
     * @param creatorUcid 申请人ucid
     */
    public void setCreatorUcid(Long creatorUcid) {
        this.creatorUcid = creatorUcid;
    }

    /**
     * 获取申请人姓名
     *
     * @return creator_name - 申请人姓名
     */
    public String getCreatorName() {
        return creatorName;
    }

    /**
     * 设置申请人姓名
     *
     * @param creatorName 申请人姓名
     */
    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName == null ? null : creatorName.trim();
    }

    /**
     * 获取申请人手机号
     *
     * @return creator_mobile - 申请人手机号
     */
    public String getCreatorMobile() {
        return creatorMobile;
    }

    /**
     * 设置申请人手机号
     *
     * @param creatorMobile 申请人手机号
     */
    public void setCreatorMobile(String creatorMobile) {
        this.creatorMobile = creatorMobile == null ? null : creatorMobile.trim();
    }

    /**
     * 获取审核人ucid
     *
     * @return auditor_ucid - 审核人ucid
     */
    public Long getAuditorUcid() {
        return auditorUcid;
    }

    /**
     * 设置审核人ucid
     *
     * @param auditorUcid 审核人ucid
     */
    public void setAuditorUcid(Long auditorUcid) {
        this.auditorUcid = auditorUcid;
    }

    /**
     * 获取审核人姓名
     *
     * @return auditor_name - 审核人姓名
     */
    public String getAuditorName() {
        return auditorName;
    }

    /**
     * 设置审核人姓名
     *
     * @param auditorName 审核人姓名
     */
    public void setAuditorName(String auditorName) {
        this.auditorName = auditorName == null ? null : auditorName.trim();
    }

    /**
     * 获取审核人手机号
     *
     * @return auditor_mobile - 审核人手机号
     */
    public String getAuditorMobile() {
        return auditorMobile;
    }

    /**
     * 设置审核人手机号
     *
     * @param auditorMobile 审核人手机号
     */
    public void setAuditorMobile(String auditorMobile) {
        this.auditorMobile = auditorMobile == null ? null : auditorMobile.trim();
    }

    /**
     * 获取审核意见
     *
     * @return audit_opinion - 审核意见
     */
    public String getAuditOpinion() {
        return auditOpinion;
    }

    /**
     * 设置审核意见
     *
     * @param auditOpinion 审核意见
     */
    public void setAuditOpinion(String auditOpinion) {
        this.auditOpinion = auditOpinion == null ? null : auditOpinion.trim();
    }

    /**
     * 获取创建时间
     *
     * @return create_time - 创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置创建时间
     *
     * @param createTime 创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取更新时间
     *
     * @return update_time - 更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置更新时间
     *
     * @param updateTime 更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Iterable<String> getRegionCodes() {
		return regionCodes;
	}

	public void setRegionCodes(Iterable<String> regionCodes) {
		this.regionCodes = regionCodes;
	}

	public Iterable<String> getAreaCodes() {
		return areaCodes;
	}

	public void setAreaCodes(Iterable<String> areaCodes) {
		this.areaCodes = areaCodes;
	}

	public Iterable<String> getShopCodes() {
		return shopCodes;
	}

	public void setShopCodes(Iterable<String> shopCodes) {
		this.shopCodes = shopCodes;
	}

	public Iterable<Integer> getAuditStatuses() {
		return auditStatuses;
	}

	public void setAuditStatuses(Iterable<Integer> auditStatuses) {
		this.auditStatuses = auditStatuses;
	}
	
    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this).omitNullValues()
        .add("id", id)
        .add("netDataId", netDataId)
        .add("netIndex", netIndex)
        .add("netType", netType)
        .add("netMonth", netMonth)
        .add("sequenceNumber", sequenceNumber)
        .add("netHouseCode", netHouseCode)
        .add("bizNo", bizNo)
        .add("houseId", houseId)
        .add("buildingId", buildingId)
        .add("resblockId", resblockId)
        .add("housedelId", housedelId)
        .add("district", district)
        .add("street", street)
        .add("yardNo", yardNo)
        .add("buildingNo", buildingNo)
        .add("houseNo", houseNo)
        .add("floor", floor)
        .add("totalFloors", totalFloors)
        .add("signCompany", signCompany)
        .add("signCompanyAlias", signCompanyAlias)
        .add("signTime", signTime)
        .add("houseOwnershipNo", houseOwnershipNo)
        .add("contractNo", contractNo)
        .add("contractTotalPrice", contractTotalPrice)
        .add("houseTotalPrice", houseTotalPrice)
        .add("houseUnitPrice", houseUnitPrice)
        .add("isLoan", isLoan)
        .add("houseProperty", houseProperty)
        .add("houseType", houseType)
        .add("buildYear", buildYear)
        .add("planningPurpose", planningPurpose)
        .add("sellerProperty", sellerProperty)
        .add("purchaserProperty", purchaserProperty)
        .add("buildArea", buildArea)
        .add("buildAreaRegion", buildAreaRegion)
        .add("layout", layout)
        .add("detailedAddress", detailedAddress)
        .add("resblockName", resblockName)
        .add("teamName", teamName)
        .add("teamCode", teamCode)
        .add("shopName", shopName)
        .add("shopCode", shopCode)
        .add("areaName", areaName)
        .add("areaCode", areaCode)
        .add("regionName", regionName)
        .add("regionCode", regionCode)
        .add("dataType", dataType)
        .add("isDrop", isDrop)
        .add("dropReason", dropReason)
        .add("isNotTarget", isNotTarget)
        .add("notTargetReason", notTargetReason)
        .add("otherComment", otherComment)
        .add("marketingArea", marketingArea)
        .add("isLianjia", isLianjia)
        .add("isLogout", isLogout)
        .add("decorationStandard", decorationStandard)
        .add("moneyTransfer", moneyTransfer)
        .add("housePropertyType", housePropertyType)
        .add("transUnitPrice", transUnitPrice)
        .add("commission", commission)
        .add("realPrice", realPrice)
        .add("auditStatus", auditStatus)
        .add("creatorUcid", creatorUcid)
        .add("creatorName", creatorName)
        .add("creatorMobile", creatorMobile)
        .add("auditorUcid", auditorUcid)
        .add("auditorName", auditorName)
        .add("auditorMobile", auditorMobile)
        .add("auditOpinion", auditOpinion)
        .add("createTime", createTime)
        .add("updateTime", updateTime)
        .toString();
    }
}