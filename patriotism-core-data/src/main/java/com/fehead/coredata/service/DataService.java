package com.fehead.coredata.service;

import com.fehead.coredata.controller.vo.data.DataListDisplayInfo;
import com.fehead.coredata.controller.vo.data.DataTypeInfo;
import com.fehead.coredata.error.BusinessException;
import org.springframework.data.domain.Pageable;

/**
 * @author lmwis
 * @description:
 * @date 2019-08-16 16:45
 * @Version 1.0
 */
public interface DataService {

    public DataTypeInfo selectDataTypeInfoById(int dataId);

    DataListDisplayInfo selectDataListsPageable(Pageable pageable) throws BusinessException;
}
