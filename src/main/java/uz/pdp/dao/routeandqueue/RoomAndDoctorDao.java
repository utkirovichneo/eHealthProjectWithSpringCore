package uz.pdp.dao.routeandqueue;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import uz.pdp.config.jdbc.SpringJdbcConfig;
import uz.pdp.dao.BaseDao;
import uz.pdp.domains.routeandqueue.RoomAndDoctor;

import java.sql.PreparedStatement;
import java.sql.Time;
import java.util.List;
import java.util.Objects;

@Component
public class RoomAndDoctorDao implements BaseDao<RoomAndDoctor> {

    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<RoomAndDoctor> rowMapper = BeanPropertyRowMapper.newInstance(RoomAndDoctor.class);

    public RoomAndDoctorDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new AnnotationConfigApplicationContext(SpringJdbcConfig.class).getBean(JdbcTemplate.class);
    }

    @Override
    public int create(RoomAndDoctor roomAndDoctor) {
        var sql = "insert into roomanddoctor (authuserdoctorid, startwork, finishwork) values (?, ?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"roomanddoctorid"});
            preparedStatement.setInt(1, roomAndDoctor.getAuthuserdoctorid());
            preparedStatement.setTime(2, Time.valueOf(roomAndDoctor.getStartwork()));
            preparedStatement.setTime(3, Time.valueOf(roomAndDoctor.getFinishwork()));
            return preparedStatement;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public void update(RoomAndDoctor roomAndDoctor) {
        var sql = "update roomanddoctor set startwork = ?, finishwork = ? where roomanddoctorid = ?";
        jdbcTemplate.update(sql, roomAndDoctor.getStartwork(), roomAndDoctor.getFinishwork(), roomAndDoctor.getRoomanddoctorid());
    }

    @Override
    public void delete(int id) {
        var sql = "delete from roomanddoctor where roomanddoctorid = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public RoomAndDoctor get(int id) {
        var sql = "select * from roomanddoctor where roomanddoctorid = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<RoomAndDoctor> getAll() {
        var sql = "select * from roomanddoctor";
        return jdbcTemplate.query(sql, rowMapper);
    }
}