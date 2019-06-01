package com.lsz.jys.common;

import java.util.List;

public class BasePage<T> {
    private Long total;
    private List<T> content;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getContent() {
        return content;
    }

    public void setContent(List<T> content) {
        this.content = content;
    }
}
