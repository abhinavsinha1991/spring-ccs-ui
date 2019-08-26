/*

 */

package com.spring.cc.logic;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.cc.config.SystemConfig;
import com.spring.cc.dto.CurrencyConvertionDto;
import com.spring.cc.service.CurrencyConverterService;

@Component
public class ConvertLogic {

	@Autowired
	CurrencyConverterService currencyConverterService;

	/**
	 * 
	 * @param convertFrom
	 * @param convertTo
	 * @param amount
	 * @return
	 */
	public String getConvertionAmount(String convertFrom, String convertTo, Long amount) {
		try {
			return new ObjectMapper().writeValueAsString(amountConvertionService(convertFrom, convertTo, amount));
		} catch (Exception e) {
			return "-1";
		}
	}

	/**
	 * 
	 * @param convertFrom
	 * @param convertTo
	 * @param amount
	 * @return
	 */
	private BigDecimal amountConvertionService(String convertFrom, String convertTo, Long amount) {
		BigDecimal convertAmount = new BigDecimal(-1);
		try {
			if ("" != SystemConfig.SERVICE_URI) {
				CurrencyConvertionDto currencyConvertionDto = currencyConverterService.getConvertionAmount(convertFrom,
						convertTo, amount);
				convertAmount = currencyConvertionDto.getTotalCalculatedAmount();
			}
		} catch (Exception e) {
			// convertAmount = -1l;
		}
		return convertAmount;
	}
}
