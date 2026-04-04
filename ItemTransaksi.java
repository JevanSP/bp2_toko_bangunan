class ItemTransaksi {
    private Barang barang;
    private int harga;
    private int jumlah;
    private double subtotal;

    public ItemTransaksi(Barang b, int j) {
        this.barang = b;
        this.harga = b.getHarga();
        this.jumlah = j;
        this.subtotal = b.getHarga() * j;
    }
    // getter untuk menampilkan informasi item transaksi
    public Barang getBarang() { return barang; }
    public int getJumlah() { return jumlah; }
    public double getSubtotal() { return subtotal; }
}