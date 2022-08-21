package com.example.application.data.generator;

import com.example.application.data.entity.Contact;
import com.example.application.data.entity.Genere;
import com.example.application.data.entity.Richiesta;
import com.example.application.data.entity.Status;
import com.example.application.data.repository.ContactRepository;
import com.example.application.data.repository.RichiesteRepository;
import com.example.application.data.repository.StatusRepository;
import com.vaadin.exampledata.DataType;
import com.vaadin.exampledata.ExampleDataGenerator;
import com.vaadin.flow.spring.annotation.SpringComponent;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@SpringComponent
public class DataGenerator {

    @Bean
    public CommandLineRunner loadData(ContactRepository contactRepository,
            StatusRepository statusRepository //,RichiesteRepository richiesteRepository
            ) {
    {
        return args -> {
            Logger logger = LoggerFactory.getLogger(getClass());
            if (contactRepository.count() != 0L) {
                logger.info("Using existing database");
                return;
            }
            int seed = 123;

            logger.info("Generating demo data");

            List<Status> statuses = statusRepository
                    .saveAll(Stream.of("Presa in carico", "Da confermare", "Cancellato", "Attesa servizi", "Confermato","Calendarizzato")
                            .map(Status::new).collect(Collectors.toList()));

                            /* 
            List<Richiesta> requests = richiesteRepository
                    .saveAll(Stream.of("Richiesta di Trasporto", "Richiesta Ricetta", "Richiesta visita medica", "Richiesta pasti", "Richiesta assistenza domiciliare","Richiesta visita di Routine")
                            .map(Richiesta::new).collect(Collectors.toList()));
            */

            logger.info("... generating 50 Contact entities...");
            ExampleDataGenerator<Contact> contactGenerator = new ExampleDataGenerator<>(Contact.class,
                    LocalDateTime.now());
            contactGenerator.setData(Contact::setFirstName, DataType.FIRST_NAME);
            contactGenerator.setData(Contact::setLastName, DataType.LAST_NAME);
            contactGenerator.setData(Contact::setEmail, DataType.EMAIL);
            contactGenerator.setData(Contact::setIDPamac, DataType.ID);
            contactGenerator.setData(Contact::setIndiceFragilitàFisica, DataType.NUMBER_UP_TO_100);
            contactGenerator.setData(Contact::setIndiceFragilitàSociale, DataType.NUMBER_UP_TO_100);
            contactGenerator.setData(Contact::setIndiceFragilitàPsicologica, DataType.NUMBER_UP_TO_100);
            contactGenerator.setData(Contact::setAddress, DataType.ADDRESS);

            Random r = new Random(seed);
            List<Contact> contacts = contactGenerator.create(50, seed).stream().map(contact -> {
                contact.setStatus(statuses.get(r.nextInt(statuses.size())));
                //contact.setRichiesta(requests.get(r.nextInt(requests.size())));

                if(Math.random()>0.5){contact.setGenere(Genere.F);}
                return contact;
            }).collect(Collectors.toList());

            contactRepository.saveAll(contacts);

            logger.info("Generated demo data");
        };
    }
  }
}
