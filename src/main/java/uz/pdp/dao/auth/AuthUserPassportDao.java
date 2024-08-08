package uz.pdp.dao.auth;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import uz.pdp.config.jdbc.SpringJdbcConfig;
import uz.pdp.dao.BaseDao;
import uz.pdp.domains.auth.AuthUserPassport;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Component
public class AuthUserPassportDao implements BaseDao<AuthUserPassport> {

    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<AuthUserPassport> rowMapper = BeanPropertyRowMapper.newInstance(AuthUserPassport.class);

    public AuthUserPassportDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new AnnotationConfigApplicationContext(SpringJdbcConfig.class).getBean(JdbcTemplate.class);
    }

    @Override
    public int create(AuthUserPassport authUserPassport) {
        var sql = "insert into authuserpassport (authuserid, passportseries, psasportnumber, firstname, lastname, fathersname, datebirth) values (?, ?, ?, ?, ?, ?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"authuserpassportid"});
            preparedStatement.setInt(1, authUserPassport.getAuthuserid());
            preparedStatement.setString(2, authUserPassport.getPassportseries());
            preparedStatement.setString(3, authUserPassport.getPsasportnumber());
            preparedStatement.setString(4, authUserPassport.getFirstname());
            preparedStatement.setString(5, authUserPassport.getLastname());
            preparedStatement.setString(6, authUserPassport.getFathersname());
            preparedStatement.setDate(7, Date.valueOf(authUserPassport.getDatebirth()));
            return preparedStatement;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public void update(AuthUserPassport authUserPassport) {
        var sql = "update authuserpassport set authuserid = ?, passportseries = ?, psasportnumber = ?, firstname = ?, lastname = ?, fathersname = ?, datebirth = ? where authuserid = ?";
        jdbcTemplate.update(sql,
                authUserPassport.getAuthuserid(),
                authUserPassport.getPassportseries(),
                authUserPassport.getPsasportnumber(),
                authUserPassport.getFirstname(),
                authUserPassport.getLastname(),
                authUserPassport.getFathersname(),
                authUserPassport.getDatebirth(),
                authUserPassport.getAuthuserpassportid()
        );
    }

    @Override
    public void delete(int id) {
        var sql = "delete from authuserpassport where authuserpassportid = ?";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public AuthUserPassport get(int id) {
        var sql = "select * from authuserpassport where authuserpassportid = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<AuthUserPassport> getAll() {
        var sql = "select * from authuserpassport;";
        return jdbcTemplate.query(sql, rowMapper);
    }
}
