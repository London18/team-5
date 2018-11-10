package util;

import checker.CheckingSystem;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class StartupEvent implements ServletContextListener, Runnable {


    private Thread thread;
    private long lastTime = 0L;
    private boolean exit = false;

    @Override
    public void contextInitialized(ServletContextEvent event) {
        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        exit = true;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(!exit) {
            if(lastTime == 0L) lastTime = System.currentTimeMillis() - 30_000L;
            if(System.currentTimeMillis() - lastTime < 30_000L) continue;
            lastTime = System.currentTimeMillis();

            CheckingSystem.updateNurses();
        }
    }
}
