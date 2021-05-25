package com.gch.druidmodule2.common.Page;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tu
 * @Package com.example.common.Page
 * @date 2021/5/8-13:13
 */

/**
 * 封装分页对象
 **/
public class PageList {
    //当前页
    private int page;
    //总行数total
    private int total;
    //总页数
    private int pages;
    //单页总数
    private int size;
    //数据列表
    private List records = new ArrayList();

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public int getPagesize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List getList() {
        if (records == null) {
            records = new ArrayList();
        }
        return records;
    }

    public void setRecords(List records) {
        this.records = records;
    }

    public int getTotalRows() {
        return total;
    }

    public void setTotal (int total) {
        this.total = total;
    }
}
