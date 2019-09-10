package com.example.KhoaLeU1Capstone.dao;

import com.example.KhoaLeU1Capstone.model.ProcessingFee;
import com.example.KhoaLeU1Capstone.model.Tshirt;

import java.util.List;

public interface ProcessingFeeDao {

    ProcessingFee addProcessingFee(ProcessingFee processingFee);

    ProcessingFee getProcessingFee(String productType);

    List<ProcessingFee> getAllProcessingFees();

    void updateProcessingFee(ProcessingFee processingFee);

    void deleteProcessingFee(String productType);
}
