package com.example.onlinebankingsystem.domain.base;

import javax.persistence.*;

@MappedSuperclass
public class BaseEntity {

    private int id;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
