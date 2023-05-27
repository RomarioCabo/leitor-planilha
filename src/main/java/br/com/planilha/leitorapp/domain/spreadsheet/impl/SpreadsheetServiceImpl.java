package br.com.planilha.leitorapp.domain.spreadsheet.impl;

import br.com.planilha.leitorapp.domain.city.City;
import br.com.planilha.leitorapp.domain.spreadsheet.SpreadsheetService;
import br.com.planilha.leitorapp.domain.spreadsheet.exception.SpreadsheetException;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class SpreadsheetServiceImpl implements SpreadsheetService {

    @Override
    public List<City> converter(MultipartFile spreadsheet) {
        try (InputStream inputStream = spreadsheet.getInputStream();
             Workbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0);

            List<City> cities = new ArrayList<>();

            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
                Row row = sheet.getRow(rowIndex);

                Cell idCidadeCell = row.getCell(0);
                Cell nomeCell = row.getCell(1);
                Cell nomeAbrevCell = row.getCell(2);
                Cell latitudeCell = row.getCell(3);
                Cell longitudeCell = row.getCell(4);

                if (idCidadeCell != null && nomeCell != null && nomeAbrevCell != null
                        && latitudeCell != null && longitudeCell != null) {

                    cities.add(buildCity(
                            getCellValueAsLong(idCidadeCell),
                            getCellValueAsString(nomeCell),
                            getCellValueAsString(nomeAbrevCell),
                            getCellValueAsString(latitudeCell),
                            getCellValueAsString(longitudeCell)
                    ));
                }
            }

            log.info("Conversão da planilha para Objeto com sucesso. Total {}", cities.size());
            return cities;
        } catch (IOException e) {
            throw new SpreadsheetException();
        }
    }

    private Long getCellValueAsLong(Cell cell) {
        return (long) cell.getNumericCellValue();
    }

    private String getCellValueAsString(Cell cell) {
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue();
            case NUMERIC -> String.valueOf(cell.getNumericCellValue());
            default -> "";
        };
    }

    private City buildCity(Long idCityWorksheet, String name, String shortName, String latitude, String longitude) {
        City city = City.builder()
                .idCityWorksheet(idCityWorksheet)
                .name(name)
                .shortName(shortName)
                .latitude(latitude)
                .longitude(longitude)
                .build();

        log.info("Cidade: {}", city);

        return city;
    }
}
