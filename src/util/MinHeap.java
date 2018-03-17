package util;

//堆，实现ListNode的堆
public class MinHeap {
    private ListNode[] data;
    private int capacity;
    private int count;

    public MinHeap(int capacity){
        this.capacity = capacity;
        data = new ListNode[this.capacity];
        count = 0;
    }

    public int size(){
        return count;
    }

    public boolean isEmpty(){
        return count == 0;
    }

    public void insert(ListNode item){

        assert count + 1 <= capacity;
        data[count+1] = item;
        count ++;
        shiftUp(count);
    }


    private void swap(int i, int j){
        ListNode t = data[i];
        data[i] = data[j];
        data[j] = t;
    }

    public ListNode extractMin(){
        assert count > 0;
        ListNode ret = data[1];

        swap( 1 , count );
        count --;
        shiftDown(1);

        return ret;
    }

    public ListNode getMin(){
        assert( count > 0 );
        return data[1];
    }


    private void shiftUp(int k){
        ListNode temp = data[k];
        while( k > 1 && data[k/2].val - temp.val > 0 ){
            data[k] = data[k / 2];
            k /= 2;
        }
        data[k] = temp;
    }

    private void shiftDown(int k){
        ListNode temp = data[k];
        while( k + k <= count ){
            int j = k + k;
            if( j+1 <= count && data[j+1].val - data[j].val < 0 )
                j ++;
            if( temp.val - data[j].val <= 0 ) break;
            data[k] = data[j];
            k = j;
        }
        data[k] = temp;
    }
}
