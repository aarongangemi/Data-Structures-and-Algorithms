public class MainTestHarness
{
    public static void main(String[] args)
    {
		DSAHeap theHeap = new DSAHeap(10);
		theHeap.add(1, 70);
		theHeap.add(2, 40);
		theHeap.add(3, 50);
		theHeap.add(4, 20);
		theHeap.add(5, 60);
		theHeap.add(6, 100);
		theHeap.add(7, 80);
		theHeap.heapSort();
		theHeap.displayHeap();
    }
}
