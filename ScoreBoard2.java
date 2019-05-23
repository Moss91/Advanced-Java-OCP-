/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.soccer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import java.util.*;
import java.io.*;
import static java.util.Map.Entry.comparingByValue;
import static java.util.stream.Collectors.toMap;

/**
 *
 * @author TiehoM
 */
public class ScoreBoard2 {

    static Map<String, Integer> scoreboard = new TreeMap<String, Integer>();

    public ScoreBoard2() {
        scoreboard.put("Liverpool", 0);
        scoreboard.put("ManchesterUnited", 0);
        scoreboard.put("Tarantulas2", 0);
        scoreboard.put("FCAwesome", 0);
        scoreboard.put("Lions", 0);
        scoreboard.put("Grouches", 0);
    }

    public static void calcPoints(String line) {
        String[] one_match = line.split(" ");
        String team1Name = one_match[0];
        String team2Name = one_match[2];
        int team1Score = Integer.parseInt(one_match[1]);
        int team2Score = Integer.parseInt(one_match[3]);
        int t1Points = 0;
        int t2Points = 0;
        if (scoreboard.containsKey(team1Name)) {
            t1Points = scoreboard.get(team1Name);
        }
        if (scoreboard.containsKey(team2Name)) {
            t2Points = scoreboard.get(team2Name);
        }
       
        if (team1Score > team2Score) {
            scoreboard.put(team1Name, t1Points + 3);
            scoreboard.put(team2Name, t2Points);
        } else if (team1Score == team2Score) {
            scoreboard.put(team1Name, t1Points + 1);
            scoreboard.put(team2Name, t2Points + 1);
        } else {
            scoreboard.put(team1Name, t1Points);
            scoreboard.put(team2Name, t2Points + 3);
        }
    }

    public static void main(String[] args) {
        ScoreBoard2 sb = new ScoreBoard2();
        //read data into an arraylist
        try {
            File myFile = new File("C:\\Users\\TiehoM\\Documents\\soccerLeague.txt");
            FileReader fileReader = new FileReader(myFile);
            BufferedReader reader = new BufferedReader(fileReader);
            String line = null;
            String[] one_match;
            while ((line = reader.readLine()) != null) {
                sb.calcPoints(line);

            }
            reader.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        // let's sort this map by values first
        Map<String, Integer> sorted = scoreboard
                .entrySet()
                .stream()
                .sorted(comparingByValue())
                .collect(
                        toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2,
                                LinkedHashMap::new));

        // above code can be cleaned a bit by using method reference
        sorted = scoreboard
                .entrySet()
                .stream()
                .sorted(comparingByValue())
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));

        // now let's sort the map in decreasing order of value
        sorted = scoreboard
                .entrySet()
                .stream()
                .sorted(Collections.reverseOrder(Map.Entry.comparingByValue()))
                .collect(
                        toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2,
                                LinkedHashMap::new));

        Set set = sorted.entrySet();
        Iterator i = set.iterator();

        // Traverse map and print elements 
        while (i.hasNext()) {
            Map.Entry me = (Map.Entry) i.next();
            System.out.print(me.getKey() + " ");
            System.out.println(me.getValue());

        }
    }
}
