package uz.pdp.dao.auth;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import uz.pdp.config.jdbc.SpringJdbcConfig;
import uz.pdp.dao.BaseDao;
import uz.pdp.domains.auth.AuthUserEmployee;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Component
public class AuthUserEmployeeDao implements BaseDao<AuthUserEmployee> {

    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<AuthUserEmployee> rowMapper = BeanPropertyRowMapper.newInstance(AuthUserEmployee.class);

    public AuthUserEmployeeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new AnnotationConfigApplicationContext(SpringJdbcConfig.class).getBean(JdbcTemplate.class);
    }

    @Override
    public int create(AuthUserEmployee authUserEmployee) {
        var sql = "insert into authuseremployee (authuserid) values (?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"authuseremployeeid"});
            preparedStatement.setInt(1, authUserEmployee.getAuthuserid());
            return preparedStatement;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public void update(AuthUserEmployee authUserEmployee) {
        var sql = "update authuseremployee set authuserid = ? where authuseremployeeid = ?;";
        jdbcTemplate.update(sql, authUserEmployee.getAuthuserid(), authUserEmployee.getAuthuseremployeeid());
    }

    @Override
    public void delete(int id) {
        var sql = "delete from authuseremployee where authuseremployeeid = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public AuthUserEmployee get(int id) {
        var sql = "select * from authuseremployee where authuseremployeeid = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<AuthUserEmployee> getAll() {
        var sql = "select * from authuseremployee;";
        return jdbcTemplate.query(sql, rowMapper);
    }
}
