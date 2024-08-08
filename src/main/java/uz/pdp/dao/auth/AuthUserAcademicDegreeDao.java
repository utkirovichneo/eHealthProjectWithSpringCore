package uz.pdp.dao.auth;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import uz.pdp.config.jdbc.SpringJdbcConfig;
import uz.pdp.dao.BaseDao;
import uz.pdp.domains.auth.AuthUserAcademicDegree;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Component
public class AuthUserAcademicDegreeDao implements BaseDao<AuthUserAcademicDegree> {

    private JdbcTemplate template;
    private BeanPropertyRowMapper<AuthUserAcademicDegree> rowMapper = BeanPropertyRowMapper.newInstance(AuthUserAcademicDegree.class);

    public AuthUserAcademicDegreeDao(JdbcTemplate template) {
        this.template = new AnnotationConfigApplicationContext(SpringJdbcConfig.class).getBean(JdbcTemplate.class);
    }

    @Override
    public int create(AuthUserAcademicDegree authUserAcademicDegree) {
        var sql = "insert into authuseracademicdegree (authuseremployeeid,degree, graduated, yeargraduated) values (?, ?, ?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        template.update(
                connection -> {
                    PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"authuseracademicdegreeid"});
                    preparedStatement.setInt(1, authUserAcademicDegree.getAuthuseremployeeid());
                    preparedStatement.setString(2, authUserAcademicDegree.getDegree());
                    preparedStatement.setString(3, authUserAcademicDegree.getGraduated());
                    preparedStatement.setDate(4, Date.valueOf(authUserAcademicDegree.getYeargraduated()));
                    return preparedStatement;
                }, keyHolder
        );
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
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
