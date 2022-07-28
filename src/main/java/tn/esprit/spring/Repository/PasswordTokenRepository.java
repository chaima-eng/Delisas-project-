package tn.esprit.spring.Repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tn.esprit.spring.Entity.PasswordResetToken;
import tn.esprit.spring.Entity.Personnel;


@Repository
public interface PasswordTokenRepository extends JpaRepository<PasswordResetToken, Long> {

    PasswordResetToken findByToken(String token);

    PasswordResetToken findByUser(Personnel user);


}
