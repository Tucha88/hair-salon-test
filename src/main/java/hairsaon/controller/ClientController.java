package hairsaon.controller;


import hairsaon.models.AppointmentArray;
import hairsaon.models.Client;
import hairsaon.models.DateAndDuration;
import hairsaon.models.Master;
import hairsaon.models.classes_for_master.Record;
import hairsaon.models.personal_models_for_schedule.Appointment;
import hairsaon.models.timetable.CalendarDay;
import hairsaon.myExtends.LightCalendar;
import hairsaon.repository.ClientRepository;
import hairsaon.repository.MasterRepository;
import hairsaon.utils.IUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Set;

/**
 * Created by Boris on 17.04.2017.
 */

/**Ветка Мастера*/

@RestController
@CrossOrigin
@RequestMapping("client")
public class ClientController {

    private final ClientRepository clientRepository;
    private final MasterRepository masterRepository;
    private final IUtils utils;

    @Autowired
    public ClientController(ClientRepository clientRepository, IUtils utils, MasterRepository masterRepository) {
        this.clientRepository = clientRepository;
        this.utils = utils;
        this.masterRepository = masterRepository;
    }

    @GetMapping("info") /**есть в документации. дата обновления: 10.06.2017*/
    public ResponseEntity<Object> getClientInfo(@RequestHeader("authorization") String token) {
        String email = utils.parsJwts(token);

        Client client = clientRepository.findClientByClientEmail(email);

        System.out.println(token + client);
        if (client == null) {
            return new ResponseEntity<>("Such client was not found", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(client, HttpStatus.OK);
    }

    @PutMapping("update") /**есть в документации. дата обновления: 10.06.2017*/
    public ResponseEntity<Object> updateClient(@RequestHeader("authorization")String token,@RequestBody Client client) {
        String email = utils.parsJwts(token);

        Client updatedClient = clientRepository.findClientByClientEmail(email);
        if (updatedClient == null) {
            return new ResponseEntity<>("Such user does not exist", HttpStatus.CONFLICT);
        }
        updatedClient.setClientName(client.getClientName());
        updatedClient.setClientLastName(client.getClientLastName());
        updatedClient.setClientPhoneNumber(client.getClientPhoneNumber());


        clientRepository.save(updatedClient);
        return new ResponseEntity<>("Client was updated", HttpStatus.OK);
    }

    @PostMapping("free_time") /**есть в документации. дата обновления: 10.06.2017*/
    public ResponseEntity<Object> freeTimeOnDate(@RequestHeader("Authorization") String token, @RequestBody DateAndDuration dateAndDuration) {
        String email = utils.parsJwts(token);
        Client updatedClient = clientRepository.findClientByClientEmail(email);
        Master master = masterRepository.findByEmail(dateAndDuration.getEmail());
        if (master == null) {
            return new ResponseEntity<>("From this email master was not found", HttpStatus.CONFLICT);
        }
        String dateStr = dateAndDuration.getMyCalendar().toString();
        int duration = dateAndDuration.getDuration();
        Set set = master.getAddressMaster().getFreeTimeOnDate(dateStr, duration);
        return new ResponseEntity<>(set, HttpStatus.OK);
    }



    @PutMapping("add_record") /**есть в документации. дата обновления: 10.06.2017*/
    public ResponseEntity<Object> addRecordForDay(@RequestHeader("Authorization") String token, @RequestBody Record record) {
        String email = utils.parsJwts(token);
        Client updatedClient = clientRepository.findClientByClientEmail(email);
        Master master = masterRepository.findByEmail(record.getMaster());
        if (master == null) {
            return new ResponseEntity<>("there is no such master", HttpStatus.CONFLICT);
        }

        LightCalendar lightCalendar = record.getCalendar();
        CalendarDay calendarDay = master.getAddressMaster().getTimetableMap().get(lightCalendar.toString());
        if (calendarDay == null) {
            return new ResponseEntity<>("Not found a calendar day", HttpStatus.CONFLICT);
        }
        if (!calendarDay.isWorking()) {
            return new ResponseEntity<>("The master does not work this day ", HttpStatus.CONFLICT);
        }

        master.getAddressMaster().getTimetableMap().get(lightCalendar.toString()).addRecord(record);
        updatedClient.addRecord(record);
        clientRepository.save(updatedClient);
        masterRepository.save(master);
        return new ResponseEntity<>("Record was added", HttpStatus.OK);
    }

    @GetMapping("records")
    public ResponseEntity<Object> getClientRecord(@RequestHeader("authorization") String token) {
        String email = utils.parsJwts(token);
        Client client = clientRepository.findClientByClientEmail(email);
        if (client == null) {
            return new ResponseEntity<>("Such client was not found", HttpStatus.CONFLICT);
        }
        ArrayList<Record> records = client.getRecords();
        return new ResponseEntity<>(records, HttpStatus.OK);
    }

    @GetMapping("favorites_masters")
    public ResponseEntity<Object> getFavoritesMasters (@RequestHeader ("authorization") String token){
        String email = utils.parsJwts(token);
        Client client = clientRepository.findClientByClientEmail(email);
        if (client == null) {
            return new ResponseEntity<>("Such client was not found", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(client.getFavoritesMasters(),HttpStatus.OK);
    }

    @PutMapping("add_favorites_masters")
    public ResponseEntity<Object> addFavoritesMasters (@RequestHeader ("authorization") String token, @RequestBody String masterEmail){
        String email = utils.parsJwts(token);
        Client client = clientRepository.findClientByClientEmail(email);
        if (client == null) {
            return new ResponseEntity<>("Such client was not found", HttpStatus.CONFLICT);
        }
        Master master = masterRepository.findByEmail(masterEmail);
        if (master == null) {
            return new ResponseEntity<>("There is no such master", HttpStatus.CONFLICT);
        }
        ArrayList<String> arrFavoritesMasters = client.getFavoritesMasters();
        for (int i = 0; i < arrFavoritesMasters.size(); i++) {
            if (arrFavoritesMasters.get(i).equals(masterEmail)){
                return new ResponseEntity<>("This master is already in the list of favorites", HttpStatus.CONFLICT);
            }
        }
        client.addFavoritesMasters(master.getEmail());
        clientRepository.save(client);
        return new ResponseEntity<>(client.getFavoritesMasters(),HttpStatus.OK);
    }

    @RequestMapping(value = "clients", method = RequestMethod.GET)
    public ResponseEntity<Object> getAllClients() {
        return new ResponseEntity<>(clientRepository.findAll(), HttpStatus.OK);
    }

  /*  @GetMapping("appointment")
    public ResponseEntity<Object> getAppointments(@RequestHeader("Authorization") String token){
        Client master = clientRepository.findClientByClientEmail(utils.parsJwts(token));
        AppointmentArray appointmentArray = new AppointmentArray();
        appointmentArray.setRecords((ArrayList<Appointment>) master.getRecords());
        return new ResponseEntity<>(appointmentArray,HttpStatus.OK);
    }
*/



}
