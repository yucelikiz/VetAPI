package dev.patika.VetAPI.service;

import dev.patika.VetAPI.dto.request.AvailableDateRequest;
import dev.patika.VetAPI.dto.response.AvailableDateResponse;
import dev.patika.VetAPI.entity.AvailableDate;
import dev.patika.VetAPI.mapper.AvailableDateMapper;
import dev.patika.VetAPI.repository.AvailableDateRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AvailableDateService {
    private final AvailableDateRepo availableDateRepo;
    private final AvailableDateMapper availableDateMapper;

    public List<AvailableDateResponse> findAll() {
        return availableDateMapper.asResponseList(availableDateRepo.findAll());
    }

    public AvailableDateResponse getById(Long id) {
        AvailableDate availableDate = availableDateRepo.findById(id)
                .orElseThrow(() -> new RuntimeException(id + "id'li uygun tarih bulunamadı!"));
        return availableDateMapper.asResponse(availableDate);
    }

    public AvailableDateResponse create(AvailableDateRequest request) {
        Optional<AvailableDate> isAvailableDateExist = Optional.ofNullable(availableDateRepo.findByAvailableDateAndDoctorId(
                request.getAvailableDate(),
                request.getDoctorId()
        ));

        if (isAvailableDateExist.isEmpty()) {
            AvailableDate savedAvailableDate = availableDateRepo.save(availableDateMapper.asEntity(request));
            return availableDateMapper.asResponse(savedAvailableDate);
        }
        throw new RuntimeException("Bu tarih daha önce sisteme kayıt olmuştur!");
    }

    public AvailableDateResponse update(Long id, AvailableDateRequest updatedAvailableDate) {
        Optional<AvailableDate> optionalExistingAvailableDate = availableDateRepo.findById(id);

        if (optionalExistingAvailableDate.isEmpty()) {
            throw new RuntimeException(id + "id'li uygun tarih bulunamadı!");
        }

        AvailableDate existingAvailableDate = optionalExistingAvailableDate.get();
        availableDateMapper.update(existingAvailableDate, updatedAvailableDate); // Use the availableDateMapper to update the existing AvailableDate entity
        AvailableDate updatedEntity = availableDateRepo.save(existingAvailableDate); // Save the updated entity to the database
        return availableDateMapper.asResponse(updatedEntity); // Map the updated entity to the response
    }

    public void deleteById(Long id) {
        Optional<AvailableDate> isAvailableDateExist = availableDateRepo.findById(id);
        if (isAvailableDateExist.isPresent()) {
            availableDateRepo.delete(isAvailableDateExist.get());
        } else {
            throw new RuntimeException(id + "id'li uygun tarih bulunamadı!");
        }
    }
}
