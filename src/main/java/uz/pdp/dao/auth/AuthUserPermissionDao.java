package uz.pdp.dao.auth;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import uz.pdp.dao.BaseDao;
import uz.pdp.domains.auth.AuthUserPermission;

import java.util.List;

public class AuthUserPermissionDao implements BaseDao<AuthUserPermission> {

    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<AuthUserPermission> rowMapper = BeanPropertyRowMapper.newInstance(AuthUserPermission.class);

    public AuthUserPermissionDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(AuthUserPermission authUserPermission) {
        var sql = "insert into authuserpermission (name, code) values (?, ?);";
        jdbcTemplate.update(sql, authUserPermission.getName(), authUserPermission.getCode());
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