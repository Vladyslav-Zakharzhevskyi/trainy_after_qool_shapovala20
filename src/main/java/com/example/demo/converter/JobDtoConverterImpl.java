package com.example.demo.converter;

import com.example.demo.dto.JobDto;
import com.example.demo.entity.Job;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class JobDtoConverterImpl implements JobDtoConverter {

    @Autowired
    private AddressDtoConverter addressDtoConverter;
    @Autowired
    private PersonDtoConverter personDtoConverter;

    @Override
    public List<JobDto> convert(List<Job> jobs, boolean includeEmployees) {
        return jobs.stream().map(job -> convert(job, includeEmployees)).collect(Collectors.toList());
    }

    @Override
    public JobDto convert(Job job, boolean includeEmployees) {
        return new JobDto(job.getId(),
                job.getPosition(),
                addressDtoConverter.convert(job.getAddress(), includeEmployees),
                includeEmployees ? personDtoConverter.convert(job.getEmployees()) : null,
                job.getStrategy());
    }
}
