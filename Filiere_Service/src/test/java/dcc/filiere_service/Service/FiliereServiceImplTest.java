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
        RequestFiliereDTO requestFiliereDTO = new RequestFiliereDTO("DCC","Devops et Cloud");
        Filiere filiere_a_ajouter = new Filiere(null,"DCC","Devops et Cloud");
        Filiere filiere_saved = new Filiere(1,"DCC","Devops et Cloud");
        ResponseFiliereDTO response = new ResponseFiliereDTO(1,"DCC","Devops et Cloud");

        // comportement des methodes
        Mockito.when(filiereMapper.DTO_to_Filiere(requestFiliereDTO)).thenReturn(filiere_a_ajouter);
        Mockito.when(filiereRepository.save(filiere_a_ajouter)).thenReturn(filiere_saved);
        Mockito.when(filiereMapper.Filiere_To_DTO(filiere_saved)).thenReturn(response);
        // tester
        ResponseFiliereDTO result = filiereService.addFiliere(requestFiliereDTO);
        //vérifier
        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isEqualTo(response);

    }

    @Test
    void getAllFilieres() {
        List<Filiere>filieres = List.of(
                new Filiere(1,"DCC","Devops et Cloud"),
                new Filiere(2,"SMI","Math Info")
        );

        List<ResponseFiliereDTO> filiereDTOS = List.of(
                new ResponseFiliereDTO(1,"DCC","Devops et Cloud"),
                new ResponseFiliereDTO(2,"SMI","Math Info")
        );
        Mockito.when(filiereRepository.findAll()).thenReturn(filieres);
        Mockito.when(filiereMapper.Filiere_To_DTO(filieres.get(0))).thenReturn(filiereDTOS.get(0));
        Mockito.when(filiereMapper.Filiere_To_DTO(filieres.get(1))).thenReturn(filiereDTOS.get(1));

        List<ResponseFiliereDTO> result = filiereService.getAllFilieres();

        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isEqualTo(filiereDTOS);


    }

    @Test
    void getFiliereById() {
        Integer id=1;
        Filiere filiere_recup = new Filiere(1,"DCC","Devops et Cloud");
        ResponseFiliereDTO response = new ResponseFiliereDTO(1,"DCC","Devops et Cloud");

        Mockito.when(filiereRepository.findById(id)).thenReturn(Optional.of(filiere_recup));
        Mockito.when(filiereMapper.Filiere_To_DTO(filiere_recup)).thenReturn(response);

        ResponseFiliereDTO result = filiereService.getFiliereById(id);
        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isEqualTo(response);

    }

    @Test
    void deleteFiliere() {
        Integer id = 1;
        filiereService.deleteFiliere(id);
    }

    @Test
    void updateFiliere() {
        Integer id=1;
        RequestFiliereDTO requestFiliereDTO = new RequestFiliereDTO("DCC","DEVOPS ET CLOUD COMPUTING");
        Filiere NV_filiere = new Filiere(null,"DCC","DEVOPS ET CLOUD COMPUTING");
        Filiere filiere=new Filiere(1,"DCC","Devops et Cloud");
        Filiere filiere_saved= new Filiere(1,"DCC","DEVOPS ET CLOUD COMPUTING");
        ResponseFiliereDTO response = new ResponseFiliereDTO(1,"DCC","DEVOPS ET CLOUD COMPUTING");

        //Action
        Mockito.when(filiereMapper.DTO_to_Filiere(requestFiliereDTO)).thenReturn(NV_filiere);
        Mockito.when(filiereRepository.findById(id)).thenReturn(Optional.of(filiere));
        Mockito.when(filiereRepository.save(filiere)).thenReturn(filiere_saved);
        Mockito.when(filiereMapper.Filiere_To_DTO(filiere_saved)).thenReturn(response);

        //Tester
        ResponseFiliereDTO result = filiereService.updateFiliere(id, requestFiliereDTO);
        AssertionsForClassTypes.assertThat(result).isNotNull();
        AssertionsForClassTypes.assertThat(result).usingRecursiveComparison().isEqualTo(response);


    }
}