public class User {
    protected String username;
    protected String sandi;

    public User(String u, String s) {
        this.username = u;
        this.sandi = s;
    }

    public boolean cekSandi(String s) {
        return this.sandi.equals(s);
    }

    public String getUsername() {
        return username;
    }
}
// Pewarisan untuk Admin dan Kasir
class Admin extends User {
    public Admin(String u, String s) {
        super(u, s);
    }
    
    public void infoRole() {
        System.out.println("Login Berhasil! Anda masuk sebagai ADMIN: " + this.username);
    }
}

class Kasir extends User {
    public Kasir(String u, String s) {
        super(u, s);
    }
    
    public void infoRole() {
        System.out.println("Login Berhasil! Anda masuk sebagai KASIR: " + this.username);
    }
}