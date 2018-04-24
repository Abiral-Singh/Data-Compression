public class HuffHeap {
    Node head;
    int heapsize;

    public HuffHeap() {
        head = null;
    }

    Node[] minHeap(int[][] a, int n) {
        //Note: array of reference Node
        heapsize = n - 1;
        Node[] node = new Node[n];
        for (int i = 0; i < n; i++) {
            node[i] = new Node();
            node[i].ascii = a[i][0];
            node[i].freq = a[i][1];
        }
        return buildMinHeap(node, n);
    }

    Node[] buildMinHeap(Node[] a, int n) {
        for (int i = n / 2; i >= 0; i--) {
            minHeapify(a, i);
        }
        return a;  //root
    }

    //min heap 'a'= array and n= length
    void minHeapify(Node[] a, int i) {
        //make heap from the node array
        //build min heap
        int smallest;
        int l = left(i);
        int r = right(i);
        if (l <= heapsize && a[l].freq < a[i].freq)
            smallest = l;
        else
            smallest = i;
        if (r <= heapsize && a[r].freq < a[smallest].freq)
            smallest = r;
        if (smallest != i) {
            //exchange a[i] and a[smallest]
            Node temp;
            temp = a[i];
            a[i] = a[smallest];
            a[smallest] = temp;
            minHeapify(a, smallest);
        }
    }

    int left(int n) {
        return 2 * n + 1;
    }

    int right(int n) {
        return 2 * n + 2;
    }

    int parent(int n) {
        return (n - 1) / 2;
    }

    // Extract minimum from heap
    Node extractMin(Node[] a) {
        if (heapsize < 0)
            System.err.println("heap underflow");

        Node min = a[0];
        a[0] = a[heapsize];
        heapsize = heapsize - 1;
        minHeapify(a, 0);
        return min;
    }

    //insert node
    void insert(Node[] a ,Node z){
        heapsize=heapsize+1;
        a[heapsize]=z;
        buildMinHeap(a,a.length);
    }
}
