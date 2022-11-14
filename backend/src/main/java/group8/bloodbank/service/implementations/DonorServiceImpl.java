package group8.bloodbank.service.implementations;
import group8.bloodbank.model.Donor;
import group8.bloodbank.model.User;
import group8.bloodbank.repository.DonorRepository;
import group8.bloodbank.repository.UserRepository;
import group8.bloodbank.service.interfaces.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class DonorServiceImpl implements DonorService {

    DonorRepository donorRepository;

    @Autowired
    public DonorServiceImpl(DonorRepository donorRepository) {
        this.donorRepository = donorRepository;
    }

    @Override
    public Donor saveDonor(Donor donor) {
        return donorRepository.save(donor);
    }

    @Override
    public List<Donor> getAll() {
        return donorRepository.findAll();
    }

}
