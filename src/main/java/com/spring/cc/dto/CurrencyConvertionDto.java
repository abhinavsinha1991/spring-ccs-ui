/*

 */

package com.spring.cc.dto;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class CurrencyConvertionDto {

	private Integer id;

	private String from;

	private String to;
	
	private Double conversionMultiple;
	
	private Integer quantity;
	
	private BigDecimal totalCalculatedAmount;
	
	private String ip;
	
	private Short port;
	
	
//	{"id":10001,"from":"USD","to":"INR","conversionMultiple":65.00,"quantity":2000,"totalCalculatedAmount":130000.00,"port":8000}
	

}
