package hairsaon.services;

import hairsaon.models.Client;
import hairsaon.models.Master;
import hairsaon.repository.ClientRepository;
import hairsaon.repository.MasterRepository;
import hairsaon.utils.IUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.ws.rs.PathParam;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


/**
 * Created by Boris on 01.04.2017.
 * Класс контроллера для изменения или удаления клиента или мастера
 * так же для получения полного списка мастеров и клиентов
 */

//TODO добавить метод для получаения всех клиентов

@RestController
@CrossOrigin
@RequestMapping("service")
public class HairSalonController {
    @Autowired
    private MasterRepository masterRepositoryr;
    @Autowired
    private ClientRepository clientRepository;

    @PutMapping("updatemaster")
    public ResponseEntity<Object> updateMuster(@RequestBody Master master) {


        Master updatedMaster = masterRepositoryr.findByEmail(master.getEmail());


        if (updatedMaster == null) {
            return new ResponseEntity<>("master doesn't exist", HttpStatus.CONFLICT);
        }
        if (!master.getPassword().equals(updatedMaster.getPassword())) {
            return new ResponseEntity<>("Wrong password", HttpStatus.CONFLICT);
        }

        updatedMaster = master;
        masterRepositoryr.saveAndFlush(updatedMaster);
        return new ResponseEntity<>(updatedMaster, HttpStatus.OK);
    }

    @RequestMapping(value = "masters", method = RequestMethod.GET)
    public ResponseEntity<List<Master>> getAllMasters() {
        return new ResponseEntity<>(masterRepositoryr.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "masters/{id:.+}", method = RequestMethod.GET)
    public ResponseEntity<Object> getMaster(@PathVariable("id") String id) {
        Master master = masterRepositoryr.findByEmail(id);
        if (master == null) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(master, HttpStatus.OK);
    }

    @RequestMapping(value = "clients", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllClients() {
        return new ResponseEntity<>(clientRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "clients/{id:.+}", method = RequestMethod.GET)
    public ResponseEntity<Object> getClient(@PathVariable("id") String id) {
        Client client = clientRepository.findClientByClientEmail(id);
        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(client, HttpStatus.OK);
    }



}
