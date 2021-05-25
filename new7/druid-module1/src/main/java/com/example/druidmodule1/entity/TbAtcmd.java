package com.example.druidmodule1.entity;

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
@TableName("tb_atcmd")
public class TbAtcmd extends Model<TbAtcmd> {

    private static final long serialVersionUID = 1L;

    private Integer Id;
    private String flag;
    private String cmd;
    private Integer acttimer;
    private String filter;
    private String display;
    private String pass;
    private String failed;
    private String end;
    private String function;


    public Integer getId() {
        return Id;
    }

    public void setId(Integer Id) {
        this.Id = Id;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public Integer getActtimer() {
        return acttimer;
    }

    public void setActtimer(Integer acttimer) {
        this.acttimer = acttimer;
    }

    public String getFilter() {
        return filter;
    }

    public void setFilter(String filter) {
        this.filter = filter;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getFailed() {
        return failed;
    }

    public void setFailed(String failed) {
        this.failed = failed;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }

    @Override
    protected Serializable pkVal() {
        return this.Id;
    }

    @Override
    public String toString() {
        return "TbAtcmd{" +
        " Id=" + Id +
        ", flag=" + flag +
        ", cmd=" + cmd +
        ", acttimer=" + acttimer +
        ", filter=" + filter +
        ", display=" + display +
        ", pass=" + pass +
        ", failed=" + failed +
        ", end=" + end +
        ", function=" + function +
        "}";
    }
}
