package uz.pdp.dao.routeandqueue;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import uz.pdp.config.jdbc.SpringJdbcConfig;
import uz.pdp.dao.BaseDao;
import uz.pdp.domains.routeandqueue.AppointmentWithDoctor;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Component
public class AppointmentWithDoctorDao implements BaseDao<AppointmentWithDoctor> {

    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<AppointmentWithDoctor> rowMapper = BeanPropertyRowMapper.newInstance(AppointmentWithDoctor.class);

    public AppointmentWithDoctorDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new AnnotationConfigApplicationContext(SpringJdbcConfig.class).getBean(JdbcTemplate.class);
    }

    @Override
    public int create(AppointmentWithDoctor appointmentWithDoctor) {
        var sql = "insert into appointmentwithdoctor (roomanddoctorid, authuserpatientid, sequencenumber, estimatedqueuetime) values (?, ?, ?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{});
            preparedStatement.setInt(1, appointmentWithDoctor.getRoomanddoctorid());
            preparedStatement.setInt(2, appointmentWithDoctor.getAuthuserpatientid());
            preparedStatement.setInt(3, appointmentWithDoctor.getSequencenumber());
            preparedStatement.setTimestamp(4, Timestamp.valueOf(appointmentWithDoctor.getEstimatedqueuetime()));
            return preparedStatement;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public void update(AppointmentWithDoctor appointmentWithDoctor) {
        var sql = "update appointmentwithdoctor set roomanddoctorid = ?, authuserpatientid = ?, sequencenumber = ?, estimatedqueuetime = ? where appointmentwithdoctorid = ?";
        jdbcTemplate.update(sql,
                appointmentWithDoctor.getRoomanddoctorid(),
                appointmentWithDoctor.getAuthuserpatientid(),
                appointmentWithDoctor.getSequencenumber(),
                appointmentWithDoctor.getEstimatedqueuetime(),
                appointmentWithDoctor.getAppointmentwithdoctorid());
    }

    @Override
    public void delete(int id) {
        var sql = "delete from appointmentwithdoctor where appointmentwithdoctorid = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public AppointmentWithDoctor get(int id) {
        var sql = "select * from appointmentwithdoctor where appointmentwithdoctorid = ?;";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<AppointmentWithDoctor> getAll() {
        var sql = "select * from appointmentwithdoctor;";
        return jdbcTemplate.query(sql, rowMapper);
    }
}