package uz.pdp.dao.auth;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import uz.pdp.dao.BaseDao;
import uz.pdp.domains.auth.AuthUserAcademicDegree;

import java.util.List;

public class AuthUserAcademicDegreeDao implements BaseDao<AuthUserAcademicDegree> {

    private JdbcTemplate template;
    private BeanPropertyRowMapper<AuthUserAcademicDegree> rowMapper = BeanPropertyRowMapper.newInstance(AuthUserAcademicDegree.class);

    public AuthUserAcademicDegreeDao(JdbcTemplate template) {
        this.template = template;
    }

    @Override
    public void create(AuthUserAcademicDegree authUserAcademicDegree) {
        var sql = "insert into authuseracademicdegree (authuseremployeeid,degree, graduated, yeargraduated) values (?, ?, ?, ?);";
        template.update(sql,
                authUserAcademicDegree.getAuthuseremployeeid(),
                authUserAcademicDegree.getDegree(),
                authUserAcademicDegree.getGraduated(),
                authUserAcademicDegree.getYeargraduated());
    }

    @Override
    public void update(AuthUserAcademicDegree authUserAcademicDegree) {
        var sql = "update authuseracademicdegree set authuseremployeeid = ?, degree = ?, graduated = ?, yeargraduated = ? where authuseracademicdegreeid = ?;";
        template.update(sql,
                authUserAcademicDegree.getAuthuseremployeeid(),
                authUserAcademicDegree.getDegree(),
                authUserAcademicDegree.getGraduated(),
                authUserAcademicDegree.getYeargraduated(),
                authUserAcademicDegree.getAuthuseracademicdegreeid());
    }

    @Override
    public void delete(int id) {
        var sql = "delete from authuseracademicdegree where authuseracademicdegreeid = ?;";
        template.update(sql, id);
    }

    @Override
    public AuthUserAcademicDegree get(int id) {
        var sql = "select * from authuseracademicdegree where authuseracademicdegreeid = ?;";
        return template.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<AuthUserAcademicDegree> getAll() {
        var sql = "select * from authuseracademicdegree;";
        return template.query(sql, rowMapper);
    }
}
