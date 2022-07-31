/**
 * 
 */
package tn.esprit.spring.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.esprit.spring.Entity.Fournisseur;
import tn.esprit.spring.Entity.Personnel;

/**
 * @author hamza
 *
 */
public interface PersonnelRepository extends JpaRepository<Personnel, Integer> {

	Personnel findByuserName (String userName);


}
