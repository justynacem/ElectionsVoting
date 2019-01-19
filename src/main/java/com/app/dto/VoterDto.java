package com.app.dto;

import com.app.model.Education;
import com.app.model.Gender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class VoterDto {

    private Long id;
    private Gender gender;
    private Education education;
    private Integer age;
    private ConstituencyDto constituencyDto;
}