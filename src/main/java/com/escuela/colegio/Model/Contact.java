package com.escuela.colegio.Model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Contact {
    @NotNull(message = "Name must not be blank")
    @Size(min=3, message = "Name must be at least 3 characters long")
    private String name;
    @NotNull(message = "Mobile number must not be blank")
    @Pattern(regexp="(^$|[0-9]{9})", message = "Invalid mobile phone number")
    private String mobileNum;
    @NotNull(message = "Email must not be blank")
    @Email(message = "Please provide a valid email address")
    private String email;
    @NotNull(message = "Subject must not be blank")
    @Size(min=5, message = "Subject must be at least 5 caracters long")
    private String subject;
    @NotNull(message = "Message must not be blank")
    @Size(min=10, message = "Message must be at least 10 caracters long")
    private String message;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileNum() {
        return mobileNum;
    }

    public void setMobileNum(String mobileNum) {
        this.mobileNum = mobileNum;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Contact{" +
                "name='" + name + '\'' +
                ", mobileNum='" + mobileNum + '\'' +
                ", email='" + email + '\'' +
                ", subject='" + subject + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
