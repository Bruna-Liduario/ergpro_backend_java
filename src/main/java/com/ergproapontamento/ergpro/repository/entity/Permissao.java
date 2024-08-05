package com.ergproapontamento.ergpro.repository.entity;

import com.ergproapontamento.ergpro.enuns.UserRole;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "permissao")
@Getter
@Setter
public class Permissao {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

	@Column(name = "httpmethod")
    private String httpMethod;
	
	@Column(name = "urlpattern")
    private String urlPattern;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private UserRole role;

}
