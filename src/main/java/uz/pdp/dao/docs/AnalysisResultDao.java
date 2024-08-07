package uz.pdp.dao.docs;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import uz.pdp.dao.BaseDao;
import uz.pdp.domains.docs.AnalysisResult;

import java.util.List;

public class AnalysisResultDao implements BaseDao<AnalysisResult> {

    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<AnalysisResult> rowMapper = BeanPropertyRowMapper.newInstance(AnalysisResult.class);

    public AnalysisResultDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(AnalysisResult analysisResult) {
        var sql = "insert into analysisresult (medicalhistoryid, description) values (?, ?);";
        jdbcTemplate.update(sql, analysisResult.getMedicalhistoryid(), analysisResult.getDescription());
    }

    @Override
    public void update(AnalysisResult analysisResult) {
        var sql = "update analysisresult set medicalhistoryid = ?, description = ? where analysisresultid = ?";
        jdbcTemplate.update(sql, analysisResult.getMedicalhistoryid(), analysisResult.getDescription(), analysisResult.getAnalysisresultid());
    }

    @Override
    public void delete(int id) {
        var sql = "delete from analysisresult where analysisresultid = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public AnalysisResult get(int id) {
        var sql = "select * from analysisresult where analysisresultid = ?;";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<AnalysisResult> getAll() {
        var sql = "select * from analysisresult";
        return jdbcTemplate.query(sql, rowMapper);
    }
}