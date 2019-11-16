package enqueue.dequeue;

public abstract class Event {
    private Runnable function;
    private Runnable callback;

    public Event(Runnable function, Runnable callback) {
        this.function = function;
        this.callback = callback;
    }

    Runnable getFunction() {
        return this.function;
    }

    Runnable getCallback() {
        return this.callback;
    }
}
