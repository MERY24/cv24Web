package fr.univrouen.xmlProject.Services;


import fr.univrouen.xmlProject.Dtos.CvDto;
import fr.univrouen.xmlProject.Entities.Cv;

import fr.univrouen.xmlProject.Exceptions.CvNotFound;
import fr.univrouen.xmlProject.Exceptions.DuplicateCvException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CvService {
    Cv saveCv(String cvDto)throws Exception;
    void updateCv(Long id , CvDto cvDto);
    boolean deleteCv(Long id);

    List<Cv> findAllCv();

    Cv findById(Long id) throws Exception;
    String cvAsXml(Long id)throws Exception;
    String CvsXml();
    List<CvDto> CvsHtml();

}