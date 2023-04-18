import java.util.*;

public class Buffer {
    private Queue<String> queue = new LinkedList<String>();
    private int queueSize;
    int numOfElements = 0;
    String item = " ";
    Boolean checker = false;

    Semaphore spaces = new Semaphore(queueSize);
    Semaphore elements = new Semaphore(0);

    public Buffer(int s) {
        queueSize = s;
    }

    public void check(Boolean c) {
        this.checker = c;
    }

    public void produce(String item) {
        while (queue.size() == queueSize) {
            spaces.P();
        }
        this.queue.add(item);
        numOfElements++;
        elements.V();
    }

    public synchronized String consume() {
        while (queue.isEmpty()) {
            elements.P();
        }
        item = queue.remove();
        numOfElements--;
        spaces.V();
        return item;
    }
}