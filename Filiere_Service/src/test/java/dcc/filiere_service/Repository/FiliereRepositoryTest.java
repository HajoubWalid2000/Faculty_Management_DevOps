package dcc.filiere_service.Repository;

import dcc.filiere_service.Entites.Filiere;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class FiliereRepositoryTest {

    @Autowired
    private FiliereRepository filiereRepository;

    @BeforeEach
    void setUp(){
        filiereRepository.save(new Filiere(null,"SMA","Math appliquee"));
        filiereRepository.save(new Filiere(null,"DCC","DEVOPS et Cloud"));
    }

    @Test
    void findFiliereByCode(){
        String code = "DCC";
        Filiere filiere_attendu = new Filiere(null,"DCC","DEVOPS et Cloud");

        Filiere result = filiereRepository.findFiliereByCode(code);

        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().ignoringFields("idFiliere").isEqualTo(filiere_attendu);

    }

    @Test
    void Not_findFiliereByCode(){
        String code = "XXX";
        Filiere result = filiereRepository.findFiliereByCode(code);
        AssertionsForClassTypes.assertThat(result).isNull();

    }


}