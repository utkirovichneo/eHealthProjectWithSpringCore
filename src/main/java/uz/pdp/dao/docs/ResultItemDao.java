package uz.pdp.dao.docs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import uz.pdp.config.jdbc.SpringJdbcConfig;
import uz.pdp.dao.BaseDao;
import uz.pdp.domains.docs.ResultItem;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Component
public class ResultItemDao implements BaseDao<ResultItem> {

    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<ResultItem> rowMapper = BeanPropertyRowMapper.newInstance(ResultItem.class);

    public ResultItemDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new AnnotationConfigApplicationContext(SpringJdbcConfig.class).getBean(JdbcTemplate.class);
    }

    @Override
    public int create(ResultItem resultItem) {
        var sql = "insert into resultitem (analysisdocumentid, name, result) values (?, ?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"resultitemid"});
            preparedStatement.setInt(1, resultItem.getAnalysisdocumentid());
            preparedStatement.setString(2, resultItem.getName());
            preparedStatement.setString(3, resultItem.getResult());
            return preparedStatement;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
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