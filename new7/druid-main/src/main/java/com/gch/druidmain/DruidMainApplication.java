package com.gch.druidmain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication(scanBasePackages = {"com.gch.druidmain", "com.example", "com.gch.druidmodule2"})
public class DruidMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(DruidMainApplication.class, args);
        System.out.println("/**********/**********/**********//**********//**********//**********//**********/\n" +
                "   .--,       .--,\n" +
                "  ( (  \\.---./  ) )\n" +
                "   '.__/o   o\\__.'\n" +
                "      {=  ^  =}\n" +
                "       >  -  <\n" +
                "      /       \\\n" +
                "     //       \\\\\n" +
                "    //|   .   |\\\\\n" +
                "    \"'\\       /'\"_.-~^`'-.\n" +
                "       \\  _  /--'         `\n" +
                "     ___)( )(___\n" +
                "    (((__) (__)))    高山仰止,景行行止.虽不能至,心向往之。\n" +
                " /**********/**********/**********//**********//**********//**********//**********/ ");

    }


}
