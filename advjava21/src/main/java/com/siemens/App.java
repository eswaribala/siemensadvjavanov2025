package com.siemens;

import com.github.javafaker.Faker;
import com.siemens.interfaces.OTPGenerator;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Faker faker = new Faker();
        String name=faker.name().fullName();
        //lambda to print each character with delay
        Runnable runnable = () -> {
            //overrding run method
            for(char ch : name.toCharArray()) {
                System.out.print(ch + " ");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("\n");
            }

        };
        Thread thread = new Thread(runnable);
        thread.start();
        try {
            thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //lambda expression for OTP generation
        OTPGenerator generator = (min,max)->
             faker.number().numberBetween(min, max);


        System.out.println("Generated OTP="+generator.getOTP(1000,9999));

    }
}
