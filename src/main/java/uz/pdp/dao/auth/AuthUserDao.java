package uz.pdp.dao.auth;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import uz.pdp.config.jdbc.SpringJdbcConfig;
import uz.pdp.dao.BaseDao;
import uz.pdp.domains.auth.AuthUser;
import uz.pdp.domains.auth.AuthUserAddress;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Component
public class AuthUserDao implements BaseDao<AuthUser> {

    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<AuthUser> rowMapper = BeanPropertyRowMapper.newInstance(AuthUser.class);

    public AuthUserDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new AnnotationConfigApplicationContext(SpringJdbcConfig.class).getBean(JdbcTemplate.class);
    }

    @Override
    public int create(AuthUser authUser) {
        System.err.println("authUser = " + authUser);
        var sql = "insert into authuser (username, password, phonenumber) values (?, ?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection ->{
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"authuserid"});
            preparedStatement.setString(1, authUser.getUsername());
            preparedStatement.setString(2, authUser.getPassword());
            preparedStatement.setString(3, authUser.getPhonenumber());
            return preparedStatement;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
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

    public AuthUser getByUsername(String username) {
        var sql = "select * from authuser where username = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, username);
    }

    @Override
    public List<AuthUser> getAll() {
        var sql = "select * from authuser;";
        return jdbcTemplate.query(sql, rowMapper);
    }
}