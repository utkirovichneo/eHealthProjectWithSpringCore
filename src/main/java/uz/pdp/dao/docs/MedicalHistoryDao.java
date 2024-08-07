package uz.pdp.dao.docs;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import uz.pdp.dao.BaseDao;
import uz.pdp.domains.docs.MedicalHistory;

import java.util.List;

public class MedicalHistoryDao implements BaseDao<MedicalHistory> {

    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<MedicalHistory> rowMapper = BeanPropertyRowMapper.newInstance(MedicalHistory.class);

    public MedicalHistoryDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(MedicalHistory medicalHistory) {
        var sql = "insert into medicalhistory (authuserpatientid) values (?);";
        jdbcTemplate.update(sql, medicalHistory.getAuthuserpatientid());
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