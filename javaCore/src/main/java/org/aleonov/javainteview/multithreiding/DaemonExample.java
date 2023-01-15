package org.aleonov.javainteview.multithreiding;

/**
 * Потоками-демонами называются потоки, работающие в фоновом режиме для нашей программы.
 */
public class DaemonExample {
    public static void main(String[] args) throws InterruptedException {
        runDaemon();
        runNotDaemon();
        System.out.println("Main is DONE!");
    }

    private static void runNotDaemon() {
        var thread = new Thread(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            System.out.println("NOT Daemon done");
        });
        thread.start();
    }

    /**
     * Если завершился последний обычный поток процесса, и остались только потоки-демоны,
     * то они будут принудительно завершены и выполнение процесса закончится
     */
    private static void runDaemon() {
        var thread = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            System.out.println("Daemon is done!");
        });
        thread.setDaemon(true);
        thread.start();
    }
}
