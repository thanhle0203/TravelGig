package com.thanhle.service;

import com.thanhle.domain.Claim;
import com.thanhle.repository.ClaimRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
}
