class Transaksi {
    private static int id = 1;
    private int idTransaksi;
    private Barang barang;
    private Pembeli pembeli;
    private int jumlah;
    private double totalHarga;
    Transaksi() {
        System.out.println("Transaksi berhasil dibuat");
    }
    public void TambahTransaksi(Barang b, Pembeli p, int j) {
        // id++ selalu bertambah saat melakukan transaksi
        idTransaksi = id++;
        barang = b;
        pembeli = p;
        jumlah = j;
        totalHarga = barang.getHarga() * jumlah;
    }
    public void cetakTransaksi(){
        System.out.println("ID Transaksi: " + idTransaksi);
        System.out.println("Nama Barang: " + barang.getNama_barang());
        System.out.println("Nama Pembeli: " + pembeli.getNama());
        System.out.println("Jumlah: " + jumlah);
        System.out.println("Total Harga: " + totalHarga);
    }
    // getter untuk menampilkan informasi transaksi
    public int getIdTransaksi() {
        return idTransaksi;
    }

}
