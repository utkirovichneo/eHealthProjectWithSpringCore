package uz.pdp.dao.auth;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import uz.pdp.config.jdbc.SpringJdbcConfig;
import uz.pdp.dao.BaseDao;
import uz.pdp.domains.auth.AuthUserPatient;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Component
public class AuthUserPatientDao implements BaseDao<AuthUserPatient> {

    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<AuthUserPatient> rowMapper = BeanPropertyRowMapper.newInstance(AuthUserPatient.class);

    public AuthUserPatientDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new AnnotationConfigApplicationContext(SpringJdbcConfig.class).getBean(JdbcTemplate.class);
    }

    @Override
    public int create(AuthUserPatient authUserPatient) {
        var sql = "insert into authuserpatient (authuserid) values (?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"authuserpatientid"});
            preparedStatement.setInt(1, authUserPatient.getAuthuserid());
            return preparedStatement;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public void update(AuthUserPatient authUserPatient) {
        var sql = "update authuserpatient set authuserid = ? where authuserpatientid = ?;";
        jdbcTemplate.update(sql, authUserPatient.getAuthuserid(), authUserPatient.getAuthuserpatientid());
    }

    @Override
    public void delete(int id) {
        var sql = "delete from authuserpatient where authuserpatientid = ?;";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public AuthUserPatient get(int id) {
        var sql = "select * from authuserpatient where authuserpatientid = ?;";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<AuthUserPatient> getAll() {
        var sql = "select * from authuserpatient;";
        return jdbcTemplate.query(sql, rowMapper);
    }
}