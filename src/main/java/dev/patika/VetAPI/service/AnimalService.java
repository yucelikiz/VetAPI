package dev.patika.VetAPI.service;

import dev.patika.VetAPI.dto.request.AnimalRequest;
import dev.patika.VetAPI.dto.response.AnimalResponse;
import dev.patika.VetAPI.entity.Animal;
import dev.patika.VetAPI.mapper.AnimalMapper;
import dev.patika.VetAPI.repository.AnimalRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AnimalService {
    private final AnimalRepo animalRepo;
    private final AnimalMapper animalMapper;

    public List<AnimalResponse> findAll(){return animalMapper.asOutput(animalRepo.findAll());}

    public AnimalResponse getById(Long id) {
        return animalMapper.asResponse(animalRepo.findById(id).orElseThrow(() -> new RuntimeException(id + "'li hayvan bulunamadı!")));
    }

    public List<AnimalResponse> getByName(String name) {
        return animalMapper.asOutput(animalRepo.findByName(name));
    }

    public List<AnimalResponse> findByCustomerId(Long customerId) {
        List<Animal> animals = animalRepo.findByCustomerId(customerId);
        return animalMapper.asOutput(animals);
    }

    public AnimalResponse create(AnimalRequest request) {
        Optional<Animal> isAnimalExist = animalRepo.findByNameAndSpeciesAndBreed(
                request.getName(),
                request.getSpecies(),
                request.getBreed()
        );

        if (isAnimalExist.isEmpty()) {
            Animal animalSaved = animalRepo.save(animalMapper.asEntity(request));
            return animalMapper.asResponse(animalSaved);
        }
        throw new RuntimeException("Bu hayvan daha önce veritabanına kaydedilmiştir!");
    }

    public AnimalResponse update(Long id, AnimalRequest request) {
        Optional<Animal> isAnimalExist = animalRepo.findById(id);
        Optional<Animal> isAnimalExistWithName = animalRepo.findByCustomerIdAndNameAndSpeciesAndBreed(
                request.getCustomerId(),
                request.getName(),
                request.getSpecies(),
                request.getBreed()
        );

        if (isAnimalExist.isEmpty()) {
            throw new RuntimeException(id + "id'li hayvan veritabanında bulunamadı!");
        }

        if (isAnimalExistWithName.isPresent() && !isAnimalExistWithName.get().getId().equals(id)) {
            throw new RuntimeException("Bu hayvan daha önce veritabanına kaydedilmiştir!");
        }

        Animal animal = isAnimalExist.get();
        animalMapper.update(animal, request);
        return animalMapper.asResponse((animalRepo.save(animal)));
    }

    public void deleteById(Long id) {
        Optional<Animal> isAnimalExist = animalRepo.findById(id);

        if (isAnimalExist.isPresent()) {
            animalRepo.delete(isAnimalExist.get());
        } else {
            throw new RuntimeException(id + "id'li hayvan veritabanında bulunamadı!");
        }
    }

    public List<AnimalResponse> findByCustomerName(String customerName) {
        return animalMapper.asOutput(animalRepo.findByCustomerName(customerName));
    }
}
