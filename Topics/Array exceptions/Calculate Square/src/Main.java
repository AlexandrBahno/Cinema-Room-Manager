class FixingExceptions {

    public static void calculateSquare(int[] array, int index) {
        // write your code here
        int size = array == null ? 0 : array.length - 1;
        if (size == 0) {
            System.out.println("Exception!");
        } else if (index >= 0 && index <= size) {
            int square = array[index] * array[index];
            System.out.println(square);
        } else {
            System.out.println("Exception!");
        }
    }
}