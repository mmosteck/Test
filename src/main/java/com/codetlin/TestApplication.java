package com.codetlin;

import com.example.test.EnumSportTickets;
import com.example.test.SportTicket;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import java.util.*;

@SpringBootApplication
public class TestApplication {

	public static void main(String[] args) throws DatatypeConfigurationException
    {
        GregorianCalendar now = new GregorianCalendar();

        int number = 1;


        ArrayList<SportTicket> list = gen();
        ArrayList<SportTicket> tickets = new ArrayList<>();


        System.out.println("Current time: " + now.getTime());

        SportTicket[] dailyToDisplay = new SportTicket[number];
        SportTicket weeklyToDisplay = null;



        for (SportTicket ticket : list)
        {
            if(ticket.getCategory().equals(EnumSportTickets.DAILY) || ticket.getCategory().equals(EnumSportTickets.WEEKLY))
                tickets.add(ticket);
        }

        for (int i = 0; i < number; i++)
        {
            now.add(Calendar.DAY_OF_MONTH, i);
            // not doing any addition/subtraction, no need to worry about overflow and it's a Long anyways
            long dailyDiff = Integer.MAX_VALUE;
            long weeklyDiff = Integer.MAX_VALUE;
            for (SportTicket ticket : tickets)
            {
                long timeToStart = now.getTimeInMillis() - ticket.getStartDate().getTime();
                long timeToEnd = now.getTimeInMillis() - ticket.getEndDate().getTime();
                if(ticket.getCategory().equals(EnumSportTickets.DAILY) && timeToStart >= 0 && timeToEnd <= 0  && timeToStart <= dailyDiff)
                {
                    dailyToDisplay[i] = ticket;
                    dailyDiff = timeToStart;
                }

                else if(timeToStart >= 0 && timeToEnd <= 0  && timeToStart <= weeklyDiff && i == 0)   // parameter NUMBER_OF_DAYS... should not be applied to weekly tickets
                {
                    weeklyToDisplay = ticket;
                    weeklyDiff = timeToStart;
                }
            }
        }

        for(SportTicket t : dailyToDisplay)
        {
            if(t != null)
             System.out.println("Ticket " +
                    t.getKey() + " start: " +
                    t.getStartDate() + " --- " +
                    t.getEndDate());
        }
        if(weeklyToDisplay != null)
            System.out.println(weeklyToDisplay.getKey());


    }
    
    private static ArrayList<SportTicket> gen() throws DatatypeConfigurationException
    {
        GregorianCalendar d1 = new GregorianCalendar();
        d1.add(Calendar.HOUR_OF_DAY, -2);

        GregorianCalendar d2 = new GregorianCalendar();
        d2.add(Calendar.HOUR_OF_DAY, -1);

        GregorianCalendar d3 = new GregorianCalendar();
        d3.add(Calendar.HOUR_OF_DAY, 1);

        GregorianCalendar d4 = new GregorianCalendar();
        d4.add(Calendar.HOUR_OF_DAY, 2);

        GregorianCalendar d5 = new GregorianCalendar();
        d5.add(Calendar.HOUR_OF_DAY, -2);
        d5.add(Calendar.DAY_OF_MONTH, 1);

        GregorianCalendar d6 = new GregorianCalendar();
        d6.add(Calendar.HOUR_OF_DAY, -1);
        d6.add(Calendar.DAY_OF_MONTH, 1);

        GregorianCalendar d7 = new GregorianCalendar();
        d7.add(Calendar.HOUR_OF_DAY, 1);
        d7.add(Calendar.DAY_OF_MONTH, 1);

        GregorianCalendar d8 = new GregorianCalendar();
        d8.add(Calendar.DAY_OF_MONTH, -1);

        GregorianCalendar d9 = new GregorianCalendar();
       //1 d9.add(Calendar.DAY_OF_MONTH, -1);
        d9.add(Calendar.HOUR_OF_DAY, -11);

        GregorianCalendar d10 = new GregorianCalendar();
        d10.add(Calendar.DAY_OF_MONTH, 1);

        GregorianCalendar[] cal = new GregorianCalendar[]{d1, d2, d3, d4, d5, d6, d7};
        GregorianCalendar[] calw = new GregorianCalendar[]{d8, d9, d10};
        ArrayList<SportTicket> list = new ArrayList<>();

        for(int i = 0; i < cal.length; i++)
        {
            SportTicket ticket = new SportTicket();
            ticket.setStartDate(cal[i].getTime());
            ticket.setStarttime(DatatypeFactory.newInstance().newXMLGregorianCalendar(cal[i]));
            ticket.setKey("k" + (i + 1));
            ticket.setCategory(EnumSportTickets.DAILY);
            cal[i].add(Calendar.HOUR_OF_DAY, 3);
            ticket.setEndtime(DatatypeFactory.newInstance().newXMLGregorianCalendar(cal[i]));
            ticket.setEndDate(cal[i].getTime());
            list.add(ticket);
        }
        for (int i = 0; i < calw.length; i++)
        {
            SportTicket ticket = new SportTicket();
            ticket.setStartDate(calw[i].getTime());
            ticket.setStarttime(DatatypeFactory.newInstance().newXMLGregorianCalendar(calw[i]));
            ticket.setKey("k" + (i + 1));
            ticket.setCategory(EnumSportTickets.WEEKLY);
            calw[i].add(Calendar.DAY_OF_MONTH, 7);
            ticket.setEndtime(DatatypeFactory.newInstance().newXMLGregorianCalendar(calw[i]));
            ticket.setEndDate(calw[i].getTime());
            list.add(ticket);
        }
        return list;
    }
}
