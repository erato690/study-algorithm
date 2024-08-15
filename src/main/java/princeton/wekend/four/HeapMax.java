package princeton.wekend.four;

public class HeapMax {

    private int[] heap;
    private int tail;

    public HeapMax(int capacidade) {
        this.heap = new int[capacidade];
        this.tail = -1;
    }

    public int left(int index){
        int indexLeft = 2*index+1;

        if(indexLeft > tail){
            return -1;
        }

        return heap[indexLeft];
    }


    public int right(int index){
        int indexRight = 2*(index+1);

        if(indexRight > tail){
            return -1;
        }

        return heap[indexRight];
    }

    public int parent(int index){
        int indexRight =  ((index-1)/2);

        if(indexRight == -1){
            return -1;
        }

        return heap[indexRight];
    }

    private int max_index(int index, int left, int right) {
        if (this.heap[index] > this.heap[left]) {
            if (isValidIndex(right)) {
                if (this.heap[index] < this.heap[right])
                    return right;
            }

            return index;

        } else {
            if (isValidIndex(right)) {
                if (this.heap[left] < this.heap[right])
                    return right;
            }

            return left;
        }
    }



    private boolean isLeaf(int index) {
        return index > parent(tail) && index <= tail;
    }

    private void swap(int i, int j) {
        int aux = this.heap[i];
        this.heap[i] = this.heap[j];
        this.heap[j] = aux;
    }

    public int extractMax() {
        if (isEmpty()) throw new RuntimeException("Empty");
        int element = this.heap[0];
        this.heap[0] = this.heap[tail];
        this.tail -= 1;

        this.heapify(0);

        return element;
    }


    public void sort(){

    }

    private void heapify(int index) {
        if (isLeaf(index) || !isValidIndex(index))
            return;

        // compares index, left and right to find max
        int index_max = max_index(index, left(index), right(index));

        // if current index is not greater than its children,
        // swap and keep heapifying.
        if (index_max != index) {
            swap(index, index_max);
            heapify(index_max);
        }
    }
    private boolean isValidIndex(int index) {
        return index >= 0 && index <= tail;
    }

    public static int[] resizeArray(int[] arr, int newSize) {
        // Cria um novo array com o tamanho desejado
        int[] resizedArray = new int[newSize];

        // Copia os elementos do array original para o novo array
        int elementsToCopy = Math.min(arr.length, newSize);
        System.arraycopy(arr, 0, resizedArray, 0, elementsToCopy);

        return resizedArray;
    }
    public void add(int n) {
        if (tail >= (heap.length - 1))
            heap = resizeArray(heap,heap.length*2);

        if (tail <= (heap.length / 4))
            heap = resizeArray(heap,heap.length/2);

        tail += 1;
        this.heap[tail] = n;

        int i = tail;
        while (i > 0 && this.heap[parent(i)] < this.heap[i]) {
            int aux = this.heap[i];
            this.heap[i] = this.heap[parent(i)];
            this.heap[parent(i)] = aux;
            i = parent(i);
        }
    }

    public boolean isEmpty() {
        return this.tail == -1;
    }

    public static void main(String[] args) {

    }
}
