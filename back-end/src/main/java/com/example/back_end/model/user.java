package com.example.back_end.model;

import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Table(name = "students")
public class user {
    @Id
    private Long id;
    private String username;
}
