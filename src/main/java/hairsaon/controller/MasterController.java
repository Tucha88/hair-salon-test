package hairsaon.controller;


import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.Geometry;
import hairsaon.models.*;
import hairsaon.models.classes_for_master.Record;
import hairsaon.models.personal_models_for_schedule.Appointment;
import hairsaon.models.timetable.CalendarDay;
import hairsaon.models.timetable.WeekDay;
import hairsaon.myExtends.LightCalendar;
import hairsaon.repository.MasterRepository;
import hairsaon.utils.IUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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



    @GetMapping("services") /**есть в документации. дата обновления: 10.06.2017*/
    public ResponseEntity<Object> getMasterServices(@RequestHeader("Authorization") String token) {
        String email = utils.parsJwts(token);


        Master master = masterRepository.findByEmail(email);
        if (master == null) {
            return new ResponseEntity<>("there is no such master", HttpStatus.CONFLICT);
        }
        ArrayList<Services> services = master.getSerivce();
        return new ResponseEntity<>(services, HttpStatus.OK);
    }

    @PostMapping("service") /**есть в документации. дата обновления: 10.06.2017*/
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

    @PutMapping("service") /**есть в документации. дата обновления: 10.06.2017*/
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


    @GetMapping("address") /**есть в документации. дата обновления: 10.06.2017*/
    public ResponseEntity<Object> getMasterAddresses(@RequestHeader("Authorization") String token) {
        String email = utils.parsJwts(token);


        Master master = masterRepository.findByEmail(email);
        if (master == null) {
            return new ResponseEntity<>("there is no such master", HttpStatus.CONFLICT);
        }

        return new ResponseEntity<>(master.getAddressMaster().getAddress(), HttpStatus.OK);
    }

    @PutMapping("address") /**есть в документации. дата обновления: 10.06.2017*/
    public ResponseEntity<Object> setMasterAddresses(@RequestHeader("Authorization") String token, @RequestBody String addresses) {
        String email = utils.parsJwts(token);

        Master master = masterRepository.findByEmail(email);
        if (master == null) {
            return new ResponseEntity<>("there is no such master", HttpStatus.CONFLICT);
        }
        if(addresses!= null) {
            master.setAddresses(addresses);
            master.getAddressMaster().setAddress(addresses);
            GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyCV43DMS9LJA9XaK10nY0I_sAGSxeDetlc");
            GeocodingResult[] results;
            master.setAddresses(addresses);
            try {
                results = GeocodingApi.geocode(context, addresses).await();
                Geometry geometry = results[0].geometry;
                master.setPlaceId(results[0].placeId);
                master.setLatitude(geometry.location.lat);
                master.setLongitude(geometry.location.lng);
            } catch (Exception e) {

                master.setPlaceId(null);
                master.setLatitude(0);
                master.setLongitude(0);
            }
        }else {
            return new ResponseEntity<>("address was null", HttpStatus.CONFLICT);
        }
        masterRepository.save(master);
        return new ResponseEntity<>("User addresses were updated", HttpStatus.OK);
    }

    @GetMapping("info") /**есть в документации. дата обновления: 10.06.2017*/
    public ResponseEntity<Object> getMasterInfo(@RequestHeader("Authorization") String token) {
        String email = utils.parsJwts(token);
        Master master = masterRepository.findByEmail(email);
        if (master == null) {
            return new ResponseEntity<>("there is no such master", HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(master, HttpStatus.OK);
    }

    @PutMapping("update") /**есть в документации. дата обновления: 10.06.2017*/
    public ResponseEntity<Object> updateMuster(@RequestHeader("Authorization") String token, @RequestBody Master master) {
        String email = utils.parsJwts(token);
        Master updatedMaster = masterRepository.findByEmail(email);

        if (updatedMaster == null) {
            return new ResponseEntity<>("master doesn't exist", HttpStatus.CONFLICT);
        }
        if (master.getPhoneNumber() != null) {
            updatedMaster.setPhoneNumber(master.getPhoneNumber());
        }
        if (master.getLastName() != null) {
            updatedMaster.setLastName(master.getLastName());
        }
        if (master.getName() != null) {
            updatedMaster.setName(master.getName());
        }
        if (master.getLang() != null) {
            updatedMaster.setLang(master.getLang());
        }
        if (master.getMasterType() != null) {
            updatedMaster.setMasterType(master.getMasterType());
        }
        /*if (master.getSerivce() != null) {
            updatedMaster.setSerivce(master.getSerivce());
        }*/
        /*GeoApiContext context = new GeoApiContext().setApiKey("AIzaSyCV43DMS9LJA9XaK10nY0I_sAGSxeDetlc");
        String adressStr = master.getAddresses();

        GeocodingResult[] results = new GeocodingResult[0];
        if (adressStr != null) {
            updatedMaster.setAddresses(adressStr);
            try {
                results = GeocodingApi.geocode(context, adressStr).await();
                Geometry geometry = results[0].geometry;
                updatedMaster.setPlaceId(results[0].placeId);
                updatedMaster.setLatitude(geometry.location.lat);
                updatedMaster.setLongitude(geometry.location.lng);
                updatedMaster.getAddressMaster().setAddress(adressStr);
            } catch (Exception e) {
                updatedMaster.setPlaceId(null);
                updatedMaster.setLatitude(0);
                updatedMaster.setLongitude(0);
            }
        }*/


        masterRepository.save(updatedMaster);

        return new ResponseEntity<>("Master is updated", HttpStatus.OK);
    }

    @RequestMapping(value = "list", method = RequestMethod.GET)
    public ResponseEntity<List<Master>> getAllMasters() {
        return new ResponseEntity<>(masterRepository.findAll(), HttpStatus.OK);
    }

   /* @GetMapping("appointment")
    public ResponseEntity<Object> getAppointments(@RequestHeader("Authorization") String token){
        Master master = masterRepository.findByEmail(utils.parsJwts(token));
        AppointmentArray appointmentArray = new AppointmentArray();
        appointmentArray.setRecords((ArrayList<Appointment>) master.getRecords());
        return new ResponseEntity<>(appointmentArray,HttpStatus.OK);
    }*/

    /**
     * Добавил Лёша 04.06.2017
     */
    @GetMapping("template") /**есть в документации. дата обновления: 10.06.2017*/
    public ResponseEntity<Object> getWeekTemplate(@RequestHeader("Authorization") String token) {
        String email = utils.parsJwts(token);
        Master master = masterRepository.findByEmail(email);
        if (master == null) {
            return new ResponseEntity<>("there is no such master", HttpStatus.CONFLICT);
        }
        ArrayList<WeekDay> weekTemplate = master.getAddressMaster().getWeekTemplate();
        return new ResponseEntity<>(weekTemplate, HttpStatus.OK);
    }

    @PutMapping("template") /**есть в документации. дата обновления: 10.06.2017*/
    public ResponseEntity<Object> setWeekTemplate(@RequestHeader("Authorization") String token, @RequestBody ArrayList<WeekDay> weekTemplate) {
        String email = utils.parsJwts(token);
        Master master = masterRepository.findByEmail(email);
        if (master == null) {
            return new ResponseEntity<>("there is no such master", HttpStatus.CONFLICT);
        }
        if (weekTemplate.size() == 0){
            return new ResponseEntity<>("User week template not was update", HttpStatus.CONFLICT);
        }
        master.getAddressMaster().setWeekTemplate(weekTemplate);
        masterRepository.save(master);
        return new ResponseEntity<>("User week template was update", HttpStatus.OK);
    }

    @PutMapping("add_day") /**есть в документации. дата обновления: 10.06.2017*/
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

    //TODO: Нужно добавить boolean в класс Master чтоб этот метод можно было вызвать всего один раз

    @GetMapping("start") /**есть в документации. дата обновления: 10.06.2017*/
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

    @GetMapping("all_timetable") /**есть в документации. дата обновления: 10.06.2017*/
    public ResponseEntity<Object> getAllTimetable (@RequestHeader("Authorization") String token){
        String email = utils.parsJwts(token);
        Master master = masterRepository.findByEmail(email);
        if (master == null) {
            return new ResponseEntity<>("there is no such master", HttpStatus.CONFLICT);
        }
        Map<String,CalendarDay> map = master.getAddressMaster().getTimetableMap();
        /*ArrayList<CalendarDay> arrTimetable = new ArrayList<CalendarDay>();
        arrTimetable.containsAll(map.values());*/
        Collection collection = map.values();
        return new ResponseEntity<>(collection, HttpStatus.OK);
    }

    @PostMapping("day_records") /**есть в документации. дата обновления: 10.06.2017*/
    public ResponseEntity<Object> getRecordForDay(@RequestHeader("Authorization") String token, @RequestBody LightCalendar lightCalendar) {
        String email = utils.parsJwts(token);
        Master master = masterRepository.findByEmail(email);
        if (master == null) {
            return new ResponseEntity<>("there is no such master", HttpStatus.CONFLICT);
        }
        if(master.getAddressMaster().getTimetableMap().get(lightCalendar.toString()).isWorking()) {
            ArrayList<Record> records = master.getAddressMaster().getTimetableMap().get(lightCalendar.toString()).getRecords();
            return new ResponseEntity<>(records, HttpStatus.OK);
        }else {
            return new ResponseEntity<>(false, HttpStatus.OK);
        }
    }

}
