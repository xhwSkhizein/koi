package com.hongv.api.controller;

import com.hongv.core.word.domain.Word;
import com.hongv.core.word.service.WordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

/**
 * 收集单词
 *
 * @author xuhongwei5
 * create by xuhongwei5 at 2018/10/11 10:56
 */
@Api
@RestController
@RequestMapping(value = "/word/collect")
public class WordCollectController {
    private Logger logger = LoggerFactory.getLogger(getClass());

    private final WordService wordService;

    @Autowired
    public WordCollectController(WordService wordService) {
        this.wordService = wordService;
    }

    @ApiOperation(value = "单词列表", notes = "单词列表")
    @GetMapping("/all")
    public ResponseEntity all() {
        String wordList = wordService.findAll()
                .stream()
                .map(Word::getWord)
                .collect(Collectors.joining("\n"));

        return ResponseEntity.ok(wordList);
    }


    /**
     * // TODO record more information
     *
     * @param word word to save
     */
    @ApiOperation(value = "添加新单词")
    @PostMapping("/add")
    public ResponseEntity add(@RequestParam(value = "word") String word) {
        if (StringUtils.isBlank(word)) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        wordService.save(word);
        logger.info("save new word: " + word);

        return ResponseEntity.ok().build();
    }
}
