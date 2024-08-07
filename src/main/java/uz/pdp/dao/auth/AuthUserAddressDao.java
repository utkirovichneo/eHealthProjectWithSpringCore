package uz.pdp.dao.auth;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import uz.pdp.dao.BaseDao;
import uz.pdp.domains.auth.AuthUserAddress;

import java.util.List;

public class AuthUserAddressDao implements BaseDao<AuthUserAddress> {

    private JdbcTemplate jdbcTemplate;
    private BeanPropertyRowMapper<AuthUserAddress> rowMapper = BeanPropertyRowMapper.newInstance(AuthUserAddress.class);

    public AuthUserAddressDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void create(AuthUserAddress authUserAddress) {
        var sql = "insert into authuseraddress (authuserpassportid, country, city, district, street, apartment) values (?, ?, ?, ?, ?, ?);";
        jdbcTemplate.update(sql,
                authUserAddress.getAuthuserpassportid(),
                authUserAddress.getCountry(),
                authUserAddress.getCity(),
                authUserAddress.getDistrict(),
                authUserAddress.getStreet(),
                authUserAddress.getApartment());
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