package com.koi.core.word.dao.impl;

import com.koi.core.word.dao.WordDAO;
import com.koi.core.word.domain.Word;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @author xuhongwei5
 * @since 2018/10/11 17:28
 */
@Repository
public class WordDAOImpl implements WordDAO, RowMapper<Word> {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    public WordDAOImpl(NamedParameterJdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Word mapRow(ResultSet resultSet, int i) throws SQLException {

        return new Word(resultSet.getLong("id"),
                resultSet.getString("word"),
                new Date(resultSet.getTimestamp("create_time").getTime()));
    }

    @Override
    public int save(String word) {
        String sql = "insert into word (word, create_time) values(:word, now())";

        return jdbcTemplate.update(sql, Collections.singletonMap("word", word));
    }

    @Override
    public List<Word> getByCursor(Long offsetId, int limit) {
        String sql = "select * from word order by id desc limit :limit";
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("limit", limit);
        if (offsetId != null) {
            sql = "select * from word where id <= :offsetId order by id desc limit :limit";
            params.addValue("offsetId", offsetId);
        }
        return jdbcTemplate.query(sql, params, this);
    }

    @Override
    public List<Word> findAll() {

        return jdbcTemplate.query("select * from word", this);
    }
}
