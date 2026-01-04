package dcc.filiere_service.mappers;

import dcc.filiere_service.DTO.RequestFiliereDTO;
import dcc.filiere_service.DTO.ResponseFiliereDTO;
import dcc.filiere_service.Entites.Filiere;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class FiliereMapperTest {


    private FiliereMapper filiereMapper = new FiliereMapper();
    @Test
    void DTO_to_Filiere() {
        RequestFiliereDTO requestFiliereDTO = new RequestFiliereDTO("SMI","MATH INFO");
        Filiere filiere = new Filiere(null,"SMI","MATH INFO");

        Filiere result = filiereMapper.DTO_to_Filiere(requestFiliereDTO);
        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isEqualTo(filiere);


    }

    @Test
    void filiere_To_DTO() {

        Filiere filiere = new Filiere(null,"SMI","MATH INFO");
        ResponseFiliereDTO responseFiliereDTO = new ResponseFiliereDTO (null,"SMI","MATH INFO");

        ResponseFiliereDTO result = filiereMapper.Filiere_To_DTO(filiere);
        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isEqualTo(responseFiliereDTO);


    }
}