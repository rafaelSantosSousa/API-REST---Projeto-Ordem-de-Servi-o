package com.rafael.os.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Getter
@Setter
@Table(name = "Tecnico")
public class Tecnico extends Pessoa implements Serializable {
	private static final long serialVersionUID = 1l;
	@JsonIgnore
	@OneToMany(mappedBy = "tecnico")
	private List<OS> list = new ArrayList<>();

}
