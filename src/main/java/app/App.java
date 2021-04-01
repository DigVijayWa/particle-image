package app;

import frame.FrameSetter;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import objects.Handler;
import utility.MouseListener;

public class App extends Canvas implements Runnable{

  private Thread thread;

  Handler handler = new Handler();

  private static final int width = 640;
  private static final int height = 640;
  static int totalSeconds = 1;
  private boolean running = false;

  public App() {
    this.addMouseListener(new MouseListener(handler));
  }

  @Override
  public void run() {
    double target = 60.0;
    double nsPerTick = 1000000000.0 / target;
    long lastTime = System.nanoTime();
    long timer = System.currentTimeMillis();
    double unprocessed = 0.0;
    int fps = 0;
    int tps = 0;
    boolean canRender = false;

    while (running) {
      long now = System.nanoTime();
      long difference = now - lastTime;
      unprocessed += difference / nsPerTick;
      lastTime = now;

      if (unprocessed >= 1.0) {
        handler.update(difference);
        unprocessed--;
        tps++;
        canRender = true;
      } else canRender = false;

      try {
        Thread.sleep(1);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      if (canRender) {
        render();
        fps++;
      }

      if (System.currentTimeMillis() - 1000 > timer) {
        timer += 1000;
        System.out.printf("FPS: %d | TPS: %d | total seconds : %d\n", fps, tps,totalSeconds++);
        fps = 0;
        tps = 0;
      }

    }
  }
  public void render() {
    BufferStrategy bs = getBufferStrategy();
    if (bs == null) {
      createBufferStrategy(3);
      return;
    }

    Graphics2D g = (Graphics2D)bs.getDrawGraphics();

    clearScreen(g);

    handler.render(g);


    g.dispose();
    bs.show();
  }
  public void clearScreen(Graphics2D g) {
    g.setColor(Color.BLACK);
    g.fillRect(0, 0, width, height);
  }
  public void start() {
    thread = new Thread(this);
    running = true;
    setBackground(Color.BLACK);
    thread.start();
  }
  public void stop() {
    try {
      running = false;
      thread.join();
    }catch(Exception E) {
      E.printStackTrace();
    }
  }
  public static void main(String args[]) {
    new FrameSetter(width,height,new app.App());
  }
}

