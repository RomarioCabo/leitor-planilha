package br.com.planilha.leitorapp.domain.spreadsheet;

import br.com.planilha.leitorapp.domain.city.City;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SpreadsheetService {

    List<City> converter(MultipartFile spreadsheet);
}
