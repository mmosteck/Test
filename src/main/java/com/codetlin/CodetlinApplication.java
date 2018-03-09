package com.example.test;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.text.DecimalFormat;
import java.util.GregorianCalendar;

@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) throws DatatypeConfigurationException
    {

		GregorianCalendar gregorianCal = new GregorianCalendar();
        System.out.println(gregorianCal.getTimeInMillis());
        SportTicket sportTicket = new SportTicket();
		sportTicket.setStarttime(DatatypeFactory.newInstance().newXMLGregorianCalendar(gregorianCal));
        sportTicket.setStartDate(gregorianCal.getTime());

        System.out.println(sportTicket.getStarttime());
        System.out.println(sportTicket.getStartDate());

	}
}
