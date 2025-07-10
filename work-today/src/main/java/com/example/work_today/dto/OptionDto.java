package com.example.work_today.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class OptionDto {
    @Schema(hidden = true)
    private Long id;
    public String option;
}
