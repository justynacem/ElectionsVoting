package com.app.service;

import com.app.dto.MyModelMapper;
import com.app.exceptions.ExceptionCode;
import com.app.exceptions.MyException;
import com.app.model.Education;
import com.app.model.Gender;
import com.app.model.Voter;
import com.app.repository.VoterRepository;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.EvaluationUtils;

import javax.transaction.Transactional;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Transactional
public class ResultsService {
    private VoterRepository voterRepository;

    public ResultsService(VoterRepository voterRepository) {
        this.voterRepository = voterRepository;
    }

    public Map<Gender, Double> getGenderStatistics() {
        try {
            return  voterRepository.findAll()
                    .stream()
                    .collect(Collectors.groupingBy(Voter::getGender, Collectors.counting()))
                    .entrySet()
                    .stream()
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            v -> (double) v.getValue() / voterRepository.findAll().size() * 100
                    ));
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, "GET GENDER STATISTICS: " + e);
        }
    }

    public Map<Education, Double> getEducationStatistics() {
        try {
            return  voterRepository.findAll()
                    .stream()
                    .collect(Collectors.groupingBy(Voter::getEducation, Collectors.counting()))
                    .entrySet()
                    .stream()
                    .collect(Collectors.toMap(
                            Map.Entry::getKey,
                            v -> (double) v.getValue() / voterRepository.findAll().size() * 100
                    ));
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, "GET EDUCATION STATISTICS: " + e);
        }
    }

    public Double getAgeStatistics() {
        try {
            return  voterRepository.getAverageAge();
        } catch (Exception e) {
            throw new MyException(ExceptionCode.SERVICE, "GET AGE STATISTICS: " + e);
        }
    }
}
