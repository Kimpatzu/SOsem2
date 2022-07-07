import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static ArrayList<Proces> losujProcesy(){
        Random random = new Random();
        ArrayList<Proces> procesy = new ArrayList<>();
        for (int i=0; i<200; i++){
            procesy.add(new Proces(random.nextInt(20), random.nextInt(100), i+1));
        }
        return procesy;
    }
    public static ArrayList<Proces> procesyTestowe(){
        ArrayList<Proces> procesy = new ArrayList<>();
        procesy.add(new Proces(8,0,3));
        procesy.add(new Proces(2,5,4));
        procesy.add(new Proces(4,7,5));
        procesy.add(new Proces(5,2,2));
        procesy.add(new Proces(1,4,1));
        return procesy;
    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1. Utwórz 200 losowych procesów \n2. Zastosuj ciąg testowy");
        int wyborDanych = scanner.nextInt();
        boolean run = true;
        ArrayList<Proces> procesy;
        if (wyborDanych==1){
            procesy=losujProcesy();
        } else {
            procesy=procesyTestowe();
        }
        System.out.println("Oto procesy na których zostanie zastosowana wybrana metoda");
        for (Proces p : procesy) {
            System.out.println(p);
        }
        while(run) {
            System.out.println("1. Zastosuj FCFS \n2. Zastosuj SJF\n3. Zastosuj SRTF\n4. Zastosuj RR\n5. Zakończ program");
            int wyborMetody = scanner.nextInt();
            System.out.println("______________________________________");
            System.out.println("Tablica wykonanych procesów: ");
            if (wyborMetody == 1) {
                ArrayList<Proces> procesArrayList = new ArrayList<>();
                for(int i=0; i<procesy.size(); i++){
                    procesArrayList.add(procesy.get(i));
                }
                FCFS fcfs = new FCFS(procesArrayList);
                fcfs.Symulacja();
            } else if (wyborMetody == 2) {
                ArrayList<Proces> procesArrayList2 = new ArrayList<>();
                for(int i=0; i<procesy.size(); i++){
                    procesArrayList2.add(procesy.get(i));
                }
                SJF sjf = new SJF(procesArrayList2);
                sjf.Symulacja();
            } else if (wyborMetody == 3) {
                ArrayList<Proces> procesArrayList3 = new ArrayList<>();
                for(int i=0; i<procesy.size(); i++){
                    procesArrayList3.add(procesy.get(i));
                }
                System.out.println("Podaj kwant czasu: ");
                int kwant = scanner.nextInt();
                SRTF srtf = new SRTF(procesArrayList3, kwant);
                srtf.Symulacja();
            } else if (wyborMetody == 4) {
                ArrayList<Proces> procesArrayList4 = new ArrayList<>();
                for(int i=0; i<procesy.size(); i++){
                    procesArrayList4.add(procesy.get(i));
                }
                System.out.println("Podaj kwant czasu: ");
                int kwantCzasu = scanner.nextInt();
                RR rr = new RR(procesArrayList4, kwantCzasu);
                rr.Symulacja();
            } else if (wyborMetody == 5) {
                run = false;
            }
        }
    }
}
