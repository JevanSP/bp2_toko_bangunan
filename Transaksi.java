import java.util.ArrayList;

class Transaksi {
    private static int counter = 1001;
    private int idNota;
    private Pembeli pembeli;
    ArrayList<ItemTransaksi> items = new ArrayList<>();
    private boolean isMember;
    private double totalKotor = 0;

    public Transaksi(Pembeli p, boolean m) {
        this.idNota = counter++;
        this.pembeli = p;
        this.isMember = m;
    }
    // method untuk menambahkan item ke dalam transaksi
    public void tambahItem(Barang b, int jml) {
        ItemTransaksi it = new ItemTransaksi(b, jml);
        items.add(it);
        totalKotor += it.getSubtotal();
    }
    // method untuk menghitung total setelah diskon jika pembeli adalah member
    public double getTotalSetelahDiskon() {
        double diskon = isMember ? (totalKotor * 0.1) : 0;
        return totalKotor - diskon;
    }
    // method untuk mencetak nota transaksi
    public void cetakNota() {
        System.out.println("\n------------------------------------");
        System.out.println("NOTA ID : " + idNota);
        System.out.println("Pembeli : " + pembeli.getNama());
        System.out.println("Alamat  : " + pembeli.getAlamat());
        System.out.println("Status  : " + (isMember ? "Member (Diskon 10%)" : "Umum"));
        System.out.println("Daftar Belanjaan:");
        for (ItemTransaksi it : items) {
            System.out.println("- "  + it.getBarang().getNama_barang() +  it.getBarang().getHarga() +  " (" + it.getJumlah() + "x) : Rp" + it.getSubtotal());
        }
        double potongan = isMember ? (totalKotor * 0.1) : 0;
        System.out.println("Total Belanja : Rp " + totalKotor);
        System.out.println("Potongan      : Rp " + potongan);
        System.out.println("TOTAL BAYAR   : Rp " + getTotalSetelahDiskon());
        System.out.println("------------------------------------");
    }
}
