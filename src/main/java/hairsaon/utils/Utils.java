package hairsaon.utils;

import hairsaon.models.Client;
import hairsaon.models.Master;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by Boris on 06.04.2017.
 * Класс для создание методов утилит, упрощения проверок
 */
@Service
public class Utils implements IUtils {
    @Override
    public Boolean isLoginInfoExist(Object obj) {
        if (obj instanceof Master){
            Master master = (Master) obj;
            if (master.getEmail().equals("") || master.getEmail() == null || master.getPassword().equals("") || master.getPassword() == null){
                return true;// Wrong login or password
            }
        }else if (obj instanceof Client){
            Client client = (Client) obj;
            if (client.getClientEmail().equals("") || client.getClientPassword().equals("") || client.getClientEmail() == null || client.getClientPassword() == null) {
                return true;
            }
        }
        return false;
    }


    @Override
    public String hashPassword(String rawPassword) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public String parsJwts(String token) {
        String email = Jwts.parser()
                .setSigningKey("ujhswljbnwygh2379633278uYYGHBGYG")
                .parseClaimsJws(token)
                .getBody()
                .get("sub", String.class);
        return email;
    }

    @Override
    public String buildJwt(String email) {
        return Jwts.builder()
                .setSubject(email)
                .claim("roles", "user")
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "ujhswljbnwygh2379633278uYYGHBGYG").compact();
    }

    @Override
    public Boolean isPasswordCorrect(String newPassword, String hashedPassrod) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder.matches(newPassword, hashedPassrod);
    }


}
