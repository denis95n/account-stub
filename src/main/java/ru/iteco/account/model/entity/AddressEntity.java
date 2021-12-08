package ru.iteco.account.model.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "address", schema = "ad")
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "country")
    private String country;

    @Column(name = "city")
    private String city;

    @Column(name = "street")
    private String street;

    @Column(name = "home")
    private String home;

}
