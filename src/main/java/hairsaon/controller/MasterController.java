package hairsaon.controller;


import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.errors.ApiException;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Geometry;
import hairsaon.models.DateAndDuration;
import hairsaon.models.DateAndRecord;
import hairsaon.models.Master;
import hairsaon.models.Services;
import hairsaon.models.classes_for_master.Record;
import hairsaon.models.timetable.CalendarDay;
import hairsaon.models.timetable.WeekDay;
import hairsaon.myExtends.LightCalendar;
import hairsaon.repository.MasterRepository;
import hairsaon.utils.IUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.*;

/**
 * Created by Boris on 19.04.2017.
 */
@RestController
@CrossOrigin
@RequestMapping("master")
public class MasterController {
    private final MasterRepository masterRepository;
    private final IUtils utils;

    @Autowired
    public MasterController(MasterRepository masterRepository, IUtils utils) {
        this.masterRepository = masterRepository;
        this.utils = utils;
    }


    @GetMapping("services")
    public ResponseEntity<Object> getMasterServices(@RequestHeader("Authorization") String token) {
        String email = utils.parsJwts(token);


        Master master = masterRepository.findByEmail(email);
        if (master == null) {
            return new ResponseEntity<>("there is no such master", HttpStatus.CONFLICT);
        }
        ArrayList<Services> services = master.getSerivce();
        return new ResponseEntity<>(services, HttpStatus.OK);
    }

    @PostMapping("service")
    public ResponseEntity<Object> setMasterServices(@RequestHeader("Authorization") String token, @RequestBody ArrayList<Services> services) {
        String email = utils.parsJwts(token);


        Master master = masterRepository.findByEmail(email);
        if (master == null) {
            return new ResponseEntity<>("there is no such master", HttpStatus.CONFLICT);
        }
        master.setSerivce(services);
        masterRepository.save(master);
        return new ResponseEntity<>("Master controller were updated", HttpStatus.OK);

    }

    @PutMapping("service")
    public ResponseEntity<Object> addMasterService(@RequestHeader("Authorization") String token, @RequestBody Services service) {
        String email = utils.parsJwts(token);


        Master master = masterRepository.findByEmail(email);
        if (master == null) {
            return new ResponseEntity<>("there is no such master", HttpStatus.CONFLICT);
        }
        master.addServise(service);
        masterRepository.save(master);
        return new ResponseEntity<>("Service was added", HttpStatus.OK);
    }


//    @GetMapping("address")
//    public ResponseEntity<Object> getMasterAddresses(@RequestHeader("Authorization") String token) {
//        String email = utils.parsJwts(token);
//
//
//        Master master = masterRepository.findByEmail(email);
//        if (master == null) {
//            return new ResponseEntity<>("there is no such master", HttpStatus.CONFLICT);
//        }
////        AddressTemp addresses = master.getAddresses();
//
//        return new ResponseEntity<>(master.getAddresses(), HttpStatus.OK);
//    }
//
//    @PutMapping("address")
//    public ResponseEntity<Object> setMasterAddresses(@RequestHeader("Authorization") String token, @RequestBody String addresses) {
//        String email = utils.parsJwts(token);
//
//        Master master = masterRepository.findByEmail(email);
//        if (master == null) {
//            return new ResponseEntity<>("there is no such master", HttpStatus.CONFLICT);
//        }
//        master.setAddresses(addresses);
//        masterRepository.save(master);
//        return new ResponseEntity<>("User addresses were updated", HttpStatus.OK);
//    }

    /**
     * Добавил Лёша 04.06.2017
     */
    @GetMapping("template")
    public ResponseEntity<Object> getWeekTemplate(@RequestHeader("Authorization") String token) {
        String email = utils.parsJwts(token);
        Master master = masterRepository.findByEmail(email);
        if (master == null) {
            return new ResponseEntity<>("there is no such master", HttpStatus.CONFLICT);
        }
        ArrayList<WeekDay> weekTemplate = master.getAddressMaster().getWeekTemplate();
        return new ResponseEntity<>(weekTemplate, HttpStatus.OK);
    }

    @PutMapping("template")
    public ResponseEntity<Object> setWeekTemplate(@RequestHeader("Authorization") String token, @RequestBody ArrayList<WeekDay> weekTemplate) {
        String email = utils.parsJwts(token);
        Master master = masterRepository.findByEmail(email);
        if (master == null) {
            return new ResponseEntity<>("there is no such master", HttpStatus.CONFLICT);
        }
        master.getAddressMaster().setWeekTemplate(weekTemplate);
        masterRepository.save(master);
        return new ResponseEntity<>("User week template was update", HttpStatus.OK);
    }

