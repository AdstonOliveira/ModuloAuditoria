
import Model.Block;
import Model.Blockchain;
import Model.Transaction;


public class Main {
    
    public static void main(String[] args) {
//        Block b = new Block();
//        System.out.println("Block ID: " + b.getId());
//        Transaction t = new Transaction();
//        
//        for(int i = 0; i < 15; i++){
//            if( b.add_transation(t).getId() != b.getId()){
//                b = b.add_transation(t);
//                System.out.println(b);
//            }
//        }
//        
////        b.calculate_hash();

    Blockchain blockchain = new Blockchain();
    for(int i = 0; i < 10; i++){
        Transaction t = new Transaction("teste");
        blockchain.add(t);
    }
    
        System.out.println(blockchain.toString());
    
    
    
        
    }
    
    
}
