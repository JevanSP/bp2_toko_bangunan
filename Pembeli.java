public class Pembeli {
    
        private int id;
        private String nama;
        private String alamat;
        
        Pembeli() {
            System.out.println("Pembeli berhasil dibuat");
        }
        Pembeli(int l, String n, String a) {
            id = l;
            nama = n;
            alamat = a;
            System.out.println("Object " + nama + " berhasil dibuat");
        }
        public int getId() {
            return id;
        }
        public String getNama() {
            return nama;
        }
        public String getAlamat() {
            return alamat;
        }
}
