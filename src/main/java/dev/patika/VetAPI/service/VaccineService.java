package dev.patika.VetAPI.service;

import dev.patika.VetAPI.dto.request.VaccineRequest;
import dev.patika.VetAPI.dto.response.AnimalResponse;
import dev.patika.VetAPI.dto.response.VaccineResponse;
import dev.patika.VetAPI.entity.Animal;
import dev.patika.VetAPI.entity.Vaccine;
import dev.patika.VetAPI.mapper.AnimalMapper;
import dev.patika.VetAPI.mapper.VaccineMapper;
import dev.patika.VetAPI.repository.VaccineRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class VaccineService {
    private final VaccineRepo vaccineRepo;
    private final VaccineMapper vaccineMapper;
    private final AnimalMapper animalMapper;

    public List<VaccineResponse> findAll() {return vaccineMapper.asOutput(vaccineRepo.findAll());}


    public VaccineResponse getById(Long id) {
        return vaccineMapper.asResponse(vaccineRepo.findById(id)
                .orElseThrow(() -> new RuntimeException(id + "id'li aşı bulunamadı!")));
    }

    public List<VaccineResponse> getByName(String name) {
        List<Vaccine> vaccineList = vaccineRepo.findByName(name);
        return vaccineMapper.asOutput(vaccineList);
    }

    public List<VaccineResponse> getByAnimalId(Long animalId) {
        List<Vaccine> vaccineList = vaccineRepo.findByAnimalId(animalId);
        return vaccineMapper.asOutput(vaccineList);
    }

    public List<AnimalResponse> findAnimalsByVaccineProtectionEndDateBetween(LocalDate startDate, LocalDate endDate) {
        List<Animal> animals = vaccineRepo.findAnimalsByVaccineProtectionEndDateBetween(startDate, endDate);
        return animalMapper.asOutput(animals);
    }

    public VaccineResponse create(VaccineRequest request) {
        //  Check if there is another vaccine with the same code, name and animalId
        Optional<Vaccine> isVaccineExist = Optional.ofNullable(vaccineRepo.findByCode(request.getCode()));

        if (isVaccineExist.isEmpty()) {
            // Check if a vaccine with the same name, code, and associated animal ID
            // has protection end date before the current date
            Optional<Vaccine> vaccineWithSameDetails = vaccineRepo.findByNameAndCodeAndAnimal_Id(
                    request.getName(),
                    request.getCode(),
                    request.getAnimalId()
            );
            if (vaccineWithSameDetails.isPresent() && vaccineWithSameDetails.get().getProtectionEndDate().isAfter(LocalDate.now())) {
                throw new RuntimeException("Bu aşının koruyuculuk tarihi henüz bitmedi!");
            }
            Vaccine savedVaccine = vaccineRepo.save(vaccineMapper.asEntity(request));
            return vaccineMapper.asResponse(savedVaccine);
        }
        throw new RuntimeException("Kod numaralı aşı daha önce kaydedilmiş!");
    }

    public VaccineResponse update(Long id, VaccineRequest request) {
        Optional<Vaccine> isVaccineExist = vaccineRepo.findById(id);
        Optional<Vaccine> existingVaccineWithSameCode = Optional.ofNullable(vaccineRepo.findByCode(
                request.getCode()
        ));

        if (isVaccineExist.isEmpty()) {
            throw new RuntimeException(id + "'li aşı bulunamadı!");
        }

        if (existingVaccineWithSameCode.isPresent() && !existingVaccineWithSameCode.get().getId().equals(id)) {
            throw new RuntimeException("Kod numaralı aşı daha önce kaydedilmiş!");
        }

        Vaccine vaccine = isVaccineExist.get();
        vaccineMapper.update(vaccine, request); // Use the vaccineMapper to update the existing Vaccine entity
        return vaccineMapper.asResponse((vaccineRepo.save(vaccine))); // Map the updated entity to the response
    }

    public void deleteById(Long id) {
        Optional<Vaccine> isVaccineExist = vaccineRepo.findById(id);

        if (isVaccineExist.isPresent()) {
            vaccineRepo.delete(isVaccineExist.get());
        } else {
            throw new RuntimeException(id + "'li aşı bulunamadı!");
        }
    }
}
