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

    public List<VaccineResponse> findAll() {
        List<Vaccine> vaccineList = vaccineRepo.findAll();
        return vaccineMapper.asResponseList(vaccineList);
    }

    public VaccineResponse getById(Long id) {
        Vaccine vaccine = vaccineRepo.findById(id)
                .orElseThrow(() -> new RuntimeException(id + "id'li aşı bulunamadı!"));
        return vaccineMapper.asResponseWithAnimalId(vaccine);
    }

    public List<VaccineResponse> getByAnimalId(Long animalId) {
        List<Vaccine> vaccineList = vaccineRepo.findByAnimal_Id(animalId);
        return vaccineMapper.asResponseList(vaccineList);
    }

    public List<AnimalResponse> findAnimalsByVaccineProtectionEndDateBetween(LocalDate startDate, LocalDate endDate) {
        List<Animal> animals = vaccineRepo.findAnimalsByVaccineProtectionEndDateBetween(startDate, endDate);
        return animalMapper.asOutput(animals);
    }

    public VaccineResponse create(VaccineRequest vaccineRequest) {
        // Check if there is another vaccine with the same code, name and animalId
        Optional<Vaccine> isVaccineExist = Optional.ofNullable(vaccineRepo.findByCode(vaccineRequest.getCode()));

        if (isVaccineExist.isEmpty()) {
            // Check if a vaccine with the same name, code, and associated animal ID
            // has protection end date before the current date
            Optional<Vaccine> vaccineWithSameDetails = vaccineRepo.findByNameAndCodeAndAnimal_Id(
                    vaccineRequest.getName(),
                    vaccineRequest.getCode(),
                    vaccineRequest.getAnimalId()
            );
            if (vaccineWithSameDetails.isPresent() && vaccineWithSameDetails.get().getProtectionEndDate().isAfter(LocalDate.now())) {
                throw new RuntimeException("Bu aşının koruyuculuk tarihi henüz bitmedi!");
            }
            Vaccine savedVaccine = vaccineRepo.save(vaccineMapper.fromRequestWithAnimalId(vaccineRequest));
            return vaccineMapper.asResponseWithAnimalId(savedVaccine);
        }
        throw new RuntimeException("Kod numaralı aşı daha önce kaydedilmiş!");
    }

    public VaccineResponse update(Long id, VaccineRequest updatedVaccine) {
        Optional<Vaccine> optionalExistingVaccine = vaccineRepo.findById(id);

        if (optionalExistingVaccine.isEmpty()) {
            throw new RuntimeException(id + "'li aşı bulunamadı!");
        }
        Vaccine existingVaccine = optionalExistingVaccine.get();
        // Check if there is another vaccine with the same code
        Optional<Vaccine> existingVaccineWithSameCode = Optional.ofNullable(vaccineRepo.findByCode(updatedVaccine.getCode()));
        if (existingVaccineWithSameCode.isPresent() && !existingVaccineWithSameCode.get().getId().equals(id)) {
            throw new RuntimeException("Kod numaralı aşı daha önce kaydedilmiş!");
        }
        vaccineMapper.update(existingVaccine, updatedVaccine); // Use the vaccineMapper to update the existing Vaccine entity
        Vaccine updatedEntity = vaccineRepo.save(existingVaccine); // Save the updated vaccine entity
        return vaccineMapper.asResponseWithAnimalId(updatedEntity); // Map the updated entity to the response
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
