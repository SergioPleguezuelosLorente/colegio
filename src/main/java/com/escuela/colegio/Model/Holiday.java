package com.escuela.colegio.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name="holidays")
public class Holiday extends BaseEntity {

    @Id
    @Column(name="day")
    private String day;

    @Column(name="reason")
    private String reason;

    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private Type type;


    public enum Type {
        Festival, Federal
    }

    public Holiday() {
    }

    public Holiday(LocalDateTime createdAt, String createdBy, LocalDateTime updatedAt, String updatedBy, String day, String reason, Type type) {
        super(createdAt, createdBy, updatedAt, updatedBy);
        this.day = day;
        this.reason = reason;
        this.type = type;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public String getDay() {
        return day;
    }

    public String getReason() {
        return reason;
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Holiday holiday = (Holiday) o;
        return Objects.equals(day, holiday.day) && Objects.equals(reason, holiday.reason) && type == holiday.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), day, reason, type);
    }

    @Override
    public String toString() {
        return "Holiday{" +
                "day='" + day + '\'' +
                ", reason='" + reason + '\'' +
                ", type=" + type +
                '}';
    }
}
