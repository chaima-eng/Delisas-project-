package tn.esprit.spring.Repository;



import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.Entity.Colis;
import java.lang.String;

@Repository
public interface IntColisRepo  extends JpaRepository<Colis,Integer> {
	
	List<Colis> findByPickupsIdPickup(int idPickup);
	
	  Colis findByCodeabar(String codeabar);
	 //Colis findByCode_a_bar(String code_a_bar);
	//List<Colis> findByPickupId(int idPickup);
	@Query("SELECT SUM(c.total) FROM Colis c INNER JOIN Pickup p ON c.idColis = p.idPickup")  
    public float Sommepickup(@Param ("idPickup") int idPickup);
	
	@Query(value="SELECT coliss_id_colis FROM colis_pickups where pickups_id_pickup = :idPickup",nativeQuery=true)  
    public List Listids(@Param ("idPickup") int idPickup);
	
	@Query(value="SELECT total FROM colis where id_colis = :idColis",nativeQuery=true) 
	float findByTotale(@Param ("idColis")int idColis);
	
	
	@Query("SELECT SUM(c.total) FROM Colis c INNER JOIN Runsheet p ON c.idColis = p.idRunsheet")  
    public float Sommerunsheet(@Param ("idRunsheet") int idRunsheet);
	
	@Query(value="SELECT coliss_id_colis FROM colis_runsheets where runsheets_id_runsheet = :idRunsheet",nativeQuery=true)  
    public List Listid(@Param ("idRunsheet") int idRunsheet);
	
	@Query(value="SELECT total FROM colis where codeabar = :codeabar",nativeQuery=true) 
	float findByTotal(@Param ("codeabar")String codeabar);
	
	
}
