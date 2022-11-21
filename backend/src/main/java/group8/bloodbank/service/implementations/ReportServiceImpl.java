package group8.bloodbank.service.implementations;

import group8.bloodbank.model.Report;
import group8.bloodbank.repository.ReportRepository;
import group8.bloodbank.service.interfaces.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ReportServiceImpl implements ReportService {

    private ReportRepository repository;

    @Autowired
    public ReportServiceImpl(ReportRepository repository){
        this.repository = repository;
    }


    @Override
    public Report saveReport(Report report) {
        return repository.save(report);
    }

    @Override
    public List<Report> getAll() {
        return repository.findAll();
    }
}
