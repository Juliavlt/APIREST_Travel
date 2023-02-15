package ifsp.travel.repository;

import ifsp.travel.model.AdditionalInfo;
import ifsp.travel.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/** Database repository interface responsible for handling operations provided for AppArsenal. */
@Repository
public interface AdditionalInfoRepository extends JpaRepository<AdditionalInfo, Long> {

    //  /** Exemplo de busca pelo campo username. */
    //  User findByUsername(String username);
    //
    //  /** Exemplo de query JPQL. */
    //  @Query("SELECT * FROM USER WHERE Username LIKE %?1")
    //  User findByUserBeginningWith(String username);
    //
    //  /** Exemplo de busca pelo campo username. */
    //  List<User> findByType(String type);
    //
    //  /** Exemplo de query JPQL. */
    //  @Query("SELECT * FROM USER WHERE Type LIKE %?1")
    //  User findByUserBeginningWithType(String type);

}
