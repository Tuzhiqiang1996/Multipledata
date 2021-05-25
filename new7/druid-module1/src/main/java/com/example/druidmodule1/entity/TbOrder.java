package com.example.druidmodule1.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.enums.IdType;
import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author Tu
 * @since 2021-05-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_order")
public class TbOrder extends Model<TbOrder> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;
    /**
     * 订单号
     */
    @TableField("order_number")
    private String orderNumber;
    @TableField("project_name")
    private String projectName;
    /**
     * 产品类型/名称
     */
    @TableField("product_name")
    private String productName;
    /**
     * 内部软件版本号
     */
    @TableField("sw_version")
    private String swVersion;
    /**
     * 硬件版本号
     */
    @TableField("hw_version")
    private String hwVersion;
    /**
     * 在APP端的软件版本号
     */
    @TableField("user_sw_version")
    private String userSwVersion;
    @TableField("order_quantity")
    private Integer orderQuantity;
    /**
     * 易微联，涂鸦，华为，腾讯等
     */
    @TableField("cloud_platform")
    private String cloudPlatform;
    private String info;
    private String bak;
    @TableField("at_table")
    private String atTable;


    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getSwVersion() {
        return swVersion;
    }

    public void setSwVersion(String swVersion) {
        this.swVersion = swVersion;
    }

    public String getHwVersion() {
        return hwVersion;
    }

    public void setHwVersion(String hwVersion) {
        this.hwVersion = hwVersion;
    }

    public String getUserSwVersion() {
        return userSwVersion;
    }

    public void setUserSwVersion(String userSwVersion) {
        this.userSwVersion = userSwVersion;
    }

    public Integer getOrderQuantity() {
        return orderQuantity;
    }

    public void setOrderQuantity(Integer orderQuantity) {
        this.orderQuantity = orderQuantity;
    }

    public String getCloudPlatform() {
        return cloudPlatform;
    }

    public void setCloudPlatform(String cloudPlatform) {
        this.cloudPlatform = cloudPlatform;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getBak() {
        return bak;
    }

    public void setBak(String bak) {
        this.bak = bak;
    }

    public String getAtTable() {
        return atTable;
    }

    public void setAtTable(String atTable) {
        this.atTable = atTable;
    }

    @Override
    protected Serializable pkVal() {
        return this.Id;
    }

    @Override
    public String toString() {
        return "TbOrder{" +
        "Id=" + Id +
        ", orderNumber=" + orderNumber +
        ", projectName=" + projectName +
        ", productName=" + productName +
        ", swVersion=" + swVersion +
        ", hwVersion=" + hwVersion +
        ", userSwVersion=" + userSwVersion +
        ", orderQuantity=" + orderQuantity +
        ", cloudPlatform=" + cloudPlatform +
        ", info=" + info +
        ", bak=" + bak +
        ", atTable=" + atTable +
        "}";
    }
}
