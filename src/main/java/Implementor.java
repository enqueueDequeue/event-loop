import enqueue.dequeue.*;

class Implementor extends Event {
    Implementor(Runnable function, Runnable callback) {
        super(function, callback);
    }
}
