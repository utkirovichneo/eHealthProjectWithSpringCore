package uz.pdp.dao.routeandqueue;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import uz.pdp.dao.BaseDao;
import uz.pdp.domains.routeandqueue.AppointmentWithDoctor;

import java.util.List;

public class AppointmentWithDoctorDao implements BaseDao<AppointmentWithDoctor> {

    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<AppointmentWithDoctor> rowMapper = BeanPropertyRowMapper.newInstance(AppointmentWithDoctor.class);

    public AppointmentWithDoctorDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(AppointmentWithDoctor appointmentWithDoctor) {
        var sql = "insert into appointmentwithdoctor (roomanddoctorid, authuserpatientid, sequencenumber, estimatedqueuetime) values (?, ?, ?, ?);";
        jdbcTemplate.update(sql,
                appointmentWithDoctor.getRoomanddoctorid(),
                appointmentWithDoctor.getAuthuserpatientid(),
                appointmentWithDoctor.getSequencenumber(),
                appointmentWithDoctor.getEstimatedqueuetime());
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