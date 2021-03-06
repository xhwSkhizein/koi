package com.hongv.koi.word.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Date;

/**
 * create by xuhongwei5 at 2018/10/11 15:05
 *
 * @author xuhongwei5
 */
public class Word {

    private final long id;
    private final String word;
    private final Date createTime;

    public Word(long id, String word, Date createTime) {
        this.id = id;
        this.word = word;
        this.createTime = createTime;
    }

    public long getId() {
        return id;
    }

    public String getWord() {
        return word;
    }

    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
