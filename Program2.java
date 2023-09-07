public class Program2 {
    public static void main(String[] args) {
        int[] arr1 = new int[] { 11, 12, 13, 14, 15, 16, 17, 18, 19 };
        Test t1 = new Test("t1", arr1);
        int[] arr2 = new int[] { 21, 22, 23, 24, 25, 26, 27, 28, 29 };
        Test t2 = new Test("t2", arr2);
        int[] arr3 = new int[] { 31, 32, 33, 34, 35, 36, 37, 38, 39 };
        Test t3 = new Test("t3", arr3);
        int[] arr4 = new int[] { 41, 42, 43, 44, 45, 46, 47, 48, 49 };
        Test t4 = new Test("t4", arr4);

    }
}

class SumArray {
    public int sum(int[] arr) {
        int result = 0;
        for (int i = 0; i < arr.length; i++) {
            result = result + arr[i];
            System.out.println("Running Total for thread :" + Thread.currentThread().getName() + " is :" + result
                    + " Thread state :" + Thread.currentThread().getState());
        }
        return result;
    }
}

class Test implements Runnable {
    private Thread thread;
    int[] arr;

    public Test(String name, int[] arr) {
        this.thread = new Thread(this, name); // this = new Task
        this.arr = arr;
        thread.start();
    }

    static SumArray sa = new SumArray();

    @Override
    public void run() {
        System.out.println("Inside run");
        int sum = 0;
        synchronized (sa) {
            sum = sa.sum(arr);

        }
        System.out.println("Total Sum :" + sum);

    }
}