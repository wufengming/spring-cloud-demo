package com.wfm.servicesystem.model.base;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;
import org.apache.commons.collections.CollectionUtils;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author wfm
 * @author jyh
 */
@Data
@Accessors(chain = true)
public class PageModel extends ParamBaseModel {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "页号")
    private Long pageIndex = 0L;

    @ApiModelProperty(value = "页面大小")
    private Long pageSize = 10L;

    @ApiModelProperty(value = "排序字段")
    private List<OrderItem> orders;

    @ApiModelProperty(value = "queryfilter的格式:查询参数")
    private String queryfilter;

    public void defaultOrder(OrderItem orderItem){
        this.defaultOrders(Arrays.asList(orderItem));
    }

    public void defaultOrders(List<OrderItem> orderItems){
        if(CollectionUtils.isEmpty(orderItems)){
            return;
        }
        this.orders=orderItems;
    }
}
