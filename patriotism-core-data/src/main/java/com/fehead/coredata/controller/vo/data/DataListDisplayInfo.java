package com.fehead.coredata.controller.vo.data;

import java.util.List;

/**
 * @author lmwis
 * @description:
 * @date 2019-08-16 17:44
 * @Version 1.0
 */
public class DataListDisplayInfo{

    private int page;

    private List<DataDisplayInfo> data_lists;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }



    public List<DataDisplayInfo> getData_lists() {
        return data_lists;
    }

    public void setData_lists(List<DataDisplayInfo> data_lists) {
        this.data_lists = data_lists;
    }
}
