package group8.bloodbank.model;

import lombok.AllArgsConstructor;

import javax.persistence.Entity;

@Entity
@AllArgsConstructor
public class Admin extends User {

    public Admin(Long id, String name, String surname, String password, Address address, String jmbg, String email, String occupation, Gender gender) {
        super(id, name, surname, password, address, jmbg, email, occupation, gender, UserType.SYSTEM_ADMINISTRATOR);
    }

}