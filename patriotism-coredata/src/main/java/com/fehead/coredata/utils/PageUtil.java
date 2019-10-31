package com.fehead.coredata.utils;

import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * 写代码 敲快乐
 * だからよ...止まるんじゃねぇぞ
 * ▏n
 * █▏　､⺍
 * █▏ ⺰ʷʷｨ
 * █◣▄██◣
 * ◥██████▋
 * 　◥████ █▎
 * 　　███▉ █▎
 * 　◢████◣⌠ₘ℩
 * 　　██◥█◣\≫
 * 　　██　◥█◣
 * 　　█▉　　█▊
 * 　　█▊　　█▊
 * 　　█▊　　█▋
 * 　　 █▏　　█▙
 * 　　 █
 *
 * @author Nightnessss 2019/9/2 19:36
 */
@Component
public class PageUtil {

    /**
     * 手动在内存中分页
     * @param tList
     * @param pageable
     * @return
     */
    public <T> List<T> convertToPage(List<T> tList, Pageable pageable){

        if(tList==null || tList.size()==0){
            return null;
        }

        List<T> result = new ArrayList<>();

        // 总页数
        int totalPage = tList.size()/pageable.getPageSize()+1;

        int filterCounter=(pageable.getPageNumber()-1)*pageable.getPageSize();
        int addCounter = pageable.getPageSize();


        for (T t : tList) {
            if(filterCounter--!=0 && filterCounter>=-1){// 数据过滤
                continue;
            }

            if(addCounter--==0 && addCounter>=-1){// 数据数目保障
                break;
            }
            result.add(t);
        }

        return result;

    }
}
