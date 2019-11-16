import enqueue.dequeue.*;
class Test {
    public static void main(String[] args) {
        EventLoop e = new EventLoop();

        Implementor i = new Implementor(new Runnable() {
            @Override
            public void run() {
                System.out.println("A Function");
            }
        }, new Runnable(){
            @Override
            public void run() {
                System.out.println("A Callback");
            }
        });

        e.trigger(i);
        e.trigger(i);

        try {
            Thread.sleep(1000);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        System.out.println("stopping");
        e.stop();
    }
}
