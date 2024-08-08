package uz.pdp.dao.auth;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import uz.pdp.config.jdbc.SpringJdbcConfig;
import uz.pdp.dao.BaseDao;
import uz.pdp.domains.auth.AuthUserDoctor;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Component
public class AuthUserDoctorDao implements BaseDao<AuthUserDoctor> {

    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<AuthUserDoctor> rowMapper = BeanPropertyRowMapper.newInstance(AuthUserDoctor.class);

    public AuthUserDoctorDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new AnnotationConfigApplicationContext(SpringJdbcConfig.class).getBean(JdbcTemplate.class);
    }

    @Override
    public int create(AuthUserDoctor authUserDoctor) {
        var sql = "insert into authuserdoctor (authuseremployeeid, specialistdoctor) values (?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"authuserdoctorid"});
            preparedStatement.setInt(1, authUserDoctor.getAuthuseremployeeid());
            preparedStatement.setString(2, authUserDoctor.getSpecialistdoctor());
            return preparedStatement;
            }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
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
