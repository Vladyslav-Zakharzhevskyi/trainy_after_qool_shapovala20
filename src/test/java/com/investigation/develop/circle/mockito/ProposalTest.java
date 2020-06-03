package com.investigation.develop.circle.mockito;

import com.investigation.develop.circle.entity.Person;
import com.investigation.develop.circle.quartz.job.LeaveProposalNotificationJob;
import com.investigation.develop.circle.repository.PersonRepository;
import com.investigation.develop.circle.service.emailsender.EMailSender;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ProposalTest {

    @Autowired
    private ApplicationContext context;

    @Autowired
    private PersonRepository personRepository;

    @InjectMocks
    private LeaveProposalNotificationJob notificationJob;

    @Mock
    private PersonRepository personRepositoryMock;
    @Mock
    private EMailSender mailSender;

    @Before
    public void setUp() {}

    @Test
    public void test_email_sends_to_person() throws JobExecutionException {

        when(personRepositoryMock.findPersonsWithOffers()).thenReturn(personRepository.findPersonsWithOffers());
        when(personRepositoryMock.findAll()).thenReturn(personRepository.findAll());

        List<Person> all = personRepository.findAll();
        all.removeAll(personRepository.findPersonsWithOffers());

        notificationJob.execute(null);
        verify(mailSender, atLeastOnce()).sendEmailLetter(any(Person.class), any(Map.class));;


    }


}