    @PutMapping("add_day")
    public ResponseEntity<Object> addCalendarDay(@RequestHeader("Authorization") String token, @RequestBody CalendarDay day) {
        String email = utils.parsJwts(token);
        Master master = masterRepository.findByEmail(email);
        if (master == null) {
            return new ResponseEntity<>("there is no such master", HttpStatus.CONFLICT);
        }
        master.getAddressMaster().getTimetableMap().put(day.getMyCalendar().toString(), day);
        masterRepository.save(master);
        return new ResponseEntity<>("Calendar Day was added", HttpStatus.OK);
    }

    @GetMapping("start")
    public ResponseEntity<Object> startMasterTimetable(@RequestHeader("Authorization") String token) {
        String email = utils.parsJwts(token);
        Master master = masterRepository.findByEmail(email);
        if (master == null) {
            return new ResponseEntity<>("there is no such master", HttpStatus.CONFLICT);
        }
        master.getAddressMaster().startMasterTrmplatr();
        masterRepository.save(master);
        TreeMap<String, CalendarDay> map = master.getAddressMaster().getTimetableMap();
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @PostMapping("free_time")
    public ResponseEntity<Object> freeTimeOnDate(@RequestHeader("Authorization") String token, @RequestBody DateAndDuration dateAndDuration) {
        String email = utils.parsJwts(token);
        Master master = masterRepository.findByEmail(email);
        if (master == null) {
            return new ResponseEntity<>("there is no such master", HttpStatus.CONFLICT);
        }
        String dateStr = dateAndDuration.getMyCalendar().toString();
        int duration = dateAndDuration.getDuration();
        Set set = master.getAddressMaster().getFreeTimeOnDate(dateStr, duration);
        return new ResponseEntity<>(set, HttpStatus.OK);
    }

    @PutMapping("record")
    public ResponseEntity<Object> addRecordForDay(@RequestHeader("Authorization") String token, @RequestBody DateAndRecord dateAndRecord) {
        String email = utils.parsJwts(token);
        Master master = masterRepository.findByEmail(email);
        if (master == null) {
            return new ResponseEntity<>("there is no such master", HttpStatus.CONFLICT);
        }
        LightCalendar lightCalendar = dateAndRecord.getMyCalendar();
        Record record = dateAndRecord.getRecord();
        master.getAddressMaster().getTimetableMap().get(lightCalendar.toString()).addRecord(record);
        masterRepository.save(master);
        return new ResponseEntity<>("Record was added", HttpStatus.OK);
    }



    @GetMapping("info")
    public ResponseEntity<Object> getMasterInfo(@RequestHeader("Authorization") String token) {
        String email = utils.parsJwts(token);
        Master master = masterRepository.findByEmail(email);
        if (master == null) {
            return new ResponseEntity<>("there is no such master", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(master, HttpStatus.OK);
    }

    @PutMapping("update")
    public ResponseEntity<Object> updateMuster(@RequestHeader("Authorization") String token, @RequestBody Master master) throws InterruptedException, ApiException, IOException {
        String email = utils.parsJwts(token);
        Master updatedMaster = masterRepository.findByEmail(email);

        if (updatedMaster == null) {
            return new ResponseEntity<>("master doesn't exist", HttpStatus.CONFLICT);
        }
        updatedMaster.setSerivce(master.getSerivce());
        updatedMaster.setPhoneNumber(master.getPhoneNumber());
        updatedMaster.setLastName(master.getLastName());
        updatedMaster.setName(master.getName());
        //добавил с
        updatedMaster.setAddressMaster(master.getAddressMaster());
        GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyCV43DMS9LJA9XaK10nY0I_sAGSxeDetlc");
        String adressStr = master.getAddressMaster().getAddress();
        GeocodingResult[] results = GeocodingApi.geocode(context, adressStr).await();
        Geometry geometry = results[0].geometry;
        updatedMaster.getAddressMaster().setPlaceId(results[0].placeId);
        updatedMaster.getAddressMaster().setLatitude(geometry.location.lat);
        updatedMaster.getAddressMaster().setLongitude(geometry.location.lng);
        //по
        updatedMaster.setLang(master.getLang());
        updatedMaster.setMasterType(master.getMasterType());
        masterRepository.save(updatedMaster);

        return new ResponseEntity<>("Master is updated", HttpStatus.OK);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseEntity<List<Master>> getAllMasters() {
        return new ResponseEntity<>(masterRepository.findAll(), HttpStatus.OK);
    }


}
