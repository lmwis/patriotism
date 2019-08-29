package com.fehead.coredata.service.impl;

import com.fehead.coredata.controller.vo.TagDisplay;
import com.fehead.coredata.dao.dataobject.Tag;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lmwis
 * @description:
 * @date 2019-08-16 17:28
 * @Version 1.0
 */
public class BaseDataService {

    /**
     * 将数据库Tag对象转换为表现层展示对象
     * @param tag
     * @return
     */
    protected List<TagDisplay> convertFromTag(List<Tag> tag){
        if (tag==null||tag.size()==0) return null;

        List<TagDisplay> tags = new ArrayList<>();

        for (Tag t : tag) {
            TagDisplay tagDisplay = new TagDisplay();
            tagDisplay.setTag_code(t.getCode());
            tagDisplay.setTag_str(t.getStr());

            tags.add(tagDisplay);
        }
        return tags;

    }
}
