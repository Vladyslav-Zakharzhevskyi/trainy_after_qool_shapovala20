package com.example.demo.converter;

import com.example.demo.dto.JobDto;
import com.example.demo.entity.Job;

import java.util.List;

public interface JobDtoConverter {

    List<JobDto> convert(List<Job> jobs, boolean includeEmployees);

    JobDto convert(Job job, boolean includeEmployees);

}
