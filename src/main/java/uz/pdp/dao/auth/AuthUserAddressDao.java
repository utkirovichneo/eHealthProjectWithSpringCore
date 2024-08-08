package uz.pdp.dao.auth;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import uz.pdp.config.jdbc.SpringJdbcConfig;
import uz.pdp.dao.BaseDao;
import uz.pdp.domains.auth.AuthUserAddress;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Objects;

@Component
public class AuthUserAddressDao implements BaseDao<AuthUserAddress> {

    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<AuthUserAddress> rowMapper = BeanPropertyRowMapper.newInstance(AuthUserAddress.class);

    public AuthUserAddressDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = new AnnotationConfigApplicationContext(SpringJdbcConfig.class).getBean(JdbcTemplate.class);
    }

    @Override
    public int create(AuthUserAddress authUserAddress) {
        var sql = "insert into authuseraddress (authuserpassportid, country, city, district, street, apartment) values (?, ?, ?, ?, ?, ?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(sql, new String[]{"authuseraddressid"});
            preparedStatement.setInt(1, authUserAddress.getAuthuserpassportid());
            preparedStatement.setString(2, authUserAddress.getCountry());
            preparedStatement.setString(3, authUserAddress.getCity());
            preparedStatement.setString(4, authUserAddress.getDistrict());
            preparedStatement.setString(5, authUserAddress.getStreet());
            preparedStatement.setString(6, authUserAddress.getApartment());
            return preparedStatement;
        }, keyHolder);
        return Objects.requireNonNull(keyHolder.getKey()).intValue();
    }

    @Override
    public void update(AuthUserAddress authUserAddress) {
        var sql = "update authuseraddress set authuserpassportid = ?, country = ?, city = ?, district = ?, street = ?, apartment = ? where authuseraddressid = ?;";
        jdbcTemplate.update(sql,
                authUserAddress.getAuthuserpassportid(),
                authUserAddress.getCountry(),
                authUserAddress.getCity(),
                authUserAddress.getDistrict(),
                authUserAddress.getStreet(),
                authUserAddress.getApartment(),
                authUserAddress.getAuthuseraddressid());
    }

    @Override
    public void delete(int id) {
        var sql = "delete from authuseraddress where authuseraddressid = ?;";
        jdbcTemplate.update(sql, id);
    }

    @Override
    public AuthUserAddress get(int id) {
        var sql = "select * from authuseraddress where authuseraddressid = ?;";
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<AuthUserAddress> getAll() {
        var sql = "select * from authuseraddress;";
        return jdbcTemplate.query(sql, rowMapper);
    }
}