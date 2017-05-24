package hairsaon.controller;

import hairsaon.models.Master;
import hairsaon.repository.ClientRepository;
import hairsaon.repository.MasterRepository;
import hairsaon.utils.IUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Boris on 24.05.2017.
 */

@RestController
@CrossOrigin
@RequestMapping("appointment")
public class Appointment {

    private final ClientRepository clientRepository;
    private final MasterRepository masterRepository;
    private final IUtils utils;

    @Autowired
    public Appointment(ClientRepository clientRepository, MasterRepository masterRepository, IUtils utils) {
        this.clientRepository = clientRepository;
        this.masterRepository = masterRepository;
        this.utils = utils;
    }

    @PutMapping("makeappointment")
    public ResponseEntity<Object> makeAppointment(@RequestBody Master master, @RequestHeader("Authorization") String token) {

        return null;
    }


}
