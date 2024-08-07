package uz.pdp.dao.docs;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import uz.pdp.dao.BaseDao;
import uz.pdp.domains.docs.MedicalRecord;

import java.util.List;

public class MedicalRecordDao implements BaseDao<MedicalRecord> {

    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<MedicalRecord> rowMapper = BeanPropertyRowMapper.newInstance(MedicalRecord.class);

    public MedicalRecordDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(MedicalRecord medicalRecord) {
        var sql = "insert into medicalrecord (medicalhistoryid, authuserdoctorid, dateofdiagnosis, diagnosis, treatment,notes) values (?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(sql,
                medicalRecord.getMedicalhistoryid(),
                medicalRecord.getAuthuserdoctorid(),
                medicalRecord.getDateofdiagnosis(),
                medicalRecord.getDiagnosis(),
                medicalRecord.getTreatment(),
                medicalRecord.getNotes());
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