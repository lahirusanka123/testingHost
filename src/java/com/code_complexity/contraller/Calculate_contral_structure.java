/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.code_complexity.contraller;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author Lahiru Sanka
 */
public class Calculate_contral_structure {

    public String calculate(String redDate) {

        String codelines = "";
        int ifcount = 1;
        int forcount = 0;
        int switchcount = 0;
        int casecount = 0;
        int count = 0;
        int Ccspps = 0;
        int Ccs = 0;
        int ifWtcs = 2;
        int forWtcs = 3;
        int switchWtcs = 2;
        int caseWtcs = 1;
        int nestedif = 0;
        int CcsppsValue = 0;
        int open_bracketCount = 0;
        int close_bracketCount = 0;
        boolean nestedbegin = false;
        int Nested_Ccspps = 0;
        int nested_Ccs = 0;
        int switch_Ccs = 0;


        for (String f_line : redDate.split("\\n")) {
//                                    f_line = f_line.split("//")[0].trim();

            count++;
            open_bracketCount += StringUtils.countMatches(f_line, "{");
            close_bracketCount += StringUtils.countMatches(f_line, "}");

            if (f_line.contains("//")) {
                String check[] = f_line.split("//");
                String str = check[0].replaceAll("\\s", "");

                if (str.equalsIgnoreCase("")) {

                    codelines += "<tr> <td >" + count + "</td> <td>" + f_line + "</td> <td></td> <td></td> <td></td> <td>0</td> </tr>" + "\n";

                } else {

                    if (str.contains("if")) {

                        int andCount = StringUtils.countMatches(f_line, "&&");
                        int orCount = StringUtils.countMatches(f_line, "||");
                        int NC = (andCount + orCount) + 1;

                        System.out.println("open bracket Counter" + open_bracketCount);
                        System.out.println("close bracket Counter" + close_bracketCount);

                        if (open_bracketCount != close_bracketCount) {

                            if (ifcount == 1) {

                                ifcount++;
                                codelines += "<tr> <td >" + count + "</td> <td>" + f_line + "</td> <td>" + ifWtcs + "</td> <td>" + NC + "</td> <td>0</td> <td>" + ifWtcs * NC + "</td> </tr>" + "\n";
                                System.out.println(f_line + "called open_bracketCount" + ifcount);
                            } else {

                                nested_Ccs = (ifWtcs * NC) + Ccs;
                                Nested_Ccspps = nested_Ccs - (ifWtcs * NC);
                                Ccs = nested_Ccs;

                                codelines += "<tr> <td >" + count + "</td> <td>" + f_line + "</td> <td>" + ifWtcs + "</td> <td>" + NC + "</td> <td>" + Nested_Ccspps + "</td> <td>" + nested_Ccs + "</td> </tr>" + "\n";

                                System.out.println("else part of called open_bracketCount" + ifcount);
                            }
                        } else {

                            Ccspps = ifWtcs * NC;
                            Ccs = Ccspps;
                            codelines += "<tr> <td >" + count + "</td> <td>" + f_line + "</td> <td>" + ifWtcs + "</td> <td>" + NC + "</td> <td>0</td> <td>" + ifWtcs * NC + "</td> </tr>" + "\n";
                            System.out.println("called open_bracketCount" + ifcount);
                        }

                        open_bracketCount = 0;
                        close_bracketCount = 0;

                    } else if (str.contains("for")) {

                        forcount++;
                        Ccspps = (forWtcs * 1);
                        Ccs += Ccspps;
                        CcsppsValue = Ccs - Ccspps;
                        codelines += "<tr> <td >" + count + "</td> <td>" + f_line + "</td> <td>" + forWtcs + "</td> <td>1</td> <td>" + CcsppsValue + "</td> <td>" + Ccs + "</td> </tr>" + "\n";

                    } else if (str.contains("switch")) {

                        switchcount++;
                        Ccspps = (switchWtcs * 1);
                        Ccs += Ccspps;
                        CcsppsValue = Ccs - Ccspps;
                        switch_Ccs = Ccs;
                        codelines += "<tr> <td >" + count + "</td> <td>" + f_line + "</td> <td>" + switchWtcs + "</td> <td>1</td> <td>" + CcsppsValue + "</td> <td>" + Ccs + "</td> </tr>" + "\n";
                        Ccs = Ccs - Ccspps;
                        
                    } else if (str.contains("case")) {

                        casecount++;
                        Ccspps = (caseWtcs * 1) + switch_Ccs;
                        CcsppsValue = Ccs - Ccspps;
                        codelines += "<tr> <td >" + count + "</td> <td>" + f_line + "</td> <td>" + caseWtcs + "</td> <td>1</td> <td>" + switch_Ccs + "</td> <td>" + ((caseWtcs * 1) + switch_Ccs) + "</td> </tr>" + "\n";
                         

                    } else {
                        codelines += "<tr> <td >" + count + "</td> <td>" + f_line + "</td> <td></td> <td></td> <td></td> <td>0</td> </tr>" + "\n";

                    }

                }

            } else if (f_line.contains("if")) {

                int andCount = StringUtils.countMatches(f_line, "&&");
                int orCount = StringUtils.countMatches(f_line, "||");
                int NC = (andCount + orCount) + 1;

                System.out.println("open bracket Counter" + open_bracketCount);
                System.out.println("close bracket Counter" + close_bracketCount);

                if (open_bracketCount != close_bracketCount) {
                    if (ifcount == 1) {

                        ifcount++;
                        Ccspps = ifWtcs * NC;
                        Ccs = Ccspps;
                        codelines += "<tr> <td >" + count + "</td> <td>" + f_line + "</td> <td>" + ifWtcs + "</td> <td>" + NC + "</td> <td>0</td> <td>" + ifWtcs * NC + "</td> </tr>" + "\n";
                        System.out.println(f_line + "called open_bracketCount" + ifcount);

                    } else {

                        nested_Ccs = (ifWtcs * NC) + Ccs;
                        Nested_Ccspps = nested_Ccs - (ifWtcs * NC);
                        Ccs = nested_Ccs;

                        codelines += "<tr> <td >" + count + "</td> <td>" + f_line + "</td> <td>" + ifWtcs + "</td> <td>" + NC + "</td> <td>" + Nested_Ccspps + "</td> <td>" + nested_Ccs + "</td> </tr>" + "\n";
                        System.out.println("else part of called open_bracketCount" + ifcount);
                    }
                } else {

                    Ccspps = ifWtcs * NC;
                    Ccs = Ccspps;
                    codelines += "<tr> <td >" + count + "</td> <td>" + f_line + "</td> <td>" + ifWtcs + "</td> <td>" + NC + "</td> <td>0</td> <td>" + ifWtcs * NC + "</td> </tr>" + "\n";
                    System.out.println("called else" + ifcount);
                }

                open_bracketCount = 0;
                close_bracketCount = 0;

            } else if (f_line.contains("for")) {

                forcount++;
                Ccspps = (forWtcs * 1);
                Ccs += Ccspps;
                CcsppsValue = Ccs - Ccspps;
                codelines += "<tr> <td >" + count + "</td> <td>" + f_line + "</td> <td>" + forWtcs + "</td> <td>1</td> <td>" + CcsppsValue + "</td> <td>" + Ccs + "</td> </tr>" + "\n";

            } else if (f_line.contains("switch")) {

                switchcount++;
                Ccspps = (switchWtcs * 1);
                Ccs += Ccspps;
                CcsppsValue = Ccs - Ccspps;
                switch_Ccs = Ccs;
                codelines += "<tr> <td >" + count + "</td> <td>" + f_line + "</td> <td>" + switchWtcs + "</td> <td>1</td> <td>" + CcsppsValue + "</td> <td>" + Ccs + "</td> </tr>" + "\n";
                Ccs = Ccs - Ccspps;

            } else if (f_line.contains("case")) {

                casecount++;
                Ccspps = (caseWtcs * 1) + switch_Ccs;
                CcsppsValue = Ccs - Ccspps;
                codelines += "<tr> <td >" + count + "</td> <td>" + f_line + "</td> <td>" + caseWtcs + "</td> <td>1</td> <td>" + switch_Ccs + "</td> <td>" + ((caseWtcs * 1) + switch_Ccs) + "</td> </tr>" + "\n";
                 
            } else {
                codelines += "<tr> <td >" + count + "</td> <td>" + f_line + "</td> <td></td> <td></td> <td></td> <td>0</td> </tr>" + "\n";

            }
        }

        return codelines;
    }

}
