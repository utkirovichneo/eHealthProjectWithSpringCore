package uz.pdp.dao.auth;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import uz.pdp.dao.BaseDao;
import uz.pdp.domains.auth.AuthUserEmployee;

import java.util.List;

public class AuthUserEmployeeDao implements BaseDao<AuthUserEmployee> {

    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<AuthUserEmployee> rowMapper = BeanPropertyRowMapper.newInstance(AuthUserEmployee.class);

    public AuthUserEmployeeDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(AuthUserEmployee authUserEmployee) {
        var sql = "insert into authuseremployee (authuserid) values (?);";
        jdbcTemplate.update(sql, authUserEmployee.getAuthuserid());
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
