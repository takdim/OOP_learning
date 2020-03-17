import java.io.IOException;

class Main {
    public static void main(String[] args) throws IOException 
    {
        ListFriends lf = new ListFriends();
        
        lf.readFiles("daftar_teman.txt");
        
        lf.showFriends();
    }
}