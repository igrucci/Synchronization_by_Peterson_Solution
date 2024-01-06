package org.example;

public class Main {

    static volatile boolean want[] = { false, false};
    static volatile int victim = 0;

    public static void main( String[] args ) {


        new Thread(() ->
              {
                  while (true) {

                      // entry
                      want[0] = true; //flag ready
                      victim = 1; // allows another
                      while( want[ 1 ] && victim == 1); // wait

                            //critical section
                      for (int i = 3; i > 0; i--) {

                          System.out.println("1 thread in the critical section " + i);
                      }
                      // exit
                      want[0] = false;
                      System.out.println("1 thread leave the critical section");
                  }

                }).start();

        new Thread(() ->
        {
                  while (true) {

                // entry
                want[1] = true; //flag ready
                victim = 0; // allows another
                while( want[ 0 ] && victim == 0); // wait

                //critical section
                for (int i = 3; i > 0; i--) {

                    System.out.println("2 thread in the critical section " + i);
                }
                // exit
                want[1] = false;
                System.out.println("2 thread leave the critical section");
            }

        }).start();

    }
}
