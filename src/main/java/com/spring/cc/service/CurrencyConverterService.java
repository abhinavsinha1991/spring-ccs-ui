/*

 */

package com.spring.cc.service;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.spring.cc.config.SystemConfig;
import com.spring.cc.dto.CurrencyConvertionDto;

@Component
public class CurrencyConverterService {

	public CurrencyConvertionDto getConvertionAmount( String convertFrom, String convertTo, Long amount)
			throws Exception {
		// http://spring-ccs-myproject.10.34.156.26.nip.io/currency-converter-feign/from/USD/to/INR/quantity/2000
		RestTemplate restTemplate = new RestTemplate();
		StringBuilder urlBuilder = new StringBuilder();
		urlBuilder.append( SystemConfig.SERVICE_URI);
		urlBuilder.append("/currency-converter-feign/");
		urlBuilder.append("/from/" + convertFrom);
		urlBuilder.append("/to/" + convertTo);
		urlBuilder.append("/quantity/" + amount);

		byte[] resultBytes = null;
		try {
			resultBytes = restTemplate.getForObject(urlBuilder.toString(), byte[].class);
			String result = new String(resultBytes, "UTF-8");
			ObjectMapper mapper = new ObjectMapper();
			return mapper.readValue(result, new TypeReference<CurrencyConvertionDto>() {
			});
		} catch (Exception e) {
			//e.printStackTrace();
			throw e;
		}
	}
}
