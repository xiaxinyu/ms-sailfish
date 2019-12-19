package com.sailfish.domain.page;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 分页DTO
 *
 * @author XIAXINYU3
 * @date 2019.12.19
 */
@Data
@AllArgsConstructor
public class PageDTO {
    public PageDTO() {
    }

    public PageDTO(Integer pageIndex, Integer pageSize) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public PageDTO(Integer pageIndex, Integer pageSize, Integer total) {
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
        this.total = total;
    }

    private Integer pageIndex;
    private Integer pageSize;
    private Integer start;
    private Integer total;
    private Integer totalPage;

    public Integer getStart() {
        return (pageIndex <= 1 ? 0 : (pageIndex - 1)) * pageSize;
    }

    public Integer getTotalPage() {
        int mod = total % pageSize;
        int pages = total / pageSize;
        int totalPage = (mod == 0 ? pages : pages + 1);
        return (totalPage == 0 ? 1 : totalPage);
    }
}
