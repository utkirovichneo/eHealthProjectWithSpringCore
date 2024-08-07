package uz.pdp.dao.routeandqueue;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import uz.pdp.dao.BaseDao;
import uz.pdp.domains.routeandqueue.RoomAndDoctor;

import java.util.List;

public class RoomAndDoctorDao implements BaseDao<RoomAndDoctor> {

    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<RoomAndDoctor> rowMapper = BeanPropertyRowMapper.newInstance(RoomAndDoctor.class);

    @Override
    public void create(RoomAndDoctor roomAndDoctor) {
        var sql = "insert into roomanddoctor (authuserdoctorid, startwork, finishwork) values (?, ?, ?);";
        jdbcTemplate.update(sql, roomAndDoctor.getAuthuserdoctorid(), roomAndDoctor.getStartwork(), roomAndDoctor.getFinishwork());
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