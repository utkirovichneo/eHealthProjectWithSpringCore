package uz.pdp.dao.auth;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import uz.pdp.config.jdbc.SpringJdbcConfig;
import uz.pdp.dao.BaseDao;
import uz.pdp.domains.auth.AuthUserPermission;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Component
public class AuthUserPermissionDao implements BaseDao<AuthUserPermission> {

    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<AuthUserPermission> rowMapper = BeanPropertyRowMapper.newInstance(AuthUserPermission.class);

    public AuthUserPermissionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new AnnotationConfigApplicationContext(SpringJdbcConfig.class).getBean(JdbcTemplate.class);
    }

    @Override
    public int create(AuthUserPermission authUserPermission) {
        var sql = "insert into authuserpermission (name, code) values (?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"authuserpermissionid"});
            preparedStatement.setString(1, authUserPermission.getName());
            preparedStatement.setString(2, authUserPermission.getCode());
            return preparedStatement;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public void update(AuthUserPermission authUserPermission) {
        var sql = "update authuserpermission set name=?, code=? where authuserpermissionid=?;";
        jdbcTemplate.update(sql, authUserPermission.getName(), authUserPermission.getCode(), authUserPermission.getAuthuserpermissionid());
    }

    @Override
    public void delete(int id) {
        var sql = "delete from authuserpermission where authuserpermissionid=?;";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public AuthUserPermission get(int id) {
        var sql = "select * from authuserpermission where authuserpermissionid = ?;";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<AuthUserPermission> getAll() {
        var sql = "select * from authuserpermission;";
        return jdbcTemplate.query(sql, rowMapper);
    }
}