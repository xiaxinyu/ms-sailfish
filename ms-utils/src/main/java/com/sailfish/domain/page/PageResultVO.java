package com.sailfish.domain.page;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * 通用分页信息返回VO
 *
 * @param <T>
 * @author XIAXINYU3
 * @date 2019.12.18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "PageResultVO", description = "通用分页信息返回VO")
public class PageResultVO<T> {

    @ApiModelProperty(value = "当前页条数", name = "pageSize")
    private Integer pageSize;

    @ApiModelProperty(value = "当前页码下标值", name = "currentPage")
    private Integer pageNum;

    @ApiModelProperty(value = "总记录数", name = "totalSize")
    private Integer totalSize;

    @ApiModelProperty(value = "总页码", name = "totalPage")
    private Integer totalPage;

    @ApiModelProperty(value = "data", name = "结果集")
    private List<T> data;
}
