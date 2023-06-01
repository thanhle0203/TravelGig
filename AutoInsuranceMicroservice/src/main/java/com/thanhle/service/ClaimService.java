package com.thanhle.service;

import com.thanhle.domain.Claim;

import java.util.List;

public interface ClaimService {
    List<Claim> getAllClaims();
    Claim getClaimById(Long id);
    Claim createClaim(Claim claim);
    void deleteClaim(Long id);
    
    void updateClaimStatus(Long claimId, String status);
    void updateClaimRepairPrice(Long claimId, double repairPrice);
	Claim findByDescription(String description);

}
