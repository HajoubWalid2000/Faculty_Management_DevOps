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
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class FiliereRepositoryTest {

    @Autowired
    private FiliereRepository filiereRepository;


    @BeforeEach
    void setUp() {
        filiereRepository.save(new Filiere(null,"MIP_T","Math Info physique Test"));
        filiereRepository.save(new Filiere(null,"SMA_T","Math appliquee Test"));
    }

    @Test
    void shouldfindbycode(){
        String code= "MIP_T";
        Filiere filiere = new Filiere(null,"MIP_T","Math Info physique Test");

        Filiere Result = filiereRepository.findFiliereByCode(code);
        AssertionsForClassTypes.assertThat(Result).isNotNull();
        AssertionsForClassTypes.assertThat(Result).usingRecursiveComparison().
                ignoringFields("idFiliere").isEqualTo(filiere);
    }

    @Test
    void NoTshouldfindbycode(){
        String code= "yyy";

        Filiere Result = filiereRepository.findFiliereByCode(code);
        AssertionsForClassTypes.assertThat(Result).isNull();

    }

}