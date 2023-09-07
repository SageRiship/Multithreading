class TickTock {

    public void tick() throws InterruptedException {
        synchronized (this) {
            System.out.print("Tick   ");
            this.notify();
            this.wait();
           // this.wait(1000);
        }
    }

    public void tock() throws InterruptedException {
        synchronized (this) {
            System.out.println("    Tock");
            this.notify();
            this.wait(); 
            //this.wait(1000); //by using this.wait() program is not terminating it is waiting for someone will notify
            //to overcome this blocking behaviour we should use this.wait(1000) , it will terminate after 1 sec
        }
    }
}

class CThread implements Runnable {

    Thread thread;

    public CThread(String name) {
        this.thread = new Thread(this, name);
        this.thread.start();
    }

    static TickTock tk = new TickTock();

    @Override
    public void run() {
        try {

            if (Thread.currentThread().getName().equals("Tickthread")) {
                for (int i = 0; i <= 5; i++) {
                    tk.tick();
                    Thread.sleep(100);
                }
            } else {
                for (int i = 0; i <= 5; i++) {
                    tk.tock();
                    Thread.sleep(100);
                }
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}

public class Program5 {
    public static void main(String[] args) {
        CThread th1 = new CThread("Tickthread");
        CThread th2 = new CThread("Tockthread");
    }
}
