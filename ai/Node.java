package ai;

public class Node {
    
    Node parent; // inisialisasi parent
    public int col; // inisialisasi  untuk menyimpan nilai kolom
    public int row; // inisialisasi untuk menyimpan nilai baris
    int gCost; // mereperesentasikan biaya dalam pencarian jalur (pathfinding)
    int hCost; // pencarian jalur untuk menyimpan nilai
    int fCost; // biaya total
    boolean solid; // mengatur apakah objek yang dibuat dengan kelas GameObject tersebut dianggap sebagai objek solid atau tidak
    boolean open; // menandai objek "terbuka atau tidak terbuka"
    boolean checked; // menandai objek atau entitas telah "dicek atau tidak dicek"

    public Node(int col, int row) {
        this.col = col;
        this.row = row;
    }
}
