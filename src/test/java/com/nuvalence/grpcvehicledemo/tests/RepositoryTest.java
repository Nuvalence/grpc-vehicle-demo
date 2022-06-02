package com.nuvalence.grpcvehicledemo.tests;

import com.nuvalence.grpcvehicledemo.model.Vehicle;
import com.nuvalence.grpcvehicledemo.repository.VehicleRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RepositoryTest {

    @Autowired
    VehicleRepository repository;

    @Test
    public void findAll() {
        var findAllFlux = repository.findAll();

        StepVerifier
                .create(findAllFlux)
                .expectNext()
                .assertNext(vehicle -> assertNotNull(vehicle.getId()))
                .expectNext()
                .assertNext(vehicle -> assertNotNull(vehicle.getId()))
                .thenCancel()
                .verify();
    }

    @Test
    public void findAllBYModelYear() {
        int modelYear = 2022;
        var findAllByModelYearFlux = repository.findAllByModelYear(modelYear);

        StepVerifier
                .create(findAllByModelYearFlux)
                .expectNext()
                .assertNext(vehicle -> assertEquals(modelYear, vehicle.getModelYear()))
                .expectNext()
                .assertNext(vehicle -> assertEquals(modelYear, vehicle.getModelYear()))
                .thenCancel()
                .verify();
    }

    @Test
    public void saveAndFindById() {
        Vehicle vehicle = new Vehicle("dummyId","kia","soul","Kia Manufacturer", 2022
                ,"transmission","transmissionDesc","driveDesc",1,2,3,1.1,2.2,3.3);

        repository.save(vehicle).block();
        var vehicleFlux = repository.findById("dummyId");

        StepVerifier
                .create(vehicleFlux)
                .assertNext(entity -> {
                    System.out.println(entity);
                    assertEquals("Kia Manufacturer", entity.getManufacturer());
                    assertNotNull(entity.getId());
                })
                .expectComplete()
                .verify();
    }

}
