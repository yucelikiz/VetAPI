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
        return animalMapper.asOutput(animalRepo.findById(id).orElseThrow(() -> new RuntimeException(id + "'li hayvan bulunamadı!")));
    }

    public AnimalResponse getByName(String name) {
        return animalMapper.asOutput(animalRepo.findByName(name).orElseThrow(() -> new RuntimeException(name + "isimli hayvan bulunamadı!")));
    }

    public List<AnimalResponse> findByCustomerId(Long customerId) {
        List<Animal> animals = animalRepo.findByCustomerId(customerId);
        return animalMapper.asOutput(animals);
    }

    public AnimalResponse create(AnimalRequest animalRequest) {
        Optional<Animal> isAnimalExist = animalRepo.findByName(animalRequest.getName());

        if (isAnimalExist.isEmpty()) {
            Animal animalSaved = animalRepo.save(animalMapper.asEntity(animalRequest));
            return animalMapper.asOutput(animalSaved);
        }
        throw new RuntimeException("Bu hayvan daha önce veritabanına kaydedilmiştir!");
    }

    public AnimalResponse update(Long id, AnimalRequest animalRequest) {
        Optional<Animal> isAnimalExist = animalRepo.findById(id);

        if (isAnimalExist.isEmpty()) {
            throw new RuntimeException(id + "id'li hayvan veritabanında bulunamadı!");
        }

        Animal existingAnimal = isAnimalExist.get();
        animalMapper.update(existingAnimal, animalRequest);

        Animal updatedAnimal = animalRepo.save(existingAnimal);
        return animalMapper.asOutput(updatedAnimal);
    }

    public void deleteById(Long id) {
        Optional<Animal> isAnimalExist = animalRepo.findById(id);

        if (isAnimalExist.isPresent()) {
            animalRepo.delete(isAnimalExist.get());
        } else {
            throw new RuntimeException(id + "id'li hayvan veritabanında bulunamadı!");
        }
    }
}
