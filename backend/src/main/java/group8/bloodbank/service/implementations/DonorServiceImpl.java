package group8.bloodbank.service.implementations;

import group8.bloodbank.model.Donor;
import group8.bloodbank.repository.DonorRepository;
import group8.bloodbank.service.interfaces.DonorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class DonorServiceImpl implements DonorService {

    DonorRepository donorRepository;

    @Autowired
    public DonorServiceImpl(DonorRepository donorRepository) {
        this.donorRepository = donorRepository;

 //       Donor donor1 = new Donor("Marko", "Matijevic", "matija123", new Address("Srbija", "Novi Sad", "Balzakova", "58"), "1233456789765", "matija123@mail.com", " ", Gender.MALE, 0, 0, Category.REGULAR, BloodType.Aneg );
  //      Donor donor2 = new Donor("Mina", "Maric", "mina123", new Address("Srbija", "Novi Sad", "Puskinova", "1"), "1233456789712", "mina123@mail.com", " ", Gender.FEMALE, 46, 0, Category.SILVER, BloodType.Bneg );
  //      Donor donor3 = new Donor("Nikola", "Milic", "nikola23", new Address("Srbija", "Novi Sad", "Presenova", "5"), "1233456789711", "niko123@mail.com", " ", Gender.MALE, 96, 0, Category.GOLD, BloodType.ABneg );

   //     donorRepository.save(donor1);
   //     donorRepository.save(donor2);
   //     donorRepository.save(donor3);

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
