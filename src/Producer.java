public class Producer implements Runnable {
    Buffer buff;
    int pNum, cnt = 0, maxPrime = 0;
    long startTime;

    public Producer(Buffer b, int n, long s) {
        this.buff = b;
        pNum = n;
        startTime = s;
    }

    @Override
    public void run() {
        if (pNum == 0) {
            System.exit(0);
        }
        if (pNum > 0) {
            buff.produce(Integer.toString(1));
            cnt++;
        }

        for (int n = 2; n <= pNum; n++) {
            boolean flag = true;
            for (int i = 2; i < n; i++) {
                if (n % i == 0) {
                    flag = false;
                    break;
                }
            }
            if (flag == true) {
                buff.produce(Integer.toString(n));
                cnt++;
                maxPrime = n;
            }
            if (n == pNum) {
                buff.check(true);
                if (buff.checker == true && buff.numOfElements == 0) {
                    long endTime = System.currentTimeMillis();
                    System.exit(0);
                }
            }
        }
    }
}