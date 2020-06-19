package singispace.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import singispace.service.AnalyticsService;
import singispace.service.ThemesService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    @Autowired
    private AnalyticsService analyticsService;

    @GetMapping("/SocialGroupCount")
    public ResponseEntity<Iterable<Number>> countAllThemes(){
        return new ResponseEntity<>(this.analyticsService.getSocialGroupCount(), HttpStatus.OK);
    }

    @GetMapping("/UserRoleCount")
    public ResponseEntity<Iterable<Number>> countAllUserRoles(){
        return new ResponseEntity<>(this.analyticsService.countAllUserRecords(), HttpStatus.OK);
    }


}
