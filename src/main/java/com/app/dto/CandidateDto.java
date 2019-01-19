package com.app.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CandidateDto {

    private Long id;
    private String name;
    private String surname;
    private Integer age;
    private MultipartFile file;
    private String photo;
    private PoliticalPartyDto politicalPartyDto;
    private ConstituencyDto constituencyDto;
}