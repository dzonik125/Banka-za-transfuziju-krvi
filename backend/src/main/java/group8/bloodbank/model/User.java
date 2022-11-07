package group8.bloodbank.model; /***********************************************************************
 * Module:  User.java
 * Author:  david
 * Purpose: Defines the Class User
 ***********************************************************************/

import javax.persistence.*;

/**
 * @pdOid 5e205961-864a-4202-b8e7-f5fc834d2be0
 */
@Entity
@Table(name ="users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

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
    private int penalty;
    @Column
    private Gender gender;

    public User() {
        super();
    }

    public User(Long id, String name, String surname, String password, Address address, String jmbg, String email, String occupation, int penalty, Gender gender) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.address = address;
        this.jmbg = jmbg;
        this.email = email;
        this.occupation = occupation;
        this.penalty = penalty;
        this.gender = gender;
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

    public int getPenalty() {
        return penalty;
    }

    public void setPenalty(int penalty) {
        this.penalty = penalty;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}