package com.focusts.rtv.api.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "usuario")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cdusuario")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cdtipousuario")
    private UserType type;
    
    @NotNull
    @Size(min = 5, max = 100)
    @NotBlank
    @Column(name = "nmusuario")
    private String name;
    
    @JsonIgnore
    @Column(name = "dssenha")
    private String password;

    @Email
    @Column(name = "dsemail")
    private String email;
    
    @Column(name = "dtaniversario")
    private LocalDate bith;
    
    @Column(name = "stpermissao")
    private Long role;
    
    @Column(name = "dtiferias")
    private LocalDate startVacation;
    
    @Column(name = "dtfferias")
    private LocalDate endVacation;
    
    @Column(name = "stusuario")
    private Boolean active;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "sttptecnico")
    private TechnicalType technicalType;
    
    @Column(name = "stfila")
    private Boolean inQuee;

}
