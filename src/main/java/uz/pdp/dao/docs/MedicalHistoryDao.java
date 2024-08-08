package uz.pdp.dao.docs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import uz.pdp.config.jdbc.SpringJdbcConfig;
import uz.pdp.dao.BaseDao;
import uz.pdp.domains.docs.MedicalHistory;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Component
public class MedicalHistoryDao implements BaseDao<MedicalHistory> {

    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<MedicalHistory> rowMapper = BeanPropertyRowMapper.newInstance(MedicalHistory.class);

    public MedicalHistoryDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new AnnotationConfigApplicationContext(SpringJdbcConfig.class).getBean(JdbcTemplate.class);
    }

    @Override
    public int create(MedicalHistory medicalHistory) {
        var sql = "insert into medicalhistory (authuserpatientid) values (?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"medicalhistoryid"});
            preparedStatement.setInt(1, medicalHistory.getAuthuserpatientid());
            return preparedStatement;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public void update(MedicalHistory medicalHistory) {
        var sql = "update medicalhistory set authuserpatientid = ? where medicalhistoryid = ?";
        jdbcTemplate.update(sql, medicalHistory.getAuthuserpatientid(), medicalHistory.getMedicalhistoryid());
    }

    @Override
    public void delete(int id) {
        var sql = "delete from medicalhistory where medicalhistoryid = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public MedicalHistory get(int id) {
        var sql = "select * from medicalhistory where medicalhistoryid = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<MedicalHistory> getAll() {
        var sql = "select * from medicalhistory";
        return jdbcTemplate.query(sql, rowMapper);
    }
}