package com.escuela.colegio.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "address_id")
    private int addressId;

    @NotBlank(message = "Address1 must not be blank")
    @Size(min = 5, message = "Address1 must be at least 5 characters long")
    private String address1;

    private String address2;

    @NotBlank(message = "State must not be blank")
    @Size(min = 5, message = "State must be at least 5 characters long")
    private String state;

    @NotBlank(message = "Zip Code must be blank")
    @Pattern(regexp = "(^$|[0-9]{5})", message = "Zip Code must be 5 digits")
    @Column(name = "zip_code")
    private int zipCode;

}
