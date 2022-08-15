package tn.esprit.spring.Entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Hub implements Serializable {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id_Hub;
	private String Adresse_hub;

	@Enumerated(EnumType.STRING)
	   private Gouvernerat gouvernerat_hub;


	
	@ElementCollection(fetch = FetchType.EAGER)
	Collection<Gouvernerat> gouvernerat_list;
	
	
	//@Enumerated(EnumType.ORDINAL)
	//private List<Gouvernerat> gl= new ArrayList <Gouvernerat>(EnumSet.allOf(Gouvernerat.class));
	 // Set<Gouvernerat> gouv = EnumSet.allOf(Gouvernerat.class);

	
	
	/*public List<String> getGl() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setGl(List<String> gl2) {
		// TODO Auto-generated method stub
		
	}*/
	

	
	//List < Gouvernerat > gouvernerats = new ArrayList < > ();
	
	//private List<Gouvernerat> gouvernerats = Arrays.asList(Gouvernerat.values());
	
	//private List<Gouvernerat> gouvernerats = Collections.list(gouvernerat);
	
	//Gouvernerat[] array = Enumeration.
		//	List<Gouvernerat> list = Arrays.asList(array).values();
/*	List<Gouvernerat> enumNames = Stream.of(Enum.values().ToString())
            .map(Enum::name)
            .collect(Collectors.toList());*/
	
}
