package com.gch.druidmodule2.entity;

import com.baomidou.mybatisplus.annotations.TableField;
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
 * @since 2021-05-24
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_order")
public class TbOrderlist extends Model<TbOrderlist> {

    private static final long serialVersionUID = 1L;

    private Integer Id;
    private String name;
    private String project;
    private String product;
    private Integer quantity;
    private String information;
    @TableField("sn_prefix")
    private String snPrefix;
    @TableField("sn_length")
    private Integer snLength;


    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getSnPrefix() {
        return snPrefix;
    }

    public void setSnPrefix(String snPrefix) {
        this.snPrefix = snPrefix;
    }

    public Integer getSnLength() {
        return snLength;
    }

    public void setSnLength(Integer snLength) {
        this.snLength = snLength;
    }

    @Override
    protected Serializable pkVal() {
        return this.Id;
    }

    @Override
    public String toString() {
        return "TbOrderlist{" +
        ", Id=" + Id +
        ", name=" + name +
        ", project=" + project +
        ", product=" + product +
        ", quantity=" + quantity +
        ", information=" + information +
        ", snPrefix=" + snPrefix +
        ", snLength=" + snLength +
        "}";
    }
}
