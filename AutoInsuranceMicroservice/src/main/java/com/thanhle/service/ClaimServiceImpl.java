package com.thanhle.service;

import com.thanhle.domain.Claim;
import com.thanhle.repository.ClaimRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

@Service
@CrossOrigin(origins = "http://localhost:8282")
public class ClaimServiceImpl implements ClaimService {

    private final ClaimRepository claimRepository;

    public ClaimServiceImpl(ClaimRepository claimRepository) {
        this.claimRepository = claimRepository;
    }

    @Override
    public List<Claim> getAllClaims() {
        return claimRepository.findAll();
    }

    @Override
    public Claim getClaimById(Long id) {
        return claimRepository.findById(id).orElse(null);
    }

    @Override
    public Claim createClaim(Claim claim) {
        return claimRepository.save(claim);
    }

    @Override
    public void updateClaimStatus(Long claimId, String status) {
        Optional<Claim> optionalClaim = claimRepository.findById(claimId);
        if (optionalClaim.isPresent()) {
            Claim claim = optionalClaim.get();
            claim.setStatus(status);
            claimRepository.save(claim);
        }
    }

    @Override
    public void updateClaimRepairPrice(Long claimId, double repairPrice) {
        Optional<Claim> optionalClaim = claimRepository.findById(claimId);
        if (optionalClaim.isPresent()) {
            Claim claim = optionalClaim.get();
            claim.setRepairPrice(repairPrice);
            claimRepository.save(claim);
        }
    }

    @Override
    public void deleteClaim(Long id) {
        claimRepository.deleteById(id);
    }

    @Override
    public Claim findByDescription(String description) {
        return claimRepository.findByDescription(description);
    }

    @Override
    public void updateClaim(Claim claim) {
        Optional<Claim> optionalClaim = claimRepository.findById(claim.getId());
        if (optionalClaim.isPresent()) {
            Claim existingClaim = optionalClaim.get();
            existingClaim.setAccidentDate(claim.getAccidentDate());
            existingClaim.setDescription(claim.getDescription());
            existingClaim.setStatus(claim.getStatus());
            existingClaim.setRepairPrice(claim.getRepairPrice());
            existingClaim.setPhone(claim.getPhone());
            existingClaim.setVehicle(claim.getVehicle());
            existingClaim.setImages(claim.getImages());
            claimRepository.save(existingClaim);
        }
    }

}
