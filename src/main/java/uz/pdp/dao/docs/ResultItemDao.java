package uz.pdp.dao.docs;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import uz.pdp.dao.BaseDao;
import uz.pdp.domains.docs.ResultItem;

import java.util.List;

public class ResultItemDao implements BaseDao<ResultItem> {

    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<ResultItem> rowMapper = BeanPropertyRowMapper.newInstance(ResultItem.class);

    public ResultItemDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(ResultItem resultItem) {
        var sql = "insert into resultitem (analysisdocumentid, name, result) values (?, ?, ?);";
        jdbcTemplate.update(sql, resultItem.getAnalysisdocumentid(), resultItem.getName(), resultItem.getResult());
    }

    @Override
    public void update(ResultItem resultItem) {
        var sql = "update resultitem set analysisdocumentid = ?, name = ?, result = ? where resultitemid = ?;";
        jdbcTemplate.update(sql,
                resultItem.getAnalysisdocumentid(),
                resultItem.getName(),
                resultItem.getResult(),
                resultItem.getResultitemid());
    }

    @Override
    public void delete(int id) {
        var sql = "delete from resultitem where resultitemid = ?;";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public ResultItem get(int id) {
        var sql = "select * from resultitem where resultitemid = ?;";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<ResultItem> getAll() {
        var sql = "select * from resultitem where resultitemid = ?;";
        return jdbcTemplate.query(sql, rowMapper);
    }
}