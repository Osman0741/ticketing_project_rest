package com.cydeo.entity;

import com.cydeo.enums.Gender;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
//@Where(clause = "is_deleted=false")         // SELECT * FROM users WHERE id = 4 AND is_deleted = false;
public class User extends BaseEntity {

    private String firstName;
    private String lastName;

    @Column(unique = true, nullable = false)
    private String userName;

    @Column(nullable = false)
    private String passWord;

    private boolean enabled;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "role_id")
    @Cascade(org.hibernate.annotations.CascadeType.ALL )
    private Role role;

    @Enumerated(EnumType.STRING)
    private Gender gender;

}
