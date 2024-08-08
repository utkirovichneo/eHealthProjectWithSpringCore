package uz.pdp.dao.docs;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import uz.pdp.config.jdbc.SpringJdbcConfig;
import uz.pdp.dao.BaseDao;
import uz.pdp.domains.docs.AnalysisDocument;

import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.List;
import java.util.Objects;

@Component
public class AnalysisDocumentDao implements BaseDao<AnalysisDocument> {

    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<AnalysisDocument> rowMapper = BeanPropertyRowMapper.newInstance(AnalysisDocument.class);

    public AnalysisDocumentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new AnnotationConfigApplicationContext(SpringJdbcConfig.class).getBean(JdbcTemplate.class);
    }

    @Override
    public int create(AnalysisDocument analysisDocument) {
        var sql = "insert into analysisdocument (analysisresultid, analysisresultdate, analysistype) values (?, ?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"analysisdocumentid"});
            preparedStatement.setInt(1, analysisDocument.getAnalysisresultid());
            preparedStatement.setTimestamp(2, Timestamp.valueOf(analysisDocument.getAnalysisresultdate()));
            preparedStatement.setString(3, analysisDocument.getAnalysistype());
            return preparedStatement;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public void update(AnalysisDocument analysisDocument) {
        var sql = "update analysisdocument set analysisresultid = ?, analysisresultdate = ?, analysistype = ? where analysisdocumentid = ?";
        jdbcTemplate.update(sql,
                analysisDocument.getAnalysisresultid(),
                analysisDocument.getAnalysisresultdate(),
                analysisDocument.getAnalysistype(),
                analysisDocument.getAnalysisdocumentid());
    }

    @Override
    public void delete(int id) {
        var sql = "delete from analysisdocument where analysisdocumentid = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public AnalysisDocument get(int id) {
        var sql = "select * from analysisdocument where analysisdocumentid = ?;";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<AnalysisDocument> getAll() {
        var sql = "select * from analysisdocument;";
        return jdbcTemplate.query(sql, rowMapper);
    }
}