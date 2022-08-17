package net.boniric.active.model;

import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@Entity
@Table(name = "users")
@NoArgsConstructor
public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Column(name = "id")
        private Long id;

        @Column(name = "password")
        private String password;

        @Column(name = "lastname")
        private String lastname;

        @Column(name = "firstname")
        private String firstname;

        @Column(name = "society")
        private String society;

        @Column(name = "address")
        private String address;

        @Column(name = "zip")
        private String zip;

        @Column(name = "city")
        private String city;

        @Column(name = "phone")
        private String phone;

        @Column(name = "email")
        private String email;

        @Column(name = "comments")
        private String comments;

        @Column(name = "role")
        private String role = "user";

        @Column(name = "sms")
        private String sms;


}
