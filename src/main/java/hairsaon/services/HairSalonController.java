package hairsaon.services;

import hairsaon.models.Client;
import hairsaon.models.Master;
import hairsaon.repository.ClientRepository;
import hairsaon.repository.MasterRepository;
import hairsaon.utils.IUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
        updatedMaster = master;
        masterRepositoryr.saveAndFlush(updatedMaster);
        return new ResponseEntity<>(updatedMaster, HttpStatus.OK);
    }

    @RequestMapping(value = "masstersarray", method = RequestMethod.GET)
    public ResponseEntity<List<Master>> getAllMasters() {
        return new ResponseEntity<>(masterRepositoryr.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "master/{id}", method = RequestMethod.GET)
    public ResponseEntity<Master> getMaster(@PathVariable String id) {
        return new ResponseEntity<>(masterRepositoryr.findByEmail(id), HttpStatus.OK);
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


}
