package com.wfm.servicecommon.vo;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * description: Paging
 * date: 2019-10-31 16:11
 * author: wfm
 * version: 1.0
 */
@ApiModel("分页")
public class Paging<T> implements Serializable {
    private static final long serialVersionUID = -1683800405530086022L;

    @ApiModelProperty("总行数")
    @JsonProperty("total")
    private long total = 0;

    @ApiModelProperty("页号")
    @JsonProperty("pageIndex")
    private long pageIndex = 0;

    @ApiModelProperty("页面大小")
    @JsonProperty("pageSize")
    private long pageSize = 0;

    @ApiModelProperty("数据列表")
    @JsonProperty("records")
    private List<T> records = Collections.emptyList();

    public Paging() {

    }

    public Paging(IPage page) {
        this.total = page.getTotal();
        this.pageIndex = page.getCurrent();
        this.pageSize = page.getSize();
        this.records = page.getRecords();
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public long getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(long pageIndex) {
        this.pageIndex = pageIndex;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getRecords() {
        return records;
    }

    public void setRecords(List<T> records) {
        this.records = records;
    }

    @Override
    public String toString() {
        return "Paging{" +
                "total=" + total +
                ", records=" + records +
                '}';
    }
}
