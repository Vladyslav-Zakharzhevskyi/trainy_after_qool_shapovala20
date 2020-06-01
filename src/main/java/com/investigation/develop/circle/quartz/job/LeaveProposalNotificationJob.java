package com.investigation.develop.circle.quartz.job;

import com.google.common.base.Strings;
import com.investigation.develop.circle.entity.Person;
import com.investigation.develop.circle.repository.PersonRepository;
import com.investigation.develop.circle.service.emailsender.EMailSender;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.google.common.collect.Maps.newHashMap;

@Component
public class LeaveProposalNotificationJob implements Job {

    private final Logger logger = LogManager.getLogger(LeaveProposalNotificationJob.class);

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    @Qualifier("writeProposalEmailLetter")
    private EMailSender eMailSender;


    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        logger.info("Starting execute LeaveProposalNotificationJob: ");
        // Persons who has shared proposal
        List<Person> personsWithOffers = personRepository.findPersonsWithOffers();
        List<String> personsEmailsWithOffers = personsWithOffers.stream().map(Person::getEmail).collect(Collectors.toList());


        getPersonsToSendNotification(persons -> {
            persons.stream()
                    .filter(person -> !personsEmailsWithOffers.contains(person.getEmail()))
                    .filter(person -> !Strings.isNullOrEmpty(person.getEmail()))
                    .forEach(person -> eMailSender.sendEmailLetter(person, newHashMap()));
        });
    }

    private void getPersonsToSendNotification(Consumer<Collection<Person>> personsToNotify) {
        final List<Person> allPersons = personRepository.findAll();

        Map<String, Person> emailByPerson = allPersons.stream()
                .filter(Person::getEmailConfirmed)
                .collect(Collectors.toMap(Person::getEmail, Function.identity(), (first, second) -> first));

        personsToNotify.accept(emailByPerson.values());
    }


}
