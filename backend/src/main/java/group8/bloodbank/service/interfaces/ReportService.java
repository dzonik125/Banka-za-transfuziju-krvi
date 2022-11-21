package group8.bloodbank.service.interfaces;

import group8.bloodbank.model.Report;

import java.util.List;

public interface ReportService {

    public Report saveReport(Report report);

    public List<Report> getAll();
}
