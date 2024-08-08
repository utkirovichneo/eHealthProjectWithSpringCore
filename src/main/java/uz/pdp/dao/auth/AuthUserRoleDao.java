package uz.pdp.dao.auth;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import uz.pdp.config.jdbc.SpringJdbcConfig;
import uz.pdp.dao.BaseDao;
import uz.pdp.domains.auth.AuthUserRole;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Component
public class AuthUserRoleDao implements BaseDao<AuthUserRole> {

    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<AuthUserRole> rowMapper = BeanPropertyRowMapper.newInstance(AuthUserRole.class);

    public AuthUserRoleDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new AnnotationConfigApplicationContext(SpringJdbcConfig.class).getBean(JdbcTemplate.class);
    }

    @Override
    public int create(AuthUserRole authUserRole) {
        var sql = "insert into authuserrole (name, code) values (?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"authuserroleid"});
            preparedStatement.setString(1, authUserRole.getName());
            preparedStatement.setString(2, authUserRole.getCode());
            return preparedStatement;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
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