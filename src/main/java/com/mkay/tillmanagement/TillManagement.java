/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mkay.tillmanagement;

 //* @author makhadzinevuwari
 

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class TillManagement {

    public static void main(String[] args) {
        
        // Map to store the denominations in the till and their counts
        Map<Integer, Integer> till = new HashMap<>();
        till.put(50, 5);
        till.put(20, 5);
        till.put(10, 6);
        till.put(5, 12);
        till.put(2, 10);
        till.put(1, 10);

        // transactions
        try (BufferedReader reader = new BufferedReader
        (new FileReader("/Users/makhadzinevuwari/Downloads/TDS/input.txt"));
             PrintWriter writer = new PrintWriter
        (new FileWriter("/Users/makhadzinevuwari/Downloads/TDS/input.txt")))
            
        {
            String line;
            while ((line = reader.readLine()) != null) 
           
            {
                String[] parts = line.split(";");
                String[] items = parts[0].split(",");
                int totalCost = 0;
                for (String item : items) 
                
                {
                    String[] itemParts = item.split(" ");
                    totalCost += Integer.parseInt(itemParts[1]);
                }
                
                String[] paymentParts = parts[1].split("-");
                int payment = 0;
                for (String paymentPart : paymentParts) 
                
                {
                    payment += Integer.parseInt(paymentPart);
                }
                
                int change = payment - totalCost;
                writer.println("Current Till: " + till);
                writer.println("Total Cost: R" + totalCost);
                writer.println("Amount Paid: R" + payment);
                writer.println("Change Given: R" + change);
                writer.println("Change Breakdown:");
                for (int denomination : till.keySet())
                
                {
                    int count = change / denomination;
                    if (count > 0)
                    
                    {
                        int actualCount = Math.min(count, till.get(denomination));
                        writer.println("R" + denomination + " x " + actualCount);
                        change -= actualCount * denomination;
                        till.put(denomination, till.get(denomination) - actualCount);
                    }
                }
                
                writer.println();
            }
            
            writer.println("Amount Left in Till: R" + calculateAmountInTill(till));
        }
        catch (IOException e) 
       
        {
            e.printStackTrace();
        }
    }

    //Total amount in the till
    private static int calculateAmountInTill(Map<Integer, Integer> till) 
    
    {
        int total = 0;
        for (Map.Entry<Integer, Integer> entry : till.entrySet())
        
        {
            total += entry.getKey() * entry.getValue();
        }
        return total;
        
    }
}