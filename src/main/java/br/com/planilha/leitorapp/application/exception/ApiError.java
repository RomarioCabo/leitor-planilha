package br.com.planilha.leitorapp.application.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.List;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ApiError {
  private HttpStatus status;

  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
  private LocalDateTime timestamp;

  private List<String> reasons;

  public ApiError(HttpStatus status, List<String> reasons) {
    this.timestamp = LocalDateTime.now();
    this.status = status;
    this.reasons = reasons;
  }
}