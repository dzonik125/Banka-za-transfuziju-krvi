package group8.bloodbank.service.implementations;

import group8.bloodbank.model.Address;
import group8.bloodbank.model.Appointment;
import group8.bloodbank.model.BloodBank;
import group8.bloodbank.model.BloodType;
import group8.bloodbank.repository.BloodBankRepository;
import group8.bloodbank.service.interfaces.BloodBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BloodBankServiceImpl implements BloodBankService {


    private BloodBankRepository bloodBankRepository;

    @Autowired
    public BloodBankServiceImpl(BloodBankRepository bloodBankRepository) {
        this.bloodBankRepository = bloodBankRepository;
        Map<BloodType, Double> map1 = new HashMap<>();
        map1.put(BloodType.Apos, 222.5);
        map1.put(BloodType.Aneg, 0.0);
        Map<BloodType, Double> map2 = new HashMap<>();
        map2.put(BloodType.Bneg, 0.0);
        map2.put(BloodType.Bpos, 0.0);
        Address a1 = new Address("Srbija", "Beograd", "Karadjordjeva", "12");
        Address a2 = new Address("Srbija", "Arandjelovac", "Ulica", "123");
        Address a3 = new Address("Srbija", "Krusevac", "Nikole Tesle", "43");
        Address a4 = new Address("Srbija", "Novi Sad", "Jovana Ducica", "32b");
        Address a5 = new Address("Srbija", "Subotica", "Bulevar Oslobodjenja", "15a");

        BloodBank b1 = new BloodBank(1l, "A banka", "", 3.4, map1, null, null
                , null, a1, null, "1", "https://firebasestorage.googleapis.com/v0/b/isapsw-6ef61.appspot.com/o/h1.jpg?alt=media&token=5268f3af-3bda-4014-b18e-3e69b57fb3ea");
        BloodBank b2 = new BloodBank(2l, "B banka", "", 1.2, map2, null, null
                , null, a2, null, "", "https://firebasestorage.googleapis.com/v0/b/isapsw-6ef61.appspot.com/o/h2.jpg?alt=media&token=3d2fca8e-7272-4dcc-bafc-231afce6eeac");
        BloodBank b3 = new BloodBank(3l, "C banka", "", 4.7, map2, null, null
                , null, a3, null, "", "https://firebasestorage.googleapis.com/v0/b/isapsw-6ef61.appspot.com/o/h3.jpg?alt=media&token=482263cb-3590-405f-9c1e-e1fcd46b5229");
        BloodBank b4 = new BloodBank(4l, "Neka druga", "", 1.7, map2, null, null
                , null, a4, null, "", "https://firebasestorage.googleapis.com/v0/b/isapsw-6ef61.appspot.com/o/h4.jpeg?alt=media&token=5b420fc6-e490-453c-afce-5d57241eda35");
        BloodBank b5 = new BloodBank(5l, "Crveni krst", "", 2.3, map2, null, null
                , null, a5, null, "", "https://firebasestorage.googleapis.com/v0/b/isapsw-6ef61.appspot.com/o/h5.jpg?alt=media&token=c1d1d5ca-2270-41b3-bb04-c3ffe4d0cc0a");
        Appointment a = new Appointment(1l, null, null, b5, null, 30);
        ArrayList<Appointment> al = new ArrayList<>();
        al.add(a);
        b5.setAppointment(al);
        bloodBankRepository.save(b1);
        bloodBankRepository.save(b2);
        bloodBankRepository.save(b3);
        bloodBankRepository.save(b4);
        bloodBankRepository.save(b5);
    }

    public List<BloodBank> getAll() {
        List<BloodBank> all = bloodBankRepository.findAll();
        return all;
    }


    public Optional<BloodBank> getById(Long id) {
        return bloodBankRepository.findById(id);
    }

    public void updateBloodBank(BloodBank b) {
        bloodBankRepository.save(b);
    }

    @Override
    public boolean getAmountOfBloodForType(BloodType type, Long id) {
        Optional<Double> b = bloodBankRepository.getAmountOfBloodForType(type, id);
        if(b.isPresent()) {
            System.out.println(b.get() > 0);
            return (b.get() > 0);
        }
        return false;
    }

    @Override
    public BloodBank getByApiKey(String apiKey) {
        return bloodBankRepository.getByApiKey(apiKey);

    }
    @Override
    public void setApiKey(String apiKey, Long id){
        bloodBankRepository.setApiKey(apiKey, id);
    }

    @Override
    public boolean CheckBloodAmount(BloodType type, double quant, Long id){
        Optional<Double> b = bloodBankRepository.CheckBloodAmount(type, id);
        if(b.isPresent()) {
            return (b.get() >= quant);
        }
        return false;
    }

    @Override
    public BloodBank saveBloodBank(BloodBank bloodBank) {
        return bloodBankRepository.save(bloodBank);
    }
}
