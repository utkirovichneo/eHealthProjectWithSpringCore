package uz.pdp.dao.docs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import uz.pdp.config.jdbc.SpringJdbcConfig;
import uz.pdp.dao.BaseDao;
import uz.pdp.domains.docs.AnalysisResult;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Component
public class AnalysisResultDao implements BaseDao<AnalysisResult> {

    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<AnalysisResult> rowMapper = BeanPropertyRowMapper.newInstance(AnalysisResult.class);

    public AnalysisResultDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new AnnotationConfigApplicationContext(SpringJdbcConfig.class).getBean(JdbcTemplate.class);
    }

    @Override
    public int create(AnalysisResult analysisResult) {
        var sql = "insert into analysisresult (medicalhistoryid, description) values (?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"analysisresultid"});
            preparedStatement.setInt(1, analysisResult.getMedicalhistoryid());
            preparedStatement.setString(2, analysisResult.getDescription());
            return preparedStatement;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
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