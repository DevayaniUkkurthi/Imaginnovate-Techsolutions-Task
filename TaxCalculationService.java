package com.imaginnovatetech.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;

import org.springframework.stereotype.Service;

import com.imaginnovatetech.model.Employee;

@Service
public class TaxCalculationService {
	public BigDecimal calculateTax(Employee employee) {
        BigDecimal yearlySalary = calculateYearlySalary(employee);
        BigDecimal taxAmount = BigDecimal.ZERO;

        if (yearlySalary.compareTo(BigDecimal.valueOf(250000)) > 0) {
            BigDecimal remainingSalary = yearlySalary.subtract(BigDecimal.valueOf(250000));
            if (remainingSalary.compareTo(BigDecimal.valueOf(250000)) <= 0) {
                taxAmount = remainingSalary.multiply(BigDecimal.valueOf(0.05));
            } else {
                taxAmount = BigDecimal.valueOf(12500);
                remainingSalary = remainingSalary.subtract(BigDecimal.valueOf(250000));
                if (remainingSalary.compareTo(BigDecimal.valueOf(500000)) <= 0) {
                    taxAmount = taxAmount.add(remainingSalary.multiply(BigDecimal.valueOf(0.1)));
                } else {
                    taxAmount = taxAmount.add(BigDecimal.valueOf(50000));
                    remainingSalary = remainingSalary.subtract(BigDecimal.valueOf(500000));
                    taxAmount = taxAmount.add(remainingSalary.multiply(BigDecimal.valueOf(0.2)));
                }
            }
        }

        BigDecimal cessAmount = BigDecimal.ZERO;
        if (yearlySalary.compareTo(BigDecimal.valueOf(2500000)) > 0) {
            BigDecimal excessAmount = yearlySalary.subtract(BigDecimal.valueOf(2500000));
            cessAmount = excessAmount.multiply(BigDecimal.valueOf(0.02));
        }

        return taxAmount.add(cessAmount).setScale(2, RoundingMode.HALF_UP);
    }

    private BigDecimal calculateYearlySalary(Employee employee) {
        LocalDate today = LocalDate.now();
        int monthsWorked = (int) employee.getDoj().until(today).toTotalMonths();
        BigDecimal totalSalary = employee.getSalary().multiply(BigDecimal.valueOf(monthsWorked));
        return totalSalary;
    }
	

}
