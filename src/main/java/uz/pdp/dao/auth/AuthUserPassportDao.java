package uz.pdp.dao.auth;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import uz.pdp.dao.BaseDao;
import uz.pdp.domains.auth.AuthUserPassport;

import java.util.List;

public class AuthUserPassportDao implements BaseDao<AuthUserPassport> {

    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<AuthUserPassport> rowMapper = BeanPropertyRowMapper.newInstance(AuthUserPassport.class);

    public AuthUserPassportDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(AuthUserPassport authUserPassport) {
        var sql = "insert into authuserpassport (authuserid, passportseries, psasportnumber, firstname, lastname, fathersname, datebirth) values (?, ?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(sql,
                authUserPassport.getAuthuserid(),
                authUserPassport.getPassportseries(),
                authUserPassport.getPsasportnumber(),
                authUserPassport.getFirstname(),
                authUserPassport.getLastname(),
                authUserPassport.getFathersname(),
                authUserPassport.getDatebirth()
                );
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
