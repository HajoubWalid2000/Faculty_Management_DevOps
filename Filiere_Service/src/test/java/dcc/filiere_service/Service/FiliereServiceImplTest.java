package dcc.filiere_service.Service;

import dcc.filiere_service.DTO.RequestFiliereDTO;
import dcc.filiere_service.DTO.ResponseFiliereDTO;
import dcc.filiere_service.Entites.Filiere;
import dcc.filiere_service.Repository.FiliereRepository;
import dcc.filiere_service.mappers.FiliereMapper;
import org.assertj.core.api.AssertionsForClassTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.OngoingStubbing;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class FiliereServiceImplTest {

    @Mock
    private FiliereRepository filiereRepository;
    @Mock
    private FiliereMapper filiereMapper;

    @InjectMocks
    private FiliereServiceImpl filiereService;

    @Test
    void addFiliere() {
        // définie les données pour tester
        RequestFiliereDTO requestFiliereDTO = new RequestFiliereDTO("SMI","MATH Info");
        Filiere filiere = new Filiere(null,"SMI","MATH Info");

        Filiere filiereSaved =  new Filiere(1,"SMI","MATH Info");
        ResponseFiliereDTO responseFiliereDTO = new ResponseFiliereDTO(1,"SMI","MATH Info");

        Mockito.when(filiereMapper.DTO_to_Filiere(requestFiliereDTO)).thenReturn(filiere);
        Mockito.when(filiereRepository.save(filiere)).thenReturn(filiereSaved);
        Mockito.when(filiereMapper.Filiere_To_DTO(filiereSaved)).thenReturn(responseFiliereDTO);

        ResponseFiliereDTO result = filiereService.addFiliere(requestFiliereDTO);
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isEqualTo(responseFiliereDTO);
    }

    @Test
    void getAllFilieres() {
        // base données
        Filiere f1 = new Filiere(1, "SMI", "MATH Info");
        Filiere f2 = new Filiere(2, "SMP", "Physique");

        List<Filiere> filieres = List.of(f1, f2);

        ResponseFiliereDTO dto1 = new ResponseFiliereDTO(1, "SMI", "MATH Info");
        ResponseFiliereDTO dto2 = new ResponseFiliereDTO(2, "SMP", "Physique");

        List<ResponseFiliereDTO> filieresDtos = List.of(dto1, dto2);

        Mockito.when(filiereRepository.findAll()).thenReturn(filieres);

        Mockito.when(filiereMapper.Filiere_To_DTO(f1)).thenReturn(dto1);
        Mockito.when(filiereMapper.Filiere_To_DTO(f2)).thenReturn(dto2);


        List<ResponseFiliereDTO> result = filiereService.getAllFilieres();

        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isEqualTo(filieresDtos);

    }

    @Test
    void getFiliereById() {
        Integer id = 1;
        Filiere filiere = new Filiere(1, "SMI", "MATH Info");
        ResponseFiliereDTO filiereDTO = new ResponseFiliereDTO(1, "SMI", "MATH Info");

        Mockito.when(filiereRepository.findById(id)).thenReturn(Optional.of(filiere));
        Mockito.when(filiereMapper.Filiere_To_DTO(filiere)).thenReturn(filiereDTO);

        ResponseFiliereDTO result = filiereService.getFiliereById(id);
        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isEqualTo(filiereDTO);

    }

    @Test
    void deleteFiliere() {
        Integer id =1;
        filiereService.deleteFiliere(id);

    }

    @Test
    void updateFiliere() {

        Integer id =1;
        RequestFiliereDTO requestFiliereDTO = new RequestFiliereDTO("SMI","Math Informatique");
        Filiere nv_filire = new Filiere(null,"SMI","Math Informatique");

        Filiere filiere = new Filiere(1,"SMI","Math Info");

        Filiere filiere_modify = new Filiere(1,"SMI","Math Informatique");
        ResponseFiliereDTO filiereDTO = new ResponseFiliereDTO(1,"SMI","Math Informatique");

        Mockito.when(filiereMapper.DTO_to_Filiere(requestFiliereDTO)).thenReturn(nv_filire);
        Mockito.when(filiereRepository.findById(id)).thenReturn(Optional.of(filiere));
        Mockito.when(filiereRepository.save(filiere)).thenReturn(filiere_modify);
        Mockito.when(filiereMapper.Filiere_To_DTO(filiere_modify)).thenReturn(filiereDTO);

        ResponseFiliereDTO result = filiereService.updateFiliere(id, requestFiliereDTO);

        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isEqualTo(filiereDTO);



    }
}