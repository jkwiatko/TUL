package com.j4c08.uwatch.data.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "contact")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String number;
    private String name;
    private String lastName;

    @OneToMany
    private List<CorrespondenceAddress> correspondenceAddresses;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<CorrespondenceAddress> getCorrespondenceAddresses() {
        return correspondenceAddresses;
    }

    public void setCorrespondenceAddresses(List<CorrespondenceAddress> correspondenceAddresses) {
        this.correspondenceAddresses = correspondenceAddresses;
    }
}
