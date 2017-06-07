/*
package hairsaon.controller;

import hairsaon.models.Client;
import hairsaon.models.Master;
import hairsaon.models.Services;
import hairsaon.models.classes_for_master.Record;
import hairsaon.models.personal_models_for_schedule.Appointment;
import hairsaon.repository.AppointmentRepository;
import hairsaon.repository.ClientRepository;
import hairsaon.repository.MasterRepository;
import hairsaon.utils.IUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

*/
/**
 * Created by Boris on 24.05.2017.
 *//*


@RestController
@CrossOrigin
@RequestMapping("appointment")
public class AppointmentController {

    private final ClientRepository clientRepository;
    private final MasterRepository masterRepository;
    private final IUtils utils;
    private final AppointmentRepository appointmentRepository;

    @Autowired
    public AppointmentController(ClientRepository clientRepository, MasterRepository masterRepository, IUtils utils, AppointmentRepository appointmentRepository) {
        this.clientRepository = clientRepository;
        this.masterRepository = masterRepository;
        this.utils = utils;
        this.appointmentRepository = appointmentRepository;
    }

    @PutMapping("appointment")
    public ResponseEntity<Object> makeAppointment(@RequestBody Appointment record, @RequestHeader("Authorization") String token) {

        Client client = clientRepository.findClientByClientEmail(utils.parsJwts(token));
        Master master = masterRepository.findByEmail(record.getMasterId());
        long endTimeInt=0;
        for (Services services:record.getService()) {

            endTimeInt = endTimeInt + services.getDuration();
        }
        long l = record.getStarTime().getTime()+(endTimeInt * 1000 * 60);

        record.setEndTime(new Date(l));
        for (Appointment record1 :
                master.getRecords()) {
            if ((record.getStarTime().getTime() >= record1.getStarTime().getTime()
                    && record.getStarTime().getTime() <= record1.getEndTime().getTime())
                    || ((record.getEndTime().getTime() >= record1.getStarTime().getTime()
                    && record.getEndTime().getTime() <= record1.getEndTime().getTime()))
                    || (record.getStarTime().getTime() > record.getEndTime().getTime())
                    || (record.getStarTime().getTime() < record1.getStarTime().getTime()
                    && record.getEndTime().getTime() > record1.getEndTime().getTime())) {

                return new ResponseEntity<>("invalid time, there is appointment in this interval", HttpStatus.CONFLICT);
            }
        }

        appointmentRepository.save(record);
        client.addRecord(record);
        master.addRecord(record);
        clientRepository.save(client);
        masterRepository.save(master);
        return new ResponseEntity<>(record, HttpStatus.OK);
    }


}
*/
