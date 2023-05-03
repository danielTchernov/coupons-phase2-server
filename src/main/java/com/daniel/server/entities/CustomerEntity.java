package com.daniel.server.entities;

import javax.persistence.*;

@Entity
@Table(name = "customers")
public class CustomerEntity {

    @Id
    @GeneratedValue
    private long id;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "first_name" , nullable = true)
    private String firstName;

    @Column(name = "last_name" , nullable = true)
    private String lastName;

    @Column(name = "address" , nullable = true)
    private String address;

    @Column(name = "amount_of_children" , nullable = true)
    private Long amountOfChildren;


    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public CustomerEntity() {
    }

    public CustomerEntity(long id, String firstName, String lastName, String address, Long amountOfChildren) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.amountOfChildren = amountOfChildren;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getAmountOfChildren() {
        return amountOfChildren;
    }

    public void setAmountOfChildren(Long amountOfChildren) {
        this.amountOfChildren = amountOfChildren;
    }

    @Override
    public String toString() {
        return "CustomerEntity{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", address='" + address + '\'' +
                ", amountOfChildren=" + amountOfChildren +
                '}';
    }
}
