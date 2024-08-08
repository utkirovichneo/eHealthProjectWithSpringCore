package uz.pdp.service;

import org.springframework.stereotype.Service;
import uz.pdp.dao.auth.AuthUserAddressDao;
import uz.pdp.dao.auth.AuthUserDao;
import uz.pdp.dao.auth.AuthUserPassportDao;
import uz.pdp.domains.auth.AuthUser;
import uz.pdp.domains.auth.AuthUserAddress;
import uz.pdp.domains.auth.AuthUserPassport;
import uz.pdp.dto.auth.AuthUserDTO;

@Service
public class AuthService {
    private AuthUserDao authUserDao;
    private AuthUserPassportDao authUserPassportDao;
    private AuthUserAddressDao authUserAddressDao;

    public AuthService(AuthUserDao authUserDao, AuthUserPassportDao authUserPassportDao, AuthUserAddressDao authUserAddressDao) {
        this.authUserDao = authUserDao;
        this.authUserPassportDao = authUserPassportDao;
        this.authUserAddressDao = authUserAddressDao;
    }

    public void createAccount(AuthUserDTO dto){
        int authuserid = authUserDao.create(AuthUser
                        .builder()
                        .username(dto.getPhonenumber())
                        .password(dto.getPassportseries()+dto.getPsasportnumber())
                        .phonenumber(dto.getPhonenumber())
                        .build());
        int authuserpassportid = authUserPassportDao.create(AuthUserPassport
                        .builder()
                        .authuserid(authuserid)
                        .firstname(dto.getFirstname())
                        .lastname(dto.getLastname())
                        .fathersname(dto.getFathersname())
                        .passportseries(dto.getPassportseries())
                        .psasportnumber(dto.getPsasportnumber())
                        .datebirth(dto.getDatebirth())
                        .build());

        int authuseraddressid = authUserAddressDao.create(AuthUserAddress
                        .builder()
                        .authuserpassportid(authuserpassportid)
                        .country(dto.getCountry())
                        .city(dto.getCity())
                        .district(dto.getDistrict())
                        .street(dto.getStreet())
                        .apartment(dto.getApartment())
                        .build());
    }

    public void createEmployee(){

    }

    public void createPatient(){

    }

    public AuthUser login(String username){
        return authUserDao.getByUsername(username);
    }
}