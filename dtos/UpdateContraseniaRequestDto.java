package edu.co.sena.worksite.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateContraseniaRequestDto {

    @NotNull
    private String contrasenia;
}
