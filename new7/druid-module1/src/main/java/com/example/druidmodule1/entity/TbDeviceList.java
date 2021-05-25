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
@TableName("tb_device_list")
public class TbDeviceList extends Model<TbDeviceList> {

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
    /**
     * -1 失败；0，未测试，1,测试通过
     */
    @TableField("check_result")
    private Integer checkResult;
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

    public Integer getCheckResult() {
        return checkResult;
    }

    public void setCheckResult(Integer checkResult) {
        this.checkResult = checkResult;
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
        return "TbDeviceList{" +
        " Id=" + Id +
        ", sn=" + sn +
        ", orderId=" + orderId +
        ", deviceid=" + deviceid +
        ", addr1=" + addr1 +
        ", addr2=" + addr2 +
        ", createTime=" + createTime +
        ", testResult=" + testResult +
        ", testDatetime=" + testDatetime +
        ", checkResult=" + checkResult +
        ", checkDatetime=" + checkDatetime +
        ", checkCount=" + checkCount +
        "}";
    }
}
