package group8.bloodbank.controller;

import group8.bloodbank.model.BloodBank;
import group8.bloodbank.model.Report;
import group8.bloodbank.service.interfaces.BloodBankService;
import group8.bloodbank.service.interfaces.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reports")
@CrossOrigin(origins = "http://localhost:4200")
public class ReportController {

    private ReportService reportService;
    private BloodBankService bloodBankService;

    @Autowired
    public ReportController(ReportService reportService, BloodBankService bankService){
        this.reportService = reportService;
        this.bloodBankService = bankService;
    }

    @GetMapping(value = "/sendReports")
    @ResponseBody
    public ResponseEntity<Boolean> sendReports(@RequestParam(value = "url") String url, @RequestHeader(value = "apiKey") String apiKey){
        BloodBank b = bloodBankService.getByApiKey(apiKey);
        if(b == null){
            return new ResponseEntity<Boolean>(HttpStatus.UNAUTHORIZED);
        }
        Report report = new Report(url, b);
        Report ret = reportService.saveReport(report);
        if(ret != null) {
            return new ResponseEntity<Boolean>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<Boolean>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(value = "/getReports")
    @ResponseBody
    public List<Report> getAll() { return reportService.getAll(); }
}
