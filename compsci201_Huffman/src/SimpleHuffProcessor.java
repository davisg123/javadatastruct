import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.*;

public class SimpleHuffProcessor implements IHuffProcessor {
    
    private HuffViewer myViewer;
    public BitInputStream myStream;
    public TreeNode myHuffmanTreeRoot;
    private static PriorityQueue<TreeNode> q = new PriorityQueue<TreeNode>();
    private static int[] vals;
    public int preprocessCompress(InputStream in) throws IOException {
    	myStream = new BitInputStream(in);
    	TreeNode tnode = myHuffmanTreeRoot;
    	int way = 0;
    	int[] fin = new int[ALPH_SIZE];
    	int word = myStream.readBits(BITS_PER_WORD);
    	
    	while(word != -1) {
    		way += 8;
    		fin[word]++;
    		word = myStream.readBits(BITS_PER_WORD);
    	}
    	q.clear();
    	for(int i = 0; i < 256; i++) {
    		if(vals[i] == 0 ){
    			continue;
    		}
    		q.add(new TreeNode(i, vals[i]));
    	}
    	
    	TreeNode root = null;
    	while(q.size() > 1){
    		TreeNode left = q.poll();
    		TreeNode right = q.poll();
    		int total = left.myWeight + right.myWeight;
    		TreeNode mid = new TreeNode(-1, total, left, right);
    		q.add(mid);
    		root = mid;
    	}
        return 0;
    }

    public int compress(InputStream in, OutputStream out, boolean force) throws IOException {
        throw new IOException("compress is not implemented");
        //return 0;
    }

    public void setViewer(HuffViewer viewer) {
        myViewer = viewer;
    }

    public int uncompress(InputStream in, OutputStream out) throws IOException {
        throw new IOException("uncompress not implemented");
        //return 0;
    }
    
    private void showString(String s){
        myViewer.update(s);
    }

}
