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
@TableName("tb_device_list_xiaojiang")
public class TbDeviceListXiaojiang extends Model<TbDeviceListXiaojiang> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "Id", type = IdType.AUTO)
    private Integer Id;
    /**
     * 序列号
     */
    private String sn;
    @TableField("order_id")
    private Integer orderId;
    /**
     * 设备ID（若存在）
     */
    private String deviceid;
    private String addr1;
    private String addr2;
    private String createTime;
    /**
     * -1,失败；0，未测试；1，测试通过
     */
    @TableField("test_result")
    private Integer testResult;
    @TableField("test_datetime")
    private String testDatetime;
    @TableField("package_datetime")
    private String packageDatetime;
    /**
     * -1 失败；0，未测试，1,测试通过
     */
    @TableField("tb_device_list_xiaojiang.package")
    private Integer packages;
    @TableField("check_datetime")
    private String checkDatetime;
    /**
     * 检查次数
     */
    @TableField("check_count")
    private Integer checkCount;


    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getDeviceid() {
        return deviceid;
    }

    public void setDeviceid(String deviceid) {
        this.deviceid = deviceid;
    }

    public String getAddr1() {
        return addr1;
    }

    public void setAddr1(String addr1) {
        this.addr1 = addr1;
    }

    public String getAddr2() {
        return addr2;
    }

    public void setAddr2(String addr2) {
        this.addr2 = addr2;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getTestResult() {
        return testResult;
    }

    public void setTestResult(Integer testResult) {
        this.testResult = testResult;
    }

    public String getTestDatetime() {
        return testDatetime;
    }

    public void setTestDatetime(String testDatetime) {
        this.testDatetime = testDatetime;
    }

    public String getPackageDatetime() {
        return packageDatetime;
    }

    public void setPackageDatetime(String packageDatetime) {
        this.packageDatetime = packageDatetime;
    }

    public Integer getPackage() {
        return packages;
    }

    public void setPackage(Integer packages) {
        this.packages = packages;
    }

    public String getCheckDatetime() {
        return checkDatetime;
    }

    public void setCheckDatetime(String checkDatetime) {
        this.checkDatetime = checkDatetime;
    }

    public Integer getCheckCount() {
        return checkCount;
    }

    public void setCheckCount(Integer checkCount) {
        this.checkCount = checkCount;
    }

    @Override
    protected Serializable pkVal() {
        return this.Id;
    }

    @Override
    public String toString() {
        return "TbDeviceListXiaojiang{" +
        " Id=" + Id +
        ", sn=" + sn +
        ", orderId=" + orderId +
        ", deviceid=" + deviceid +
        ", addr1=" + addr1 +
        ", addr2=" + addr2 +
        ", createTime=" + createTime +
        ", testResult=" + testResult +
        ", testDatetime=" + testDatetime +
        ", packageDatetime=" + packageDatetime +
        ", package=" + packages +
        ", checkDatetime=" + checkDatetime +
        ", checkCount=" + checkCount +
        "}";
    }
}
