package group8.bloodbank.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Getter
@Setter
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
    @NotNull(message = "Name cannot be null")
    private String name;
    @Column
    @NotNull(message = "Surname cannot be null")
    private String surname;
    @Column
    @NotNull(message = "Password cannot be null")
    private String password;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @NotNull(message = "Password cannot be null")
    private Address address;
    @Column
    @Size(max = 13, message = "Jmbg should contains 13 numbers")
    private String jmbg;
    @Column
    @NotNull(message = "Password cannot be null")
    @Email(regexp = ".+[@].+[\\\\.].+", message = "Email should be valid")
    //@CustomConstraint(message = "Email should be unique")
    private String email;
    @Column
    @NotNull(message = "Password cannot be null")
    private String occupation;
    @Column
    @NotNull(message = "Password cannot be null")
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

}