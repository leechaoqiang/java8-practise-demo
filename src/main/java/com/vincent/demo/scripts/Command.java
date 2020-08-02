package com.vincent.demo.scripts;

import java.io.BufferedReader;
import java.io.InputStreamReader;
public class Command {

    public static void exeCmd(String commandStr) {
        BufferedReader br = null;
        try {
            Process process = Runtime.getRuntime().exec(commandStr);
            br = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            StringBuilder sb = new StringBuilder();
            while ((line = br.readLine()) != null) {
                sb.append(line + "\n");
            }
            System.out.println(sb.toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            if (br != null)
            {
                try {
                    br.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("========begin======>>>>>");
//        String commandStr = "ping www.baidu.com";
        String commandStr = "ifconfig";
        Command.exeCmd(commandStr);
        System.out.println("========end======>>>>>");
    }
}
