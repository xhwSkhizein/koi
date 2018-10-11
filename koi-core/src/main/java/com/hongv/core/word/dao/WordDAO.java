package com.hongv.core.word.dao;


import com.hongv.core.word.domain.Word;

import java.util.List;

/**
 * @author xuhongwei5
 * @since 2018/10/11 17:25
 */
public interface WordDAO {
    /**
     * 保存
     */
    int save(String word);

    /**
     * 根据游标获取
     */
    List<Word> getByCursor(Long offsetId, int limit);

    /**
     * 获取全部
     */
    List<Word> findAll();
}
