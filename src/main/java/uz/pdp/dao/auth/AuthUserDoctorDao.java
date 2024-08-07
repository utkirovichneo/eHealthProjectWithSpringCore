package uz.pdp.dao.auth;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import uz.pdp.dao.BaseDao;
import uz.pdp.domains.auth.AuthUserDoctor;

import java.util.List;

public class AuthUserDoctorDao implements BaseDao<AuthUserDoctor> {

    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<AuthUserDoctor> rowMapper = BeanPropertyRowMapper.newInstance(AuthUserDoctor.class);

    public AuthUserDoctorDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(AuthUserDoctor authUserDoctor) {
        var sql = "insert into authuserdoctor (authuseremployeeid, specialistdoctor) values (?, ?);";
        jdbcTemplate.update(sql, authUserDoctor.getAuthuseremployeeid(), authUserDoctor.getSpecialistdoctor());
    }

    @Override
    public void update(AuthUserDoctor authUserDoctor) {
        var sql = "update authuserdoctor set authuseremployeeid = ?, specialistdoctor = ? where authuserdoctorid = ?;";
        jdbcTemplate.update(sql, authUserDoctor.getAuthuseremployeeid(), authUserDoctor.getSpecialistdoctor(), authUserDoctor.getAuthuserdoctorid());
    }

    @Override
    public void delete(int id) {
        var sql = "delete from authuserdoctor where authuserdoctorid = ?;";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public AuthUserDoctor get(int id) {
        var sql = "delete from authuserdoctor where authuserdoctorid = ?;";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<AuthUserDoctor> getAll() {
        var sql = "select * from authuserdoctor;";
        return jdbcTemplate.query(sql, rowMapper);
    }
}
