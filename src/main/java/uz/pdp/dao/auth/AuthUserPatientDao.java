package uz.pdp.dao.auth;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import uz.pdp.dao.BaseDao;
import uz.pdp.domains.auth.AuthUserPatient;

import java.util.List;

public class AuthUserPatientDao implements BaseDao<AuthUserPatient> {

    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<AuthUserPatient> rowMapper = BeanPropertyRowMapper.newInstance(AuthUserPatient.class);

    public AuthUserPatientDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(AuthUserPatient authUserPatient) {
        var sql = "insert into authuserpatient (authuserid) values (?);";
        jdbcTemplate.update(sql, authUserPatient.getAuthuserid());
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