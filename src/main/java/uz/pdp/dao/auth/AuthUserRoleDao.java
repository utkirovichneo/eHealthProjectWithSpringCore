package uz.pdp.dao.auth;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import uz.pdp.dao.BaseDao;
import uz.pdp.domains.auth.AuthUserRole;

import java.util.List;

public class AuthUserRoleDao implements BaseDao<AuthUserRole> {

    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<AuthUserRole> rowMapper = BeanPropertyRowMapper.newInstance(AuthUserRole.class);

    public AuthUserRoleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(AuthUserRole authUserRole) {
        var sql = "insert into authuserrole (name, code) values (?, ?);";
        jdbcTemplate.update(sql, authUserRole.getName(), authUserRole.getCode());
    }

    @Override
    public void update(AuthUserRole authUserRole) {
        var sql = "update authuserrole set name=?, code=? where authuserroleid=?;";
        jdbcTemplate.update(sql, authUserRole.getName(), authUserRole.getCode(), authUserRole.getAuthuserroleid());
    }

    @Override
    public void delete(int id) {
        var sql = "delete from authuserrole where authuserroleid=?;";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public AuthUserRole get(int id) {
        var sql = "select * from authuserrole where authuserroleid = ?;";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<AuthUserRole> getAll() {
        var sql = "select * from authuserrole;";
        return jdbcTemplate.query(sql, rowMapper);
    }
}