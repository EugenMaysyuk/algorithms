import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

/**
 * Subset.
 * <p>
 * Created by Eugene on 11-Mar-16.
 */
public class Subset {
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        while (!StdIn.isEmpty()) {
            rq.enqueue(StdIn.readString());
        }

        int k = Integer.parseInt(args[0]);
        for (int i = 0; i < k; i++) {
            StdOut.println(rq.dequeue());
        }
    }
}
