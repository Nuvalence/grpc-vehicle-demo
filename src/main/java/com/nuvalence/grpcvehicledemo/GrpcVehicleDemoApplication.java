package com.nuvalence.grpcvehicledemo;

import com.nuvalence.grpcvehicledemo.model.Vehicle;
import com.nuvalence.grpcvehicledemo.repository.VehicleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.core.ReactiveMongoOperations;
import reactor.core.publisher.Flux;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

@SpringBootApplication
public class GrpcVehicleDemoApplication {

	public static void main(String[] args) { SpringApplication.run(GrpcVehicleDemoApplication.class, args); }

	@Bean
	CommandLineRunner init(ReactiveMongoOperations operations, VehicleRepository repository) {

		// load data in resources folder to web embedded mongodb
		String[] dataYears = {"2019", "2020", "2021", "2022"};

			return args -> {

				for (String year: dataYears) {

					Path path = Path.of("src/main/resources/fuel data/"+year+"data.csv");

					System.out.println("START STREAM");
					Flux<String> vehicleFlux = Flux.using(
							() -> Files.lines(path),
							Flux::fromStream,
							Stream::close
					);
					vehicleFlux
							.skip(1)
							.flatMap(sfItem -> {
								String[] lineSplit = sfItem.split(",");
								Vehicle vehicle = new Vehicle(
										null, lineSplit[2],lineSplit[3],lineSplit[1],
										Integer.parseInt(lineSplit[0]),lineSplit[8],lineSplit[22],lineSplit[28],
										Integer.parseInt(lineSplit[9]),Integer.parseInt(lineSplit[10]),Integer.parseInt(lineSplit[11]),
										Double.parseDouble(lineSplit[12]),Double.parseDouble(lineSplit[13]),Double.parseDouble(lineSplit[14])
								);
								return repository.save(vehicle);
							})
							.subscribe();
				}
			};
	}
}

// sample
//				Flux<Vehicle> vehicleFlux = Flux.just(v1,v2,v3)
//						.flatMap(vehicle -> repository.save(vehicle))
//						.flatMap(repository::save);//
//				vehicleFlux
//						.thenMany(repository.findAll())
//						.subscribe(System.out::println);

