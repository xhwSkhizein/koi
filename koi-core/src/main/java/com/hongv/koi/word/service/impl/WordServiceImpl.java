package com.hongv.koi.word.service.impl;

import com.hongv.koi.word.dao.WordDAO;
import com.hongv.koi.word.service.WordService;
import com.hongv.koi.word.domain.Word;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author xuhongwei5
 * @since 2018/10/11 19:35
 */
@Service("wordService")
public class WordServiceImpl implements WordService {

    private final WordDAO wordDAO;

    @Autowired
    public WordServiceImpl(WordDAO wordDAO) {
        this.wordDAO = wordDAO;
    }

    @Override
    public void save(String word) {
        if (StringUtils.isBlank(word)) {
            throw new IllegalArgumentException("word should not be blank!");
        }
        wordDAO.save(word);
    }

    @Override
    public List<Word> findAll() {

        return wordDAO.findAll();
    }

//    @Override
//    public List<Word> findByCursor(Long offsetId, int limit) {
//
//        return wordDAO.getByCursor(offsetId, limit);
//    }
}
