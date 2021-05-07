package com.springtechnicaltest.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@SuperBuilder
@Table(name = "player")
public class Player implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "login", unique = true, nullable = false)
    private String login;
}
