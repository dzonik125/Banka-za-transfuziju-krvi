package group8.bloodbank.model;
import net.bytebuddy.dynamic.loading.InjectionClassLoader;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name ="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column
    private UserType userType;

    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String password;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Address address;
    @Column
    private String jmbg;
    @Column
    private String email;
    @Column
    private String occupation;
    @Column
    private Gender gender;

    public User() {
        super();
    }

    public User(Long id, String name, String surname, String password, Address address, String jmbg, String email, String occupation, Gender gender, UserType userType) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.address = address;
        this.jmbg = jmbg;
        this.email = email;
        this.occupation = occupation;
        this.gender = gender;
        this.userType = userType;
    }

    public User(String name, String surname, String email, String password, String jmbg, Address address, String occupation, Gender gender, UserType userType) {
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.address = address;
        this.jmbg = jmbg;
        this.email = email;
        this.occupation = occupation;
        this.gender = gender;
        this.userType = userType;
    }


    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getJmbg() {
        return jmbg;
    }

    public void setJmbg(String jmbg) {
        this.jmbg = jmbg;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}