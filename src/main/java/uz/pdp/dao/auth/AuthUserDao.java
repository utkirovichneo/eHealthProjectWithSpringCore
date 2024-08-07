package uz.pdp.dao.auth;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import uz.pdp.dao.BaseDao;
import uz.pdp.domains.auth.AuthUser;

import java.util.List;

public class AuthUserDao implements BaseDao<AuthUser> {

    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<AuthUser> rowMapper = BeanPropertyRowMapper.newInstance(AuthUser.class);

    public AuthUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(AuthUser authUser) {
        var sql = "insert into authuser (username, password, phonenumber) values (?, ?, ?);";
        jdbcTemplate.update(sql, authUser.getUsername(), authUser.getPassword(), authUser.getPhonenumber());
    }

    @Override
    public void update(AuthUser authUser) {
        var sql = "update authuser set username = ?, password = ?, phonenumber = ? where authuserid = ?;";
        jdbcTemplate.update(sql, authUser.getUsername(), authUser.getPassword(), authUser.getPhonenumber(), authUser.getAuthuserid());
    }

    @Override
    public void delete(int id) {
        var sql = "delete from authuser where authuserid = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public AuthUser get(int id) {
        var sql = "delete from authuser where authuserid = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<AuthUser> getAll() {
        var sql = "select * from authuser;";
        return jdbcTemplate.query(sql, rowMapper);
    }
}