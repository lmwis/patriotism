package com.fehead.service;

import com.fehead.controller.vo.data.DataDisplayInfo;
import com.fehead.controller.vo.data.DataListDisplayInfo;
import com.fehead.controller.vo.data.DataTypeInfo;
import com.fehead.error.BusinessException;
import org.springframework.data.domain.Pageable;

import java.util.List;

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
