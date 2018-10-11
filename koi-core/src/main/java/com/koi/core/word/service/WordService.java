package com.koi.core.word.service;


import com.koi.core.word.domain.Word;

import java.util.List;

/**
 * @author xuhongwei5
 * @since 2018/10/11 19:23
 */
public interface WordService {
    /**
     * 保存
     */
    void save(String word);

    List<Word> findAll();

    /**
     * 分页查
     */
//    List<Word> findByCursor(Long offsetId, int limit);
}
