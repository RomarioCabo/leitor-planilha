package br.com.planilha.leitorapp.domain.spreadsheet;

import br.com.planilha.leitorapp.domain.city.CityResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface SpreadsheetService {

    List<CityResponse> converter(MultipartFile spreadsheet);
}
