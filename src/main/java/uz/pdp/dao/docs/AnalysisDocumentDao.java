package uz.pdp.dao.docs;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import uz.pdp.dao.BaseDao;
import uz.pdp.domains.docs.AnalysisDocument;

import java.util.List;

public class AnalysisDocumentDao implements BaseDao<AnalysisDocument> {

    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<AnalysisDocument> rowMapper = BeanPropertyRowMapper.newInstance(AnalysisDocument.class);

    public AnalysisDocumentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(AnalysisDocument analysisDocument) {
        var sql = "insert into analysisdocument (analysisresultid, analysisresultdate, analysistype) values (?, ?, ?);";
        jdbcTemplate.update(sql, analysisDocument.getAnalysisresultid(), analysisDocument.getAnalysisresultdate(), analysisDocument.getAnalysistype());
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