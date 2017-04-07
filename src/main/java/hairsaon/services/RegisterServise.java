package hairsaon.services;

import hairsaon.models.client.Client;
import hairsaon.repository.client.IClientRepo;
import hairsaon.models.master.Master;
import hairsaon.repository.master.IMasterRepo;
import hairsaon.repository.master.MasterRepository;
import hairsaon.utils.IUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletException;
import java.util.Date;

/**
 * Created by Boris on 06.04.2017.
 */
@RestController
@CrossOrigin
@RequestMapping("/register")
public class RegisterServise{
    @Autowired
    private IClientRepo clientRepo;
    @Autowired
    private IUtils utils;
    @Autowired
    private IMasterRepo masterRepo;
    @Autowired
    MasterRepository masterRepository;


    @PostMapping("/master")
    public String registerMaster(@RequestBody Master master) throws ServletException {
//        if (utils.isLoginInfoExist(master)){
//            return new ResponseEntity<Master>(HttpStatus.NOT_ACCEPTABLE);// Wrong login or password
//        }
//        Master master1 = masterRepo.registerMaster(master);
//        if (master1 == null) {
//            return new ResponseEntity<Master>(HttpStatus.BAD_REQUEST); // Found same login
//        }
//
//        return new ResponseEntity<Master>(master1, HttpStatus.OK);
//        if (masterRepository.findByEmail(master.getEmail())!=null){
//            return new ResponseEntity<Master>(HttpStatus.BAD_REQUEST);
//        }
//        return  new ResponseEntity<Master>(masterRepository.save(master),HttpStatus.OK);

        if (master.getEmail() == null || master.getPassword() == null) {
            throw new ServletException("Please fill in username and password");
        }


        if (masterRepository.findByEmail(master.getEmail())!=null){
            throw new ServletException("This user already exists");
        }

        String jwtToken = Jwts.builder().setSubject(master.getEmail() + master.getPassword()).claim("roles", "user").setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, "ujhswljbnwygh2379633278uYYGHBGYG").compact();

        return "{\"toketn\":" + "\"" + jwtToken + "\"}";
    }

    @PostMapping("client")
    public ResponseEntity<Client> registerClient(@RequestBody Client client){
        if (utils.isLoginInfoExist(client)){
            return new ResponseEntity<Client>(HttpStatus.NOT_ACCEPTABLE);// Wrong login or password
        }
        Client clientNew = clientRepo.registerClient(client);
        if (clientNew == null) {
            return new ResponseEntity<Client>(HttpStatus.BAD_REQUEST); // Found same login
        }

        return new ResponseEntity<Client>(clientNew, HttpStatus.OK);
    }

}
