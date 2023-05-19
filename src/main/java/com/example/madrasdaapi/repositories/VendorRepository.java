package com.example.madrasdaapi.repositories;

import com.example.madrasdaapi.dto.commons.SalesAnalysis;
import com.example.madrasdaapi.models.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface VendorRepository extends JpaRepository<Vendor, Long> {
     @Procedure(name = "GET_SALES_ANALYSIS_BY_VENDOR_ID")
     SalesAnalysis getSalesAnalysisByVendorId(@Param("vendorId") Long vendorId);

     @Procedure(name = "TOP_SELLERS_FOR_VENDOR")
     List<Object[]> TOP_SELLERS_FOR_VENDOR(@Param("vendor_id") Long vendorId, @Param("lim") Integer limit);

     @Procedure(name = "monthly_sales_by_id")
     Object[][] monthly_sales_by_id(@Param("vendor_id") Long vendorId);

     @Procedure(name = "HOT_SELLERS")
     Object[][] HOT_SELLERS(@Param("N") Integer limit);

     @Procedure(name = "products_sold_today")
     Integer products_sold_today(@Param(("vendor_id")) Long vendorId);

     Vendor findByUser_Email(String email);

     Optional<Vendor> getVendorByUser_Email(String email);

     @Query("select v.id from Vendor v where v.user.email = ?1")
     Long findIdByUser_Email(String email);

     List<Vendor> findAllByPayoutRequested(Boolean payout);

     @Procedure("TOGGLE_VENDOR_ACCOUNT")
     void toggleVendorAccount(Long vendorId);

     @Modifying
     @Query("UPDATE Vendor v SET v.profilePic = NULL WHERE v.id = :vendorId")
     void deleteVendorProfilePicture(Long vendorId);

     List<Vendor> findByStatus(Boolean status);

}