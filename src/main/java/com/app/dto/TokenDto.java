package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TokenDto {
    private Long id;
    private String tokenValue;
    private LocalDateTime expirationDate;
    private VoterDto voterDto;
}
