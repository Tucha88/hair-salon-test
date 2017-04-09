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
    private static String UPLOAD_PATH = "F://upload//";
    @Autowired
    private IUtils utils;
    @Autowired
    private MasterRepository masterRepositoryr;
    @Autowired
    private ClientRepository clientRepository;

    @PutMapping("updatemaster")
    public ResponseEntity<Master> updateMuster(@RequestBody Master master) {


        Master updatedMaster = masterRepositoryr.findByEmail(master.getEmail());


        if (updatedMaster == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        if (!master.getPassword().equals(updatedMaster.getPassword())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
//        updatedMaster.setLang(master.getLang());
//        updatedMaster.setName(master.getName());
//        updatedMaster.setMasterType(master.getMasterType());
//        updatedMaster.setLastName(master.getLastName());
//        updatedMaster.setPhoneNumber(master.getPhoneNumber());
//        updatedMaster.addServise(master.getSerivce());
//        updatedMaster.addAdress(master.getAdress());

        updatedMaster = master;
        masterRepositoryr.saveAndFlush(updatedMaster);
        return new ResponseEntity<>(updatedMaster, HttpStatus.OK);
    }

    @RequestMapping(value = "masters", method = RequestMethod.GET)
    public ResponseEntity<List<Master>> getAllMasters() {
        return new ResponseEntity<>(masterRepositoryr.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "masters/{id:.+}", method = RequestMethod.GET)
    public ResponseEntity<Master> getMaster(@PathVariable("id") String id) {
        Master master = masterRepositoryr.findByEmail(id);
        if (master == null) {
            return new ResponseEntity<Master>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(master, HttpStatus.OK);
    }

    @RequestMapping(value = "clients", method = RequestMethod.GET)
    public ResponseEntity<List<Client>> getAllClients() {
        return new ResponseEntity<>(clientRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "clients/{id:.+}", method = RequestMethod.GET)
    public ResponseEntity<Client> getClient(@PathVariable("id") String id) {
        Client client = clientRepository.findClientByClientEmail(id);
        if (client == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PutMapping("updateclient")
    public ResponseEntity<Client> updateClietn(@RequestBody Client client) {
        Client updatedClient = clientRepository.findClientByClientEmail(client.getClientEmail());
        if (updatedClient == null) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        if (!client.getClientPassword().equals(updatedClient.getClientPassword())) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        updatedClient = client;
        clientRepository.saveAndFlush(updatedClient);
        return new ResponseEntity<>(updatedClient, HttpStatus.OK);
    }

    @PostMapping("uploadfile")
    public ResponseEntity<String> uploadFile(MultipartFile file) {

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_PATH + file.getOriginalFilename());
            Files.write(path, bytes);
            return new ResponseEntity<String>("Thanks for a upload", HttpStatus.OK);
        } catch (IOException e) {
            String str = e.getMessage();
            return new ResponseEntity<String>(str, HttpStatus.CONFLICT);
        }
    }


}
