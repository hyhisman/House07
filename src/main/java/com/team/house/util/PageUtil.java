package com.team.house.util;

public class PageUtil {

    private Integer page;  //接收页码
    private Integer rows;  //页大小

    public PageUtil() {
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }
}

