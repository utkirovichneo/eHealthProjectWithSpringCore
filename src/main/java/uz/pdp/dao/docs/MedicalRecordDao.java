package uz.pdp.dao.docs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import uz.pdp.config.jdbc.SpringJdbcConfig;
import uz.pdp.dao.BaseDao;
import uz.pdp.domains.docs.MedicalRecord;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Component
public class MedicalRecordDao implements BaseDao<MedicalRecord> {

    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<MedicalRecord> rowMapper = BeanPropertyRowMapper.newInstance(MedicalRecord.class);

    public MedicalRecordDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new AnnotationConfigApplicationContext(SpringJdbcConfig.class).getBean(JdbcTemplate.class);
    }

    @Override
    public int create(MedicalRecord medicalRecord) {
        var sql = "insert into medicalrecord (medicalhistoryid, authuserdoctorid, dateofdiagnosis, diagnosis, treatment,notes) values (?, ?, ?, ?, ?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"medicalrecordid"});
            preparedStatement.setInt(1, medicalRecord.getMedicalhistoryid());
            preparedStatement.setInt(2, medicalRecord.getAuthuserdoctorid());
            preparedStatement.setTimestamp(3, Timestamp.valueOf(medicalRecord.getDateofdiagnosis()));
            preparedStatement.setString(4, medicalRecord.getDiagnosis());
            preparedStatement.setString(5, medicalRecord.getTreatment());
            preparedStatement.setString(6, medicalRecord.getNotes());
            return preparedStatement;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public void update(MedicalRecord medicalRecord) {
        var sql = "update medicalrecord set medicalhistoryid = ?, authuserdoctorid = ?, dateofdiagnosis = ?, diagnosis = ?, treatment = ?, notes = ? where medicalrecordid = ?";
        jdbcTemplate.update(sql,
                medicalRecord.getMedicalhistoryid(),
                medicalRecord.getAuthuserdoctorid(),
                medicalRecord.getDateofdiagnosis(),
                medicalRecord.getDiagnosis(),
                medicalRecord.getTreatment(),
                medicalRecord.getNotes(),
                medicalRecord.getMedicalrecordid());
    }

    @Override
    public void delete(int id) {
        var sql = "delete from medicalrecord where medicalrecordid = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public MedicalRecord get(int id) {
        var sql = "select * from medicalrecord where medicalrecordid = ?;";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<MedicalRecord> getAll() {
        var sql = "select * from medicalrecord where medicalrecordid = ?;";
        return jdbcTemplate.query(sql, rowMapper);
    }
}