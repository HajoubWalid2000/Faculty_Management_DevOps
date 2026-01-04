package dcc.filiere_service.mappers;

import dcc.filiere_service.DTO.RequestFiliereDTO;
import dcc.filiere_service.DTO.ResponseFiliereDTO;
import dcc.filiere_service.Entites.Filiere;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FiliereMapperTest {

    FiliereMapper filiereMapper = new FiliereMapper();

    @Test
    void DTO_to_Filiere() {

        RequestFiliereDTO requestFiliereDTO= new RequestFiliereDTO("SMA","MATH Appliquee");
        Filiere filiere_attendu = new Filiere(null,"SMA","MATH Appliquee");

        Filiere result = filiereMapper.DTO_to_Filiere(requestFiliereDTO);
        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isEqualTo(filiere_attendu);
    }

    @Test
    void filiere_To_DTO() {
        Filiere filiere = new Filiere(1,"SMA","MATH Appliquee");
        ResponseFiliereDTO response_attendu = new ResponseFiliereDTO(1,"SMA","MATH Appliquee");

        ResponseFiliereDTO result = filiereMapper.Filiere_To_DTO(filiere);
        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isEqualTo(response_attendu);


    }
}