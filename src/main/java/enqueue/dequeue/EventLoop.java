package enqueue.dequeue;

import java.util.ArrayList;

public class EventLoop {
    private Thread worker;
    private boolean kill = false;

    public final int sleepTime = 1000;

    ArrayList<Event> queue;

    public EventLoop() {
        queue = new ArrayList<>();
        restart();
    }

    public void restart() {
        kill = false;

        worker = new Thread(new Runnable() {
            @Override
            public void run() {
                work();
            }
        });

        worker.start();
    }

    public synchronized void add(Event event) {
        queue.add(event);
    }

    public synchronized void trigger() throws IllegalStateException {
        if (kill) throw new IllegalStateException();
        worker.interrupt();
    }

    public void trigger(Event event) {
        add(event);
        trigger();
    }

    public ArrayList<Event> stop() {
        kill = true;
        worker.interrupt();
        return this.queue;
    }

    private void work() {
        while(true) {
            if (kill) break;

            if (queue.size() > 0) {
                Event event = queue.get(0);
                queue.remove(0);
                if (null != event.getFunction()) event.getFunction().run();
                if (null != event.getCallback()) event.getCallback().run();
            } else {
                try {
                    synchronized(worker) {
                        worker.wait();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
